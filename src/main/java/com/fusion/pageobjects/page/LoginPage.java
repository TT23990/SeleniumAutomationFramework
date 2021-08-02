package com.fusion.pageobjects.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.anthem.selenium.SuperHelper;
import com.anthem.selenium.utility.EnvHelper;

/*
'Revision History
'#############################################################################
'@rev.On	@rev.No		@rev.By				  @rev.Comments
'										
'#############################################################################
*/

/**
 * Page Object Model for Login com.fusion.pageobjects.page
 * 
 * @author Lachireddy Palla(AF33811)
 * @since 21-September-2020
 *
 */
public class LoginPage extends SuperHelper {

	private static LoginPage thisIsTestObj;

	// So that there only one object accesses this class at any moment
	public synchronized static LoginPage get() {
		thisIsTestObj = PageFactory.initElements(getWebDriver(), LoginPage.class);
		return thisIsTestObj;
	}

	// Add objects here...
	@FindBy(how = How.XPATH, using = "//div/input[@id='txtUserID']")
	public WebElement userNameTextbox;

	@FindBy(how = How.XPATH, using = "//div/input[@id='txtPassword']")
	public WebElement passwordTextbox;

	@FindBy(how = How.XPATH, using = "//div[@id='submit_row']/button[@class='loginButton']")
	public WebElement loginButton;

	@FindBy(how = How.ID, using = "@id=spnLoginFrgtPwd")
	public WebElement troubleLoggingIngLink;

	public void loginPegaApplication() {

		try {
			// getLoginInfo function provides the user id and password from the user profile
			String[] userInfo = getLoginInfo(EnvHelper.getValue("user.profile"));
			seSetUserId(userNameTextbox, userInfo[0]);
			seSetPassword(passwordTextbox, userInfo[1]);
			seClick(loginButton, "Log In");
			seWaitForPageLoad();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}