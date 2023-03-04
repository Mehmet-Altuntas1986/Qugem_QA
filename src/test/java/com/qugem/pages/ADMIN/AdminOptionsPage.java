package com.qugem.pages.ADMIN;

import com.qugem.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class AdminOptionsPage extends BasePage {


  @FindBy(xpath = "(//input)[1]")
  public WebElement companyInput;

  @FindBy(xpath = "(//input)[2]")
  public WebElement roleInput;

  @FindBy(xpath = "(//input)[3]")
  public WebElement taxFreeInput;

  @FindBy(xpath = "(//input)[4]")
  public WebElement flatRateTaxInput;

  @FindBy(xpath = "(//input)[5]")
  public WebElement perKmFeeInput;

  @FindBy(xpath = "(//input)[6]")
  public WebElement fixedPayRateInput;

  @FindBy(
      xpath =
          "(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[1]")
  public WebElement addClientButton;

  @FindBy (xpath = "(//input[@id='name'])[1]") public WebElement newClientName;
  @FindBy (xpath = "(//input[@id='name'])[2]") public WebElement newClientPLZ;
  @FindBy (xpath = "(//input[@id='name'])[3]") public WebElement newClientCity;
  @FindBy (xpath = "(//input[@id='name'])[4]") public WebElement newClientAddress;
  @FindBy(xpath = "//div[@id='demo-simple-select-outlined']")  public WebElement landOptionButton;

  @FindBy (xpath = "//button[@type='submit']") public WebElement submitNewClient;





  @FindBy(
      xpath =
          "(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[2]")
  public WebElement addCompanyButton;

  @FindBy(
      xpath =
          "(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[3]")
  public WebElement addRoleButton;

  @FindBy(
      xpath =
          "(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[4]")
  public WebElement addFixedRatesButton;




  // 1_Land options Locators -10
  @FindBy(xpath = "//li[@data-value='Baden-Württemberg']")
  public WebElement landOpt1;

  @FindBy(xpath = "//li[@data-value='Bayern']")
  public WebElement landOpt2;

  @FindBy(xpath = "//li[@data-value='Berlin']")
  public WebElement landOpt3;

  @FindBy(xpath = "//li[@data-value='Brandenburg']")
  public WebElement landOpt4;



  @FindBy(xpath = "//li[@data-value='Bremen']")
  public WebElement landOpt5;

  @FindBy(xpath = "//li[@data-value='Hamburg']")
  public WebElement landOpt6;

  @FindBy(xpath = "//li[@data-value='Hessen']")
  public WebElement landOpt7;

  @FindBy(xpath = "//li[@data-value='Mecklenburg-Vorpommern']")
  public WebElement landOpt8;

  @FindBy(xpath = "//li[@data-value='Niedersachsen']")
  public WebElement landOpt9;

  @FindBy(xpath = "//li[@data-value='Nordrhein-Westfalen']")
  public WebElement landOpt10;

  @FindBy(xpath = "//li[@data-value='Rheinland-Pfalz']")
  public WebElement landOpt11;

  @FindBy(xpath = "//li[@data-value='Saarland']")
  public WebElement landOpt12;

  @FindBy(xpath = "//li[@data-value='Sachsen']")
  public WebElement landOpt13;

  @FindBy(xpath = "//li[@data-value='Sachsen-Anhalt']")
  public WebElement landOpt14;

  @FindBy(xpath = "//li[@data-value='Schleswig-Holstein']")
  public WebElement landOpt15;

  @FindBy(xpath = "//li[@data-value='Thüringen']")
  public WebElement landOpt16;



  @FindBy (xpath = "(//tbody//tr[1]/td[1])[1]") public WebElement verifyClientName;     // use this to verify new client creation and also delete client
  @FindBy (xpath = "(//tbody//tr[1]/td[2])[1]") public WebElement verifyClientAddress;
  @FindBy (xpath = "(//tbody//tr[1]/td[3])[1]") public WebElement verifyClientPLZ;
  @FindBy (xpath = "(//tbody//tr[1]/td[4])[1]") public WebElement verifyClientLand;
  @FindBy (xpath = "(//tbody//tr[1]/td[5])[1]") public WebElement verifyClientCity;

  @FindBy (xpath = "(//button[@class='MuiButtonBase-root MuiButton-root MuiButton-contained MuiButton-containedPrimary'])[5]") public WebElement optionsSure_DeleteYESButton;



}
