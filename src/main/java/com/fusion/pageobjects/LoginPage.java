package com.fusion.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage()  {
        super();
        initElements();
//        PageFactory.initElements(getWebDriver(),this);
    }

    // Add objects here...
    @FindBy(id = "ttt")
    @CacheLookup WebElement fusionLogo;


    public String validateLoginPageTitle() {
        return null;
    }


    public boolean validateAnthemLogo() {
        return false;
    }


    public boolean fillOutLoginForm(String username, String password) {
        seWaitForElementLoad(inputID("txtUserID"));
        seSetUserId(inputID("txtUserID"), username);
        seSetPassword(inputID("txtPassword"), password);
        return true;
    }

    public boolean clickOnLoginButton() {
        seClick(selectSpan("Log in"), "Log In");
        return true;
    }
}

 