package com.pageobjects.abp;

import com.framework.context.ScenarioContext;
import com.framework.report.Status;
import com.framework.reusable.WebReusableComponents;
import org.openqa.selenium.*;
import com.framework.components.Settings;
import com.framework.cucumber.TestHarness;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.framework.components.ApplitoolsOperations;
import com.framework.components.ScriptHelper;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

import static com.framework.selenium.SeleniumTestParameters.getAppUrl;
import static com.pageobjects.abp.VesselVisitPage.*;
import static org.apache.poi.sl.draw.geom.GuideIf.Op.val;
import static com.pageobjects.abp.VesselVisitPage.USRN;
import static com.pageobjects.abp.VesselVisitPage.JOBID1;



public class ShuntPage extends WebReusableComponents
{
    //Top
    public static String ShuntOrderNumber;
    String Tareweight;
    String defaultweight;
    String From;
    String To;


    protected ApplitoolsOperations appliTools = new ApplitoolsOperations();

    protected void launchApp()
    {
        launchUrl(getAppUrl());
        maximizeWindow();
        appliTools.captureContent("");
    }
    protected By Cargo = By.xpath("//span[normalize-space(text())='Cargo']");
    protected By Booking = By.xpath("//span[normalize-space(text())='Booking']");
    protected By downArrow = By.xpath("//i[@class='pi pi-fw pi-chevron-down ml-auto pr-2']");
    //protected By Shuntorders =By.xpath("//a[@class='menu-label text-left d-block w-full' and text()='Shunt Orders']");

    protected By Shuntorders = By.xpath("//li[@label='Shunt Orders']/a[text()='Shunt Orders']");

    protected void CargoBookingShuntOrders() throws InterruptedException {
        Thread.sleep(3000);
        setZoomLevel(50);
        Thread.sleep(2000);

        clickElement(Cargo);
        clickElement(downArrow);
        Thread.sleep(2000);
        scrollIntoViewAndClick(Shuntorders);
    }
    protected By ShuntordersTitle =By.xpath("//a[@class='grid content-stretch no-underline' and ./span[text()='Shunt Order']]");

    protected void ShuntOrderGrid()
    {
        verifyPageHeaderTitle(ShuntordersTitle,"Shunt Order","Shunt Orders Page:");
    }

    protected By addunderShunt = By.id("ShuntOrder.Grid.add");

    //Step 30
    protected void clickonaddONShuntpage(){ clickElement(addunderShunt);}


    protected By ShuntOrderInfo = By.xpath("//h4[@class='section-heading ng-binding' and contains(text(), 'Shunt Order Information')]");
    protected void NewShuntOrderPage()
    {
        verifyPageHeaderTitle(ShuntOrderInfo, "Shunt Order Information","ShuntOrder-Page:");
    }

    protected By orderNoInput =By.id("form-component-ordr.Name");
    protected By sourceTypeDrpdwn = By.id("form-component-ordr.FromStockEndPointTypeDescriptionID");
    protected By weightMethodDrpdwn = By.id("form-component-ordr.WeighMethodTypeDescriptionID");
    protected By consignorInput = By.id("form-component-Consignee.OriginalOrg_OrgTypeGuid");
    protected By consignorIndexvalue = By.xpath("(//li[@data-ng-repeat='option in options'])[1]");
    protected By cafetra = By.xpath("//button[@class='filter-select-option ng-binding' and text()=' CEFETRA  (CEF) - Customer ']");
    protected By maxRun = By.xpath("//input[@id='form-component-ordr.MaximumNumberOfRuns']");
    protected By vesselValue = By.xpath("//*[@id=\"form-component-ordr.FromStockEndPointTypeDescriptionID\"]/option[4]");

    //#Step 31
    protected void EntersShuntOrderDetails()
    {
        selectDropdownDD(sourceTypeDrpdwn,"text","Vessel");
        //Unmanned Weighbridge
        selectDropdownDD(weightMethodDrpdwn,"text","Weighbridge");

        entercongDetails();
        //Retare value is :
        enterText(maxRun,"0");

    }
    //#Step 31
    protected void EntersShuntOrderDetailsFor1D_Testcase()
    {
        selectDropdownDD(sourceTypeDrpdwn,"text","Vessel");
        //Unmanned Weighbridge
        selectDropdownDD(weightMethodDrpdwn,"text","Unmanned Weighbridge");

        entercongDetails();
        //Retare value is :
        enterText(maxRun,"0");

    }


    protected void enterOrdernumber(String ONName)
    {
        clickElement(orderNoInput);
        enterText(orderNoInput,ONName);
        System.out.println("The Order number inputted is:" +ONName);

    }
    protected void entercongDetails()
    {
        enterText(consignorInput,"Ce");
        clickElement(cafetra);
    }

    protected By saveBtnR = By.id("bt-save");
    protected By saveBtnBtm = By.xpath("//button[@data-ng-if=\"action.action == 'save'\" and .//span[text()='Save']]");
    protected By saveandaddBtn = By.xpath("//span[@class='text ng-binding' and text()='Save & Add']");
    protected By safeZone = By.xpath("//*[@id=\"CommtracVue\"]/div[1]/header/div/div[1]/nav/ol/li[1]/a/span/img");

    protected void clickOnRightSave() {
        JSClick(saveBtnR);
    }
    protected void clickOnBottomSave() {
        clickElement(saveBtnBtm);
    }

    //#Step 31.3
    protected By Msg1 = By.xpath("//div[@class='p-message-text']");
    protected void successnotification()
    {
        String a = getTextFromElement(Msg1);
        System.out.println("The success notification message is :" +a);
    }

