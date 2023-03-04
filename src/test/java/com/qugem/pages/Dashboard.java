package com.qugem.pages;

import com.mongodb.client.model.Filters;
import com.qugem.utilities.BrowserUtils;
import com.qugem.utilities.Driver;
import org.bson.conversions.Bson;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Dashboard extends BasePage {
    public Dashboard() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall'])[1]")
    public WebElement sprinterExpandRow;

    @FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall'])[2]")
    public WebElement lkwExpandRow;

    @FindBy(xpath = "(//button[@class='MuiButtonBase-root MuiIconButton-root MuiIconButton-sizeSmall'])[3]")
    public WebElement sattleExpandRow;

    public List<String> getValuesOfVehicleType(String vehicleType) {
        try {
            return BrowserUtils.getElementsText(Driver.get().findElements(By.xpath("//*[text() = '" + vehicleType + "']/../td")));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public List<WebElement> getVehiclesPlateAndDriverList(String vehicleType, String status) {
        int typeLocatorValue = 0;
        int statusLocatorValue = 0;
        if (vehicleType.equals("Sprinter")) typeLocatorValue = 2;
        if (vehicleType.equals("LKW")) typeLocatorValue = 5;
        if (vehicleType.equals("Sattle")) typeLocatorValue = 8;
        if (status.equals("idle")) statusLocatorValue = 1;
        if (status.equals("inUse")) statusLocatorValue = 2;
        if (status.equals("inRepair")) statusLocatorValue = 3;
        String locator = "((//tbody/tr)[" + typeLocatorValue + "]//tbody//td)[" + statusLocatorValue + "]/p";
        try{
            return Driver.get().findElements(By.xpath(locator));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Bson getFilterInAutosCollection(String vehicleType, String status) {
        String statusLocatorValue = "";
        if (status.equals("idle")) statusLocatorValue = "idle";
        if (status.equals("inUse")) statusLocatorValue = "in_use";
        if (status.equals("inRepair")) statusLocatorValue = "in_repair";
        return Filters.and(Filters.eq("type", vehicleType), Filters.eq("status", statusLocatorValue));
    }

    public String getEmployeeCountOrPercentage(String position,String returnValue){
        int positionLocatorValue = 0;
        int returnValueLocatorValue = 2;
        if (returnValue.equals("EmployeeCount")) returnValueLocatorValue = 1;
        if (position.equals("Present")) positionLocatorValue = 7;

        String locator = "((//tbody/tr)[" + positionLocatorValue + "]//tbody//td)[" + returnValueLocatorValue + "]/p";
        try{
            return Driver.get().findElement(By.xpath(locator)).getText();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}