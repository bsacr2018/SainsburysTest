Website ScraperTask test for Sainsbury's Software Engineer role (Manchester)

1. Installation

This software can be downloaded as a zip file from 
https://github.com/bsacr2018/SainsburysTest

which would be named SainsburysTest-master.zip

In a unix terminal, this can then be unzipped,

$ unzip SainsburysTest-master.zip

and accessed from the command line with:

$ cd SainsburysTest-master

[A SNAPSHOT jar (with-dependencies) is available in the target directory, should maven not be directly available.]

With maven installed on the machine a fresh install is possible with:

$ mvn install

This will automatically run the unit tests as well. They can be run separately using the command:

$ mvn test

or for individual test classes, such as TotalTest:

$ mvn -Dtest=TotalTest test



2. Running the software

The software relies on a config.properties file which is in the src/main/resources directory.

Running the software using those default settings can be achieved via maven with

$ mvn exec:java

It also ought to be possible to run the software directly with java:

$ java -jar target/sainsburystest-1.0.0-SNAPSHOT-jar-with-dependencies.jar

[I had hoped to be able to run with just the regular SNAPSHOT jar, but perhaps there are some issues 
with the maven pom file.]

A copy of the results I got when running the program is available in the home directory of the project.


3. Design Decisions

I decided to develop this as a Maven project, given that this was to be a console application,
and that I wanted to be able to provide a straightforward build, run and test process.

[I did entertain Spring Boot as well, but decided that given the size of a potential jar file that would be 
generated and also the performance, it wasn't really the best approach.]

The JUnit-Jupiter framework (JUnit 5) is used to provide the unit testing approach.

Two further libaries are also required:

(1) a mechanism for parsing HTML, using DOM methods. Here I've chosen the JSoup library.
(2) a further mechanism for delivering the JSON format from the products that are parsed from the DOM. 
Here I use the Jackson library.

I wanted to focus on parsing the DOM format of the individual product pages into the Product objects. 
In terms of validation, I've worked on the basis, that if there is any discrepancy between the 
information provided in the titles of the Product pages and the Products page, then this is flagged.
Had I had more time available, I would have sought to put more checks in place to ensure that the
html being read the information being extracted as really coherent.

I also spent rather a long time dealing with the JSON aspects, and the value that annotations bring.



4. Issues

I think I would definitely approach this task more from there ground up were I to tackle it again,
building each class as a whole using a more TDD type approach. 


