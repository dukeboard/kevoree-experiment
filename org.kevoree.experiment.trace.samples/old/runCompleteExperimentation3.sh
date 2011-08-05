#!/bin/bash


if [[ $# -ge 2 ]]; then

	# check parameters to find the decrease value if it exists
	decrease=0
	failure=0
	for n in $@; do
		if [[ `echo ${n} | grep "failure="` -ne "" ]]; then
			failure=`echo "${n}" | sed 's/failure=//g'`
		fi
	#	echo "${n} is not the \"decrease\" parameter"
	done
	echo "failure=$failure"

	echo $1
	echo $2

	dir=`pwd`
	echo $dir

	cd $dir
	
	screen -A -m -d -S failureApp ./runFailure.sh

	$dir/runGregLoggerServer.sh start
	sleep 10
	$dir/runKevoreeAgents.sh start
	sleep 15
	$dir/runBootStrap.sh $*
	sleep 15
	
	delay=$2
	while [[ $failure -ne "0" ]]; do
		startTime=`date +%s`
		let "endTime=startTime+$1"
		while [[ $endTime -gt `date +%s` ]]; do
			$dir/runModification.sh
			sleep $delay
			if [[ $delay -gt "0" ]]; then
				let "delay=delay-decrease"
			fi
		done
		# add a failure
		screen -p 0 -S failureApp -X eval 'stuff \"down\"\015'
		let "failure=failure-1"
		echo "failure=$failure"
	done

	sleep 120

	oarsh $i "screen -p 0 -S failureApp  -X eval 'stuff \"q\"\015'" &

	$dir/runKevoreeAgents.sh stop

	$dir/runGregLoggerServer.sh stop

	timestamp=`date +%s`
	mkdir $timestamp
	cd $timestamp
	cp ../bootstrap/* ./
	cp ../gregServer/* ./
	cd ..
else
	echo "you need to define at least 2 parameters"
	echo "usage: ./runCompleteExperimentation <length> <delay> [[sendNotification | -sendNotification] | [alwaysAskModel | -alwaysAskModel] | delay=<delay1> | failure=<number>]"
	echo "			<length> (seconds): how long it runs"
	echo "			<delay> (seconds): delay between changes"
	echo "			sendNotification specifies that all model changes on a node include notification to all nodes"
	echo "			alwaysAskModel specifies that each time there is a gossiper request, the request directly ask the model"
	echo "			<delay1> (milliseconds): delay between two gossiper requests for each nodes"
	echo "			<number>: number of link failure to reach"
fi


