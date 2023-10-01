Feature: Get Employees

  @Test
  Scenario: Get list all employees who have age is greater than 60 and salary is greater than 300000
    Given I get all employees with minAge is 60 and minSalary is 300000