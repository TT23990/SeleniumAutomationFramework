package com.fusion.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.anthem.selenium.constants.DateTimeConstants;
import com.anthem.selenium.utility.EnvHelper;
import com.anthem.selenium.utils.DateTimeUtils;

public class FEPUtils extends CoreSuperHelper {

	/**
	 * If there are multiple windows displayed, function will points to the latest window
	 * 
	 * @return WebDriver return driver object which is pointing the latest window
	 * 
	 * @author TP&E
	 */
	public static WebDriver seSwitchToNewWinow() {
		try {
			Set<String> setWindowHandles = getWebDriver().getWindowHandles();

			for (String strWindowHandle : setWindowHandles) {

				getWebDriver().switchTo().window(strWindowHandle);

				log(PASS, "Window is switched", "Window is switched successfully. Window handle is " + strWindowHandle, false);
			}

		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "Window is not switched", "Window is not switched successfully", false);
		}
		return getWebDriver();

	}

	/**
	 * Function is to verify Error Message Text
	 * 
	 * @author Gautam Kumar (AF15254)
	 * @param errorElement  object whose error text need to be verified
	 * @param expectedValue Expected error message
	 * @return boolean if error verification is successful or not
	 */
	public static boolean verifyFieldError(WebElement errorElement, String strFieldName, String strExpectedValue) {
		boolean blnIsErrorVerificationSuccessful = false;

		try {

			String strErrorMessage = seGetElementValue(errorElement);

			if (strErrorMessage.equalsIgnoreCase(strExpectedValue)) {
				log(PASS, strFieldName + "s error message verification",
						strFieldName + "s error message verification successfull. Expected error message: [" + strExpectedValue + "] Actual error message: [" + strErrorMessage + "]", true);
				blnIsErrorVerificationSuccessful = true;
			} else {
				setLastTestPassed(false);
				log(FAIL, strFieldName + "s error message verification",
						strFieldName + "s error message verification unsuccessfull. Expected error message: [" + strExpectedValue + "] Actual error message: [" + strErrorMessage + "]", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setLastTestPassed(false);
			log(FAIL, strFieldName + "s error message verification", "Error Message is not verified successfully. Exception occurred.", true);
		}

		return blnIsErrorVerificationSuccessful;
	}

	/**
	 * Function is to verify label's Text
	 * 
	 * @Author Gautam Kumar (AF15254)
	 * @param label         label object whose text need to be verified
	 * @param expectedValue Expected value of label's text
	 * @return boolean Is verification successful or not
	 */
	public static boolean verifyLabelText(WebElement label, String strLabelName, String strExpectedValue) {

		boolean blnIsVerificationSuccessful = false;

		try {
			String strAtualValue = seGetElementValue(label);

			if (strAtualValue.equalsIgnoreCase(strExpectedValue)) {
				log(PASS, strLabelName + "s label text verification", strLabelName + "s label text is verified. Expected value: [" + strExpectedValue + "] Actual value: [" + strAtualValue + "]",
						true);
				blnIsVerificationSuccessful = true;
			} else {
				setLastTestPassed(false);
				log(FAIL, strLabelName + "s label text verification",
						"Expected and actual values of " + strLabelName + " label do not match. Expected value: [" + strExpectedValue + "] Actual value: [" + strAtualValue + "]", true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setLastTestPassed(false);
			log(FAIL, strLabelName + "s label text verification", "Label Text is not verified sucessfully. Exception occurred.", true);
		}

		return blnIsVerificationSuccessful;
	}

	/**
	 * Function to verify expected and actual boolean values and report the same
	 * 
	 * @param blnActualResult          Boolean value which is an Outcome of a evaluation. This value is expected to be true
	 * @param strFinalVerificationStep Step
	 * @param strFinalVerificationPass What to report if the step passes
	 * @param strFinalVerificationFail What to report is the step fails
	 * @param blnCaptureScreen
	 * @return boolean if assert was successful or not
	 * 
	 * @author af37512 Santosh Bukkashetti
	 * @since 22-August-2017
	 */
	public static boolean assertTrueAndReport(boolean blnActualResult, String strFinalVerificationStep, String strFinalVerificationPass, String strFinalVerificationFail, boolean blnCaptureScreen) {

		if (blnActualResult) {
			log(PASS, strFinalVerificationStep, strFinalVerificationPass, blnCaptureScreen);
			return true;
		} else {
			log(FAIL, strFinalVerificationStep, strFinalVerificationFail, blnCaptureScreen);
			setLastTestPassed(false);
			return false;
		}
	}

	/**
	 * Function to verify expected and actual boolean values and report the same
	 * 
	 * @param blnActualResult          Boolean value which is an Outcome of a evaluation. This value is expected to be false
	 * @param strFinalVerificationStep Step
	 * @param strFinalVerificationPass What to report if the step passes
	 * @param strFinalVerificationFail What to report is the step fails
	 * @param blnCaptureScreen
	 * @return boolean if assert was successful or not
	 * 
	 * @author af37512 Santosh Bukkashetti
	 * @since 22-August-2017
	 */
	public static boolean assertFalseAndReport(boolean blnActualResult, String strFinalVerificationStep, String strFinalVerificationPass, String strFinalVerificationFail, boolean blnCaptureScreen) {

		if (!blnActualResult) {
			log(PASS, strFinalVerificationStep, strFinalVerificationPass, blnCaptureScreen);
			return true;
		} else {
			log(FAIL, strFinalVerificationStep, strFinalVerificationFail, blnCaptureScreen);
			setLastTestPassed(false);
			return false;
		}
	}

	/**
	 * Function to return the user name as it appears in the PACT application based on who is logged into the machine
	 * 
	 * @return String of the users name that is logged into the machine in the PACT format
	 * 
	 * @author AD20221 Algie Watts
	 * @since 9/6/2017
	 */
	public static String getUserNamePACT() {
		try {
			String strReturnValue = null;
			String strUserID = getUserHome();

			String[][] strArrUsers = { { "AD20221", "ALGIE R. WATTS" }, { "HARLETR", "TREVOR HARLESS" }, { "AE05811", "SANDEEP CHATLA MALLEMKONDA" }, { "AC15525", "BRIAN MEEK" },
					{ "AD29556", "RAVI SWAMY" }, { "AC81546", "SHWETHA SOMISETTY" }, { "AF33811", "LACHIREDDY PALLA" }, };

			for (int i = 0; i < strArrUsers.length; i++) {
				if (strArrUsers[i][0].equalsIgnoreCase(strUserID)) {
					strReturnValue = strArrUsers[i][1];
				}
			}
			if (strReturnValue.equals(null)) {
				log(FAIL, "Get PACT User Name", "Fail - User " + strUserID + " Not included in list in getUserNamePACT function", false);
			}

			return strReturnValue;
		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "Get PACT User Name", "Fail - exception thrown in getUserNamePACT function", false);
			return null;
		}
	}

	/**
	 * Function to return the user name as it appears in the BlueLINK application based on who is logged into the machine
	 * 
	 * @return String of the users name that is logged into the machine in the BlueLINK format
	 * 
	 * @author AD20221 Algie Watts
	 * @since 9/6/2017
	 */
	public static String getUserNameBlueLINK() {
		try {
			String strReturnValue = null;

			String strUserID = getUserHome();

			String[][] strArrUsers = { { "AD20221", "Watts, Algie R." }, { "HARLETR", "Harless, Trevor" }, { "AE05811", "Chatla Mallemkonda, Sandeep" }, { "AC15525", "Meek, Brian" },
					{ "AD29556", "Swamy, Ravi" }, { "AC81546", "Somisetty, Shwetha" }, { "AF33811", "Palla, Lachireddy" }, };

			for (int i = 0; i < strArrUsers.length; i++) {
				if (strArrUsers[i][0].equalsIgnoreCase(strUserID)) {
					strReturnValue = strArrUsers[i][1];
				}
			}
			if (strReturnValue.equals(null)) {
				log(FAIL, "Get PBlueLINKACT User Name", "Fail - User " + strUserID + " Not included in list in getUserNameBlueLINK function", false);
			}

			return strReturnValue;
		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "Get BlueLINK User Name", "Fail - exception thrown in getUserNameBlueLINK function", false);
			return null;
		}
	}

	/**
	 * Function to return the user name as it appears in the FIAS application based on who is logged into the machine
	 * 
	 * @return String of the users name that is logged into the machine in the FIAS format
	 * 
	 * @author AD20221 Algie Watts
	 * @since 9/6/2017
	 */
	public static String getUserNameFIAS() {
		try {
			String strReturnValue = null;

			String strUserID = getUserHome();

			String[][] strArrUsers = { { "AD20221", "Algie Watts" }, { "HARLETR", "Trevor Harless" }, { "AE05811", "Sandeep Chatlamallemkonda" }, { "AC15525", "Brian Meek" },
					{ "AD29556", "Ravi Swamy" }, { "AC81546", "Shwetha Somisetty" }, { "AF33811", "Lachireddy Palla" }, };

			for (int i = 0; i < strArrUsers.length; i++) {
				if (strArrUsers[i][0].equalsIgnoreCase(strUserID)) {
					strReturnValue = strArrUsers[i][1];
				}
			}
			if (strReturnValue.equals(null)) {
				log(FAIL, "Get FIAS User Name", "Fail - User " + strUserID + " Not included in list in getUserNameFIAS function", false);
			}

			return strReturnValue;
		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "Get FIAS User Name", "Fail - exception thrown in getUserNameFIAS function", false);
			return null;
		}
	}

	/**
	 * Function to return the user name as it appears in the RTSR application based on who is logged into the machine
	 * 
	 * @return String of the users name that is logged into the machine in the BlueLINK format
	 * 
	 * @author AD20221 Algie Watts
	 * @since 9/6/2017
	 */
	public static String getUserNameRTSR() {
		try {
			String strReturnValue = null;

			String strUserID = getUserHome();

			String[][] strArrUsers = { { "AD20221", "Watts, Algie R." }, { "HARLETR", "Harless, Trevor" }, { "AE05811", "Chatla Mallemkonda, Sandeep" }, { "AC15525", "Meek, Brian" },
					{ "AD29556", "Swamy, Ravi" }, { "AC81546", "Somisetty, Shwetha" }, { "AF33811", "Palla, Lachireddy" }, };

			for (int i = 0; i < strArrUsers.length; i++) {
				if (strArrUsers[i][0].equalsIgnoreCase(strUserID)) {
					strReturnValue = strArrUsers[i][1];
				}
			}
			if (strReturnValue.equals(null)) {
				log(FAIL, "Get PBlueLINKACT User Name", "Fail - User " + strUserID + " Not included in list in getUserNameBlueLINK function", false);
			}

			return strReturnValue;
		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "Get BlueLINK User Name", "Fail - exception thrown in getUserNameBlueLINK function", false);
			return null;
		}
	}

	/**
	 * Function to return the user name as it appears in the Daily Drawdown application based on who is logged into the machine
	 * 
	 * @return String of the users name that is logged into the machine in the BlueLINK format
	 * 
	 * @author AD20221 Algie Watts
	 * @since 8/9/2017
	 */
	public static String getUserNameDRDWN() {
		try {
			String strReturnValue = null;

			String strUserID = getUserHome();

			String[][] strArrUsers = { { "AD20221", "Watts, Algie" }, { "HARLETR", "Harless, Trevor" }, { "AE05811", "Chatla Mallemkonda, Sandeep" }, { "AC15525", "Meek, Brian" },
					{ "AD29556", "Swamy, Ravi" }, { "AC81546", "Somisetty, Shwetha" }, { "AF33811", "Palla, Lachireddy" }, };

			for (int i = 0; i < strArrUsers.length; i++) {
				if (strArrUsers[i][0].equalsIgnoreCase(strUserID)) {
					strReturnValue = strArrUsers[i][1];
				}
			}
			if (strReturnValue.equals(null)) {
				log(FAIL, "Get PBlueLINKACT User Name", "Fail - User " + strUserID + " Not included in list in getUserNameBlueLINK function", false);
			}

			return strReturnValue;
		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "Get BlueLINK User Name", "Fail - exception thrown in getUserNameBlueLINK function", false);
			return null;
		}
	}

	/**
	 * Function to return the user name as it appears in the application based on who is logged into the machine
	 * 
	 * @return String of the users name that is logged into the machine based on the application
	 * 
	 * @author AD20221 Algie Watts
	 * @since 9/6/2017
	 */
	public static String getUserName() {
		try {
			String strReturnValue = null;

			String strApplication = EnvHelper.getValue("jira.application.name");

			switch (strApplication) {
			case "FEP - PIP Audit Coordination Tool":
				strReturnValue = getUserNamePACT();
				break;
			case "FEP - BlueLink":
				strReturnValue = getUserNameBlueLINK();
				break;
			case "FEP - FEP Internet Audit System":
				strReturnValue = getUserNameFIAS();
				break;
			case "FEP - Realtime Streamline Reporting":
				strReturnValue = getUserNameRTSR();
				break;
			case "FEP - Daily Drawdown":
				strReturnValue = getUserNameDRDWN();
				break;

			default:
				String strUserID = getUserHome();

				String[][] strArrUsers = { { "AD20221", "Algie Watts" }, { "HARLETR", "Trevor Harless" }, { "AE05811", "Sandeep Chatla Mallemkonda" }, { "AC15525", "Brian Meek" },
						{ "AD29556", "Ravi Swamy" }, { "AC81546", "Shwetha Somisetty" }, { "AF33811", "Lachireddy Palla" } };

				for (int i = 0; i < strArrUsers.length; i++) {
					if (strArrUsers[i][0].equalsIgnoreCase(strUserID)) {
						strReturnValue = strArrUsers[i][1];
					}
				} // End For loop
				if (strReturnValue.equals(null)) {
					log(FAIL, "Get User Name", "Fail - User " + strUserID + " Not included in list in getUserName function", false);
				}
				break;
			} // End Switch

			return strReturnValue;
		} catch (Exception e) {
			log(FAIL, "Get User Name Function", "Fail - exception thrown in getUserName function", false);
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Function to return the US domain ID of the user based on who is logged into the machine
	 * 
	 * @return String of the US domain ID of the user that is logged into the machine in the PACT format
	 * 
	 * @author AD20221 Algie Watts
	 * @since 9/6/2017
	 */
	public static String getUserHome() {
		try {
			String strUserName = System.getProperty("user.home");

			String[] strArrUserName = strUserName.split("\\\\");

			String strReturnValue = strArrUserName[strArrUserName.length - 1];

			return strReturnValue;
		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "Get User Home String", "Fail - exception thrown in getUserHome function", false);
			return null;
		}
	}

	/**
	 * Function to get the value from the string with "=" sign
	 * 
	 *
	 * @author AF33811 Lachireddy Palla
	 * @since 9/5/2017
	 */
	@SuppressWarnings("finally")
	public static String seGetValueFromString(WebElement testObject, String strFieldName) {

		String strValue = null;

		try {

			String strObject = seGetElementValue(testObject);

			strValue = strObject.substring(strObject.indexOf('=') + 1).trim();

		} catch (Exception excException) {
			// processExceptions("Exception occurred while fetching value from
			// the Selected String", excException);
			throw excException;
		} finally {
			return strValue;
		}

	}

	/**
	 * Function to verify the total number of field is equal to the Individual sub Fields
	 * 
	 * 
	 * @author AF33811 Lachireddy Palla
	 * @since 9/5/2017
	 */

	public static boolean seCompareTotalWithIndividualCount(WebElement testObjectTotal, String strTotalFieldName, WebElement... WebElements) {

		String strExpectedValue = seGetValueFromString(testObjectTotal, strTotalFieldName);
		String WebElement1 = null;
		int intTotal = 0;
		@SuppressWarnings("unused")
		int arr[];

		HashMap<String, Integer> mapValue = new HashMap<String, Integer>();
		try {

			if (null != WebElements) {
				arr = new int[WebElements.length];
				for (WebElement webEl : WebElements) {
					String WebElement = seGetElementValue(webEl);

					Thread.sleep(5000);
					WebElement1 = WebElement.substring(WebElement.indexOf('=') + 1).trim();
					mapValue.put(WebElement.substring(0, WebElement.indexOf('=')), Integer.parseInt(WebElement1));
					intTotal = intTotal + Integer.parseInt(WebElement1);
				}

				Thread.sleep(5000);

				if (String.valueOf(intTotal).equalsIgnoreCase(strExpectedValue))

				{
					log(PASS, "Compare Total Count with Individual Counts",
							"Pass: TotalNumber of " + strExpectedValue + ", Individual values of " + mapValue + " Total Count matched with Individual Counts", true);

					return true;

				} else {

					// Fail if the Total Count is not matched with Individual
					// Counts

					log(FAIL, "Compare Total Count with Individual Counts",
							"Fail: TotalNumber of " + strExpectedValue + ", Individual values of " + mapValue + " Total Count in Not equal with Individual Counts", true);

					setLastTestPassed(false);

					return false;

				}

			}

		} catch (Exception excException) {

			log(FAIL, "Default function seCompareTotalWithIndividualCount", "Exception while executing seCompareTotalWithIndividualCount function", false);

		}
		return false;

	}

	/**
	 * Function is to verify the Previous value and present value using the relational operators like "<, > and ==" signs
	 * 
	 * 
	 * @author AF33811 Lachireddy Palla
	 * @since 9/11/2017
	 */

	@SuppressWarnings("finally")
	public static boolean SeCompareValues(String previousValue, String relationaloperator, String testobject) {

		int previousValue1 = Integer.parseInt(previousValue);
		int newValue1 = Integer.parseInt(testobject);
		boolean flag = false;

		String strOperator = null;

		try {
			switch (relationaloperator) {
			case ">":
				strOperator = "Greater Than(>)";
				if (previousValue1 > newValue1) {
					flag = true;
				}
				break;
			case "<":
				strOperator = "Less Than(<)";
				if (previousValue1 < newValue1) {
					flag = true;
				}
				break;
			case "==":
				strOperator = "Equal To(==)";
				if (previousValue1 == newValue1) {
					flag = true;
				}
				break;
			default:
				log(ERROR, "Compare two values", "Error: Selected Relational Operator:  " + relationaloperator + "  is invalid, Valid Relational Operators for this function are:  > , < , == ");
				return flag;

			}
			if (flag == true) {
				log(PASS, "Compare two values", "Pass: Verified Previous Value: " + previousValue1 + " is " + strOperator + " New Value: " + newValue1);
			} else if (flag == false) {
				log(FAIL, "Compare two values", "Fail: Previous Value:  " + previousValue1 + " is NOT " + strOperator + " New Value: " + newValue1);
				setLastTestPassed(false);
			}
		} catch (Exception excException) {
			excException.printStackTrace();
			throw excException;
		} finally {
			return flag;
		}
	}

	/**
	 * Function is to get the value from the Link of the string, mainly this function will get the value from the last parenthesis of the string
	 * 
	 * 
	 * @author AF33811 Lachireddy Palla
	 * @since 9/11/2017
	 */

	@SuppressWarnings("finally")
	public static String seGetValueFromLinkParanthesis(WebElement testObject, String strFieldName) {

		String strValue = null;

		try {
			String strObject = seGetElementValue(testObject);
			strValue = strObject.substring(strObject.lastIndexOf("(") + 1).replace(")", "").trim();

		} catch (Exception excException) {
			// processExceptions("Exception occurred while fetching vale from
			// the Selected String", excException);
			throw excException;
		} finally {
			return strValue;
		}
	}

	public static boolean handleMultipleWindows(WebDriver Driver) throws InterruptedException {
		Thread.sleep(4000);
		String currentWindow = Driver.getWindowHandle(); // Storing parent window reference into a String Variable
		System.out.println(" Check title " + Driver.getCurrentUrl() + Driver.getWindowHandles().size());
		for (String popUpHandle : Driver.getWindowHandles()) {
			if (popUpHandle.equalsIgnoreCase(currentWindow))
				continue;
			Driver.switchTo().window(popUpHandle);
			String sTitle = Driver.getCurrentUrl();
			System.out.println(sTitle);
		}
		return false;
	}

	public static String seGetStateCode(String stateName) {

		Map<String, String> states = new HashMap<String, String>();

		states.put("alabama", "AL");
		states.put("alaska", "AK");
		states.put("arizona", "AZ");
		states.put("arkansas", "AR");
		states.put("british columbia", "BC");
		states.put("california", "CA");
		states.put("colorado", "CO");
		states.put("connecticut", "CT");
		states.put("delaware", "DE");
		states.put("district of columbia", "DC");
		states.put("florida", "FL");
		states.put("georgia", "GA");
		states.put("hawaii", "HI");
		states.put("idaho", "ID");
		states.put("illinois", "IL");
		states.put("indiana", "IN");
		states.put("iowa", "IA");
		states.put("kansas", "KS");
		states.put("kentucky", "KY");
		states.put("louisiana", "LA");
		states.put("maine", "ME");
		states.put("maryland", "MD");
		states.put("massachusetts", "MA");
		states.put("michigan", "MI");
		states.put("minnesota", "MN");
		states.put("mississippi", "MS");
		states.put("missouri", "MO");
		states.put("nebraska", "NE");
		states.put("nevada", "NV");
		states.put("new hampshire", "NH");
		states.put("new jersey", "NJ");
		states.put("new mexico", "NM");
		states.put("new york", "NY");
		states.put("north carolina", "NC");
		states.put("north dakota", "ND");
		states.put("ohio", "OH");
		states.put("oklahoma", "OK");
		states.put("oregon", "OR");
		states.put("pennsylvania", "PA");
		states.put("puerto rico", "PR");
		states.put("rhode island", "RI");
		states.put("south carolina", "SC");
		states.put("south dakota", "SD");
		states.put("tennessee", "TN");
		states.put("texas", "TX");
		states.put("utah", "UT");
		states.put("vermont", "VT");
		states.put("virgin islands", "VI");
		states.put("virginia", "VA");
		states.put("washington", "WA");
		states.put("west virginia", "WV");
		states.put("wisconsin", "WI");
		states.put("wyoming", "WY");

		return states.get(stateName.toLowerCase());

	}

	public static String seGetStateName(String StateCode) {

		Map<String, String> states = new HashMap<String, String>();

		states.put("AL", "Alabama");
		states.put("AK", "Alaska");
		states.put("AZ", "Arizona");
		states.put("AR", "Arkansas");
		states.put("BC", "British Columbia");
		states.put("CA", "California");
		states.put("CO", "Colorado");
		states.put("CT", "Connecticut");
		states.put("DE", "Delaware");
		states.put("DC", "District Of Columbia");
		states.put("FL", "Florida");
		states.put("GA", "Georgia");
		states.put("HI", "Hawaii");
		states.put("ID", "Idaho");
		states.put("IL", "Illinois");
		states.put("IN", "Indiana");
		states.put("IA", "Iowa");
		states.put("KS", "Kansas");
		states.put("KY", "Kentucky");
		states.put("LA", "Louisiana");
		states.put("ME", "Maine");
		states.put("MD", "Maryland");
		states.put("MA", "Massachusetts");
		states.put("MI", "Michigan");
		states.put("MN", "Minnesota");
		states.put("MS", "Mississippi");
		states.put("MO", "Missouri");
		states.put("NE", "Nebraska");
		states.put("NV", "Nevada");
		states.put("NH", "New Hampshire");
		states.put("NJ", "New Jersey");
		states.put("NM", "New Mexico");
		states.put("NY", "New York");
		states.put("NC", "North Carolina");
		states.put("ND", "North Dakota");
		states.put("OH", "Ohio");
		states.put("OK", "Oklahoma");
		states.put("OR", "Oregon");
		states.put("PA", "Pennsylvania");
		states.put("PR", "Puerto Rico");
		states.put("RI", "Rhode Island");
		states.put("SC", "South Carolina");
		states.put("SD", "South Dakota");
		states.put("TN", "Tennessee");
		states.put("TX", "Texas");
		states.put("UT", "Utah");
		states.put("VT", "Vermont");
		states.put("VI", "Virgin Islands");
		states.put("VA", "Virginia");
		states.put("WA", "Washington");
		states.put("WV", "West Virginia");
		states.put("WI", "Wisconsin");
		states.put("WY", "Wyoming");

		return states.get(StateCode.toUpperCase());
	}

	/**
	 * Function is to log the Build Version, Build Date and Server Name for the current software build
	 * 
	 * @param testObject WebElement to pull the build data from (Expects 3 separate lines. Build Version, Build Date and Server Name)
	 * 
	 * @author AD20221 Algie Watts
	 * @since 12/4/2018
	 */

	public static void logBuildData(WebElement testObject) {

		try {

			if (testObject.isDisplayed()) {

				log(PASS, "Build Version", getBuildVersion(testObject), false);
				log(PASS, "Build Date", getBuildDate(testObject), false);
				log(PASS, "Server Name", getServerName(testObject), false);
			} else {
				setLastTestPassed(false);
				log(FAIL, "Log Build Data", "Build Data is NOt displayed in the current build", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "Log Build Data", "An Exception occurred while running logBuildData function", false);
		}
	}

	/**
	 * Function is to get the Build Version for the current software build
	 * 
	 * @param testObject WebElement to pull the Build Version from (Expects 3 separate lines. Build Version, Build Date and Server Name)
	 * 
	 * @author AD20221 Algie Watts
	 * @since 12/4/2018
	 */
	public static String getBuildVersion(WebElement testObject) {

		String strReturnValue = "";

		try {

			if (testObject.isDisplayed()) {
				String strVersion = testObject.getText();
				String[] strVersionSplit = strVersion.split(": ");

				strReturnValue = strVersionSplit[1].replace("Build Date", "").trim();
			} else {
				setLastTestPassed(false);
				log(FAIL, "Get Build Version", "Build Data is NOt displayed in the current build", false);
			}
		} catch (Exception e) {
			setLastTestPassed(false);
			e.printStackTrace();
			log(FAIL, "Get Build Version", "An Exception occurred while running getBuildVersion function", false);
		}

		return strReturnValue;
	}

	/**
	 * Function is to get the Build Date for the current software build
	 * 
	 * @param testObject WebElement to pull the Build Date from (Expects 3 separate lines. Build Version, Build Date and Server Name)
	 * 
	 * @author AD20221 Algie Watts
	 * @since 12/4/2018
	 */
	public static String getBuildDate(WebElement testObject) {

		String strReturnValue = "";

		try {

			if (testObject.isDisplayed()) {
				String strVersion = testObject.getText();
				String[] strVersionSplit = strVersion.split(": ");

				strReturnValue = strVersionSplit[2].replace("Server Name", "").trim();
			} else {
				setLastTestPassed(false);
				log(FAIL, "Get Build Date", "Build Data is NOt displayed in the current build", false);
			}
		} catch (Exception e) {
			setLastTestPassed(false);
			e.printStackTrace();
			log(FAIL, "Get Build Date", "An Exception occurred while running getBuildDate function", false);
		}

		return strReturnValue;
	}

	/**
	 * Function is to get the Server Name for the current software build
	 * 
	 * @param testObject WebElement to pull the Server Name from (Expects 3 separate lines. Build Version, Build Date and Server Name)
	 * 
	 * @author AD20221 Algie Watts
	 * @since 12/4/2018
	 */
	public static String getServerName(WebElement testObject) {

		String strReturnValue = "";

		try {

			if (testObject.isDisplayed()) {
				String strVersion = testObject.getText();
				String[] strVersionSplit = strVersion.split(": ");

				strReturnValue = strVersionSplit[3].trim();
			} else {
				setLastTestPassed(false);
				log(FAIL, "Get Server Name", "Build Data is NOt displayed in the current build", false);
			}
		} catch (Exception e) {
			setLastTestPassed(false);
			e.printStackTrace();
			log(FAIL, "Get Server Name", "An Exception occurred while running getServerName function", false);
		}

		return strReturnValue;
	}

	/**
	 * Function is used to click on the drop down value in a Salesforce application since it is dynamic
	 * 
	 * @param strDropDownValue String value of the drop down value to select. This is the value stored in the title attribute for the drop down selection.
	 * 
	 * @author AD20221 Algie Watts
	 * @since 10/31/2019
	 */
	public static boolean selectSalesforceDropdownValue(String strDropDownValue) {
		boolean blnReturnValue = false;
		try {
			getWebDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			WebElement element = getWebDriver().findElement(By.xpath("//div[contains(@class, 'uiMenuList--short visible positioned')]//li/a[@title='" + strDropDownValue + "']"));
			getWebDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			seScrollIntoView(element, "Scroll till " + element + " is on the screen");
			blnReturnValue = seClick(element, "Clicked element in the dropdown with title attribute set to [" + strDropDownValue + "].");
		} catch (org.openqa.selenium.NoSuchElementException excNoSuchElement) {
			log(FAIL, "Select Salesforce Dropdown Value", "FAIL: Element does not exist.  Please verify element name in title attribute is correct", false);
			throw excNoSuchElement;
		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "An Exception occurred while running selectSalesforceDropdownValue function", e.getLocalizedMessage(), false);
			throw e;
		}
		return blnReturnValue;
	}

	public static String getServiceValue(JSONObject jobj, String element) {
		String value = "";
		try {
			value = jobj.getString(element);
		} catch (Exception e) {
			return value;
		}
		return value;
	}

	public static int seResultSetCount(ResultSet resultSet) throws SQLException {
		try {
			int i = 0;
			while (resultSet.next()) {
				i++;
			}
			return i;
		} catch (Exception e) {
			log(FAIL, "An Exception occurred while running selectSalesforceDropdownValue function", e.getLocalizedMessage(), false);
			e.printStackTrace();
		}
		return 0;
	}
	
	public static String getRandomDateFromCurrentDate(int numOfDays) {
        String formattedDate = "";
        try {
              Date curDate = DateTimeUtils.getCurrentDate();
              Date endDate = DateTimeUtils.getIncrOrDecrDate(numOfDays, DateTimeConstants.DAYS);
              long random=0;
              if(numOfDays>=0) {
                    random = ThreadLocalRandom.current().nextLong( curDate.getTime(),endDate.getTime());
              }else {
                    random = ThreadLocalRandom.current().nextLong( endDate.getTime(),curDate.getTime());
              }
              formattedDate = DateTimeUtils.getDate(DateTimeUtils.DATE_FORMAT_DEFAULT, new Date(random));
              System.out.println("for:"+formattedDate);
        } catch (Exception e) {
              e.getLocalizedMessage();
        }
        return formattedDate;
  }
	
	public static String getDateFromCurrentDate(int numOfDays) {
        String formattedDate = "";
        long random ;
        try {
              Date curDate = DateTimeUtils.getCurrentDate();
              Date endDate = DateTimeUtils.getIncrOrDecrDate(numOfDays, DateTimeConstants.DAYS);
              long SltDate=0;
              if(numOfDays>=0) {
            	  SltDate = ThreadLocalRandom.current().nextLong( curDate.getTime(),endDate.getTime());
            	  
            	  random = ThreadLocalRandom.current().nextLong( curDate.getTime(),endDate.getTime());
                  
              }else {
            	  random = ThreadLocalRandom.current().nextLong( endDate.getTime(),curDate.getTime());
              
              }
              formattedDate = DateTimeUtils.getDate(DateTimeUtils.DATE_FORMAT_DEFAULT, new Date(random));
              System.out.println("for:"+formattedDate);
        } catch (Exception e) {
              e.getLocalizedMessage();
        }
        return formattedDate;
  }
	
	/**
	 * Function is used to convert the date returned from a database to a string value for comparison to a gui table
	 * 
	 * @param strDateString String value of the date passed form the database
	 * @param strDateFormatString
	 * 
	 * @author AD20221 Algie Watts
	 * @since 9/9/2020
	 */
	public static String getConvertedDataBaseDate(String strDateString, String strDateFormatString) {
		String strReturnValue = "";
		
		if (strDateString.length()>0) {
			try {
				Date date = new SimpleDateFormat("yyyy-M-dd hh:mm:ss.S").parse(strDateString);
				strReturnValue = new SimpleDateFormat(strDateFormatString).format(date);
			} catch (ParseException e) {
				e.printStackTrace();
				setLastTestPassed(false);
				log(FAIL, "An Exception occurred while running getConvertedDataBaseDate function", e.getLocalizedMessage(), false);
			}
		}
		
		return strReturnValue;
	}
	
	/**
	 * Compares two strings buy only logs if there is a failure
	 * </p>
	 * 
	 * @param sourceString-
	 *            String to compare to target
	 * @param targetString
	 *            String used to compare target string with
     * @param operator
     * 			  Whether the strings should be equal or less than or greater than.
	 * 
	 * @author Algie Watts
	 * @since 9-September-2020
	 */
	public static boolean seCompareStringsNoLog(String sourceString, String targetString, String operator) {
		boolean blnReturnValue = false;
		
		switch (operator.toLowerCase()){
		case "=": case "equals": case "equal":
			if (sourceString.compareTo(targetString) == 0) {
				blnReturnValue = true;
			} else {
				setLastTestPassed(false);
				log(FAIL, "Compare Strings Without entering a log message", "FAIL: " + sourceString + " does not equal " + targetString);
			}
			break;
		case ">": case "greater than": case "after":
			if (sourceString.compareTo(targetString) > 0) {
				blnReturnValue = true;
			} else {
				setLastTestPassed(false);
				log(FAIL, "Compare Strings Without entering a log message", "FAIL: " + sourceString + " is not greater than or after " + targetString);
			}
			break;
		case "<": case "less than": case"before":
			if (sourceString.compareTo(targetString) < 0) {
				blnReturnValue = true;
			} else {
				setLastTestPassed(false);
				log(FAIL, "Compare Strings Without entering a log message", "FAIL: " + sourceString + " is not less than or before " + targetString);
			}
			break;
		case "!=": case "not equal":
			if (sourceString.compareTo(targetString) == 0) {
				setLastTestPassed(false);
				log(FAIL, "Compare Strings Without entering a log message", "FAIL: " + sourceString + " is equal to " + targetString);
			} else {
				blnReturnValue = true;
			}
			break;
		default:
			log(FAIL,"Compare Strings Without entering a log message", "FAIL: invalid value entered for operator.  Please enter one opf the following: =, equals, equal, >, greater than, after, < less than, before, !=, not equal.");
		}
		return blnReturnValue;
	}
}