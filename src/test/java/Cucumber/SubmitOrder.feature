Feature: Purchase the order from Ecommerce webside.

  Background:
    Given Land on Ecommerce webside

  @PositiveTests
  Scenario Outline:Positive test on submitting order
    Given Logged in with username <username> and password <password>
    When user added the product <product>
    And  checkout the order
    Then "THANKYOU FOR THE ORDER." messsage should displayed on confirmationPage.
    Examples:
      | username            | password  | product         |
      | vaibhav26@gmail.com | VacZ@1234 | ADIDAS ORIGINAL |
      | VacZ@9464.com       | VacZ@9464 | ZARA COAT 3     |

