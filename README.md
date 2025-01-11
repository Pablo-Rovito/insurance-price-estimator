# insurance-price-estimator
Simple estimator to use docker for the first time. Integrates Springboot backend, React frontend and PostgreSQL DB

This is a system that allows users to get a quick pricing on a life insurance. 

## Main goals
* Create an API for managing insurance pricing
* Implement a simple Frontend for consuming the API
* Include some business logic 

## Stack
* Backend: Springboot with Java
* Frontend: React with Typescript
* Database: PostgreSQL
* Containarization: Docker

## How to deploy it on local environment
1) Clone somewhere
2) Open Docker Desktop (or make Docker engine available by whatever means are preferred)
3) In root folder, execute docker-compose up --build
4) Everything should work out fine... routed ports are as follows:
    * frontend: http://localhost:3000
    * backend: http://localhost:8080
    * database: http://localhost:5432

## How to modify the app
1) Modify whatever
2) If app is deployed with Docker, open console in root, 'docker-compose down', then rebuild with 'docker-compose up --build'
3) For deploying individually outside Docker:
    * Database: Open PGAdmin4 (it´s the easiest way for me)
        * New connection with parameters
            - Host: localhost
            - Port: 5432
            - Username: check out backend access params in resources package
            - Password: same as above
        * Create Database with name 'insurance'
        * Connect to 'insurance', paste contents of init.sql in QueryTool tab, execute.
    * Backend: execute file /backend/src/main/java/com/insurancepriceestimator/backend/BackendApplication.java
    * Frontend: run /frontend> npm start

## Layers
### Database
* Table 'quotes'
* Columns
    * id
    * policyHolder (corresponds to name)
    * premium (corresponds to yearly amount of insurance)
    * coverageType (corresponds to coverage)

### Backend
* POST /quotes: returns price based on inputted user data (name, age, coverage)
    * name: name of the inquirer
    * age: age of the inquirer
    * coverage: MINIMUM_COVERAGE, REGULAR_COVERAGE, SUPER_COVERAGE

### Business logic
* Price = Coverage * Age * Risk factor
    * Risk factor depends on the age, ranging from 0.10 to 0.99 as the age increases
* Discount
    * if age <= 25 -> 25%
    * if name contains 'discount' -> 10%

### Frontend
* QuoteForm: form for inputting user data and showing result.
    * Form: it receives age, name and type of policy, this last one from an select because there are limited policy types
    * Result: it shows a stringified representation of the body of the API response
    * History: it shows the quotes obtained in the current session (so no persistency) and it is handled with a local state
* Workflow: user fills form -> data is sent to backend -> price is retrieved from backend and shown on screen