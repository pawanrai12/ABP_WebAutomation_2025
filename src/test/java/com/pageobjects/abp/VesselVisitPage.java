package com.pageobjects.abp;

import com.framework.context.ScenarioContext;
import org.openqa.selenium.*;
import org.testng.Assert;

import com.framework.components.ApplitoolsOperations;
import com.framework.reusable.WebReusableComponents;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.framework.selenium.SeleniumTestParameters.getAppUrl;

public class VesselVisitPage extends WebReusableComponents
{
    public static String Weightval;
    String stockid;
    public static String USRN;
    public static String JOBID;
    public static String JOBID1;
    protected By loginHeader = By.name("user-name");
    //url --https://cmt-bulk-cdf-ppd.abph.int/app/v/signin

    protected By UserName = By.xpath("(//input[contains(@class, 'commtrac-logininput')])[1]");
    protected By UserNameInn = By.xpath("(//input[@class='p-inputtext p-component p-filled w-full commtrac-logininput'])[1]");
    protected By Password = By.xpath("//input[@type='password']");
    protected By signinButton = By.xpath("(//button[contains(., 'Sign in')])[1]");

    protected By vesselTab = By.xpath("//span[normalize-space(text())='Vessel']");
    protected By visitBtn = By.xpath("//a[normalize-space(text())='Visits']");
    protected By addBtn = By.xpath("//span[normalize-space(text())='Add']");

    protected By vesselNameinput = By.id("form-component-vst.OriginalVehicleGuid");
    // This also selects ETA by indexing (//input[@class='form-control'])[1]
    protected By dateInput = By.xpath("(//input[@class='form-control'])[3]");
    protected By ETAinput = By.xpath("//div[@id='form-component-vst.PlannedArrivalTime']//input[@class='form-control']");
    protected By ETDinput = By.xpath("//div[@id='form-component-vst.PlannedDepartureTime']//input[@class='form-control']");

    protected By saveBtnR = By.id("bt-save");
    protected By saveBtn = By.xpath("//button[@data-ng-if=\"action.action == 'save'\" and .//span[text()='Save']]");
    protected By safeZone = By.xpath("//*[@id=\"CommtracVue\"]/div[1]/header/div/div[1]/nav/ol/li[1]/a/span/img");

    //Top  Pages
    protected By visitGridTitle = By.xpath("//span[normalize-space(text())='Visit Grid']");
    protected By editVisitTitle = By.xpath("//span[normalize-space(text())='Edit Visit']");
    protected By incommingManifestTitle = By.xpath("//span[normalize-space(text())='Vessel Incoming Manifest']");
    protected By consignmentTitle = By.xpath("//span[normalize-space(text())='Consignment']");

    protected By vesselvisitSearch = By.id("form-component-vst.Vessel-0");
    protected By Index2 = By.xpath("//tbody/tr/td[span[@data-column-name='vst.VoyageNumber']]");
    protected By Index = By.xpath("(//tbody[@class='ng-scope']/tr[1]/td[2]/span)[2]");
    protected By Index1 = By.xpath("//*[@id=\"commtrac-angular\"]/div[1]/div/div/div/div[4]/div[1]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td[2]");
//    protected By Index1 = By.xpath("//*[@id=\"commtrac-angular\"]/div[1]/div/div/div/div[4]/div[1]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[1]/td[4]/span");
//    protected By Index1 = By.xpath("//*[@id=\"commtrac-angular\"]/div[1]/div/div/div/div[4]/div[1]/div[1]/table/tbody/tr[2]/td[2]/table/tbody/tr[1]");

    protected By incomingmanifestPen = By.xpath("//a[contains(@class, 'grid-item-action') and .//span[normalize-space(text())='Incoming Manifest']]");

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

    protected void verifyLogin() { Assert.assertTrue(elementVisible(loginHeader, 10));}

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

    protected void clickonVessel_Visit()
    {
        setZoomLevel(50);
        clickElement(vesselTab);
        clickElement(visitBtn);
    }

    protected void safe()
    {
        clickElement(safeZone);
    }

