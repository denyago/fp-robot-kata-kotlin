# Functional Programming Kata in Kotlin

The aim is to implement a function that will move a robot in a forest.

Inputs are the map of the forest and commands. The robot starts in the top left corner.
Output is the map of the forest after the robot has moved.

Tasks:
- Implement the function `moveRobot` in `Game.kt`
- Additional: Implement logic where the robot will go around a tree if it encounters one
- Additional: Refactor the parsing code in `Game.kt` to be more functional
- Additional: Add validations to the input
- Additional: Make an interactive mode for the game 

Limitations. You can't ...:
- set any `val` or `var` inside the functions you write
- use any loops
- use any mutable data structures
- do `try { } catch { }` or `throw`