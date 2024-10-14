Feature: Update pets

  Background:
    Given That the user was able to send requests to the PetStore API.

  Scenario: Update pet's information using update method
    Given The user sent a get request using the pet id "5".
    And The user wants to update the pet's name and status.
    When The user sends a Put request to update the pet.
    Then The user should see that the response has the status 200.
    And The user should see that the pet's name and status were updated.


  Scenario: Update pet's information using post method
    Given The user sent a get request using the pet id "7".
    When The user sends a post request to update the pet's name and status.
    Then The user should see that the response has the status 200.
    And The user should see that the pet's name and status were updated.