Feature: Post user.

  Background:
    Given That the user was able to send requests to the PetStore API.

  Scenario: Create user
    Given The user wants to create an user with random data.
    When The user sends a Post request to create the user.
    Then The user should see that the response has the status 200.
    And should see that the user was created in the platform.

  Scenario: Create user with invalid id
    When The user sends a Post request to create the user with invalid id..
    Then The user should see that the response has the status 400.

