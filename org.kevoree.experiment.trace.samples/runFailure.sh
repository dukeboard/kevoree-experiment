#!/bin/bash

PWD_OLD=`pwd`
mkdir failure;cd failure;java -cp ../org.kevoree.experiment.modelScript-1.2.0-SNAPSHOT.jar org.kevoree.experiment.modelScript.FailureApp poisson
cd $PWD_OLD
