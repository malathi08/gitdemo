@Smoketest
Feature: validate compose

  Background: 
    Given Launch site using "firefox" browser
    And Lunch site

  Scenario: validate yahoo compose mail
    When enter userid as "msdet21"
    And click userid next button
    And enter password  "Learn@123"
    And click pwd next button
    Then validate login page
    Then compose a mail by taking data from EXcel file using "src\\test\\resources\\testdatafiles\\composemailto.xlsx"
    Then logout mail
    And quite site
