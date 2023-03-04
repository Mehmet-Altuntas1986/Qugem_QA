package com.qugem.pages.EMPLOYEE;

import com.qugem.pages.BasePage;
import com.qugem.utilities.BrowserUtils;
import com.qugem.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AttendancePage extends BasePage {

  @FindBy(xpath = "((//tr[@class='MuiTableRow-root MuiTableRow-hover'])[1]/td)[1]")
  public WebElement personalNumber;

  @FindBy(
      css =
          "[class='MuiSelect-root MuiSelect-select MuiTablePagination-select MuiSelect-selectMenu MuiInputBase-input']")
  public WebElement linesPerPageButton;

  @FindBy(xpath = "(//*[@role='option'])[1]") // [1] --> 5 , [2] --> 10 , [3] --> 25
  public WebElement linesPerPageOption10;

  @FindBy(xpath = "(//*[@role='option'])[2]") // [1] --> 5 , [2] --> 10 , [3] --> 25
  public WebElement linesPerPageOption25;

  @FindBy(xpath = "(//*[@role='option'])[3]") // [1] --> 5 , [2] --> 10 , [3] --> 25
  public WebElement linesPerPageOption50;

  @FindBy(xpath = "(//*[@class='MuiIconButton-label'])[4]")
  public WebElement nextPageButton;

  public void clickEditButton(String id) {
    clickEditButton(id, 5);
  }

  public void clickEditButton(String id, int locatorIndex) {

    // boolean next = true;
    try {
      linesPerPageButton.click();
      BrowserUtils.waitForClickablility(linesPerPageOption10, 2);
      linesPerPageOption50.click();
    } catch (Exception e) {
      e.printStackTrace();
    }
    label:
    do {
      BrowserUtils.waitFor(1); // stale element
      // BrowserUtils.waitForStaleElement(Driver.get().findElement(By.xpath("(//tbody/tr/td)[1]")));
      List<String> elementsTextList = BrowserUtils.getElementsText(By.xpath("//tbody/tr/td[1]"));

      for (int i = 1; i <= elementsTextList.size(); i++) {
        if (id.equals(elementsTextList.get(i - 1))) {
          BrowserUtils.scrollToElement(Driver.get().findElement(By.xpath("(//tbody/tr/td[" + locatorIndex + "])[" + i + "]"))); // scroll handling
          Driver.get().findElement(By.xpath("(//tbody/tr/td[" + locatorIndex + "])[" + i + "]")).click();
          break label;
        }
      }
      try {
        nextPageButton.click();
      } catch (Exception e) {
        Assert.fail("No record of the related EmployeeId was found.");
      }
    } while (true);
  }

  @FindBy(
      css =
          "[class=\"MuiTypography-root MuiTablePagination-caption MuiTypography-body2 MuiTypography-colorInherit\"]")
  public WebElement pageNumInfo;
}
