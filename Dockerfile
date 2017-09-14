FROM library/java:8
MAINTAINER Bharat Srinivasan
EXPOSE 8080
CMD java -jar my-axonbank-0.0.1-SNAPSHOT.jar
ADD build/libs/my-axonbank-0.0.1-SNAPSHOT.jar .
