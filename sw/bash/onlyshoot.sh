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

# positioning
echo aiming ...
echo "l6480 goto $position" > $dev
sleep 0.3

#shooting
echo shooting ...
echo "shoot" > $dev
echo "BLDC setrpm 1372" > $dev
sleep 2.5

#prepare for reload and next round
echo finished
echo "BLDC off" > $dev
echo "l6480 goto 0" > $dev
sleep 1

echo "BLDC sound 5" > $dev
echo reload ...
