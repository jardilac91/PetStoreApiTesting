# PetStore API Testing Project

This project automates the testing of the Swagger Pet Store API using **SerenityBDD**, **Cucumber**, and **RestAssured**. It is designed to cover critical API operations such as creating, updating, deleting pets, store orders, and user management functionalities.

## Requirements

- **Java**: Version 17
- **Gradle**: Version 8.5
- **Dependencies**:
    - Serenity BDD
    - RestAssured
    - Cucumber

Ensure the correct Java and Gradle versions are installed before running the project.

## Project Structure

- **src/java/org/co/entity**: Contains object representations of the API entities (Pets, Store, User).
- **src/java/org/co/questions**: Contains custom questions like `ResponseCode` (to validate status codes) and `ResponseContent` (to extract and convert responses into entities).
- **src/java/org/co/tasks**: Contains reusable tasks for sending API requests:
    - `SendDeleteRequestWithParam`: Sends a DELETE request using a parameter.
    - `SendGetRequestWithParams`: Sends a GET request with parameters.
    - `SendGetRequestWithoutParams`: Sends a GET request without parameters.
    - `SendLoginUserRequest`: Sends a GET request for user authentication.
    - `SendPostRequest`: Sends a POST request with a body object.
    - `SendPutRequest`: Sends a PUT request with a body object.
- **src/java/org/co/utils**: Includes utility classes:
    - `DataGenerator`: Generates random test data.
    - `PetStoreApiConstants`: Holds constants like URLs.
- **test/java/petStoreApi/config**: Contains the `MakeAnApiRequest` class, which enables an actor to make API requests.
- **test/java/petStoreApi/runners**: Contains Cucumber runners (`PetRunner`, `StoreRunner`, `UserRunner`) for executing the tests.
- **test/java/petStoreApi/stepdefinitions**: Contains step definitions for Pets, Store, and User endpoints.
- **test/resources/features**: Contains Cucumber feature files for scenarios related to each API endpoint.

