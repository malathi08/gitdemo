Feature:Yahoo Login
	Background:
		Given Launch site using "chrome" browser
		Scenario:Validate yahoo home page title
		When title of Page is "Yahoo"
		Then close the site
		
	Scenario Outline:validate login details 
		When enter uid as "<uid>"
		And click uid next button
		And validate outcome related to uid criteria as "<uidcriteria>"
		And enter password as "<pwd>"
		And click password next button
		And validate outcome related to pwd criteria as "<pwdcriteria>"
		Then close the site
		
			Examples:
		|uid			|uidcriteria			|pwd			|pwdcriteria|
		|msdet21	|valid 						|Learn@123|valid			|
		
		Examples:
		|uid			|uidcriteria			|pwd			|pwdcriteria|
		| 				|blank						|N/A			|N/A  			|
		|lernm21	|invalid					|N/A			|N/A				|
		