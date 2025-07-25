package com.cucumber.steps.ABP;

import com.framework.components.Settings;
import com.framework.cucumber.TestHarness;
import com.pageobjects.abp.Truck_Intake_Page;

import java.io.IOException;
import java.util.Properties;

import gherkin.lexer.Th;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Properties;

public class Truck_Intake_Steps extends Truck_Intake_Page {
    protected TestHarness testHarness = new TestHarness();
    protected Properties properties = Settings.getInstance();


    //# Launch Steps
    @Given("Launch this App using {string}")
    public void launch_this_App(String tcid) {
        testHarness.initializeTestData(tcid);
        launchApp();
    }

    @When("the user has launched the app")
    public void theUserHasLaunchedTheApp()
    {
        verifyTitle();
    }

    @And("the user has logged in with valid cred")
    public void theUserHasLoggedInWithValidCred()
    {
        String usrname = testHarness.getData("Truck_Intake", "UserName");
        System.out.println(usrname);
        enterUserName(usrname);

        String pswd = testHarness.getData("Truck_Intake", "Password");
        System.out.println(pswd);
        enterPassword(pswd);

        clickonSigninButton();
    }

    @Then("the CommTrac app home page should be displayed")
    public void theCommTracAppHomePageShouldBeDisplayed()
    {
        //verifyHomepage();
    }

    //# Step 2.1
    @When("I Navigate to Cargo>Booking>Intakes")
    public void iNavigateToCargoBookingIntakes() throws InterruptedException
    {
        CargoTruckIntakes();
    }
    //# Step 2.2
    @And("the user clicks on Add in Truck Intake")
    public void theUserClicksOnAddInTruckIntake() {
        clickonaddONTruckpage();
    }
    //# Step 2.3
    @Then("the Truck Intake screen should be displayed")
    public void theTruckIntakeScreenShouldBeDisplayed()
    {
        TruckIntakescreendisplayed();
    }

    //# Step 3
    @When("the user enters Intake Name")
    public void theUserEntersIntakeName() {
        String Intakedetails = testHarness.getData("Truck_Intake", "IntakeName");
        enterdetailsofIntakes(Intakedetails);

    }

    //# Step 3.1
    @And("the user changes progress to Working")
    public void theUserChangesProgressToWorking() {
        changetoWorking("Working");
    }
    //# Step 3.2 &  # Step 5.2
    @And("the user clicks on SaveR")
    public void theUserClicksOnSaveR() {
        clickOnSaveR();
    }

    //# Step 3.3
    @Then("the Truck Intake screen with Consignment section should be displayed")
    public void theTruckIntakeScreenWithConsignmentSectionShouldBeDisplayed() {
    }

    //# Step 4.1
    @When("the user clicks on Add under the Consignment section")
    public void theUserClicksOnAddUnderTheConsignmentSection() {
        clickonaddONconsignment();
    }

    //# Step 4.3
    @Then("Consignment page should be displayed")
    public void consignmentPageShouldBeDisplayed() {
        verifyConsignmentpage();
    }


    //# Step 5.1
    @When("the user enters the Consignor details field")
    public void theUserEntersTheConsignorDetailsField() {
        clickonConsingerInput();
        String consignerdetails = testHarness.getData("Truck_Intake", "Consignor");
        //Consigner = peacock
        entercongDetails(consignerdetails);
    }
    //# Step 5.2  --Clicking on SaveR
    //# Step 5.3
    @Then("a Saved successfully message should be displayed and the Consignment Stock option should be available")
    public void aSavedSuccessfullyMessageShouldBeDisplayedAndTheConsignmentStockOptionShouldBeAvailable() throws IOException {
        verifyConsignmnetMessage();
    }

    //# Step 6.1
    @When("the user clicks on Add under Consignment Stock")
    public void theUserClicksOnAddUnderConsignmentStock() throws InterruptedException {
        clickonaddconsignment();

    }
    //# Step 6.2
    @Then("Product Selection page should be displayed")
    public void productSelectionPageShouldBeDisplayed() throws InterruptedException {
        ProductSelectionPage();
    }

    //# Step 7.1
    @When("the user enters the product stock details")
    public void theUserEntersTheProductStockDetails() {
            String product = testHarness.getData("Truck_Intake", "Product");
            //*** Product = Salt
            enterProdDetails(product);
    }
    //# Step 7.1 --For 7D step
    @When("the user enters the product stock details for {int}D")
    public void theUserEntersTheProductStockDetailsForD(int arg0)
    {
        String product = testHarness.getData("Truck_Intake", "Product");
        //*** Product = Ammonium Sulphate and selecting 1000kg bag
        enterProdDetails(product);
        //*** Product = Ammonium Sulphate and selecting 1000kg
        selectStoargetype();

    }

