package com.fusion.pageobjects;

import com.anthem.selenium.SuperHelper;
import com.fusion.listeners.WebEventListener;
import com.fusion.utilities.CoreSuperHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedHashMap;
import java.util.List;

public class BasePage extends SuperHelper {

    protected static WebDriver chromeDriver;
    public static WebDriver wd;
    protected static WebDriverWait wait;
    protected static Actions action;
    protected static JavascriptExecutor js;
    protected static String SwitchDriver = "Default";
    public static String profiles = "ALL";

    public static final int TIMEOUT = 15; //seconds
    public static final int POLLING = 900;// milliseconds
    public static final String TEST_CYCLE = "dryrun";
    public static final String SANDBOX = "SIT";

    public static LinkedHashMap<String, LinkedHashMap<String, String>> outputData=new LinkedHashMap<>();
    public static LinkedHashMap<String, String> applicationData;

    //Initialize event listener
    public void initElements() {
        wd=SwitchDriver.equalsIgnoreCase("Private")?chromeDriver:getWebDriver();
        applicationData=new LinkedHashMap<>();
        applicationData.put("Status","No Run");
        applicationData.put("URL","Unknown");
        wd = new EventFiringWebDriver(wd).register(new WebEventListener());
        wait = new WebDriverWait(wd, TIMEOUT, POLLING);
        action = new Actions(wd);
        js=(JavascriptExecutor) wd;
        PageFactory.initElements(new AjaxElementLocatorFactory(wd, TIMEOUT), this);
    }

    private static BasePage thisIsTestObj;

    // So that there only one object accesses this class at any moment
    public synchronized static BasePage get() {
        thisIsTestObj = PageFactory.initElements(getWebDriver(), BasePage.class);
        return thisIsTestObj;
    }

    public static WebElement getActiveElement() {
        return wd.switchTo().activeElement();
    }
    public WebElement inputID(String value) {
        return wd.findElement(By.xpath("//input[@id='" + value + "']"));
    }
    public static WebElement selectSpanImg(String value) {
        return wd.findElement(By.xpath("//span[contains(text(),'" + value + "')]/following::img[1]"));
    }
    public static WebElement selectSearchItem(String value) {
        return wd.findElement(By.xpath("//input[@id='input-5']/following::ul[3]//mark[text()='" + value + "'][1]"));
    }
    public static WebElement selectDiv(String value) {
        return wd.findElement(By.xpath("//div[text()='" + value + "']"));
    }

    public WebElement labelText(String value) {
        return wd.findElement(By.xpath("//label[text()='" + value + "']"));
    }

    public static WebElement showMoreAction() {
        return wd.findElement(By.xpath("//div[text()='Create Quote/Proposal' or text()='Edit']/following::a[contains(@title,' more actions')][1]"));
    }
    protected static WebElement selectLinkText(String value) {
        return wd.findElement(By.xpath("//a[text()='"+value+"']"));
    }
    protected static WebElement selectParaLink(String value) {
        return wd.findElement(By.xpath("//p[text()='"+value+"']/following::a[1] |" +
                "//span[text()='"+value+"']/following::a[1]"));
    }
    public static WebElement selectSpan(String value) {
        return wd.findElement(By.xpath("//span[text()='" + value + "']"));
    }

    public WebElement selectSpanCaseTitle(String value) {
        return wd.findElement(By.xpath("//span[text()='" + value + "' and @class='case_title']"));
    }


    public static WebElement selectSpanPrecedingInput(String value) {
        return wd.findElement(By.xpath("//span[text()='" + value + "']/preceding::input[1]"));
    }
    public static WebElement spanContainsPrecedingLabel(String value) {
        return wd.findElement(By.xpath("//span[contains(text(),'" + value + "')]/preceding::label[1]"));
    }
    public static WebElement starContainsPrecedingLabel(String value) {
        return wd.findElement(By.xpath("//*[contains(text(),'" + value + "')]/preceding::label[1]"));
    }

    public static WebElement spanTitle(String value) {
        return wd.findElement(By.xpath("//span[@title='" + value + "']"));
    }
    public WebElement buttonText(String value) {
        return wd.findElement(By.xpath("//button[text()='" + value + "']"));
    }
    public static WebElement buttonTextContains(String value) {
        return wd.findElement(By.xpath("//button[contains(text(),'" + value + "')]"));
    }
    public static By buttonTextContainsBy(String value) {
        return By.xpath("//button[contains(text(),'" + value + "')]");
    }
    public static WebElement spanInputBox(String value) {
        return wd.findElement(By.xpath("//span[text()='" + value + "']/following::input[1]"));
    }
    public static WebElement spanLightningText(String value) {
        return wd.findElement(By.xpath("//span[text()='"+value+"']/following::lightning-formatted-text[1]"));
    }
    public static WebElement selectImgLink(String value) {
        return wd.findElement(By.xpath("//img[@alt='"+value+"']/parent::a"));
    }
    public static WebElement spanLinkInputBox(String value) {
        return wd.findElement(By.xpath("//span[text()='"+value+"']/following::a[1]"));
//        return wd.findElement(By.xpath("//span[text()='"+value+"']/following::a[1]/following::li[1]//a[text()='"+item+"']"));
    }
    public static WebElement spanImgButton(String value) {
        return wd.findElement(By.xpath("//span[text()='"+value+"']/following::img[1]"));
    }
    public static WebElement linkContainsPrecedingInput(String value) {
        return wd.findElement(By.xpath("//a[contains(text(),'"+value+"')]/preceding::input[1]"));
    }
    public static WebElement labelPrecedingInput(String value) {
        return wd.findElement(By.xpath("//label[text()='"+value+"']/preceding::input[1]"));
    }
    public static WebElement selectTD(String value) {
        return wd.findElement(By.xpath("//td[text()='"+value+"']"));
    }
    public static WebElement spanTextAreaBox(String value) {
        return wd.findElement(By.xpath("//span[text()='"+value+"']/following::textarea[1]"));
    }
    public static WebElement labelTextAreaBox(String value) {
        return wd.findElement(By.xpath("//label[text()='"+value+"']/following::textarea[1]"));
    }
    public WebElement labelInput(String value) {
        return wd.findElement(By.xpath("//label[text()='"+value+"']/following-sibling::*//input"));
    }
    public WebElement labelSelect(String value) {
        return wd.findElement(By.xpath("//label[text()[normalize-space()='" + value + "']]/../following-sibling::*//input[contains(@class,'select')]"));
    }
    public WebElement labelLink(String value) {
        return wd.findElement(By.xpath("//label[text()[normalize-space()='" + value + "']]/../following-sibling::*//a[contains(@class,'select')]"));
    }