    //#Step 32
    protected By addunderConsignementShunt =By.xpath("//*[@id='Order.ConsignmentGrid.add']");
    protected void clickonaddconsignment() throws InterruptedException {
        Thread.sleep(3000);
        setZoomLevel(50);
        clickElement(addunderConsignementShunt);}

    protected By productselectionTitle = By.xpath("//h1[@class='modal-header ng-binding ng-scope' and contains(text(), 'Product Selection')]");
    protected void ProductSelectionPage()
    {
        verifyPageHeaderTitle(productselectionTitle, "Product Selection","Product Selection Page:");
    }

    //#Step 32
    protected By productInput = By.id("form-component-Product");
    protected By productIndexvalue = By.xpath("//ul[@class='filter-select-options']/li[1]/button");
    protected By consignmentstockInput = By.id("form-component-consignmentStock");
    protected By selectConsignmentstockindex1 = By.xpath("//div[@id='section-OrderManagementOrderFeatureSetShuntOrderOrder_OrderQuantityAddProdInfo']//table/tbody/tr[@class='detailed-filter-select-option ng-scope'][1]");
    //protected By selectConsignmentstockindex1 = By.xpath("(//tr[@class='detailed-filter-select-option ng-scope'])[1]");

    //#Step 33 --USRN Value has to pass
    protected void UserSelectsProductAndAConsignmentStock(String productdetails) throws InterruptedException {

        // Entering Product value and selecting through index 1
        enterText(productInput,productdetails);
        System.out.println("The Entered Product Name is:>>>" + productdetails);
        clickElement(productIndexvalue);

        Thread.sleep(5000);
        setZoomLevel(45);
        //****Globally Passing USRN
        //enterText(consignmentstockInput,"SALT:\"04072025\"\"040157 AM\"");

        // @@@@ Retrieve USRN from ScenarioContext
        String usrn = (String) ScenarioContext.get("USRN");
        if (usrn == null) {
            throw new IllegalStateException("USRN is not set in ScenarioContext");
        }

        enterText(consignmentstockInput, usrn);
        System.out.println("The validation on Shunt Product selection:" + usrn);

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

    protected By sourceinputOrder = By.id("form-component-ocssa.OriginalFromStorageAreaGuid");
    protected By destinationinput = By.xpath("//div[@id='section-ShuntOrder_OrderQuantityToFrom']//input[contains(@class, 'filter-select-input')]");
    protected By destAuto = By.xpath("//button[@class='filter-select-option ng-binding' and normalize-space(text())='AUTOMATION AREA - Maize - Common Stowage Area - [MAIZE, Weight]']");

    protected By readonlyDiv = By.xpath("//div[contains(@class, 'numeric-input-readable') and contains(text(), '0.000')]");
    protected By editableInput = By.xpath("//input[@id='form-component-DefaultWeight']");
    protected By readonlyDiv1 = By.xpath("(//div[@class='numeric-input-readable input-text ng-binding'])[3]");
    protected By editableInput1 = By.xpath("//input[contains(@id, 'form-component-') and contains(@id, '.DisplayQuantity')]");

    protected By clickout = By.xpath("(//span[@class='form-component-help ng-scope'])[23]");

    //#Step 34
    protected void UserEntersShuntOrderQuantityDetails() throws InterruptedException
    {
        Thread.sleep(5000);
        setZoomLevel(50);
        scrollIntoViewAndClick(sourceinputOrder);
        clickElement(sourceinputOrder);
        Thread.sleep(5000);
        selectDropdownDS(sourceinputOrder);

        enterText(destinationinput,"AUTOMATION AREA - Maize - Common Stowage Area");
        scrollIntoViewAndClick(destAuto);

        //ordertoatlweight="60.000";
        defaultweight="30.000";

        //enterDynamicNumericInput(By readonlyDivBy, By inputBy, String valueToEnter)
        Thread.sleep(5000);
        enterDynamicNumericInput(readonlyDiv,editableInput, defaultweight);
        System.out.println("The entered Default value is:" + defaultweight);

        clickElement(clickout);
        Thread.sleep(5000);
        //Dynamically passing value of the weight
        enterDynamicNumericInput(readonlyDiv1,editableInput1,Weightval);
        System.out.println("The entered Total weight value is:" + Weightval);

    }
    protected By destByIndex = By.xpath("(//button[contains(@class, 'filter-select-option')])[2]");
    protected void UserEntersShuntOrderQuantityDetailsFor_1B_TC() throws InterruptedException
    {
        Thread.sleep(5000);
        setZoomLevel(50);
        scrollIntoViewAndClick(sourceinputOrder);
        clickElement(sourceinputOrder);
        Thread.sleep(5000);
        selectDropdownDS(sourceinputOrder);

        enterText(destinationinput,"AUTOMATION AREA - Consignment Group");
        //commenting this selection and adding based on Index selection
                //scrollIntoViewAndClick(destAuto);
        scrollIntoViewAndClick(destByIndex);

        //ordertoatlweight="60.000";
        defaultweight="30.000";

        //enterDynamicNumericInput(By readonlyDivBy, By inputBy, String valueToEnter)
        Thread.sleep(5000);
        enterDynamicNumericInput(readonlyDiv,editableInput, defaultweight);
        System.out.println("The entered Default value is:" + defaultweight);

        clickElement(clickout);
        Thread.sleep(5000);
        //Dynamically passing value of the weight
        enterDynamicNumericInput(readonlyDiv1,editableInput1,Weightval);
        System.out.println("The entered Total weight value is:" + Weightval);

    }


    //#Step 35 Trucks > Add New > Shunt
    protected By Trucks = By.xpath("//span[normalize-space(text())='Trucks']");
    protected By Shunt = By.xpath("//a[contains(@class, 'menu-label') and text()='Shunt']");
    protected By TruckDownarrow = By.xpath("//i[@class='pi pi-fw pi-chevron-down ml-auto pr-2']");

    protected void UserNavigatesToTrucksAddNewShunt()
    {
        setZoomLevel(70);
        clickElement(Trucks);
        clickElement(TruckDownarrow);
        clickElement(Shunt);
        JSClick(safezoneimgclick);

    }
    //#Step 35.3
    protected By BookInshuntTitle = By.xpath("//a[@class='grid content-stretch no-underline']//span[@class='p-menuitem-text']");
    protected void BookInShuntTruckPage()
    {
        verifyPageHeaderTitle(BookInshuntTitle,"Book In Shunt Truck","Book In Shunt Truck Page:");
    }

    //#Step 36
    protected By safezoneimgclick = By.xpath("//a[@class='cursor-pointer']//img[@alt='CommTrac brand logo']");
    protected By enterOrdernoInShuntdetails = By.xpath("//input[@id='form-component-originalOrderGuid']");
    protected By ordervalueIndex = By.xpath("(//button[contains(@class, 'filter-select-option')])[1]");

    protected By trucknoInput = By.xpath("//input[@id='form-component-truck']");
    protected By trucknoIndex = By.xpath("(//button[@class='filter-select-option ng-binding'])[1]");
    protected By driverInput = By.xpath("//input[@id='form-component-driver']");

    protected By TareInput = By.xpath("//input[@id='form-component-vtttg.DisplayTareWeight']");
    protected By Tarediv = By.xpath("//div[contains(@class,'numeric-input-readable') and contains(@class,'input-text')]");

    protected By lastwashedInput = By.xpath("//input[@class='form-control']");
    protected By TruckchecksInput = By.xpath("//div[@id='form-component-truckChecks']//button[contains(@class, 'ms-choice')]");
    protected By Truckselectallcheckbox = By.xpath("//input[@type='checkbox' and @data-name='selectAll']");
    protected By safeclick = By.xpath("(//span[@class='icon-info form-component-help-icon'])[9]");

    protected By lastloadInput3 = By.xpath("//input[@id='form-component-lastLoad3']");
    protected By lastloadInput2 = By.xpath("//input[@id='form-component-lastLoad2']");
    protected By lastloadInput1 = By.xpath("//input[@id='form-component-lastLoad1']");

    protected By lastloadBeans = By.xpath("//button[contains(@class, 'filter-select-option') and normalize-space(text())='BEANS (BEANS)']");
    protected By loadMethodWashed = By.xpath("//button[contains(@class, 'filter-select-option ng-binding') and normalize-space(text())='WASHED (WASHED)']");

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

        //last washed enter date
        enterDate(lastwashedInput,"Today");

        //Truck checks
        clickElement(TruckchecksInput);
        clickElement(Truckselectallcheckbox);
        infoclicksSafeclicks();

        //Last Load
        enterText(lastloadInput1,"beans");
        JSClick(lastloadIndexclick);

        enterText(lastloadMethodInput1,"Washed");
        Thread.sleep(3000);
        JSClick(lastloadIndexclick);


        enterText(lastloadInput2,"beans");
        Thread.sleep(3000);
        JSClick(lastloadIndexclick);


        enterText(lastloadMethodInput2,"Washed");
        Thread.sleep(3000);
        JSClick(lastloadIndexclick);

        enterText(lastloadInput3,"beans");
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

    }
    //Declared at Top as public for Get text
    protected By fromLocation = By.xpath("//select[@id='form-component-ssm_r.OriginalFrom_StorageAreaGUID']");
    protected By toLocation = By.xpath("//select[@id='form-component-ssm_r.OriginalTo_StorageAreaGUID']");

