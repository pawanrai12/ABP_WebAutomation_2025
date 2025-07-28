package com.pageobjects.abp;

import com.framework.reusable.WebReusableComponents;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

public class Bagging_UnbaggingPage extends WebReusableComponents
{
    private final By operationMenu = By.xpath("//div[contains(@class, 'commtrac-menubutton')]//span[text()='Operations']");
    protected By UnbaggingBtn = By.xpath("//li[@label='Unbagging']/a[text()='Unbagging']");
    protected By BagginggBtn = By.xpath("//li[@label='Bagging']/a[text()='Bagging']");

    protected By addBtnBlending = By.xpath("(//button/span[text()='Add'])[1]");

    protected By Selectbagging = By.xpath("//span[@aria-label='Select Product to Bag']");
    protected By SelectUnbagging = By.xpath("//span[@aria-label='Select Product to Unbag']");

    protected By SelectbaggingType = By.xpath("//span[@aria-label='Select Bagging Type']");

    protected By SelectconsignmentstockDrpdwn = By.xpath("(//span[@aria-label='Select Consignment Stock'])[1]");
    protected By SelectstorageareaDrpdwn = By.xpath("(//span[text()='Select a Storage Area'])[1]");

    protected By SelectArea1 = By.xpath("(//span[@data-pc-section='text'])[1]");
    protected By SelectArea2 = By.xpath("(//span[@data-pc-section='text'])[2]");

    protected By numberofbagsInput = By.xpath("//input[@aria-label='Number of Bags']");
    protected By saveNexecute = By.xpath("//button[@aria-label='Save & Execute']");
    protected By yesBtn = By.xpath("//button[@aria-label='Yes']");

    protected By CompletedUnBagging = By.xpath("//span[text()='Completed/Cancelled Unbagging Jobs']");
    protected By CompletedBagging = By.xpath("//span[text()='Completed/Cancelled Bagging Jobs']");


    protected void Selecting_BaggingMenu() throws InterruptedException {
        setZoomLevel(50);
        clickElement(operationMenu);
        Thread.sleep(2000);

        clickElement(BagginggBtn);
        Thread.sleep(2000);

    }
    protected void add_BaggingDetails() throws InterruptedException {
        clickElement(addBtnBlending);
        Thread.sleep(2000);

        selectDropdownDD(Selectbagging,"text","Ammonium Sulphate");
        Thread.sleep(2000);

        selectDropdownDD(SelectbaggingType,"text","1000kg - Bag");
        Thread.sleep(2000);

        selectDropdownDD(SelectconsignmentstockDrpdwn,"text","USRN-1000313");
        Thread.sleep(2000);

        clickElement(SelectstorageareaDrpdwn);
        Thread.sleep(2000);
        clickElement(SelectArea1);
        Thread.sleep(2000);
        clickElement(SelectArea2);
        Thread.sleep(2000);

        enterText(numberofbagsInput,"5");

        selectDropdownDD(SelectconsignmentstockDrpdwn,"text","USRN-1000314");
        Thread.sleep(2000);

        clickElement(SelectstorageareaDrpdwn);
        Thread.sleep(2000);
        clickElement(SelectArea1);
        Thread.sleep(2000);
        clickElement(SelectArea2);
        Thread.sleep(2000);

        clickElement(saveNexecute);
        Thread.sleep(2000);

        clickElement(yesBtn);
        Thread.sleep(2000);

        clickElement(CompletedBagging);
        Thread.sleep(5000);

    }

    protected void Selecting_UnBaggingMenu() throws InterruptedException
    {
        setZoomLevel(50);
        clickElement(operationMenu);
        Thread.sleep(2000);

        clickElement(UnbaggingBtn);
        Thread.sleep(2000);

    }
    protected void add_UnbaggingDetails() throws InterruptedException
    {
        clickElement(addBtnBlending);
        Thread.sleep(2000);

        selectDropdownDD(SelectUnbagging,"text","Ammonium Sulphate");
        Thread.sleep(2000);

        selectDropdownDD(SelectbaggingType,"text","1000kg - Bag");
        Thread.sleep(2000);

        selectDropdownDD(SelectconsignmentstockDrpdwn,"text","USRN-1000314");
        Thread.sleep(2000);

        clickElement(SelectstorageareaDrpdwn);
        Thread.sleep(2000);
        clickElement(SelectArea1);
        Thread.sleep(2000);
        clickElement(SelectArea2);
        Thread.sleep(2000);

        enterText(numberofbagsInput,"5");

        //selecting the consignmnet 313 automatically in Unbagging Process -so removed

        clickElement(SelectstorageareaDrpdwn);
        Thread.sleep(2000);
        clickElement(SelectArea1);
        Thread.sleep(2000);
        clickElement(SelectArea2);
        Thread.sleep(2000);

        clickElement(saveNexecute);
        Thread.sleep(2000);

        clickElement(yesBtn);
        Thread.sleep(2000);

        clickElement(CompletedUnBagging);
        Thread.sleep(5000);

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
    protected By shortcodeInput = By.id("form-component-stk.ShortCode-0-operator");

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
        enterText(areainput,"1000KG- BAGS");
        Thread.sleep(2000);
        clickElement(applyfilterBtn);
        Thread.sleep(2000);

        clickOnTableElementByValue("1000KG- BAGS",TableBlendValue);

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
