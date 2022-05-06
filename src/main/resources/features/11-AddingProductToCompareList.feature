@Regression
  @CompareList
Feature: Logged user could add different products to compare list
  Scenario: user navigates to the product page and clicks add to compare list to add it
    When user logs in with "adam@adam.com" as a valid email and "123456" as a valid password then direct to the selected item then clicks on add to compare list for the wanted item
    Then selected item should be added to the compare list