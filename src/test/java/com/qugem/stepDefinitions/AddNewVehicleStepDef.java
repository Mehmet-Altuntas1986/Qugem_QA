package com.qugem.stepDefinitions;

import com.github.javafaker.Faker;
import com.qugem.pages.AUTO.AutoDetailsPage;
import com.qugem.pages.AUTO.AutoPage;
import com.qugem.pages.AUTO.AutoUploadPage;
import com.qugem.utilities.BrowserUtils;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Keys;

public class AddNewVehicleStepDef {

  AutoPage autoPage = new AutoPage();
  AutoUploadPage autoUploadPage = new AutoUploadPage();
  AutoDetailsPage autoDetailsPage = new AutoDetailsPage();

  @Given("the user clicks add auto button")
  public void the_user_clicks_add_auto_button() {
    autoPage.addAutoButton.click();
  }

  Faker faker =new Faker();

  @Given("the user fills the form")
  public void the_user_fills_the_form() {
    autoUploadPage.createNewAuto();
    autoUploadPage.plateInput.sendKeys(autoUploadPage.getNewAutoInfoMap().get("Plate"));
    autoUploadPage.brandInput.sendKeys(autoUploadPage.getNewAutoInfoMap().get("Brand"));
    autoUploadPage.modelInput.sendKeys(autoUploadPage.getNewAutoInfoMap().get("Model"));
    autoUploadPage.yearBuyInput.sendKeys(autoUploadPage.getNewAutoInfoMap().get("YearBuy"));
    autoUploadPage.yearProductionInput.sendKeys(autoUploadPage.getNewAutoInfoMap().get("YearProduction"));
    autoUploadPage.currentKmInput.sendKeys(autoUploadPage.getNewAutoInfoMap().get("CurrentKM"));
    autoUploadPage.purchasePriceInput.sendKeys(Keys.BACK_SPACE , autoUploadPage.getNewAutoInfoMap().get("PurchasePrice"));

    autoUploadPage.selectType.click(); //  to open SelectBox for TYPE
    BrowserUtils.waitForClickablility(autoUploadPage.typeSATTLE, 3); //  wait until options clickability
    autoUploadPage.getAutoTypeElement().click(); //  click an option
    BrowserUtils.waitFor(1);
  }

  @When("the user clicks the save button")
  public void the_user_clicks_the_save_button() {
    autoUploadPage.submitButton.click();
  }

  @When("the user navigates to {string}")
  public void the_user_navigates_to(String tab) {
    BrowserUtils.waitForClickablility(autoPage.autoPageTab, 5);
    autoPage.navigatePages(tab);
  }

  @Then("the user should be able to see new auto information")
  public void the_user_should_be_able_to_see_new_auto_information() {

    BrowserUtils.waitForVisibility(autoDetailsPage.detailPlate,1); // without that wait test cannot reads the plate and gives error
                                                                                 // WHY IMPLICIT WAIT FROM HOOKS NOT WORKING ???

    Assert.assertEquals("Plate is not matching",autoUploadPage.getNewAutoInfoMap().get("Plate"), autoDetailsPage.detailPlate.getText());

    Assert.assertEquals(
        autoUploadPage.getNewAutoInfoMap().get("Brand"), autoDetailsPage.detailBrand.getText());

    Assert.assertEquals(
        autoUploadPage.getNewAutoInfoMap().get("Model"), autoDetailsPage.detailModel.getText());

    Assert.assertEquals(
        autoUploadPage.getNewAutoInfoMap().get("Type"), autoDetailsPage.detailType.getText());

    Assert.assertEquals(
        autoUploadPage.getNewAutoInfoMap().get("YearBuy"), autoDetailsPage.detailYearBuy.getText());

    Assert.assertEquals(
        autoUploadPage.getNewAutoInfoMap().get("YearProduction"),
        autoDetailsPage.detailProductYear.getText());
  }
}
