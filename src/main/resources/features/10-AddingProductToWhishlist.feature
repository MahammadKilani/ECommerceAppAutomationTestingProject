@Regression
  @Wishlist
Feature: Logged user could add different products to Wishlist
  Scenario: user navigates to the product page and clicks add to wishlist to add it
    When user logs in with "adam@adam.com" as a valid email and "123456" as a valid password then direct to the selected item then clicks on add to wishlist for the wanted item
    Then selected item should be added to the wishlist