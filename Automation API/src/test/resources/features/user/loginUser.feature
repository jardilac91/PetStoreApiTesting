Feature: Login user

  Background:
    Given That the user was able to send requests to the PetStore API.

    Scenario: login user with valid data.
      When The user sends a get request to login using username "user1" and password "abc"
      Then The user should see that the response has the status 200.

    Scenario: logout user
      Given The user sends a get request to login using username "user1" and password "abc"
      When The user sends a get request to logout the user.
      Then The user should see that the response has the status 200.