# Implementation documentation
## Values for memory and processing calculations
- a is the size of board (xLength time yLength)
- p tells how deep the recursion of minmax algorithm goes.
## Memory usage
### Board and GameStatus
- Boards memory usage is O(a)
- Gamestatus memory usage is the same as board O(a) = S
### MinMax
MinMax generates children of all possible GameStatuses. At first it generates a amount of boards then from that board configuration a-1 boards.<br> 
This continues until depth p is reached so the Memory requirement is O((a!/(a-p)!)S)=O(a(a!/a-p)!)=O(a!/(a-p)!)
## Flaws 
- HashMap implementation is still flawed, reduces performance.
- The AI assumes that the other player plays like the AI (min max algorithm) so if there is a way for the opponent to win, all moves are all as bad since the opponent can win from any move. (AI still tries to win as fast as possible or to lose as fast as possible)
- Time requirements jump a lot from board size to size. Increasing depth has huge impact on the calculation times.
- Heurstic estimations are not perfect.
- UI could be more user friendly.
- Bigger boards limit the depth because of the calculation times leading to flawed plays.
- Heuristics are made for general purpose, they might work better on some boards than on others.
## References
- http://www.cse.yorku.ca/~oz/hash.html used for djb 2 hash
- https://en.wikipedia.org/wiki/Minimax used for minMax algorithm
- Tietorakenteet ja algoritmit Antti Laaksonen (2020) used for ArrayList and HashMap
