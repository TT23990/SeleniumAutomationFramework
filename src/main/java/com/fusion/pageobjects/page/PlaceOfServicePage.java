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
 * Page Object Model for Place Of Service Home com.fusion.pageobjects.page
 * 
 * @author Lachireddy Palla(AF33811)
 * @since 21-October-2020
 *
 */
public class PlaceOfServicePage extends SuperHelper {

	private static PlaceOfServicePage thisIsTestObj;

	// So that there only one object accesses this class at any moment
	public synchronized static PlaceOfServicePage get() {
		thisIsTestObj = PageFactory.initElements(getWebDriver(), PlaceOfServicePage.class);
		return thisIsTestObj;
	}

	// Add objects here...
	@FindBy(how = How.XPATH, using = "//span[text()='Place of Service']")
	public WebElement placeOfServiceTitle;

	@FindBy(how = How.XPATH, using = "//button[text()='Add new code']")
	public WebElement addNewCodeButton;

	@FindBy(how = How.NAME, using = "$PpyWorkPage$pCodeSearchValue")
	public WebElement codeSearchTextbox;

	@FindBy(how = How.XPATH, using = "//button[text()='Search']")
	public WebElement searchButton;

	@FindBy(how = How.XPATH, using = "//button[text()='Clear']")
	public WebElement clearButton;

	@FindBy(how = How.XPATH, using = "//table[@pl_prop='D_PlaceOfServiceList.pxResults']")
	public WebElement placeOfServiceTable;

	@FindBy(how = How.XPATH, using = "//tr//a[@class='iconExplore']")
	public WebElement editCodeDetailsIcon;

	@FindBy(how = How.XPATH, using = "//tr//a[@class='iconExplore']")
	public WebElement deleteIcon;

	// span[contains(text(),'Add code')]")

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Add code')]")
	public WebElement addCodeHeaderName;

	@FindBy(how = How.XPATH, using = "//div[@id='modaldialog_hd']/button[@id='container_close']")
	public WebElement closeIcon;

	@FindBy(how = How.NAME, using = "$PpyWorkPage$pAfterValues$pCode")
	public WebElement codeTextbox;

	@FindBy(how = How.NAME, using = "$PpyWorkPage$pAfterValues$pEffectiveDate")
	public WebElement effectiveDateTextbox;

	@FindBy(how = How.NAME, using = "$PpyWorkPage$pAfterValues$pExpirationDate")
	public WebElement cancelDateTextbox;

	@FindBy(how = How.NAME, using = "$PpyWorkPage$pAfterValues$pName")
	public WebElement nameTextbox;

	@FindBy(how = How.NAME, using = "$PpyWorkPage$pAfterValues$pACESCode")
	public WebElement acesCodeTextbox;

	@FindBy(how = How.NAME, using = "$PpyWorkPage$pAfterValues$pDescription")
	public WebElement descriptionTextbox;

	@FindBy(how = How.NAME, using = "$PpyWorkPage$pAfterValues$pUserComments")
	public WebElement commentsTextbox;

	@FindBy(how = How.XPATH, using = "//button[@data-test-id='202001101453020991227247']")
	public WebElement cancelButton;

	@FindBy(how = How.XPATH, using = "//button[text()='Add']")
	public WebElement addButton;
	
	@FindBy(how = How.XPATH, using = "//span[@id='ERRORMESSAGES_ALL']//li")
	public WebElement errorMessage;
}