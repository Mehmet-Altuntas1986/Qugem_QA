package com.qugem.pages.EMPLOYEE;

import com.qugem.pages.BasePage;
import com.qugem.utilities.BrowserUtils;
import com.qugem.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EmployeePage extends BasePage {
  public EmployeePage() {
    PageFactory.initElements(Driver.get(), this);
  }

  @FindBy(xpath = "(//button[@ class='MuiButtonBase-root MuiFab-root MuiFab-sizeSmall MuiFab-primary'])")
  public WebElement createNewEmployeeButton;

  public void clickOnNewEmployeeButton() {
    BrowserUtils.waitForClickablility(createNewEmployeeButton, 1);
    createNewEmployeeButton.click();
  }
}
