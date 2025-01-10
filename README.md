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
* Orchestration: Kubernetes

## Layers
### Backend
* POST /quotes: returns price based on inputted user data (name, age, coverage)
* GET /quotes/{id}: gets previously stored price

### Business logic
* Price = Coverage * Age * Risk factor

### Frontend
* QuoteForm: form for inputting user data
* QuoteResult: shows price
* Workflow: user fills form -> data is sent to backend -> price is retrieved from backend and shown on screen