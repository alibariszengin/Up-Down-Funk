
# AVM Threading Elevator System 


A shopping mall system that asynchronously manages 4 different elevators as 4 different threads for people in a mall to go from the ground floor to the upper floors and from the upper floors to the ground floor.

Developed in 2021.

## Table of contents
* [General info](#general-info)
* [Technologies-Tools](#technologies-tools)
* [Modules](#modules)
* [Setup](#setup)
* [Contact](#contact)


## General Info

Let's say there was a shopping mall one day. This mall would admit a random number of people between 0-10 every 500ms.

These people he bought would stand in line to go from the ground floor to the floor he wanted. At this point, there were 4 noble elevators in the shopping mall trying to fulfill their duties with great dignity. 

These 4 elevators have a quota of 10 people and pass between each floor in 200ms, distribute the people in the queue to the floors who want to go up to the floors, and on the way back, they pick up and drop people from the floors where 0-5 random people queue to exit every 1000ms.

<div  align="center">
	<img src="https://github.com/alibariszengin/Up-Down-Funk/blob/main/src/elevator/Up-down.gif?raw=true"   height="400">
	</div>



Elevators handles all the operations as 4 different threads and 

## Technologies-Tools
Project is created with:
* Java 11
* Intellij Idea
* Java Swing
* Java Threads

## Modules
- There are 4 elevators as "ElevatorThread" class to operate elevator operations.
- "AVM" class to hold floors details and synchronize some of the operations like change the floors queues.
- "ControlThread" to bind and control all the operations and classes.
- "ExitThread" and "LoginThread" to move customers to login or exit the AVM queues randomly.
- "InterfaceForm" to display the all the changes on floor queues and elevators on swing interface.
- "Person" class to represent customers.

## Setup
To run this project, open the project with your IDE and run the Main class. 

## Contact

Ali Barış Zengin  -  [alibariszengin@gmail.com](mailto:alibariszengin@gmail.com)

Project Link:  [https://github.com/alibariszengin/Up-Down-Funk](https://github.com/alibariszengin/Up-Down-Funk)
