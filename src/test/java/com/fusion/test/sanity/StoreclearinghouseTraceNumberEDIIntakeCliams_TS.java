package com.fusion.test.sanity;

import com.anthem.selenium.constants.BrowserConstants;
import com.anthem.selenium.utility.EnvHelper;
import org.testng.annotations.Test;
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
 * Manual test case: <Test the Store Clearinghouse Trace Number for EDI Intake Claims >
 * <p>
 * Test script template
 * <p>
 * This Test script will cover the below testcases . 
 * TC-343,TC-344,TC-345
 * The Script will validate the valueaddednetworktraceid,DCN,ECN in the FHPS_H837Transactions table,
 * FHPS_claims_COntrol table, FHPS_claims_Work table based on input 837 files. Also the script will
 * search the claims in the pega application for types Dental,facility and profession for 14 states.
 * Please refer the EDIintake claims excel for data details.
 * 
 * @author Bhagavathi Rao Kommuri (AG95926)
 * @since 02-Nov-2020
 *
 */

public class StoreclearinghouseTraceNumberEDIIntakeCliams_TS extends CoreSuperHelper {

	static String BASE_URL = EnvHelper.getValue("url");

	public static void aTAFInitParams() {
		MANUAL_TC_EXECUTION_EFFORT = "00:35:00";
	}

	@Test
	public void verifyStoreClearinghouseTraceNumber() {
		aTAFInitParams();
		initiateTestScript();
	}

	public static void executeScript() {

		setIgnoreLastTestFailure(true);
		try {
			if (getCellValue("Run_Flag").equalsIgnoreCase("Yes")) {
				logExtentReport(getCellValue("TEST_CASE_DESCRIPTION"));
				seOpenBrowser(BrowserConstants.Chrome, BASE_URL);

				String strDcn = getCellValue("HCTRansactions_DCN");
				String strStatus = getCellValue("X12_Status");
				String str837file = getCellValue("File_Name");
				String strCliamstype = getCellValue("Claim_Type");
				String strClaimControlECN = getCellValue("ClaimsControl_ECN");
				String strClaimControlDCN = getCellValue("ClaimControl_DCN");
				String strClaimWorkDCN = getCellValue("ClaimWork_DCN");

				String strQuery = "select a.filename,a.x12claimstatus,a.valueaddednetworktraceid,a.claimtype,b.intake_filename,b.ecn,b.dcn,c.dcn,c.claimnumber from fepsit_pegadata.hc_837transactions a,fepsit_pegadata.fhps_claims_control b,fepsit_pegadata.fhps_claims_work c where a.filename =b.intake_filename  and   a.filename =c.inputfilename and a.filename='"
						+ str837file + "'";
				ResultSet rsClaimStatus = FusionUtils.seGetDatabaseResultset(strQuery);

				// To Retrieve the Data from ResultSet

				rsClaimStatus.absolute(1);// For Row 1
				String strDbX12Status = rsClaimStatus.getString("x12claimstatus");
				String strDbValueaddednetworktraceid = rsClaimStatus.getString("valueaddednetworktraceid");
				String strDbFilename = rsClaimStatus.getString("filename");
				String strDbClaimtype = rsClaimStatus.getString("claimtype");
				String strDbClaimsControlDcn = rsClaimStatus.getString("DCN");
				String strDbClaimsWorkDcn = rsClaimStatus.getString("DCN");
				String strDbClaimsControlEcn = rsClaimStatus.getString("ECN");
				String strDbClaimNum = rsClaimStatus.getString("claimnumber");

				// Test Scripts Start Here
				LoginPage.get().loginPegaApplication();
				seWaitForPageLoad();
				FusionUtils.seSwitchClaimsExaminerRole();
				FusionUtils.seSearchClaimWithClaimNumber(strDbClaimNum);
			    
				// To Compare the Data from Source to target
			
				seCompareStrings(strDbX12Status, strStatus, "=", "X12 Claim Status");
				seCompareStrings(strDbValueaddednetworktraceid,strDcn, "=","trace number");
				seCompareStrings(strDbFilename,str837file, "=","FIle Name");
				seCompareStrings(strDbClaimtype,strCliamstype, "=","Claim Type");
				seCompareStrings(strClaimControlECN,strDbClaimsControlEcn,"=","Verify Claims ECN in Control Table");
				seCompareStrings(strClaimControlDCN,strDbClaimsControlDcn,"=","Verify Claims DCN in Control Table");
				seCompareStrings(strClaimWorkDCN,strDbClaimsWorkDcn,"=","Verify Claims DCN In Work Table");

			}
		} catch (Exception e) {
			e.printStackTrace();
			log(FAIL, "An Exception Occured While Running Test Script", e.getLocalizedMessage(), false);
			setLastTestPassed(false);
		} finally {
			seCloseBrowser();
		}
	}
}