    protected void verifyingVesselToStorageLocation()
    {
        System.out.println("The From Location is:" + From);
        System.out.println("The To Location is:" + To);
    }
    protected By savenextBtn = By.xpath("//span[normalize-space(text())='Save And Next']");

    protected void UserclicksSaveNext()
    {
        JSClick(savenextBtn);
    }
    protected By saveaddBtn = By.xpath("//span[normalize-space(text())='Save & Add']");

    protected void UserclicksSaveNAdd()
    {
        JSClick(saveaddBtn);
    }
    protected By saveendBtn = By.xpath("//span[normalize-space(text())='Save & End']");

    protected void UserclicksSaveNEnd()
    {
        JSClick(saveendBtn);
    }
    protected By truckvisitTitle = By.xpath("//span[@class='p-menuitem-text' and normalize-space(text())='Truck Visit']");

    protected void BookInShuntTruckpage()
    {
        verifyPageHeaderTitle(truckvisitTitle,"Truck Visit","Book In Shunt Truck page Title is verified:");
    }

    protected By bookoutshuntBtn = By.xpath("//span[@class='text ng-binding' and text()='Book Out Shunt']");
    //protected By Truckindex1 = By.xpath("(//table//tbody[@class='ng-scope']//tr[@data-index='0'])[1]");
    //protected By Tindex = By.xpath("//*[@id=\"commtrac-angular\"]/div[1]/div/div/div/div[3]/div/table/tbody/tr[2]/td[1]/table/tbody[@class='ng-scope']/tr[@data-index='0']");
    //protected By Oindex = By.xpath("//table[@class='table dataTable']//tbody[@class='ng-scope']//span[@data-column-name='tv.Orders' and text()='ShuntID\"24062025\"\"020844 PM\"']");
    //Index is not picking

