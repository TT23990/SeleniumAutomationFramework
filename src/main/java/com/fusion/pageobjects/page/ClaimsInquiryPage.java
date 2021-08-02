package com.fusion.pageobjects.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.anthem.selenium.SuperHelper;

/*
'Revision History
'#############################################################################
'@rev.On	@rev.No		@rev.By				  @rev.Comments
'
'#############################################################################
*/

/**
 * Page Object Model for Claim Inquiry page
 *
 * @author Lachireddy Palla(AF33811)
 * @since 29-September-2020
 */
public class ClaimsInquiryPage extends SuperHelper {

    private static ClaimsInquiryPage thisIsTestObj;

    // So that there only one object accesses this class at any moment
    public synchronized static ClaimsInquiryPage get() {
        thisIsTestObj = PageFactory.initElements(getWebDriver(), ClaimsInquiryPage.class);
        return thisIsTestObj;
    }

    // Add objects here...
    @FindBy(how = How.XPATH, using = "//h2[text()='Search claims']")
    public WebElement SearchClaimsPageHeader;

    @FindBy(how = How.XPATH, using = "//div[@data-expr-id='187f541e75d4e548be91e66ee99a391a0bc1fc72_49']//button")
    public WebElement viewEventsButton;

    // Dynamic Field to select Claim Number from the search results
    public WebElement dynamicClaimNumber(String strClaimNumber) {
        return getWebDriver().findElement(By.xpath("//td/div/span[text()='" + strClaimNumber + "']"));
    }

    @FindBy(how = How.XPATH, using = "//button[@data-test-id='201607080940200123385414']")
    public WebElement searchButton;

    @FindBy(how = How.XPATH, using = "//button[@data-test-id='20170525144103076958974']")
    public WebElement moduleIdField;

    @FindBy(how = How.XPATH, using = "//button[@data-test-id='201606281525580775382641']")
    public WebElement fhpsErrorCodeField;

    @FindBy(how = How.XPATH, using = "//span[@data-test-id='201611221722090705181000']")
    public WebElement receivedOnField;

    @FindBy(how = How.XPATH, using = "//div[text()='Patient Info']/following::span[text()='Last Name'][1]/following::span[1]")
    public WebElement patientLastNameField;

    @FindBy(how = How.XPATH, using = "//div[text()='Patient Info']/following::span[text()='First Name'][1]/following::span[1]")
    public WebElement patientFirstNameField;

    @FindBy(how = How.XPATH, using = "//div[text()='Patient Info']/following::span[text()='Middle Name'][1]/following::span[1]")
    public WebElement patientMiddleNameField;

    @FindBy(how = How.XPATH, using = "//div[text()='Patient Info']/following::span[text()='Suffix'][1]/following::span[1]")
    public WebElement patientsuffixField;

    @FindBy(how = How.XPATH, using = "//div[text()='Patient Info']/following::span[text()='Realtionship Code'][1]/following::span[1]")
    public WebElement patientRelationshipCodeField;

    @FindBy(how = How.XPATH, using = "//div[text()='Patient Info']/following::span[text()='Date of Birth'][1]/following::span[1]")
    public WebElement patientDateOfBirthField;

    @FindBy(how = How.XPATH, using = "//div[text()='Patient Info']/following::span[text()='Gender'][1]/following::span[1]")
    public WebElement patientGenderField;

    @FindBy(how = How.XPATH, using = "//div[text()='Patient Info']/following::div[text()='Address'][1]/following::span[1]")
    public WebElement patientAddressLine1Field;

    @FindBy(how = How.XPATH, using = "//div[text()='Patient Info']/following::div[text()='Address'][1]/following::span[3]")
    public WebElement patientCityField;

    @FindBy(how = How.XPATH, using = "//div[text()='Patient Info']/following::div[text()='Address'][1]/following::span[4]")
    public WebElement patientStateField;

    @FindBy(how = How.XPATH, using = "//div[text()='Patient Info']/following::div[text()='Address'][1]/following::span[5]")
    public WebElement patientZipCodeField;

    @FindBy(how = How.XPATH, using = "//div[text()='Subscriber Info']/following::span[text()='Last Name'][1]/following::span[1]")
    public WebElement subscriberLastNameField;

    @FindBy(how = How.XPATH, using = "//div[text()='Subscriber Info']/following::span[text()='First Name'][1]/following::span[1]")
    public WebElement subscriberFirstNameField;

    @FindBy(how = How.XPATH, using = "//div[text()='Subscriber Info']/following::span[text()='Middle Name'][1]/following::span[1]")
    public WebElement subscriberMiddleNameField;

    @FindBy(how = How.XPATH, using = "//div[text()='Subscriber Info']/following::span[text()='Gender'][1]/following::span[1]")
    public WebElement subscriberGenderField;

    @FindBy(how = How.XPATH, using = "//div[text()='Subscriber Info']/following::span[text()='Social Security Number'][1]/following::span[1]")
    public WebElement subscriberSsnField;

