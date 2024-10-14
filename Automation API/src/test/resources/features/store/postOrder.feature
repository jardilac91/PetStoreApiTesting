Feature: Post Order

  Background:
    Given That the user was able to send requests to the PetStore API.

  Scenario: Create a new order
    Given The user wants to create an order with random data.
    And get the pet inventories by status
    When The user sends a Post request to create the order.
    Then The user should see that the response has the status 200.
    And should see that the order was created in the platform.
    And should see that the status in the inventory increases with quantity field in the order created

  Scenario: Create a new order with a new status
    Given The user wants to create an order with random data and a new status.
    And get the pet inventories by status
    When The user sends a Post request to create the order.
    Then The user should see that the response has the status 200.
    And should see that the order was created in the platform.
    And The new status was added to the inventory.

  Scenario: Create a new order with an invalid id
    When The user sends a Post request to create the order with an invalid id.
    Then The user should see that the response has the status 400.