# ToDo-RestApi-Spring-Hibernate

Application run on port 9090
## About the Rest Service:

### Features:

**1.** Add new task for a certain date.

**2.** Get all task for certain date.

**3.** Get each task individually.

**4.** Update task.

**5.** Delete each task.

## Here is what this little application demonstrates:

    1. Full integration with the Spring Boot Framework: inversion of control, dependency injection, etc.
    2. Writing a RESTful service using annotation: supports JSON request / response; remember to add JSON as desired Accept header in your request
    3. Exception mapping from application exceptions to the right HTTP response with exception details in the body
    4. Spring Data Integration with JPA/Hibernate with just a few lines of configuration and familiar annotations.
    5. Automatic CRUD functionality against the data source using JPA Repository pattern

### Create a task resource
POST /todo/task

### Using curl:
  `curl -d '{"description":"message", "date":"1993-04-15"}' -H "Content-Type: application/json" -X POST http://localhost:9090/todo/task`

## Update a task resource
PUT /todo/task/{id}

### Using curl:
`curl -d '{"id":1,"description":"bbbb", "date":"1993-04-15"}' -H "Content-Type: application/json" -X PUT http://localhost:9090/todo/task/1`

## GET single task resource
GET /todo/task/{id}

### Using curl:
	`curl http://localhost:9090/todo/task/1`
	
## GET all task resource
GET /todo/task

### Using curl:
	`curl http://localhost:9090/todo/task`
	
## GET all task resource for specific date
GET /todo/task?date=yyyy-mm-dd

### Using curl:
	`curl http://localhost:9090/todo/task?date=1993-04-15`

## Delete a specific task resource
DELETE /todo/task/{id}

### Using curl:
	`curl -X "DELETE" http://localhost:9090/todo/task/1`
	
