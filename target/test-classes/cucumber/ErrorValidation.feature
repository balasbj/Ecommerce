#
@tag
Feature: Error Validation
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Negative scenario for login page
    Given I landed on Ecommerce Page
    When I logged in with <username> and <password>
    Then "Incorrect email or password." message is displayed
    Examples: 
      | username            | password |
      | balasb477@gmail.com | Kgf@200 |