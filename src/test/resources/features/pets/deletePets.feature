Feature: Delete pets

  Background:
    Given That the user was able to send requests to the PetStore API.

  Scenario: Delete a pet
    Given The user created a pet with random data
    When The user sends a Delete request to delete the pet created
    Then The user should see that the response has the status 200.
    And Should see a 404 status when he searches for the pet's id.

  Scenario: Delete a non-existent pet
    When The user sends a Delete request to delete a non-existent pet.
    Then The user should see that the response has the status 404.