@Regression
  @CreatingOrder
Feature: Create successful Order
  Scenario: user navigates to the product page add it to the cart, navigates to the cart and complete order successfully
    When user logs in with "adam@adam.com" as a valid email and "123456" as a valid password
    And direct to the selected item then clicks on ADD TO CART for the wanted item
    And direct to the cart to proceed checkout
    And complete all fields till order confirmed
    Then order should be successfully created