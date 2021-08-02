package stepdefinitions;

import com.anthem.selenium.utility.EnvHelper;
import com.fusion.actions.LoginAction;
import com.fusion.utilities.CoreSuperHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class CucumberHooks extends CoreSuperHelper {
	static String BASE_URL = EnvHelper.getValue("url");

	@Before
	public void setUp() throws InterruptedException {
		System.out.println("*****Executing before hook*****");
		LoginAction.loginToPegaApplication();
	}

	@After
	public void tearDown() throws InterruptedException {
		System.out.println("*****Executing after hook*****");
//		LoginAC.logoutAndCloseBrowser();
	}
}