# Testing documentation
## How has code been tested and what has been tested.
Testing has been done with different JUnit tests.
The Tests can be performed using JUnit framework.<br/>
Code is generally tested using plenty unit test.
Few larger tests have been done using small boards (3x3, 2x2) Big Board testing is work in progress.<br/>
Plenty of manual testing has been done.
UI is tested manually and is work in progress.
Also few time tests have been done. More time tests will be made when optimisation is improved.
## What inputs have been used in testing
Unit tests have been tried to made using diverse situations and with different edge cases. 3x3 and 2x2 boards have only few scenarios currently.<br/>
Time testing has been made using diverse linearly comperable boards.<br/>
## Timetests and graphs
these tests are from week 4 <br>
https://pastebin.com/Nm2w6kE0 raw data of time tests. HashMap utilization is work in progress. Hopefully I can improve it so it improves performance. Currently only makes performance worse.<br>
jacoco report
<img src="https://raw.githubusercontent.com/JaakkoRE/Extended-tic-tac-toe-AI/master/Documentation/Images/Jacoco%20report.png">
Length, height and vcl are same. Optimization gets bad after larger than 19 x 19 boards. Generally time goal is below 20 seconds. Depth is the biggest optimization factor and changing depth by 1 has big impact on performance.
<img src="https://raw.githubusercontent.com/JaakkoRE/Extended-tic-tac-toe-AI/master/Documentation/Images/Graph1.png" >
