#!/bin/bash

echo "install bundles"


#bnd wrap -output org.eclipse.emf.common.jar org.eclipse.emf.common_2.7.0.v20110912-0920.jar
#mvn install:install-file -Dfile= org.eclipse.emf.common.jar -DgroupId=org.eclipse.emf -Dversion=1.0 -Dpackaging=bundle -DartifactId=emf.common
#mvn install:install-file -Dfile= org.eclipse.emf.common.jar -DgroupId=org.eclipse.emf -Dversion=1.0 -Dpackaging=jar -DartifactId=emf.common

bnd wrap -output org.eclipse.emf.ecore.xmi.jar org.eclipse.emf.ecore.xmi_2.7.0.v20110520-1406.jar
mvn install:install-file -Dfile=org.eclipse.emf.ecore.xmi.jar -DgroupId=org.eclipse.emf -Dversion=1.1 -Dpackaging=bundle -DartifactId=emf.ecore.xmi
#mvn install:install-file -Dfile=org.eclipse.emf.ecore.xmi.jar -DgroupId=org.eclipse.emf -Dversion=1.1 -Dpackaging=jar -DartifactId=emf.ecore.xmi

bnd wrap -output org.eclipse.emf.ecore.jar org.eclipse.emf.ecore_2.7.0.v20110912-0920.jar
mvn install:install-file -Dfile=org.eclipse.emf.ecore_2.7.0.v20110912-0920.jar -DgroupId=org.eclipse.emf -Dversion=1.1 -Dpackaging=bundle -DartifactId=emf.ecore
#mvn install:install-file -Dfile=org.eclipse.emf.ecore_2.7.0.v20110912-0920.jar -DgroupId=org.eclipse.emf -Dversion=1.1 -Dpackaging=jar -DartifactId=emf.ecore

bnd wrap -output org.eclipse.viatra2.emf.incquery.runtime.jar org.eclipse.viatra2.emf.incquery.runtime_0.4.0.201201150015.jar
mvn install:install-file -Dfile=org.eclipse.viatra2.emf.incquery.runtime.jar -DgroupId=org.eclipse.viatra2 -Dversion=1.1 -Dpackaging=bundle -DartifactId=viatra2.emf.incquery.runtime
#mvn install:install-file -Dfile=org.eclipse.viatra2.emf.incquery.runtime.jar -DgroupId=org.eclipse.viatra2 -Dversion=1.1 -Dpackaging=jar -DartifactId=viatra2.emf.incquery.runtime

bnd wrap -output org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.jar org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete_3.3.0.201201150015.jar
mvn install:install-file -Dfile=org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.jar -DgroupId=org.eclipse.viatra2 -Dversion=1.1 -Dpackaging=bundle -DartifactId=viatra2.gtasm.patternmatcher.incremental.rete
#mvn install:install-file -Dfile=org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.jar -DgroupId=org.eclipse.viatra2 -Dversion=1.1 -Dpackaging=jar -DartifactId=viatra2.gtasm.patternmatcher.incremental.rete


exit 0


