#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Purchase the order from Ecommerce website

  Background:
    Given I landed on Ecommerce Page

  Scenario Outline: Positive scenario for submitting the order
    Given I logged in with <username> and <password>
    When I add product "<productName>" to the cart
    And checkout product "<productName>" and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

    Examples:
      | username            | password | productName  |
      | balasb477@gmail.com | Kgf@2000 | ZARA COAT 3  |