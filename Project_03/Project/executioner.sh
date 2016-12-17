#!/bin/bash
COUNTER=1
until [  $COUNTER -gt 3 ]; do
	COUNTER_INNER=1
	until [  $COUNTER_INNER -gt 72 ]; do
    java Simulation ../config.txt 1000 ./graph_0$COUNTER.txt $COUNTER_INNER
		let COUNTER_INNER+=10
	done
  let COUNTER+=1
done
