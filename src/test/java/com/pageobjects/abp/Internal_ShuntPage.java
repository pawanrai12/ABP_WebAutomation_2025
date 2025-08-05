package com.pageobjects.abp;

import com.aventstack.extentreports.ExtentTest;
import com.framework.report.Status;
import com.framework.reusable.WebReusableComponents;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import software.amazon.awssdk.services.ec2.model.Storage;

import java.time.Duration;
import java.util.List;


public class Internal_ShuntPage extends WebReusableComponents
{
    public static String GlobalWeightval;
    public static String ShuntOrderNumber;
    String Tareweight;
    String defaultweight;

    protected By Shuntorders = By.xpath("//li[@label='Shunt Orders']/a[text()='Shunt Orders']");
    protected By Cargo = By.xpath("//span[normalize-space(text())='Cargo']");
    protected By Booking = By.xpath("//span[normalize-space(text())='Booking']");
    protected By downArrow = By.xpath("//i[@class='pi pi-fw pi-chevron-down ml-auto pr-2']");

    //#Step 29.1
    protected void CargoBookingShuntOrders() throws InterruptedException {
        Thread.sleep(3000);
        setZoomLevel(50);
        Thread.sleep(2000);

        clickElement(Cargo);
        clickElement(downArrow);
        Thread.sleep(2000);
        scrollIntoViewAndClick(Shuntorders);
    }
    //#Step 29.2
    protected By ShuntordersTitle =By.xpath("//a[@class='grid content-stretch no-underline' and ./span[text()='Shunt Order']]");
    protected void ShuntOrderGrid()
    {
        verifyPageHeaderTitle(ShuntordersTitle,"Shunt Order","Shunt Orders Page:");
    }

    protected By ShuntAreaTable = By.xpath("//span[@data-column-name='name']");
    protected By editBtn = By.xpath("//span[@class='text ng-binding' and text()='Edit']");
    protected By completeBtn = By.xpath("//span[@class='text ng-binding' and text()='Complete']");
    protected By returnBtn = By.xpath("//span[@class='text ng-binding' and text()='Return']");


    protected void completeShuntorders() throws InterruptedException {
        clickOnTableElementByValue(ShuntOrderNumber,ShuntAreaTable);

        clickElement(editBtn);
        Thread.sleep(3000);
        clickElement(completeBtn);
        Thread.sleep(3000);
        clickElement(returnBtn);
        Thread.sleep(3000);

        clickElement(filterBtn);
        Thread.sleep(2000);

        clickElement(resetfilterBtn);
        Thread.sleep(2000);

        clickElement(addfilterBtn);
        Thread.sleep(2000);

        clickElement(orderstatus);
        Thread.sleep(2000);

        selectDropdownDD(selectorderstatus,"text","Equal");
        Thread.sleep(2000);

        selectDropdownDD(selectComplete,"text","Complete");
        Thread.sleep(2000);

        clickElement(applyfilterBtn);
        Thread.sleep(5000);

        printOrderAndMTDetails();
    }

