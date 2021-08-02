package com.fusion.test.scripts.lookups;


import com.fusion.actions.DashboardAction;
import com.fusion.actions.HomeAction;
import com.fusion.actions.LookupsAction;
import com.fusion.utilities.CoreSuperHelper;
import org.testng.Assert;
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
 * @author :Touseef Tamboli (AG95927)
 * @since 05-Nov-2020
 * <p>
 * This Script will validate following test cases  TC-309,TC-310,TC-311,TC-312. The script will execute based on claim
 * number and verify rendering state in ClaimEntryHeaderPage ie Plan code. In the data sheet  plan code is mapped to
 * rendering states based on scenarios and input file.
 */

public class VerifyProcedureCode extends CoreSuperHelper {

    public static void aTAFInitParams() {
        MANUAL_TC_EXECUTION_EFFORT = "00:45:00";
    }

    @Test(groups = { "LookupTable" })
    public void verifyLookupTableOperation() {
        aTAFInitParams();
        initiateTestScript();
    }

    public static void executeScript() {
        try {
            Assert.assertTrue(loginToPegaApplication(),"Login to Pega");
            HomeAction home= new HomeAction();
            //ClaimsManager,ClaimsExaminer,JuniorClaimsExaminer,BusinessAnalyst,TestAnalyst
            Assert.assertTrue(home.switchUserRoleTo(getCellValue("UserRole")));
            Assert.assertTrue(home.selectPegaMenu("Lookups"));
            Assert.assertTrue(home.selectPegaMenu(getCellValue("Table")));
            LookupsAction lookups= new LookupsAction();
            Assert.assertTrue(lookups.verifyLookupTableName(getCellValue("Table")));
            if(getCellValue("Action").toLowerCase().contains("add"))
                Assert.assertTrue(lookups.performLookupAction(getCellValue("ADD")));
            else {
                Assert.assertTrue(lookups.lookupTableSearch());
                Assert.assertTrue(lookups.selectLookupAction("HCPC"));
                Assert.assertTrue(lookups.performLookupAction(getCellValue("Action")));
            }
//            Assert.assertTrue(lookups.verifyLookupTableErrors());
            String strConfirmationID=home.verifyModalDialogue();
            Assert.assertFalse(strConfirmationID.contains("found"));
            if(!strConfirmationID.equals("process")) {
                DashboardAction dashboard = new DashboardAction();
                Assert.assertTrue(home.switchUserRoleTo(getCellValue("ManagerRole")));
                Assert.assertTrue(dashboard.searchWorkQueue("Lookup Manager",strConfirmationID));
                Assert.assertTrue(lookups.performApprovals());
            }

            Thread.sleep(5000);
            home.logoutAndCloseBrowser();
        } catch (Exception e) {
            e.printStackTrace();
            log(FAIL, "An Exception Occurred While Running Test Script", e.getLocalizedMessage(), false);
            setLastTestPassed(false);
        }
    }
}
