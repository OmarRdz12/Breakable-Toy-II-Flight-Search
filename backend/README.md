# Breakable-Toy-II-Flight-Search (backend)
## Description
The backend API is divided into three essential parts:
- controllers: This is the section where the endpoint route and what is expected to be returned are placed.
- service: This is the connection that exists between the endpoint and what is going to be obtained.
- dao: This is the repository of task information, at this point is where access to mock locations is given.

## Run and test
1. Create a .env file and define the following variables
```bash
  API_KEY = YOUR AMADEUS API KEY
  API_SECRET = YOUR AMADEUS API SECRET
  FRONTEND_URL = http://localhost:5053
```