    public void printOrderAndMTDetails() {
        try {
            // Find all elements for each data point
            List<WebElement> orderNumbers = driver.findElements(
                    By.xpath("//span[@data-column-name='name']")); // Added Order Number
            List<WebElement> orderStatuses = driver.findElements(
                    By.xpath("//span[@data-column-name='ordr.orderStatusDescription']"));
            List<WebElement> requiredMTs = driver.findElements(
                    By.xpath("//span[@data-column-name='requiredMT']"));
            List<WebElement> remainingMTs = driver.findElements(
                    By.xpath("//span[@data-column-name='remainingMT']"));
            List<WebElement> deliveredWeights = driver.findElements(
                    By.xpath("//span[@data-column-name='deliveredWeight']"));

            System.out.println("Total order rows found: " + orderNumbers.size());

            // Determine the number of rows to iterate, based on the smallest list size
            int minSize = Math.min(orderNumbers.size(),
                    Math.min(orderStatuses.size(),
                            Math.min(requiredMTs.size(),
                                    Math.min(remainingMTs.size(), deliveredWeights.size()))));

            if (minSize == 0) { // Check if any data is found
                String msg = "No order data found on the page.";
                System.out.println(msg);
                addTestLog(msg);
                return; // Exit if no data is found
            }

            // Check for mismatched list sizes and log warnings
            if (orderNumbers.size() != minSize || orderStatuses.size() != minSize ||
                    requiredMTs.size() != minSize || remainingMTs.size() != minSize ||
                    deliveredWeights.size() != minSize) {
                String warning = "Warning: Mismatched number of elements for different data points. " +
                        "Order Numbers: " + orderNumbers.size() +
                        ", Order Statuses: " + orderStatuses.size() +
                        ", Required MTs: " + requiredMTs.size() +
                        ", Remaining MTs: " + remainingMTs.size() +
                        ", Delivered Weights: " + deliveredWeights.size() +
                        ". Iterating based on smallest list size (" + minSize + ").";
                System.out.println(warning);
                addTestLog(warning);
            }

            // --- Output Header ---
            String header = String.format("| %-15s | %-18s | %-12s | %-14s | %-16s |",
                    "ORDER NUMBER", "ORDER STATUS", "REQUIRED MT", "REMAINING MT", "DELIVERED WEIGHT");
            String separator = "+-----------------+--------------------+--------------+----------------+------------------+";
            System.out.println(separator);
            System.out.println(header);
            System.out.println(separator);
            addTestLog("--- Order Details ---");

            // Iterate through the elements and print their text
            for (int i = 0; i < minSize; i++) {
                String orderNumber = orderNumbers.get(i).getText();
                String orderStatus = orderStatuses.get(i).getText();
                String requiredMT = requiredMTs.get(i).getText();
                String remainingMT = remainingMTs.get(i).getText();
                String deliveredWeight = deliveredWeights.get(i).getText();

                // Format each row neatly
                String rowOutput = String.format("| %-15s | %-18s | %-12s | %-14s | %-16s |",
                        orderNumber, orderStatus, requiredMT, remainingMT, deliveredWeight);
                System.out.println(rowOutput);
                addTestLog(rowOutput); // You might want to log each row separately or the whole block
            }
            System.out.println(separator); // Closing separator
            addTestLog("--- End Order Details ---");

        } catch (Exception e) {
            String error = "An exception occurred while reading order table data: " + e.getMessage();
            System.err.println(error); // Use System.err for errors
            addTestLog("[Error] " + error);
            e.printStackTrace(); // Print stack trace for debugging
        }
    }


    //Step 30.1
    protected By addunderShunt = By.id("ShuntOrder.Grid.add");
    protected void clickonaddONShuntpage(){ clickElement(addunderShunt);}

    //Step 30.2
    protected By ShuntOrderInfo = By.xpath("//h4[@class='section-heading ng-binding' and contains(text(), 'Shunt Order Information')]");
    protected void NewShuntOrderPage()
    {
        verifyPageHeaderTitle(ShuntOrderInfo, "Shunt Order Information","ShuntOrder-Page:");
    }

    protected By orderNoInput =By.id("form-component-ordr.Name");
    protected void enterOrdernumber(String ONName)
    {
        clickElement(orderNoInput);
        enterText(orderNoInput,ONName);
        System.out.println("The Order number inputted is:" +ONName);
    }
    //#Step 31
    //protected By orderNoInput =By.id("form-component-ordr.Name");
    protected By sourceTypeDrpdwn = By.id("form-component-ordr.FromStockEndPointTypeDescriptionID");
    protected By weightMethodDrpdwn = By.id("form-component-ordr.WeighMethodTypeDescriptionID");
    protected By consignorInput = By.id("form-component-Consignee.OriginalOrg_OrgTypeGuid");
    protected By consignorIndexvalue = By.xpath("(//li[@data-ng-repeat='option in options'])[1]");
    protected By maxRun = By.xpath("//input[@id='form-component-ordr.MaximumNumberOfRuns']");

    protected void EntersShuntOrderDetailsFor9A_Testcase(String Consigneedetails) throws InterruptedException {
        Thread.sleep(3000);
        setZoomLevel(50);
        selectDropdownDD(sourceTypeDrpdwn,"text","Stockpile");
        //Weighbridge
        selectDropdownDD(weightMethodDrpdwn,"text","Weighbridge");

        //Retare value is :
        enterText(maxRun,"0");

        //Entering the Consigner i.e, Consignee details
        enterText(consignorInput,Consigneedetails);
        System.out.println("The Entered Consignee Name is:>>>" +Consigneedetails);
        clickElement(consignorIndexvalue);

    }
    //#Step 31
    protected void EntersShuntOrderDetailsFor9B_Testcase(String Consigneedetails)
    {
        selectDropdownDD(sourceTypeDrpdwn,"text","Stockpile");
        //Weighbridge
        selectDropdownDD(weightMethodDrpdwn,"text","Weighbridge");

        //Retare value is :
        enterText(maxRun,"0");

        //Entering the Consigner i.e, Consignee details
        enterText(consignorInput,Consigneedetails);
        System.out.println("The Entered Consignee Name is:>>>" +Consigneedetails);
        clickElement(consignorIndexvalue);

    }
    //Step 31.2
    protected By saveBtnR = By.id("bt-save");
    protected By saveBtnBtm = By.xpath("//button[@data-ng-if=\"action.action == 'save'\" and .//span[text()='Save']]");
    protected By saveandaddBtn = By.xpath("//span[@class='text ng-binding' and text()='Save & Add']");
    protected By safeZone = By.xpath("//*[@id=\"CommtracVue\"]/div[1]/header/div/div[1]/nav/ol/li[1]/a/span/img");
    protected By saveaddBtn = By.xpath("//span[normalize-space(text())='Save & Add']");
    protected By saveendBtn = By.xpath("//span[normalize-space(text())='Save & End']");
    protected void UserclicksSaveNAdd()
    {
        JSClick(saveaddBtn);
    }

