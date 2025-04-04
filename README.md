# Breakable-Toy-II-Flight-Search
# Description
This application is designed to allow users to search for flights between their preferred airports. Searches can be made using a section that includes airport names, departure dates, number of adults, currency, and the ability to select a return date to purchase round-trip flights.

The results show an overview of available flights and can be sorted by duration and/or price, ascending or descending. Clicking on the flights also displays specific details.

### Technologies used
- **Front-end**: Vite(React), TypeScript, Ant Design, Axios and Sonner.
- **Backend**: Java with Gradle, Spring Boot.

## Instalation
1. Clone the repository:
```bash
  https://github.com/OmarRdz12/Breakable-Toy-II-Flight-Search.git
```
2. You need:
```bash
  docker or podman
  docker-compose or podman-compose
```
3. In the frontend create a .env file inside frontend/
```bash
  VITE_API_URL = http://localhost:5054/
```
4. In the backend create a .env file inside backend/src/main/resources
```bash
  API_KEY = YOUR AMADEUS API KEY
  API_SECRET = YOUR AMADEUS API SECRET
  FRONTEND_URL = http://localhost:5053
```
5. Run de project
```bash
    docker-compose up or podman-compose up
```
6. You can read the individual readme to better understand the project
- [Frontend Readme](frontend/README.md)
- [Backend Readme](backend/README.md)
