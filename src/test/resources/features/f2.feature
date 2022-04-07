Feature: validate compose

  Background: 
    Given Launch site using "firefox" browser
    And Lunch site

  Scenario Outline: validate yahoo compose mail
    When enter userid as "msdet21"
    And click userid next button
    And enter password  "Learn@123"
    And click pwd next button
    Then validate login page
    Then compose a mail to different ids and validate
      | to                         | sub    | messagebody | attach                                                |
      | msdet21@yahoo.com          | wishes | hi          | C:\\Users\\vani\\OneDrive\\Pictures\\bugvsfeature.png |
      | mamatha2623@rediffmail.com | wishes | hi          | C:\\Users\\vani\\OneDrive\\Pictures\\bugvsfeature.png |
      | learnsdet21@gmail.com			 | wishes | hi          | C:\\Users\\vani\\OneDrive\\Pictures\\images.jpeg 			|
    Then logout mail
    And quite site
