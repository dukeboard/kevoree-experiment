#!/bin/sh


if [[ $# -ge 2 ]]; then

echo $1
echo $2

dir=`pwd`
echo $dir

$dir/runGregLoggerServer.sh start

sleep 10

$dir/runKevoreeAgents.sh start

sleep 15

$dir/runBootStrap.sh $*

sleep 15

delay=$2
startTime=`date +%s`

let "endTime=startTime+$1"

while [[ $endTime -gt `date +%s` ]]; do
	echo $endTime
	echo `date +%s`
        $dir/runModification.sh
        sleep $delay
done

sleep 120

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
	echo "usage: ./runCompleteExperimentation <length> <delay> [[sendNotification | -sendNotification] | [alwaysAskModel | -alwaysAskModel] | delay=<delay1>]"
	echo "			<length> (seconds): how long it runs"
	echo "			<delay> (seconds): delay between changes"
	echo "			sendNotification specifies that all model changes on a node include notification to all nodes"
	echo "			alwaysAskModel specifies that each time there is a gossiper request, the request directly ask the model"
	echo "			<delay1> (milliseconds): delay between two gossiper request for each nodes"
fi


