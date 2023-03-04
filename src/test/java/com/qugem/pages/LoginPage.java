package com.qugem.pages;

import com.qugem.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

  public LoginPage() {
    PageFactory.initElements(Driver.get(), this);
  }

  @FindBy(xpath = "//input[@name='email']")
  public WebElement userName;

  @FindBy(xpath = "//input[@name='password']")
  public WebElement password;

  @FindBy(xpath = "(//span[@class='MuiButton-label'])[1]")
  public WebElement submit;

  public void login(String userNameStr, String passwordStr) {
    userName.sendKeys(userNameStr);
    password.sendKeys(passwordStr);
    submit.click();
  }
}
