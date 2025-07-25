package com.pageobjects.abp;

import com.framework.reusable.WebReusableComponents;
import org.openqa.selenium.By;
import com.framework.context.ScenarioContext;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.framework.components.ApplitoolsOperations;
import com.framework.reusable.WebReusableComponents;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

import static com.framework.selenium.SeleniumTestParameters.getAppUrl;

public class Truck_Intake_Page extends WebReusableComponents {

    //Launch Steps
    protected ApplitoolsOperations appliTools = new ApplitoolsOperations();

    protected void launchApp()
    {
        launchUrl(getAppUrl());
        maximizeWindow();
        appliTools.captureContent("");
    }

    protected void verifyTitle() {
        getPageTitle();
        String expectedTitle = "Sign in | TMS"; // Replace with the expected title
        String actualTitle = getPageTitle();
        System.out.println("Page Title: " + actualTitle);

        Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match!");
        //Assert.assertTrue(elementVisible(loginHeader, 10));
    }

    protected By UserName = By.xpath("(//input[contains(@class, 'commtrac-logininput')])[1]");
    protected By Password = By.xpath("//input[@type='password']");
    protected By signinButton = By.xpath("(//button[contains(., 'Sign in')])[1]");

    protected void enterUserName(String Username1) { enterText(UserName, Username1);}
    protected void enterPassword(String Password1) { enterText(Password, Password1);}
    protected void clickonSigninButton() { clickElement(signinButton); }

    protected void verifyHomepage() {
        getPageTitle();
        String expectedTitle = "TMS"; // Replace with the expected title
        String actualTitle = getPageTitle();
        System.out.println("The Home Page Title is : " + actualTitle);

        Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match!");
        //Assert.assertTrue(elementVisible(loginHeader, 10));
    }

    //# Step 2.1
    protected By Cargo = By.xpath("//span[normalize-space(text())='Cargo']");
    protected By Booking = By.xpath("//span[normalize-space(text())='Booking']");
    protected By downArrow = By.xpath("//i[@class='pi pi-fw pi-chevron-down ml-auto pr-2']");
    protected By Intakes = By.xpath("//li[@label='Intakes']/a[text()='Intakes']");

    protected void CargoTruckIntakes() throws InterruptedException {
        setZoomLevel(50);
        clickElement(Cargo);
        clickElement(downArrow);
        Thread.sleep(2000);
        scrollIntoViewAndClick(Intakes);
    }

    //# Step 2.2
    protected By addunderTruckpage = By.id("VisitManagement.VisitFeatureSet.Truck_Manifest.Edit.add");

    protected void clickonaddONTruckpage() {
        clickElement(addunderTruckpage);
    }
    //# Step 2.3
    protected By IntakeHeader = By.xpath("//span[contains(text(), 'Intakes')]");

    protected void TruckIntakescreendisplayed()
    {
        verifyPageHeaderTitle(IntakeHeader, "Intakes", "Intakes-Page:");
    }

    //# Step 3
    protected By IntakeInput = By.xpath("//input[@id='form-component-vst.Name']");
    protected By selectProgress = By.id("form-component-vst.ProgressTypeDescriptionID");

    protected void enterdetailsofIntakes(String Intakedetails)
    {
        setZoomLevel(50);
        clickElement(IntakeInput);
        //*** Value passed from the Excel sheet
        enterText(IntakeInput, Intakedetails);
        System.out.println("The Entered Intake Name is:" +Intakedetails);

        //** Taking Intake details Globally --IBR Number ***////
        // From the Extracted text -Storing Globally.
        ScenarioContext.set("IBR", Intakedetails);
        System.out.println("Stored Intakedetails IBR: " + Intakedetails);
        try {
            Files.write(Paths.get("Intakedetails.txt"), Intakedetails.getBytes());
        } catch (IOException e) {
            System.out.println("Failed to write IBR to file: " + e.getMessage());
        }
    }

