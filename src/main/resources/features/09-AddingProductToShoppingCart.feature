@Regression
  @ShoppingCart
Feature: Logged user could add different products to Shopping cart
  Scenario: user navigates to the product page and clicks ADD TO CART to add it to the cart
  When user logs in with "adam@adam.com" as a valid email and "123456" as a valid password then direct to the selected item then clicks on ADD TO CART for the wanted item
  Then selected item should be added to the shopping cart