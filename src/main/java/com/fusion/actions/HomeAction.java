package com.fusion.actions;

import com.fusion.pageobjects.HomePage;
import org.openqa.selenium.Keys;

import static com.anthem.selenium.SuperHelper.*;
import static com.fusion.utilities.CoreSuperHelper.seMoveToElement;
import static com.fusion.utilities.CoreSuperHelper.seWaitForVisibility;

public class HomeAction extends BaseActions{
    public HomePage home;

    public void logoutAndCloseBrowser() throws InterruptedException {
        System.out.println("Logging off ***");
        home = new HomePage();
        getWebDriver().switchTo().defaultContent();
        seClick(home.ProfilePhoto,"Profile photo");
        seClick(home.selectSpan("Log off"),"Logout");
        Thread.sleep(3000);
        System.out.println("Closing browser ***");
        seCloseBrowser();
    }

    public boolean switchUserRoleTo(String role) {
        System.out.println("Switching role to "+role+" ***");
        home = new HomePage();
        if(home.getTitle().contains(role)) return true;
        getWebDriver().switchTo().defaultContent();
        seClick(home.ProfilePhoto,"Profile photo");
        seMoveToElement(home.selectSpan("Switch apps"),"Switch apps");
        seWaitForVisibility(home.selectUser(role.replaceAll("\\s","")),"Switch role: "+role);
        seClick(home.selectUser(role.replaceAll("\\s","")),"Switch role: "+role);
        seWaitForPageLoad();
        return true;
    }

    public boolean selectPegaMenu(String item) {
        System.out.println("Selecting Pega Menu item : "+item+" ***");
        home = new HomePage();
        getWebDriver().switchTo().defaultContent();
        seMoveToElement(home.selectSpan(item),"Pega Menu: "+item);
        seClick(home.selectSpan(item),"Pega Menu: "+item);
        seMoveToElement(home.ProfilePhoto,"Profile photo");
        return true;
    }

    public boolean validateSelectedSandbox(String value) {
        return home.ApplicationSandboxName.getText().contains(value);
    }


    public boolean validateSelectedApplication(String application) {
        return home.ApplicationName.getText().equalsIgnoreCase(application);
    }

    public boolean selectApplication(String appName) {
        try {
            home.ApplicationLaunchBtn.click();
            home.ApplicationSearchBox.sendKeys(appName);
            home.selectApplicationFromSearchList(appName).click();
            home.applicationData.put("Application", appName);
            home.applicationData.put("URL", home.getCurrentUrl());
//            outputData.put("Home",applicationData);
            Thread.sleep(home.POLLING);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean searchItemList(String item, String value) {
        try {
            home.GlobalSearchOption.sendKeys(item + Keys.RETURN);
//        getHeaderSearchOption.sendKeys(Keys.TAB + value);
            home.GlobalSearchBox.clear();
            home.GlobalSearchBox.sendKeys(value);
            Thread.sleep(home.POLLING);
            home.waitForElementToAppear(home.SelectSearchItemFromList);
            home.SelectSearchItemFromList.click();

            return true;
        } catch (Exception e) {
            System.out.println("Item > " + item + " :" + value + " not found");
            return false;
        }
    }
}
