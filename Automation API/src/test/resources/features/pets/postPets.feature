Feature: Post Pets

  Background:
    Given That the user was able to send requests to the PetStore API.

    Scenario: Create a new Pet
      Given The user wants to create a pet with random data.
      When The user sends a Post request to create the pet.
      Then The user should see that the response has the status 200.
      And should see that the pet was created in the platform.

  Scenario: Create a Pet with a new status
    Given The user wants to create a pet with the data:
      | id  | name    | categoryId | categoryName | status    | photoUrls       | tags              |
      | 35  | dog 35  | 2          | Dogs         | sleeped    | url1,url2       | 1-tag1,2-tag2     |
    When The user sends a Post request to create the pet.
    Then The user should see that the response has the status 200.
    And should see that the pet was created in the platform.

    Scenario: Create a new pet without id
      Given The user wants to create a pet with the data:
        | id  | name  | categoryId | categoryName | status    | photoUrls       | tags              |
        |     | Cat 2 | 2          | Cats         | available | url1,url2       | 1-tag1,2-tag2     |
      When The user sends a Post request to create the pet.
      Then The user should see that the response has the status 500.







