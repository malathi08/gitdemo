Feature: Yahoo Login
	Background:
		Given Launch site using "chrome" browser 
		And Lunch site
		@Smoketest
		Scenario:Validate yahoo home page title
		When title of the  Page is "Yahoo"
		Then close yahoo site
		
	Scenario Outline:validate login details 
		When enter userid as "<uid>"
		And click userid next button
		And validate outcome related to userid criteria as "<uidcriteria>"
		And enter password  "<pwd>"
		And click pwd next button
		And validate outcome related to password criteria as "<pwdcriteria>"
		Then quite site
		
		@Smoketest
			Examples:
		|uid			|uidcriteria			|pwd			|pwdcriteria|
		|msdet21	|valid 						|Learn@123|valid			|
		
		Examples:
		|uid			|uidcriteria			|pwd			|pwdcriteria|
		| 				|blank						|N/A			|N/A  			|
		|msdet21	|valid						|lear23		|invalid		|
		|lernm21	|invalid					|N/A			|N/A				|
		