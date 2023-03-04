package com.qugem.stepDefinitions;

import com.github.javafaker.Faker;
import com.qugem.pages.ADMIN.AdminSignupPage;
import com.qugem.pages.ADMIN.AdminUsersPage;
import com.qugem.pages.LoginPage;
import com.qugem.utilities.BrowserUtils;
import com.qugem.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

public class AddNewUserStepDef {
  Faker faker = new Faker();
  public static final int COMPANY_OPTION_NUMBER = 1; // 1- 5
  AdminUsersPage usersPage = new AdminUsersPage();
  AdminSignupPage signupPage = new AdminSignupPage();
  public String newUserEmail = faker.internet().emailAddress();
  String newUserName = faker.name().fullName();
  public String newUserPassword = "qugem123";
  String newUserCompany = "QUICKLY TRANSPORTE GMBH";    // findElements ile list -- Dinamik



  @When("Admin clicks addNewUser button")
  public void admin_clicks_addNewUser_button() {

    usersPage.userAddButton.click();
  }

  @When("Admin fills the form and submit")
  public void admin_fills_the_form_and_submit() {
    signupPage.userNameInput.sendKeys(newUserName);
    signupPage.emailInput.sendKeys(newUserEmail);
    signupPage.passwordInput.sendKeys(newUserPassword);
    signupPage.confirmPasswordInput.sendKeys(newUserPassword);
    signupPage.companyRadioSelect.click();
    signupPage.companyCheckbox1.click();
    BrowserUtils.waitFor(1);
    Actions action = new Actions(Driver.get());
    action.sendKeys(Keys.ESCAPE).build().perform();
    BrowserUtils.waitFor(1);
    signupPage.addUserSubmit.click();
    BrowserUtils.waitFor(4);
  }

  @Then("Admin should be able to see the New User on Users page")
  public void admin_should_be_able_to_see_the_New_User_on_Users_page() {
    List<WebElement> rowWebElements = signupPage.getRowElements(5, 3, newUserEmail);
    Assert.assertEquals("verify Email", newUserEmail, rowWebElements.get(2).getText());
    System.out.println("rowWebElements.toString() = " + rowWebElements.get(2).getText());

    Assert.assertEquals("verify Name", newUserName, rowWebElements.get(0).getText());
    System.out.println("rowWebElements.toString() = " + rowWebElements.get(0).getText());

    Assert.assertEquals("verify Company", newUserCompany, rowWebElements.get(1).getText());
    System.out.println("rowWebElements.toString() = " + rowWebElements.get(1).getText());

  }

  @Then("the user login again as newUser")
  public void the_user_login_again_as_newUser() {
    new LoginPage().login(newUserEmail, newUserPassword);
    BrowserUtils.waitFor(2);

  }

}
