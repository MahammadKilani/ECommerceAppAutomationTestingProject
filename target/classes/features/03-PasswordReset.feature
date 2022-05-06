@Regression
  @PasswordReset
Feature:User could reset his or her password successfully

    Scenario: user navigates to the reset page and enters empty email
      When user enters "" in Email and clicks on reset button
      Then message "Enter your email" should displayed

    Scenario: user navigates to the reset page and enters a valid email
      When user enters "adam@adam.com" in Email and clicks on reset button
      Then message "Email with instructions has been sent to you." should displayed