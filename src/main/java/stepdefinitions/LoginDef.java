package stepdefinitions;

import com.fusion.pageobjects.LoginPage;
import com.fusion.utilities.CoreSuperHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginDef extends CoreSuperHelper {

    public LoginPage login;

    @Given("User launched the chrome browser with given URL")
    public void user_launched_the_chrome_browser_with_URL() {
        System.out.println("User launched the chrome browser with given URL ***");
//        seOpenBrowser(BrowserConstants.Chrome, EnvHelper.getValue("url"));
    }

    @Then("User enters the given user id and password")
    public void user_enters_the_given_user_id_and_password() {
        System.out.println("User enters the given user id and password ***");
//        login=new LoginPOC();
//        String[] userInfo = getLoginInfo(EnvHelper.getValue("user.profile"));
//        login.fillOutLoginForm(userInfo[0],userInfo[1]);
    }

    @And("Click on login button")
    public void click_on_login_button() {
        System.out.println("Click on login button ***");
//        login.clickOnLoginButton();
    }

    @Then("Page title should be {string}")
    public void page_title_should_be(String title) throws InterruptedException {
        System.out.println("Page title should be {string} ***");
        Thread.sleep(1000);
//        driver.getTitle();
//        Assert.assertEquals("Login failed",title,driver.getTitle());

    }

    @When("User click on logout link")
    public void user_click_on_logout_link() throws Throwable {
        System.out.println("User click on logout link ***");
//        home.clickLnkLogout();
        Thread.sleep(3000);
    }

    @Then("Close browser")
    public void close_browser() {
        System.out.println("Close browser ***");
//        seCloseBrowser();
    }

}