    //# Step 3.1
    protected void changetoWorking(String value)
    {
        selectByValue(selectProgress, value);
    }
    //# Step 3.2 (Common Buttons)
    protected By nextBtn = By.xpath("//button[normalize-space(.)='Next']");
    protected By returnBtn = By.xpath("//a[@id='bt-cancel']/span[normalize-space()='Return']");
    //protected By saveBtnR = By.id("bt-save");
    protected By saveBtnRight = By.xpath("//button[@id='bt-save']");
    protected By saveBtn = By.xpath("//button[@data-ng-if=\"action.action == 'save'\" and .//span[text()='Save']]");
    protected By safeZone = By.xpath("//*[@id=\"CommtracVue\"]/div[1]/header/div/div[1]/nav/ol/li[1]/a/span/img");

    protected void clickOnSaveR()
    {
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Adjust timeout as needed
        //wait.until(ExpectedConditions.elementToBeClickable(saveBtnRight));
        clickOnElementaction(saveBtnRight);
    }

    protected void clickOnSaveBtm() {
        clickElement(saveBtn);
    }

    protected void clickOnNextBtm()
    {
        clickElement(nextBtn);
    }
    protected void clickOnReturnBtn()
    {
        clickElement(returnBtn);
    }

    //# Step 3.3

    //# Step 4.1
    protected By addunderConsignement= By.xpath("//*[@id='VesselVisitManifest.Grid.add']");
    protected void clickonaddONconsignment()
    {
        setZoomLevel(50);
        clickElement(addunderConsignement);
    }
    //# Step 4.2
    protected By consignmentTitle = By.xpath("//span[normalize-space(text())='Consignment']");
    protected void verifyConsignmentpage()
    {
        verifyPageHeaderTitle(consignmentTitle,"Consignment","The Consignment Page Title is:");
    }

    //# Step 5.1
    //Enter Details of consignment
    protected By consignerInput = By.id("form-component-consignor.OriginalOrg_OrgTypeGuid");
    protected By consignerIndex1 = By.xpath("//li[@data-ng-repeat='option in options'][1]/button");

    protected void clickonConsingerInput()
    {
        clickElement(consignerInput);
    }

    protected void entercongDetails(String consignerdetails)
    {
        enterText(consignerInput,consignerdetails);
        clickElement(consignerIndex1);
        System.out.println("The Entered Intake Name is:" +consignerdetails);
    }
    //# Step 5.2 --the user clicks on SaveR

    //# Step 5.3
    protected By savedMsg = By.xpath("//div[@class='p-message-text' and @data-pc-section='text']");
    protected void verifyConsignmnetMessage() throws IOException
    {
        //verifyAndLogConfirmationMessage(savedMsg);
        String s1 = getTextFromElement(savedMsg);
        System.out.println("Text From:"+s1);
    }

    //saved for Future--7.4 stepno. added
    protected By usrntextIntake = By.xpath("//tr[@data-index='0']/td/span[@data-column-name='manifest.Consignment']");
    protected void getIntakeUSRN()
    {
        String intakeusrn = getTextFromElement(usrntextIntake);
        System.out.println("The Global Intake Truck USRN is:" +intakeusrn);

        // From the Extracted text -Storing Globally.
        ScenarioContext.set("IUSRN", intakeusrn);
        System.out.println("Stored Intake IUSRN: " + intakeusrn);
        try {
            Files.write(Paths.get("intakeusrn.txt"), intakeusrn.getBytes());
        } catch (IOException e) {
            System.out.println("Failed to write IUSRN to file: " + e.getMessage());
        }
    }

    //# Step 6.1
    protected By addunderconsignementStock = By.xpath("//*[@id='StockConsolidation.Grid.add']");
    protected void clickonaddconsignment() throws InterruptedException {
        setZoomLevel(60);
        clickElement(addunderconsignementStock);
        Thread.sleep(2000);
    }

    //# Step 6.2
    protected By productselectionTitle = By.xpath("//h1[@class='modal-header ng-binding ng-scope' and contains(text(), 'Product Selection')]");

    protected void ProductSelectionPage() throws InterruptedException {
        Thread.sleep(2000);
        verifyPageHeaderTitle(productselectionTitle, "Product Selection","Product Selection Page:");
    }


    //# Step 7.1
    //Enter Details of consignment
    protected By consignerInputBox = By.xpath("//*[@id='form-component-OriginalProductGuid']");
    protected By productselectionIndex1 = By.xpath("//ul[@class='filter-select-options']/li[1]");

