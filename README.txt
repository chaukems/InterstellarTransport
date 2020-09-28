App usage:

The app consists of the following modules
api-gateway - nothing implemented yet
shortest-path-service
eureka-service
web-app

Running the application:
-------------------------
Clean build the parent project to build all the modules
- start eureka-service
- start shortest-path-service
- start web-app

You can access the web-app using this url http://localhost:8090/InterTransport

Swagger Token generation.
-----------------------------

execute post on the following rest endpoint on postman or any rest client

http://localhost:8100/login
payload:
{
  "username": "admin",
  "password": "admin123"
}

Response:
{
    "jwttoken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYwMDc2OTcxOSwiaWF0IjoxNjAwNzUxNzE5fQ.PFGl5WbdqCSoQh8v0z7GkhZAEHHPJhHvZo_dxwzXS5_42r0LViA1gSwjLNnFYNnEHjQueNUqCMD-OUfEva2iIg"
}

Use this token on swagger ui (http://localhost:8100/swagger-ui.html) for authorization.



