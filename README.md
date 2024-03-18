# **User Service**
Service to handle authentication as well as authorization.

## **Requirements**
* JDK 17+
* Maven 3
* MySQL
* Apache Kafka

## **Prerequisites**
Set these environment variables containing database credentials.
`USER_SERVICE_DB_URL=jdbc:mysql://<host>:3306/<db_name>;
USER_SERVICE_DB_USERNAME=<db_username>;
USER_SERVICE_DB_PASSWORD=<db_password>
FROM_EMAIL_ID=<email_id_from_which_email_will_be_sent>`

## **Running the application locally**
`mvn spring-boot:run`

To create jar and execute jar file:
`mvn package`

This will create jar file inside target\e-com-user-1.0.0.jar
To execute jar file:

`java -jar e-com-user-1.0.0.jar`

## **Steps:**
To signup a new user, send post request through postman to this endpoint: `/users/signup`

`{
   "email" : "<email_id>",
   "password" : "<password>",
   "name" : "<name>"
}`