# aueb-mini-projects

## Athens University of Economics and Business ([AUEB](https://aueb.gr/))
[Coding Factory](https://codingfactory.aueb.gr/) @ [AUEB](https://aueb.gr/) Java Projects. Chapter 10. Mini-Projects

## Projects info
## THEATER SEAT MANAGEMENT APP
Theater booking management application. Uses characters for columns and numbers for rows. Example: Seat C2 is at second
row , third column. The whole theater has 30 rows and 12 columns. Has methods for booking a row if it is not
already booked. Method for booking cancellation for booked seats.

## TIC-TAC-TOE APP
A simple Tic Tac Toe App.
Explanation: Two players play in turns and mark a 3x3 `Array` with X and O. One symbol for each player.
Winner is the one that manages to fill three consecutive elements with his symbol
in any dimension of the `Array` (horizontally,vertically,diagonally). Input with two `int`, first for row second
for column. example: 2 3 -> second row , third column.

## MAXIMUM-SUM-SUB-ARRAY APP
Finds the maximum sum subArray of a given `Array`. Popular algorithm called Kadane's algorithm.
It is based on the simple notion that , while using a `for` loop to examine the whole `Array` once
(in order to not increase the time complexity), because each next possible `Array` we
examine has next number of the `Array` added to it , we don't need to recalculate. We just examine
if the past max `Array` , when having the next `Array` element added to it . . . if it has bigger `sum` than the previous.

## INT-FILTERING APP
Disclaimer : Due to random generation of `int` it might not find adequate filtered group on the first run !

`Int` filtering Application.(Firsts makes a file with 49 random `int`).
Then reads `int` numbers from that file with values from 1 to 49. Creates all possible combinations of 6 numbers
and filters them so that they fill the following criteria: 1) Consists of `max` 4 even numbers. 2) consists of `max` 4 odd numbers
3) consists at most two consecutive numbers. 4) consists at most three numbers with same last digits. 5)Has at most 3 numbers
in same range of magnitude.

## SHALLOW-COPY-EXAMPLE APP
Exhibition application for shallow-copy and deep-copy.
Shallow-copies a two-dimensional `Array` to a second one. Make changes to the second one and prints on console (std output) the effect on the first one.
Deep-copies again the first two-dimensional `Array` to a third one. Make changes to the third one and prints on console the effect on the first one.

## Installation
To install and run the projects, follow these steps:
1. Clone the repository to your local machine
2. Run each project

