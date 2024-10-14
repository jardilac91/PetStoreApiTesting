# PetStore API Performance Testing with K6

This project focuses on performance testing of the Swagger PetStore API using **K6**. It evaluates the API's ability to handle various loads, stress, and spike conditions to identify potential performance bottlenecks.

## Project Structure

- **main.js**: The main script to execute performance tests for different API endpoints (Pets, Orders, Users).
- **config.js**: Contains configurations for different test scenarios:
  - **Load Testing**
  - **Stress Testing**
  - **Spike Testing**
- **endpoints/**: Contains individual API operations grouped by:
  - **Pets**: `getPet()`, `createPet()`
  - **Orders**: `getOrder()`, `createOrder()`
  - **Users**: `getUser()`, `createUser()`, `loginUser()`, `logoutUser()`
