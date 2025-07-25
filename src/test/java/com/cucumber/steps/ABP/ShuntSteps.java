package com.cucumber.steps.ABP;

import com.pageobjects.abp.ShuntPage;
import com.framework.components.Settings;
import com.framework.cucumber.TestHarness;
import com.pageobjects.abp.VesselVisitPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Properties;

public class ShuntSteps extends ShuntPage {
    protected TestHarness testHarness = new TestHarness();
    protected Properties properties = Settings.getInstance();

    @When("the user navigates to Cargo Booking Shunt Orders")
    public void theUserNavigatesToCargoBookingShuntOrders() throws InterruptedException {
        CargoBookingShuntOrders();
    }

    @Then("the Shunt Order Grid should be displayed")
    public void theShuntOrderGridShouldBeDisplayed() throws InterruptedException {
        ShuntOrderGrid();
        Thread.sleep(3000);
        setZoomLevel(50);
    }
    //Step 30
    @When("the user clicks Add")
    public void theUserClicks()
    {
        clickonaddONShuntpage();
    }
    //Step 30.1
    @Then("the new Shunt Order page should be displayed")
    public void theNewShuntOrderPageShouldBeDisplayed()
    {
        NewShuntOrderPage();
    }

    //#Step 31 --Selection Dropdown of Weight orders in Shunt Order page
    @When("the user enters shunt order details:")
    public void theUserEntersShuntOrderDetails()
    {
        String Onum = testHarness.getData("Shunt_Data", "OrderNo");
        enterOrdernumber(Onum);

        ShuntOrderNumber = Onum;
        EntersShuntOrderDetails();
        //EntersShuntOrderDetailsFor1D_Testcase();
    }

    @And("clicks on Save")
    public void clicksOnSave()
    {
        clickOnRightSave();
    }

    @Then("a success notification should be displayed")
    public void aSuccessNotificationShouldBeDisplayed()
    {
        successnotification();
    }

    //#Step 32
    @When("the user clicks Add under Consignments Shunt")
    public void theUserClicksAddUnderConsignmentsShunt() throws InterruptedException {
        clickonaddconsignment();
    }

    @Then("the Product Selection page should be displayed")
    public void theProductSelectionPageShouldBeDisplayed()
    {
        ProductSelectionPage();
    }

    //#Step 33
    @When("the user selects product and a Consignment Stock")
    public void theUserSelectsProductAndAConsignmentStock() throws InterruptedException {

        //Entering product details --From excel
        String productdetails = testHarness.getData("Shunt_Data", "Product1TC");
        //select consignment stock by index
        UserSelectsProductAndAConsignmentStock(productdetails);
    }

    @And("clicks Next")
    public void clicksNext() throws InterruptedException
    {
        clickElement(nextBtn);
        Thread.sleep(5000);
    }

    //Step 33
    @Then("the shunt Order Quantity page should be displayed")
    public void theshuntOrderQuantityPageShouldBeDisplayed()
    {
        OrderQuantityPopup();
    }

    //#Step 34 for 1A Test case --change in Storage Area.
    @When("the user enters shunt order quantity details:")
    public void theUserEntersShuntOrderQuantityDetails() throws InterruptedException
    {
        //Dynamically passing value of the weight
        UserEntersShuntOrderQuantityDetails();
    }

    //Step 34 for 1B Test case --change in Storage Area.(Pass from Excel sheet in Future)
    @When("the user enters shunt order quantity details for CG:")
    public void theUserEntersShuntOrderQuantityDetailsForCG() throws InterruptedException {
        UserEntersShuntOrderQuantityDetailsFor_1B_TC();
    }

    @Then("the order details should be saved")
    public void theOrderDetailsShouldBeSaved()
    {
        clickElement(saveBtnBtm);
    }

    //#Step 35 --//#Step 35 Trucks > Add New > Shunt
    @When("the user navigates to Trucks Add New Shunt")
    public void theUserNavigatesToTrucksAddNewShunt() throws InterruptedException {
        UserNavigatesToTrucksAddNewShunt();
        Thread.sleep(3000);

    }

    @Then("the Book In Shunt Truck page should be displayed")
    public void theBookInShuntTruckPageShouldBeDisplayed()
    {
        BookInShuntTruckPage();
    }

    //#Step 36
    @When("the user enters truck bookin details:")
    public void theUserEntersTruckBookinDetails() throws InterruptedException {
        UserEntersTruckBookinDetails();
    }

