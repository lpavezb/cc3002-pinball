# Pinball - Tarea 2 CC3002

Implementation of a Pinball game given a base code.


## Getting Started

These instructions will get you a copy of the project up and running 
on your local machine for development and testing purposes.


### Prerequisites

- Java 8 - [*How to install the JDK on Ubuntu Linux*](https://stackoverflow.com/questions/14788345/how-to-install-the-jdk-on-ubuntu-linux)

- IntelliJ

### Installing

Download the source code in github - [source code](https://github.com/lpavezb/cc3002-pinball) 

Open the project with IntelliJ

## Project implementation
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

## Patterns

The main design patterns used in this project are the Observer pattern and the Visitor pattern, 
the class Game (the controller) is an Observer, that is observing the GameTable, that is an Observer
and an Observable, and the Table is observing the hittable elements (Bumpers and Targets), 
the table uses the visitor pattern to implement methods that depends on the type of the Hittable
(like the getNumberOfDropTargets method, it creates a TargetVisitor and ask the visitor to do the operation).
When a Hittable is hit, it creates an IVisitor object and notify the Table (sending the IVisitor to the Table),
then the table notify the Game (also sending the IVisitor), and the Game "triggers" the IVisitor, 
this results in triggering a Bonus or a change in the game score.

## Location of each module

- The Game class is in the package controller
- The Bonus classes are in the package logic.bonus
- The Hittable classes are in the package logic.gameelements
    - Targets are in the package logic.gameelements.target
    - Bumpers are in the package logic.gameelements.bumper
- The Table classes are in the package logic.table
- The Visitor classes are in the package logic.visitor
- The "inverse" visitor classes are in the package logic.inverseVisitor

## Run Tests

To run the tests (and you are using IntelliJ) you have to right click the java directory 
(the one inside the test directory) and click Run 'All Tests' or Run 'All Tests' with Coverage.
To configure what package is included in the coverage go to the 'Edit Configurations...' option, 
then go to the Code Coverage section and in the 'Packages and classes to record coverage data' 
add the packages controller and logic, then Run 'All Tests' with Coverage again.

## Authors

* **Juan-Pablo Silva** - [*Pinball Base Code*](https://github.com/lpavezb/cc3002/tree/master/PinballBaseCode) - [juanpablos](https://github.com/juanpablos)
* **Lukas Pavez** - *homework implementation* - [lpavezb](https://github.com/lpavezb)

