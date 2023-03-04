package com.qugem.pages;

import com.qugem.utilities.BrowserUtils;
import com.qugem.utilities.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class BasePage {

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

  @FindBy(xpath = "(//*[@class='MuiIconButton-label'])[5]")
  public WebElement nextPageButton;

  public BasePage() {
    PageFactory.initElements(Driver.get(), this);
  }

  @FindBy(xpath = "//span[.='Dashboard']")
  public WebElement dashboardTab;

  @FindBy(xpath = "//*[@href='/employee']") // "//*[@href='/salary']"
  public WebElement employeePageTab;

  @FindBy(xpath = "//*[@href='/employee/attendance']")
  public WebElement attendancePageTab;

  @FindBy(xpath = "//*[@href='/salary']")
  public WebElement salaryPageTab;

  @FindBy(xpath = "(//*[@href='/auto'])[1]")
  public WebElement autoPageTab;

  @FindBy(xpath = "//span[.='Admin']")
  public WebElement adminTab;

  @FindBy(
      xpath =
          "(//*[@class=\"MuiTypography-root MuiListItemText-primary MuiTypography-body2 MuiTypography-displayBlock\"])[1]")
  public WebElement adminUsersModule;

  @FindBy(xpath ="//*[@class=\"MuiTypography-root MuiListItemText-primary MuiTypography-body2 MuiTypography-displayBlock\"]")
  public List<WebElement> modules;

  @FindBy(
      xpath =
          "(//*[@class=\"MuiTypography-root MuiListItemText-primary MuiTypography-body2 MuiTypography-displayBlock\"])[2]")
  public WebElement adminOptionsModule;

  @FindBy(
      xpath =
          "(//*[@class=\"MuiTypography-root MuiListItemText-primary MuiTypography-body2 MuiTypography-displayBlock\"])[3]")
  public WebElement adminHolidaysModule;

  @FindBy(
          xpath =
                  "(//*[@class=\"MuiTypography-root MuiListItemText-primary MuiTypography-body2 MuiTypography-displayBlock\"])[4]")
  public WebElement adminWithdrawalAmountsModule;

  @FindBy(xpath = "//span[.='Quickly GmbH']")
  public WebElement quicklyTab;

  @FindBy(xpath = "//span[.='Kinesis GPS']")
  public WebElement kinesisTab;

  @FindBy(xpath = "(//*[@class='MuiIconButton-label'])[1]") // Account select menu
  public WebElement accountButton;

  // profile

  @FindBy(xpath = "//*[.='Sign out']")
  public WebElement signOut;

  public void navigatePages(String tabName) {

    WebElement tab = dashboardTab;

    switch (tabName) {
      case "dashboard":
        tab = dashboardTab;
        break;
      case "employee":
        tab = employeePageTab;
        break;
      case "attendance":
        tab = attendancePageTab;
        break;
      case "salary":
        tab = salaryPageTab;
        break;
      case "auto":
        tab = autoPageTab;
        break;
      case "quickly":
        tab = quicklyTab;
        break;
      case "kinesis":
        tab = kinesisTab;
        break;
      case "adminUsers":
        tab = adminUsersModule;
        if (modules.size() == 0) adminTab.click();
        break;
      case "adminOptions":
        tab = adminOptionsModule;
        if (modules.size() == 0) adminTab.click();
        break;
      case "adminHolidays":
        tab = adminHolidaysModule;
        if (modules.size() == 0) adminTab.click();
        break;
      case "adminWithdrawalAmounts":
        tab = adminWithdrawalAmountsModule;
        if (modules.size() == 0) adminTab.click();
        break;
    }
    try {
      BrowserUtils.waitForClickablility(tab, 5);
      tab.click();
    } catch (Exception ignored) {}
  }

  public List<WebElement> getRowElements(int totalColumnNumber, int columnNumber, String key) {

    List<WebElement> listOfRowWebElements = new ArrayList<>();
    try {
      linesPerPageButton.click();
      BrowserUtils.waitForClickablility(linesPerPageOption10, 2);
      linesPerPageOption10.click();
    } catch (Exception ignored) {}

    label:
    do {
      BrowserUtils.waitFor(1);

      List<String> elementsTextList =BrowserUtils.getElementsText(By.xpath("//tbody/tr/td[" + columnNumber + "]"));

      for (int i = 1; i <= elementsTextList.size(); i++) {
        if (key.equals(elementsTextList.get(i - 1))) {
          BrowserUtils.scrollToElement(Driver.get().findElement(By.xpath("(//tbody/tr/td[" + columnNumber + "])[" + i + "]"))); // scroll handling
          for (int j = 1; j <= totalColumnNumber; j++) {
            listOfRowWebElements.add(Driver.get().findElement(By.xpath("(//tbody/tr/td[" + j + "])[" + i + "]")));
          }
          break label;
        }
      }
      try {
        nextPageButton.click();
      } catch (Exception e) {
        Assert.fail("No record of the related "+ key +" was found.");
      }
    } while (true);
    return listOfRowWebElements;
  }
}