    protected void selectTheTruck() throws InterruptedException {
//        JSClick(safeZone);
//        Thread.sleep(5000);
//        setZoomLevel(50);
//        //Pass value from Globally Data ---ShuntOrderNumber
//        //below Table matching code is written
//        clickOnShuntID(ShuntOrderNumber);
//        JSClick(bookoutshuntBtn);
    }
    // This the Row data of Truck been clicked
    protected By refreshTable = By.xpath("//div[@class='grid-control-button-right']//button[@data-tooltip='Refresh']");

//
//    public void clickOnShuntID(String shuntValueToMatch) throws InterruptedException {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Robust wait time
//
//        try {
//            System.out.println("Attempting to click on Shunt ID: " + shuntValueToMatch);
//
//            // Wait for and click the refresh button
//            wait.until(ExpectedConditions.visibilityOfElementLocated(refreshTable));
//            WebElement refreshButton = wait.until(ExpectedConditions.elementToBeClickable(refreshTable));
//            System.out.println("Clicking refresh button...");
//            refreshButton.click();
//
//            // Wait for table to reload after refresh
//            Thread.sleep(5000); // Consider replacing with dynamic wait later
//            System.out.println("Waited for 5 seconds after refresh.");
//
//            // Locate all rows again
//            List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//                    By.xpath("//span[@data-column-name='tv.Orders']")));
//
//            System.out.println("Total rows found: " + rows.size());
//
//            for (WebElement row : rows) {
//                String rowText = row.getText();
//                System.out.println("Row text: " + rowText);
//
//                if (rowText.contains(shuntValueToMatch)) {
//                    System.out.println("Match found! Clicking on row: " + rowText);
//                    try {
//                        row.click();
//
//                        // Wait for next screen indicator after click
//                        wait.until(ExpectedConditions.visibilityOfElementLocated(
//                                By.xpath("//span[@class='text ng-binding' and text()='Book Out Shunt']")));
//
//                        System.out.println("Clicked successfully and navigated to next screen.");
//                        return;
//                    } catch (Exception clickException) {
//                        System.out.println("Click failed on matched row. Reason: " + clickException.getMessage());
//                        throw new RuntimeException("Click failed on matched ShuntID row: " + rowText, clickException);
//                    }
//                }
//            }
//
//            // If we reach here, no match was found
//            String message = "ShuntID NOT FOUND in table: " + shuntValueToMatch;
//            System.out.println(message);
//            throw new NoSuchElementException(message); // Explicit failure for missing ShuntID
//
//        } catch (Exception e) {
//            System.out.println("Exception while clicking ShuntID: " + e.getMessage());
//            e.printStackTrace(); // Full trace for debugging
//            throw e; // Re-throw to fail test
//        }
//    }



    // The logic is correct but adding wait
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



//    public void clickOnShuntID(String shuntValueToMatch) {
//        try {
//            List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table dataTable']//tbody[@class='ng-scope']//tr"));
//
//            for (WebElement row : rows) {
//                if (row.getText().contains(shuntValueToMatch)) {
//                    System.out.println("Clicking on row with ShuntID: " + shuntValueToMatch);
//                    row.click();
//                    return;
//                }
//            }
//
//            System.out.println("ShuntID not found: " + shuntValueToMatch);
//
//        } catch (Exception e) {
//            System.out.println("Exception while clicking ShuntID: " + e.getMessage());
//        }
//    }
    //Step 38.3
    protected By grossDivBook = By.xpath("(//div[contains(@class, 'numeric-input-readable') and contains(@class, 'input-text')])[3]");
    protected By safeclickgross = By.xpath("(//span[@class='icon-info form-component-help-icon'])[25]");


