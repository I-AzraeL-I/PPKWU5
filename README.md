# About the project
This is an API which scraps contact data from *panoramafirm.pl* and creates downloadable vCards for them
#### Endpoints
- GET /companies
#### Request parameters
- service - name of requested service
- location - city of a service
- page (optional, default = 1) - page of found results
#### Example
- GET /companies?service=szkola&location=warszawa 
- GET /companies?service=szkola&location=warszawa&page=2
## Tech stack
- Spring Boot 2.6.1
- Java 11
- JSoup
- ez-vcard
## Building the project
Clone the project and use Maven to build the app.
```
$ mvn clean install
```
Run the application (port 8080 by default)
