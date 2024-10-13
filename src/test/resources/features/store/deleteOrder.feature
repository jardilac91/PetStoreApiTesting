Feature: delete order

  Background:
    Given That the user was able to send requests to the PetStore API.

  Scenario: Delete an order
    Given The user created an order with random data
    When The user sends a Delete request to delete the order created
    Then The user should see that the response has the status 200.
    And Should see a 404 status when he searches for the order's id.

  Scenario: Delete a non-existent order
    When The user sends a Delete request to delete a non-existent order.
    Then The user should see that the response has the status 404.
