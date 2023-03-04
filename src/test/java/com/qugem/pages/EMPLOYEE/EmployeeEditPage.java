package com.qugem.pages.EMPLOYEE;

import com.qugem.pages.BasePage;
import com.qugem.utilities.BrowserUtils;
import com.qugem.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EmployeeEditPage extends BasePage {

  public ArrayList<String> cities = new ArrayList<>();
  public ArrayList<String> dispatchers = new ArrayList<>();

  public EmployeeEditPage() {
    cities.add("Stuttgart");
    cities.add("Ludwigsburg");
    cities.add("Koblenz");
    cities.add("Heilbronn");
    cities.add("München");
    dispatchers.add("Kenan Bey");
    dispatchers.add("Haci Bey");
    dispatchers.add("Polat Bey");
    dispatchers.add("Aysun Hanim");
  }

  @FindBy(xpath = "(//button[@type='submit'])[2]")
  public WebElement submitButton;

  @FindBy(xpath = "(//button)[7]")
  public WebElement salaryCalculateButton;

  // Select options Locators
  @FindBy(xpath = "(//*[@id='demo-simple-select-outlined'])[1]")
  public WebElement landSelect; // 8     1 Bundesland - Mandatory

  @FindBy(xpath = "(//*[@id='demo-simple-select-outlined'])[2]")
  public WebElement companySelect; // 11    2 Werk / Sirket Mandatory

  @FindBy(xpath = "(//*[@id='demo-simple-select-outlined'])[3]")
  public WebElement roleSelect; // 12    3 Tätigkeit / Rolü Mandatory

  @FindBy(xpath = "(//*[@id='demo-simple-select-outlined'])[4]")
  public WebElement clientSelect; // 13    4 Auftraggeber / sip.Sahi.Fir Mandatory

  @FindBy(xpath = "(//*[@id='demo-simple-select-outlined'])[5]")
  public WebElement salaryTypSelect; // 16    5 Lohnart / MaasTürü Mandatory

  @FindBy(xpath = "(//*[@id='demo-simple-select-outlined'])[6]")
  public WebElement taxClassSelect; // 17    6 Steuerklasse / VergDilmi NotMANDATORY

  @FindBy(xpath = "(//*[@id='demo-simple-select-outlined'])[7]")   // At verify this locator is not working
  public WebElement childNumberDropbox; // 20    7 ChildNumberDropBox

  @FindBy(xpath = "(//*[@id='demo-simple-select-outlined'])[7]")
  public WebElement churchSelectVerification;

  @FindBy(xpath = "(//*[@id='demo-simple-select-outlined'])[8]")
  public WebElement churchSelect; // 20    8 Kirshesteuer / kiliseVergi Mandatory

  @FindBy(xpath = "//input[@id='health-select']")
  public WebElement healtInsuranceSelect;


  /**
   * Input box method -- This method is merging the parameter with locator here we use Driver
   * findElm method *
   */
  public WebElement getInputBox(String label) {
    String locator = "[name=" + label + "]";
    return Driver.get().findElement(By.cssSelector(locator));
  }

  /** This method is selecting an Option for each selectBox and returning the selectiona as a String (for Verify) */
  public String clickAndReturnRandomOption(){
    List<WebElement> elements = Driver.get().findElements(By.xpath("//*[@id='menu-']/div[3]/ul/li"));
    WebElement element = elements.get(new Random().nextInt(elements.size()));
    BrowserUtils.waitForClickablility(element,3);
    String elementText = element.getText();
    element.click();
    return elementText;
  }

  /** This method is changing randomly additional fee checkBoxes and returning a boolean value for verify */
  public boolean selectRandomAdditionalFees(int i){
    Random random = new Random();
    WebElement element = Driver.get().findElement(By.xpath("(//input[@type='checkbox'])["+i+"]"));
      if (random.nextBoolean()){
        element.click();
        return true;
      }else {
        return false;
      }
  }

}
