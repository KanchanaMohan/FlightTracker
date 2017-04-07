*FLIGHT TRACKER*

#**Project Information:**

As part of Addidas coding challenge, I am pleased to submit FlightTracker (v1.0) implemented as per the requirements specified at [Coding Challenge Link](https://bitbucket.org/adigsd/backend-flitetrakr)

#**Directory Structure:**

Source files located at: \FlightTracker\src\main\java\

Test cases (Basic level done) located at: \FlightTracker\src\test\java

Test reports at : \FlightTracker\build\reports\tests\test

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

1. The unit testing framework is done, however testing is only very minimally done (only for ConnectionPriceTest.java), I would like to do intensive unit verification for the class and also extend it to other classes aiming at a test coverage of 100%. I hope to achieve this in a further release.

2. The application is very sensitive to the formatting of the input text (not tolerant enough to white spaces too), this shall be corrected in a further release.

3. In the example queries provided, there was spelling mistakes, the queries were framed correcting the mistakes. 

   i.e How mand different connections --> How many different connections

   Find all conenctions --> Find all connections

#**Assumptions**

1. The number of Airports/Stop over destinations is limited to a few and will not increase significantly. In that case more efficient (with less complexity) searching algorithms will be demanded.

2. Connections information provided is only one way information i.e. NUE-FRA-60 is the connection from NUE to FRA and no connection from FRA to NUE exists, unless explicitly mentioned.
