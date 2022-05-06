@Regression
  @CurrencySwitch
Feature: Logged User could switch between currencies US-Euro

  Scenario: user navigates to currency switcher and change from US Dollar to Euro
    When user logs in with "adam@adam.com" as a valid email and "123456" as a valid password then selects the Euro currency
    Then selected currency should be displayed in the page