#!/bin/bash

## this experiment changes delay between research of changes by each node

if [[ $# -ge 2 ]]; then

# check parameters to find the decrease value if it exists
delayCheck=0
i=0
for n in $@; do
	if [[ `echo ${n} | grep "delay="` -ne "" ]]; then
		delayCheck=`echo "${n}" | sed 's/delay=//g'`
	fi
#	echo "${n} is not the \"decrease\" parameter"
done
echo "delayCheck=$delayCheck"

echo $1
echo $2

dir=`pwd`
echo $dir

time="5000"

while [[ $delayCheck -gt "1" ]]; do
	$dir/runGregLoggerServer.sh start

	sleep 10

	#$dir/runKevoreeAgents.sh start

	sleep 15

	$dir/runBootStrap.sh $* "delay=$delayCheck"

	sleep 15

	delay=$2
	startTime=`date +%s`

	let "endTime=startTime+$1"

	while [[ $endTime -gt `date +%s` ]]; do
		echo $endTime
		echo `date +%s`
		$dir/runModification.sh
		sleep $delay
#		echo $delay
#		if [[ $delay -gt "0" ]]; then
#			let "delay=delay-decrease"
#		fi
#		echo $delay
	done

	sleep 120

	#$dir/runKevoreeAgents.sh stop

	$dir/runGregLoggerServer.sh stop

	timestamp="`date +%s`_$delayCheck"
	mkdir $timestamp
	cd $timestamp
	cp ../bootstrap/* ./
	cp ../gregServer/* ./
	cd ..
	
	echo $delayCheck
	if [[ $delayCheck -gt "5000" ]]; then
		let "delayCheck=delayCheck-5000"
		time="5000"
	elif [[ $delayCheck -gt "1000" ]]; then
		let "delayCheck=delayCheck-1000"
		time="1000"
	elif [[ $delayCheck -gt "100" ]]; then
		let "delayCheck=delayCheck-100"
		time="100"
	elif [[ $delayCheck -ge "10" ]]; then
		let "delayCheck=delayCheck-10"
		time="10"
	fi
	sleep 10
done
else
	echo "you need to define at least 2 parameters"
#	echo "usage: ./runCompleteExperimentation <length> <delay> [[sendNotification | -sendNotification] | [alwaysAskModel | -alwaysAskModel] | delay=<delay1> | decrease=<number>]"
	echo "usage: ./runCompleteExperimentation <length> <delay> [[sendNotification | -sendNotification] | [alwaysAskModel | -alwaysAskModel] | delay=<delay1> ]"
	echo "			<length> (seconds): how long it runs"
	echo "			<delay> (seconds): delay between changes"
	echo "			sendNotification specifies that all model changes on a node include notification to all nodes"
	echo "			alwaysAskModel specifies that each time there is a gossiper request, the request directly ask the model"
	echo "			<delay1> (milliseconds): delay between two gossiper requests for each nodes"
#	echo "			<number> (seconds): decrease the delay between changes by <number> after each change"
fi