    protected void UserclicksSaveNEnd()
    {
        JSClick(saveendBtn);
    }
    //#Step 31.3
    protected By Msg1 = By.xpath("//div[@class='p-message-text']");
    protected void successnotification()
    {
        String a = getTextFromElement(Msg1);
        System.out.println("The success notification message is :" +a);
    }

    //#Step 32.1
    protected By addunderConsignementShunt =By.xpath("//*[@id='Order.ConsignmentGrid.add']");
    protected void clickonaddconsignment() throws InterruptedException {
        Thread.sleep(3000);
        setZoomLevel(50);
        clickElement(addunderConsignementShunt);}

    //#Step 32.2
    protected By productselectionTitle = By.xpath("//h1[@class='modal-header ng-binding ng-scope' and contains(text(), 'Product Selection')]");
    protected void ProductSelectionPage()
    {
        verifyPageHeaderTitle(productselectionTitle, "Product Selection","Product Selection Page:");
    }

    //#Step 33 for 9A Test Cases ***Pass the Stoarage Area ****
    protected By productInput = By.id("form-component-Product");
    protected By productIndexvalue = By.xpath("//ul[@class='filter-select-options']/li[1]/button");
    protected By consignmentstockInput = By.id("form-component-consignmentStock");
    protected By selectConsignmentstockindex1 = By.xpath("//div[@id='section-OrderManagementOrderFeatureSetShuntOrderOrder_OrderQuantityAddProdInfo']//table/tbody/tr[@class='detailed-filter-select-option ng-scope'][1]");

    protected void UserSelectsProductAndAConsignmentStockfor9ATc(String productdetails, String StorageArea) throws InterruptedException {

        // Entering Product value and selecting through index 1
        enterText(productInput,productdetails);
        System.out.println("The Entered Product Name is:>>>" + productdetails);
        clickElement(productIndexvalue);

        Thread.sleep(5000);
        setZoomLevel(45);

        //*** Passing USRN of SALT from Storage Area
        enterText(consignmentstockInput,StorageArea);
        System.out.println("The Entered Movement Storage Area Name is:>>>" + StorageArea);

        Thread.sleep(2000);
        JSClick(selectConsignmentstockindex1);

    }

    //#Step 33 for 9B Test Cases  ***Pass the Stoarage Area ****
    protected void UserSelectsProductAndAConsignmentStockfor9BTc(String productdetails,String StorageArea) throws InterruptedException {

        // Entering Product value and selecting through index 1
        enterText(productInput,productdetails);
        System.out.println("The Entered Product Name is:>>>" + productdetails);
        clickElement(productIndexvalue);

        Thread.sleep(5000);
        setZoomLevel(45);

        //*** Passing USRN of Consignment from Storage Area --From EXCEL Add here
        enterText(consignmentstockInput,StorageArea);
        System.out.println("The Entered Movement Storage Area Name is:>>>" + StorageArea);

        Thread.sleep(2000);
        JSClick(selectConsignmentstockindex1);

    }



    protected By nextBtn = By.xpath("//button[@class='ng-scope button-primary']");

    //#Step 33
    protected By orderquantityTitle = By.xpath("//h1[@class='modal-header ng-binding ng-scope']");

    protected void OrderQuantityPopup()
    {
        //expected : Product Selection
        verifyPageHeaderTitle(orderquantityTitle, "Order Quantity","Order Quantity Popup:");
    }

    //Step 34 for 9A Test case --change in Storage Area.
    protected By sourceinputOrder = By.id("form-component-ocssa.OriginalFromStorageAreaGuid");
    protected By destinationinput = By.xpath("//div[@id='section-ShuntOrder_OrderQuantityToFrom']//input[contains(@class, 'filter-select-input')]");
    protected By destByIndex = By.xpath("(//button[contains(@class, 'filter-select-option')])[2]");

