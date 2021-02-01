# gervigreind-lab3

Lab 03 for the AI course at Reykjavík University

Yann Le Lorier

Wojciech Woźniak

Ermir Pellumbi

[TOC]

## 1. State of the environment

According to the code reviewed in the ```State.java``` and the `Environment.java` file, the information we possess about the environment and state is:

- ```size_x, size_y```: The dimensions of the environment
- `home`: home position for the agent
- `obstacles`: The locations of the obstacles
- `current_state`: 
  - `dirt`: coordinates of the dirt spots
  - `position`: current agent position
  - `orientation`: current orientation
  - `turned_on`: if the agent is turned on or not

By reading and understanding the attributes of these two classes, we can make an observation that the state does not have the coordinates for the obstacles. The reason for this is that it would not make sense to store the information each time the state changes, because the obstacles can be considered to be immobile, which means that that part of the environment will not change.

***

## 2. Implementation



***

## 3. Size of the State Space

With an environment of width $W$, length $L$, and $D$ dirty spots

Assuming that the dimensions are big enough to have many possible states compared to the obstacles:

we can begin by multiplying:
$$
W \times D
$$
Then, we need to add the possible orientations of the robot (N, E, S, W):
$$
W \times L \times 4
$$
Now, we have to consider the dirt spots:
$$
S_S = W \times L \times 4+D
$$

***

## 4. Comparison of estimates



***

## 5. Bonus Points



***

## 6. Admissible heuristic?



***

## 7. A* Search implementation



***

## 8. 25 Bonus points



***

## 9. 20 Bonus Points

