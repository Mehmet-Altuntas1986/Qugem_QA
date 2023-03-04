package com.qugem.pages.ADMIN;

import com.qugem.pages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminUsersPage extends BasePage {

    @FindBy(xpath = "//button[@aria-label='add']")
    public WebElement userAddButton;

}