    protected void enterProdDetails(String product)
    {
        setZoomLevel(50);
        clickElement(consignerInputBox);
        enterText(consignerInputBox,product);
        clickElement(productselectionIndex1);
        System.out.println("The Entered Product Name in Cargo>Product selection Page is:" +product);


        // From the Extracted text -Storing Globally.
        ScenarioContext.set("PRODUCT", product);
        System.out.println("Stored name of the PRODUCT: " + product);
        try {
            Files.write(Paths.get("product.txt"), product.getBytes());
        } catch (IOException e) {
            System.out.println("Failed to write PRODUCT name to file: " + e.getMessage());
        }

        //Confirming that Weight dropdown selected automatically
        System.out.println("****The Storage Type 'Weight' is selected****");
    }
    //# Step 7.1 --For 7D step
    protected By SelectstorageType = By.id("form-component-originalStorageCategoryGuid");
    protected void selectStoargetype()
    {
        selectDropdownDD(SelectstorageType,"text","1000kg - Bag (1000kg - Bag)");
        //Confirming that dropdown selected
        System.out.println("****The Storage Type '1000kg - Bag (1000kg - Bag)' is selected****");
    }

    //# Step 7.2
    //The Common Next button is been used --from step 3.1
    
    //# Step 7.3 ----Entering weight value Globally
    protected By inputweight = By.xpath("//input[starts-with(@id, 'form-component-') and contains(@id, '.DisplayOpeningQuantity')]");
    protected By safeZoneInfoBtn = By.xpath("//span[@data-tooltip='Opening Quantity of Weight.']");
    protected By safeZoneInfo7D = By.xpath("//span[@data-tooltip='Opening Quantity of 1000kg - Bag.']");
    protected By wieghtinputdiv = By.xpath("(//div[@class='numeric-input-readable input-text ng-binding' and normalize-space(text())='0.000'])[2]");
    protected By baginput = By.xpath("(//input[starts-with(@id, 'form-component-') and contains(@id, '.DisplayOpeningQuantity')])[1]");
    protected By baginputdiv = By.xpath("(//div[@class='numeric-input-readable input-text ng-binding'])[9]");

    protected void productDetailWeight(String productweight) throws InterruptedException {
        //ProdWeightval ="75";
        Thread.sleep(5000);
        enterDynamicNumericInput(wieghtinputdiv,inputweight, productweight);
        JSClick(safeZoneInfoBtn);
        //Clicked on Bottom save
        clickOnSaveBtm();

        //** Storing productweight details Globally --weight Number ***////
        // From the Extracted text -Storing Globally.
        ScenarioContext.set("ProductWeight", productweight);
        System.out.println("Stored productweight is: " + productweight);
        try {
            Files.write(Paths.get("productweight.txt"), productweight.getBytes());
        } catch (IOException e) {
            System.out.println("Failed to write productweight to file: " + e.getMessage());
        }

    }

    //# Step 7.3 for 7D TC
    protected By stockindifyInput = By.id("form-component-c_s.StockIdentifier");

    protected void StockEntry(String stockidentifier,String productweight) throws InterruptedException {
        Thread.sleep(5000);
        enterText(stockindifyInput,stockidentifier);

        Thread.sleep(5000);
        scrollIntoView(safeZoneInfo7D);
        enterDynamicNumericInput(baginputdiv,baginput,productweight);
        JSClick(safeZoneInfo7D);
        //Clicked on Bottom save
        clickOnSaveBtm();

        //** Storing productweight details Globally --weight Number ***////
        // From the Extracted text -Storing Globally.
        ScenarioContext.set("ProductWeight", productweight);
        System.out.println("Stored productweight is: " + productweight);
        try {
            Files.write(Paths.get("productweight.txt"), productweight.getBytes());
        } catch (IOException e) {
            System.out.println("Failed to write productweight to file: " + e.getMessage());
        }


    }



    //# Step 7.4 ----Clicks on Save Bottom
    protected void savedMsg_ConsignmentStockCreated()
    {
        String savedMsg_ConsignmentStock = getTextFromElement(savedMsg);
        System.out.println("Click on Bottom save: The Captured saved message after Adding Consignment Stock i.e, Weight" +savedMsg_ConsignmentStock);
    }

