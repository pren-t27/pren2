echo "l6480 goto 0" > /dev/rfcomm0; 
echo "BLDC on" > /dev/rfcomm0; 
sleep 16; 
echo "l6480 goto 5500" > /dev/rfcomm0; 
sleep 0.3; 
echo "shoot" > /dev/rfcomm0; 
sleep 2.5; 
echo "BLDC off" > /dev/rfcomm0; 
echo "l6480 goto 0" > /dev/rfcomm0;
sleep 1; 
echo "BLDC sound" > /dev/rfcomm0
