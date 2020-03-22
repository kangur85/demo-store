### Demo store
An example of Spring Boot RESTful application.

### Running demo-store
In order to build, test and run the app, please navigate to project directory and execute:
    
    ./gradlew clean build
    docker-compose build
    docker-compose up
    
Application starts at http://localhost:8080. 

Swagger is available at http://localhost:8080/swagger-ui.html.

### Persistence

MySQL image is launched by `docker-compose`. If you need database store between app restarts, 
uncomment volumes part in `docker-compose.yml`.

In-memory H2 database is used for testing.