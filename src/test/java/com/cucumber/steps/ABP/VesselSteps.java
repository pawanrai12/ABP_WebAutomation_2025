package com.cucumber.steps.ABP;
import com.framework.components.Settings;
import com.framework.cucumber.TestHarness;
import com.pageobjects.abp.VesselVisitPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.Properties;

public class VesselSteps extends VesselVisitPage
{
    protected TestHarness testHarness = new TestHarness();
    protected Properties properties = Settings.getInstance();

    @Given("Launch the Application using {string}")
    public void launch_the_Application(String tcid) {
        testHarness.initializeTestData(tcid);
        launchApp();
    }

    @When("the user has launched the Immingham Pre Prod application")
    public void theUserHasLaunchedTheImminghamPreProdApplication()
    {
        verifyTitle();
    }

    @And("the user has logged in with valid credentials")
    public void theUserHasLoggedInWithValidCredentials()
    {
        String usrname = testHarness.getData("Vessel_Data", "UserName");
        System.out.println(usrname);
        enterUserName(usrname);

        String pswd = testHarness.getData("Vessel_Data", "Password");
        System.out.println(pswd);
        enterPassword(pswd);

        clickonSigninButton();
    }

    @Then("the CommTrac application home page should be displayed")
    public void theCommTracApplicationHomePageShouldBeDisplayed()
    {
       //verifyHomepage();
    }

    @When("the user navigates to Vessel - Visit")
    public void NavigatesToVesselVisit()
    {
        clickonVessel_Visit();
    }

    @Then("the Vessel Visit screen should be displayed")
    public void theVesselVisitScreenShouldBeDisplayed()
    {
        verifyVisitGridpage();
    }

    //#Step 3
    @When("the user clicks Add on Vessel Visit")
    public void theUserClicksAddOnVesselVisit()
    {
        safe();
        add_Visit();
    }

    @Then("the Edit Visit screen should be displayed")
    public void theEditVisitScreenShouldBeDisplayed()
    {
        verifyeditVisitpage();
        getID();
    }
    //*** STEP 4 ***
    @And("the user enters vessel visit details")
    public void theUserEntersVesselVisitDetails()
    {
        String vesseldetails = testHarness.getData("Vessel_Data", "Name");
        enterdetailsofVessel(vesseldetails);

        enterDates();
        berthselection("ROATH DOCK TERMINAL - Berth (RTH D)");
        changetoWorking("Working");

    }

    @And("clicks Save on vessel visit screen")
    public void clicksSaveOnVesselVisitScreen()
    {
        handleSaveAndOverride();
    }

    @And("clicks Save")
    public void clicksSave()
    {
        clickOnSave();
    }

    //#Step 4.3
    @Then("a confirmation message should be displayed on Edit Visit screen")
    public void aConfirmationMessageShouldBeDisplayedOnEditVisitScreen()
    {
        dd();
        OnEditVisitScreen();
    }

//    @Given("the Vessel Visit screen is displayed")
//    public void theVesselVisitScreenIsDisplayed() throws InterruptedException {
//        clickonVessel_Visit();
//        safe();
//        //pageclick();
//        clickOnSearchVesselVisit();
//        SendTextOnSearchVesselVisit();
//        Thread.sleep(5000);
//        //waitUntilPageLoaded(5);
//    }
//
    @Given("the Vessel Visit screen is displayed")
    public void theVesselVisitScreenIsDisplayed() {
        verifyeditVisitpage();
    }

    @When("the user selects Incoming Manifest")
    public void theUserSelectsIncomingManifest() {
        //clickonIndex1();
        clickonIncommingmanifest();
    }

    @Then("the Incoming Manifest should be displayed")
    public void theIncomingManifestShouldBeDisplayed() {
        verifyIncommingmanifestpage();
    }

    @When("the user clicks Add under Consignments")
    public void theUserClicksAddUnderConsignments()
    {
        clickonaddONconsignment();
    }

    @Then("the Consignment page should be displayed")
    public void theConsignmentPageShouldBeDisplayed()
    {
        verifyConsignmentpage();
    }

