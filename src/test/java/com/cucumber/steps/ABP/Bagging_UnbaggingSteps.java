package com.cucumber.steps.ABP;

import com.pageobjects.abp.Bagging_UnbaggingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Bagging_UnbaggingSteps extends Bagging_UnbaggingPage
{
    //Verify Storage Area and Complete Vessel Visit
    //Step 40.1
    @When("the user navigate to Operations Storage Area")
    public void theUserNavigateToOperationsStorageArea() throws InterruptedException {
        Selecting_BaggingMenu();
        add_BaggingDetails();
        UserNavigatesToCargoStorageAreas();
    }
    //Step 40.2
    @And("select the Storage Area")
    public void selectTheStorageArea() throws InterruptedException {
        UserNavigateToAreaStockyard();
        areaselection();

    }

    //Step 40.3
    @Then("all discharged product should be displayed in Storage Areas")
    public void allDischargedProductShouldBedisplayedInStorageAreas() throws InterruptedException {
        allDischargedProducts();
    }
}
