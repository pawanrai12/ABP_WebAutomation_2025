package com.pageobjects.abp;
import com.framework.context.ScenarioContext;
import com.framework.reusable.WebReusableComponents;
import org.openqa.selenium.By;
import com.framework.components.Settings;
import com.framework.cucumber.TestHarness;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.framework.components.ApplitoolsOperations;
import com.framework.components.ScriptHelper;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import static com.framework.selenium.SeleniumTestParameters.getAppUrl;
import static com.pageobjects.abp.VesselVisitPage.USRN;

public class CommonStowagePage extends WebReusableComponents {
    protected ApplitoolsOperations appliTools = new ApplitoolsOperations();

    protected void launchApp() {
        launchUrl(getAppUrl());
        maximizeWindow();
        appliTools.captureContent("");
    }

    protected By CargoMenu = By.xpath("//span[normalize-space(text())='Cargo']");
    protected By CommonStowage = By.xpath("//a[normalize-space(text())='Common Stowage']");
    protected By CommonStowageTitle = By.xpath("//span[normalize-space(text())='Common Stowage']");
    protected By searchInput = By.xpath("//input[@placeholder='Search Name']");
    protected By AddBtn = By.xpath("//span[normalize-space(text())='Add']");
    protected By NameInput = By.xpath("//input[@id='form-component-csg.Name']");
    protected By CustomeridInput = By.id("form-component-csg.OriginalCustomerOrganisationGuid");
    protected By PeacockDrpdwn = By.xpath("(//button[@class='filter-select-option ng-binding'])[1]");
    protected By DrpdwnbyIndex = By.xpath("//li[@data-ng-repeat='option in options'][1]");
    protected By CSaddBtn = By.xpath("//button[@id='csg_csGrid.add']");
    protected By CSGTitle = By.xpath("//span[normalize-space(text())='Common Stowage Group']");
    protected By CSTitlePopup = By.xpath("//h1[@class='modal-header ng-binding ng-scope' and normalize-space(text())='Add to Common Stowage']");
    protected By ConsignmentInput = By.id("form-component-csg_cs.OriginalConsignment_StockGuid");

    protected By saveBtnR = By.xpath("//span[normalize-space()='Save']");
    protected By saveBtnBtm = By.xpath("//button[@data-ng-if=\"action.action == 'save'\" and .//span[text()='Save']]");
    protected By safeZone = By.xpath("//*[@id=\"CommtracVue\"]/div[1]/header/div/div[1]/nav/ol/li[1]/a/span/img");

    //Step 19.1
    protected void clickOnRightSave() {
        JSClick(saveBtnR);

    }

    protected void clickOnBottomSave() {
        clickElement(saveBtnBtm);
    }

    protected By savenextBtn = By.xpath("//span[normalize-space(text())='Save And Next']");

    protected void UserclicksSaveNext() {
        clickElement(savenextBtn);
    }

    //#Step 14.1
    protected void UserNavigatesToCargoCommonStowage() throws InterruptedException {
        clickElement(CargoMenu);
        Thread.sleep(5000);
        scrollIntoViewAndClick(CommonStowage);
        clickElement(safeZone);
    }

    //#Step 14.2
    protected void VerifyCommonStowageGrid() {
        verifyPageHeaderTitle(CommonStowageTitle, "Common Stowage", "Common Stowage Page:");
    }

    //#Step 16.1
    protected void clicksAddinCommonStowage() {
        clickElement(AddBtn);
    }

    //#Step 16.2
    protected void EntersTheCommudityDetails() {
        enterText(NameInput, "OmSairam1");
        enterText(CustomeridInput, "cefe");
        clickElement(PeacockDrpdwn);
        //selectDropdownByValue(PeacockDrpdwn, " PEACOCKS (PEA) - Customer (PEA) ");
        //selectValue(PeacockDrpdwn,"PEACOCKS (PEA) - Customer (PEA)");
    }

    //Details for Truck Intakes-7A Testcases
    protected void EntersTheCommudityDetailsfor7A(String name, String nameinput)
    {
        enterText(NameInput,name);
        enterText(CustomeridInput,nameinput);
        //PeacockDrpdwn also will work
        clickElement(DrpdwnbyIndex);
    }

    //#Step 16.4
    protected void CommonStowageGroupDetailsPage() {
        verifyPageHeaderTitle(CSGTitle, "Common Stowage Group", "Common Stowage Group Page:");
    }

    //#Step 17.1
    protected void UserClicksAddUnderConsignmentStocks() {
        clickElement(CSaddBtn);
    }
    //#Step 17.2
    protected void AddToCommonStowageWindow() {
        verifyPageHeaderTitle(CSTitlePopup, "Add to Common Stowage", "Add to Common Stowage Popup:");
    }

    //#Step 18.1
    protected By AddtoCSList = By.xpath("(//*[@id='section-VisitVessel']//tr[@class='detailed-filter-select-option ng-scope'])[1]");

    protected void theUserSelectsConsignmentStockFor1A() throws IOException {
        String usrn = "";

        try {
            usrn = new String(Files.readAllBytes(Paths.get("usrn.txt")));
            System.out.println("Reused USRN in Home: " + usrn);
        } catch (IOException e) {
            System.out.println("Failed to read USRN from file: " + e.getMessage());
        }

        enterText(ConsignmentInput, usrn);
        clickElement(AddtoCSList);

    }
    protected void UserSelectsConsignmentStockSevenA() throws IOException {
        String intakeusrn = "";

        try {
            intakeusrn = new String(Files.readAllBytes(Paths.get("intakeusrn.txt")));
            System.out.println("Reused IUSRN From 7A: " + intakeusrn);
        } catch (IOException e) {
            System.out.println("Failed to read IUSRN from file: " + e.getMessage());
        }

        enterText(ConsignmentInput, intakeusrn);
        clickElement(AddtoCSList);

    }

    protected void addedStock() {
        String s = getTextofElement(AddtoCSList);
        System.out.println("the stock added to Common Stowage Group is:" + s);
    }

    protected void UserClicksSaveOnCommonStowage() {
        clickOnBottomSave();
    }

    //Step 19.1
    protected By Msg = By.xpath("//div[@class='p-message-content']");

    protected void CommonStowageGroupDetailsMsg() {
        //getTextFromElement(Msg);
        getTextofElement(Msg);
    }

    protected void UserSearchesForCommodity() {
        enterText(searchInput, "search");
    }
}





















































































































































