package com.fusion.test.sanity;

import com.anthem.selenium.constants.BrowserConstants;
import com.anthem.selenium.utility.EnvHelper;
import org.testng.annotations.Test;
import com.fusion.pageobjects.page.ClaimEntryHeaderPage;
import com.fusion.pageobjects.page.LoginPage;
import com.fusion.utilities.CoreSuperHelper;
import com.fusion.utilities.FusionUtils;

import java.sql.ResultSet;

/*
'Revision History
'#############################################################################
'@rev.On	@rev.No		@rev.By				  @rev.Comments
'										
'#############################################################################
*/

/**
 * Manual test case: <EDI Intake  Capture Receipt State from Filename for claim type Professional and Dental>
 * @author :Bhagavathi Rao Kommuri (AG95926)
 * @since 05-OCT-2020
 * 
 * This Script will validate following test cases  TC-339,TC340,TC-341,TC-342. The script will execute based on claim 
 * number and verify rendering state in ClaimEntryHeaderPage ie Plan code. In the data sheet  plan code is mapped to 
 * rendering states based on scenarios and inputfile
 *
 */

public class captureReceiptStateFromFileNameHCFAandDSR_TS extends CoreSuperHelper {

	static String BASE_URL = EnvHelper.getValue("url");

	public static void aTAFInitParams() {
		MANUAL_TC_EXECUTION_EFFORT = "00:45:00";
	}

	@Test
	public void verifyReceiptState() {
		aTAFInitParams();
		initiateTestScript();
	}

	public static void executeScript() {
		
		setIgnoreLastTestFailure(true);
		try {
			if (getCellValue("Run_Flag").equalsIgnoreCase("Yes")) {
				setIgnoreLastTestFailure(true);
				String tcDescription = getCellValue("TEST_CASE_DESCRIPTION");
				logExtentReport(tcDescription);
				seOpenBrowser(BrowserConstants.Chrome, BASE_URL);
				String str837file=getCellValue("File_Name");
				String strDbPlanCode= getCellValue("Plan_Code");
				
				String strQuery = "select claimnumber from fepsit_pegadata.fhps_claims_work where inputfilename='"+str837file+"'";

				ResultSet rsClaimStatus = FusionUtils.seGetDatabaseResultset(strQuery);

				// To Retrieve the Data from ResultSet
				rsClaimStatus.absolute(1);// For Row 1
				String strClaimno=rsClaimStatus.getString("claimnumber");
				//Login into Application
				LoginPage.get().loginPegaApplication();
				//Switch the Roles
				FusionUtils.seSwitchClaimsExaminerRole();
				//Search the Claim in Pega
				FusionUtils.seSearchClaimWithClaimNumberAndClcikOnClaim(strClaimno);
				seMoveToElement(ClaimEntryHeaderPage.get().planCodeField,"Plan Code");
				// To Compare the Data from Source to target
				seVerifyFieldValue(ClaimEntryHeaderPage.get().planCodeField,strDbPlanCode, "Plan Code");
				
				
			};
		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "An Exception Occured While Running Test Script", e.getLocalizedMessage(), false);
			setLastTestPassed(false);
		} finally {
			seCloseBrowser();
		}
	}
}
