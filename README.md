# my-axonbank

A spring boot application that uses Axon 3 demonstrating CQRS using RabbitMQ. 
Gradle is the build tool. It uses docker to bring up your services.

Make sure you have docker installed, before you can run the application.

## How to run it?
1. `gradle bootRepackage` will package your application in a JAR.
2. `docker-compose build` will build the image of the application.
3. `docker-compose up` will bring up RabbitMQ and my-axonbank spring boot application.

Sprint boot application runs on port 8080.
You can use `docker-compose ps` to view different ports exposed.
