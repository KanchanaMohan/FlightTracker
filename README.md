*FLIGHT TRACKER*

#**Project Information:**

As part of Addidas coding challenge, I am pleased to submit FlightTracker (v1.1) implemented as per the requirements specified at [Coding Challenge Link](https://bitbucket.org/adigsd/backend-flitetrakr)

#**Directory Structure:**

Source files located at: \FlightTracker\src\main\java\

Test cases (Basic level done) located at: \FlightTracker\src\test\java

Test reports at : \FlightTracker\build\reports\tests\test (will be generated while building)

Input file used for testing: \FlightTracker\Input.txt

#**Tools for running the project:**

-jre 1.80.0

-gradle 3.4.1

-JUnit 4.8.1


#**Steps for executing the project:**

1.Unzip the FlightTracker.zip

2.Execute the following command from the project directory (FlightTracker) for building the project

$ gradle build
 
3.Execute the following command from the project directory (FlightTracker) for building the project

$ gradle run -PInputFilePath="['input_file_path']"

example input_file_path: F:/Input.txt

4.Output will be printed in the console and also logged into an Output file located in the \FlightTracker\Output

#**Known Issues/ Problems:**

1. The 'Find all connections from XXX to XXX below XXXEuros' use case is not considering multiple stop overs at the same location. 

2. Unit testing is only very minimally done. Integration level testing is not done, however it can be incorporated with the JUnit framework.

3. The application is very sensitive to the formatting of the input text (not tolerant enough to white spaces too).

4. In the example queries provided, there was spelling mistakes, the queries were framed correcting the mistakes. 

   i.e How mand different connections --> How many different connections

   Find all conenctions --> Find all connections
   
   cheapest connection form LHR to LHR  --> cheapest connection from LHR to LHR

#**Assumptions**

1. The number of Airports/Stop over destinations is limited to a few and will not increase significantly. In that case more efficient (with less complexity) searching algorithms will be demanded.

2. Connections information provided is only one way information i.e. NUE-FRA-60 is the connection from NUE to FRA and no connection from FRA to NUE exists, unless explicitly mentioned.

#** Changes from version 1.0 **
1. Added minimal unit testing for all classes.

2. Corrected error observed in 'different connections with maximum n stops' and 'different connections with exactly n stop' use cases. This was observed while unit testing.
