Feature:

  Background:
    Given Land on Ecommerce webside

  @NegativeTests
  Scenario Outline: Negative test for login
    Given Logged in with username <username> and password <password>
    Then message <msg> should displayed on login Page
    Examples:
      | username            | password | msg |
      | vaibhav26@gmail.com | frgferge |Incorrect email or password.|
      | vaibhav26@gmail.com | f4f4fr   |Incorrect email or password.|

