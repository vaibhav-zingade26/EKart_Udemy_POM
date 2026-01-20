Feature: Smoke Testing

  Background:
    Given Land on Ecommerce webside

  @PositiveTests
  Scenario Outline:Positive test on submitting order
    Given Logged in with username <username> and password <password>
    When user added the product <product>
    And  checkout the order
    Then "THANKYOU FOR THE ORDER." message should be displayed on confirmationPage.
    Examples:
      | username            | password  | product         |
      | vaibhav26@gmail.com | VacZ@1234 | ADIDAS ORIGINAL |
      | VacZ@9464.com       | VacZ@9464 | ZARA COAT 3     |


    Scenario Outline:User able to go on orders and Cart page
      Given Logged in with username <username> and password <password>
      When User clicked on orders
      Then User should land on orders page
      And User came back on and dashboard click on cart
      Then User should land on Cart page
      Examples:
        | username            | password  |
        | vaibhav26@gmail.com | VacZ@1234 |


    Scenario Outline:Verify the price and product
    Given Logged in with username <username> and password <password>
    And fetch the price of <product>
    When click on view of <product>
    Then Verify <product> name and price value
    Examples:
      | username            | password  | product|
      | vaibhav26@gmail.com | VacZ@1234 |iphone 13 pro|
      | vaibhav26@gmail.com | VacZ@1234 |ZARA COAT 3|
      | vaibhav26@gmail.com | VacZ@1234 |ADIDAS ORIGINAL|
      | vaibhav26@gmail.com | VacZ@1234 |Automation 8|

  @Today
  Scenario Outline:Verify total amount of shopping
    Given Logged in with username <username> and password <password>
    When user added the products <product1> and <product2>
    Then Verify total amount in cartPage
    Examples:
      | username            | password  |product1 | product2|
      | vaibhav26@gmail.com | VacZ@1234 |ADIDAS ORIGINAL|ZARA COAT 3|

  @Today
  Scenario Outline:Verify search button
    Given Logged in with username <username> and password <password>
    When user search the products <product1>
    Then <product1> should come on dashboard
    When user search the products <product2>
    Then <product2> should come on dashboard
    Examples:
      | username            | password  |product1 | product2|
      | vaibhav26@gmail.com | VacZ@1234 |ADIDAS ORIGINAL|ZARA COAT 3|


