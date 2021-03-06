# ToDo-RestApi-Spring-Hibernate
Application runs on port 9090, change port in application.yml file and rebuild the package using maven

### Download the 'fat' jar file from here [here](https://github.com/VishalTheAries/ToDo-RestApi-Spring-Hibernate/blob/master/target/todo-application-0.0.1-SNAPSHOT.jar)

and run this jar using following command
	`java -jar todo-application-0.0.1-SNAPSHOT.jar`

or build using maven
	`mvn clean package` and find the jar in target folder

## About the Rest Service:

### Features:

**1.** Add new task for a certain date.<br/>
**2.** Get all task for certain date.<br/>
**3.** Get each task individually.<br/>
**4.** Update task.<br/>
**5.** Delete each task.<br/>

## Here is what this little application demonstrates:

1. Integration with the Spring Boot Framework: inversion of control, dependency injection, etc.
2. Writing a RESTful service using annotation: supports JSON request / response; remember to add JSON as desired Accept header in your request
3. Spring Data Integration with JPA/Hibernate with just a few lines of configuration and familiar annotations.
4. Automatic CRUD functionality against the data source using JPA Repository pattern

### Create a task resource
`POST /todo/task`<br/>
`Accept: application/json`<br/>
`Content-Type: application/json`<br/>
```
{
"description" : "text",
"date" : "2017-06-17"
}

RESPONSE: HTTP 201 (Created)
Location header: http://localhost:9090/todo/task/1
```
### Using curl:
    curl -d '{"description":"message", "date":"1993-04-15"}' -H "Content-Type: application/json" -X POST http://localhost:9090/todo/task

## Update a task resource

`PUT /todo/task/{id}`<br/>
`Accept: application/json`<br/>
`Content-Type: application/json`<br/>
```
{
"id":1,
"description" : "text",
"date" : "2017-06-17"
}
RESPONSE: HTTP 204 (No Content)
```
### Using curl:
    curl -d '{"id":1,"description":"text", "date":"2017-06-17"}' -H "Content-Type: application/json" -X PUT http://localhost:9090/todo/task/1

## GET single task resource
`GET /todo/task/{id}`<br/>
`Accept: application/json`<br/>

```
Response: 200 (OK)
Content-Type: application/json;charset=UTF-8
{"id":1,"description":"text","date":"2017-06-17"}
```
### Using curl:
	curl http://localhost:9090/todo/task/1
	
## GET all task resource
`GET /todo/task`<br/>
`Accept: application/json`<br/>
```
Response: 200 (OK)
Content-Type: application/json;charset=UTF-8
[{"id":1,"description":"text","date":"2017-06-17"}]
```
### Using curl:
	curl http://localhost:9090/todo/task
	
## GET all task resource for specific date
`GET /todo/task?date=yyyy-mm-dd`<br/>
`Accept: application/json`<br/>

```
Response: 200 (OK)
Content-Type: application/json;charset=UTF-8
[{"id":1,"description":"text","date":"2017-06-17"}]
```
### Using curl:
	curl http://localhost:9090/todo/task?date=2017-06-17

## Delete a specific task resource
`DELETE /todo/task/{id}`
```
RESPONSE: HTTP 204 (No Content)
```
### Using curl:
	curl -X "DELETE" http://localhost:9090/todo/task/1
	
