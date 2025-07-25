package com.cucumber.steps.ABP;

import com.framework.components.Settings;
import com.framework.cucumber.TestHarness;
import com.pageobjects.abp.BlendingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Properties;

public class BlendingSteps extends BlendingPage
{
    protected TestHarness testHarness = new TestHarness();
    protected Properties properties = Settings.getInstance();

    //# Launch Steps
    @Given("User has Launch the App using {string}")
    public void userHasLaunchTheAppUsing(String tcid)
    {
        testHarness.initializeTestData(tcid);
        launchApp();
    }

    @When("User has launched the Immingham application")
    public void userHasLaunchedTheImminghamApplication()
    {
        verifyTitle();
    }

    @And("User has logged in with valid credentials")
    public void userHasLoggedInWithValidCredentials()
    {
        String usrname = testHarness.getData("Blend", "UserName");
        System.out.println(usrname);
        enterUserName(usrname);

        String pswd = testHarness.getData("Blend", "Password");
        System.out.println(pswd);
        enterPassword(pswd);

        clickonSigninButton();
    }

    @Then("CommTrac application Home page should be displayed")
    public void commtracApplicationHomePageShouldBeDisplayed()
    {
        verifyHomepage();
    }

    //Step#1
    @When("I click on Menu")
    public void iClickOnMenu() throws InterruptedException {
        //hi();
    }
    //Step#1.1
    @And("I do some actions")
    public void iDoSomeActions() throws InterruptedException {
        //hello();
    }
    //Step#1.1
    @Then("Menu is displayed")
    public void menuIsDisplayed() throws InterruptedException
    {
        bye();
        handleSaveAndOverride();
    }


    //Verify Storage Area and Complete Vessel Visit
    //Step 40.1
    @When("the user navigates to Operations Storage Area")
    public void theUserNavigatesToOperationsStorageArea() throws InterruptedException {
        UserNavigatesToCargoStorageAreas();
    }
    //Step 40.2
    @And("selects the Storage Area")
    public void selectsTheStorageArea() throws InterruptedException {
        UserNavigateToAreaStockyard();
        areaselection();

    }

    //Step 40.3
    @Then("all discharged products should be displayed in Storage Areas")
    public void allDischargedProductsShouldBedisplayedInStorageAreas() throws InterruptedException {
        allDischargedProducts();
    }

}
