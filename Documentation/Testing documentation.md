# Testing documentation
## How has code been tested and what has been tested.
Testing has been done with different JUnit tests.
The Tests can be performed using JUnit framework.<br/>
Code is generally tested using plenty unit test.
Few larger tests have been done using small boards (3x3, 2x2) Big Board testing is work in progress.<br/>
Plenty of manual testing has been done.
UI is tested manually and is work in progress.
Also few time tests have been done and other performance tests.
## What inputs have been used in testing
Unit tests have been tried to made using diverse situations and with different edge cases.<br/>
Time testing has been made using diverse linearly comperable boards.<br/>
Other performance tests (Heuristic tournament and X loses tests) use random values and premade ones.
## Code coverage and test result information
### jacoco
jacoco report <br>
<img src="https://github.com/JaakkoRE/Extended-tic-tac-toe-AI/blob/master/Documentation/Images/Jacoco%20code%20coverage%20report.png"><br>
### Time tests
https://pastebin.com/Nm2w6kE0 raw data of time tests. HashMap utilization didn't quite pan out.<br>
Length, height and victory length (how many of the same symbol is needed to be in a row for a win) are same. Optimization gets bad after larger than 19 x 19 boards. Generally time goal is between 15-60 seconds. Depth (how many turns are made before checking turns heuristic value estimation) is the biggest optimization factor and changing depth by 1 has big impact on performance. Some higher times are accepted to ensure better performance of the ai. 
<img src="https://raw.githubusercontent.com/JaakkoRE/Extended-tic-tac-toe-AI/master/Documentation/Images/Time%20comparison%20graph.png" >
### X loses test
<br>
In X loses test the idea is that X should always lose or the game should be a tie if the ai plays optimally since O always has equal or more pieces on the board. Succeeding the test doesn't quarantee that the AI is good but losing this test often is an indication that the ai is bad. The test uses 9 premade tests and 26 random boards.<br>
With current random seed and premade tests x wins with victory length of 5, height of 7 and width of 9.<br>
<img src="https://github.com/JaakkoRE/Extended-tic-tac-toe-AI/blob/master/Documentation/Images/loss.png">
###  Random heuristic tournament
Random heuristic tournament proves that the ai can lose to different versions on itself on harder boards. There is rarely a definitive winner and often these values work better
on some boards and worse on others. And often these values perform bad on manual testing. 
### Overall
Performance tests prove that the ai is not perfect. Harder bigger boards proved to be challenging. For example in 10 x 10 boards the ai can't see 2 opponent moves forwards meaning that if victory length is 5 and opponent has open 3 line the ai can't know that opponent wins with 2 moves. This makes good heuristics important, but creating perfect heuristics is difficult. Overall I am pretty happy with the ai.s performance.





