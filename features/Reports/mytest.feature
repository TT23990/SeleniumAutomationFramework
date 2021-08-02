@LoginPage
Feature: Login, I want to be able to login to the application with valid credential.

  Background: Manual Execution Effort For Automation Metrics
    Given Manual Execution Effort is 00:18:00

  Scenario: Successful login with valid credentials

    Given User launched the chrome browser with given URL

    Then User enters the given user id and password

    And Click on login button

#    Then Page title should be "Dashboard / nopCommerce administration"
#
#    When User click on logout link
#
#    Then Page title should be "Your store. Login"

    Then Close browser



