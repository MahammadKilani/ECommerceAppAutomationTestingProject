@Regression
  @ColorFiltering
Feature: Logged user could filter with color
  Scenario: user would select specific category Apparel --> Shoes, then filter by red color
    When user logs in with "adam@adam.com" as a valid email and "123456" as a valid password then direct to the selected category then filter by red color
    Then Only items with red color should be displayed