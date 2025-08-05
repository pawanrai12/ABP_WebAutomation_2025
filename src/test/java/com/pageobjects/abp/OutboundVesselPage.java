package com.pageobjects.abp;

import com.framework.reusable.WebReusableComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class OutboundVesselPage extends WebReusableComponents {

    protected By cargo = By.xpath("//span[text()='Cargo']");
    protected By booking = By.xpath("//li[@label='Booking']");
    protected By deliveryOrder = By.xpath("//li[@label='Delivery Orders']");

    public void clickOnCargo() throws InterruptedException {
        Thread.sleep(300);
        clickElement(cargo);
    }
    public void clickOnBooking(){
        clickElement(booking);
    }
    public void clickOnDeliveryOrder(){
        clickElement(deliveryOrder);
    }

    protected By addBtn = By.xpath("//span[text()='Add']");

    public void clickOnAddDeliveryOrder(){
        clickElement(addBtn);
    }

    protected By deliveryOrderName= By.xpath("//input[@id='form-component-ordr.Name']");

    public void addDeliveryOrderName(String orderName){
        enterText(deliveryOrderName, orderName);
    }

    protected By dconsignor= By.xpath("//input[@id='form-component-Consignee.OriginalOrg_OrgTypeGuid']");
    protected By selectConsignorValue = By.xpath("(//button[@class='filter-select-option ng-binding'])[1]");

    public void addConsignor(String dConsignortxt){
        enterText(dconsignor, dConsignortxt);
        clickElement(selectConsignorValue);
    }
    protected By dSave = By.id("bt-save");
    public void saveDeliveyOrderPage(){
        clickElement(dSave);
    }
    protected By addConsignment = By.id("order.ConsignmentGrid.add");
    protected By dproduct = By.id("form-component-Product");
    protected By selectdProductValue = By.xpath("(//button[@class='filter-select-option ng-binding'])[1]");
    protected By consignmentStock = By.id("form-component-ConsignmentStock");
    protected By selectConsignmentValue = By.xpath("(//tr[@class='detailed-filter-select-option ng-scope'])[1]");
    protected By psNxtBtn = By.xpath("//span[text()='Next']");
    protected  By PrfrdLoc = By.id("form-component-onlyAllowDeliveryFromPreferredLocation");
    protected By inputTotalWeight = By.xpath("//input[@id='form-component-ocsc_8b4b9ff3-6792-e311-af78-00155d374301.DisplayQuantity']");
    protected By readTotalWeight = By.xpath("(//div[@class='numeric-input-readable input-text ng-binding' and normalize-space(text())='0.000'])[1]");
    protected  By OrdrqntySaveBtn = By.xpath("//button[@class='ng-scope button-primary']");

    public void clickAddConsignment(){
        clickElement(addConsignment);
    }

    public void enterProduct(String productTxt){
        enterText(dproduct, productTxt);
        clickElement(selectdProductValue);
    }

    public void enterConsignment(String consignmentTxt){
        enterText(consignmentStock, consignmentTxt);
        clickElement(selectConsignmentValue);
    }

    public void clickonPSNext(){
        clickElement(psNxtBtn);
    }
    public void clickonprfrdLoc(){
        setZoomLevel(50);
        clickElement(PrfrdLoc);
    }
    public void entrTotalWeight(String Weight){

       enterDynamicNumericInput(readTotalWeight,inputTotalWeight,Weight);
    }
    public void clickonordrQntySave(){
        clickElement(OrdrqntySaveBtn);
    }

    protected By addSubDeliverBtn = By.id("order.SubDeliveryOrderGrid.add");
    protected By sdConsignment = By.id("form-component-BL.OriginalGuid");
    protected By sdConsignmentValue = By.xpath("//button[@class='filter-select-option ng-binding']");
    protected By sdNext = By.xpath("//span[text()='Next']");
    protected By visitType = By.id("form-component-visitType");
    protected By readOrderTotalWeight = By.xpath("//div[@class='numeric-input-readable input-text ng-binding' and normalize-space(text())='0.000']");
    protected By inputOrderTotalWeight= By.xpath("//input[@id='form-component-ocsc_8b4b9ff3-6792-e311-af78-00155d374301.DisplayQuantity']");
    protected By sdSaveBtn = By.xpath("(//span[text()='Save'])[2]");
    public void clickonAddSubDelivery(){
        clickElement(addSubDeliverBtn);
    }
    public void addSDConsignment(){
        clickElement(sdConsignment);
        clickElement(sdConsignmentValue);
    }
    public void clickonSDNext(){
        clickElement(sdNext);
    }
    public void changeVisitType(){
        selectDropdownByMatchingText(visitType,"startswith","Train");
    }
    public void enterOrderTotalWeight(String Weight){
        enterDynamicNumericInput(readOrderTotalWeight,inputOrderTotalWeight,Weight);
    }
    public void clickSDSave(){
        clickElement(sdSaveBtn);
    }

    protected By trainsMenu = By.xpath("//span[text()='Trains']");
    protected  By trainsSubMenu =By.xpath("//a[text()='Trains']");
    protected  By addRail =By.xpath("//a[@id='Train_Visit.Grid.add']");
    protected By trainId = By.id("form-component-vst.Name");
    protected By wagons = By.id("form-component-numberOfWagons");
    protected By tSaveBtn = By.xpath("//button[@id='bt-save']");
    protected By arriveBtn = By.xpath("//span[text()='Arrive']");

    public void clickonTrainsMenu() throws InterruptedException {
        clickElement(trainsMenu);
        Thread.sleep(2000);
        clickElement(trainsSubMenu);
    }
    public void clickonAddRail(){
        clickElement(addRail);
    }
    public void entertrainId(String trainName){
        enterText(trainId, trainName);
    }
    public void enterWagons(){
        enterText(wagons,"2");
    }
    public void clickontSave(){
        clickElement(tSaveBtn);
    }
    public void clickonArrive(){
        clickElement(arriveBtn);
    }

    protected  By outgoingManifest = By.xpath("//span[text()='Outgoing Manifest']");
    protected  By addSubdlvryOrdr = By.xpath("//span[text()='Add Sub-Delivery Order']");
    protected  By cutomer = By.id("form-component-customer");
    protected  By customerValue = By.xpath("(//button[@class='filter-select-option ng-binding'])[1]");
    protected  By productSD = By.id("form-component-product");
    protected By productSDValue= By.xpath("(//button[@class='filter-select-option ng-binding'])[1]");
    protected By subDeliveryOrderNum = By.id("form-component-subDeliveryOrder");
    protected By subDeliveryOrderNumValue = By.xpath("(//tr[@class='detailed-filter-select-option ng-scope'])[1]");
    protected By addSubDeliveryOrderSaveBtn = By.xpath("//button[@class='ng-scope button-primary']");

    public void clickonOutgoingManifest(){
    clickElement(outgoingManifest);
    }
    public void clickonAddSubDeliveryOrder(){
        clickElement(addSubdlvryOrdr);
    }
    public void enterCustomer(String customerName) throws InterruptedException {
        enterText(cutomer,customerName);
        Thread.sleep(3000);
        clickElement(customerValue);
    }
    public void enterSDproduct(String productName) throws InterruptedException {
        enterText(productSD,productName);
        clickElement(productSDValue);
        Thread.sleep(2000);
    }
    public void enterSubDelivery(String subDelivery) throws InterruptedException {
        enterText(subDeliveryOrderNum,subDelivery);
        clickElement(subDeliveryOrderNumValue);
        Thread.sleep(2000);
    }
    public void clickonsdsave(){
        clickElement(addSubDeliveryOrderSaveBtn);
    }

    protected By loadStock = By.xpath("//span[text()='Load Stock']");
    protected By lsConsignment= By.id("form-component-consignmentStock");
    protected By lsConsignmentValue = By.xpath("//div[@class='detailed-filter-select-options']");
    protected By railCars= By.xpath("//div[@class='ms-parent multi-select ']");
    protected By railCarschkBox= By.xpath("(//input[@type='checkbox'])[1]");
    protected By eventType= By.id("form-component-eventType");
    protected By event= By.id("form-component-e.originalEventClassificationGUID");
    protected By lsNext= By.xpath("//span[text()='Next']");

    public void clickonLoadStock(){
        clickElement(loadStock);
    }
    public void clickonLsConsignment() throws InterruptedException {
        setZoomLevel(50);
        clickElement(movementEndTime);
        clickElement(today);
        Thread.sleep(2000);
        clickElement(movementEndTime);
        clickElement(lsConsignment);
        clickElement(lsConsignmentValue);
    }
    public void clickonRailCars() throws InterruptedException {
        clickElement(railCars);
        Thread.sleep(2000);
        clickElement(railCarschkBox);
        Thread.sleep(2000);
       // clickElement(railCars);
    }
    public void clickonEventType(){
        selectDropdownByMatchingText(eventType,"contains","Operation");
    }
    public void clickonEvent() throws InterruptedException {
        Thread.sleep(2000);
        selectDropdownByMatchingText(event,"contains","LOADING");
        Thread.sleep(2000);
    }
    public void clickonLsNext(){
        clickElement(lsNext);
    }

    protected By lsfrom = By.id("form-component-ssm_r.OriginalSourceConsignment_StockGuid");
    protected By lsFromValue = By.xpath("//div[@class='detailed-filter-select-options']");
    protected By lsreadWeight= By.xpath("//div[@class='numeric-input-readable input-text ng-binding']");
    protected By lsInputWeight= By.id("form-component-ssm_r_c_8b4b9ff3-6792-e311-af78-00155d374301.DisplayQuantity");
    protected By movementEndTime = By.xpath("//input[@class='form-control']");
    protected By today= By.xpath("//a[@title='Go to today']");
    protected By lsSave = By.xpath("//button[@data-ng-if=\"action.action == 'save'\" and .//span[text()='Save']]");
   //protected By lsSave = By.xpath("(//span[@class='text ng-binding' and normalize-space(text())='Save'])[2]");

    public void clickonLsFrom(){
        clickElement(lsfrom);
        clickElement(lsFromValue);
    }
    public void enterlsWeight(String weight){
        enterDynamicNumericInput(lsreadWeight,lsInputWeight,weight);
    }
    public void clickonMovementEndTime() throws InterruptedException {
        clickElement(movementEndTime);
        clickElement(today);
        Thread.sleep(2000);
        clickElement(movementEndTime);
    }
    public void clickonLsSave() throws InterruptedException {
        clickElement(lsSave);
        Thread.sleep(3000);
    }

    protected By tEdit = By.xpath("//span[@class='text ng-binding' and normalize-space(text())='Edit']");
    protected By depart = By.xpath("//span[@class='text ng-binding' and normalize-space(text())='Depart']");
    protected By departSave = By.xpath("//span[@class='text ng-binding' and normalize-space(text())='Save']");
    protected By completedTrains = By.xpath("//span[@class='text ng-binding' and normalize-space(text())='Completed Trains']");

    public void selecttheTrain(String trainId){
        try {
            List<WebElement> rows = driver.findElements(
                    By.xpath("//span[@data-column-name='trainId']"));


            System.out.println("Total rows found: " + rows.size());

            for (WebElement row : rows) {
                String rowText = row.getText();
                System.out.println("Row text: " + rowText);

                if (rowText.contains(trainId)) {
                    System.out.println("Clicking on row with TrainValue: " + trainId);
                    System.out.println("Row matched: " + rowText);
                    row.click();
                    return;
                }
            }

            System.out.println("TrainId not found: " + trainId);

        } catch (Exception e) {
            System.out.println("Exception while clicking trainId: " + e.getMessage());
        }
    }
    public void clickonEditTrain(){
        clickElement(tEdit);
    }
    public void clickonDepart() throws InterruptedException {
        clickElement(depart);
        Thread.sleep(5000);
    }
    public void clickonDepartSave(){
        clickElement(departSave);
    }
    public void clickonCompletedTrain(){
        clickElement(completedTrains);
    }
    public void searchCompletedTrain(String completedTrainId){
        try {
            List<WebElement> rows = driver.findElements(
                    By.xpath("//span[@data-column-name='trainID']"));


            System.out.println("Total rows found: " + rows.size());

            for (WebElement row : rows) {
                String rowText = row.getText();
                System.out.println("Row text: " + rowText);

                if (rowText.contains(completedTrainId)) {
                    System.out.println("Clicking on row with TrainValue: " + completedTrainId);
                    System.out.println("Row matched: " + rowText);
                    return;
                }
            }

            System.out.println("CompletedTrainId not found: " + completedTrainId);

        } catch (Exception e) {
            System.out.println("Exception while clicking CompleTrainId: " + e.getMessage());
        }
    }

}
