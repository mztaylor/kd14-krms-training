# Kuali Days 2014 KRMS Training

# Purpose
To help users understand the functionality related to KRMS and how to add it to their client applications

# Setup

## Setup Requirements
- Java JDK 1.7+ - http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html
- MySQL 5.5.31 - http://dev.mysql.com/downloads/mysql/
- MySQL Workbench - http://dev.mysql.com/downloads/tools/workbench/
- SVN 1.7+ - https://subversion.apache.org/packages.html
- Maven 3.0.4+ - http://maven.apache.org/download.cgi
- Eclipse 3.8+ (or Spring Tool Suite 3.3+) - https://spring.io/tools/sts/all

## Eclipse and Rice Setup

You will need to install java, eclipse, mysql for this project.  Instructions for setup can be found at:

    http://site.kuali.org/rice/latest/reference/html/Intro_To_Rice.html#d4798e2496

## Project Setup

### Setup krms training project
Clone a copy of the git project by using Eclipse > 'Import' > 'Git' > 'Projects from Git'
On the repository source select 'Github' and enter 'git@github.com:mztaylor/kd14-krms-training.git'

### Build environment
Once the project is checked out, check that it is a maven project by the 'm' icon over the project name.  You can also right
click on the project and should see maven item in the selection.  If it is not there, select 'Configure' > 'Enable Maven Nature'

Right click on the project and select 'Maven' > 'Update Project'.  Then select 'Run as' > 'maven install' to build the project.

### Database Setup
Then select 'Run as' > 'maven build...'.  Set the goal to 'clean install' and profiles to 'mysql,setup'.  Select 'apply' and 'run'.

### Application startup

Right click the project and select 'Run as ' > 'Run as server'.  Select the tomcat 7 server and on Argument tab, add to vm arguments:

    -Xms512m -Xmx1024m -XX:PermSize=256m  -XX:MaxPermSize=256m

Select 'apply' button and 'run' button.  The server should startup and you can select the [project home page](http://localhost:8080/krworkshop/)
 
# Training Overview

## KRMS Introduction
### What is KRMS?
 - What makes it different from Drools?
 - What is it used for?
 - How does it work?

### What is a KRMS Rule?
 - Proposition
 - Action
 - Terms
 - Facts

## Extra Help - Setting up a tomcat 7 server from scratch 

You may choose to install this project from an existing eclipse or spring tool suite.  If that is the case you will want to setup your server.  For this project we're using tomcat7 server.  When you select 'Run as..' > 'Server', it will give you an option to manually setup a server.  On this screen select 'Apache' and 'Tomcat v7.0 Server'.  The next tab will give you the option of selecting an existing server or downloading one. If you choose to download or setup a server, you will still need two additional jars in your common lib library, spring-instrument.jar and the  mysql-connector-java.jar.  You can find these on 'search.maven.org', download and add to your common/lib directory in the tomcat 7 directory. Afterwards you will want to update the Launch Configuration arguments tab with the vm setting in the earlier instructions and update the 'Timeouts' tab
with 180 seconds for 'start'.


# Troubleshooting

I'm getting 'Unable to update maven project - Unsupported IClasspathEntry kind=4'
- Right click on the project, select maven - disable maven nature
- Right click -> Run as -> Maven 'eclipse:clean'
- Right click -> Configure -> Enable Maven nature

I'm getting red X's when I import the project
- Right click on the project and select 'Maven' > 'Update Project'

I'm getting an error about unable to import 'Git-ish'
- Do a step by step import using next rather than finish

I'm getting 'Access denied for user 'root'@'localhost' (using password: NO) -> [Help 1]' when building my database
- Some users may have 'root' or another password setup by default in their database.  This project is currently setup
 to use no password for the mysql database.  Update your pom.xml file property 'impex.dba.password' to the correct password
 (generally root)

I'm getting unable to start server and the details mention the phrase 'out of sync'
- You need to right click on your project and select 'refresh', then restart your server

My server is failing to start and giving a 45 second error
- Go to server screen and search for 'Timeouts' section.  Increase the 'start' timeout to 180 seconds

I can't find the arguments tab
- Go to server screen and search for 'Launch Configuration' in the 'General Information' section





