#!/bin/bash
if [ $# -eq 0 ]; then
    sound=0
    dev=/dev/rfcomm0
elif [ $# -eq 1 ]; then
    sound=$1
    dev=/dev/rfcomm0
elif [ $# -ge 2 ]; then
    sound=$1
    dev=$2
fi

#Play all sounds
if [ $sound -eq 0 ]; then
    echo Play all sounds ...
    echo
else
    echo Play sound ...
fi

if [ $sound -eq 1 -o $sound -eq 0 ]; then
    echo Axel F ...
    echo "BLDC sound 1" > $dev
    sleep 8
fi

if [ $sound -eq 2 -o $sound -eq 0 ]; then
    echo Tetris theme ...
    echo "BLDC sound 2" > $dev
    sleep 25
fi

if [ $sound -eq 3 -o $sound -eq 0 ]; then
    echo A-Team ...
    echo "BLDC sound 3" > $dev
    sleep 22
fi

if [ $sound -eq 4 -o $sound -eq 0 ]; then
    echo Insomnia ...
    echo "BLDC sound 4" > $dev
    sleep 15
fi

if [ $sound -eq 5 -o $sound -eq 0 ]; then
    echo Popcorn ...
    echo "BLDC sound 5" > $dev
    sleep 15
fi

if [ $sound -eq 6 -o $sound -eq 0 ]; then
    echo Sandstorm ...
    echo "BLDC sound 6" > $dev
    sleep 17
fi

if [ $sound -eq 7 -o $sound -eq 0 ]; then
    echo Airwolf Theme
    echo "BLDC sound 7" > $dev
    sleep 19
fi

if [ $sound -eq 8 -o $sound -eq 0 ]; then
    echo Imperial march ...
    echo "BLDC sound 8" > $dev
    sleep 33
fi

echo finished
