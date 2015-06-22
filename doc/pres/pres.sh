#!/bin/bash

# Set screen propperly
echo Setting up screens
killall redshift
xrandr --output HDMI1 --off
xrandr --output VGA1 --off
xrandr --output VGA1 --mode 1024x768
xrandr --output LVDS1 --mode 1024x768

# start presentation
echo Starting presentation
evince -s pres.pdf &> /dev/null &

echo done
