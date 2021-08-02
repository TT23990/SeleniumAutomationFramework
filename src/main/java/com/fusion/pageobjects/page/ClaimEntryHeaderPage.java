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
 * Page Object Model for Claim Entry Header com.fusion.pageobjects.page
 * 
 * @author Lachireddy Palla(AF33811)
 * @since 22-September-2020
 *
 */
public class ClaimEntryHeaderPage extends SuperHelper {

	private static ClaimEntryHeaderPage thisIsTestObj;

	// So that there only one object accesses this class at any moment
	public synchronized static ClaimEntryHeaderPage get() {
		thisIsTestObj = PageFactory.initElements(getWebDriver(), ClaimEntryHeaderPage.class);
		return thisIsTestObj;
	}

	// Add objects here...
	@FindBy(how = How.XPATH, using = "//div[text() ='New Claim']")
	public WebElement newClaimEnteryPageHeader;

	@FindBy(how = How.XPATH, using = "//label[text() ='State']")
	public WebElement stateLabel;

	@FindBy(how = How.XPATH, using = "//input[@data-test-id='202007282231190811575185']")
	public WebElement stateTextbox;

//	@FindBy(how = How.XPATH, using = "//label[text() ='Claim Form']")
//	@FindBy(how = How.XPATH, using ="//div[@class='content-item content-field item-4 flex required']/label")
	@FindBy(how = How.XPATH, using = "//label[@data-test-id='202007282231190811575185-Label']")
	public WebElement claimFormLabel;

	@FindBy(how = How.XPATH, using = "//button[text()='Save for ']")
	public WebElement saveForLaterButton;

	@FindBy(how = How.XPATH, using = "//a[@data-test-id='201708161835040936130769']")
	public WebElement form;

	@FindBy(how = How.XPATH, using = "//li[@id='Tab2']//span[text()='New']")
	public WebElement newTab1;

	@FindBy(how = How.XPATH, using = "//label[@data-test-id='20200728225608065251690-Label']")
	public WebElement receiptDateLabel;

	@FindBy(how = How.XPATH, using = "//input[@data-test-id='20200728225608065251690']")
	public WebElement receiptDateTextbox;

	@FindBy(how = How.XPATH, using = "//input[@data-test-id=\120200728225608065252147-Label']")
	public WebElement claimNumberLabel;

	@FindBy(how = How.XPATH, using = "//input[@data-test-id='20200728225608065252147']")
	public WebElement claimNumberTextbox;

//	@FindBy(how = How.XPATH, using = "//input[@id='ab8c94c1']")

	@FindBy(how = How.XPATH, using = "//input[@name='$PpyWorkPage$pClaim$pClaimForm']")
//			"//input[@id='ab8c94c1']")
	public WebElement claimFormTextbox;

	// Dynamic Field to select Claim Form
	public WebElement dynamicClaimForm(String strClaimForm) {
		String strClaimFormUpdate = strClaimForm.toUpperCase();
		return getWebDriver().findElement(By.xpath("//div//span[text()='" + strClaimFormUpdate + "']"));
//		return getWebDriver().findElement(By.xpath("//table[@pl_prop_class='Antm-FHPS-Data-Lookup-KeyValue']//span[text()=" + strClaimFormUpdate + "]"));
	}

	// Dynamic Field to select State
	public WebElement dynamicState(String strState) {
		String strStateUpdate = strState.toUpperCase();
		return getWebDriver().findElement(By.xpath("//tr[contains(@data-gargs,'" + strStateUpdate + "')]//div[@class='cellIn']/span/span"));
	}

//	@FindBy(how = How.XPATH, using = "//input[@data-test-id='20200728225608065353165']")
	@FindBy(how = How.XPATH, using = "//input[@id='bdd5e75d']")

	public WebElement subscriberIdTextbox;

	@FindBy(how = How.XPATH, using = "//button[@data-test-id='20180110112125064054133']")
	public WebElement nextButton;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Invalid State specified. Please choose a State from the list.')]")
	public WebElement stateErrorMsg;

	@FindBy(how = How.XPATH, using = "//div[@id='$PpyWorkPage$pClaim$ppyStateError']/span[@class='iconError dynamic-icon-error']")
//			"//span[@id='PegaRULESErrorFlag']")
	public WebElement stateErrorMsg1;

	@FindBy(how = How.XPATH, using = "//div[@data-test-id='202009101806250734960']")
	public WebElement emptySpace;

	@FindBy(how = How.XPATH, using = "//span[@data-test-id='201607192126110807412392-Label']")
	public WebElement totalClaimAmountLabel;
	
	@FindBy(how = How.XPATH, using = "//input[@name='$PpyWorkPage$pClaim$pAttachmentIndicator']")
	public WebElement attachmentTextbox;
	
	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Plan Code')]/following::div[1]")
	public WebElement planCodeField;
	
	
	
}