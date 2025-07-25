package com.pageobjects.abp;

import com.framework.reusable.WebReusableComponents;
import org.openqa.selenium.By;
import com.framework.context.ScenarioContext;
import org.openqa.selenium.*;
import org.testng.Assert;

import com.framework.components.ApplitoolsOperations;
import com.framework.reusable.WebReusableComponents;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static com.framework.selenium.SeleniumTestParameters.getAppUrl;

public class DeliveryOrderPage extends WebReusableComponents {
    public static String Weightval;
    String stockid;
    public static String USRN;
    public static String JOBID;
    public static String JOBID1;
    protected By loginHeader = By.name("user-name");
    //url --https://cmt-bulk-cdf-ppd.abph.int/app/v/signin

    protected By UserName = By.xpath("//input[@type='text']");
    protected By Password = By.xpath("//input[@type='password']");
    protected By signinButton = By.xpath("//div[@id='CommtracVue']//form//button[1]/span");

    protected By vesselTab = By.xpath("//span[normalize-space(text())='Vessel']");
    protected By visitBtn = By.xpath("//a[normalize-space(text())='Visits']");
    protected By addBtn = By.xpath("//span[normalize-space(text())='Add']");

    //Step 15
    protected By Cargo = By.xpath("//span[normalize-space(text())='Cargo']");
    protected By Booking = By.xpath("//span[normalize-space(text())='Booking']");
    protected By downArrow = By.xpath("//i[@class='pi pi-fw pi-chevron-down ml-auto pr-2']");
    //protected By Shuntorders =By.xpath("//a[@class='menu-label text-left d-block w-full' and text()='Shunt Orders']");

    protected By Shuntorders = By.xpath("//li[@label='Shunt Orders']/a[text()='Shunt Orders']");

    protected void CargoBookingDeliveryOrders() throws InterruptedException {
        clickElement(Cargo);
        clickElement(downArrow);
        Thread.sleep(2000);
        scrollIntoViewAndClick(Shuntorders);
    }




















































}
