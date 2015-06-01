#!/bin/bash
if [ $# -eq 0 ]; then
    position=0
    dev=/dev/rfcomm0
elif [ $# -eq 1 ]; then
    position=$1
    dev=/dev/rfcomm0
elif [ $# -ge 2 ]; then
    position=$1
    dev=$2
fi

#initialize device, start bldc and wait until ready
echo initialize device ...
echo "l6480 goto 0" > $dev
echo "BLDC on" > $dev
sleep 15
echo ready
sleep 1

# positioning
echo aiming ...
echo "l6480 goto $position" > $dev
sleep 0.3

#shooting
echo shooting ...
echo "shoot" > $dev
sleep 2.5

#prepare for reload and next round
echo finished
echo "BLDC off" > $dev
echo "l6480 goto 0" > $dev
sleep 1

echo "BLDC sound" > $dev
echo reload ...
