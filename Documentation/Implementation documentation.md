
# Implementation documentation
## Values for memory and processing calculations
- a is the size of board (xLength time yLength).
- p tells how deep the recursion of minmax algorithm goes.
- n is the amount of values in HashMap or ArrayList.
## Memory and time compexity
### Memory compexity

#### Board and GameStatus
- Boards memory usage is O(a).
- Gamestatus memory usage is the same as board O(a) = S.
#### MinMax
MinMax generates children of all possible GameStatuses. At first it generates a amount of boards then from that board configuration a-1 boards.<br> 
This continues until depth p is reached so the Memory requirement is O((a!/(a-p)!)S)=O((a!/(a-p)!)a)=O(a!/(a-p)!).<br>
Because of alpha beta pruning, the worst case is rarely reached.
#### HashMap and ArrayList
ArrayList and Hashmaps memory requirements are O(n).
#### Other methods
Constant memory complexities.
### Time complexity
#### Board and GameStatus
- Boards board setup, copying, string generation, hash generation time complexities are O(a), the rest are constants.
- GameStatus victorychecks are O(a)=c (worst case for example, 1 height 4 length, 4 victory length board: x,x,x,x).
- GameStatus checkAll is O(ac)=O(a^2), realistically this worst case is rarely reached. 
- Other Gamestatus method time complexities are constants.
#### Heuristics
- Heuristics has multiple different methods which time complexities are O(a)=d (For example heuristic vertical has similar time complexity to Gamestatus victorychecks)
- Heuristics evaluate time complexity is then O(ad)=O(a^2)=d.
#### MinMaxAI
MinMax generates children of all possible GameStatuses. At first it generates a amount of boards then from that board configuration a-1 boards.<br> 
This continues until depth p so at worst a!/(a-p)! calculations in which GameStatus checkAll c=O(a) is used and at the end of the recursion d=O(a^2)evaluate method. <br>
So O(a!/(a-p)!cd)=O(a!/(a-p)!a^3)=O(a!/(a-p)!).
<br>
Because of alpha beta pruning, the worst case is rarely reached.
#### HashMap and ArrayList
- ArrayList size increase time complexity is O(n), thus add methods time complexity is O(n), the rest are constants (no remove function).
- HashMap put requires O(a) time complexity for hash and o(n) for ArrayList size increase, so the timecomplexity is O(an)=O(c). Get method is constant. (Hashmap list size is always 10000000 currently).
#### Other methods
Constant time complexities.
### Ways to limit worst case scenarios
This ai uses few pruning methods to limit the worst case scenarios. It uses the standard alpha beta pruning and if the board has 3 pieces or more in it, it wont judge cases where there is not an 'O' or 'X' within 2 tiles in any direction.
## Logic structure
### Simple packet logic
<img src="https://raw.githubusercontent.com/JaakkoRE/Extended-tic-tac-toe-AI/master/Documentation/Images/simple%20logic.png"> <br>
### Method logic
<img src="https://raw.githubusercontent.com/JaakkoRE/Extended-tic-tac-toe-AI/master/Documentation/Images/Complex%20logic.png"> <br>
## Flaws 
- HashMap implementation is still flawed, reduces performance.
- The AI assumes that the other player plays like the AI (min max algorithm) so if there is a way for the opponent to win, all moves are all as bad since the opponent can win from any move. (AI still tries to win as fast as possible or to lose as fast as possible)
- Time requirements jump a lot from board size to size. Increasing depth has huge impact on the calculation times.
- Heuristic estimations are not perfect.
- Bigger boards limit the depth because of the calculation times leading to flawed plays.
- Heuristics are made for general purpose, they might work better on some boards than on others.
- Graphical and text userinterface could be more userfriendly and in general made to work better.
## References
- http://www.cse.yorku.ca/~oz/hash.html used for djb 2 hash
- https://en.wikipedia.org/wiki/Minimax used for minMax algorithm
- Tietorakenteet ja algoritmit Antti Laaksonen (2020) used for ArrayList and HashMap
