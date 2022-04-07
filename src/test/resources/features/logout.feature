Feature: Compose

  Background: 
    Given Launch site using "chrome"

  Scenario Outline: validate yahoo compose mail 
    When enter uid as "msdet21"
    And click uid next button
    And enter password as "Learn@123"
    And click password next button
    Then validate login
    And compose mail and test
      | to                         | subject | messagebody |
      | learnsdet21@gmail.com      | wishes  | hi          |
      | mamatha2623@rediffmail.com | wishes  | hi          |
    And do logout
    Then close site
