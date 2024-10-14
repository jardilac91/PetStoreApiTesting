Feature: Get Pets

  Background:
    Given That the user was able to send requests to the PetStore API.

  Scenario: get pet information by ID
    When The user sends a get request using the pet id "1".
    Then The user should see that the response has the status 200.
    And The response should contains the name "Cat 1" and the status "available"

  Scenario Outline: Try to get information using an non-existent ID
    When The user sends a get request using the pet id "<id>".
    Then The user should see that the response has the status 404.
    Examples:
    | id |
    | -1 |
    | 1000 |

  Scenario Outline: Try to get information using an invalid ID
    When The user sends a get request using the pet id "<id>".
    Then The user should see that the response has the status 400.
    Examples:
      | id |
      | abc |
