package com.fusion.actions;

import com.fusion.pageobjects.DashboardPage;
import static com.anthem.selenium.SuperHelper.*;
import static com.fusion.utilities.CoreSuperHelper.seIsElementDisplayed;

public class DashboardAction extends BaseActions {
    DashboardPage dashboard;

    public boolean searchWorkQueue(String workQueue, String strConfirmation) throws InterruptedException {
        // Approve Reject and Send back to originator
        dashboard = new DashboardPage();
        getWebDriver().switchTo().defaultContent();
        seClick(dashboard.selectTab("Dashboard"), "Dashboard");
        seClick(dashboard.workQueueItem(workQueue), workQueue);
        seClick(dashboard.workBasketIdFilter, "ID Filter");
        Thread.sleep(5000);
        seWaitForElementLoad(dashboard.labelInput("Search Text"));
        seSetText(dashboard.labelInput("Search Text"), strConfirmation);
        seClick(dashboard.buttonText("Apply"), "Apply");
        seWaitForPageLoad();

        if(seIsElementDisplayed(dashboard.workBasketItem(strConfirmation),"Work ID:"+strConfirmation,true)){
            seClick(dashboard.workBasketItem(strConfirmation),"Work ID: "+strConfirmation);
            return true;
        }else return false;
    }



}