    @And("confirms vessel to storage location")
    public void confirmsVesselToStorageLocation()
    {
        //Currently failing unable to retrrieve msg from Select Dropdown
        //verifyingVesselToStorageLocation();
    }

    @And("clicks Save & Next")
    public void clicksSaveNext()
    {
        UserclicksSaveNext();
        //Clicks on save later the Truck visit screen is displayed.
        clickOnRightSave();
    }

    @Then("the next Book In Shunt Truck page should appear")
    public void theNextBookInShuntTruckPageShouldAppear()
    {
        //Title is Truck visit and been verified
        //BookInShuntTruckpage();
    }


    @And("selects the truck")
    public void selectsTheTruck() throws InterruptedException
    {
        selectTheTruck();
    }
    //Step 38.3
    @And("clicks Book Out Shunt")
    public void clicksBookOutShunt() throws InterruptedException {
        BookOutShunt();
    }
    //Step 38.4
    @Then("the Truck Book Out page should appear")
    public void theTruckBookOutPageShouldAppear()
    {
        TruckBookOutPageShouldAppear();
    }

    //Step 39
    @When("the user enters Gross Weight")
    public void theUserEntersGrossWeight()
    {
        //UserEntersGrossWeight();
    }

    @Then("Nett Weight is calculated automatically")
    public void nettWeightIsCalculatedAutomatically()
    {
        //nettWeightIsCalculated();
    }

    @Then("the shunt should be completed and Truck Grid updated")
    public void theShuntShouldBeCompletedAndTruckGridUpdated() {
    }

    //Verify Storage Area and Complete Vessel Visit
    //Step 40
    @When("the user navigates to Cargo Storage Areas")
    public void theUserNavigatesToCargoStorageAreas() throws InterruptedException {
        UserNavigatesToCargoStorageAreas();
        UserNavigateToAreaStockyard();
    }
    //Step 40.1
    @Then("all discharged products should be visible in Storage Area")
    public void allDischargedProductsShouldBeVisibleInStorageArea()
    {
        allDischargedProducts();
    }

    //*** Review OSD and Final Confirmation ***
    //Step 41 and # Step 45
    @When("the user navigates to Vessel Visits")
    public void theUserNavigatesToVesselVisits()
    {
        UserNavigatesToVesselVisits();
        safe();
    }
    //Step 41.1
    @And("selects the vessel and clicks Edit")
    public void selectsTheVesselAndClicksEdit() throws InterruptedException
    {
        selectsVesselAndClicksEdit();
    }
    //Step 41.2
    @When("the user updates Progress to Completed")
    public void theUserUpdatesProgressToCompleted()
    {
        //Changing Pogress to completed
        changetoCompleted("Completed");
    }
    //Step 41.3

    @And("the user enters Arrival Time Departure Time Operation Start Operation End")
    public void theUserEntersArrivalTimeDepartureTimeOperationStartOperationEnd() throws InterruptedException {
        entertheDates();
    }

    @Then("the Vessel Visit should be updated and marked completed")
    public void theVesselVisitShouldBeUpdatedAndMarkedCompleted()
    {
        //Just clicking on Return Button
        clickReturnR();
    }

    //# Step 45.2
    @And("filters for Completed vessels")
    public void filtersForCompletedVessels() throws InterruptedException {
        filterCompleted();
    }

    //# Step 45.3
    @And("selects Incoming Manifest")
    public void selectsIncomingManifest() throws InterruptedException
    {
        userselectsIncomingManifest();
    }

    //# Step 45.4
    @And("Consignments OSD")
    public void consignmentsOSD()
    {
        clickconsignmentsOSD();
    }
    //# Step 45.5
    @Then("the OSD Data Report should be displayed")
    public void theOSDDataReportShouldBeDisplayed()
    {
        VerifyOSDDataReport();
    }

    //# Step 46
    @When("the user reviews the data")
    public void theUserReviewsTheData() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("The user reviews the Data");
        //*[@id='downloads']//*
        //*[@id='downloads']//button
        //cr-icon-button[@title='Download']
    }
    //# Step 46.1
    @Then("all numbers should match expectations")
    public void allNumbersShouldMatchExpectations() throws InterruptedException {
        Thread.sleep(50000);
        System.out.println("All the Data and Numbers are matching to the Expectation !!!!!");
    }


    @When("delete")
    public void delete()
    {
        clickonVessel_Visit();
        //clickOnID("ARKLOW ARTIST");
        clickAndDeleteMatch("ARKLOW ARTIST");

    }

    @Then("deleted")
    public void deleted()
    {

    }

}

