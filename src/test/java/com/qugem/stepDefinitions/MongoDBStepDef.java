package com.qugem.stepDefinitions;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.qugem.pages.Dashboard;
import com.qugem.utilities.BrowserUtils;
import com.qugem.utilities.MongoDBUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MongoDBStepDef {

    Dashboard dashboard = new Dashboard();
    MongoCollection<Document> autosCollection = MongoDBUtils.getCollection("autos");

    @Then("Verify the MongoClient is not null")
    public void verify_the_MongoClient_is_not_null() {
        assertNotNull(MongoDBUtils.getMongoClient());
    }

    @Then("Verify the database is not null")
    public void verify_the_database_is_not_null() {
        assertNotNull(MongoDBUtils.getDatabase());
    }

    @Then("Verify the collection is not null")
    public void verify_the_collection_is_not_null() {
        assertNotNull(MongoDBUtils.getCollection("employees"));
    }

    @Then("Verify the {string} employee information")
    public void verify_the_employee_information(String id) {
        MongoCollection<Document> employees = MongoDBUtils.getCollection("employees");
        Document objectData = MongoDBUtils.getObjectDataByObjectId(employees, id);
        assertEquals("Syreeta", objectData.get("firstname"));
        assertEquals("Wyman", objectData.get("lastname"));
        assertEquals("Oßweil", objectData.get("address"));
    }

    @When("Click the all arrow buttons")
    public void click_the_all_arrow_buttons() {
        BrowserUtils.waitForVisibility(dashboard.sprinterExpandRow, 10);
        dashboard.sprinterExpandRow.click();
        BrowserUtils.waitForVisibility(dashboard.lkwExpandRow, 10);
        dashboard.lkwExpandRow.click();
        BrowserUtils.waitForVisibility(dashboard.sattleExpandRow, 10);
        dashboard.sattleExpandRow.click();
    }

    @Then("Verify count data on vehicle type {string}")
    public void verify_count_data_on_vehicle_type(String vehicleType) {
        Bson filterIdle = Filters.and(Filters.eq("type", vehicleType), Filters.eq("status", "idle"));
        Bson filterInUse = Filters.and(Filters.eq("type", vehicleType), Filters.eq("status", "in_use"));
        Bson filterInRepair = Filters.and(Filters.eq("type", vehicleType), Filters.eq("status", "in_repair"));
        Bson filterTotal = Filters.and(Filters.eq("type", vehicleType));

        String idleCount = dashboard.getValuesOfVehicleType(vehicleType).get(0);
        String inUseCount = dashboard.getValuesOfVehicleType(vehicleType).get(1);
        String inRepairCount = dashboard.getValuesOfVehicleType(vehicleType).get(2);
        String totalCount = dashboard.getValuesOfVehicleType(vehicleType).get(3);

        assertEquals(Integer.toString(MongoDBUtils.getCountOfObjectsInaDocument(autosCollection, filterIdle)), idleCount);
        assertEquals(Integer.toString(MongoDBUtils.getCountOfObjectsInaDocument(autosCollection, filterInUse)), inUseCount);
        assertEquals(Integer.toString(MongoDBUtils.getCountOfObjectsInaDocument(autosCollection, filterInRepair)), inRepairCount);
        assertEquals(Integer.toString(MongoDBUtils.getCountOfObjectsInaDocument(autosCollection, filterTotal)), totalCount);
    }

    @Then("Verify vehicle plate and user data on {string} vehicle type {string}")
    public void verify_vehicle_plate_and_user_data_on_vehicle_type(String status, String vehicleType) {
        Bson filter = dashboard.getFilterInAutosCollection(vehicleType, status);
        List<String> expectedVehiclePlateAndCurrentDriverList = new ArrayList<>();
        for (Document document : autosCollection.find(filter)) {
            String currentDriver = document.get("currentDriver") == null ? "" : " " + document.get("currentDriver");
            String plate = (String) document.get("plate");
            String plateAndCurrentDriver = plate + currentDriver;
            expectedVehiclePlateAndCurrentDriverList.add(plateAndCurrentDriver.trim());
        }

        List<WebElement> actualVehiclePlateAndCurrentDriverList = dashboard.getVehiclesPlateAndDriverList(vehicleType, status);

        for (int i = 0; i < actualVehiclePlateAndCurrentDriverList.size(); i++) {
            assertEquals(expectedVehiclePlateAndCurrentDriverList.get(i), BrowserUtils.getElementsText(actualVehiclePlateAndCurrentDriverList).get(i));
            System.out.println("Expected Result= "+expectedVehiclePlateAndCurrentDriverList.get(i));
            System.out.println("Actual Result= "+BrowserUtils.getElementsText(actualVehiclePlateAndCurrentDriverList).get(i));
        }

    }

}
