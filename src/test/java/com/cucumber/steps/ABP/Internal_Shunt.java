package com.cucumber.steps.ABP;

import com.framework.components.Settings;
import com.framework.cucumber.TestHarness;
import com.pageobjects.abp.Internal_ShuntPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Properties;

public class Internal_Shunt extends Internal_ShuntPage
{
    protected TestHarness testHarness = new TestHarness();
    protected Properties properties = Settings.getInstance();

    //#Step 29.1
    @When("the user navigates to Cargo Booking Shunt Order")
    public void theUserNavigatesToCargoBookingShuntOrder() throws InterruptedException {
        CargoBookingShuntOrders();
    }
    //#Step 29.2
    @Then("the Shunt Orders Grid should be displayed")
    public void theShuntOrdersGridShouldBeDisplayed() throws InterruptedException {
        ShuntOrderGrid();
        Thread.sleep(3000);
        setZoomLevel(50);
    }
    //Step 30.1
    @When("the user click Add")
    public void theUserClick()
    {
        clickonaddONShuntpage();
    }

    //Step 30.2
    @Then("the new Shunt Order pages should be displayed")
    public void theNewShuntOrderPagesShouldBeDisplayed()
    {
        NewShuntOrderPage();
    }

    //#Step 31.1 --"9A" >> Selection Dropdown of Weight orders in Shunt Order page
    @When("the user enters shunt order details for {int}A:")
    public void theUserEntersShuntOrderDetailsForA(int arg0) throws InterruptedException {
        String Onum = testHarness.getData("Shunt_Data", "OrderNo");
        enterOrdernumber(Onum);

        ShuntOrderNumber = Onum;

        //Entering Consignee details --From excel
        String Consigneedetails = testHarness.getData("Shunt_Data", "Consigne");
        //Entering details of Source & Destination Type
        EntersShuntOrderDetailsFor9A_Testcase(Consigneedetails);
    }

    //#Step 31.1 --"9B"  Selection Dropdown of Weight orders in Shunt Order page
    @When("the user enters shunt order details for {int}B:")
    public void theUserEntersShuntOrderDetailsForB(int arg0) throws InterruptedException {
        String Onum = testHarness.getData("Shunt_Data", "OrderNo");
        enterOrdernumber(Onum);

        ShuntOrderNumber = Onum;

        //Entering Consignee details --From excel
        String Consigneedetails = testHarness.getData("Shunt_Data", "Consigne");
        //Entering details of Source & Destination Type
        EntersShuntOrderDetailsFor9B_Testcase(Consigneedetails);
    }
    //Step 31.2
    @And("click on Save")
    public void clickOnSave()
    {
        clickOnRightSave();
    }
    //Step 31.3
    @Then("success notification should be displayed")
    public void SuccessNotificationShouldBeDisplayed()
    {
        successnotification();
    }

    //#Step 32.1
    @When("the user click Add under Consignments Shunt")
    public void theUserClickAddUnderConsignmentsShunt() throws InterruptedException {
        clickonaddconsignment();
    }
    //#Step 32.2
    @Then("the Product Selections page should be displayed")
    public void theProductSelectionsPageShouldBeDisplayed()
    {
        ProductSelectionPage();
    }

    //#Step 33 for 9A Test Cases --Enter Storage Area (SHUNT Excel sheet)
    @When("the user selects product and a Consignment Stock for {int}A")
    public void theUserSelectsProductAndAConsignmentStockForA(int arg0) throws InterruptedException
    {
        //Entering product details --From excel
        String productdetails = testHarness.getData("Shunt_Data", "Product");
        //Entering Storage Area details --From excel
        String StorageArea = testHarness.getData("Shunt_Data", "StorageArea");
        //select consignment stock by index
        UserSelectsProductAndAConsignmentStockfor9ATc(productdetails,StorageArea);
    }
    //#Step 33 for 9B Test Cases --Enter Storage Area
    @When("the user selects product and a Consignment Stock for {int}B")
    public void theUserSelectsProductAndAConsignmentStockForB(int arg0) throws InterruptedException
    {
        //Entering product details --From excel
        String productdetails = testHarness.getData("Shunt_Data", "Product");
        //Entering Storage Area details --From excel

        String StorageArea = testHarness.getData("Shunt_Data", "StorageArea");
        //select consignment stock by index
        UserSelectsProductAndAConsignmentStockfor9BTc(productdetails,StorageArea);
    }

