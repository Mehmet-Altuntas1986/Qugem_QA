package com.qugem.stepDefinitions;

import com.qugem.pages.EMPLOYEE.AttendanceEditPage;
import com.qugem.pages.EMPLOYEE.AttendancePage;
import com.qugem.utilities.BrowserUtils;
import io.cucumber.java.en.*;

public class AttendanceStepDef {

  AttendanceEditPage attendanceEditPage = new AttendanceEditPage();
  AttendancePage attendancePage = new AttendancePage();
  int dayCntrl;

  @When("the user clicks to edit button of Employee {string}")
  public void the_user_clicks_to_edit_button_of_Employee(String id) {
    attendancePage.clickEditButton(id);
    BrowserUtils.waitFor(1);
  }

  @When("the user edit attendance for {int} day as {string}")
  public void the_user_edit_attendance_for_day_as(int day, String opt) {
    dayCntrl = day;
    attendanceEditPage.dayEdit(day, opt);
    BrowserUtils.waitFor(1);
  }

  @Then("verify attendance update")
  public void verify_attendance_update() {
    attendanceEditPage.dayVerify(dayCntrl);
    BrowserUtils.waitFor(1);
  }
}
