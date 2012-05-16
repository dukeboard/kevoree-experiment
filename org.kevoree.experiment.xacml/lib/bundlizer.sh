#!/bin/bash

echo "install bundles"


bnd wrap -output sunxacml.jar sunxacml.jar
mvn install:install-file -Dfile=sunxacml.jar -DgroupId=sun.xacml -Dversion=1.0 -Dpackaging=jar -DartifactId=xacml



exit 0


