package com.fusion.actions;

import com.anthem.selenium.SuperHelper;
import com.anthem.selenium.constants.BrowserConstants;
import com.anthem.selenium.utility.EnvHelper;
import com.fusion.pageobjects.BasePage;
import com.fusion.pageobjects.DashboardPage;
import com.fusion.utilities.CoreSuperHelper;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import static com.anthem.selenium.SuperHelper.*;
import static com.anthem.selenium.SuperHelper.seClick;
import static com.anthem.selenium.utility.ExcelUtility.getCellValue;
import static com.fusion.utilities.CoreSuperHelper.seIsElementDisplayed;

public class BaseActions {

    public void iframeManager(String value) {

    }

    public boolean tableOperator(String tableName, String operation, String searchValue) {
//        tableName=tableName.replaceAll("\\s","");
        BasePage basePage = new BasePage();
        List<WebElement> rowList = basePage.getTableRows(tableName);
        //TODO No results to display
        if (rowList.size() > 1 && rowList.size() == 2)
            if (seGetText(rowList.get(1).findElements(By.tagName("td")).get(0)).contains("No results to display"))
                return false;

        List<String> header = new ArrayList<>();
        for (WebElement eachHeader : rowList.get(0).findElements(By.tagName("th")))
            header.add(seGetText(eachHeader));

        boolean found=true;
        for (WebElement eachRow : rowList) {
            List<WebElement> colList = eachRow.findElements(By.tagName("td"));
            if (colList.size() == 0) continue;

            System.out.println("Description: " + seGetText(colList.get(header.indexOf("Description"))));
            found=true;
            for(String eachSearchValue:searchValue.split(","))
                if(!seGetText(colList.get(header.indexOf(eachSearchValue))).contains(getCellValue(eachSearchValue))) {
                    found = false;
                    break;
                }

//            if (seGetText(colList.get(header.indexOf("Description"))).contains(searchValue) ||
//                    seGetText(colList.get(header.indexOf("Code"))).contains(searchValue))
                if(found)
                switch (operation.toLowerCase()) {
                    case "search":
                        System.out.println("Search successful: " + searchValue);
                        return true;
                    case "update":
                        eachRow.findElement(By.className("iconUpdate")).click();
                        return true;
                    case "delete":
                        eachRow.findElement(By.className("iconDelete")).click();
                        return true;
                }
        }

        return found;
    }


    public String verifyModalDialogue() {
        String action = getCellValue("ApprovalRequired");
        BasePage basePage = new BasePage();
        String strConfirmationId = "process";
        if (!action.toLowerCase().contains("yes")) {
            if (seIsElementDisplayed(basePage.modalDialog("close"), "Modal Dialogue", true)) {
                System.out.println("Modal Dialogue displayed which is not expected");
                seClick(basePage.modalDialog("close"), "Close Modal dialog");
                strConfirmationId = "found";
            }
        } else {
            if (seIsElementDisplayed(basePage.modalDialog("close"), "Modal Dialogue", true)) {
                strConfirmationId = seGetText(basePage.modalDialog("message")).replace("\n", " ");
                System.out.println("Confirmation id: " + strConfirmationId);
                strConfirmationId=strConfirmationId.split(" ")[0];
                strConfirmationId=strConfirmationId.substring(1, strConfirmationId.length() - 1);
                seClick(basePage.modalDialog("close"), "Close Modal dialog");
            } else strConfirmationId = "not found";

        }
        return strConfirmationId;
    }

}

 