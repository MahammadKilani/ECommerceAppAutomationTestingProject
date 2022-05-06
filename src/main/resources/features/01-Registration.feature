@Regression
  @Registration
Feature: User could register with valid data

  Scenario: user inputs empty string for all fields
    When user enters "" in First Name, "" in Last Name, "" in Email, "" in Password and "" in Confirm Password
    Then user should not be able to register using empty fields, notification messages should appear to guide the user

  Scenario: user inputs invalid email, short password and unmatched confirmation
    When user enters "Adam" in First Name, "Adam" in Last Name, "AdamAdam" in Email, "12345" in Password and "12346" in Confirm Password
    Then user should not be able to register using invalid email, short password and unmatched confirmation, notification messages should appear to guide the user

  Scenario: user inputs valid data
    When user enters "Adam" in First Name, "Adam" in Last Name, "Adam@Adam.com" in Email, "123456" in Password and "123456" in Confirm Password
    Then user should be able to register using valid data