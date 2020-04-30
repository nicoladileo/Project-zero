# Project Zero

Project zero is a project template for the development of Java web application written with ZK/Spring framework.
Thanks to Bootstrap 4, it can be useful for building responsive UI.

### Prerequisites

What you need

```
Java JDK 1.8 
Maven Command Line
MySQL
Apache Tomcat 8 (or other application server)
```

## Build the application 

Once cloned the repo you can build it doing these steps:
```
* Go to the folder where pom.xml is located
* Run mvn clean install
```

## Run the application 

```
* Locate the target folder and the .war file inside it that you had build in the previous step
* Deploy project zero war file on your favourite application server. For Apache Tomcat deploy it under webapps folder and start tomcat
* Open MySQL and execute the script file that you find in scr/main/resources/script.sql
* Open your browser at the link http://localhost:8080/projectzero-0.0.1-SNAPSHOT/
* Username and password are: nicola.dileo/password
* Enjoy
```

## Features

```
* Maven Project
* Spring security
* PDF generation con Jasper Report
* MD Bootstrap charts
* Push notification with One Signal
* Role based authentication
* i18n
```

## Authors

* **Nicola Dileo** - [Github](https://nicoladileo.github.io)






