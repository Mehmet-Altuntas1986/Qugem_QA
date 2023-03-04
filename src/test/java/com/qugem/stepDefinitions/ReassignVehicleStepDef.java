package com.qugem.stepDefinitions;

import com.qugem.pages.AUTO.AutoPage;
import com.qugem.utilities.BrowserUtils;
import com.qugem.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

import static org.junit.Assert.fail;

public class ReassignVehicleStepDef {

    AutoPage autoPage= new AutoPage();
    Actions action = new Actions(Driver.get());
    String plateVerify;
    @Given("Click the usage button of {string} to end the usage")
    public void click_the_usage_button_of_to_end_the_usage(String plate) {

        List<WebElement> rowElements = autoPage.getRowElements(9, 1, plate);
        if (rowElements.get(5).getText().equals("Boşta") || rowElements.get(5).getText().equals("Leerlauf"))
        {
            fail("The Vehicle is NOT being used");
        } else {
            rowElements.get(6).findElement(By.xpath(".//button")).click();
        }
        plateVerify=plate;
    System.out.println("plateVerify = " + plateVerify);
    }

    @Given("Click the last records Edit Button")
    public void click_the_last_records_Edit_Button() {
        BrowserUtils.waitForClickablility(autoPage.assignEditButton,1);
        autoPage.assignEditButton.click();
    }

    @Given("Update endDate")
    public void update_endDate() {
        DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN);
        LocalDate date = LocalDate.now();
        String format = date.format(germanFormatter);

        autoPage.returnDateInputBox.sendKeys(format);
    }

    int distance=325;
    @Given("Update endKM")
    public void update_endKM() {
        String kmStr = autoPage.startKMInputBox.getAttribute("value");
        int firstKM= Integer.parseInt(kmStr);
        String endKMStr= String.valueOf(firstKM+distance);
        //String.valueOf(Integer.parseInt(autoPage.startDateInputBox.getAttribute("value")) +500)
        autoPage.endKMInputBox.sendKeys(endKMStr);
    }

    @Given("Click to Submit Button")
    public void click_to_Submit_Button() {
      //  BrowserUtils.waitFor(2);
        autoPage.submitButton.click();
    }

    @Then("Return info should be listed on PopUP with Distance")
    public void return_info_should_be_listed_on_PopUP_with_Distance() {

        BrowserUtils.waitForVisibility(autoPage.distanceAfterReturn,1);
        BrowserUtils.waitFor(1);
        String text = autoPage.distanceAfterReturn.getText();
        String substring = text.substring(0, text.indexOf(" "));
        Assert.assertEquals(distance,Integer.parseInt(substring));
    }

    @Then("Close usage dialog")
    public void close_usage_dialog() {
        // close pop up
        action.sendKeys(Keys.ESCAPE).build().perform();
    }
    @Then("The Vehicles status should be Free in Auto page for {string}")
    public void the_Vehicles_status_should_be_Free_in_Auto_page_for(String plate) {
        // check the status is Free or not  --- Attribute  >> status = "in_use"   - "idle"
        // autoPage.popUpHeader.sendKeys(Keys.ESCAPE);
        List<WebElement> afterReturnList = autoPage.getRowElements(9, 1, plate);
        String text = afterReturnList.get(5).getText();
        Assert.assertEquals(text,"Leerlauf");  // if it goes in Turkish??
        System.out.println(afterReturnList.get(5).getText());
        System.out.println(afterReturnList.get(5).getAttribute("status")); // here comes null


    }



}
