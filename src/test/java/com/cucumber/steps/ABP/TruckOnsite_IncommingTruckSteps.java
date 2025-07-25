package com.cucumber.steps.ABP;

import com.framework.components.Settings;
import com.framework.cucumber.TestHarness;
import com.pageobjects.abp.TOnsite_IncommingTruck_Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Properties;

public class TruckOnsite_IncommingTruckSteps extends TOnsite_IncommingTruck_Page
{
    protected TestHarness testHarness = new TestHarness();
    protected Properties properties = Settings.getInstance();

    //Truck Onsite
    //Step 17.1
    @When("the User navigates to Trucks On Site")
    public void theUserNavigatesToTrucksOnSite() throws InterruptedException {
        //UserNavigatesToTrucksonSite();
    }
    //Step 17.2
    @And("selects the Add Incoming truck")
    public void selectsTheAddIncomingTruck() throws InterruptedException {
        //UserselectsTheAddIncomingTruck();
    }
    //Step 17.3
    @Then("the Incoming Truck page should appear")
    public void theIncomingTruckPageShouldAppear() throws InterruptedException {
        //verifyIncomingTruckPage();
    }

    //Step 18.1
    @When("the user enters truck details{int}A:")
    public void theUserEntersTruckDetailsA(int arg0) throws InterruptedException {
        TruckBookout7A();
    }
    @When("the user enters truck details{int}B:")
    public void theUserEntersTruckDetailsB(int arg0) throws InterruptedException {
        TruckBookout7B();
    }
    @When("the user enters truck details{int}C:")
    public void theUserEntersTruckDetailsC(int arg0) throws InterruptedException {
        TruckBookout7C();
    }

    @When("the user enters truck details{int}D:")
    public void theUserEntersTruckDetailsD(int arg0) throws InterruptedException {
        TruckBookout7D();
    }

    //Step 18.2
    @And("clicks on save")
    public void clicksOnSave() {
        //UserclicksSaveR();
    }
    @And("user select truck bookout")
    public void userSelectTruckBookout() throws InterruptedException {
        //Bookout();
        //UserclicksSaveR();
        //Thread.sleep(10000);
    }

    //Step 18.
    @Then("the final report should appear")
    public void theFinalReportShouldAppear() {

    }

    //Step 23.1
    @When("I complete the truck processing")
    public void iCompleteTheTruckProcessing() throws InterruptedException {
        System.out.println("The e truck processing is Completed");
        UserNavigatesToTrucksonSite();
    }
    //Step 23.2
    @And("I navigate to the Completed Trucks section")
    public void iNavigateToTheCompletedTrucksSection() throws InterruptedException {
        UserNavigateToTheCompletedTrucks();
    }
    //Step 23.3
    @Then("I should see the truck's status as Completed")
    public void iShouldSeeTheTruckSStatusAsCompleted() {
        verifyCompletedTruckPage();
    }

    //Step 24.1
    @When("I navigate to the Cargo>Storage Areas menu")
    public void iNavigateToTheCargoStorageAreasMenu() {
        UserNavigatesToCargoStorageAreas();
    }
    //Step 24.2
    @And("a truck has completed the discharge process & I view the designated storage area on the screen")
    public void aTruckHasCompletedTheDischargeProcessIViewTheDesignatedStorageAreaOnTheScreen() throws InterruptedException {
        UserNavigateToAreaStockyard();
    }
    //Step 24.3
    @Then("I should see all the discharged products from the truck listed in the designated storage area")
    public void iShouldSeeAllTheDischargedProductsFromTheTruckListedInTheDesignatedStorageArea() {
        allDischargedProducts();
    }

}
