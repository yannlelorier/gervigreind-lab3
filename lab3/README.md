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

We can begin by getting the total surface area, times the number of possibilities of facing one cardinal point (4). This represents any place where the agent can be where there is no dirt. Now, we need to take into consideration the dirt spots, which are a state of their own:
$$
\begin{align}
S_S &= (W \times L) \times 4 +D\times 4\\
	&= 4WL+4D\\
	&= 4(WL+D)
\end{align}
$$

***

## 4. Comparison of estimates

<u>**Depth-First Search**</u>:

- Completeness: This algorithm is not complete
- Optimality: It is not always optimal
- Space and Time complexity:
  - Time: $O(3^m)$
  - Space: $O(3^m)$

In order to make it complete, we must discard the nodes we've already visited.

<u>**Breadth-First Search**</u>:

- Completeness: This algorithm is by definition complete. As long as a solution exists, the algorithm will return it.
- Optimality: This algorithm is optimal, because since it expands every node, it will find the shortest solution (if it $\exists$)
- Space and Time complexity: 
  - Time: $O(3^d)$ where $N$ is the set of all nodes.
  - Space: $O(3^d)$ again since all of the vertices must be remembered (worst case)

<u>**Uniform-Cost Search**</u>:

- Completeness: It is complete if the solution exists
- Optimality: It is optimal as it always selects the path with the lowest cost.
- Space and Time complexity:
  - Time: $O(3^{c/e})$ where $e$ is the minimum cost of an edge and $c$ is the cost to the optimal solution
  - Space: $O(3^{c/e})$ it is the same as it needs to remember the path to the optimal solution

**<u>A* Search</u>**:

- Completeness: It is complete
- Optimality: It is optimal
- Space and Time complexity:
  - Time: $O(3^d)$ 
  - Space: $O(3^d)$

It seems like a bad idea to use it. However, when using a good heuristic, these complexities go down.

***

## 5. Bonus Points

The size of the search tree and the size of the state space differ because the search tree has to take into account all of the possible actions and still expand (even if they are illegal)

***

## 6. Admissible heuristic?

Assuming the space has relatively more spaces than obstacles, the heuristic is admissible. *i.e.* we are relaxing the problem.

This is because the problem is discretized, so the Manhattan distance (lateral moves) is the one that is sufficient for this problem.

***

## 7. A* Search implementation



***

## 8. 25 Bonus points (Better heuristic)



***

## 9. 20 Bonus Points (Detection of revisited states)

