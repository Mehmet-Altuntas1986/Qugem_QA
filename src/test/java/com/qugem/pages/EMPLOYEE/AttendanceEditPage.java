package com.qugem.pages.EMPLOYEE;

import com.qugem.pages.BasePage;
import com.qugem.utilities.BrowserUtils;
import com.qugem.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AttendanceEditPage extends BasePage {

  public static final int UI_OFFSET = 2;

  @FindBy(xpath = "(//span[@class='MuiButton-label'])[1]")
  public WebElement submitButton;

  String expectedDayOption;

  public void dayEdit(int day, String opt) {
    expectedDayOption = opt;
    day += UI_OFFSET;
    Driver.get().findElement(By.xpath("(//*[@id='demo-simple-select'])[" + day + "]")).click();
    BrowserUtils.waitFor(1);
    Driver.get().findElement(By.xpath("//li[@data-value='" + opt + "']")).click();
    BrowserUtils.waitFor(1);
    submitButton.click();
  }

  public void dayVerify(int dayControl) {
    dayControl += UI_OFFSET;
    String text =
        Driver.get()
            .findElement(By.xpath("(//input[@class='MuiSelect-nativeInput'])[" + dayControl + "]"))
            .getAttribute("value");
    Assert.assertEquals("VERIFY UPDATE", expectedDayOption, text);
    System.out.println("optt EXPECTED = " + expectedDayOption);
    System.out.println("text ACTUAL   = " + text);
  }
}
