package tests;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
		features= {"src//test//resources//features"},
		glue= {"gluecode"},
		monochrome=true,
		plugin= {"pretty","html:target//paralleltest","rerun:target//faikedtestscenarios.txt"}
		)

public class ParallelTest extends AbstractTestNGCucumberTests
{
	@DataProvider(parallel=true)
	public Object[][] scenarios()
	{
		return super.scenarios();
	}
}
