package com.cucumber.steps.ABP;

import com.framework.cucumber.TestHarness;
import com.framework.components.Settings;
import com.pageobjects.abp.ConsignmentGroupPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;
import java.util.Properties;

public class ConsignmentGroupSteps extends ConsignmentGroupPage
{
    protected TestHarness testHarness = new TestHarness();
    protected Properties properties = Settings.getInstance();

//    @Given("Launch the Application using {string}")
//    public void launch_the_Application(String tcid) {
//        testHarness.initializeTestData(tcid);
//        launchApp();
//    }
    //#Step 14
    @When("the user navigates to Cargo Consignment Group")
    public void theUserNavigatesToCargoConsignmentGroup()
    {
        UserNavigatesToCargoConsignmentGroup();
    }
    //#Step 14.1
    @Then("the Consignment Group Grid should be displayed")
    public void theConsignmentGroupGridShouldBeDisplayed()
    {
        VerifyConsignmentGroupGrid();
    }

    @When("the user enters commoditysname in the search box")
    public void theUserEntersCommoditysnameInTheSearchBox()
    {
        //userclicksAdd();
        clickOnRightSave();
        //verifyafterSave();
    }

    //16
    @When("the user clicks Add under ConsignmentStocks")
    public void theUserClicksAddUnderConsignmentStocks()
    {
        UserClicksAddUnderConsignmentStocks();
    }
    //16.1
    @Then("the Add to Consignment Group window should be displayed")
    public void theAddToConsignmentGroupWindowShouldBeDisplayed()
    {
        VerifyAddtoConsignmentGroupPopup();
    }

    @When("the user selects a Consignment Stock")
    public void theUserSelectsAConsignmentStock()
    {
        //UserSelectsAConsignmentStock();
    }

    //16.1
    @When("the user clicks Add under ConsignmentGroup")
    public void theUserClicksAddUnderConsignmentGroup()
    {
        UserClicksAddUnderConsignmentStocks();
    }

    @And("enters the Commodity details:")
    public void entersTheCommodityDetails()
    {
        UserentersTheCommodityDetails();
    }
    // 7b Data passing through excel
    @And("enters the Commodity details:{int}b")
    public void entersTheCommodityDetailsB(int arg0) {
        setZoomLevel(70);

        String name = testHarness.getData("Truck_Intake","CSGName");
        String nameinput = testHarness.getData("Truck_Intake","CSGNameInput");

        EntersTheCommudityDetailsfor7B(name,nameinput);
    }

    @And("clicks on Save in consignmentGroup page")
    public void clicksOnSaveInConsignmentGroupPage()
    {
        JSClick(saveBtnR);

    }

    @Then("the Consignment Group details page should be displayed")
    public void theConsignmentGroupDetailsPageShouldBeDisplayed()
    {
        conginementpopupMsg();
    }

    //Step 17
    @When("the user clicks Add under Consignment Stocks in CGpage")
    public void theUserClicksAddUnderConsignmentStocksInCGpage()
    {
        clickaddConsginmentStock();
    }

    //Step 17.1
    @Then("the Add to Consignment Group window should appear CG")
    public void theAddToConsignmentGroupWindowShouldAppearCG()
    {
        //just verified the popup of title
        AddToConsignmentGroupWindow();
    }

    //Step 18
    @When("the user selects Consignment Stock in CG")
    public void theUserSelectsConsignmentStockinCG() throws IOException {
        UserSelectsConsignmentStockinCG();
    }
    //Step 18.3
    @Then("the stock should be added to Consignment Stock Group CSV")
    public void theStockShouldBeAddedToConsignmentStockGroupCSV()
    {
//       addedStock();
    }

    //Step 18.2
    @And("clicks on Save Btn in CG")
    public void clicksOnSaveBtnInCG() {
        clickOnBottomSaveCG();
    }

    //Step 19
    @When("the user clicks RSave on Consignment Stock")
    public void theUserClicksRSaveOnConsignmentStock()
    {
        JSClick(saveBtnR);
    }
    //Step 19.1
    @Then("the Consignment Stock Group details should be saved")
    public void theConsignmentStockGroupDetailsShouldBeSaved() {
    }

}