    protected By readonlyDiv = By.xpath("//div[contains(@class, 'numeric-input-readable') and contains(text(), '0.000')]");
    protected By editableInput = By.xpath("//input[@id='form-component-DefaultWeight']");
    protected By readonlyDiv1 = By.xpath("(//div[@class='numeric-input-readable input-text ng-binding'])[3]");
    protected By editableInput1 = By.xpath("//input[contains(@id, 'form-component-') and contains(@id, '.DisplayQuantity')]");

    protected By clickout = By.xpath("(//span[@class='form-component-help ng-scope'])[23]");

    protected void clickOnRightSave() {
        JSClick(saveBtnR);
    }
    //Change the Destination input xpath
    protected void UserEntersShuntOrderQuantityDetailsFor_9A_TC(String GW) throws InterruptedException
    {
        scrollIntoViewAndClick(sourceinputOrder);
        clickElement(sourceinputOrder);
        Thread.sleep(2000);
        //**Select Source By Drop down **//
        selectDropdownDD(sourceinputOrder,"text","AUTOMATION AREA SALT - Common Stowage");
        //selectDropdownDS(sourceinputOrder);

        enterText(destinationinput," AUTOMATION AREA - Salt - Common Stowage Group Internal Move");
        //commenting this selection and adding based on Index selection
        scrollIntoViewAndClick(destByIndex);

        //This is the Default/Expected Weight per Shunt
        defaultweight = "30.000";
        //*** Passing GlobalWeight of Storage Area from excel
        GlobalWeightval = GW;
        System.out.println("The Entered Movement Storage Area Name is:>>>" + GW);

        //enterDynamicNumericInput(By readonlyDivBy, By inputBy, String valueToEnter)
        Thread.sleep(5000);
        enterDynamicNumericInput(readonlyDiv,editableInput, defaultweight);
        System.out.println("The entered Default value is:" + defaultweight);

        clickElement(clickout);
        Thread.sleep(5000);

        //Dynamically passing value of the weight
        enterDynamicNumericInput(readonlyDiv1,editableInput1,GlobalWeightval);
        System.out.println("The entered Total weight value is:" + GlobalWeightval);

    }

    protected void UserEntersShuntOrderQuantityDetailsFor_9B_TC(String GW) throws InterruptedException
    {
        scrollIntoViewAndClick(sourceinputOrder);
        clickElement(sourceinputOrder);
        Thread.sleep(2000);

        selectDropdownDD(sourceinputOrder,"text","AUTOMATION AREA Consignment");

        enterText(destinationinput,"AUTOMATION AREA - Consignment Internal Move");
        scrollIntoViewAndClick(destByIndex);

        //This is the Default/Expected Weight per Shunt
        defaultweight="30.000";
        //*** Passing GlobalWeight of Storage Area from excel
        GlobalWeightval = GW;
        System.out.println("The Entered Movement Storage Area Name is:>>>" + GW);

        //enterDynamicNumericInput(By readonlyDivBy, By inputBy, String valueToEnter)
        Thread.sleep(5000);
        enterDynamicNumericInput(readonlyDiv,editableInput, defaultweight);
        System.out.println("The entered Default value is:" + defaultweight);

        clickElement(clickout);
        Thread.sleep(5000);

        //Dynamically passing value of the weight
        //enterDynamicNumericInput(readonlyDiv1,editableInput1,"30");
        enterDynamicNumericInput(readonlyDiv1,editableInput1,GlobalWeightval);
        System.out.println("The entered Total weight value is:" + GlobalWeightval);

    }

    //Step 38
    protected By safezoneimgclick = By.xpath("//a[@class='cursor-pointer']//img[@alt='CommTrac brand logo']");
    protected By Trucks = By.xpath("//span[normalize-space(text())='Trucks']");
    protected By onsiteMenu = By.xpath("//a[@class='menu-label text-left' and text()='On Site']");
    protected void UserNavigatesToTrucksOnSite() throws InterruptedException {
        setZoomLevel(50);
        JSClick(Trucks);
        JSClick(onsiteMenu);
        Thread.sleep(3000);
        clickElement(safezoneimgclick);
        clickOnElementaction(Trucks);
        Thread.sleep(5000);
    }
    //Step 38.2 (For 9A Testcase)
    protected By addshunttruckBtn = By.xpath("//a[@id='Truck_Visit.Grid.addShunt']");

