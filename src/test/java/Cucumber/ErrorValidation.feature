Feature:

  Background:
    Given Land on Ecommerce webside

  @NegativeTests
  Scenario Outline: Negative test for login
    Given Logged in with username <username> and password <password>
    Then "Incorrect email or password." messsage should displayed on login Page.
    Examples:
      | username            | password |
      | vaibhav26@gmail.com | frgferge |
      | vaibhav26@gmail.com | f4f4fr   |

