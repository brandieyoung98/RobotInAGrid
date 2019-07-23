# RobotInAGrid
My solution to problem 8.2 (Cracking the Coding Interview, 6th Edition):  Imagine a robot sitting on the upper left corner of grid with r rows and c columns. The robot can only move in two directions, right and down, but certain cells are "off limits" such that the robot cannot step on them. Design an algorithm to find a path for the robot from the top left to the bottom right. 

Dynamic Programming is used so that the runtime is not exponential. The runtime is O(rows * columns) because we visit each grid space at most once. If the space has already been visited it will be contained in my fails ArrayList and fail the conditional if visited again after failing.
