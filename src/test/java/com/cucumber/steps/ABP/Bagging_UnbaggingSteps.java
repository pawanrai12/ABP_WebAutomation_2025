package com.cucumber.steps.ABP;

import com.pageobjects.abp.Bagging_UnbaggingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Bagging_UnbaggingSteps extends Bagging_UnbaggingPage
{
    //Bagging Process
    @When("the user navigate to Operations Bagging")
    public void theUserNavigateToOperationsBagging() throws InterruptedException
    {
        Selecting_BaggingMenu();
    }

    @Then("Bagging Process starts")
    public void baggingProcessStarts() throws InterruptedException
    {
        add_BaggingDetails();
    }

    //UnBagging Process
    @When("the user navigate to Operations UnBagging")
    public void theUserNavigateToOperationsUnBagging() throws InterruptedException
    {
        Selecting_UnBaggingMenu();
    }

    @Then("UnBagging Process starts")
    public void unbaggingProcessStarts() throws InterruptedException
    {
        add_UnbaggingDetails();
    }

    //Verify Storage Area and Complete Vessel Visit
    //Step 40.1
    @When("the user navigate to Operations Storage Area")
    public void theUserNavigateToOperationsStorageArea() throws InterruptedException {
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