    //#Step 3
    protected void add_Visit() {

        clickElement(addPlusBtn);
    }
//
//    protected void add_Visit() throws InterruptedException {
//        //clickElement(addBtn);
////        JavascriptExecutor js = (JavascriptExecutor) driver;
////        js.executeScript("document.elementFromPoint(508, 39).click();");
//        Thread.sleep(5000);
//        WebElement body = driver.findElement(By.tagName("body"));
//        body.click();
//        Thread.sleep(5000);
//        clickElement(addPlusBtn);
//        Thread.sleep(5000);
//    }




    protected void verifyVisitpage() {
        getPageTitle();
        String expectedTitle = "Visit Grid | TMS"; // Replace with the expected title
        String actualTitle = getPageTitle();
        System.out.println("The Visit Page Title is : " + actualTitle);

        Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match!");
        //Assert.assertTrue(elementVisible(loginHeader, 10));
    }

    protected void verifyVisitGridpage()
    {
        verifyPageHeaderTitle(visitGridTitle,"Visit Grid","Vessel>Visit-Page:");
    }
    //#Step 3.1

    protected void verifyeditVisitpage()
    {
        verifyPageHeaderTitle(editVisitTitle,"Edit Visit","Edit visit Page:");
    }

    protected By jobidInput = By.xpath("//input[@id='form-component-vst.FriendlyVisitRef']");
    protected void getID() {

        System.out.println(">>> getID() called");
        String jobid = getTextFromElement(jobidInput);
        System.out.println(jobid);

        // Set both the static variable and scenario context
        JOBID = jobid;
        ScenarioContext.set("JOBID", jobid);
        System.out.println("Stored JOBID: " + jobid);
        try {
            Files.write(Paths.get("jobid.txt"), jobid.getBytes());
        } catch (IOException e) {
            System.out.println("Failed to write JOBID to file: " + e.getMessage());
        }

    }

    //Change here for Excel --Vessel Name and add xpath for each selected items
    protected By  vessel_ARKLOWARTIST = By.xpath("//button[@class='filter-select-option ng-binding' and normalize-space(text())='ARKLOW ARTIST (9851983)']");
    protected void enterdetailsofVessel(String vesselName)
    {
        enterText(vesselNameinput, vesselName);
        clickElement(vessel_ARKLOWARTIST);
    }

    protected By  selectProgress= By.id("form-component-vst.ProgressTypeDescriptionID");
    protected By  selectBerth= By.id("form-component-vst.OriginalOperationalLocationGUID");
    protected By  addPlusBtn= By.id("vst.Grid.add");


    //Step 4
    protected void enterDates()
    {
        enterDateTime(ETAinput,"Today");
        enterDateTime(ETDinput,"Tomorrow");
    }

    protected void changetoWorking(String value)
    {
        selectByValue(selectProgress, value);
    }