    //Old logic --above is the updated one
    protected void BookOutShunt() throws InterruptedException {
        // Convert Weightval string to integer
        int totalWeight = 0;
        try {
            totalWeight = Integer.parseInt(Weightval.trim());
        } catch (NumberFormatException e) {
            // Log the error or handle it as needed if the weight value is not a valid number.
            System.err.println("Error parsing Weightval: " + Weightval);
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




    protected By errorTitle = By.xpath("//div[contains(@class, 'form-messages')]//li[contains(text(), 'Form is invalid')]");
    protected By confirmPopup = By.xpath("//button[contains(@class, 'modal-confirm')]/span[normalize-space(.)='Confirm']");

    //DUE to BUG
    protected By editshuntBtn = By.xpath("//span[@class='text ng-binding' and text()='Edit']");

    protected void selectTheTruckForEdit() throws InterruptedException {
        //clickElement(safeZone);
        Thread.sleep(5000);
        setZoomLevel(50);
        //Pass value from Globally Data ---ShuntOrderNumber
        //below Table matching code is written
        clickOnShuntID(ShuntOrderNumber);
        JSClick(editshuntBtn);
        // Select Instectide
        Thread.sleep(5000);
        selectDropdownByMatchingText(selectInsteside,"contains","N/A");

        JSClick(saveBtnR);
    }
    //Step 38.4
    protected By bookoutpageTitle = By.xpath("//span[@class='p-menuitem-text' and text()='Book Out Shunt']");
    protected void TruckBookOutPageShouldAppear()
    {
        verifyPageHeaderTitle(bookoutpageTitle,"Book Out Shunt","Book Out Shunt Page:");
    }

    //Step 39
    protected void UserEntersGrossWeight()
    {
        // Select Gross weight
        enterDynamicNumericInput(grossDiv,grossInp,"44.000");
        infoclicksSafeclicks();

        clickOnRightSave();
    }
    //Step 39.1
    protected By netinput = By.xpath("//input[@id='form-component-vtttg.DisplayNettWeight']");
    protected void nettWeightIsCalculated()
    {
        String net = getTextFromElement(netinput);
        System.out.println("The Net weight calculated value: " + net);
    }
       // saveandaddBtn
    // ******Cargo > Storage Area****
    protected By storageareaMenu = By.xpath("//a[@class='menu-label text-left' and text()='Storage Areas']");

    //Step 40
    protected void UserNavigatesToCargoStorageAreas()
    {
        clickElement(Cargo);
        scrollIntoViewAndClick(storageareaMenu);
        clickElement(safeZone);
    }
//    protected By newarea = By.xpath("(//span[@data-column-name='stk.Name'])[2]");
//    protected By filterBtn = By.xpath("//span[@class='text ng-binding' and text()=' Filter ']");
//    protected By AddfilterBtn = By.xpath("//span[@class='text ng-binding' and text()='Add Filter']");
//    protected By namefilter = By.xpath("//button[@class='ng-binding' and text()=' Name ']");
//    protected By nameselect = By.id("form-component-stk.Name-0-operator");
//    protected By nameinput = By.id("form-component-stk.Name-0");
//    protected By applyfilterBtn = By.xpath("//button[@class='button-primary']");
//
    protected By automationarea = By.xpath("//span[@class='data-type-text' and text()='AUTOMATION AREA']");
    protected By viewStockyard = By.xpath("//span[@class='text ng-binding' and text() ='View Stockyard']");

//    protected void filterselection()
//    {
//        clickElement(filterBtn);
//        clickElement(AddfilterBtn);
//        clickElement(namefilter);
//        selectDropdownByValue(nameselect,"Name");
//        enterText(nameinput,"AUTOMATION AREA");
//        clickElement(applyfilterBtn);
//    }
    protected void UserNavigateToAreaStockyard() throws InterruptedException {
        Thread.sleep(6000);
//        getEle(automationarea);

            getEle(automationarea).get(1).click();
//        String text1 = list.get(0).getText();
//        System.out.println(text1+" "+"First element");
//        String text = list.get(1).getText();
//        System.out.println(text1+" "+"Second element");
//        list.get(1).click();

      // scrollIntoViewAndClick(getEle(automationarea).get(1));
      //  filterselection();
        //clickElement(newarea);
        //clickElement(automationarea);
        //clickElement(viewStockyard);
        scrollIntoViewAndClick(viewStockyard);
    }
    //Step 40.1
    protected By storageareaTitle = By.xpath("//span[@class='p-menuitem-text' and text() ='Storage Areas']");
    protected void allDischargedProducts()
    {
        verifyPageHeaderTitle(storageareaTitle,"Storage Areas","Storage Areas Page:");

    }


    //*** Review OSD and Final Confirmation ***
    protected By vesselTab = By.xpath("//span[normalize-space(text())='Vessel']");
    protected By visitBtn = By.xpath("//a[normalize-space(text())='Visits']");

    //Step 41 and # Step 45
    protected void UserNavigatesToVesselVisits()
    {
        clickElement(vesselTab);
        clickElement(visitBtn);
    }
    protected void safe()
    {
        clickElement(safeZone);
    }

    protected By vesselvisitSearch = By.xpath("//div[contains(@class, 'form')]//input[@id='form-component-vst.Vessel-0']");

    protected void clickOnSearchVesselVisit() {
        clickElement(vesselvisitSearch);
    }
    protected By newarea = By.xpath("(//span[@data-column-name='stk.Name'])[2]");
    protected By filterBtn = By.xpath("//span[@class='text ng-binding' and text()=' Filter ']");
    protected By AddfilterBtn = By.xpath("//span[@class='text ng-binding' and text()='Add Filter']");

    protected By namefilter = By.xpath("//button[@class='ng-binding' and normalize-space(text())='Name']");
    protected By visitfilter = By.xpath("//button[@class='ng-binding' and normalize-space(text())='Visit ID']");

    protected By nameselect = By.id("form-component-stk.Name-0-operator");
    protected By visitselect = By.xpath("//select[@id='form-component-vst.VisitID-0-operator']");


    protected By visitinput = By.xpath("//input[@id='form-component-vst.VisitID-0']");
    protected By nameinput = By.id("form-component-stk.Name-0");
    protected By vesselinput = By.id("form-component-vst.Vessel-0");

    protected By applyfilterBtn = By.xpath("//button[@class='button-primary']");
    protected By resetBtn = By.xpath("//span[@class='text ng-binding' and normalize-space(text())='Reset']");

    protected void filterselection() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.elementToBeClickable(filterBtn)).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(AddfilterBtn)).click();
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(AddfilterBtn));
        Thread.sleep(5000);
        wait.until(ExpectedConditions.elementToBeClickable(visitfilter)).click();
        Thread.sleep(5000);
        filterJob();
        Thread.sleep(5000);

