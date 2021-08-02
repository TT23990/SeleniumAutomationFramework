package com.fusion.pageobjects.page;

import org.openqa.selenium.By;
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
 * Page Object Model for Claim Search com.fusion.pageobjects.page
 * 
 * @author Lachireddy Palla(AF33811)
 * @since 29-September-2020
 *
 */
public class ClaimsSearchPage extends SuperHelper {

	private static ClaimsSearchPage thisIsTestObj;

	// So that there only one object accesses this class at any moment
	public synchronized static ClaimsSearchPage get() {
		thisIsTestObj = PageFactory.initElements(getWebDriver(), ClaimsSearchPage.class);
		return thisIsTestObj;
	}

	// Add objects here...
	@FindBy(how = How.XPATH, using = "//h2[text()='Search claims']")
	public WebElement SearchClaimsPageHeader;

	@FindBy(how = How.XPATH, using = "//label[@data-test-id='2015012016464501504618-Label']")
	public WebElement claimNumberLabel;

	@FindBy(how = How.XPATH, using = "//input[@data-test-id='2015012016464501504618']")
	public WebElement claimNumberTextbox;

	@FindBy(how = How.XPATH, using = "//label[@data-test-id='20150106123644032338944-Label']")
	public WebElement billingProviderLabel;

	@FindBy(how = How.XPATH, using = "//input[@data-test-id='20150106123644032338944']")
	public WebElement billingProviderTextbox;

	@FindBy(how = How.XPATH, using = "//label[@data-test-id='20150106123644032439838-Label']")
	public WebElement subscriberIdLabel;

	@FindBy(how = How.XPATH, using = "//input[@data-test-id='20150106123644032439838']")
	public WebElement subscriberIdTextbox;

	@FindBy(how = How.XPATH, using = "//label[text()='Type']")
	public WebElement claimTypeLabel;

	@FindBy(how = How.XPATH, using = "//input[@data-test-id='201501061243030773319555']")
	public WebElement professionalCheckbox;

	@FindBy(how = How.XPATH, using = "//input[@data-test-id='201501061243030774320859']")
	public WebElement institutionalCheckbox;

	@FindBy(how = How.XPATH, using = "//input[@data-test-id='20180110151432019521281']")
	public WebElement dentalCheckbox;

	@FindBy(how = How.XPATH, using = "//label[@data-test-id='201706291219370529303203-Label']")
	public WebElement statusLabel;

	@FindBy(how = How.XPATH, using = "//input[@data-test-id='201706291219370529303203']")
	public WebElement statusTextbox;

	// Dynamic Field to select Claim Number from the search results
	public WebElement dynamicClaimNumber(String strClaimNumber) {
		return getWebDriver().findElement(By.xpath("//td/div/span[text()='" + strClaimNumber + "']"));
	}

	@FindBy(how = How.XPATH, using = "//button[@data-test-id='201607080940200123385414']")
	public WebElement searchButton;
	
	@FindBy(how = How.XPATH, using = "//div[text()='No claims found with the given search criteria']")
	public WebElement claimNotFoundErrorMessage;
	
}