    protected void berthselection(String value)
    {
        selectByValue(selectBerth, value);
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
            clickOnSave();

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

    //Step 4.3

    protected By saveMsg = By.xpath("//div[@class='p-message-text' and @data-pc-section='text']");
    protected void dd() {
        String sai1 = getTextFromElement(saveMsg);
        String sai = getTextofElement(saveMsg);
        System.out.println(">>> DD1getID() called " + sai1);
        System.out.println(">>> DDgetID() called " + sai);
    }

    protected By jobInput= By.xpath("//input[@id='form-component-vst.FriendlyVisitRef']");
    public void OnEditVisitScreen()
    {
        JOBID1 = getTextFromElement(jobInput);
        System.out.println(JOBID1);

        String jobid1 = getTextFromElement(jobInput); // Replace with actual logic
        ScenarioContext.set("JOBID1", jobid1);
        System.out.println("Stored JOBID1: " + jobid1);

        try {
            Files.write(Paths.get("jobid1.txt"), jobid1.getBytes());
        } catch (IOException e) {
            System.out.println("Failed to write JOBID1 to file: " + e.getMessage());
        }
    }

    protected void clickOnSave()
    {
        clickOnElementaction(saveBtnR);
    }

    protected void clickOnCSSave() {
        clickElement(saveBtn);
    }
    protected void clickOnSearchVesselVisit() {
        clickElement(vesselvisitSearch);
    }
    protected void SendTextOnSearchVesselVisit() {
        enterText(vesselvisitSearch,"pavan");
    }
    //button[@class='button-icon'][2]
    protected By Pclick1 = By.xpath("/button[@class='button-icon'][2]");
    protected void pageclick() {
        clickElement(Pclick1);
        waitUntilElementClickable(Pclick1,4);
    }

    protected void clickonIncommingmanifest()
    {
        clickElement(incomingmanifestPen);
    }

    //Step 11
    protected void verifyIncommingmanifestpage()
    {
        verifyPageHeaderTitle(incommingManifestTitle,"Vessel Incoming Manifest","Incoming Manifest Page:");
    }

    protected By addunderConsignement= By.xpath("//*[@id=\"VesselVisitManifest.Grid.add\"]");
    protected void clickonaddONconsignment()
    {
        clickElement(addunderConsignement);
    }

    //9.3
    protected void verifyConsignmentpage()
    {
        verifyPageHeaderTitle(consignmentTitle,"Consignment","The Consignment Page Title is");
    }

    //Enter Details of consignment
    protected By consignerInput = By.id("form-component-consignor.OriginalOrg_OrgTypeGuid");
    protected By cafetra = By.xpath("//button[@class='filter-select-option ng-binding' and text()=' CEFETRA  (CEF) - Customer ']");

    //Methods
    protected void clickonConsingerInput()
    {
        clickElement(consignerInput);
    }

    protected void entercongDetails()
    {
        setZoomLevel(50);
        enterText(consignerInput,"Ce");
        clickElement(cafetra);
    }

    protected void verifyConsignmnetMessage() throws IOException
    {
        verifyAndLogConfirmationMessage(ConsignmentDetailsMessage);
        String usrn = getVal(ConsignmentDetailsMessage); // Replace with actual logic


        ScenarioContext.set("USRN", usrn);
        System.out.println("Stored USRN: " + usrn);
        try {
            Files.write(Paths.get("usrn.txt"), usrn.getBytes());
        } catch (IOException e) {
            System.out.println("Failed to write USRN to file: " + e.getMessage());
        }

    }

    protected By ConsignmentDetailsMessage = By.xpath("//div[@class='p-message-text']");

    protected By CstockText = By.xpath("//div[@id='section-StockManagementManifestConsignmentFeatureSetConsignmentIncomingPageStocks']/div[1]/h4[1]");

//    protected void verifyConsignementStock()
//    {
//        getAttribute(CstockText,);
//    }

    protected By CstockAdd = By.xpath("//button[@id='StockConsolidation.Grid.add']//span[2]");

    protected void clickOnAddCS()
    {
        clickElement(CstockAdd);
    }

    protected By ProductSelectionPage = By.xpath("//h1[@class='modal-header ng-binding ng-scope' and normalize-space(text())='Product Selection']");

    protected void verifyProductSelectionPage()
    {
        verifyPageHeaderTitle(ProductSelectionPage,"Product Selection","The Product selection Popup Title is");
    }

    protected By Productinput = By.xpath("//input[@id='form-component-OriginalProductGuid']");

    protected By selectIndex1ofProduct = By.xpath("//ul[@class='filter-select-options']/li[1]");
    protected By selectIndex2ofProduct = By.xpath("//button[@class='filter-select-option ng-binding'][1]");
    protected By nextBtn = By.xpath("//button[@class='ng-scope button-primary']");
    protected By inputweight = By.xpath("//input[starts-with(@id, 'form-component-') and contains(@id, '.DisplayOpeningQuantity')]");
    protected By safeZone1 = By.xpath("//*[@id=\"section-StockManagementManifestConsignmentFeatureSetVesselVisit_IncomingStockConsolidationEditWhat\"]/div/div/div[57]/div/div/div[2]/span/span");
    protected By safeZoneinAssignCinHold = By.xpath("//span[@class='ng-binding ng-scope' and normalize-space(text())='Product Description']");
    protected By wieghtinputdiv = By.xpath("(//div[@class='numeric-input-readable input-text ng-binding' and normalize-space(text())='0.000'])[2]");

    protected void enterProductAndclicknext(String product) throws InterruptedException
    {
        setZoomLevel(50);
        enterText(Productinput, product);
        Thread.sleep(5000);
        //waitUntilElementLocated(selectIndex1ofProduct, 5);
        //selectByValue(selectIndex1ofProduct, " MAIZE (MZ) ");
        clickElement(selectIndex2ofProduct);
        //selectDropdownByValue(selectIndex2ofProduct, " MAIZE (MZ) ");
        Thread.sleep(2000);
        clickElement(nextBtn);
    }

    protected By Msg1 = By.xpath("//div[@class='p-message-text']");
    protected void validateMsg()
    {
        String Text = getTextFromElement(Msg1);
        System.out.println("The Message after clicking on save btn : " + Text);
    }

    //Step 9
    protected void productDetails() throws InterruptedException {
        Weightval ="60";
        Thread.sleep(5000);
        enterDynamicNumericInput(wieghtinputdiv,inputweight, Weightval);
        JSClick(safeZone1);
    }
    // validation on Consignment stock has to be added here(vessel--lineno.37)
    //protected By total_mt = By.xpath("//span[@class='data-type-numeric' and contains(text(), '"+Weightval+"')]");
    protected By total_mt = By.xpath("//span[@class='data-type-text' and text()='CEFETRA ']/following::td[3]/span[contains(text(), '"+ Weightval +"')]");

    protected By returnR = By.xpath("//a[@id='bt-cancel' and .//span[normalize-space()='Return']]");

    protected void clickonReturnBtnConsignmentPage()
    {
        JSClick(returnR);
    }

    //Step 11.1
    protected By AddunderCargoinholds = By.xpath("//button[@id='CargoInHold.Grid.add' and .//span[normalize-space()='Add']]");
    protected void clickonAddBtnCargoinholds()
    {
        clickElement(AddunderCargoinholds);
    }
    //Step 12.1
    protected By consginmentInput = By.xpath("//input[@id='form-component-consignmentStock']");
    protected By consignementDropdown = By.xpath("//tbody//tr[contains(@class, 'detailed-filter-select-option')][1]");

    protected void consignmentinputCargoinholds()
    {
        elementVisible(consginmentInput);
        clickElement(consginmentInput);

        elementVisible(consignementDropdown);
        clickElement(consignementDropdown);
    }
    //Step 12.3
    protected By assignCIHTitle = By.xpath("//h1[@class='modal-header ng-binding ng-scope' and normalize-space(text())='Assign Cargo In Hold']");
    protected void verifyAssignCargoinholdpopup()
    {
        verifyPageHeaderTitle(assignCIHTitle,"Assign Cargo In Hold","Assign Cargo in Hold Popup>:");
    }

    //Step 11.2
    protected By cargoinholdTitle = By.xpath("//h1[contains(@class, 'modal-header') and @data-ng-if='formPage.pageData.label'][1]");

    protected void verifyCargoinholdpopup()
    {
        verifyPageHeaderTitle(cargoinholdTitle,"Cargo In Hold","CargoInHold>Popup:");
    }
    //Step 13.1
    protected By hold1 = By.id("form-component-sa.ParentStorageAreaGuid");
    protected By Assignweightinput = By.xpath("//input[starts-with(@id, 'form-component-') and contains(@id, '.DisplayInitialQuantity')]");
    protected By AssignweightDiv = By.xpath("//div[contains(@class,'numeric-input-readable') and contains(@class,'input-text')]");

    //Verification and Validation
    protected void VerifySelectsHold()
    {
        String selectedHold = getTextFromElement(hold1);
        System.out.println("The Selected Hold is:" + selectedHold);
    }
    //Step 13.2
    protected void userentersWeight() throws InterruptedException
    {
        //Weightval is the Globally declared to enter common weight
        Thread.sleep(5000);
        setZoomLevel(50);
        enterDynamicNumericInput(AssignweightDiv,Assignweightinput, Weightval);
        clickElement(safeZoneinAssignCinHold);
    }

    protected By stockidentifierInput = By.xpath("//input[@id='form-component-c_s.StockIdentifier']");
    protected void enterproductDetails()
    {
        stockid = "Arklow Fox - Maize";
        enterText(stockidentifierInput,stockid);

        Weightval ="100";
        //enterText(wieghtinput,Weightval);
        clickElement(safeZone1);
    }



}
