package com.cucumber.steps.ABP;

import com.framework.cucumber.TestHarness;
import com.pageobjects.abp.CommonStowagePage;
import com.framework.components.Settings;
import com.framework.cucumber.TestHarness;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.Properties;

public class CommonStowageSteps extends CommonStowagePage {
    protected TestHarness testHarness = new TestHarness();
    protected Properties properties = Settings.getInstance();

//    @Given("Launch the Application using {string}")
//    public void launch_the_Application(String tcid) {
//        testHarness.initializeTestData(tcid);
//        launchApp();
//    }

    //#Step 14.1
    @When("the user navigates to Cargo Common Stowage")
    public void theUserNavigatesToCargoCommonStowage() throws InterruptedException
    {
        UserNavigatesToCargoCommonStowage();
    }
    //#Step 14.2
    @Then("the Common Stowage Grid should be displayed")
    public void theCommonStowageGridShouldBeDisplayed()
    {
        VerifyCommonStowageGrid();
    }

    //#Step 16.1
    @When("the user clicks on Add in Common Stowage")
    public void theUserClicksOnAddInCommonStowage()
    {
        clicksAddinCommonStowage();
    }

    //#Step 16.2
    @And("enters the Commudity details:")
    public void entersTheCommudityDetails()
    {
        setZoomLevel(70);
        EntersTheCommudityDetails();
    }
    //#Step 16.2 --This is the step for  7A
    @And("enters the Commudity details:{int}a")
    public void entersTheCommudityDetailsA(int arg0) {
        setZoomLevel(70);

        String name = testHarness.getData("Truck_Intake","CSGName");
        String nameinput = testHarness.getData("Truck_Intake","CSGNameInput");

        EntersTheCommudityDetailsfor7A(name,nameinput);
    }
    //#Step 16.3
    @And("clicks on Save in common stowage")
    public void clicksOnSaveInCommonStowage()
    {
        clickOnRightSave();
    }
    //#Step 16.4
    @Then("the Common Stowage Group details page should be displayed")
    public void theCommonStowageGroupDetailsPageShouldBeDisplayed()
    {
        CommonStowageGroupDetailsPage();
    }

    //#Step 17.1
    @When("the user clicks Add under Consignment Stocks")
    public void theUserClicksAddUnderConsignmentStocks()
    {
        UserClicksAddUnderConsignmentStocks();
    }
    //#Step 17.2
    @Then("the Add to Common Stowage window should appear")
    public void theAddToCommonStowageWindowShouldAppear()
    {
        AddToCommonStowageWindow();
    }

    //Add a common from vessel
    //#Step 18.1 --For 1A -Common Stowage
    @When("the user selects Consignment Stock for OneA")
    public void theUserSelectsConsignmentStockForOneA() throws IOException {
        theUserSelectsConsignmentStockFor1A();
    }

    //#Step 18.1 --For 7A --Common Stowage
    @When("the user selects Consignment Stock")
    public void theUserSelectsConsignmentStock() throws IOException {
        UserSelectsConsignmentStockSevenA();
    }

    //#Step 18.2
    @And("clicks on Save Btn")
    public void clicksOnSaveBtn()
    {
        clickOnBottomSave();
    }

    //#Step 18.3
    @Then("the stock should be added to Common Stowage Group CSV")
    public void theStockShouldBeAddedToCommonStowageGroupCSV()
    {
//        addedStock();
    }

    @When("the user clicks Save on Common Stowage")
    public void theUserClicksSaveOnCommonStowage()
    {
        UserClicksSaveOnCommonStowage();
    }


    //Step 19.1
    @When("the user clicks RSave on Common Stowage")
    public void theUserClicksRSaveOnCommonStowage() throws InterruptedException {
        clickOnRightSave();
        Thread.sleep(3000);
    }
    //Step 19.2
    @Then("the Common Stowage Group details should be saved")
    public void theCommonStowageGroupDetailsShouldBeSaved()
    {
//        CommonStowageGroupDetailsMsg();
    }





    //Unused Step definitions


    @When("the user searches for commodity")
    public void theUserSearchesForCommodity()
    {
        UserSearchesForCommodity();
    }

    @Then("filtered Common Stowage results should be displayed")
    public void filteredCommonStowageResultsShouldBeDisplayed()
    {

    }

    @When("the user selects the commodity")
    public void theUserSelectsTheCommodity() {
    }

    @And("clicks on Edit")
    public void clicksOnEdit()
    {

    }

    @And("if not found, adds a new commodity and saves")
    public void ifNotFoundAddsANewCommodityAndSaves()
    {
        clickOnRightSave();
    }


}


