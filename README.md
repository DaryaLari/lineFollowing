# lineFollowing
Control Theory assignment. EV3 + LeJOS

This is a program for Lego Mindstorm line-following robot with 2 light-sensors.
It uses PID controller with error taken as a difference between values taken from two sensors.

I've chosen Kp, Ki and Kd values firstly by picking random values and then by making some corrections of the most successful of them.
My steps:
1) Choose Kp value (with Ki = 0 & Kd = 0), when the robot is able to follow the line, but the oscillations are quite big;
2) Select Ki & Kd for already chosen Kp.

The main difficulty was in selecting values for PID coefficients in such way, that the robot could follow even with sharp bends (almost 90 deg (as a yellow line on the first video)).

There was a trade-off between accuracy of robot's trajectory and its speed. I've chosen to increase accuracy by decreasing robot's speed.

I think that this solution is optimal, since the robot can remain stable even on quite large bends and at the same time it is not much limited with the speed.

Link to youtube video:
https://youtu.be/JxugYgWeYkg
(additional):
https://youtu.be/CtYLKcgbnsc
