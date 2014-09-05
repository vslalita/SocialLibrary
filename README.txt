SocialLibrary
=============

Social library for a group of members. This document gives the instructions on how to run the application.

Tools Used: 

Web Server: jetty
Database Server: mySQL (Version:

Steps to install mysql on windows: 
 Step 1: Download mysql from the link - http://dev.mysql.com/downloads/mysql/. 
 Step 2: Unzip the setup file and execute the downloaded MSI file.
 Step 3: Click on the "setup". Then Select Typical for set up Type .Click on Install. Click Next. Click Next. Click Finish.
 Step 4: Select Detailed Configuration. Click on Next.
 Step 5: select "Transactional Database Only".
 Step 6: Select the drive where the database files will be stored. 
 Step 7: Select DecisionSupport/OLAP. Click on Next.It is recommended that you leave the default port 3306 in place
 Step 8: Select Standard CharacterSet. CLick on Next.
 Step 9: Choose install as Windows Service.
 Step 10: Enter passoword. Click on next. Click on Execute. Click on Finish

Reference Link: http://ushastry.blogspot.com/2009/06/installing-mysql-on-windows.html


To install Maven: 
Maven- Please follow installation instructions for the approporiate OS as described in this link. http://maven.apache.org/download.cgi
JDK 1.7 - Please install using this link as a reference - http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html
Make note of the folder where you installed the JDK 1.7 to. Set JAVA_HOME to point to the /bin/

Running the Web-App

Navigate to the project location. From the terminal(Linux or Mac) or command Prompt in Windows, type the following command cd <Project Location>

Run the following command

mvn jetty:run

In a web browser, type the following url - http://localhost:8080.


