package com.qugem.stepDefinitions;

import com.qugem.pages.Dashboard;
import com.qugem.utilities.BrowserUtils;
import io.cucumber.java.en.*;

public class NavigatePages {

  @Then("admin navigates to {string}")
  public void admin_navigates_to(String modulName) {
    new Dashboard().navigatePages(modulName);

  }
}
