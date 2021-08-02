package com.fusion.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {
    //    Initializing com.fusion.pageobjects.page objects
    public HomePage() {
        super();
        initElements();
    }

    @FindBy(xpath = "//i[@class='icons avatar name-t ']")
    public WebElement ProfilePhoto;

    @FindBy(xpath = "//span[text()='Log off']")
    public WebElement LogoutLink;

    public WebElement selectUser(String value) {
        return wd.findElement(By.xpath("//a[contains(@data-click,'FHPS:"+value+"')]"));
    }

    @FindBy(xpath = "//span[@class='appName slds-context-bar__label-action slds-context-bar__app-name']")
    public WebElement ApplicationName;

    @FindBy(xpath = "//div[@class='slds-notify slds-notify_alert system-message level-info slds-theme_info']/child::span")
    public WebElement ApplicationSandboxName;

    @FindBy(xpath = "//a[text()='Switch to Salesforce Classic']")
    public WebElement switchToClassic;

    @FindBy(xpath = "//div/a[text()='Switch to Lightning Experience']")
    public WebElement switchToLightning;

    @FindBy(xpath = "//input[@id='input-5']")
    public WebElement GlobalSearchOption;

    @FindBy(xpath = "//input[@id='input-5']/following::input[1]")
    //| //input[@id='173:0;p']") @id='173:82;a' or @id='173:0;p' or //input[@id='143:0;p']
    public WebElement GlobalSearchBox;

    @FindBy(xpath = "//div[@class='slds-icon-waffle']")
    public WebElement ApplicationLaunchBtn;//span[@class='uiImage']/following::div[5]

    @FindBy(xpath = "//input[@class='slds-input'][@placeholder='Search apps and items...']")
    public WebElement ApplicationSearchBox;

    @FindBy(xpath = "//button[text()='View All']")
    public WebElement viewAllApplicationLink;

    @FindBy(xpath = "//a[@id='19:7415;a']")
    public WebElement searchDataMatch;

    @FindBy(xpath = "//div[@class='setupGear']//a[@role='button']")
    public WebElement setupGearIcon;

    @FindBy(xpath = "//mark[contains(@class,'data-match')]")
    public WebElement SelectSearchItemFromList;


    //Actions:

    public boolean validateSelectedSandbox(String value) {
        return ApplicationSandboxName.getText().contains(value);
    }


    public boolean validateSelectedApplication(String application) {
        return ApplicationName.getText().equalsIgnoreCase(application);
    }









}

 