    protected void UserclickOnAddShuntTruck() throws InterruptedException {
        JSClick(addshunttruckBtn);
    }

    //Step 38.3 (For 9A Testcase)
    protected By BookInShuntTruckTitle = By.xpath("//a[./span[normalize-space(text())='Book In Shunt Truck']]");

    protected void VerifyBookInShuntTruckpage()
    {
        verifyPageHeaderTitle(BookInShuntTruckTitle,"Book In Shunt Truck","Book In Shunt Truck page Title is verified:");
    }

    protected By enterOrdernoInShuntdetails = By.xpath("//input[@id='form-component-originalOrderGuid']");
    protected By ordervalueIndex = By.xpath("(//button[contains(@class, 'filter-select-option')])[1]");

    protected By trucknoInput = By.xpath("//input[@id='form-component-truck']");
    protected By trucknoIndex = By.xpath("(//button[@class='filter-select-option ng-binding'])[1]");
    protected By driverInput = By.xpath("//input[@id='form-component-driver']");

    protected By TareInput = By.xpath("//input[@id='form-component-vtttg.DisplayTareWeight']");
    protected By Tarediv = By.xpath("//div[contains(@class,'numeric-input-readable') and contains(@class,'input-text')]");
    protected By safeclick = By.xpath("(//span[@class='icon-info form-component-help-icon'])[9]");

    protected By lastwashedInput = By.xpath("//input[@class='form-control']");
    protected By TruckchecksInput = By.xpath("//div[@id='form-component-truckChecks']//button[contains(@class, 'ms-choice')]");
    protected By Truckselectallcheckbox = By.xpath("//input[@type='checkbox' and @data-name='selectAll']");

    protected By lastloadInput3 = By.xpath("//input[@id='form-component-lastLoad3']");
    protected By lastloadInput2 = By.xpath("//input[@id='form-component-lastLoad2']");
    protected By lastloadInput1 = By.xpath("//input[@id='form-component-lastLoad1']");

    protected By lastloadMethodInput1 = By.xpath("//input[@id='form-component-lastLoad1cleaningMethod']");
    protected By lastloadMethodInput2 = By.xpath("//input[@id='form-component-lastLoad2cleaningMethod']");
    protected By lastloadMethodInput3 = By.xpath("//input[@id='form-component-lastLoad3cleaningMethod']");

    protected By lastloadIndexclick = By.xpath("//ul[@class='filter-select-options']//button[contains(@class, 'filter-select-option')][1]");

    protected By tasccnoinput = By.xpath("//input[@id='form-component-schemeRegistration']");

    protected By selectInsteside = By.id("form-component-insecticide_EntityTag_Guid");

    protected By selectAssurance = By.id("form-component-originalSchemeGuid");


    //check Ggross div xpath for now changed from 2 to 3
    protected By grossDiv = By.xpath("(//div[contains(@class, 'numeric-input-readable') and contains(@class, 'input-text')])[2]");
    protected By grossInp = By.xpath("//input[@id='form-component-vtttg.DisplayGrossWeight']");

    protected void infoclicksSafeclicks()
    {
        clickElement(safeclick);
    }

    protected void UserEntersTruckBookinDetails() throws InterruptedException {
        //ShuntOrderNumber is passing from global variable --same order which is passed in previous steps
        //so i have used -ONName from step no.31  the same order number

        //enterText(enterOrdernoInShuntdetails,"ShuntID 23062025 025231 AM");
        Thread.sleep(5000);
        enterText(enterOrdernoInShuntdetails,ShuntOrderNumber);
        JSClick(ordervalueIndex);

        // Check for Proper truck No. now selected through index1
        enterText(trucknoInput,"t");
        clickElement(trucknoIndex);

        // Entering driver data
        enterText(driverInput,"Tim");
        infoclicksSafeclicks();

        // Enter Tare weight and Declared Globally
        Tareweight = "14.000";
        enterDynamicNumericInput(Tarediv,TareInput,Tareweight);
        infoclicksSafeclicks();
        // *** As we are getting Truck override popup
        handleSaveAndOverride();

        Thread.sleep(5000);

    }
    protected By overridepopup = By.xpath("//h1[@class='modal-header ng-binding ng-scope' and normalize-space(text())='Override Validations']");
    protected By override_checkbox = By.id("form-component-ConfirmOverride");
    protected By override_reason = By.id("form-component-ReasonOverride");
    protected By override_confirmBtn = By.xpath("//span[@class='text ng-binding' and normalize-space(text())='Confirm']");