    @And("click Next")
    public void clickNext() throws InterruptedException
    {
        clickElement(nextBtn);
        Thread.sleep(5000);
    }

    //Step 33
    @Then("the shunt Order Quantity pages should be displayed")
    public void theshuntOrderQuantityPagesShouldBeDisplayed()
    {
        OrderQuantityPopup();
    }

    //Step 34 for 9A Test case --change in Storage Area. ****Area change ****
    @When("the user enters shunt order quantity details for {int}A:")
    public void theUserEntersShuntOrderQuantityDetailsForA(int arg0) throws InterruptedException {
        String GW = testHarness.getData("Shunt_Data", "GlobalW");
        //***Source & Destination area has to be changed. make as excel
        UserEntersShuntOrderQuantityDetailsFor_9A_TC(GW);
    }
    //Step 34 for 9B Test case --change in Storage Area. ****Area change ****
    @When("the user enters shunt order quantity details for {int}B:")
    public void theUserEntersShuntOrderQuantityDetailsForB(int arg0) throws InterruptedException {
        String GW = testHarness.getData("Shunt_Data", "GlobalW");
        //**Source & Destination area has to be changed.
        UserEntersShuntOrderQuantityDetailsFor_9B_TC(GW);
    }

    @Then("the order detail should be saved")
    public void theOrderDetailShouldBeSaved()
    {
        clickElement(saveBtnBtm);
    }

    //Truck Onsite
    //Step 38.1
    @When("the user navigates to Trucks On Site")
    public void theUserNavigatesToTrucksOnSite() throws InterruptedException {
        UserNavigatesToTrucksOnSite();
    }
    //Step 38.2 (For 9A Testcase)
    @And("click on Add Shunt Truck")
    public void clickOnAddShuntTruck() throws InterruptedException {
        UserclickOnAddShuntTruck();
    }
    //Step 38.3 (For 9A Testcase)
    @Then("the Book in Shunt Truck page should appear")
    public void theBookInShuntTruckPageShouldAppear() throws InterruptedException {
        VerifyBookInShuntTruckpage();
        UserEntersTruckBookinDetails();
        clickOnShuntID(ShuntOrderNumber);
        BookOutShunt();
    }

    //Step 38.3 (For 9B Testcase)
    @Then("the Book in Shunt Truck pages should appear")
    public void theBookInShuntTruckPagesShouldAppear() throws InterruptedException {
        VerifyBookInShuntTruckpage();
        //For Consignment Truck Steps
        UserEntersTruckBookinDetailsForConsignment();
        clickOnShuntID(ShuntOrderNumber);
        BookOutShunt();
    }

    //Verify Storage Area and Complete Vessel Visit
    //Step 40
    @When("the user navigates to Cargo Storage Area")
    public void theUserNavigatesToCargoStorageArea() throws InterruptedException {
        UserNavigatesToCargoStorageAreas();
        UserNavigateToAreaStockyard();
    }
    //For 9B Storage Area
    @And("selects the Consignment Area")
    public void selectsTheConsignmentArea() throws InterruptedException
    {
        String areaname = testHarness.getData("StorageArea", "AreaName");
        System.out.println(areaname);
        areaselection_Consignment(areaname);
    }
    //For 9A Storage Area
    @And("selects the Common Stowage Area")
    public void selectsTheCommonStowageArea() throws InterruptedException
    {
        String areaname = testHarness.getData("StorageArea", "AreaName");
        System.out.println(areaname);
        areaselection_CommonStowage(areaname);
    }

    //Step 40.1
    @Then("all discharged products should be visible in Storage Areas")
    public void allDischargedProductsShouldBeVisibleInStorageAreas() throws InterruptedException {
        allDischargedProducts();
    }

    //** Step 41.3
    @Then("complete the Shunt Order")
    public void completeTheShuntOrder() throws InterruptedException
    {
        completeShuntorders();
    }
}
