package com.fusion.actions;

import com.fusion.pageobjects.LookupsPage;

import static com.fusion.utilities.CoreSuperHelper.*;

public class LookupsAction extends BaseActions {

    LookupsPage lookups;

    //TODO Add New Code

    //TODO read table grid

    public boolean verifyLookupTableName(String tableName) {
        seSwitchToFrame("PegaGadget0Ifr");
        System.out.println("Verifying lookup Table Name Expected: " + tableName + " ***");
        lookups = new LookupsPage();
        return seIsElementDisplayed(lookups.selectSpanCaseTitle(tableName), tableName, true);
    }

    //TODO Search Text
    public boolean selectLookupAction(String table) throws InterruptedException {
        String strSearchValue = getCellValue("Code").isEmpty() ? "Description" : "Code";
        String strAction = getCellValue("Action");
        if (!tableOperator(table, strAction, strSearchValue)) {
            System.out.println("No results to display");
            return false;
        }
        return true;
    }

    public boolean verifyLookupTableErrors() {
        System.out.println("Verifying lookup Table errors.");
        seAlertAccept();
        lookups = new LookupsPage();
        return lookups.errorList().size() <= 0;
    }

    public boolean performApprovals() {
        System.out.println("Verifying approval flow");
        lookups = new LookupsPage();
        seSwitchToFrame("PegaGadget1Ifr");
            seSetText(lookups.labelTextAreaBox("Leave a Comment"), getCellValue("Manager_Comments"), "Comments");
            String strManangerAction=getCellValue("Manager_Action");
        switch (strManangerAction) {
            case "Send back to Originator":
            case "Approve":
                seClick(lookups.buttonText(strManangerAction), strManangerAction);
                break;

            case "Reject":
                seClick(lookups.rejectButton(strManangerAction), strManangerAction);
                break;
        }
        return true;
    }

    public boolean lookupTableSearch() throws InterruptedException {
        //        seSwitchToFrame("PegaGadget0Ifr");
        //Provider file and NDC Code different search criteria
        System.out.println("Verifying Lookup search ***");
        lookups = new LookupsPage();
        switch(getCellValue("table")){
            case "NDC Code":
            case "Provider File":
                return true;
            default:
                String strSearchValue = getCellValue("Code").isEmpty() ? "Description" : "Code";
                seSetText(lookups.divInput("Code/Description"), getCellValue(strSearchValue));
                seClick(lookups.buttonText("earch"), "Search");
                Thread.sleep(1000);
                return true;
        }

    }

    public boolean performLookupAction(String strAction) throws InterruptedException {
        switch (strAction.toLowerCase()) {
            case "add":


            case "update":
                seClick(lookups.labelText(getCellValue("Type")), "Code Type");
                Thread.sleep(500);
                seSetText(lookups.labelTextAreaBox("Description"), getCellValue("Description"));
                seSetText(lookups.labelTextAreaBox("Comments"), getCellValue("Comments"));
                seClick(lookups.buttonText("Update"), "Update button");
                return true;

            case "delete":
                seSetText(lookups.labelTextAreaBox("Comments"), getCellValue("Comments"));
                seClick(lookups.buttonText("Delete"), "Delete button");
                return true;

            case "search": return true;
        }
        return false;
    }


}
