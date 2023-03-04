package com.qugem.stepDefinitions;

import com.qugem.pages.AUTO.AutoPage;
import com.qugem.utilities.BrowserUtils;
import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import java.util.List;
import static org.junit.Assert.*;
public class AssignVehicleStepDef {

  AutoPage autoPage = new AutoPage();

  // Verify veraibles
  public String verifyPlate;
  public String firstDriverExpected;
  public String secondDriverExpected;
  public String startDateExpected;
  public String KMExpected;

  @And("Click the usage button of {string}")
  public void click_the_usage_button_of(String plate) {
    verifyPlate = plate;
    List<WebElement> rowElements = autoPage.getRowElements(9, 1, plate);
    if (rowElements.get(5).getText().equals("In Benutzung") || rowElements.get(5).getText().equals("Kullanımda")) {
      fail("The Vehicle is being used");
    } else {
      rowElements.get(6).findElement(By.xpath(".//button")).click();
    }
  }

  @And("Click the add button")
  public void click_the_add_button() {
    BrowserUtils.waitForClickablility(autoPage.addAssigneeButton, 2);
    autoPage.addAssigneeButton.click();
  }

  @And("Select {string}") // firstDriver Select step
  public void select(String firstDriver) {
    firstDriverExpected = firstDriver;
    if (!firstDriver.isEmpty()) {
      autoPage.firstDriverInputBox.click();
      autoPage.firstDriverInputBox.sendKeys(firstDriver, Keys.ARROW_DOWN, Keys.ENTER);
    }
  }

  @And("Select if not null {string}")
  public void select_if_not_null(String secondDriver) {
    secondDriverExpected = secondDriver;
    if (!secondDriver.isEmpty()) {
      autoPage.secondDriverInputBox.sendKeys(secondDriver, Keys.ARROW_DOWN, Keys.ENTER);
    }
  }

  @And("Update date{string}")
  public void update_date(String startDate) {
    if (!startDate.isEmpty()) {
      autoPage.startDateInputBox.sendKeys(startDate);
    }
    startDateExpected = startDate;
  }

  @And("Update if its not null {string}")
  public void update_if_its_not_null(String startKM) {
    if (!startKM.isEmpty()) {
      KMExpected = startKM;
      String keysPressed = Keys.chord(Keys.CONTROL, "A");
      autoPage.startKMInputBox.sendKeys(keysPressed);
      autoPage.startKMInputBox.sendKeys(startKM);
    } else {
      KMExpected = autoPage.startKMInputBox.getAttribute("value") + " km";
    }
  }

  @And("Click Submit Button")
  public void click_Submit_Button() {
    autoPage.submitButton.click();
  }

  @Then("Assignee info should be listed on PopUP")
  public void assignee_info_should_be_listed_on_PopUP() {
    String header = autoPage.popUpHeader.getText();
    String actualPlate = header.substring(0, header.indexOf('-')).trim();

    assertEquals("Verify Plate", verifyPlate, actualPlate);
    List<WebElement> popUpRowElements = autoPage.getRowElements(8, 1, "1.");
    String actualDriverNames = popUpRowElements.get(1).getText();

    assertEquals(
        "Verify first and second driver names",
        firstDriverExpected + " , " + secondDriverExpected,
        actualDriverNames);

    assertEquals("Verify the EndDate", popUpRowElements.get(3).getText(), "In Benutzung");

    StringBuilder kmActualWithoutPoint = new StringBuilder(popUpRowElements.get(4).getText());
    kmActualWithoutPoint.deleteCharAt(kmActualWithoutPoint.indexOf("."));
    StringBuilder kmExpectedWithoutPoint = new StringBuilder(KMExpected);
    assertEquals("Verify StartKilometer", kmExpectedWithoutPoint, kmActualWithoutPoint);

    assertTrue("Verify Bearbeiten Button", popUpRowElements.get(5).isDisplayed());
    assertTrue("Verify Löschen Button", popUpRowElements.get(6).isDisplayed());
  }

  @Then("Assignee info should be listed on Table")
  public void assignee_info_should_be_listed_on_Table() {

    /*
    Navigate to Vehicles page again - so refreshed
    search the table for plate using-->(getrowElements method)
    founded row verify with
    in benutzung (End KM)
    firstDriver / second
    Start DAte
    Start Km
     */

  }

  @Then("Vehicle with {string} should be InUse")
  public void vehicle_with_should_be_InUse(String plate) {}

  @Then("Assigned Drivers should be listed on table")
  public void assigned_Drivers_should_be_listed_on_table() {}
}
