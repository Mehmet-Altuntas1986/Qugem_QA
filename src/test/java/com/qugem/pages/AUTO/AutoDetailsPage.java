package com.qugem.pages.AUTO;

import com.qugem.pages.BasePage;
import com.qugem.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AutoDetailsPage extends BasePage {

  public AutoDetailsPage() {
    PageFactory.initElements(Driver.get(), this);
  }

  // Auto Detail Page Locators

  @FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body'])[1]")
  public WebElement detailPlate;

  @FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body'])[2]")
  public WebElement detailBrand;

  @FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body'])[3]")
  public WebElement detailModel;

  @FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body'])[4]")
  public WebElement detailType; // art

  @FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body'])[6]")
  public WebElement detailYearBuy;

  @FindBy(xpath = "(//td[@class='MuiTableCell-root MuiTableCell-body'])[5]")
  public WebElement detailProductYear;
}
