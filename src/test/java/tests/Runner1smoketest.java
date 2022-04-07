package tests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features={"src\\test\\resources\\features"},
		glue= {"gluecode"},
		tags="@Smoketest",
		monochrome=true,
		plugin= {"pretty","html:target/smoketestresyahoo","json:target/smoketestyahoores",
				"junit:target/smoketestyahoores.xml","rerun:target/failedyahoosmoketest.txt"})
public class Runner1smoketest extends AbstractTestNGCucumberTests 
{

}
