package com.fusion.pageobjects.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.anthem.selenium.SuperHelper;

/*
'Revision History
'#############################################################################
'@rev.On	@rev.No		@rev.By				  @rev.Comments
'										
'#############################################################################
*/

/**
 * Page Object Model for Home com.fusion.pageobjects.page
 * 
 * @author Lachireddy Palla(AF33811)
 * @since 21-September-2020
 *
 */
public class HomePage extends SuperHelper {

	private static HomePage thisIsTestObj;

	// So that there only one object accesses this class at any moment
	public synchronized static HomePage get() {
		thisIsTestObj = PageFactory.initElements(getWebDriver(), HomePage.class);
		return thisIsTestObj;
	}

	// Add objects here...
	@FindBy(how = How.XPATH, using = "//aside[@data-skip-target='navigation']")
	public WebElement leftNavifationPane;

	@FindBy(how = How.XPATH, using = "//div[@class='menu-slider menu-slider-nofullscr menu-format-create-case-menu']//span[text()='New']")
//	@FindBy(how = How.XPATH, using = "//li[@id='menu-item-$PpyNavigation1600781066221$ppyElements$l1']")
	public WebElement newTab;

	@FindBy(how = How.XPATH, using = "//div[@class='menu-slider menu-slider-nofullscr menu-format-create-case-menu']//span[text()='Claim']")
	public WebElement claimEntryLink;

	@FindBy(how = How.XPATH, using = "//div[@class='menu-slider menu-slider-nofullscr menu-format-create-case-menu']//span[text()='Claims inquiry']")
	public WebElement claimInquiryLink;

	@FindBy(how = How.XPATH, using = "//li[@data-test-id='201801111409540178632']")
	public WebElement claimsTab;

	@FindBy(how = How.XPATH, using = "//ul[@data-menu-id='DataPortalDisplayMenuItemsCE5630fb080-2']//span[text()='Claims Search']")
	public WebElement claimsSearchLink;

	@FindBy(how = How.XPATH, using = "//a[@data-test-id='201708161835040936130769']")
	public WebElement getNextHeaderLink;

	@FindBy(how = How.XPATH, using = "//div[contains(@data-keydown,'showMenu')]")
	public WebElement loggedinUserIdLink;

	@FindBy(how = How.XPATH, using = "//span[text()='Switch apps']")
	public WebElement switchAppsLink;

	@FindBy(how = How.XPATH, using = "//a[contains(@data-click,'FHPS:TestAnalyst')]")
	public WebElement testAnalystRoleLink;

	@FindBy(how = How.XPATH, using = "//a[contains(@data-click,'FHPS:BusinessAnalyst')]")
	public WebElement businessAnalystRoleLink;

	@FindBy(how = How.XPATH, using = "//a[contains(@data-click,'FHPS:JuniorClaimsExaminer')]")
	public WebElement juniorClaimExaminerRoleLink;

	@FindBy(how = How.XPATH, using = "//a[contains(@data-click,'FHPS:ClaimsManager')]")
	public WebElement claimsManagerRoleLink;

	@FindBy(how = How.XPATH, using = "//a[contains(@data-click,'FHPS:ClaimsExaminer')]")
	public WebElement claimsExaminerRoleLink;

	@FindBy(how = How.XPATH, using = "//span[text()='Lookups']")
	public WebElement lookupsLink;

	@FindBy(how = How.XPATH, using = "//span[text()='Place Of Service']")
	public WebElement placeOfServiceLink;

	@FindBy(how = How.XPATH, using = "//div/h2[text()='Work queues']")
	public WebElement workQueuesHeaderName;

	@FindBy(how = How.XPATH, using = "//div[@title='Click to expand Work queues']")
	public WebElement workQueuesExpandLink;

	@FindBy(how = How.XPATH, using = "//div[@title='Click to collapse Work queues']")
	public WebElement workQueuesCollapseLink;

	@FindBy(how = How.XPATH, using = "//div/h2[text()='Team members']")
	public WebElement teamMembersHeaderName;

	@FindBy(how = How.XPATH, using = "//div[@title='Click to expand Team members']")
	public WebElement teamMembersExpandLink;

	@FindBy(how = How.XPATH, using = "//div[@title='Click to collapse Team members']")
	public WebElement teamMembersCollapseLink;

	@FindBy(how = How.XPATH, using = "//span[@data-name='Lookup Manager']")
	public WebElement lookupManagerLink;

	@FindBy(how = How.XPATH, using = "//table[@data-test-id='201709150134270808564-layout']//th[1]//a[@id='pui_filter']")
	public WebElement workQueuesTableIdFilterLink;

	@FindBy(how = How.XPATH, using = "//input[@data-test-id='201411181100280377101613']")
	public WebElement searchTextbox;

	@FindBy(how = How.XPATH, using = "//button[contains(@onclick,'OK')]")
	public WebElement applyButton;

	@FindBy(how = How.XPATH, using = "//button[contains(@onclick,'cancel')]")
	public WebElement cancelButton;

	@FindBy(how = How.XPATH, using = "//span[text()='Work Item']")
	public WebElement workItemTab;
}