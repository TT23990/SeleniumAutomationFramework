package com.fusion.test.suites;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = { "features/mytest.feature"},
		glue = "com.fusion.steps.classic",
		plugin = {"pretty","html:target/test-output.html"}
//		tags = {"@MemberEligibility or @DiagnosisCode or @HCPC"}, 
//		plugin = { "pretty", "json:target/cucumber-reports/report.json","html:target/cucumber-reports" }
)

public class RunnerClassTestNG extends AbstractTestNGCucumberTests {
	
//	 private TestNGCucumberRunner testNGCucumberRunner;
//	 @AfterSuite(alwaysRun = true) public void setUpClass() {
//		 System.out.println("Before");}
	/*
	 * @BeforeClass(alwaysRun = true) public void setUpClass() {
	 * System.out.println("Before"); testNGCucumberRunner = new
	 * TestNGCucumberRunner(this.getClass());
	 * 
	 * 
	 * }
	 */

	/*
	 * @DataProvider public Object[][] features(){ return
	 * testNGCucumberRunner.provideFeatures(); }
	 */
	/*
	 * @Test(groups= "cucumber" , dataProvider="features") public void
	 * feature(CucumberFeatureWrapper cucumberFeature) {
	 * testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature()); }
	 */

	/*
	 * @AfterClass(alwaysRun = true) public void tearDownClass() {
	 * 
	 * 
	 * 
	 * testNGCucumberRunner.finish(); }//
	 */
	
}