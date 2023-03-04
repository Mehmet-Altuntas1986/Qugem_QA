package com.qugem.stepDefinitions;

import com.qugem.utilities.BrowserUtils;
import com.qugem.utilities.Driver;
import com.qugem.utilities.MongoDBUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

public class Hooks {

  @Before
  public void setUp() {
    Driver.get().manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); // is that working non dynamic??
    Driver.get().manage().window().maximize();
    WebElement html = Driver.get().findElement(By.tagName("html"));
    html.sendKeys(Keys.chord(Keys.CONTROL, "0")); // WHAT IS THAT FOR !!
  }

  @After
  public void tearDown(Scenario scenario) {
    if (scenario.isFailed()) {
      final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
      scenario.attach(screenshot, "image/png", "screenshot");
    }

    BrowserUtils.waitFor(2);
    //Driver.closeDriver();
  }
  @Before("@MongoDB")
  public void setUpdb(){
    System.out.println("\tconnecting to database...");
    //create connection to db
    MongoDBUtils.createConnection();
  }

}
