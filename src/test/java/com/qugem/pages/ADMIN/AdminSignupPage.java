package com.qugem.pages.ADMIN;

import com.qugem.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminSignupPage extends BasePage {

  @FindBy(css = "[name=username]")
  public WebElement userNameInput;

  @FindBy(css = "[name=email]")
  public WebElement emailInput;

  @FindBy(css = "[name=password]")
  public WebElement passwordInput;

  @FindBy(css = "[name=confirmPassword]")
  public WebElement confirmPasswordInput;

  @FindBy(css = "div#company")
  public WebElement companyRadioSelect;

  @FindBy(xpath = "//button[@type='submit']")
  public WebElement addUserSubmit;

  @FindBy(xpath = "(//li/span)[1]")
  public WebElement companyCheckbox1;

  @FindBy(xpath = "(//li/span)[2]")
  public WebElement companyCheckbox2;

  @FindBy(xpath = "(//li/span)[3]")
  public WebElement companyCheckbox3;

  @FindBy(xpath = "(//li/span)[4]")
  public WebElement companyCheckbox4;

  @FindBy(xpath = "(//li/span)[5]")
  public WebElement companyCheckbox5;
}
