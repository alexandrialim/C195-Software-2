# C195-Software-2 — QAM2 TASK 1: JAVA APPLICATION DEVELOPMENT
JAVA APPLICATION DEVELOPMENT

The purpose of this application is to help users schedule appointments by creating a custom GUI Scheduling Desktop App
Author: Alexandria Lim
Email: alim18@wgu.edu
App Version 1.0
Date: 05/28/2023

IDE: IntelliJ IDEA 2022.3 (Community Edition)
Build #IC-223.7571.182, built on November 29, 2022
Runtime version: 17.0.5+1-b653.14 amd64
VM: OpenJDK 64-Bit Server VM by JetBrains s.r.o.
JDK Version: Java SE 17.0.5
JavaFX version: JavaFX SDK 17.0.5
MySQL connector: mysql-connector-j-8.0.33.jar

How to run the program:
1. Set the JDK and JavaFX version to 17.0.5
2. Import mysql-connector-j-8.0.33
3. Open up Main.java and click run.
4. Once the program launches the login screen, please log in with valid username and password credentials that exist in the database and click the "Sign In" button.
5. Note: This program will not run properly with other JDK & JavaFX versions.

Description of the additional generated report:
For the 3rd report, I chose to display the number of customers per division. In this case, a division can also be referred to as state or provence. 
I wrote SQL queries to pull data from the First Level Divisions table and Customers table and move the output of that query into a new temporary table. 
The query that is used for generating this collection of data can be found in DAO.QueryReports.java
Once the data was created on the database end, I ran a SELECT * FROM command to get all the data in that table. 
As long as the user isn't running CREATE TEMPORARY TABLE manually in the database, there will not be any errors that occur in the database.
