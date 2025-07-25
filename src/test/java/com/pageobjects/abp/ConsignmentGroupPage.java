package com.pageobjects.abp;

import com.framework.components.ApplitoolsOperations;
import com.framework.reusable.WebReusableComponents;
import org.openqa.selenium.By;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.framework.selenium.SeleniumTestParameters.getAppUrl;

public class ConsignmentGroupPage extends WebReusableComponents {
    protected ApplitoolsOperations appliTools = new ApplitoolsOperations();

    protected void launchApp()
    {
        launchUrl(getAppUrl());
        maximizeWindow();
        appliTools.captureContent("");
    }

    protected By CargoMenu = By.xpath("//span[normalize-space(text())='Cargo']");
    protected By StorageAreaLink = By.xpath("//a[text()='Storage Areas']");
    protected By StorageAreaTab = By.xpath("//span[normalize-space(text())='AUTOMATION AREA']");
    protected By ViewStockyardBtn = By.xpath("//span[normalize-space(text())='View Stockyard']");
    protected By xx = By.xpath(" //span[text()='AUTOMATION AREA'][1]");
    protected void smg()
    {
        clickElement(CargoMenu);
        clickElement(StorageAreaLink);
        clickElement(safeZone);
        clickElement(xx);
        //clickElement(StorageAreaTab);
        clickElement(ViewStockyardBtn);

    }

    protected By saveBtnR = By.id("bt-save");
    protected By saveBtnBtm = By.xpath("//button[@data-ng-if=\"action.action == 'save'\" and .//span[text()='Save']]");
    protected By safeZone = By.xpath("//*[@id=\"CommtracVue\"]/div[1]/header/div/div[1]/nav/ol/li[1]/a/span/img");

    protected void clickOnRightSave() {
        clickOnElementaction(saveBtnR);
    }
    protected void clickOnBottomSave() {
        clickElement(saveBtnBtm);
    }

    protected By savenextBtn = By.xpath("//span[normalize-space(text())='Save And Next']");

    protected void UserclicksSaveNext()
    {
        clickElement(savenextBtn);
    }

    protected By ConsignmentGroupLink = By.xpath("//a[normalize-space(text())='Consignment Group']");
    //#Step 14
    protected void UserNavigatesToCargoConsignmentGroup()
    {
        setZoomLevel(50);
        JSClick(CargoMenu);
        JSClick(ConsignmentGroupLink);
        JSClick(safeZone);
    }
    protected By ConsignmentGroupGridTitle = By.xpath("//span[@class='p-menuitem-text' and text()='Consignment Group']");



    //#Step 14.1
    protected void VerifyConsignmentGroupGrid()
    {
        verifyPageHeaderTitle(ConsignmentGroupGridTitle,"Consignment Group","Consignment Group Grid:");
    }

    protected By CSAdd = By.xpath("//span[@class='text ng-binding' and text()='Add']");
    protected By ConsignmentInput = By.id("form-component-csg_cs.OriginalConsignment_StockGuid");

    //Step 16
    protected void UserClicksAddUnderConsignmentStocks()
    {
        clickElement(CSAdd);
    }
    protected By nameInput = By.id("form-component-csg.Name");
    protected By customerInput = By.id("form-component-csg.OriginalCustomerOrganisationGuid");
    //clicks based on index 1
    protected By customerDrpdwn = By.xpath("//input[@id='form-component-csg.OriginalCustomerOrganisationGuid']/following-sibling::ul[@class='filter-select-options']//li[1]/button");

    //Step 16.1
    protected void UserentersTheCommodityDetails()
    {
       enterText(nameInput,"Hello");
       enterText(customerInput,"cefe");
       clickElement(customerDrpdwn);
    }
    //Step 16.1
    //Details for Truck Intakes-7B Testcases
    protected void EntersTheCommudityDetailsfor7B(String name, String nameinput)
    {
        enterText(nameInput,name);
        enterText(customerInput,nameinput);
        clickElement(customerDrpdwn);
    }


    //Step 16.1
    protected By CGTitlePopup = By.xpath("//h1[@class='modal-header ng-binding ng-scope' and @data-ng-if='formPage.pageData.label' and text()='Add to Consignment Group']");
    protected void VerifyAddtoConsignmentGroupPopup()
    {
        verifyPageHeaderTitle(CGTitlePopup,"Add to Consignment Group","Add to Consignment Pop up:");
    }
    protected By consginmentsavePopup = By.xpath("//div[@class='p-message-text' and @data-pc-section='text']");
    protected void conginementpopupMsg()
    {
        String s = getTextFromElement(consginmentsavePopup);
        System.out.println("The Popup message is:" + s);
    }

    //Step 17
    protected By CSaddBtn = By.xpath("//button[@id='csg_csGrid.add']");
    protected void clickaddConsginmentStock()
    {
        clickElement(CSaddBtn);
    }
    protected By CGTitlePopupText = By.xpath("//h1[@class='modal-header ng-binding ng-scope' and normalize-space(text())='Add to Consignment Group']");
    //Step 17.1
    protected void AddToConsignmentGroupWindow()
    {
        verifyPageHeaderTitle(CGTitlePopupText,"Add to Consignment Group","ADD Consignment Group Popup appears with Header:");
    }

    //Step 18
    protected By AddtoCSList = By.xpath("(//*[@id='section-VisitVessel']//tr[@class='detailed-filter-select-option ng-scope'])[1]");

    protected void UserSelectsConsignmentStockinCG() throws IOException {
        String usrn = "";

        try {
            usrn = new String(Files.readAllBytes(Paths.get("usrn.txt")));
            System.out.println("Reused USRN from the Vessel Page : " + usrn);
        } catch (IOException e) {
            System.out.println("Failed to read USRN from file: " + e.getMessage());
        }

        enterText(ConsignmentInput, usrn);
        clickElement(AddtoCSList);

    }
    //Step 18.2
    protected void clickOnBottomSaveCG()
    {
        JSClick(saveBtnBtm);
    }





//    protected By AddBtn = By.id("cg.Grid.add");
//    protected By CGSearch = By.id("form-component-cg.Name-0");
//    protected By NameInput = By.id("form-component-csg.Name");
//    protected By CustomerInput = By.id("form-component-csg.OriginalCustomerOrganisationGuid");
//    protected By a = By.xpath("//button[@class='filter-select-option ng-binding']");

//    protected By a1 = By.xpath("//tr[@class='detailed-filter-select-option ng-scope'][1]");
//    protected void UserSelectsAConsignmentStock()
//    {
//        clickElement(ConsignmentInput);
//        selectDropdownByValue(a1," CIRCULAR HOLLOW SECTION - S235 - 1_0 CIRCULAR HOLLOW SECTION - S235 - 323.9x0x4x6000");
//    }
//    //#Step 16
//    protected void userclicksAdd()
//    {
//        clickElement(AddBtn);
//        enterText(NameInput,"Rama");
//        enterText(CustomerInput,"a");
//        clickElement(a);
//    }
//    protected By Msg1 = By.xpath("//div[@class='p-message-content']");
//    protected void verifyafterSave()
//    {
//        String s = getTextFromElement(Msg1);
//        System.out.println(s);
//    }















































































































































}


