Feature: login
  Background: 
    Given Launch site using "chrome"

  Scenario Outline: validate yahoo login and logout
    When enter uid as "<uid>"
    And click uid next button
    And enter password as "<pwd>"
    And click password next button
    And do logout
    Then close the site

    Examples: 
      | uid     | pwd       |
      | msdet21 | Learn@123 |