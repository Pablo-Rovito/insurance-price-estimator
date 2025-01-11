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

### Frontend
* QuoteForm: form for inputting user data and showing result.
* Workflow: user fills form -> data is sent to backend -> price is retrieved from backend and shown on screen