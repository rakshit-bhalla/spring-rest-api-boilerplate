<p align="center">
  <a href="http://nestjs.com/" target="blank"><img src="https://spring.io/images/spring-logo-9146a4d3298760c2e7e49595184e1975.svg" width="320" alt="Spring Logo" /></a>
</p>

  <p align="center">Boilerplate code for REST API in <a href="https://spring.io/" target="_blank">Spring</a> framework for building efficient and scalable server-side applications.</p>
  <!--[![Backers on Open Collective](https://opencollective.com/nest/backers/badge.svg)](https://opencollective.com/nest#backer)
  [![Sponsors on Open Collective](https://opencollective.com/nest/sponsors/badge.svg)](https://opencollective.com/nest#sponsor)-->

## Description

- REST API with MVC standards in [Spring](https://spring.io/) framework.
- Code has been written in java 11.
- Documentation has been implemented with [Swagger](https://swagger.io/).
- Health check has been implemented using [Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html).
- [H2 database](https://www.h2database.com/html/main.html) (In-memory SQL) has been used.
- Logging has been implemented using [Slf4j](http://www.slf4j.org/)
- Testing has been implemented using [Junit](https://junit.org/junit5/)

## Running the app

- Clone the repository
  ```bash
  $ git clone https://github.com/rakshit-bhalla/spring-rest-api-boilerplate.git
  ``` 
- Open it using Intellij
- Click on add configurations, then select maven and run it using the given command
  ```bash
  $ spring-boot:run
  ``` 
- Visit this [url](http://localhost:8080/users)
- To run the code via cmd, package the code into a jar and run it using the given command
  ```bash
  $ java -jar -Dserver.port=8080 boilerplate-0.0.1-SNAPSHOT.jar
  ``` 
- Base Documentation can be checked [here](http://localhost:8080/swagger-ui.html)
- Health of the application can be checked [here](http://localhost:8080/actuator/health)
- Database can be accessed [here](http://localhost:8080/h2-console/) using these credentials
    - JDBC URL - jdbc:h2:mem:sampledb
    - User Name - username
    - Password - password
- Logs will be present at
  ```bash
  $ cd logs/spring-rest-api-boilerplate.log
  ```