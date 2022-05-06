@Regression
  @TagSelection
Feature: Logged user could select different tags
  Scenario: user navigates to any category then select a tag
    When user logs in with "adam@adam.com" as a valid email and "123456" as a valid password then direct to the selected category then clicks on cool tag
    Then user should be navigated to a page with products tagged cool