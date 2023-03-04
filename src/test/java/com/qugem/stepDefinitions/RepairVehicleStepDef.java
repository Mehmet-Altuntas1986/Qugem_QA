package com.qugem.stepDefinitions;

import com.qugem.pages.AUTO.AutoPage;
import com.qugem.utilities.BrowserUtils;
import com.qugem.utilities.ConfigurationReader;
import com.qugem.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.fail;

public class RepairVehicleStepDef {

    AutoPage autoPage = new AutoPage();
    Actions action = new Actions(Driver.get());
    public static final String START_DATE = "01/03/2022";
    private static final String PLATE = ConfigurationReader.get("testPlate");
    public static final String DETAIL_TEXT = "Q205 Repair Details " + PLATE;
    public static int REPAIR_KM;

    @When("Click the Repair button of {string} to end the usage")
    public void click_the_Repair_button_of_to_end_the_usage(String plate) {

        List<WebElement> rowElements = autoPage.getRowElements(9, 1, PLATE);
        if (rowElements.get(5).getText().equals("Boşta") || rowElements.get(5).getText().equals("Leerlauf")) {
            rowElements.get(7).findElement(By.xpath(".//button")).click();
        } else {
            fail("A new repair cannot be open The Vehicle is inUse or already has a opened repair process.. ");
        }
    }

    @When("Click the addButton")
    public void click_the_addButton() {
        BrowserUtils.waitForClickablility(autoPage.addRepairButton, 1);
        autoPage.addRepairButton.click();
    }

    @When("Update startDate")
    public void update_startDate() {
        BrowserUtils.waitFor(2);
        autoPage.startDateInputBox.sendKeys(START_DATE);
    }

    @When("Update KM")
    public void update_KM() {
        REPAIR_KM = Integer.parseInt(autoPage.repairKMInputBox.getAttribute("value"));
    }

    @When("Enter Repair details")
    public void enter_Repair_details() {
        BrowserUtils.waitForVisibility(autoPage.detailsInputBox, 1);
        autoPage.detailsInputBox.sendKeys(DETAIL_TEXT);
    }

    @Then("Repair info should be listed on PopUP with Edit Delete")
    public void repair_info_should_be_listed_on_PopUP_with_Edit_Delete() throws ParseException {
        List<WebElement> rowElements = autoPage.getRowElements(7, 3, "In Reparatur");
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(START_DATE);
        SimpleDateFormat formatter = new SimpleDateFormat("dd. MMMM yyyy", Locale.GERMAN);
        String strDate = formatter.format(date);
        Assert.assertEquals(strDate, rowElements.get(1).getText());
        Assert.assertEquals("In Reparatur", rowElements.get(2).getText());
        Assert.assertEquals(REPAIR_KM + " km", rowElements.get(3).getText());
        Assert.assertEquals("0 €", rowElements.get(4).getText());
        Assert.assertEquals(DETAIL_TEXT, rowElements.get(5).getText());
    }

    @Then("Close Repair Menu")
    public void close_Repair_Menu() {
        // close pop up
        action.sendKeys(Keys.ESCAPE).build().perform();
    }

    @Then("The Vehicles status should be InRepair in Auto page")
    public void the_Vehicles_status_should_be_InRepair_in_Auto_page() {
        // verify the vehicle status on auto page
        List<WebElement> rowElements = autoPage.getRowElements(9, 1, PLATE);
        Assert.assertEquals("In Reparatur", rowElements.get(5).getText());
    }
}
