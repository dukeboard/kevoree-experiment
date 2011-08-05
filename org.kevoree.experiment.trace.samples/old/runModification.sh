#!/bin/bash

PWD_OLD=`pwd`
rm -rf modification;mkdir -p modification;cd modification;java -cp ../org.kevoree.experiment.modelScript-1.2.0-SNAPSHOT.jar org.kevoree.experiment.modelScript.ModificationApp
cd $PWD_OLD
