package com.fusion.test.scripts;


import com.fusion.actions.HomeAction;
import com.fusion.utilities.CoreSuperHelper;
import org.testng.annotations.Test;

import static com.fusion.actions.LoginAction.loginToPegaApplication;

/*
'Revision History
'#############################################################################
'@rev.On	@rev.No		@rev.By				  @rev.Comments
'										
'#############################################################################
*/

/**
 * Manual test case: <EDI Intake  Capture Receipt State from Filename for claim type Medicare A/B>
 *
 * @author :Touseef Tamboli (AG95927)
 * @since 05-Nov-2020
 * <p>
 * This Script will validate following test cases  TC-309,TC-310,TC-311,TC-312. The script will execute based on claim
 * number and verify rendering state in ClaimEntryHeaderPage ie Plan code. In the data sheet  plan code is mapped to
 * rendering states based on scenarios and input file.
 */

public class VerifyFusionLogin extends CoreSuperHelper {

    public static void aTAFInitParams() {
        MANUAL_TC_EXECUTION_EFFORT = "00:45:00";
    }

    @Test
    public void verifyFusionLogin() {
        aTAFInitParams();
        initiateTestScript();
    }

    public static void executeScript() {
        try {
            HomeAction home= new HomeAction();
            loginToPegaApplication();
            //ClaimsManager,ClaimsExaminer,JuniorClaimsExaminer,BusinessAnalyst,TestAnalyst
            home.switchUserRoleTo("TestAnalyst");



            home.logoutAndCloseBrowser();

        } catch (Exception e) {
            e.printStackTrace();
            log(FAIL, "An Exception Occurred While Running Test Script", e.getLocalizedMessage(), false);
            setLastTestPassed(false);
        }
    }
}
