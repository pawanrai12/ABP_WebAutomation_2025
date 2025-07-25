package com.pageobjects.abp;

import com.framework.components.ApplitoolsOperations;
import com.framework.reusable.WebReusableComponents;
import org.openqa.selenium.By;
import org.testng.Assert;

import static com.framework.selenium.SeleniumTestParameters.getAppUrl;

public class New_Vessel_Page extends WebReusableComponents
{
    //#Step 1.1
    protected ApplitoolsOperations appliTools = new ApplitoolsOperations();

    protected void launchApp()
    {
        launchUrl(getAppUrl());
        maximizeWindow();
        appliTools.captureContent("");
    }

    //#Step 1.2
    protected By UserName = By.xpath("//input[@type='text']");
    protected By Password = By.xpath("//input[@type='password']");
    protected By signinButton = By.xpath("//div[@id='CommtracVue']//form//button[1]/span");
    protected By homepageTitle = By.xpath("//span[text()='Welcome to CommTrac']");

    protected void enterUserName(String Username1) { enterText(UserName, Username1);}
    protected void enterPassword(String Password1) { enterText(Password, Password1);}
    protected void clickonSigninButton() { clickElement(signinButton); }

    //#Step 1.3
    protected void verifyHomepage() {
        verifyPageHeaderTitle(homepageTitle,"Welcome to CommTrac","Home Page Header is :");
    }

    //#Step 2.1
    protected By vesselTab = By.xpath("//span[normalize-space(text())='Vessel']");
    protected By visitBtn = By.xpath("//a[normalize-space(text())='Visits']");

    protected void clickonVessel_Visit()
    {
        setZoomLevel(50);
        clickElement(vesselTab);
        clickElement(visitBtn);
    }
    //#Step 2.2
    protected By vesselvisitsTitle = By.xpath("//span[text()='Vessel Visits']");
    protected void verifyVesselVisitpage()
    {
        verifyPageHeaderTitle(vesselvisitsTitle,"Vessel Visits","Vessel>Visit-Page:");
    }

    //#Step 3.1
    protected By  addPlusBtn= By.xpath("(//span[@data-pc-section='label' and text()='Add'])[2]");
    protected By safeZone = By.xpath("//*[@id=\"CommtracVue\"]/div[1]/header/div/div[1]/nav/ol/li[1]/a/span/img");

    protected void safe()
    {
        clickElement(safeZone);
    }
    protected void add_Visit() {

        clickElement(addPlusBtn);
    }

    //#Step 3.2
    //Same verifyHomepage() --1.3 step is used as the Title is similar

    //*** STEP 4.1 ***
    //Change here for Excel --Vessel Name and add xpath for each selected items
    protected By vesselNameinput = By.xpath("(//input[@data-pc-name='pcinputtext'])[1]");
    protected By  vessel_ARKLOWARTIST = By.xpath("//button[@class='filter-select-option ng-binding' and normalize-space(text())='ARKLOW ARTIST (9851983)']");
    protected void enterdetailsofVessel(String vesselName)
    {
        enterText(vesselNameinput, vesselName);
        clickElement(vessel_ARKLOWARTIST);
    }

    //*** STEP 4.2 ***
    //*** STEP 4.1 ***
    //*** STEP 4.1 ***
    //*** STEP 4.1 ***
    //*** STEP 4.1 ***
    //*** STEP 4.1 ***
}
