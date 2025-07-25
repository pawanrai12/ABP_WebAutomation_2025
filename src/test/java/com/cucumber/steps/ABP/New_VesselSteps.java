package com.cucumber.steps.ABP;

import com.framework.components.Settings;
import com.framework.cucumber.TestHarness;
import com.pageobjects.abp.New_Vessel_Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Properties;

public class New_VesselSteps extends New_Vessel_Page {
    protected TestHarness testHarness = new TestHarness();
    protected Properties properties = Settings.getInstance();

//    //#Step 1.1 --Commenting as im using the vessel calling function

    @Given("Launch the App using {string}")
    public void launch_the_App(String tcid) {
        testHarness.initializeTestData(tcid);
        launchApp();
    }

    //#Step 1.1
    //URL-https://abp-sws-dsc-main.commtrac-stg.co.uk/app/v/signin

    @When("the user has launched the application")
    public void theUserHasLaunchedTheApplication() throws InterruptedException {
        Thread.sleep(5000);
        String usrname = testHarness.getData("Vessel_Data", "UserName");
        System.out.println(usrname);
        enterUserName(usrname);

        String pswd = testHarness.getData("Vessel_Data", "Password");
        System.out.println(pswd);
        enterPassword(pswd);
    }
    //#Step 1.2
    @And("the user has logged in with Valid credentials")
    public void theUserHasLoggedInWithValidCredentials()
    {
        clickonSigninButton();
    }
    //#Step 1.3
    @Then("the CommTrac application home page should displayed")
    public void theCommTracApplicationHomePageShouldDisplayed()
    {
        verifyHomepage();
    }

    //#Step 2.1
    @When("the user navigates to Vessel Visit")
    public void theUserNavigatesToVesselVisit()
    {
        clickonVessel_Visit();
    }

    //#Step 2.2
    @Then("the Vessel Visit screen should displayed")
    public void theVesselVisitScreenShouldDisplayed()
    {
        verifyVesselVisitpage();
    }

    //#Step 3.1
    @When("user clicks Add on Vessel Visit")
    public void theUserClicksAddOnVesselVisit()
    {
        safe();
        add_Visit();
    }
    //#Step 3.2
    @Then("Edit Visit screen should be displayed")
    public void theEditVisitScreenShouldBeDisplayed()
    {
        verifyHomepage();
    }

//    //*** STEP 4.1 ***
//    @And("the user enters vessel visit details")
//    public void theUserEntersVesselVisitDetails()
//    {
//        String vesseldetails = testHarness.getData("Vessel_Data", "Name");
//        enterdetailsofVessel(vesseldetails);
//
//        enterDates();
//        berthselection("ROATH DOCK TERMINAL - Berth (RTH D)");
//        changetoWorking("Working");
//
//    }
//    //*** STEP 4.2 ***
//    @And("clicks Save on vessel visit screen")
//    public void clicksSaveOnVesselVisitScreen()
//    {
//        handleSaveAndOverride();
//    }
//
//    //*** STEP 4.3 ***
//    @And("clicks Save")
//    public void clicksSave()
//    {
//        clickOnSave();
//    }
//
//    //*** STEP 4.4 ***
//    @Then("a confirmation message should be displayed on Edit Visit screen")
//    public void aConfirmationMessageShouldBeDisplayedOnEditVisitScreen()
//    {
//        dd();
//        OnEditVisitScreen();
//    }

}
