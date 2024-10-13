Feature: Get orders

  Background:
    Given That the user was able to send requests to the PetStore API.

  Scenario: get an existent order
    When The user sends a get request using the order id "1".
    Then The user should see that the response has the status 200.
    And The response should contains the quantity 100 and the status "placed".

  Scenario: get an order with a non-existent id
    When The user sends a get request using the order id "5".
    Then The user should see that the response has the status 404.

  Scenario: get an order with an invalid id
    When The user sends a get request using the order id "abc".
    Then The user should see that the response has the status 400.
