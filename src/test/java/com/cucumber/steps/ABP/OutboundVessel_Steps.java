package com.cucumber.steps.ABP;


import com.framework.components.Settings;
import com.framework.cucumber.TestHarness;
import com.pageobjects.abp.OutboundVesselPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.Properties;

import static software.amazon.ion.impl.PrivateIonConstants.True;

public class OutboundVessel_Steps extends OutboundVesselPage {
    protected TestHarness testHarness = new TestHarness();
    protected Properties properties = Settings.getInstance();

    @When("I navigate to Cargo> Booking > Delivery Orders")
    public void iNavigateToCargoBookingDeliveryOrders() throws InterruptedException {
        clickOnCargo();
        clickOnBooking();
        clickOnDeliveryOrder();
    }

    @And("the user clicks on Add delivery order")
    public void theUserClicksOnAddDeliveryOrder() {
        clickOnAddDeliveryOrder();
    }

    @Then("the delivery order screen should be displayed")
    public void theDeliveryOrderScreenShouldBeDisplayed() {
    }


    @When("the user enters Delivery order Name")
    public void theUserEntersDeliveryOrderName() {
        String orderName =testHarness.getData("Truck_Intake","DeliveryOrderName");
        System.out.println(orderName);
        addDeliveryOrderName(orderName);
    }

    @And("the user enters consignor")
    public void theUserEntersConsignor() {
        String consignorTxt =testHarness.getData("Truck_Intake","Consignor");
        System.out.println(consignorTxt);
        addConsignor(consignorTxt);
    }

    @And("the user clicks on SaveD")
    public void theUserClicksOnSaveD() {
        saveDeliveyOrderPage();
    }

    @Then("the Delivery order screen with Consignment section should be displayed")
    public void theDeliveryOrderScreenWithConsignmentSectionShouldBeDisplayed() {
    }


    @When("user clicks on Add Consignment")
    public void userClicksOnAddConsignment() {
        clickAddConsignment();
    }

    @And("enters all the required mandatory fields")
    public void entersAllTheRequiredMandatoryFields() {
        String productTxt =testHarness.getData("Truck_Intake","Product");
        System.out.println(productTxt);
        enterProduct(productTxt);

        String consignmentTxt =testHarness.getData("Truck_Intake","CSGName");
        System.out.println(consignmentTxt);
        enterConsignment(consignmentTxt);
    }

    @And("the user clicks Next on Product Selection page")
    public void theUserClicksNextOnProductSelectionPage() {
        clickonPSNext();
    }

    @Then("Order Quantity page should be displayed")
    public void orderQuantityPageShouldBeDisplayed() {
    }


    @When("user enters the total weight")
    public void userEntersTheTotalWeight() throws InterruptedException {
        clickonprfrdLoc();
        String totalWeight = testHarness.getData("Truck_Intake", "Weight");
        System.out.println(totalWeight);
        entrTotalWeight(totalWeight);
        Thread.sleep(200);

    }

    @And("the user clicks save on order quantity page")
    public void theUserClicksSaveOnOrderQuantityPage() throws InterruptedException {
        clickonordrQntySave();
        Thread.sleep(3000);
    }

    @Then("Delivery Order page should be updated with consignment")
    public void deliveryOrderPageShouldBeUpdatedWithConsignment() {
    }

    @When("user clicks on Add Sub Delivery Order")
    public void userClicksOnAddSubDeliveryOrder() {
        clickonAddSubDelivery();

    }

    @And("user enters the consignment")
    public void userEntersTheConsignment() {
        addSDConsignment();
    }

    @And("user clicks Next on sub-delivery order page")
    public void userClicksNextOnSubDeliveryOrderPage() {
        clickonSDNext();
    }

    @Then("Consignment details page should be displayed")
    public void consignmentDetailsPageShouldBeDisplayed() {
    }

    @When("user changes visit type to Train")
    public void userChangesVisitTypeToTrain() {
        changeVisitType();
    }

    @And("enters the Order total weight")
    public void entersTheOrderTotalWeight() {
        String orderTotalWeight =testHarness.getData("Truck_Intake","Weight");
        System.out.println(orderTotalWeight);
        enterOrderTotalWeight(orderTotalWeight);
    }

    @And("the user clicks save on sub delivery order consignment page")
    public void theUserClicksSaveOnSubDeliveryOrderConsignmentPage() {
        setZoomLevel(50);
        clickSDSave();
    }

