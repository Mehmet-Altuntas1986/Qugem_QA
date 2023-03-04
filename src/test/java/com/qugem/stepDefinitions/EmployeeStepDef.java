package com.qugem.stepDefinitions;

import com.github.javafaker.Faker;
import com.qugem.pages.EMPLOYEE.EmployeeDetailsPage;
import com.qugem.pages.EMPLOYEE.EmployeeEditPage;
import com.qugem.pages.EMPLOYEE.EmployeePage;
import com.qugem.utilities.BrowserUtils;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Random;

public class EmployeeStepDef {
  Faker faker = new Faker();
  Random random = new Random();
  EmployeeEditPage employeeEditPage = new EmployeeEditPage();
  EmployeeDetailsPage employeeDetailsPage = new EmployeeDetailsPage();
  DateTimeFormatter germanFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.GERMAN);

  public String FIRST_NAME = faker.name().firstName();
  public String LAST_NAME = faker.name().lastName();
  public String EMPLOYEE_NUMBER = String.valueOf(faker.number().randomNumber(5,false));
  public String SOCIAL_SEC_NUMBER = String.valueOf(faker.number().numberBetween(100000000000L, 499999999999L));
  public String TAX_ID = String.valueOf(faker.number().numberBetween(50000000000L, 99999999999L));
  public String ADDRESS = employeeEditPage.cities.get(random.nextInt(employeeEditPage.cities.size()));
  public String POST_CODE = String.valueOf(faker.number().numberBetween(12555,89555));
  public String CITY = ADDRESS;
  public static final String ENTRY_DATE = "18.04.2020";
  public String DISPATCHER = employeeEditPage.dispatchers.get(random.nextInt(employeeEditPage.dispatchers.size()));
  public final String ANNUAL_LEAVE = String.valueOf(faker.number().numberBetween(0,30));
  public static final String HEALTH_INSURANCE = "BKK EWE";
  public static String NUMBER_OF_KIDS ;
  public static String COMPANY_OPTION;
  public static String ROLE_OPTION;
  public static String LAND_OPTION;
  public static String SALARY_TYPE_OPTION;
  public static String TAX_CLASS_OPTION;
  public static String CLIENT_OPTION;
  public String GROSS_SALARY = String.valueOf(faker.number().numberBetween(28,39)*100);
  public static String CHURCH_OPTION;


  @When("the user clicks on the  create new employee button")
  public void the_user_clicks_on_the_createNewEmployeeButton() {
    EmployeePage employeePage = new EmployeePage();
    employeePage.clickOnNewEmployeeButton();
  }

  @When("the user fills the form for newEmployee")
  public void the_user_fills_the_form_for_newEmployee() {

    employeeEditPage.getInputBox("firstname").sendKeys(FIRST_NAME);
    employeeEditPage.getInputBox("lastname").sendKeys(LAST_NAME);
    employeeEditPage.getInputBox("employeeNumber").sendKeys(Keys.BACK_SPACE + EMPLOYEE_NUMBER);
    employeeEditPage.getInputBox("socialSecurityNumber").sendKeys(Keys.BACK_SPACE + SOCIAL_SEC_NUMBER);
    employeeEditPage.getInputBox("taxId").sendKeys(Keys.BACK_SPACE + TAX_ID);
    employeeEditPage.getInputBox("address").sendKeys(ADDRESS);
    employeeEditPage.getInputBox("postCode").sendKeys(POST_CODE);
    employeeEditPage.landSelect.click();
    LAND_OPTION = employeeEditPage.clickAndReturnRandomOption();
    employeeEditPage.getInputBox("city").sendKeys(CITY);
    employeeEditPage.getInputBox("entryDate").sendKeys(ENTRY_DATE);
    employeeEditPage.companySelect.click();
    COMPANY_OPTION = employeeEditPage.clickAndReturnRandomOption();
    employeeEditPage.roleSelect.click();
    ROLE_OPTION = employeeEditPage.clickAndReturnRandomOption();
    employeeEditPage.clientSelect.click();
    CLIENT_OPTION = employeeEditPage.clickAndReturnRandomOption();
    employeeEditPage.getInputBox("dispatcher").sendKeys(DISPATCHER);
    employeeEditPage.getInputBox("annualLeave").sendKeys(Keys.BACK_SPACE + ANNUAL_LEAVE);
    employeeEditPage.salaryTypSelect.click();
    SALARY_TYPE_OPTION = employeeEditPage.clickAndReturnRandomOption();
    employeeEditPage.taxClassSelect.click();
    TAX_CLASS_OPTION=employeeEditPage.clickAndReturnRandomOption();
    employeeEditPage.healtInsuranceSelect.click();
    employeeEditPage.healtInsuranceSelect.sendKeys(HEALTH_INSURANCE);
    employeeEditPage.healtInsuranceSelect.sendKeys(Keys.ENTER);
    BrowserUtils.waitFor(1);
    employeeEditPage.childNumberDropbox.click();    NUMBER_OF_KIDS=employeeEditPage.clickAndReturnRandomOption();
    employeeEditPage.churchSelect.click();
    CHURCH_OPTION=employeeEditPage.clickAndReturnRandomOption();
    employeeEditPage.getInputBox("grossSalary").sendKeys(Keys.BACK_SPACE + GROSS_SALARY);
    employeeEditPage.salaryCalculateButton.click();
    BrowserUtils.waitFor(1);   // Dinamik bir wait yapmak icin --> netto gehalt control edilene kadar bekle formülü
    employeeEditPage.submitButton.click();
  }

  @Then("the user should see new record on EmployeesPage")
  public void the_user_should_see_new_record_on_EmployeesPage() {
    BrowserUtils.waitFor(2);
    Assert.assertEquals(FIRST_NAME,employeeDetailsPage.getInputBoxValue("firstname"));
    Assert.assertEquals(LAST_NAME,employeeDetailsPage.getInputBoxValue("lastname"));
    Assert.assertEquals(EMPLOYEE_NUMBER,employeeDetailsPage.getInputBoxValue("employeeNumber"));
    Assert.assertEquals(SOCIAL_SEC_NUMBER,employeeDetailsPage.getInputBoxValue("socialSecurityNumber"));
    Assert.assertEquals(TAX_ID,employeeDetailsPage.getInputBoxValue("taxId"));
    Assert.assertEquals(ADDRESS,employeeDetailsPage.getInputBoxValue("address"));
    Assert.assertEquals(POST_CODE,employeeDetailsPage.getInputBoxValue("postCode"));
    Assert.assertEquals(LAND_OPTION,employeeEditPage.landSelect.getText());
    Assert.assertEquals(CITY,employeeDetailsPage.getInputBoxValue("city"));
    Assert.assertEquals(String.valueOf(LocalDate.parse(ENTRY_DATE, germanFormatter)), employeeDetailsPage.getInputBoxValue("entryDate"));
    Assert.assertEquals("",employeeEditPage.getInputBox("exitDate").getAttribute("value"));
    Assert.assertEquals(COMPANY_OPTION,employeeEditPage.companySelect.getText());
    Assert.assertEquals(ROLE_OPTION,employeeEditPage.roleSelect.getText());
    Assert.assertEquals(CLIENT_OPTION,employeeEditPage.clientSelect.getText());
    Assert.assertEquals(DISPATCHER,employeeDetailsPage.getInputBoxValue("dispatcher"));
    Assert.assertEquals(ANNUAL_LEAVE,employeeDetailsPage.getInputBoxValue("annualLeave"));
    Assert.assertEquals(SALARY_TYPE_OPTION,employeeEditPage.salaryTypSelect.getText());
    Assert.assertEquals(TAX_CLASS_OPTION,employeeEditPage.taxClassSelect.getText());
    Assert.assertEquals(HEALTH_INSURANCE,employeeDetailsPage.getInputBoxValue("healthInsurance"));
    Assert.assertEquals(NUMBER_OF_KIDS,employeeDetailsPage.getInputBoxValue("numberOfKids"));
    Assert.assertEquals(CHURCH_OPTION,employeeEditPage.churchSelectVerification.getText());
    Assert.assertEquals(GROSS_SALARY,employeeDetailsPage.getInputBoxValue("grossSalary"));

  }

}
