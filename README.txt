App usage:

You can access the app using this url http://localhost:8090/InterTransport

Swagger Token generation.

execute post on the following rest endpoint on postman or any rest client

http://localhost:8090/InterTransport/login
payload:
{
  "username": "admin",
  "password": "admin123"
}

Response:
{
    "jwttoken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYwMDc2OTcxOSwiaWF0IjoxNjAwNzUxNzE5fQ.PFGl5WbdqCSoQh8v0z7GkhZAEHHPJhHvZo_dxwzXS5_42r0LViA1gSwjLNnFYNnEHjQueNUqCMD-OUfEva2iIg"
}

Use this token on swagger ui (http://localhost:8090/InterTransport/swagger-ui.html) for authorization.



