#!/bin/bash
COUNTER=1
until [  $COUNTER -gt 72 ]; do
    java World config.txt 1000 $COUNTER
    let COUNTER+=10
done
