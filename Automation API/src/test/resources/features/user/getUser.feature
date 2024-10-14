Feature: Get user.
  Background:
    Given That the user was able to send requests to the PetStore API.

    Scenario: Get user by username
      When The user sends a get request to search the data of the user "user2".
      Then The user should see that the response has the status 200.
      And The id returned is "2", the firstname is "first name 2".


  Scenario: Send get request with a non-existent username
    When The user sends a get request to search the data of the user "non-existant".
    Then The user should see that the response has the status 404.

