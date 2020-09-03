#!/bin/bash
PROJECTS=" sunflower watermelon kiwi cherry strawberry"
WORKING_DIR=$PWD
for PRO in $PROJECTS; do
	cd $PRO
	echo "Building $PRO"
	sleep 1
	mvn -DskipTests clean package
	cd $WORKING_DIR
done