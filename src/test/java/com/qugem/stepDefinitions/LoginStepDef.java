package com.qugem.stepDefinitions;

import com.qugem.pages.BasePage;
import com.qugem.pages.Dashboard;
import com.qugem.pages.LoginPage;
import com.qugem.utilities.BrowserUtils;
import com.qugem.utilities.ConfigurationReader;
import com.qugem.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;

public class LoginStepDef {

  public static final String DASHBOARD_URL = "https://qugem-staging.netlify.app/";

  @Given("the user logged in as {string}")
  public void the_user_logged_in_as(String userType) {

    Driver.get().get(ConfigurationReader.get("url"));

    String username = null;
    String password = null;

    if (userType.equals("admin")) {
      username = ConfigurationReader.get("admin_username");
      password = ConfigurationReader.get("admin_password");
    } else if (userType.equals("c_admin1")) {
      username =
          ConfigurationReader.get(
              "c_admin1_username"); // username and password selection should be in LoginPage
      password = ConfigurationReader.get("c_admin1_password"); // Framework Maintenance
    } else if (userType.equals("c_admin2")) {
      username = ConfigurationReader.get("c_admin2_username");
      password = ConfigurationReader.get("c_admin2_password");
    }
    new LoginPage().login(username, password);
  }

  @Given("the user is on the login page")
  public void the_user_is_on_the_login_page() {
    String url = ConfigurationReader.get("url");
    Driver.get().get(url);
  }

  @When("the user enters the tester2 credentials")
  public void the_user_enters_the_tester2_credentials() {

    String username = ConfigurationReader.get("tester2_username");
    String password = ConfigurationReader.get("tester2_password");

    LoginPage loginPage = new LoginPage();
    loginPage.login(username, password);
  }

  Dashboard dashboard = new Dashboard();

  @When("the user logged out")
  public void the_user_logged_out() {

      BrowserUtils.waitFor(6); // Waiting for message -- Cloose Button  / Container
      dashboard.accountButton.click();
      BrowserUtils.waitForClickablility(dashboard.signOut,1);
      dashboard.signOut.click();
  }

  @Then("the user should be at login page")
  public void the_user_should_be_at_login_page() {

    BrowserUtils.waitForPageToLoad(3);
    Assert.assertEquals(
        "Url not matched with expected !",
        ConfigurationReader.get("url"),
        BrowserUtils.getCurrentUrl());
  }

  @Then("the user should be on the dashboardPage")
  public void the_user_should_be_on_the_dashboardPage() {
    BrowserUtils.waitFor(2);
    Assert.assertEquals("Verify the PageURL", DASHBOARD_URL, Driver.get().getCurrentUrl());
  }
}
