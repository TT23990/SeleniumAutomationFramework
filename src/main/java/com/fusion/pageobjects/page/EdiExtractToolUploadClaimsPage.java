package com.fusion.pageobjects.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.fusion.utilities.CoreSuperHelper;

/*
'Revision History
'#############################################################################
'@rev.On	@rev.No		@rev.By				  @rev.Comments
'										
'#############################################################################
*/

/**
 * Contains the web elements for the EDI Extract Tool Upload Claims screen
 * 
 * @author Lachireddy (AF33811)
 * @since 12-Nov-2019
 * 
 *
 */
public class EdiExtractToolUploadClaimsPage extends CoreSuperHelper {

	private static EdiExtractToolUploadClaimsPage thisIsTestObj;

	// So that there only one object accesses this class at any moment
	public synchronized static EdiExtractToolUploadClaimsPage get() {
		thisIsTestObj = PageFactory.initElements(getWebDriver(), EdiExtractToolUploadClaimsPage.class);
		return thisIsTestObj;
	}

	// Recommended model for all objects

	@FindBy(how = How.XPATH, using = "//h1/a[text()='EDI Extract Tool']")
	public WebElement homePageHeaderFields;

	@FindBy(how = How.XPATH, using = "//div/label[text()='File:']")
	public WebElement fileLabel;

	@FindBy(how = How.CLASS_NAME, using = "span12")
	public WebElement chooseFilesButton;
	
	@FindBy(how = How.ID, using = "uploadFile01")
	public WebElement chooseFilesInput;

	@FindBy(how = How.XPATH, using = "//input[@value = '1']")
	public WebElement qa1RegionRadioButton;

	@FindBy(how = How.XPATH, using = "//input[@value = '2']")
	public WebElement qa2RegionRadioButton;

	@FindBy(how = How.XPATH, using = "//input[@value = '3']")
	public WebElement qa3RegionRadioButton;

	@FindBy(how = How.XPATH, using = "//span[text()='Submit']")
	public WebElement submitButton;

	@FindBy(how = How.XPATH, using = "//span[text()='Reset']")
	public WebElement resetButton;
}