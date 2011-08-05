#!/bin/bash

PWD_OLD=`pwd`
rm -rf bootstrap;mkdir bootstrap;cd bootstrap;java -jar ../org.kevoree.experiment.modelScript-1.2.0-SNAPSHOT.jar $*
cd $PWD_OLD
