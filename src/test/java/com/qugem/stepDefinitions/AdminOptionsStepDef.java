package com.qugem.stepDefinitions;

import com.qugem.pages.ADMIN.AdminOptionsPage;
import com.qugem.utilities.BrowserUtils;
import com.qugem.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.util.List;

public class AdminOptionsStepDef {

  AdminOptionsPage adminOptionsPage = new AdminOptionsPage();

  public static final String CLIENT_NAME = "TestClient";
  public static final String CLIENT_PLZ = "44444";
  public static final String CLIENT_CITY = "TestLand";
  public static final String CLIENT_ADDRESS = "Tester straße 260";
  public WebElement land = adminOptionsPage.landOpt15;  // give land option number here 1-16

  String newCompanyOption = "TEST_COMPANY";
  String newRoleOption = "TEST_ROLE";

  @When("admin adds new Client")
  public void admin_adds_new_Client() {
    adminOptionsPage.addClientButton.click();
    adminOptionsPage.newClientName.sendKeys(CLIENT_NAME);
    adminOptionsPage.newClientPLZ.sendKeys(CLIENT_PLZ);
    adminOptionsPage.newClientCity.sendKeys(CLIENT_CITY);
    adminOptionsPage.newClientAddress.sendKeys(CLIENT_ADDRESS);
    adminOptionsPage.landOptionButton.click();
    BrowserUtils.waitFor(1);
    land.click();
    adminOptionsPage.submitNewClient.click();
  }

  @Then("New Client Value needs to be listed on OptionsPage")
  public void new_Client_Value_needs_to_be_listed_on_OptionsPage() {
    BrowserUtils.waitFor(1);
    Assert.assertEquals("newClient - Name ",CLIENT_NAME,adminOptionsPage.verifyClientName.getText());
    Assert.assertEquals("newClient - Address ",CLIENT_ADDRESS,adminOptionsPage.verifyClientAddress.getText());
    Assert.assertEquals("newClient - PLZ ",CLIENT_PLZ,adminOptionsPage.verifyClientPLZ.getText());
    Assert.assertEquals("newClient - CITY ",CLIENT_CITY,adminOptionsPage.verifyClientCity.getText());
  }

  public String clientNameVerify;