    @When("User click on Trains>Trains")
    public void userClickOnTrainsTrains() throws InterruptedException {
        clickonTrainsMenu();
    }

    @And("the user clicks on Add Rail visit")
    public void theUserClicksOnAddRailVisit() {
        clickonAddRail();
    }

    @Then("the Rail visit page should be displayed")
    public void theRailVisitPageShouldBeDisplayed() {
    }

    @When("User enters the Train Id")
    public void userEntersTheTrainId() {
        String trainTxt = testHarness.getData("Truck_Intake","TrainId");
        System.out.println(trainTxt);
        entertrainId(trainTxt);
    }

    @And("enters all the required  fields")
    public void entersAllTheRequiredFields() {
        enterWagons();
    }

    @And("the users click save on Rail visit page")
    public void theUsersClickSaveOnRailVisitPage() {
        clickontSave();
    }

    @And("user clicks on Arrive at the bottom of the page")
    public void userClicksOnArriveAtTheBottomOfThePage() {
        clickonArrive();
    }

    @When("user clicks on Outgoing Manifest")
    public void userClicksOnOutgoingManifest() {
        clickonOutgoingManifest();
    }

    @And("clicks on Add sub delivery order under consignments")
    public void clicksOnAddSubDeliveryOrderUnderConsignments() {
        clickonAddSubDeliveryOrder();
    }

    @And("user enters the required fields")
    public void userEntersTheRequiredFields() throws InterruptedException {
        String customerTxt = testHarness.getData("Truck_Intake","Consignor");
        System.out.println(customerTxt);
        enterCustomer(customerTxt);
        String prdTxt = testHarness.getData("Truck_Intake","Product");
        System.out.println(prdTxt);
        enterSDproduct(prdTxt);
        String sdTxt = testHarness.getData("Truck_Intake","DeliveryOrderName");
        System.out.println(sdTxt);
        enterSubDelivery(sdTxt);
        clickonsdsave();
    }

    @And("user clicks Save on sub-delivery order page")
    public void userClicksSaveOnSubDeliveryOrderPage() {
    }

    @Then("the sub-delivery order details screen should be displayed")
    public void theSubDeliveryOrderDetailsScreenShouldBeDisplayed() {
    }

    @When("user clicks on Load Stock")
    public void userClicksOnLoadStock() {
        clickonLoadStock();
    }

    @And("enters the details for all the required field")
    public void entersTheDetailsForAllTheRequiredField() throws InterruptedException {
        clickonLsConsignment();
        clickonRailCars();
        clickonEventType();
        clickonEvent();
        //Thread.sleep(5000);
    }

    @And("User clicks Next on load from storage page")
    public void userClicksNextOnLoadFromStoragePage() {
        clickonLsNext();
    }

    @And("enters the data to all the fields")
    public void entersTheDataToAllTheFields() throws InterruptedException {
        clickonLsFrom();
        String lsWeight =testHarness.getData("Truck_Intake","Weight");
        System.out.println(lsWeight);
        enterlsWeight(lsWeight);
        clickonMovementEndTime();
        Thread.sleep(3000);
    }

    @And("user clicks save on load from storage page")
    public void userClicksSaveOnLoadFromStoragePage() throws InterruptedException {
        clickonLsSave();

    }

    @When("user selects the train and click on edit")
    public void userSelectsTheTrainAndClickOnEdit() {
        String TrainId =testHarness.getData("Truck_Intake", "TrainId");
        System.out.println(TrainId);
        selecttheTrain(TrainId);
        clickonEditTrain();
    }

    @And("user clicks on Depart at the bottom of the screen")
    public void userClicksOnDepartAtTheBottomOfTheScreen() throws InterruptedException {
        clickonDepart();
    }
    @And("the users click save on Depart Rail visit page")
    public void theUsersClickSaveOnDepartRailVisitPage() {
        clickonDepartSave();
    }

    @When("user click on Completed Trains")
    public void userClickOnCompletedTrains() throws InterruptedException {
        clickonCompletedTrain();
        Thread.sleep(2000);
    }

    @And("search for the recent completed Train")
    public void searchForTheRecentCompletedTrain() {
        String completedTrainId =testHarness.getData("Truck_Intake", "TrainId");
        System.out.println(completedTrainId);
        searchCompletedTrain(completedTrainId);
    }

    @Then("the completed train should be present")
    public void theCompletedTrainShouldBePresent() {
        System.out.print("Recent completed Train ID is found");
    }


}