    //# Step 7.5 ----Clicks on Save Right
    //will Capture again the saved message after Adding Consignment Stock i.e, Weight
    protected void savedMsgText()
    {
        String savedMsg_ConsignmentStock = getTextFromElement(savedMsg);
        System.out.println("Click on Right save: The Captured saved message after Adding Consignment Stock i.e, Weight" +savedMsg_ConsignmentStock);
    }

    //8.1 releated
    protected void clickOnProduct(String match) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Increased wait time

            // Robust XPath: Target the <span> element that has data-column-name='stocks.Product'
            // and contains the 'match' text (case-insensitive).
            // This ensures it works if 'SALT' changes to 'Sugar', etc., as long as the column name attribute is stable.
            String productSpanXPath = "//span[@data-column-name='stocks.Product' and contains(translate(text(), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), '" + match.toUpperCase() + "')]";

            System.out.println("Attempting to find and click on: " + productSpanXPath);

            // Wait for the specific span element to be present and clickable
            WebElement productSpan = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(productSpanXPath)));

            System.out.println("Found product span. Text: " + productSpan.getText());

            // 1. Scroll the element into view (still good practice)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", productSpan);
            System.out.println("Scrolled product span into view.");

            // Add a small sleep after scrolling, though elementToBeClickable should handle it
            Thread.sleep(200);

            // 2. Click the specific span element
            productSpan.click();
            System.out.println("Successfully clicked on product span with text: " + match);

            // IMPORTANT: Address the "Warning: Preferred location link did not become clickable"
            // This is still a critical failure point in your test flow.
            // You MUST ensure that the next element you expect to interact with becomes available.
            // If clicking 'SALT' is supposed to open a new modal, navigate to a new page,
            // or make new elements appear, you need to wait for those.
            try {
                // Assuming this link appears *after* clicking on SALT/Sugar.
                // If it's on a new page or within a modal, ensure the context is correct.
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[span[@class='text ng-binding' and text()='Preferred Location']]")));
                System.out.println("Preferred location link is now clickable, indicating successful transition.");
            } catch (org.openqa.selenium.TimeoutException innerEx) {
                System.err.println("CRITICAL WARNING: Preferred location link did not become clickable after clicking '" + match + "'. This indicates the click on '" + match + "' did NOT lead to the expected next UI state. Error: " + innerEx.getMessage());
                // Consider failing the test here or taking a screenshot to debug the UI state.
                throw new AssertionError("Post-click validation failed: Preferred location link not clickable.", innerEx);
            }

        } catch (org.openqa.selenium.TimeoutException e) {
            System.err.println("Timeout: Product span with text '" + match + "' not found or not clickable within the specified time. Error: " + e.getMessage());
            throw new AssertionError("Failed to find or click product span: " + match, e);
        } catch (Exception e) {
            System.err.println("Exception while attempting to click on product span with text '" + match + "'. Error: " + e.getMessage());
            throw new AssertionError("Unexpected error during product click: " + match, e);
        }
    }

    //# Step 8.1
    protected By scrolltillAdd = By.xpath("//button[@id='StockConsolidation.Grid.add']");

    protected void clickOnProductName() throws InterruptedException {
        setZoomLevel(50);
        Thread.sleep(3000);
        scrollIntoView(scrolltillAdd);

        String product = (String) ScenarioContext.get("PRODUCT");  // Retrieve global value
        //*** Clicks on Product Name --From Globally saved Product Name
        clickOnProduct(product);
        Thread.sleep(2000);
    }
    //# Step 8.2
    protected void MenuDisplayed() throws InterruptedException {
        System.out.println("The product menu is been displayed: User is clicking on Preffered location");
        Thread.sleep(2000);
    }

    //# Step 9.1
    protected By prefferedLoc = By.xpath("//a[span[@class='text ng-binding' and text()='Preferred Location']]");
    protected void ClicksonprefferedLoc() throws InterruptedException {
        System.out.println("The product menu is been displayed: User is clicking on Preffered location");
        clickElement(prefferedLoc);
        Thread.sleep(2000);
    }
    //# Step 9.2
    protected By preferredLocationTitle = By.xpath("//span[@class='p-menuitem-text' and text()='Preferred Location']");
    protected void verifypreferredLocationTitlePage()
    {
        verifyPageHeaderTitle(preferredLocationTitle, "Preferred Location","Preferred Location Page:");
    }

    //# Step 10.1
    protected By select = By.xpath("//span[@aria-label='Select Storage Area' and @role='combobox']");
    protected By selectStoargearea = By.xpath("(//div[@class='p-select-dropdown' and @data-pc-section='dropdown'])[1]");
    protected By prefferedSaveBtn = By.xpath("//span[@data-pc-section='label' and text()='Save']/ancestor::button");
    protected By allvalueslocation = By.xpath("//li[@role='option' and @data-pc-section='option']");

    protected By scrolltodrp = By.xpath("//li[@aria-label='AUTOMATION AREA - SALT-COMMON STOWAGE']");
    protected By SALTCOMMONSTOWAGE = By.xpath("//li[contains(@class, 'p-select-option') and .//span[text()='AUTOMATION AREA - SALT-COMMON STOWAGE']]");

    protected By scrolldrp1 = By.xpath("//li[@aria-label='AUTOMATION AREA - CGroup MultiStock']");
    protected By CGroup = By.xpath("//li[contains(@class, 'p-select-option') and .//span[text()='AUTOMATION AREA - CGroup MultiStock']]");

    protected By AMMONIUMSULPHATE = By.xpath("//li[contains(@class, 'p-select-option') and .//span[text()='AUTOMATION AREA - Ammonium Sulphate']]");
    protected By scrolldrp2 = By.xpath("//li[@aria-label='AUTOMATION AREA - Ammonium Sulphate']");

    protected void selectingLocation_COMMONSTOWAGE() throws InterruptedException {
        clickElement(selectStoargearea);
        Thread.sleep(5000);

        //***Scrolled the Storage Area
        scrollIntoView(scrolltodrp);
        Thread.sleep(3000);
        JSClick(SALTCOMMONSTOWAGE);
        Thread.sleep(3000);
        clickElement(prefferedSaveBtn);
    }
    protected void selectingLocation_ConsignmentGroup() throws InterruptedException {
        clickElement(selectStoargearea);
        Thread.sleep(5000);

        //***Scrolled the Storage Area
        scrollIntoView(scrolldrp1);
        Thread.sleep(3000);
        JSClick(CGroup);
        Thread.sleep(3000);
        clickElement(prefferedSaveBtn);
    }
    //** For 7D Tc **///
    protected void selectingLocation() throws InterruptedException {
        clickElement(selectStoargearea);
        Thread.sleep(5000);

        //***Scrolled the Storage Area
        scrollIntoView(scrolldrp2);
        Thread.sleep(3000);
        JSClick(AMMONIUMSULPHATE);
        Thread.sleep(3000);
        clickElement(prefferedSaveBtn);
    }

//    protected void selectingLocation() throws InterruptedException {
//
//
//
//
//
//        //selectCustomDropdown(selectStoargearea,allvalueslocation,"AUTOMATION AREA - SALT-COMMON STOWAGE");
//
//        //AUTOMATION AREA - Salt - Consignment Group
////        selectCustomDropdown(selectStoargearea,allvalueslocation,"AUTOMATION AREA - Salt - Consignment Group");
//        //clickElement(prefferedSaveBtn);
//        //selectCustomDropdown(selectzone,allvalueslocation,"dd");
//
//
//        //savedMsg_ConsignmentStockCreated();
//    }

    protected By selectzone = By.xpath("(//div[@class='p-select-dropdown' and @data-pc-section='dropdown'])[2]");

    //protected By allvalueslocation = By.xpath("//ul[@role='listbox' and @id='pv_id_3_list']/li[@role='option']");
    protected By allvalueslocation2 = By.xpath("//span[@class='p-select-option-label']");

    protected By valueSaltCommonstowage = By.xpath("//li[@role='option']/span[text()='AUTOMATION AREA - SALT-COMMON STOWAGE']");


    //# Step 10.2


















}
























