  @When("admin deletes {string} Client")
  public void admin_deletes_Client(String clientNameToDelete) {
    clientNameVerify =clientNameToDelete;   // for verify

    List<WebElement> clients = Driver.get().findElements(By.xpath("((//table)[1])//td[1]"));

    List<String> elementsText = BrowserUtils.getElementsText(clients);
    if (elementsText.contains(clientNameToDelete))
    {
      for (int i = 1; i <= clients.size(); i++) {
        WebElement clientName = Driver.get().findElement(By.xpath("((//table)[1]//td[1])[" + i + "]"));
        if (clientName.getText().equals(clientNameToDelete)) {
          try {
            Driver.get().findElement(By.xpath("((//table)[1]//td[6])[" + i + "]//button[2]")).click();
            BrowserUtils.waitForClickablility(adminOptionsPage.optionsSure_DeleteYESButton, 1);
            adminOptionsPage.optionsSure_DeleteYESButton.click();
            break;
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      }
    } else {
      Assert.fail("There is no record to delete  = " +clientNameToDelete);
    }
  }

  @Then("New Client Value needs to be no more listed on OptionsPage")
  public void new_Client_Value_needs_to_be_no_more_listed_on_OptionsPage()  {  // bunu methoda cevirmeli 3 yerde kullandik
    List<WebElement> clientElements = Driver.get().findElements(By.xpath("((//table)[1])//td[1]"));
    for (WebElement clientElement : clientElements) {
      Assert.assertFalse("Is the selected client DELETED ? ", clientElement.getText().equals(clientNameVerify));
    }
    System.out.println(clientNameVerify +" deleted Successfully !");
  }


  @When("admin adds new Company")
  public void admin_adds_new_Company() {
    //BrowserUtils.scrollToElement(Driver.get().findElement(By.xpath("(//h3)[3]")));
    adminOptionsPage.companyInput.sendKeys(newCompanyOption);
    BrowserUtils.waitFor(1);
    adminOptionsPage.addCompanyButton.click();
  }

  @Then("New Company Value needs to be listed on OptionsPage")
  public void new_Company_Value_needs_to_be_listed_on_OptionsPage() {

   // BrowserUtils.waitForPageToLoad(2);
    Driver.get().navigate().refresh();
    // BrowserUtils.waitForPageToLoad(5);
    List<WebElement> companies = Driver.get().findElements(By.xpath("(//table)[2]/tbody/tr"));
    System.out.println("companies.size() = " + companies.size());
    BrowserUtils.scrollToElement(Driver.get().findElement(By.xpath("(//table)[2]/tbody/tr")));
    String lastCompany = companies.get(companies.size() - 1).getText();
    System.out.println("lastCompany = " + lastCompany);
    Assert.assertTrue("verify last value", lastCompany.contains(newCompanyOption));
  }





  @When("admin adds new Role")
  public void admin_adds_new_Role() {
    adminOptionsPage.roleInput.sendKeys(newRoleOption);
    adminOptionsPage.addRoleButton.click();
  }

  @Then("New Role Value needs to be listed on OptionsPage")
  public void new_Role_Value_needs_to_be_listed_on_OptionsPage() {
    Driver.get().navigate().refresh();
    BrowserUtils.waitForPageToLoad(5);
    List<WebElement> roles = Driver.get().findElements(By.xpath("(//table)[3]/tbody/tr"));
    System.out.println("role.size() = " + roles.size());
    BrowserUtils.scrollToElement(Driver.get().findElement(By.xpath("(//table)[3]/tbody/tr")));
    String lastRole = roles.get(roles.size() - 1).getText();
    System.out.println("lastRole = " + lastRole);
    Assert.assertTrue("verify last value", lastRole.contains(newRoleOption));
  }

  String taxFreeOption = "55";
  String flatRateTaxOption = "22";
  String perKmFeeOption = "0.56";
  String fixedPayRateOption = "23";

  @When("admin changes FixedRates")
  public void admin_changes_FixedRates() {
    adminOptionsPage.taxFreeInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), taxFreeOption);
    adminOptionsPage.flatRateTaxInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), flatRateTaxOption);
    adminOptionsPage.perKmFeeInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), perKmFeeOption);
    adminOptionsPage.fixedPayRateInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), fixedPayRateOption);
    adminOptionsPage.addFixedRatesButton.click();
  }

  @Then("New FixedRates Value needs to be listed on OptionsPage")
  public void new_FixedRates_Value_needs_to_be_listed_on_OptionsPage() {

    Assert.assertEquals(
        "verify", taxFreeOption, adminOptionsPage.taxFreeInput.getAttribute("value"));
    Assert.assertEquals(
        "verify", flatRateTaxOption, adminOptionsPage.flatRateTaxInput.getAttribute("value"));
    Assert.assertEquals(
        "verify", perKmFeeOption, adminOptionsPage.perKmFeeInput.getAttribute("value"));
    Assert.assertEquals(
        "verify", fixedPayRateOption, adminOptionsPage.fixedPayRateInput.getAttribute("value"));
  }


  String lastCompanyText="";

  @When("Admin deletes the last created Company")
  public void admin_deletes_the_last_created_Company() {
    List<WebElement> tableElements = Driver.get().findElements(By.xpath("(//table)[2]//button"));

    lastCompanyText = Driver.get().findElement(By.xpath("((//table)[2]//th)[" + tableElements.size() + "]")).getText();
    lastCompanyText= lastCompanyText.substring(3);    // need the first spaces index adres here

    Driver.get().findElement(By.xpath("((//table)[2]//button)["+tableElements.size()+"]")).click(); // delete button click
    BrowserUtils.waitForClickablility(adminOptionsPage.optionsSure_DeleteYESButton, 1);
    adminOptionsPage.optionsSure_DeleteYESButton.click();
  }

  @Then("The Company Value needs to be no more listed on OptionsPage")
  public void the_Company_Value_needs_to_be_no_more_listed_on_OptionsPage() {
    List<WebElement> tableElements = Driver.get().findElements(By.xpath("((//table)[2])//th[1]"));
    for (WebElement companyRow : tableElements) {

      Assert.assertFalse("Is the selected client DELETED? ", lastCompanyText.equals(companyRow.getText().substring(companyRow.getText().indexOf(" ")+1)));
    }
    System.out.println("lastCompanyText = " + lastCompanyText);
  }


  String lastRoleText="";
  @When("Admin deletes the last created ROLE")
  public void admin_deletes_the_last_created_ROLE() {
    List<WebElement> tableElements = Driver.get().findElements(By.xpath("(//table)[3]//button"));
    lastRoleText = Driver.get().findElement(By.xpath("((//table)[3]//th)[" + tableElements.size() + "]")).getText();

    Driver.get().findElement(By.xpath("((//table)[3]//button)["+tableElements.size()+"]")).click();
    BrowserUtils.waitForClickablility(adminOptionsPage.optionsSure_DeleteYESButton, 1);      // delete button click
    adminOptionsPage.optionsSure_DeleteYESButton.click();

    System.out.println(lastRoleText);
  }


  @Then("The ROLE Value needs to be no more listed on OptionsPage")
  public void the_ROLE_Value_needs_to_be_no_more_listed_on_OptionsPage() {

    List<WebElement> table3Rows = Driver.get().findElements(By.xpath("((//table)[3])//th"));



  }



}
