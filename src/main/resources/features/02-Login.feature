@Regression
  @Login
  Feature: User could log in with valid email and password

    Scenario: user inputs empty email and empty password
      When user inputs "" in Email and "" in Password
      Then user should not be able to login with empty email and empty password

    Scenario: user inputs invalid email
      When user inputs "adam@adam" in Email and "123456" in Password
      Then user should not be able to login with invalid email

    Scenario: user inputs invalid password
      When user inputs "adam@adam.com" in Email and "12345" in Password
      Then user should not be able to login with invalid password

    Scenario: user inputs valid email and valid password
      When user inputs "adam@adam.com" in Email and "123456" in Password
      Then user should be able to login with valid email and valid password