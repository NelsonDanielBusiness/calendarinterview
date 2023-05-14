# Calendar Interview 

## Docker

Using Maven, Docker and Docker compose. 
Generate a local image (interviewcalendar:latest) of the application using 

```
./mvnw compile jib:dockerBuild
```
And then run the application using 

```
docker-compose -f docker/app.yml up -d
```
(Missing validation to boot the application after the database to ensure it connects correctly so you probably will need to boot the database first, just run this command twice, should do the trick)

This will run the Spring Boot application and an instance of Mysql Database, which the schema will be created by Liquibase (Chose Database Migration Tool)

## Requirements to Run locally 

For building and running the application you need:

- [JDK 1.11](http://www.oracle.com/technetwork/java/javase/downloads/)
- [Maven 3](https://maven.apache.org)

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `de.codecentric.springbootsample.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Postman
Added [interview calendar.postman_collection.json](postman%2Finterview%20calendar.postman_collection.json) file under /postman folder, with the collection from postman with all the endpoints used to test the application.

Important endpoints:
POST /candidate - creates candidates
POST /interviewers - creates interviewers
POST /interview-availabilities/batch - creates multiple availabilities (for interviewers and candidates)
GET /interview/arrange - arrange interview for candidate and interviewers
