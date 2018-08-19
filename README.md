# Pinball - Homework 2 and 3 CC3002

Implementation of a Pinball game given a base code.


## Getting Started

These instructions will get you a copy of the project up and running 
on your local machine for development and testing purposes.


### Prerequisites

- Java 8 - [*How to install the JDK on Ubuntu Linux*](https://stackoverflow.com/questions/14788345/how-to-install-the-jdk-on-ubuntu-linux)

- JavaFX

- IntelliJ

### Installing

Download the source code in github - [source code](https://github.com/lpavezb/cc3002-pinball) 

Open the project with IntelliJ

## Project implementation
### Logic
For the homework implementation I followed these steps:

Create the AbstractHittable (abstract class) that implements the 
common methods for Bumpers and Targets defined in the Hittable interface.

Create the AbstractBumper (abstract class) class that extends the 
AbstractHittable class and implements the Bumper interface.

Create the requested Bumpers, where the constructor calls the AbstractBumper constructor
with the Bumper information (initial and upgrade score, and the hit times to upgrade), and an 
accept method for the visitor pattern.

Create the AbstractTarget (abstract class) class that extends the 
AbstractHittable class and implements the Target interface.

Create the requested Targets, where the constructor calls the AbstractBumper constructor
with the Target score, and each Target implements the hit and accept methods.

Create the AbstractBonus (abstract class) that implements the Bonus interface. The AbstractBonus class
contains the number of times that the bonus has been triggered.

Create the 3 Bonus classes that extends the AbstractBonus class. Each Bonus implements the trigger 
method.

Create the GameTable class and the NullTable class, both implements the Table interface.

Create the Visitor abstract class with 4 empty visit methods (one method for each Hittable), 
and the visitTable method, for the visitor can access the hittable elements stored in the table.

Create a visitor class for each Hittable with the desired methods, and a visitor for Targets (TargetVisitor)
and another for Bumpers (BumperVisitor)

Create the interface IVisitor for an "inverse visitor" pattern where each visitor has an accept
method and a trigger method, there is and IVisitor for each Bonus and one to modify the score of the game.

#### Additions to homework 2:
Added automatic reset of DropTargets on DropTargetBonus

#### Homework 3:
Create the main application class GUI (extends GameApplication).

Create the enum GameType, includes the types used in the game.

Create the PinballFactory, this class contains the method to create the entities used in the game.

Create the Component classes, this classes are for controlling the game entities, BallComponent controls the ball 
(this class was obtained from the [Pong](https://github.com/AlmasB/FXGLGames/tree/master/Pong) game and modified), 
FlipperControl the Flippers, BumperControl the Bumpers and TargetControl the Targets.

The BumperControlFactory and TargetControlFactory are for building the Components BumperControl and TargetControl.

## Patterns

The main design patterns used in this project are the Observer pattern and the Visitor pattern, 
the class Game (the controller) is an Observer, that is observing the GameTable, that is an Observer
and an Observable, and the Table is observing the hittable elements (Bumpers and Targets), 
the table uses the visitor pattern to implement methods that depends on the type of the Hittable
(like the getNumberOfDropTargets method, it creates a TargetVisitor and ask the visitor to do the operation).
When a Hittable is hit, it creates an IVisitor object and notify the Table (sending the IVisitor to the Table),
then the table notify the Game (also sending the IVisitor), and the Game "triggers" the IVisitor, 
this results in triggering a Bonus or a change in the game score.

#### Additions to homework 2:

Factory pattern for Table creation

#### Homework 3

Factory pattern for BumperControl and TargetControl.


## Graphical User Interface

This project use the library FXGL.

### Core

The elements shown in the screen are:

- Flippers: blue rectangles
- Ball: red circle 
- Bumpers: all bumpers are circles, the color depends on the type:
    - KickerBumpers: blue
    - PopBumpers: cyan
- Targets: all targets are squares, the color depends on the type:
    - DropTarget: yellow
    - PopBumpers: white
- Information: the information of actual score and lives is shown in a white bar at the top of the screen.

The user can do the following actions:

- Active a Flipper: with the keys A and S the user can active the left and right flipper respectively.
- New Ball: if there are no balls on the screen, the user can spawn a new one with the SPACE key.
- New Table: at any moment can be spawn a new table with the key N.

The interactions that can happen in the screen are:

- Gravity: there is a gravity force in the y axis.
- Flippers: when activated, it spin around their center until reaching a max rotation, after releasing the key,
 it returns to the original position.
- Tables: when a table is spawn, there are 14x6 available positions in which each hittables can spawn (two hittables 
can't be in the same position). The available positions are between the info bar and the middle of the screen.
- Walls: there are walls surrounding the visible space of the screen, and the ball bounce when is colliding with a wall, 
except for the bottom wall, in this case the ball is removed from the game and a life point is lost.
- Bounces: the ball bounce when hitting a wall or a hittable in the opposite direction, same force.
- Ball spawning: when a ball is spawn, it appear in the center of the screen, with a small velocity in the y axis.

### Features

The game includes the following features:

- Different state: when a hittable changes its state, it also changes its color:
    - KickerBumpers: blue -> dark green
    - PopBumpers: cyan -> lawn green
    - DropTarget: yellow -> red
    - PopBumpers: white -> purple
- Hittable sounds: when the ball hits a hittable, a sound is played, there is two sounds for each type of hittable, 
one sound for the initial state and other for the changed state (Bumper upgraded or Target deactivated). 
There is also a sound when a Target is deactivated and other when a Bumper is upgraded. A total of 10 different sounds.
- Timers: when a hittable changes state, it comes back to the previous state after a given time for each type of hittable.
 Because I could not implement this feature with the master timer, i did it in a different way, each entity component
 has two variables, time and a timeControl, on the onUpdate method the timeControl increase in 1, and if timeControl%60 = 0,
 time increase in 1, this method assumes that there are 60 fps. The time obtained is not exact, but is close. 
- Testing mechanism: the user can test hit hittable hits with a mouse click on top of the hittable.
- Different flippers: each flipper can be activated separately with different keys (A and S).
- Sparks on hitting: when the ball hits a hittable, sparks particles appear for a short time.
- Bonus with sounds: when a bonus is triggered, a sound is played, each one of the three bonus has a different sound.

 

## Location of each module

- The Game class is in the package controller
- The Bonus classes are in the package logic.bonus
- The Hittable classes are in the package logic.gameelements
    - Targets are in the package logic.gameelements.target
    - Bumpers are in the package logic.gameelements.bumper
- The Table classes are in the package logic.table
- The Visitor classes are in the package logic.visitor
- The "inverse" visitor classes are in the package logic.inverseVisitor
- The GUI classes are in the package gui

## Run Tests

To run the tests (and you are using IntelliJ) you have to right click the java directory 
(the one inside the test directory) and click Run 'All Tests' or Run 'All Tests' with Coverage.
To configure what package is included in the coverage go to the 'Edit Configurations...' option, 
then go to the Code Coverage section and in the 'Packages and classes to record coverage data' 
add the packages controller and logic, then Run 'All Tests' with Coverage again.

## Run the game

To run the game (using IntelliJ) you have to right click the GUI class (inside the gui package) and click
 'Run GUI.main()'.
 
 
## Authors

* **Juan-Pablo Silva** - [*Pinball Base Code*](https://github.com/lpavezb/cc3002/tree/master/PinballBaseCode) - [juanpablos](https://github.com/juanpablos)
* **Lukas Pavez** - *homework implementation* - [lpavezb](https://github.com/lpavezb)

## Libraries

* [FXGL](https://github.com/AlmasB/FXGL)

## Sounds

* All sounds effects belong to the game Zelda - Ocarina of Time - [Source](http://noproblo.dayjo.org/ZeldaSounds/)

## Bugs

There is a bug that I couldn't fix, it happens sometimes when the ball is removed from the game, and a 
NullPointerException is thrown, when this happens the game is terminated.

A way I tried to avoid the error (and the one I decided to implement) is to remove the PhysicsComponent of the ball and 
then remove the ball, this actually avoid the error, but leaves an 'invisible' ball in the game, this invisible balls
sometimes collides with the current ball and changes its direction.

Other way is to get the body of the PhysicsComponent, remove the PhysicsComponent, destroy the body after 0.1 seconds 
and remove the ball, but with this also throws the error, for this I decided to stay with the first option.
 