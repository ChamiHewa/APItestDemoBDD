Feature: Verify different GET operations using REST Assured. I want to receive details of a specified car type.

  Scenario Outline: Fetch cars of an invalid type
    Given the API endpoint for fetching cars
    When a request is made to fetch cars of <invalidType>
    Then the response status code should be 404
    And the response should be empty
    Examples:
      | invalidType |

  Scenario Outline: Fetch cars of a specified type successfully
    Given the API endpoint for fetching cars
    When a request is made to fetch cars of <validType>
    Then the response status code should be 200
    And the response contains a list of cars of <validType>
    Examples:
      | validType |



