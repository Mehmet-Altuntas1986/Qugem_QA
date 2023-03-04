package com.qugem.pages.EMPLOYEE;

import com.qugem.pages.BasePage;
import com.qugem.utilities.Driver;
import org.openqa.selenium.By;

public class EmployeeDetailsPage extends BasePage {

    public String getInputBoxValue(String label) {
        String locator = "[name=" + label + "]";
        return Driver.get().findElement(By.cssSelector(locator)).getAttribute("value");
    }
 }
