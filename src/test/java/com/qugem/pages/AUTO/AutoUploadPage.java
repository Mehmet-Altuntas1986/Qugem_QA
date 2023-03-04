package com.qugem.pages.AUTO;

import com.github.javafaker.Faker;
import com.mongodb.client.model.Facet;
import com.qugem.pages.BasePage;
import com.qugem.utilities.BrowserUtils;
import com.qugem.utilities.Driver;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.management.StringValueExp;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.IntStream;

public class AutoUploadPage extends BasePage {

  // Only Admin USER can Add New Vehicle !

  public AutoUploadPage() {
    PageFactory.initElements(Driver.get(), this);
  }

  WebElement newAutoType;
  Map<String, String> newAutoInfo = new HashMap<>();

  // INPUT PAGE LOCATORS (TO FILL THE VALUES FOR NEW CAR)
  @FindBy(xpath = "//input[@name='plate']")
  public WebElement plateInput;

  @FindBy(xpath = "//input[@name='brand']")
  public WebElement brandInput;

  @FindBy(xpath = "//input[@name='model']")
  public WebElement modelInput;

  @FindBy(xpath = "//input[@name='year_buy']")
  public WebElement yearBuyInput;

  @FindBy(xpath = "//input[@name='year_production']")
  public WebElement yearProductionInput;

  @FindBy(xpath = "//input[@name='purchasePrice']")
  public WebElement purchasePriceInput;

  @FindBy(xpath = "//input[@name='currentKm']")
  public WebElement currentKmInput;

  // SELECT MENU  -- No Select Tag
  @FindBy(xpath = "//*[@id='mui-component-select-type']")
  public WebElement selectType;
  // select options
  @FindBy(xpath = "//*[@*='LKW']")
  public WebElement typeLKW;

  @FindBy(xpath = "//*[@*='Sattle']")
  public WebElement typeSATTLE;

  @FindBy(xpath = "//*[@*='Sprinter']")
  public WebElement typeSPRINTER;

  @FindBy(css = "[type='submit']")
  public WebElement submitButton;

  // Filling a Map for creation (use the value coming from feature file)
  public void createNewAuto() {
    int year= getYear();
    newAutoInfo.put("Plate", getPlate());
    newAutoInfo.put("Brand", getBrand());      //  ints1[1][1]
    newAutoInfo.put("Model", getModel());      //  ints1[1][2]
    newAutoInfo.put("YearProduction", String.valueOf(year));
    newAutoInfo.put("YearBuy", String.valueOf(year+2));
    newAutoInfo.put("CurrentKM",  String.valueOf(getKM()));
    newAutoInfo.put("PurchasePrice", String.valueOf(getPrice()));


    switch (getType()) { // type
      case "LKW":
        newAutoType = typeLKW;
        newAutoInfo.put("Type", "LKW");
        break;
      case "Sattle":
        newAutoType = typeSATTLE;
        newAutoInfo.put("Type", "Sattle");
        break;
      case "Sprinter":
        newAutoType = typeSPRINTER;
        newAutoInfo.put("Type", "Sprinter");
        break;
    }
  }

  public Map<String, String> getNewAutoInfoMap() {
    return newAutoInfo;
  }

  public WebElement getAutoTypeElement() {
    return newAutoType;
  }

  Random rand = new Random();
  Faker fake = new Faker();

  public String getPlate() {
    String plate;
    // city code
    List<String> givenList =
        Arrays.asList("S", "LB", "KO", "MA", "HD", "LEO", "HN", "M", "N", "SCH");
    String cityCode = givenList.get(rand.nextInt(givenList.size()));
    // 2 char middle
    String middle = Character.toString(getOneChar()) + Character.toString(getOneChar());
    // 3 digit lastPart
    String lastPlatePart = String.valueOf(fake.number().randomNumber(3, true));
    plate = cityCode + " " + middle + " " + lastPlatePart;
    return plate;
  }
  public static char getOneChar() {
    Random rd = new Random();
    String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    char myChar = abc.charAt(rd.nextInt(abc.length()));
    return myChar;
  }


  public String getBrand() {
    String brand;
    List<String> givenList =
        Arrays.asList(
            "FORD", "IVECO", "MERCEDES", "TESLA", "VOLVO", "SCANIA", "MAGIRUS", "RENAULT");
    brand = givenList.get(rand.nextInt(givenList.size()));
    return brand;
  }

  public String getModel() {
    String model;
    List<String> givenList =
        Arrays.asList("Actors", "1500", "Active", "TeslaTruck", "5500", "4560", "Kirkayak");
    model = givenList.get(rand.nextInt(givenList.size()));
    // List<Array> brand_ModelList = {["Volvo","5000"], ["Mercedes","Actors"]
    // ["Mercedes","Active"]};
    return model;
  }

  public int getYear() {
    int a = rand.nextInt(20) + 2000;
    return a;
  }

  /*  public int getYear2(int yp){
    int yearBuy;
    int a = fake.number().numberBetween(2022-yp);
    return yearBuy;
  }*/
  public int getKM() {
    int km = rand.nextInt(19)*10000;
    return km;
  }

  public int getPrice() {
/*    IntStream ints = rand.ints(500, 20000, 80000);
    int[] ints1 = ints.toArray();
    int price = ints1[rand.nextInt(ints1.length)]*/;
    int price = rand.nextInt(100+50)*2000;

    return price;
  }

  public String getType() {
    List<String> givenList = Arrays.asList("LKW", "Sattle", "Sprinter");
    String type = givenList.get(rand.nextInt(givenList.size()));
    return type;
  }
}
