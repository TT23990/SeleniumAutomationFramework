/**
 * 
 */
package com.fusion.utilities;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.anthem.selenium.SuperHelper;


/**
 * @author shiva.katula
 * 
 * ##### Do not delete this file. ######
 * 
 * This class is specifically only for any Project specific Helper Methods for SuperHelper Extension
 *
 * Any method defined in this class will be automatically available in the project like Super Helper methods.
 * Any methods defined here will need to be called without the Class Name to maintain consistency in calling
 * If Project defines a helper method here and if that method is harvested back into Selenium Framework
 * by Architects, then the local method in this class can be deleted with out changing any of the test scripts.
 * 
 * To maintain naming convention across all the projects do not name the method with the Project Name.
 * Also take the suggestion/Approval for the method name to avoid discrepancy with the Framework methods.
 *  
 */
public class CoreSuperHelper extends SuperHelper implements FusionConstants{
	
			

	/**
	 * Function is to verify whether an element is enabled
	 * 
	 * @author Gautam Kumar (AF15254)
	 * 
	 * @param testObject
	 *            object which need to be verified
	 * @param blnScreenShot 
	 * 			  Whether or not to take a screen shot on Pass           
	 * @return boolean is element enabled or not
	 */

	public static boolean seIsElementEnabled(WebElement testObject, String strElementName, boolean blnScreenShot) {
		
		boolean seIsElementEnabled = false;
		
		try {

			if (testObject.isEnabled()) {
				log(PASS, "Verify " + strElementName + " is enabled", strElementName + " is enabled", blnScreenShot);
				seIsElementEnabled = true;
			} else {
				setLastTestPassed(false);
				log(FAIL, "Verify " + strElementName + " is enabled", strElementName + " is not enabled", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setLastTestPassed(false);
			log(FAIL, "Verify " + strElementName + " is enabled", "Exception while verifyng " + strElementName + " is enabled", true);
		}
		
		return seIsElementEnabled;
	}

	/**
	 * Function is to verify whether an element is disabled
	 * 
	 * @author Gautam Kumar (AF15254)
	 * 
	 * @param testObject
	 *            object which need to be verified
	 * @param blnScreenShot 
	 * 			  Whether or not to take a screen shot on pass
	 * @return boolean is element disabled or not
	 */
	public static boolean seIsElementDisabled(WebElement testObject, String strElementName, boolean blnScreenShot) {
		boolean blnIsElementDisplayed = false;
		
		try {

			if (!testObject.isEnabled()) {
				log(PASS, "Verify " + strElementName + " is disabled", strElementName + " is disabled", blnScreenShot);
				blnIsElementDisplayed = true;
			} else {
				setLastTestPassed(false);
				log(FAIL, "Verify " + strElementName + " is disabled", strElementName + " is not disabled", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setLastTestPassed(false);
			log(FAIL, "Verify " + strElementName + " is disabled", "Exception while verifyng " + strElementName + " is disabled", true);
		}
		
		return blnIsElementDisplayed;
	}

	/**
	 * Function is to verify whether the element is displayed or not
	 * 
	 * @author Gautam Kumar (AF15254)
	 * 
	 * @param testObject
	 *            object which need to be verified
	 * @param blnScreenShot
	 * 			  Whether or not to take a screen shot on pass
	 *  @return boolean is element displayed or not
	 */
	public static boolean seIsElementDisplayed(WebElement testObject, String strElementName, boolean blnScreenShot) {
		boolean blnIsElementDisplayed = false;
		
		try {
			if (testObject.isDisplayed()) {
				log(PASS, "Verify " + strElementName + " is displayed", strElementName + " is displayed", blnScreenShot);
				blnIsElementDisplayed = true;
			} else {
				setLastTestPassed(false);
				log(FAIL, "Verify " + strElementName + " is displayed", strElementName + " is NOT displayed", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setLastTestPassed(false);
			log(FAIL, "Verify " + strElementName + " is displayed", "Exception while verifyng " + strElementName + " is displayed", true);
		}
		
		return blnIsElementDisplayed;
	}

	/**
	 * Function is to verify whether the element is NOT displayed
	 * 
	 * @author Algie Watts (AD20221)
	 *  
	 * @param testObject
	 *            object which need to be verified
	 * @param blnScreenShot
	 * 			  Whether or not to take a screen shot on Pass
	 *  @return boolean is element displayed or not
	 */
	
	/*
	'Revision History
	'#############################################################################
	'@rev.On	@rev.No		@rev.By				  @rev.Comments
	'08/04/2017	1			Santosh/Lachireddy	Updated if Statement to verify true, 
	'											added catch phrase for no such element exception
	'#############################################################################
	*/
	
	public static boolean seIsElementNotDisplayed(WebElement testObject, String strElementName, boolean blnScreenShot) {
		boolean blnIsElementNotDisplayed = false;

		try {
			getWebDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			boolean blnIsdisplayed = testObject.isDisplayed();
			getWebDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			if (blnIsdisplayed == true) {
				log(FAIL, "Verify " + strElementName + " is NOT displayed", strElementName + " is displayed", true);
			} else {
				log(PASS, "Verify " + strElementName + " is NOT displayed", strElementName + " is NOT displayed", blnScreenShot);
				blnIsElementNotDisplayed = true;
			}
		} catch (org.openqa.selenium.NoSuchElementException excNoSuchElement) {
			log(PASS, "Verify " + strElementName + " is NOT displayed", strElementName + " is NOT displayed", blnScreenShot);
			blnIsElementNotDisplayed = true;
		} catch (Exception excException) {
			excException.printStackTrace();
			log(FAIL, "Verify " + strElementName + " is NOT displayed",
					"Exception while verifyng " + strElementName + " is NOT displayed", true);
		}
		return blnIsElementNotDisplayed;
	}
	

	/**
	 * Function is to verify whether the element is selected
	 * 
	 * @author Gautam Kumar (AF15254)
	 * 
	 * @param testObject
	 *            object which need to be verified
	 *  @return boolean is element selected or not
	 */
	
	public static boolean seIsElementSelected(WebElement testObject, String strElementName) {
    	boolean blnIsElementSelectedSuccess = false;
		try {
			if (testObject.isSelected()) {
				log(PASS, "Verify " + strElementName + " is Selected", strElementName + " is Selected", true);
				 blnIsElementSelectedSuccess=true;
				
			} else {
				setLastTestPassed(false);
				log(FAIL, "Verify " + strElementName + " is Selected", strElementName + " is not Selected", true);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			setLastTestPassed(false);
			log(FAIL, "Verify " + strElementName + " is not selected ", "Exception while verifyng " + strElementName + " is not selected", true);
			
		}
	return	blnIsElementSelectedSuccess;
		}   
	/**
	 * Function is to verify whether the element is not selected
	 * 
	 * @author Gautam Kumar (AF15254)
	 * 
	 * @param testObject
	 *            object which need to be verified
	 *  @return boolean is element not selected
	 */
    public static boolean seIsElementNotSelected(WebElement testObject, String strElementName) {
    	boolean blnIsElementNotSelectedSuccess = false;
		try {
			if (!testObject.isSelected()) {
				log(PASS, "Verify " + strElementName + " is not Selected", strElementName + " is not Selected", true);
				blnIsElementNotSelectedSuccess = true;
				
			} else {
				setLastTestPassed(false);
				log(FAIL, "Verify " + strElementName + " is  not Selected", strElementName + " is Selected", true);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			setLastTestPassed(false);
			log(FAIL, "Verify " + strElementName + " is selected ", "Exception while verifyng " + strElementName + " is selected", true);
			
		}
		return blnIsElementNotSelectedSuccess;
	}
    
	/**
	 * Just an overloaded function of SuperHelper's seVerifyFieldValue.</br>
	 * In addition to seVerifyFieldValue's functionality, this function takes screenshot even for passed steps.
	 * @param testObject required test object need to test
	 * @param strExpectedValue required Expected data to validate
	 * @param strStep Enter field Name to be validated
	 * @param blnTakeScreenshot Whether to capture a screenshot if step is passed or not. </br>
	 * 			If step failed, SuperHelper's seVerifyFieldValue will captures screenshot by default.
	 * @return boolean true or false depending on the verification was successful or not.
	 */
	public static boolean seVerifyFieldValue(WebElement testObject, String strExpectedValue, String strStep, boolean blnTakeScreenshot)
	{
		boolean blnReturnValue = false;
		try {
			blnReturnValue = seVerifyFieldValue(testObject, strExpectedValue, strStep);
			
			if (blnReturnValue == true && blnTakeScreenshot == true)
			{
				log(PASS, "Screenshot while verifying fild value of" + strStep, "Screenshot while verifying fild value of" + strStep, true);
			}
		} catch (Exception e) {
			log(FAIL, "Executing custom seVerifyFieldValue function", "Exception while executing custom seVerifyFieldValue function", false);
			e.printStackTrace();
		}
		return blnReturnValue;		
	}
	
	/**
	 * Function to scroll until an element is displayed on screen</br>
	 * 
	 * @param testObject required test object need to test
	 * @param strStep Enter field Name to be validated
	 * @return boolean true or false depending on the verification was successful or not.
	 */
	public static boolean seMoveToElement (WebElement testObject, String strStep) {
		
		try {
			Actions actions = new Actions(getWebDriver());
			actions.moveToElement(testObject);
			actions.perform();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
				
	}
	
	/**
	 * Function to scroll until an element is displayed on screen</br>
	 * 
	 * @param testObject required test object need to test
	 * @param strStep Enter field Name to be validated
	 * @return boolean true or false depending on the verification was successful or not.
	 */
	public static boolean seScrollIntoView(WebElement testObject, String strStep) {
		
		try {
			
			WebDriver driver = getWebDriver();
			
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",testObject);
			
			log(PASS, "Scroll till object is on screen", "Scroll until " + strStep + " is displayed on screen.", false);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Function to wait until an element is able to be click</br>
	 * 
	 * @param Element Web Element that will be checked for click ability
	 * @param strElementName String name of the element for use in log messages
	 */
	public static void seWaitForClickable(WebElement Element, String strElementName)
    {
		
		try {
			WebDriver driver = getWebDriver();
			
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			        .withTimeout(java.time.Duration.ofSeconds(60))
			 .pollingEvery(java.time.Duration.ofMillis(1000))
			 .ignoring(NoSuchElementException.class)
			   .ignoring(org.openqa.selenium.WebDriverException.class)
			   	.ignoring(StaleElementReferenceException.class);
			        wait.until((ExpectedConditions.elementToBeClickable(Element)));
		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "Wait for clickable on " + strElementName, "Exception of type occurred while waiting for clickable on " + strElementName, true);
		}
    }

	/**
	 * Function to wait until an element is visible</br>
	 * 
	 * @param Element Web Element that will be checked for visibility
	 * @param strElementName String name of the element for use in log messages
	 */
	public static void seWaitForVisibility(WebElement Element, String strElementName)
    {
		
		try {
			WebDriver driver = getWebDriver();
			
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			        .withTimeout(java.time.Duration.ofSeconds(60))
			 .pollingEvery(java.time.Duration.ofMillis(1000))
			 .ignoring(NoSuchElementException.class)
			   .ignoring(org.openqa.selenium.WebDriverException.class)
			   	.ignoring(StaleElementReferenceException.class);
			        wait.until((ExpectedConditions.visibilityOf(Element)));
		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "Wait for visibility of " + strElementName, "Exception occurred while waiting for visibility of " + strElementName, true);
		}
    }

	/**
	 * Function to wait until an alert is present</br>
	 */
	public static void seWaitForAlert()
    {
		
		try {
			WebDriver driver = getWebDriver();
			
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			        .withTimeout(java.time.Duration.ofSeconds(60))
					.pollingEvery(java.time.Duration.ofMillis(1000))
			 .ignoring(NoSuchElementException.class)
			   .ignoring(org.openqa.selenium.WebDriverException.class);
			      wait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "Wait for Alert to be present", "Exception occurred while waiting for alert to be present", true);
		}
    }

	/**
	 * Function to accept an alert message</br>
	 */
	public static void seAlertAccept()
	{
		getWebDriver().switchTo().alert().accept();
		
		log(PASS, "Click Alert Accept (OK, YES, Etc.) Button", "Alert Accepted", false);
	}
	
	/**
	 * Function to dismiss an alert message</br>
	 */
	public static void seAlertDismiss()
	{
		getWebDriver().switchTo().alert().dismiss();
		
		log(PASS, "Click Alert Dismiss (NO, CANCEL, Etc.) Button", "Alert Dismissed", false);
	}
	
	/**
	 * Function to get the text from an alert message pop up</br>
	 * 
	 * @return string value of the text field on the alert pop up.
	 */
	public static String seAlertGetText()
	{
		String strReturnValue = getWebDriver().switchTo().alert().getText();
		
		log(PASS, "Get the text from an Alert", "Alert Text [" + strReturnValue + "] Captured", false);
		
		return strReturnValue;
	}
	
	/**
	 * Function to refresh the current browser window</br>
	 */
	public static void seRefreshBrowserWindow() 
	{
		getWebDriver().navigate().refresh();
		
		seWaitForPageLoad();
		
		log(PASS, "Refresh the Browser Window", "Browser Wndow Refreshed.", false);
		
	}

	/**
	 * Function to check to see if an element is present.  This function is meant to be used in If statements in scripts</br>
	 * 
	 * @param Element Web Element that will be checked for presence
	 * @param strElementName String name of the element for use in log messages
	 * 
	 * @return returns a boolean value of true if present and false if not
	 */
	public static boolean seCheckForElement(WebElement Element, String strElementName)
	{
		boolean blnReturnValue = true;
		
		try {
			boolean blnIsdisplayed = Element.isDisplayed();
			if (blnIsdisplayed == true) {
				log(PASS, "Check if " + strElementName + " is displayed", strElementName + " is displayed", false);										
			}
			else {
				log(PASS, "Check if " + strElementName + " is displayed", strElementName + " is NOT displayed", false);
				blnReturnValue = false;
			}
				
	} catch (org.openqa.selenium.NoSuchElementException excNoSuchElement) {
		log(PASS, "Check if " + strElementName + " is displayed", strElementName + " is NOT displayed", false);
		blnReturnValue = false;
	}catch (Exception excException)	{
		excException.printStackTrace();
		log(FAIL, "Check if " + strElementName + " is displayed", "Exception while verifyng " + strElementName + " is displayed", true);
	}
		return blnReturnValue;
	}
	
	/**
	 * Function to check to verify the Hover text value.  This function is meant to be used in If statements in scripts</br>
	 * 
	 * @param Element Web Element that will be checked for presence
	 * @param ExpectedValue Expected value to compare the t
	 * @param strElementName String name of the element for use in log messages.DO NOT USE SPECIAL CHARACTERS
	 * 
	 * @return returns a boolean value of true if present and false if not
	 */
	public static boolean seVerifyHoverText(WebElement Element, String ExpectedValue, String strElementName)
	{
		boolean blnReturnValue = false;
		
		try {
			
			Actions action = new Actions(getWebDriver());
			action.clickAndHold(Element).perform();
		    String strHoverText = Element.getAttribute("title");
		    
		    boolean blnVerifyHover = seCompareStrings(ExpectedValue, strHoverText, "=", strElementName+ " Hover Text");
		    
		    if (blnVerifyHover == true) 
			
			{
				log(PASS, "Verify hover text for " + strElementName, "PASS: Hover Text Expected Value ["+ExpectedValue +"], Matches Actual value ["+strHoverText+"]", true);
				blnReturnValue = true;
			}
			else {
				log(FAIL, "Verify hover text for " + strElementName, "FAIL: Hover Text Expected Value ["+ExpectedValue +"], Does not Match Actual value ["+strHoverText+"]", true);
				setLastTestPassed(false);
			}
		    
		    action.moveToElement(Element).release();
	
	}catch (Exception excException)	{
		excException.printStackTrace();
		log(FAIL, "Verify hover text for " + strElementName, "Exception while executing seVerifyHoverText Function", true);
	}
		return blnReturnValue;
	}
	
	/**
	 * Function to select an option from a drop down using the index of the selection instead of text</br>
	 * 
	 * @param testObject Web Element of the drop down
	 * @param Index index of the option to select (starts with 0)
	 * @param strElementName String name of the element for use in log messages.DO NOT USE SPECIAL CHARACTERS
	 * 
	 * @return returns a boolean value of true if selected correctly and false if not
	 */
	public static boolean seSelectIndex(WebElement testObject, int Index, String strElementName) {
		boolean blnResult = false;
		Select dropDown = null;
		try {
			if (testObject != null) {
				dropDown = new Select(testObject);
				dropDown.selectByIndex(Index);

				String strSelectedtext = dropDown.getFirstSelectedOption().getText();
				List<WebElement> allOptions = dropDown.getOptions();
				String strOptionAtIndex = allOptions.get(Index).getText();

				if (strSelectedtext.equalsIgnoreCase(strOptionAtIndex)) {
					blnResult = true;
					log(PASS, "Select index " + Index + " from " + strElementName, "Drop down INDEX [" + Index + "] with VALUE [" + strOptionAtIndex + "] selected from " + strElementName, false);
				} else {
					blnResult = false;
					setLastTestPassed(false);
					log(FAIL, "Select index " + Index + " from " + strElementName, "Drop down INDEX [" + Index + "] with VALUE [" + strOptionAtIndex + "] not selected from " + strElementName + ". Actual selection is [" + strSelectedtext + "]", true);
				}
			} else {
				blnResult = false;
				setLastTestPassed(false);
				log(FAIL, "Select index " + Index + " from " + strElementName, "***Element not Found***");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "Select index " + Index + " from " + strElementName, "Exception occurred while performing seSelectIndex function");
		}

		return blnResult;
	}

	/**
	 * Function to entering data into test box when seSetText doesn't work </br>
	 * 
	 * @param testObject Web Element of the Test Box
	 * @param strText entered in Text Box
	 * @param strElementName String name of the element for use in log messages.DO NOT USE SPECIAL CHARACTERS
	 * 
	 * @return returns a boolean value of true if selected correctly and false if not
	 */
	public static boolean seSendKeys(WebElement testObject, String strText, String strElementName) {
		boolean blnResult = false;

		try {
			if (testObject != null) {
				
				testObject.sendKeys(strText);
				
				String strSelectedtext = testObject.getText();

				if (strSelectedtext.equalsIgnoreCase(strText)) {
					blnResult = true;
					log(PASS, "Enter Text into  " + strElementName, " Entered Text [" + strText + "] into " + strElementName, false);
				} else {
					blnResult = false;
					setLastTestPassed(false);
					log(FAIL, "Enter Text into  " + strElementName, " Fail: Entered Text [" + strText + "] into " + strElementName+". Actual Text in Field ["+ strSelectedtext +"]", false);
				}
			} else {
				blnResult = false;
				setLastTestPassed(false);
				log(FAIL, "Enter Text into  " + strElementName, "***Element not Found***");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "Enter Text into  " + strElementName, "Exception occurred while performing seSendKeys function");
		}

		return blnResult;
	}
	
	/**
	 * Function to entering data into text box when seSetText doesn't work for a Text Box that requires seGetAttribute("value")</br>
	 * 
	 * @param testObject
	 *            Web Element of the Test Box
	 * @param strText
	 *            entered in Text Box
	 * @param strElementName
	 *            String name of the element for use in log messages.DO NOT USE SPECIAL CHARACTERS
	 * 
	 * @return returns a boolean value of true if selected correctly and false if not
	 */
	public static boolean seSendKeysTextBox(WebElement testObject, String strText, String strElementName) {
		boolean blnResult = false;

		try {
			if (testObject != null) {
				
				testObject.click();
				testObject.sendKeys(Keys.chord(Keys.CONTROL, "a"), "");
				testObject.sendKeys(strText);

				String strSelectedtext = testObject.getAttribute("value");

				if (strSelectedtext.equalsIgnoreCase(strText)) {
					blnResult = true;
					log(PASS, "Enter Text into  " + strElementName, " Entered Text [" + strText + "] into " + strElementName, false);
				} else {
					blnResult = false;
					setLastTestPassed(false);
					log(FAIL, "Enter Text into  " + strElementName, " Fail: Entered Text [" + strText + "] into " + strElementName + ". Actual Text in Field [" + strSelectedtext + "]", false);
				}
			} else {
				blnResult = false;
				setLastTestPassed(false);
				log(FAIL, "Enter Text into  " + strElementName, "***Element not Found***");
			}

		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "Enter Text into  " + strElementName, "Exception occurred while performing seSendKeys function");
		}

		return blnResult;
	}
	
	/**
	 * Function to perform a click on an object using the Actions class </br>
	 * 
	 * @param testObject     Web Element object to click on
	 * @param strElementName String name of the element to be clicked for use in log messages.
	 * 
	 */
	public static void seClickUsingActions(WebElement testObject, String strElementName) {
		try {
			Actions action=new Actions(getWebDriver());
			action.moveToElement(testObject).click().build().perform();
			log(PASS,"Click on " + strElementName, "PASS: clicked on " + strElementName);
		} catch (Exception e) {
			log(FAIL, "Click on " + strElementName, "Failed to click on " + strElementName);
			setLastTestPassed(false);
			throw e;
		}
	}
	
	/**
	 * Function to select a value from a drop down when it has a tag type of Mat-Select </br>
	 * 
	 * @param testObject     Web Element object to click on
	 * @param strElementName String name of the element to be clicked for use in log messages.
	 * @param strSelectValue The value to select from the list/drop down
	 * 
	 */
	public static void seMatSelect(WebElement testObject, String strElementName, String strSelectValue) {
		try {
			seClick(testObject, strElementName);
			WebElement selectValue = getWebDriver().findElement(By.xpath("//mat-option/span[contains(text(), '" + strSelectValue + "')]"));
			seMoveToElement(selectValue, "Move to " + strSelectValue + " in the selection list");
			selectValue.click();
			seWaitForPageLoad();
			
			getWebDriver().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			String strSelectedValue = testObject.findElement(By.xpath(".//div[@class='mat-select-value']/span/span")).getText().trim();
			getWebDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
			if (strSelectedValue.equalsIgnoreCase(strSelectValue)) {
				log(PASS,"Select " + strSelectValue + " from " + strElementName,"PASS: " + strSelectValue + " successfully selected from " + strElementName);
			} else {
				log(FAIL,"Select " + strSelectValue + " from " + strElementName,"FAIL: failed to select " + strSelectValue + " from " + strElementName, true);
				setLastTestPassed(false);
			}
			
		} catch (org.openqa.selenium.NoSuchElementException excNoSuchElement) {
			if (strSelectValue.equalsIgnoreCase("None")) {
				log(PASS,"Select " + strSelectValue + " from " + strElementName,"PASS: " + strSelectValue + " successfully selected from " + strElementName);
			} else {
				log(FAIL, "Select " + strSelectValue + " from " + strElementName, "Exception Occurred during execution of function seMatSelect");
				setLastTestPassed(false);
				throw excNoSuchElement;
			}
		} catch (Exception e) {
			log(FAIL, "Select " + strSelectValue + " from " + strElementName, "Exception Occurred during execution of function seMatSelect");
			setLastTestPassed(false);
			throw e;
		}
	}
}


