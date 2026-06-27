Feature: validating place Api's
@AddPlace
  Scenario Outline: add place using addPlace API
    Given Add Place Payload "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" http request
    Then the api call get success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_id to maps name "<name>" using "GetPlaceAPI" using "GET"
    Examples:
      | name    | language   | address         |
      | patrick | English-US | spongbob lagoon |
@DeletePlace
  Scenario: verify delete place API
    Given Delete place payload
    When user calls "DeletePlaceAPI" with "POST" http request
    Then the api call get success with status code 200
    Then "status" in response body is "OK"