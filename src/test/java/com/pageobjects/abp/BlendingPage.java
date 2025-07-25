package com.pageobjects.abp;

import com.framework.components.ApplitoolsOperations;
import com.framework.reusable.WebReusableComponents;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.List;

import static com.framework.selenium.SeleniumTestParameters.getAppUrl;

public class BlendingPage extends WebReusableComponents
{
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
        System.out.println("The Home Page is displayed");
    }

    //Step#1.1
    //private final By adminBtn = By.xpath("//div[contains(@class, 'commtrac-menubutton') and .//span[text()='Admin']]");
    private final By adminBtn = By.xpath("//span[@class='menu-label block text-base' and text()='Admin']");
    protected By masterdataBtn = By.xpath("//li[@label='Master Data']/a[@href='/app/v/MasterData']");
    protected By productsLink = By.xpath("(//span[text()='Products'])[2]");
    protected By addBtnproduct = By.id("Product.Grid.add");
    protected By productInput = By.id("form-component-prod.Name");
    protected By shortcodeInput = By.id("form-component-stk.ShortCode-0-operator");
    protected By Selectdangerousgoods = By.id("form-component-prod.DangerousGoodsTypeDescriptionID");
    //No ([None])
    protected By resultofblendCheckBox = By.id("form-component-prod.BlendedProduct");
    protected By saveRBtn = By.xpath("//button[./span[@class='text ng-binding' and text()='Save']]");
    protected By savenaddRBtn = By.xpath("//button[./span[@class='text ng-binding' and text()='Save and Add']]");
    protected By returRBtn = By.xpath("//a[./span[@class='text ng-binding' and text()='Return']]");
    protected By blendsLink = By.xpath("(//span[text()='Blends'])[1]");
    protected By addBtnblend = By.id("blendSetup.add");
    protected By nameInput = By.id("form-component-bs.name");
    protected By createnewproductCheckbox = By.id("form-component-bs.ShouldBlendConsignments");
    protected By Selectresultingproduct = By.id("form-component-bs.OriginalBlendedProductGuid");
    protected By addBtnunderproducts = By.id("BlendSetup.Products.Grid.add");
    protected By Selectproduct = By.id("form-component-OriginalProductGuid");
    //TSP (TSP)
    //GR (GR)
    protected By divPerInput = By.xpath("//div[contains(@class, 'numeric-input-readable') and @data-ng-hide='hasFocus']");
    protected By inputPerInput = By.xpath("//input[@id='form-component-Percentage']");
    protected By saveBtm = By.xpath("//button[@data-ng-if=\"action.action == 'save'\" and .//span[text()='Save']]");

    protected void hi() throws InterruptedException {
        setZoomLevel(50);
        clickElement(adminBtn);
        Thread.sleep(2000);

        clickElement(masterdataBtn);
        Thread.sleep(2000);

        clickElement(productsLink);
        Thread.sleep(2000);

        clickElement(addBtnproduct);
        Thread.sleep(2000);

        enterText(productInput,"ACITIC ACID");
        Thread.sleep(2000);

        enterText(shortcodeInput,"AC");
        Thread.sleep(2000);

        selectDropdownDD(Selectdangerousgoods,"text","No ([None])");
        Thread.sleep(2000);

        clickElement(resultofblendCheckBox);
        Thread.sleep(2000);

        clickElement(saveRBtn);
        Thread.sleep(2000);

    }
    //Step#1.2
    protected void hello() throws InterruptedException {
        Thread.sleep(2000);

        clickOnElementaction(adminBtn);
        Thread.sleep(2000);

        clickElement(masterdataBtn);
        Thread.sleep(2000);

        clickElement(blendsLink);
        Thread.sleep(2000);

        clickElement(addBtnblend);
        Thread.sleep(2000);

        enterText(nameInput,"H123");
        Thread.sleep(2000);

        clickElement(createnewproductCheckbox);
        Thread.sleep(2000);

        selectDropdownDS(Selectresultingproduct);
        Thread.sleep(2000);

        clickElement(saveRBtn);
        Thread.sleep(2000);

        //Selecting TSP
        clickElement(addBtnunderproducts);
        Thread.sleep(2000);

        selectDropdownDD(Selectproduct,"text","TSP (TSP)");
        Thread.sleep(2000);

        enterDynamicNumericInput(divPerInput,inputPerInput,"60");
        Thread.sleep(2000);

        clickElement(saveBtm);
        Thread.sleep(2000);

        //Selecting GR
        clickElement(addBtnunderproducts);
        Thread.sleep(2000);

        selectDropdownDD(Selectproduct,"text","GR (GR)");
        Thread.sleep(2000);

        enterDynamicNumericInput(divPerInput,inputPerInput,"40");
        Thread.sleep(2000);

        clickElement(saveBtm);
        Thread.sleep(2000);

    }

    private final By operationMenu = By.xpath("//div[contains(@class, 'commtrac-menubutton')]//span[text()='Operations']");
    private final By blendingBtn = By.xpath("//li[@label='Blending']/a[text()='Blending']");
    private final By addBtnBlending = By.xpath("(//button/span[text()='Add'])[1]");
    protected By SelectBlendDrpdwn = By.xpath("//div[contains(@class,'p-dropdown') and .//span[@aria-label='Select Blend']]");
    protected By requiredtonnageInput = By.xpath("(//input[@role='spinbutton'])[1]");
    protected By SelectConsignmentDrpdwn = By.xpath("(//span[@aria-label='Select Consignment'])[1]");
    protected By SelectSourceDrpdwn = By.xpath("(//span[@aria-label='Select Source'])[1]");
    protected By SelectDestinationDrpdwn = By.xpath("//span[@aria-label='Select Destination']");
    protected By saveNexecuteBtn = By.xpath("//button[span/text()=' Save & Execute ']");

    protected By confirmationPopup = By.xpath("//span[@data-pc-section='headertitle' and text()='Confirmation']");
    protected By reasoninputConfirmation = By.xpath("//textarea[@id='noteReason']");
    protected By acceptBtn = By.xpath("//span[text()='Accept']");

    protected void bye() throws InterruptedException {
        setZoomLevel(50);
        clickElement(operationMenu);
        Thread.sleep(2000);

        clickElement(blendingBtn);
        Thread.sleep(2000);

        clickElement(addBtnBlending);
        Thread.sleep(2000);


        selectDropdownDD(SelectBlendDrpdwn,"text","TSP60GR40-1 (TSP60GR40-1)");
        Thread.sleep(5000);

        enterText(requiredtonnageInput,"10");
        Thread.sleep(2000);

        selectDropdownDD(SelectConsignmentDrpdwn,"text","USRN-1000309 - TSP");
        Thread.sleep(2000);
        selectDropdownDD(SelectSourceDrpdwn,"text","AUTOMATION AREA - BLENDING-TSP");
        Thread.sleep(2000);

        scrollIntoView(SelectConsignmentDrpdwn);
        selectDropdownDD(SelectConsignmentDrpdwn,"text","USRN-1000310 - GR");
        Thread.sleep(2000);
        selectDropdownDD(SelectSourceDrpdwn,"text","AUTOMATION AREA - BLENDING-GR");
        Thread.sleep(2000);

        selectDropdownDD(SelectDestinationDrpdwn,"text","AUTOMATION AREA - BLENDED-PRODUCT");
        Thread.sleep(2000);
        selectDropdownDD(SelectConsignmentDrpdwn,"text","USRN-1000312 - TSP60GR40-1");
        Thread.sleep(2000);

    }

    protected void Popuphandling()
    {
        enterText(reasoninputConfirmation,"test");
        clickElement(acceptBtn);
    }

    public void handleSaveAndOverride() {
        try {
            // Perform clickOnSaveNExecute function
            clickElement(saveNexecuteBtn);
            Thread.sleep(2000);

            // Find the WebElement using the By locator
            WebElement popupElement = driver.findElement(confirmationPopup);

            // Check if override popup appears
            waitUntilElementVisible(confirmationPopup,10);

            if (popupElement.isDisplayed()) {
                // If popup appears, handle it
                Popuphandling();
            }
        } catch (NoSuchElementException | TimeoutException | StaleElementReferenceException | InterruptedException e){
            // If popup doesn't appear
            System.out.println("Override popup doesn't appear, moving to next steps. Error: " + e.getMessage());
        }
    }

    //Step 40
    // ******Cargo > Storage Area****
    protected By storageareasMenu = By.xpath("//a[contains(@class, 'menu-label') and text()='Storage Areas']");
    protected By stockMenu = By.xpath("//span[normalize-space(text())='Stock']");
    private final By safeZone = By.xpath("//img[@alt='CommTrac brand logo']");

    protected void UserNavigatesToCargoStorageAreas() throws InterruptedException {
        setZoomLevel(50);
        clickElement(operationMenu);
        clickElement(stockMenu);
        scrollIntoViewAndClick(storageareasMenu);
        Thread.sleep(5000);
        scrollIntoView(safeZone);
    }

    protected By automationarea = By.xpath("//span[@class='data-type-text' and text()='AUTOMATION AREA']");
    protected By storageAreaTable = By.xpath("//span[@data-column-name='stk.Name']");
    protected By viewStockyard = By.xpath("//span[@class='text ng-binding' and text() ='View Stockyard']");
    protected By shortcode = By.xpath("//button[normalize-space(text())='Short Code']");
    protected By shortcodeInputs = By.xpath("//input[@placeholder='Search Short Code']");

    protected void UserNavigateToAreaStockyard() throws InterruptedException {
        Thread.sleep(2000);
        driver.navigate().refresh();
        setZoomLevel(50);
        Thread.sleep(4000);

        clickElement(filterBtn);
        Thread.sleep(2000);
        clickElement(addfilterBtn);
        Thread.sleep(2000);
        clickElement(shortcode);
        Thread.sleep(2000);

        selectDropdownDD(shortcodeInput,"text","Contains");
        Thread.sleep(2000);
        enterText(shortcodeInputs,"AUTO");
        Thread.sleep(2000);
        clickElement(applyfilterBtn);
        Thread.sleep(2000);

        clickOnTableElementByValue("AUTOMATION AREA",storageAreaTable);
        //***Using table method for clicking on area ***//
        //getEle(automationarea).get(1).click();
        scrollIntoViewAndClick(viewStockyard);
        Thread.sleep(2000);

        scrollIntoView(filterBtn);

    }

    //Step 40.1
    protected By viewcontentsBtn = By.xpath("//span[normalize-space(text())='View Contents']");
    protected By filterBtn = By.xpath("//button[@class='popup-toggle button-secondary']");
    protected By addfilterBtn = By.xpath("//button[contains(@class, 'filter-add-button')]");
    protected By area = By.xpath("(//button[normalize-space(text())='Area'])[1]");
    protected By selectarea = By.id("form-component-sys.Name-0-operator");
    protected By areainput = By.xpath("//input[@id='form-component-sys.Name-0']");
    protected By applyfilterBtn = By.xpath("//button[contains(., 'Apply Filter')]");

    protected void areaselection() throws InterruptedException {
        clickElement(filterBtn);
        Thread.sleep(2000);
        clickElement(addfilterBtn);
        Thread.sleep(2000);
        clickElement(area);
        Thread.sleep(2000);
        selectDropdownDD(selectarea,"text","Contains");
        Thread.sleep(2000);

        //***Entering Area Name and applying filter
        enterText(areainput,"BLENDED-PRODUCT");
        Thread.sleep(2000);
        clickElement(applyfilterBtn);
        Thread.sleep(2000);

        clickOnTableElementByValue("BLENDED-PRODUCT",TableBlendValue);

        Thread.sleep(2000);
        clickElement(viewcontentsBtn);

        Thread.sleep(6000);
        printTableValuesSideBySide(consignmentNames,metricTonnes);
    }
    protected By TableBlendValue = By.xpath("//span[@class='data-type-text']");
    protected By consignmentNames = By.xpath("//span[@data-column-name='consignmentName']");
    protected By metricTonnes = By.xpath("//span[@data-column-name='metricTonnes']");
    protected By stockoverviewTitle = By.xpath("//span[normalize-space(text())='Stock Overview']");

    protected void allDischargedProducts()
    {
        System.out.println("All the Products in the Storage Area is displayed");
        verifyPageHeaderTitle(stockoverviewTitle,"Stock Overview","Storage Areas Page Title is:");

    }

}
