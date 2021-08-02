package com.fusion.test.sanity;

import com.anthem.selenium.constants.BrowserConstants;
import com.anthem.selenium.utility.EnvHelper;
import org.testng.annotations.Test;
import com.fusion.pageobjects.page.ClaimsInquiryPage;
import com.fusion.pageobjects.page.LoginPage;
import com.fusion.utilities.CoreSuperHelper;
import com.fusion.utilities.FusionUtils;

import java.sql.ResultSet;

import static com.fusion.utilities.CommonUtils.convertDateFormat;

/*
'Revision History
'#############################################################################
'@rev.On	@rev.No		@rev.By				  @rev.Comments
'										
'#############################################################################
*/

/**
 * Manual test case: TC-391 , Novologix claims
 * <p>
 *
 * <p>
 * Please refer this test script while creating other test scripts
 *
 * @author Touseef Tamboli (AG95927)
 * @since 26-Oct-2020
 */

public class VerifyReceivedDateOfNovologixClaims_TS extends CoreSuperHelper {

    static String BASE_URL = EnvHelper.getValue("url");

    public static void aTAFInitParams() {
        MANUAL_TC_EXECUTION_EFFORT = "00:05:00";
    }

    @Test
    public void verifyReceivedDateOfNovologixClaims() {
        aTAFInitParams();
        initiateTestScript();
    }

    public static void executeScript() {

        setIgnoreLastTestFailure(true);
        try {
            if (getCellValue("Run_Flag").equalsIgnoreCase("Yes")) {
                setIgnoreLastTestFailure(true);

                logExtentReport(getCellValue("TEST_CASE_DESCRIPTION"));

                String strQuery = "SELECT s.claimid,s.x12claimstatus,s.filename,t.datereceived,t.claimnumber"
                        + " FROM fepsit_pegadata.hc_837transactions  s"
                        + " INNER JOIN fepsit_pegadata.FHPS_CLAIMS_WORK  t ON s.valueaddednetworktraceid = t.DCN"
                        + " WHERE FILENAME='" + getCellValue("File_Name") + "'";

                ResultSet rsClaimStatus = FusionUtils.seGetDatabaseResultset(strQuery);

                // To Retrive the Data from ResultSet
                rsClaimStatus.absolute(1);// For Row 1
                String strx12Status = rsClaimStatus.getString("x12claimstatus");
                String claimNumber = rsClaimStatus.getString("claimnumber");
                String receivedOn = rsClaimStatus.getString("datereceived");
                System.out.println("strx12Status: " + strx12Status + "\nClaim Number: " + claimNumber);

                // To Compare the Data from Source to target
                if (seCompareStrings(strx12Status, "1", "=", "X12 Claim Status")) {

                    //**********************Goto Pega*********************
                    seOpenBrowser(BrowserConstants.Chrome, BASE_URL);
                    // Test Scripts Start Here
                    LoginPage.get().loginPegaApplication();
                    FusionUtils.seSwitchClaimsExaminerRole();
                    FusionUtils.seSearchClaimWithClaimNumberAndClcikOnClaim(claimNumber);
                    seMoveToElement(ClaimsInquiryPage.get().receivedOnField, "Received On Date");
                    String state=getCellValue("State");
                    seVerifyFieldValue(ClaimsInquiryPage.get().receivedOnField,
                            convertDateFormat(getCellValue("Novologix").equalsIgnoreCase("Yes")&&
                                    getCellValue("Repricer_Segment").equalsIgnoreCase("Yes")&&
                                    (state.contains("MO")||state.contains("IN")||state.contains("OH"))?
                                    getCellValue("Repricer_Date"):receivedOn), "Received date: ");


                }else{
                    System.out.println("X12 Claim Status: "+strx12Status+" unsuccessful");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            log(FAIL, "An Exception Occurred While Running Test Script", e.getLocalizedMessage(), false);
            setLastTestPassed(false);
        } finally {
            seCloseBrowser();
        }
    }
}