    public WebElement divSelect(String value) {
        return wd.findElement(By.xpath("//div[text()[normalize-space()='" + value + "']]"));
    }

    public WebElement divInput(String value) {
        return wd.findElement(By.xpath("//div[text()='"+value+"']/following::input[1]"));
    }
    public static WebElement headerText1(String item, String value) {
        return wd.findElement(By.xpath("//p[contains(text(),'" + item + "')]" +
                "//following::lightning-formatted-text[contains(text(),'" + value + "')]"));
    }
    public static WebElement selectApplicationFromSearchList(String appName) {
        return wd.findElement(By.xpath("//b[contains(text(),'" + appName + "')]"));
    }
    public static void waitForElementToBeClickable(WebElement locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    protected static void waitForElementToAppear(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static void waitForElementToAppear(WebElement locator) {
        wait.until(ExpectedConditions.visibilityOf(locator));
    }
    protected static void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
    protected static void waitForElementToDisappear(WebElement locator) {
        wait.until(ExpectedConditions.invisibilityOf(locator));
    }
    protected static void waitForElementThread() {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    protected static void waitForElementStaleness(WebElement locator) {
        wait.until(ExpectedConditions.not(ExpectedConditions.stalenessOf(locator)));
    }
    protected static void waitForTextToAppear(By locator, String text) {
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(locator, text)));
    }
    protected static void waitForTextToAppear(WebElement locator, String text) {
        wait.until(ExpectedConditions.textToBePresentInElementValue(locator, text));
    }
    public WebElement modalDialog(String value) {
        String xpath="";
        switch(value) {
            case "title":xpath="//div[@id='modaldialog_hd']/span[@id='modaldialog_hd_title']";break;
            case "id":xpath="//div[@id='modaldialog_hd']/following::span[@class='heading_3']";break;
            case "message":xpath="//div[@id='modaldialog_hd']/following::div[contains(@class,'mimic_a_sentence')][1]";break;
            case "close": xpath="//div[@id='modaldialog_hd']/button[@title='close modal']"; break;
        }
        return getWebDriver().findElement(By.xpath(xpath));
    }
    public List<WebElement> getTableRows(String value) {
        //OccurrenceCodes
        return wd.findElements(By.xpath("//table[contains(@pl_prop_class,'"+value+"')]/tbody/tr"));

        //a[@class="iconUpdate"]  a[@class="iconDelete"]

        //span[@class='case_title']/ancestor::div[17]//child::tr[@class='oddRow cellCont' or @class='evenRow cellCont']
        //table[@pl_prop_class="Antm-FHPS-Data-Lookup-OccurrenceCodes"]//tr
        //table[@pl_prop_class="Antm-FHPS-Data-Lookup-Audit"]//tr
        //table[contains(@pl_prop_class,'ICD10')]//tr
        //table[contains(@pl_prop_class,'Code') and not(contains(@pl_prop_class,'Audit'))]/tbody/tr
        //className':'Antm-FHPS-Data-Lookup-Audit'

        //table[@pl_prop_class="Antm-FHPS-Data-Lookup-Audit"]/tbody/tr/following::table

        //table[contains(@pl_prop_class,'Audit') and not(contains(@pl_prop_class,'Code'))]/tbody/tr//table/tbody/tr

        //Final
        //table[@pl_prop_class="Antm-FHPS-Data-Lookup-OccurrenceCodes"]/tbody/tr/following::table[5]//tr/following::table[4 and 7]//tr
    }
    public WebElement selectTab(String value) {
        return wd.findElement(By.xpath("//span[text()='"+value+"' and @class='textIn']"));
    }
    public static String getCurrentUrl() {
        return wd.getCurrentUrl();
    }
    public String getTitle() {
        return wd.getTitle();
    }
    protected boolean elementExists(String  value) {
        try {
            return wd.getPageSource().contains(" "+value+" ")?true:false;
//            return !wd.findElements(locator).isEmpty();
//        } catch (NoSuchElementException e) {
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean getOutputValue(String value) {
        try {
            applicationData.put(value, spanLightningText(value).getText());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void incognitoChromeDriver(String url){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("-incognito");
        options.addArguments("--disable-notifications");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        chromeDriver=new ChromeDriver(capabilities);
        chromeDriver.get(url);
        chromeDriver.manage().window().maximize();

    }

}

 