//        String jobid = (String) ScenarioContext.get("JOBID");
//        if (jobid == null) {
//            throw new IllegalStateException("JOBID is not set in ScenarioContext");
//        }
        String jobid1 = (String) ScenarioContext.get("JOBID1");
        if (jobid1 == null) {
            throw new IllegalStateException("JOBID1 is not set in ScenarioContext");
        }


        //System.out.println("The entered in 744 line is :" + jobid);
        //System.out.println("The entered in 541 line is :" + JOBID);
        System.out.println("The entered in 541DDcaps line is :" + JOBID1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(visitinput)).sendKeys(jobid1);

        // Uncomment and use this if vessel name input is required
        // wait.until(ExpectedConditions.visibilityOfElementLocated(vesselinput)).sendKeys("ARKLOW ARTIST");

        wait.until(ExpectedConditions.elementToBeClickable(applyfilterBtn)).click();
    }

    protected void filterJob() {
        selectDropdownDD(visitselect, "text", "Equal");
    }

//    protected void filterselection() throws InterruptedException {
//
//        clickElement(filterBtn);
//        Thread.sleep(3000);
//        clickElement(AddfilterBtn);
//        Thread.sleep(3000);
//        clickElement(visitfilter);
//        Thread.sleep(3000);
//
//        filterJob();
//        Thread.sleep(3000);
//        //Pass the USRN -Globally instead of JOBID
//        System.out.println("The entered in 541 line is :" + JOBID);
//        enterText(visitinput,JOBID);
//
//        Thread.sleep(3000);
//
//        //Pass the vessel Name
////        enterText(vesselinput,"ARKLOW ARTIST");
////        Thread.sleep(3000);
//
//        clickElement(applyfilterBtn);
//    }
//


