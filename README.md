# Bajaj Finserv Health - Java Qualifier

This is a Spring Boot application created for the Bajaj Finserv Health Java Qualifier test.

## Overview
The application automatically runs on startup without any REST controllers or UI. It implements `CommandLineRunner` to perform the following steps:
1. Calls the `generateWebhook` API to get an access token and webhook URL.
2. Determines the assigned SQL question based on the last two digits of the registration number.
3. Submits the final SQL query answer to the provided webhook using the access token.

## Technologies Used
* Java 17
* Spring Boot 3.2.4
* Maven
* Spring Web (RestTemplate)
* Jackson (for JSON parsing)

## How to Run

1. Make sure you have Java 17 installed.
2. Clone or download this repository.
3. Open a terminal in the project directory.
4. Run the application using the Maven wrapper:
   ```
   ./mvnw spring-boot:run
   ```
   Or if you are using an IDE like Eclipse or IntelliJ, simply run the `QualifierApplication.java` class.

## Project Structure
* `src/main/java/com/bajaj/qualifier/QualifierApplication.java`: Main Spring Boot application class.
* `src/main/java/com/bajaj/qualifier/WebhookRunner.java`: Contains all the logic for hitting the APIs and submitting the SQL query.

## Author
* Shon Jagtap
* RegNo: ADT23SOCB1048
