package com.fusion.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.anthem.selenium.constants.DatabaseConstants;
import com.anthem.selenium.utility.EnvHelper;
import com.anthem.selenium.utils.DatabaseUtils;

import com.fusion.pageobjects.page.ClaimsSearchPage;
import com.fusion.pageobjects.page.HomePage;

public class FusionUtils extends CoreSuperHelper {

    /**
     * This function will connect the database based sql Query and it will return the Result set using result set User can retrive the Data and perform validations.
     *
     * @param strSqlQuery
     * @return rsValidOutput
     * @author Lachireddy Palla(AF33811)
     * @since 10/23/2020
     */
    public static ResultSet seGetDatabaseResultset(String strSqlQuery) {
        ResultSet rsReturnValue = null;
        try {
            String[] dataHubUserCredetials = getLoginInfo(EnvHelper.getValue("pegasitdbprofile.profile"));
            String strDBURL = EnvHelper.getValue("databaseurl");
            Connection conn = null;
            conn = DatabaseUtils.getConnection(DatabaseConstants.Database_ORACLE, strDBURL, dataHubUserCredetials[0], dataHubUserCredetials[1]);
            PreparedStatement ps = conn.prepareStatement(strSqlQuery, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rsOutput = ps.executeQuery();
            int intRecordCount = seGetResultSetCount(rsOutput);
            if (intRecordCount >= 1) {
                rsReturnValue = rsOutput;
            } else {
                log(FAIL, "Executed SqlQuery is Incorrect or Output returned is Null,");
                setLastTestPassed(false);
            }
        } catch (Exception e) {
            log(FAIL, "An Exception occurred while running seGetDatabaseResultset function", e.getLocalizedMessage(), false);
            setLastTestPassed(false);
            e.printStackTrace();
        }
        return rsReturnValue;
    }

    public static int seGetResultSetCount(ResultSet resultSet) throws SQLException {
        try {
            int i = 0;
            while (resultSet.next()) {
                i++;
            }
            return i;
        } catch (Exception e) {
            log(FAIL, "An Exception occurred while running seGetResultSetCount function", e.getLocalizedMessage(), false);
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Function To search the Claim using with Claim Number And Claim on Claim
     *
     * @param strClaimNumber
     * @return blnClmNum
     * @author Lachireddy Palla(AF33811)
     * @since 10/27/2020
     */
    public static boolean seSearchClaimWithClaimNumberAndClcikOnClaim(String strClaimNumber) {
        boolean blnClmNum = false;
        try {
            // Navigate to Claim Search
            seMoveToElement(HomePage.get().leftNavifationPane, "Left navigation Pane");
            seClickUsingActions(HomePage.get().claimsTab, "Claims");
            seClick(HomePage.get().claimsSearchLink, "Claims Search");
            seMoveToElement(HomePage.get().getNextHeaderLink, "Get Next");

            // Search Claim
            seSwitchToFrame("PegaGadget0Ifr");
            seSetText(ClaimsSearchPage.get().claimNumberTextbox, strClaimNumber, "Claim Number");
            seClick(ClaimsSearchPage.get().searchButton, "Search");
            boolean blnErrorMsg = FEPUtils.seIsElementNotDisplayed(ClaimsSearchPage.get().claimNotFoundErrorMessage, "Claim Not Found Error Message", true);
            if (blnErrorMsg == false) {
                log(FAIL, "Claim Not Found, Please verify the Serched Claim Number", false);
                setLastTestPassed(false);
            } else {
                blnClmNum = seIsElementDisplayed(ClaimsSearchPage.get().dynamicClaimNumber(strClaimNumber), "Claim Number", true);
                if (blnClmNum) {
                    seClick(ClaimsSearchPage.get().dynamicClaimNumber(strClaimNumber), "Claim Num");
                    getWebDriver().switchTo().defaultContent();
                    seSwitchToFrame("PegaGadget1Ifr");
                }
            }
        } catch (Exception e) {
            log(FAIL, "An Exception occurred while running seSearchClaimWithClaimNumberAndClcikOnClaim function", e.getLocalizedMessage(), false);
            setLastTestPassed(false);
            e.printStackTrace();
        }
        return blnClmNum;
    }

    /**
     * Function To search the Claim using with Claim Number
     *
     * @param strClaimNumber
     * @return blnClmNum
     * @author Lachireddy Palla(AF33811)
     * @since 10/27/2020
     */
    public static boolean seSearchClaimWithClaimNumber(String strClaimNumber) {
        boolean blnClmNum = false;
        try {
            // Navigate to Claim Search
            seMoveToElement(HomePage.get().leftNavifationPane, "Left navigation Pane");
            seClickUsingActions(HomePage.get().claimsTab, "Claims");
            seClick(HomePage.get().claimsSearchLink, "Claims Search");
            seMoveToElement(HomePage.get().getNextHeaderLink, "Get Next");

            // Search Claim
            seSwitchToFrame("PegaGadget0Ifr");
            seSetText(ClaimsSearchPage.get().claimNumberTextbox, strClaimNumber, "Claim Number");
            seClick(ClaimsSearchPage.get().searchButton, "Search");
            boolean blnErrorMsg = FEPUtils.seIsElementNotDisplayed(ClaimsSearchPage.get().claimNotFoundErrorMessage, "Claim Not Found Error Message", true);
            if (blnErrorMsg == false) {
                log(FAIL, "Claim Not Found, Please verify the Serched Claim Number", false);
                setLastTestPassed(false);
            } else {
                blnClmNum = seIsElementDisplayed(ClaimsSearchPage.get().dynamicClaimNumber(strClaimNumber), "Claim Number", true);
            }
        } catch (Exception e) {
            log(FAIL, "An Exception occurred while running seSearchClaimWithClaimNumber function", e.getLocalizedMessage(), false);
            setLastTestPassed(false);
            e.printStackTrace();
        }
        return blnClmNum;
    }

    /**
     * Function To Switch Test Analyst Role In PEGA application
     *
     * @return blnClmNum
     * @author Lachireddy Palla(AF33811)
     * @since 10/27/2020
     */
    public static boolean seSwitchTestAnalystRole() {
        boolean blnTestAnalyst = false;
        try {
            String strPageTitle = getWebDriver().getTitle();
            if (!strPageTitle.equalsIgnoreCase("Test Analyst")) {
                seHighlightElement(HomePage.get().loggedinUserIdLink);
                seMoveToElement(HomePage.get().loggedinUserIdLink, "User Id");
                seClick(HomePage.get().loggedinUserIdLink, "User Id");
                seMoveToElement(HomePage.get().switchAppsLink, "Switch Apps");
                seMoveToElement(HomePage.get().testAnalystRoleLink, "Test Analyst Link");
                seClick(HomePage.get().testAnalystRoleLink, "Test Analyst Link");
                blnTestAnalyst = seCompareStrings(true, getWebDriver().getTitle(), "Test Analyst", "=", "Test Analyst Link");
            } else {
                blnTestAnalyst = true;
            }
        } catch (Exception e) {
            log(FAIL, "An Exception occurred while running seSwitchTestAnalystRole function", e.getLocalizedMessage(), false);
            setLastTestPassed(false);
            e.printStackTrace();
        }
        return blnTestAnalyst;
    }

    /**
     * Function To Switch Claims Examiner Role In PEGA application
     *
     * @return blnClmNum
     * @author Lachireddy Palla(AF33811)
     * @since 10/27/2020
     */
    public static boolean seSwitchClaimsExaminerRole() {
        boolean blnClaimsExaminer = false;
        try {
            String strPageTitle = getWebDriver().getTitle();
            if (!strPageTitle.equalsIgnoreCase("Claims Examiner")) {
                seHighlightElement(HomePage.get().loggedinUserIdLink);
                seMoveToElement(HomePage.get().loggedinUserIdLink, "User Id");
                seClick(HomePage.get().loggedinUserIdLink, "User Id");
                seMoveToElement(HomePage.get().switchAppsLink, "Switch Apps");
                seMoveToElement(HomePage.get().claimsExaminerRoleLink, "Claims Examiner Link");
                seClick(HomePage.get().claimsExaminerRoleLink, "Claims Examiner Link");
                blnClaimsExaminer = seCompareStrings(true, getWebDriver().getTitle(), "Claims Examiner", "=", "Test Analyst Link");
            } else {
                blnClaimsExaminer = true;
            }
        } catch (Exception e) {
            log(FAIL, "An Exception occurred while running seSwitchClaimsExaminerRole function", e.getLocalizedMessage(), false);
            setLastTestPassed(false);
            e.printStackTrace();
        }
        return blnClaimsExaminer;
    }

    /**
     * Function To Switch Junior Claims Examiner Role In PEGA application
     *
     * @return blnJrClaimsExaminer
     * @author Lachireddy Palla(AF33811)
     * @since 10/27/2020
     */
    public static boolean seSwitchJrClaimsExaminerRole() {
        boolean blnJrClaimsExaminer = false;
        try {
            String strPageTitle = getWebDriver().getTitle();
            if (!strPageTitle.equalsIgnoreCase("Junior Claims Examiner")) {
                seHighlightElement(HomePage.get().loggedinUserIdLink);
                seMoveToElement(HomePage.get().loggedinUserIdLink, "User Id");
                seClick(HomePage.get().loggedinUserIdLink, "User Id");
                seMoveToElement(HomePage.get().switchAppsLink, "Switch Apps");
                seMoveToElement(HomePage.get().juniorClaimExaminerRoleLink, "Junior Claims Examiner Link");
                seClick(HomePage.get().juniorClaimExaminerRoleLink, "Jr Claims Examiner Link");
                blnJrClaimsExaminer = seCompareStrings(true, getWebDriver().getTitle(), "Junior Claims Examiner", "=", "Test Analyst Link");
            } else {
                blnJrClaimsExaminer = true;
            }
        } catch (Exception e) {
            log(FAIL, "An Exception occurred while running seSwitchClaimsExaminerRole function", e.getLocalizedMessage(), false);
            setLastTestPassed(false);
            e.printStackTrace();
        }
        return blnJrClaimsExaminer;
    }

    /**
     * Function To Switch Business Analyst Role In PEGA application
     *
     * @return blnBusinessAnalyst
     * @author Lachireddy Palla(AF33811)
     * @since 10/27/2020
     */
    public static boolean seSwitchBusinessAnalystRole() {
        boolean blnBusinessAnalyst = false;
        try {
            String strPageTitle = getWebDriver().getTitle();
            if (!strPageTitle.equalsIgnoreCase("blnBusinessAnalyst")) {
                seHighlightElement(HomePage.get().loggedinUserIdLink);
                seMoveToElement(HomePage.get().loggedinUserIdLink, "User Id");
                seClick(HomePage.get().loggedinUserIdLink, "User Id");
                seMoveToElement(HomePage.get().switchAppsLink, "Switch Apps");
                seMoveToElement(HomePage.get().businessAnalystRoleLink, "Business Analyst Link");
                seClick(HomePage.get().businessAnalystRoleLink, "Business Analyst Link");
                blnBusinessAnalyst = seCompareStrings(true, getWebDriver().getTitle(), "Business Analyst", "=", "Test Analyst Link");
            } else {
                blnBusinessAnalyst = true;
            }
        } catch (Exception e) {
            log(FAIL, "An Exception occurred while running seSwitchClaimsExaminerRole function", e.getLocalizedMessage(), false);
            setLastTestPassed(false);
            e.printStackTrace();
        }
        return blnBusinessAnalyst;
    }

    /**
     * Function To Switch Business Analyst Role In PEGA application
     *
     * @return blnBusinessAnalyst
     * @author Lachireddy Palla(AF33811)
     * @since 10/27/2020
     */
    public static boolean seSwitchClaimsManagerRole() {
        boolean blnClaimsManager = false;
        try {
            String strPageTitle = getWebDriver().getTitle();
            if (!strPageTitle.equalsIgnoreCase("Claims Manager")) {
                seHighlightElement(HomePage.get().loggedinUserIdLink);
                seMoveToElement(HomePage.get().loggedinUserIdLink, "User Id");
                seClick(HomePage.get().loggedinUserIdLink, "User Id");
                seMoveToElement(HomePage.get().switchAppsLink, "Switch Apps");
                seMoveToElement(HomePage.get().businessAnalystRoleLink, "Claims Manager Link");
                seClick(HomePage.get().businessAnalystRoleLink, "Claims Manager Link");
                blnClaimsManager = seCompareStrings(true, getWebDriver().getTitle(), "Claims Manager", "=", "Claims Manager Link");
            } else {
                blnClaimsManager = true;
            }
        } catch (Exception e) {
            log(FAIL, "An Exception occurred while running seSwitchClaimsExaminerRole function", e.getLocalizedMessage(), false);
            setLastTestPassed(false);
            e.printStackTrace();
        }
        return blnClaimsManager;
    }
}