    @FindBy(how = How.XPATH, using = "//div[text()='Subscriber Info']/following::span[text()='Date of birth'][1]/following::span[1]")
    public WebElement subscriberDateOfBirthField;

    @FindBy(how = How.XPATH, using = "//div[text()='Subscriber Info']/following::span[text()='Plan Name'][1]/following::span[1]")
    public WebElement subscriberPlanNameField;

    @FindBy(how = How.XPATH, using = "//div[text()='Subscriber Info']/following::div[text()='Address'][1]/following::span[1]")
    public WebElement subscriberAddressLine1Field;

    @FindBy(how = How.XPATH, using = "//div[text()='Subscriber Info']/following::div[text()='Address'][1]/following::span[3]")
    public WebElement subscriberCityField;

    @FindBy(how = How.XPATH, using = "//div[text()='Subscriber Info']/following::div[text()='Address'][1]/following::span[4]")
    public WebElement subscriberStateField;

    @FindBy(how = How.XPATH, using = "//div[text()='Subscriber Info']/following::div[text()='Address'][1]/following::span[5]")
    public WebElement subscriberZipCodeField;

    @FindBy(how = How.XPATH, using = "//div[text()='Subscriber Info']/following::div[text()='Address'][1]/following::span[8]")
    public WebElement subscriberZipCodeExtField;

    @FindBy(how = How.XPATH, using = "//div[text()='Subscriber Info']/following::span[text()='Employer Name'][1]/following::span[1]")
    public WebElement subscriberEmployerNameField;

    @FindBy(how = How.XPATH, using = "//div[text()='Subscriber Info']/following::span[text()='Employment Status Code'][1]/following::span[1]")
    public WebElement subscriberEmploymentStatusCodeField;

    @FindBy(how = How.XPATH, using = "//div[text()='Subscriber Info']/following::span[text()='Employer Location'][1]/following::span[1]")
    public WebElement subscriberEmployerLocationField;

    @FindBy(how = How.XPATH, using = "//div[text()='Policy Info']/following::span[text()='Policy number'][1]/following::span[1]")
    public WebElement policyNumberField;

    @FindBy(how = How.XPATH, using = "//div[text()='Policy Info']/following::span[text()='Payer Responsibility Number'][1]/following::span[1]")
    public WebElement payerResponsibilityNumberField;

    @FindBy(how = How.XPATH, using = "//div[text()='Policy Info']/following::span[text()='Subscriber ID'][1]/following::span[1]")
    public WebElement SubscriberIdField;

    @FindBy(how = How.XPATH, using = "//div[text()='Billing Provider']/following::span[text()='Facility name'][1]/following::span[1]")
    public WebElement facilityNameField;

    @FindBy(how = How.XPATH, using = "//div[text()='Billing Provider']/following::div[text()='Address'][1]/following::span[1]")
    public WebElement facilityAddressLine1Field;

    @FindBy(how = How.XPATH, using = "//div[text()='Billing Provider']/following::div[text()='Address'][1]/following::span[3]")
    public WebElement facilityCityField;

    @FindBy(how = How.XPATH, using = "//div[text()='Billing Provider']/following::div[text()='Address'][1]/following::span[4]")
    public WebElement facilityStateField;

    @FindBy(how = How.XPATH, using = "//div[text()='Billing Provider']/following::div[text()='Address'][1]/following::span[5]")
    public WebElement facilityZipCodeField;

    @FindBy(how = How.XPATH, using = "//div[text()='Billing Provider']/following::div[text()='Address'][1]/following::span[8]")
    public WebElement facilityZipCodeExtField;

    @FindBy(how = How.XPATH, using = "//div[text()='Billing Provider']/following::span[text()='Provider NPI'][1]/following::span[1]")
    public WebElement providerNpiField;

    @FindBy(how = How.XPATH, using = "//div[text()='Billing Provider']/following::span[text()='Tax ID'][1]/following::span[1]")
    public WebElement taxIdField;

    @FindBy(how = How.XPATH, using = "//div[text()='Billing Provider']/following::a[text()='PAY TO ADDRESS']")
    public WebElement payToAddressLink;

    @FindBy(how = How.XPATH, using = "//div[text()='Billing Provider']/following::div[text()='PAY TO ADDRESS']/following::span[1]")
    public WebElement payToAddressLine1Field;

    @FindBy(how = How.XPATH, using = "//div[text()='Billing Provider']/following::div[text()='PAY TO ADDRESS']/following::span[3]")
    public WebElement payToAddressCityField;

    @FindBy(how = How.XPATH, using = "//div[text()='Billing Provider']/following::div[text()='PAY TO ADDRESS']/following::span[4]")
    public WebElement payToAddressStateField;

    @FindBy(how = How.XPATH, using = "//div[text()='Billing Provider']/following::div[text()='PAY TO ADDRESS']/following::span[5]")
    public WebElement payToAddressZipCodeField;

    @FindBy(how = How.XPATH, using = "//div[text()='Billing Provider']/following::div[text()='PAY TO ADDRESS']/following::span[8]")
    public WebElement payToAddressZipCodeExtField;
}