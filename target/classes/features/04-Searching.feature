@Regression
  @Searching
Feature: Logged User could search for any product

  Scenario:  and hover to the search field in the main page
    When user logs in with "adam@adam.com" as a valid email, "123456" as a valid password and "Lenovo" as an input for the search field
    Then search results should be displayed in the page