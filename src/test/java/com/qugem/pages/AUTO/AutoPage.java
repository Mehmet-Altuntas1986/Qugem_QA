package com.qugem.pages.AUTO;

import com.qugem.pages.BasePage;
import com.qugem.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AutoPage extends BasePage {

  public AutoPage() {
    PageFactory.initElements(Driver.get(), this);
  }

  @FindBy(xpath = "//span[@class='MuiFab-label']")
  public WebElement addAutoButton;

  @FindBy(xpath = "(//button[@aria-label='add'])[2]/span")
  public WebElement addAssigneeButton;

  @FindBy(xpath = "(//div[4]/div[3]/div/div[2]//button)[1]")
  public WebElement assignEditButton;

  @FindBy(xpath = "//*[@class='MuiTableRow-root MuiTableRow-head']")
  public WebElement tableHead;

  @FindBy (xpath = "//td/button") public WebElement firstUsageButton;



  ////////////////// Nutzung Locators////

  @FindBy(xpath = "//th[6]/span")
  public WebElement statusFilter;

  @FindBy(xpath = "//tbody/tr[1]/td[7]//button")
  public WebElement firstFreeUsageButton;

  @FindBy (xpath = "(//input[@spellcheck='false'])[1]")
  public WebElement firstDriverInputBox;

  @FindBy (xpath = "(//input[@spellcheck='false'])[2]")
  public WebElement secondDriverInputBox;

  @FindBy (xpath = "//input[@name='deliveryDate']")
  public WebElement startDateInputBox;

  @FindBy (xpath = "//input[@name='startKM']")
  public WebElement startKMInputBox;

  @FindBy (xpath = "//input[@name='repairKM']")
  public WebElement repairKMInputBox;

  @FindBy (xpath = "//button[@type='submit']")
  public WebElement submitButton;

  @FindBy (xpath = "(//input[@placeholder='Filter'])[1]")
  public WebElement plateFilterInputBox;

  @FindBy(xpath = "//h2")
  public WebElement popUpHeader;


  @FindBy (xpath = "//input[@name='returnDate']")
  public WebElement returnDateInputBox;

  @FindBy (xpath = "//input[@name='endKM']")
  public WebElement endKMInputBox;

  @FindBy(xpath = "((//table)[2]//tr)[2]/td[7]")
  public WebElement distanceAfterReturn;

  //////// Repair
  @FindBy(xpath = "(//span[@class='MuiFab-label'])[2]")
  public WebElement addRepairButton;

  @FindBy(xpath = "//input[@name='details']")
  public WebElement detailsInputBox;



  //(//button[@aria-label='add'])[2]/span

}
