Feature: Delete user

  Background:
  Given That the user was able to send requests to the PetStore API.

  Scenario: Delete an user
    Given The user created an user with random data.
    When The user sends a Delete request to delete the user created
    Then The user should see that the response has the status 200.
    And Should see a 404 status when he searches for the user's username.

  Scenario: Delete a non-existent user
    When The user sends a Delete request to delete a non-existent user.
    Then The user should see that the response has the status 404.