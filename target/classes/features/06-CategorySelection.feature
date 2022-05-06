@Regression
  @CategorySelection
Feature: Logged user could select different Categories
  Scenario: user selects random category then hover and open sub-Category if found
    When user logs in with "adam@adam.com" as a valid email and "123456" as a valid password then selects random category then hover and open sub-Category
    Then user should be able to see the contents of sub-Category on its webpage