# API automation for Products API

This document explains required software, test structure and how to run tests.

## Prerequisites
Software that is required to make this repo work :) 
- Java 1.8
- Maven 

### Libraries used

#### rest-assured

- Test and validate API end points

#### TestNG

- Is used mainly as test runner, to maintain test structure using annotations

#### gson

- Java library to serialize and deserialize Java objects to JSON

#### hamcrest

- Used for assertions, matchers to verify results

## Project Structure

```
pom.xml
testng.xml

src
 └──main
      └──java
           └──com
                └──hellofresh
             	               └── api
                                ├── BookingApi.java : Has extracted calls for Products API
    
                  	       └── constants
                                ├── LoggingMessageContants.java : Class to keep defined Constant messages 

                  	       └── template
                                ├── Booking.java : To form a body for create Booking API. 
                                ├── BookingDate.java : To form a body for BookingDate Json.
                                             
                  	       └── util
                                ├── PropertiesReader.java : Class for reading data from file.
                                ├── UtililtyFunctions.java :  Utility functions with common functions to generate random data.

src
 └──main
      └──resources
             ├── env.properties : Class for defining URL data.

src
 └──test
      └──java
           └──com
                └──hellofresh
               	       └── bookingapitestcases             
                                ├── BookingBaseTest.java : Class for reading Base URI from properties file. 
                                ├── CreateBookingTest.java : Class for Creating Account API TestCases
                                ├── DeleteBookingTest.java : Class for Delete Booking API TestCases (Test case not executed due to not having token clarification)
                                ├── GetAllBookingsTest.java : Class for GetAllBooking API TestCases.
                                ├── GetBookingTest.java : Class for GetBooking API TestCases                                                                           
                                       
src
 └──test
      └──java
           └──com
                └──hellofresh
             	       └── dataprovider  
                                ├── RepositoryCreateBookingTestCase.java : Class for defining data provider for Create booking test case.                                      

```

## Running Test

From terminal use `mvn clean test` to run all the tests


Find reports :
- {Working directory}/helloFreshAPITest/target/surefire-reports/emailable-report.html
- {Working directory}/helloFreshAPITest/target/surefire-reports/APIAssignment/APIAssignment suite.html