Feature: Create Employee


  @Test
  Scenario Outline: Create new employee successfully
    Given I create new employee
    Then I should see response with expectStatus: "<status>",expectMessage: "<message>" and expectStatusCode: 200
    Examples:
      |status      |message      |
      |success     |Successfully! Record has been added.|