    //# Step 7.2
    @And("the user clicks on Next")
    public void theUserClicksOnNext() {
        clickOnNextBtm();
    }

    //# Step 7.3 --Entering weight value Globally
    @And("user enters weight val")
    public void userEntersWeightVal() throws InterruptedException
    {
        String productweight = testHarness.getData("Truck_Intake", "Weight");
        productDetailWeight(productweight);
        Thread.sleep(3000);
    }

    //# Step 7.3 --Entering weight value Globally --For 7D TC
    @And("enters the stock identifier details n num of bags weight details")
    public void entersTheStockIdentifierDetailsNNumOfBagsWeightDetails() throws InterruptedException {
        String stockidentifier = testHarness.getData("Truck_Intake", "CSGName");
        String productweight = testHarness.getData("Truck_Intake", "Weight");
        StockEntry(stockidentifier,productweight);

    }

    //# Step 7.4
    @Then("Saved successfully message should be displayed and consignment stock should be created")
    public void savedSuccessfullyMessageShouldBeDisplayedAndConsignmentStockShouldBeCreated() throws InterruptedException {
        setZoomLevel(50);
        clickOnSaveR();
        Thread.sleep(5000);
    }


    //# Step 8.1
    @When("the user clicks on the product under Consignment Stock")
    public void theUserClicksOnTheProductUnderConsignmentStock() throws InterruptedException {
        clickOnProductName();
    }
    //# Step 8.2
    @Then("a product menu should be displayed")
    public void aProductMenuShouldBeDisplayed() throws InterruptedException {
        MenuDisplayed();
    }

    //# Step 9.1
    @When("the user clicks on Preferred Location")
    public void theUserClicksOnPreferredLocation() throws InterruptedException {
        ClicksonprefferedLoc();
    }
    //# Step 9.2
    @Then("the Preferred Location window should be displayed")
    public void thePreferredLocationWindowShouldBeDisplayed() {
        verifypreferredLocationTitlePage();
    }

    //# Step 10.1 --For 7A test Case
    @When("the user selects COMMONSTOWAGE Storage Area to discharge stock")
    public void theUserSelectsCOMMONSTOWAGEStorageAreaToDischargeStock() throws InterruptedException {
        selectingLocation_COMMONSTOWAGE();

        //*** clicking on theReturn btn to capture IUSRN number
        clickOnReturnBtn();
        //***To Grab USRN Intake from the Consignment Stock Page
        Thread.sleep(5000);
        getIntakeUSRN();

        //***will Capture again the saved message after Adding Consignment Stock i.e, Weight
        clickOnSaveR();
    }
    //# Step 10.1 --For 7B test Case
    @When("the user selects ConsignmentGroup Storage Area to discharge stock")
    public void theUserSelectsConsignmentGroupStorageAreaToDischargeStock() throws InterruptedException {
        selectingLocation_ConsignmentGroup();

        //*** clicking on theReturn btn to capture IUSRN number
        clickOnReturnBtn();
        //***To Grab USRN Intake from the Consignment Stock Page
        Thread.sleep(5000);
        getIntakeUSRN();

        //***will Capture again the saved message after Adding Consignment Stock i.e, Weight
        clickOnSaveR();
    }

    //# Step 10.1 --For 7 test Case
    @When("the user selects a Storage Area to discharge stock")
    public void theUserSelectsAStorageAreaToDischargeStock() throws InterruptedException {
        selectingLocation_COMMONSTOWAGE();
        //*** clicking on theReturn btn to capture IUSRN number
        clickOnReturnBtn();
        //***To Grab USRN Intake from the Consignment Stock Page
        Thread.sleep(5000);
        getIntakeUSRN();

        //***will Capture again the saved message after Adding Consignment Stock i.e, Weight
        clickOnSaveR();

        //****Quick message not able to Grab the text
        //savedMsgText();
    }
    //# Step 10.1 --For 7D test Case
    @When("the user selects Storage Area to discharge stock")
    public void theUserSelectsStorageAreaToDischargeStock() throws InterruptedException {
        selectingLocation();

        //*** clicking on theReturn btn to capture IUSRN number
        clickOnReturnBtn();
        //***To Grab USRN Intake from the Consignment Stock Page
        Thread.sleep(5000);
        getIntakeUSRN();

        //***will Capture again the saved message after Adding Consignment Stock i.e, Weight
        clickOnSaveR();
    }

    //# Step 10.2
    @And("the user clicks on Save")
    public void theUserClicksOnSave() {
    }
    //# Step 10.3
    @Then("consignment page should displayed")
    public void consignmentPageShouldDisplayed() {
    }

}

