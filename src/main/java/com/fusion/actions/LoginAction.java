package com.fusion.actions;

import com.anthem.selenium.constants.BrowserConstants;
import com.anthem.selenium.utility.EnvHelper;
import com.fusion.pageobjects.LoginPage;
import com.fusion.utilities.CoreSuperHelper;

import java.sql.SQLOutput;

public class LoginAction extends CoreSuperHelper {

    public static LoginPage login;

    public static boolean loginToPegaApplication() throws InterruptedException {
        setIgnoreLastTestFailure(true);
        logExtentReport(getCellValue("TEST_CASE_DESCRIPTION"));
        System.out.println("User launched the chrome browser with given URL ***");
        seOpenBrowser(BrowserConstants.Chrome, EnvHelper.getValue("url"));

        System.out.println("User enters the given user id and password ***");
        login = new LoginPage();
        getWebDriver().switchTo().defaultContent();
        String[] userInfo = getLoginInfo(EnvHelper.getValue("user.profile"));
        login.fillOutLoginForm(userInfo[0], userInfo[1]);

        System.out.println("Click on login button ***");
        login.clickOnLoginButton();

        System.out.println("Check Fusion logo ***");
        if(seIsElementDisplayed(login.selectDiv("FUSION"),"Fusion Logo",true))
            return true;
            else {
            seCloseBrowser();
                return false;
        }
    }


}