    protected void overridepopuphandling()
    {
        setZoomLevel(50);
        clickElement(override_checkbox);
        enterText(override_reason,"test");
        clickElement(override_confirmBtn);
    }

    public void handleSaveAndOverride() {
        try {
            // Perform clickOnSave function
            clickElement(saveBtnR);

            // Find the WebElement using the By locator
            WebElement popupElement = driver.findElement(overridepopup);

            // Check if override popup appears
            waitUntilElementVisible(overridepopup,10);

            if (popupElement.isDisplayed()) {
                // If popup appears, handle it
                overridepopuphandling();
            }
        } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e){
            // If popup doesn't appear
            System.out.println("Override popup doesn't appear, moving to next steps. Error: " + e.getMessage());
        }
    }

    protected void UserEntersTruckBookinDetailsForConsignment() throws InterruptedException {
        //ShuntOrderNumber is passing from global variable --same order which is passed in previous steps
        //so i have used -ONName from step no.31  the same order number

        //enterText(enterOrdernoInShuntdetails,"ShuntID 23062025 025231 AM");
        Thread.sleep(5000);
        enterText(enterOrdernoInShuntdetails,ShuntOrderNumber);
        JSClick(ordervalueIndex);

        // Check for Proper truck No. now selected through index1
        enterText(trucknoInput,"A");
        clickElement(trucknoIndex);

        // Entering driver data
        enterText(driverInput,"Raj");
        infoclicksSafeclicks();

        // Enter Tare weight and Declared Globally
        Tareweight = "14.000";
        enterDynamicNumericInput(Tarediv,TareInput,Tareweight);
        infoclicksSafeclicks();

        //last washed enter date
        enterDate(lastwashedInput,"Today");

        //Truck checks
        clickElement(TruckchecksInput);
        clickElement(Truckselectallcheckbox);
        infoclicksSafeclicks();

        //Last Load
        enterText(lastloadInput1,"MAIZE");
        JSClick(lastloadIndexclick);

        enterText(lastloadMethodInput1,"Washed");
        Thread.sleep(3000);
        JSClick(lastloadIndexclick);


        enterText(lastloadInput2,"MAIZE");
        Thread.sleep(3000);
        JSClick(lastloadIndexclick);


        enterText(lastloadMethodInput2,"Washed");
        Thread.sleep(3000);
        JSClick(lastloadIndexclick);

        enterText(lastloadInput3,"MAIZE");
        Thread.sleep(3000);
        JSClick(lastloadIndexclick);


        enterText(lastloadMethodInput3,"Washed");
        Thread.sleep(3000);
        JSClick(lastloadIndexclick);

        //Getting Text from the From and To Location
        //From = getTextFromElement(fromLocation);
        //From = getSelectedOptionText(fromLocation);
        //To = getSelectedOptionText(toLocation);

        // Entering Tascc no
        enterText(tasccnoinput,"TASCC1234");

        // Select Assurance
        selectDropdownByMatchingText(selectAssurance,"contains","TASCC (TASCC)");

        // Select Instectide --This has been removed from the Front Ends
        //selectDropdownByMatchingText(selectInsteside,"contains","N/A");
        clickElement(saveBtnR);
        Thread.sleep(5000);

    }
    //Declared at Top as public for Get text
    protected By fromLocation = By.xpath("//select[@id='form-component-ssm_r.OriginalFrom_StorageAreaGUID']");
    protected By toLocation = By.xpath("//select[@id='form-component-ssm_r.OriginalTo_StorageAreaGUID']");


    public void clickOnShuntID(String shuntValueToMatch) {
        try {
            List<WebElement> rows = driver.findElements(
                    By.xpath("//span[@data-column-name='tv.Orders']"));
            //By.xpath("//table[@class='table dataTable']//tbody[@class='ng-scope']//tr"));

            System.out.println("Total rows found: " + rows.size());

            for (WebElement row : rows) {
                String rowText = row.getText();
                System.out.println("Row text: " + rowText);  // Print every row for visibility

                if (rowText.contains(shuntValueToMatch)) {
                    System.out.println("Clicking on row with ShuntID: " + shuntValueToMatch);
                    System.out.println("Row matched: " + rowText);  // Print the matched row
                    row.click();
                    return;
                }
            }

            System.out.println("ShuntID not found: " + shuntValueToMatch);

        } catch (Exception e) {
            System.out.println("Exception while clicking ShuntID: " + e.getMessage());
        }
    }
    protected By grossDivBook = By.xpath("(//div[contains(@class, 'numeric-input-readable') and contains(@class, 'input-text')])[3]");
    protected By bookoutshuntBtn = By.xpath("//span[@class='text ng-binding' and text()='Book Out Shunt']");

    protected void BookOutShunt() throws InterruptedException {
        // Convert Weightval string to integer
        int totalWeight = 0;
        try {
            totalWeight = Integer.parseInt(GlobalWeightval.trim());
        } catch (NumberFormatException e) {
            // Log the error or handle it as needed if the weight value is not a valid number.
            System.err.println("Error parsing Weightval: " + GlobalWeightval);
            return; // Exit the method if parsing fails.
        }

        int netChunk = 30;
        int tareWeight = 14;

        int fullLoops = totalWeight / netChunk;
        int remainder = totalWeight % netChunk;

        // Loop for full chunks
        for (int i = 0; i < fullLoops; i++) {
            // Re-locate elements in each loop iteration to avoid StaleElementReferenceException.
            clickOnShuntID(ShuntOrderNumber);       // Select truck by ID
            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10)); // Increased wait time for stability
            wait1.until(ExpectedConditions.visibilityOfElementLocated(bookoutshuntBtn));
            JSClick(bookoutshuntBtn);               // Click book out button

            // Wait for gross input to appear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Increased wait time for stability
            wait.until(ExpectedConditions.visibilityOfElementLocated(grossInp));

            enterDynamicNumericInput(grossDivBook, grossInp, "44.000"); // Enter gross: 30 net + 14 tare
            //JSClick(safeclickgross);

            UserclicksSaveNAdd(); // Save and continue
        }

        // Handle final weight entry for remainder
        String grossStr;
        if (remainder > 0) {
            float finalGross = tareWeight + remainder;
            grossStr = String.format("%.3f", finalGross);
        } else {
            grossStr = "44.000";
        }

        // Re-locate elements for the final entry
        clickOnShuntID(ShuntOrderNumber);
        JSClick(bookoutshuntBtn);

        // Wait for gross input to appear for the final entry
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Increased wait time for stability
        wait.until(ExpectedConditions.visibilityOfElementLocated(grossInp));

        enterDynamicNumericInput(grossDivBook, grossInp, grossStr);
        //JSClick(safeclickgross);
        UserclicksSaveNEnd();
    }


    //Step 40
    // ******Cargo > Storage Area****
    protected By storageareaMenu = By.xpath("//a[@class='menu-label text-left' and text()='Storage Areas']");
    protected void UserNavigatesToCargoStorageAreas()
    {
        setZoomLevel(50);
        clickElement(Cargo);
        scrollIntoViewAndClick(storageareaMenu);
        clickElement(safeZone);
    }

    protected By automationarea = By.xpath("//span[@class='data-type-text' and text()='AUTOMATION AREA']");
    protected By viewStockyard = By.xpath("//span[@class='text ng-binding' and text() ='View Stockyard']");

    protected void UserNavigateToAreaStockyard() throws InterruptedException {
        Thread.sleep(2000);
        getEle(automationarea).get(1).click();
        scrollIntoViewAndClick(viewStockyard);
        Thread.sleep(2000);

        scrollIntoView(filterBtn);

    }

    //Step 40.1
    protected By viewcontentsBtn = By.xpath("//span[normalize-space(text())='View Contents']");
    protected By filterBtn = By.xpath("//button[@class='popup-toggle button-secondary']");
    protected By resetfilterBtn = By.xpath("//span[normalize-space(text())='Reset']");
    protected By addfilterBtn = By.xpath("//button[contains(@class, 'filter-add-button')]");
    protected By area = By.xpath("(//button[normalize-space(text())='Area'])[1]");
    protected By orderstatus = By.xpath("//button[normalize-space(text())='Order Status']");
    protected By selectorderstatus = By.id("form-component-orderStatusDescriptionID-0-operator");
    protected By selectComplete = By.id("form-component-orderStatusDescriptionID-0");
    protected By selectarea = By.id("form-component-sys.Name-0-operator");
    protected By areainput = By.xpath("//input[@id='form-component-sys.Name-0']");
    protected By applyfilterBtn = By.xpath("//button[contains(., 'Apply Filter')]");
    protected By pageselect = By.xpath("//select[@data-ng-model='clause.pageSize']");

    protected void areaselection_Consignment(String areaname) throws InterruptedException {
        clickElement(filterBtn);
        Thread.sleep(2000);
        clickElement(addfilterBtn);
        Thread.sleep(2000);
        clickElement(area);
        Thread.sleep(2000);
        selectDropdownDD(selectarea,"text","Contains");
        Thread.sleep(2000);
        //enterText(areainput,"Consignment");
        enterText(areainput,areaname);
        Thread.sleep(2000);
        clickElement(applyfilterBtn);
        Thread.sleep(2000);


        //clickOnArea("Consignment Internal Move");
        clickOnArea(areaname);


        Thread.sleep(2000);
        clickElement(viewcontentsBtn);

        Thread.sleep(6000);
        printConsignmentAndMTValues();

    }
    protected void areaselection_CommonStowage(String areaname) throws InterruptedException {
        clickElement(filterBtn);
        Thread.sleep(2000);
        clickElement(addfilterBtn);
        Thread.sleep(2000);
        clickElement(area);
        Thread.sleep(2000);
        selectDropdownDD(selectarea,"text","Contains");
        Thread.sleep(2000);
        //enterText(areainput,"Salt");
        enterText(areainput,areaname);
        Thread.sleep(2000);
        clickElement(applyfilterBtn);
        Thread.sleep(2000);


        //clickOnArea("Salt - Common Stowage Group Internal Move");
        clickOnArea(areaname);


        Thread.sleep(2000);
        clickElement(viewcontentsBtn);

        Thread.sleep(6000);
        printConsignmentAndMTValues();

    }


    public void clickOnArea(String ValueToMatch) {
        try {
            List<WebElement> rows = driver.findElements(
                    By.xpath("//span[@class='data-type-text']"));

            System.out.println("Total rows found: " + rows.size());

            for (WebElement row : rows) {
                String rowText = row.getText();
                System.out.println("Row text: " + rowText);  // Print every row for visibility

                if (rowText.contains(ValueToMatch)) {
                    System.out.println("Clicking on row with AreaID: " + ValueToMatch);
                    System.out.println("Row matched: " + rowText);  // Print the matched row
                    row.click();
                    return;
                }
            }

            System.out.println("Area not found: " + ValueToMatch);

        } catch (Exception e) {
            System.out.println("Exception while clicking on Area: " + e.getMessage());
        }
    }

    protected By storageareaTitle = By.xpath("//span[@class='p-menuitem-text' and text() ='Storage Areas']");
    protected void allDischargedProducts()
    {
        System.out.println("All the Products in the Storage Area is displayed");
        //verifyPageHeaderTitle(storageareaTitle,"Storage Areas","Storage Areas Page:");

    }
    //From the Table getting values of MT and Name
    protected void addTestLog(String message) {
        System.out.println("[Info] " + message);
    }

    protected void printConsignmentAndMTValues() {
        try {
            List<WebElement> consignmentNames = driver.findElements(
                    By.xpath("//span[@data-column-name='consignmentName']"));

            List<WebElement> metricTonnes = driver.findElements(
                    By.xpath("//span[@data-column-name='metricTonnes']"));

            System.out.println("Total consignment rows found: " + consignmentNames.size());

            // Determine the number of rows to iterate, based on the smallest list size
            int minSize = Math.min(consignmentNames.size(), metricTonnes.size());

            if (minSize == 0) {
                String msg = "No consignment data found on the page.";
                System.out.println(msg);
                addTestLog(msg);
                return; // Exit if no data is found
            }

            if (consignmentNames.size() != metricTonnes.size()) {
                String warning = "Warning: Mismatched number of Consignment Names and M/T values. " +
                        "Consignments: " + consignmentNames.size() +
                        ", M/T: " + metricTonnes.size() +
                        ". Iterating based on smallest list size (" + minSize + ").";
                System.out.println(warning);
                addTestLog(warning);
            }

            // --- Output Header ---
            String header = String.format("| %-30s | %-15s |", "CONSIGNMENT NAME", "METRIC TONNES");
            String separator = "+--------------------------------+-----------------+";
            System.out.println(separator);
            System.out.println(header);
            System.out.println(separator);
            addTestLog("--- Consignment Details ---");


            for (int i = 0; i < minSize; i++) {
                String consignmentName = consignmentNames.get(i).getText();
                String metricTonneValue = metricTonnes.get(i).getText(); // Always safe due to minSize

                // Format each row neatly
                String rowOutput = String.format("| %-30s | %-15s |", consignmentName, metricTonneValue);
                System.out.println(rowOutput);
                addTestLog(rowOutput);
            }
            System.out.println(separator); // Closing separator
            addTestLog("--- End Consignment Details ---");

        } catch (Exception e) {
            String error = "An exception occurred while reading table data: " + e.getMessage();
            System.err.println(error); // Use System.err for errors
            addTestLog("[Error] " + error);
            e.printStackTrace(); // Print stack trace for debugging
        }
    }
}