//    protected void SendTextOnSearchVesselVisit() {
//        enterText(vesselvisitSearch,JOBID);
//    }
    protected By vesselindex1 = By.xpath("(//tbody[@class='ng-scope']/tr[1]/td[2]/span)[2]");
    protected By editBtn = By.xpath("//span[@class='text ng-binding' and text()='Edit']");
    protected By completedindex1 = By.xpath("(//tbody[@class='ng-scope']/tr[1]/td[2]/span[1])[1]");
    protected By completedindex2 = By.xpath("(//tbody[@class='ng-scope']/tr[1]/td[2]  /span[@class='data-type-text'])[3]");
    protected By incomingmanifestPen = By.xpath("//span[normalize-space(text())='Incoming Manifest']");

    //Step 41.1
    protected void selectsVesselAndClicksEdit() throws InterruptedException
    {
        //Applied filter to get the latest selection of vessel
        filterselection();
        //clickOnSearchVesselVisit();
        //SendTextOnSearchVesselVisit();
        Thread.sleep(5000);
        clickElement(vesselindex1);
        Thread.sleep(5000);
        clickElement(editBtn);
    }


    //Step 41.2

    protected By selectProgress = By.id("form-component-vst.ProgressTypeDescriptionID");
    protected By safeclickcompleted = By.xpath("(//span[@class='icon-info form-component-help-icon'])[23]");

    protected void changetoCompleted(String value)
    {
        selectByValue(selectProgress, value);
        JSClick(safeclickcompleted);
    }

    //Step 41.3
    protected By Arrivaltimeinput = By.xpath("//span[normalize-space(text())='Arrival Time']/following::input[@type='text'][1]");
    protected By OPstartinput = By.xpath("//span[normalize-space(text())='Operation Start']/following::input[@type='text'][1]");
    protected By OPendinput = By.xpath("//span[normalize-space(text())='Operation End']/following::input[@type='text'][1]");
    protected By Deptimeinput = By.xpath("//span[normalize-space(text())='Departure Time']/following::input[@type='text'][1]");

    protected void entertheDates() throws InterruptedException
    {
        enterDateTime(Arrivaltimeinput,"Today");
        JSClick(safeclickcompleted);
        Thread.sleep(3000);

        enterDateTime(OPstartinput,"Today");
        JSClick(safeclickcompleted);
        Thread.sleep(3000);

        enterDateTime(OPendinput,"Today");
        JSClick(safeclickcompleted);
        Thread.sleep(3000);

        enterDateTime(Deptimeinput,"Today");
        JSClick(safeclickcompleted);
        Thread.sleep(3000);

        //JSClick(saveBtnR);

        handleSaveAndOverride();

    }
    protected By overridepopup = By.xpath("//h1[@class='modal-header ng-binding ng-scope' and normalize-space(text())='Override Validations']");
    protected By override_checkbox = By.id("form-component-ConfirmOverride");
    protected By override_reason = By.id("form-component-ReasonOverride");
    protected By override_confirmBtn = By.xpath("//span[@class='text ng-binding' and normalize-space(text())='Confirm']");

    protected void overridepopuphandling()
    {
        clickElement(override_checkbox);
        enterText(override_reason,"test complete step");
        clickElement(override_confirmBtn);
    }
    public void handleSaveAndOverride() {
        try {
            // Perform clickOnSave function
            JSClick(saveBtnR);

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

    protected By returnR = By.xpath("//a[@id='bt-cancel']/span[normalize-space()='Return']");
    protected void clickReturnR()
    {
        JSClick(returnR);
        System.out.println("The Vessel Visit is updated and marked completed !!!!");
    }


    //# Step 45.3
    protected void userselectsIncomingManifest() throws InterruptedException {

        //Instead of search i have added filter based on job number
        //clickOnSearchVesselVisit();
        //SendTextOnSearchCompleted();

        //In the filder OSD number should pass globally
        filterselection();
        Thread.sleep(5000);

        clickElement(completedindex2);
        Thread.sleep(5000);

        clickElement(incomingmanifestPen);
    }
    //# Step 45.2
    protected By completedBtn = By.xpath("//span[@class='text ng-binding' and text()='Completed']");
    protected By resetRedBtn = By.xpath("//button[.//span[normalize-space(text())='Reset']]");
    protected void filterCompleted() throws InterruptedException {
        Thread.sleep(5000);
        clickElement(completedBtn);
        Thread.sleep(5000);
        System.out.println("Successfully clicked on the Filter -Completed!!");

        Thread.sleep(5000);
        System.out.println("Clicked on the Reset Filter -Before applying!!");
        JSClick(resetRedBtn);

        Thread.sleep(5000);
    }

    protected void SendTextOnSearchCompleted() {
        enterText(vesselvisitSearch,"rcb");
    }

    //# Step 45.4
    protected By OSD = By.id("VesselVisitManifest.Grid.osdReport");
    protected void clickconsignmentsOSD()
    {
        clickElement(OSD);
    }

    //# Step 45.5
    protected By ReportselectionTitle = By.xpath("//span[@class='p-menuitem-text' and text()='Report Selection']");
    protected void VerifyOSDDataReport()
    {
        switchToLastestWindow();
        waitUntilNewTabLoads();
        verifyPageHeaderTitle(ReportselectionTitle,"Report Selection","Report Selection Page:");

        switchToIFrame("iframeComponent");
        // Now interact with an element inside the iframe
        //driver.findElement(By.id("downloadButton")).click();

        // Once done, switch back to main content
        switchToDefaultContent();
        switchToParentWindow();
    }












    //Delete vessel
    protected By delete = By.xpath("//button[.//span[normalize-space(text())='Delete']]");
    protected By rightarrow = By.xpath("//button[@class='button-icon' and @data-ng-click='goToNextPage()']//span[contains(@class, 'icon-greater-than')]");

    protected void clickonVessel_Visit()
    {
        setZoomLevel(50);
        clickElement(vesselTab);
        clickElement(visitBtn);
    }
//    public void clickOnID(String match) {
//        try {
//            // Find all rows with the specified xpath
//            List<WebElement> rows = driver.findElements(
//                    By.xpath("//span[@data-column-name='vst.Vessel']"));
//
//            System.out.println("Total rows found: " + rows.size());
//
//            // Iterate through each row
//            for (WebElement row : rows) {
//                String rowText = row.getText();
//                System.out.println("Row text: " + rowText);
//
//                // Check if the row text contains the match string
//                if (rowText.contains(match)) {
//                    System.out.println("Clicking on row with match: " + match);
//                    System.out.println("Row matched: " + rowText);
//                    row.click();
//                    return; // Exit after clicking the matching row
//                }
//            }
//
//            // Log if no match is found
//            System.out.println("Value not found: " + match);
//
//        } catch (Exception e) {
//            System.out.println("Exception while clicking on value: " + match + ". Error: " + e.getMessage());
//        }
//    }

    public boolean clickAndDeleteMatch(String... keywords) {
        final String SAFE_CLICK_XPATH = "//a[@class='cursor-pointer']//img[@alt='CommTrac brand logo']";

        try {
            final int MAX_PAGES_TO_CHECK = 5;
            boolean overallDeletionOccurred = false; // Flag to indicate if ANY deletion happened across all pages

            for (int pageAttempt = 1; pageAttempt <= MAX_PAGES_TO_CHECK; pageAttempt++) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

                System.out.println("--- Checking Page " + pageAttempt + " for matches ---");

                // Flag to see if any element was found matching the criteria on the current page
                // This determines if we should try to navigate to the next page
                boolean matchFoundOnCurrentPage = false;
                boolean actionPerformedOnCurrentPage = false; // Tracks if a click (vessel, delete, safe) happened

                // Use a do-while loop or re-fetch list strategy to handle dynamic content
                // We will loop until no more matching elements are found on the *current* page,
                // or until we explicitly break.
                while (true) { // Loop to re-check the current page after an action
                    List<WebElement> vesselElements = null;
                    try {
                        vesselElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                                By.xpath("//span[@data-column-name='vst.Vessel']")));
                    } catch (TimeoutException e) {
                        System.out.println("No vessel elements found on Page " + pageAttempt + ". Moving to next.");
                        break; // No vessels, so move to next page attempt
                    }


                    System.out.println("Page " + pageAttempt + " - Current count of vessel elements: " + vesselElements.size());

                    WebElement matchingVessel = null; // To hold the specific matching element
                    String matchingVesselText = null;

                    // Find the first matching element that has not been processed yet (conceptually)
                    // The best way to do this is to iterate and immediately break if found, then re-fetch.
                    for (WebElement currentElement : vesselElements) {
                        String currentVesselName = currentElement.getText();
                        boolean allKeywordsPresent = true;
                        if (currentVesselName != null) {
                            for (String keyword : keywords) {
                                if (!currentVesselName.toUpperCase().contains(keyword.toUpperCase())) {
                                    allKeywordsPresent = false;
                                    break;
                                }
                            }
                        } else {
                            allKeywordsPresent = false;
                        }

                        if (allKeywordsPresent) {
                            matchingVessel = currentElement;
                            matchingVesselText = currentVesselName;
                            matchFoundOnCurrentPage = true; // A match was found
                            break; // Found one, exit this inner loop to process it
                        }
                    }

                    if (matchingVessel != null) {
                        System.out.println("Processing match: '" + matchingVesselText + "' on page " + pageAttempt);
                        actionPerformedOnCurrentPage = true; // An action will be attempted

                        try {
                            // Click on the matching vessel name
                            wait.until(ExpectedConditions.elementToBeClickable(matchingVessel));
                            matchingVessel.click();
                            System.out.println("Clicked on: " + matchingVesselText);
                            Thread.sleep(2000); // Wait for 2 seconds

                            // --- Attempt Delete Flow ---
                            WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                                    By.xpath("//button[.//span[normalize-space(text())='Delete']]")));
                            deleteButton.click();
                            System.out.println("Clicked 'Delete' button.");
                            Thread.sleep(2000); // Wait for popup/dialog to appear

                            WebElement reasonTextArea = wait.until(ExpectedConditions.presenceOfElementLocated(
                                    By.xpath("//textarea[@id='form-component-Reason']")));
                            reasonTextArea.sendKeys("test");
                            System.out.println("Entered 'test' into the Reason textarea.");
                            Thread.sleep(2000); // Wait for 2 seconds

                            WebElement confirmButton = wait.until(ExpectedConditions.elementToBeClickable(
                                    By.xpath("//button[@id='inline-bt-Delete' and .//span[normalize-space(text())='Confirm']]")));
                            confirmButton.click();
                            System.out.println("Clicked 'Confirm' button on the delete popup.");
                            Thread.sleep(3000); // Increased wait for deletion to process and DOM to settle

                            System.out.println("Successfully deleted: " + matchingVesselText);
                            overallDeletionOccurred = true;

                            // After successful delete, the page state has definitely changed.
                            // The 'while(true)' loop will cause a re-fetch of 'vesselElements' at its next iteration.
                            // So, we effectively "restart" the search on the current page.

                        } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException e) {
                            System.out.println("Delete button or associated elements not found/stale for vessel: " + matchingVesselText + ". Performing safe click.");
                            try {
                                // Perform the "safe click" action
                                WebElement safeClickElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SAFE_CLICK_XPATH)));
                                safeClickElement.click();
                                System.out.println("Performed safe click on: " + SAFE_CLICK_XPATH);
                                Thread.sleep(2000); // Wait for popup to dismiss

                                // After safe click, the page state might have reverted.
                                // The 'while(true)' loop will cause a re-fetch of 'vesselElements' at its next iteration.
                                // We continue the while loop to re-scan for potential other matches on the same page.
                            } catch (Exception safeClickEx) {
                                System.err.println("Error during safe click, potentially leaving popup open or page in bad state: " + safeClickEx.getMessage());
                                // If safe click also fails, it's a critical error.
                                // Consider breaking out of loops or throwing a more specific exception.
                                // For now, we'll continue the while loop, hoping it recovers.
                            }
                        }
                    } else {
                        // No matching vessel found on the current page in this iteration
                        System.out.println("No more matching vessels found on Page " + pageAttempt + " in this scan. Breaking inner loop.");
                        break; // Exit the while(true) loop for this page, move to next page logic
                    }
                } // End of while(true) loop for current page


                // After exhausting all attempts to find and process matches on the current page:
                // Only click the right arrow if no match was found on this page *at all*
                // OR if matches were found but no further matches are left on this page, and it's not the last page.
                if (!matchFoundOnCurrentPage || (matchFoundOnCurrentPage && !actionPerformedOnCurrentPage)) {
                    // This means either no matching elements were found OR an action was found but couldn't be performed (e.g., button not clickable)
                    // and we are ready to move to the next page.
                    if (pageAttempt < MAX_PAGES_TO_CHECK) {
                        try {
                            WebElement rightArrow = wait.until(ExpectedConditions.elementToBeClickable(
                                    By.xpath("//button[@class='button-icon' and @data-ng-click='goToNextPage()']//span[contains(@class, 'icon-greater-than')]")));
                            rightArrow.click();
                            System.out.println("Clicked right arrow to go to Page " + (pageAttempt + 1));
                            Thread.sleep(3000); // Wait for the new page to load
                        } catch (Exception e) {
                            System.out.println("Could not click right arrow or no more pages. Error: " + e.getMessage());
                            break; // Exit outer loop if right arrow is not found or an error occurs
                        }
                    }
                } else if (actionPerformedOnCurrentPage && pageAttempt == MAX_PAGES_TO_CHECK) {
                    // If actions were performed on the last page, we have finished checking all pages.
                    System.out.println("Actions performed on last page (" + pageAttempt + "). Finishing search.");
                    break; // Exit the loop
                }
                // If action was performed on current page and it's not the last page, the loop will naturally continue
                // to the next pageAttempt, and the 'while(true)' will re-scan the *new* page's elements.

            } // End of outer for loop (pageAttempt)

            System.out.println("Finished checking all " + MAX_PAGES_TO_CHECK + " pages. Overall deletion occurred: " + overallDeletionOccurred);
            return overallDeletionOccurred; // Return true if ANY deletion happened
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during clickAndDeleteMatch: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void navigateRightNTimes(int n) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            for (int i = 0; i < n; i++) {
                try {
                    WebElement rightArrow = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//button[@class='button-icon' and @data-ng-click='goToNextPage()']//span[contains(@class, 'icon-greater-than')]")));
                    rightArrow.click();
                    System.out.println("Navigated right " + (i + 1) + " time(s).");
                    Thread.sleep(2000); // Wait for the page to load
                } catch (Exception e) {
                    System.out.println("Could not click right arrow on navigation attempt " + (i + 1) + ". Perhaps no more pages. Error: " + e.getMessage());
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error during navigation: " + e.getMessage());
            e.printStackTrace();
        }
    }





















































































}