    @When("the user enters consignment details:")
    public void theUserEntersConsignmentDetails()
    {
        clickonConsingerInput();
        entercongDetails();

    }

    @Then("a confirmation message should be displayed")
    public void aConfirmationMessageShouldBeDisplayed() throws IOException {

        verifyConsignmnetMessage();

    }

    //Adding Consignment Stock

    @Given("the Consignment page is displayed")
    public void theConsignmentPageIsDisplayed()
    {
        verifyConsignmentpage();
    }

    @When("the user clicks Add Consignment Stock")
    public void theUserClicksAddConsignmentStock()
    {
        clickOnAddCS();
    }

    @Then("the Product selection page should be displayed")
    public void theProductSelectionPageShouldBeDisplayed()
    {
        verifyProductSelectionPage();
    }

    //Step 9
    @When("the user enters consignment stock details:")
    public void theUserEntersConsignmentStockDetails() throws InterruptedException
    {
        String product_input = testHarness.getData("Vessel_Data", "Product");
        System.out.println("The Entered Product is:" + product_input);
        enterProductAndclicknext(product_input);
        Thread.sleep(5000);
        setZoomLevel(50);
        Thread.sleep(5000);
        productDetails();
    }

    @And("clicks Save on CS")
    public void clicksSaveOnCS() {
        clickOnCSSave();
    }
    //9.3
    @Then("the Consignment page should be displayed with confirmation")
    public void theConsignmentPageShouldBeDisplayedWithConfirmation()
    {
        //Need to verify confirmation
        verifyConsignmentpage();
        clickOnSave();
        //validateMsg();
    }

    @When("the user clicks Return")
    public void theUserClicksReturn()
    {
        clickonReturnBtnConsignmentPage();
    }

    @Then("the Incoming Manifest page should be displayed")
    public void theIncomingManifestPageShouldBeDisplayed()
    {
        verifyIncommingmanifestpage();
    }

    // Step11
    @Given("the Incoming Manifest page is displayed")
    public void theIncomingManifestPageIsDisplayed() throws InterruptedException
    {
        verifyIncommingmanifestpage();
    }

//    @Given("the Incoming Manifest page is displayed")
//    public void theIncomingManifestPageIsDisplayed() throws InterruptedException {
//        clickonVessel_Visit();
//        safe();
//        clickOnSearchVesselVisit();
//        SendTextOnSearchVesselVisit();
//        Thread.sleep(5000);
//        clickonIndex1();
//        Thread.sleep(5000);
//        clickonIncommingmanifest();
//    }
    //Step 11.1
    @When("the user clicks Add under Cargo in Holds")
    public void theUserClicksAddUnderCargoInHolds()
    {
        clickonAddBtnCargoinholds();
    }
    //Step 11.2
    @Then("the Cargo In Holds - Consignment Selection popup should appear")
    public void theCargoInHoldsConsignmentSelectionPopupShouldAppear()
    {
        verifyCargoinholdpopup();
    }
    //Step 12.1
    @When("the user selects a Consignment Number")
    public void theUserSelectsAConsignmentNumber()
    {
        consignmentinputCargoinholds();
    }
    //Step 12.3
    @Then("the Assign Cargo in Hold page should be displayed")
    public void theAssignCargoInHoldPageShouldBeDisplayed()
    {
        verifyAssignCargoinholdpopup();
    }

//This is the common step to Initialize Sheet
    @Given("Use the {string}")
    public void useThe(String tcid) {
        testHarness.initializeTestData(tcid);
    }

    //Step 13.1
    @When("the user selects Hold")
    public void theUserSelectsHold()
    {
        VerifySelectsHold();
    }
    //Step 13.2
    @And("enters Weight")
    public void entersWeight() throws InterruptedException {
        userentersWeight();
    }
    //Step 13.3
    @And("clicks on Hold page Save")
    public void clicksOnHoldPageSave()
    {
        clickElement(nextBtn);
    }
    //Step 13.4
    @Then("the Cargo should be visible on Cargo In Holds Table and Vessel Visual")
    public void theCargoShouldBeVisibleOnCargoInHoldsTableAndVesselVisual()
    {

    }


}
