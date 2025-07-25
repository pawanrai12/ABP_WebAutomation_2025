package com.cucumber.steps.ABP;

import com.framework.components.Settings;
import com.framework.cucumber.TestHarness;
import com.pageobjects.abp.DeliveryOrderPage;

import java.util.Properties;

import com.framework.components.Settings;
import com.framework.cucumber.TestHarness;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Properties;

public class DeliveryOrderSteps extends DeliveryOrderPage {
    protected TestHarness testHarness = new TestHarness();
    protected Properties properties = Settings.getInstance();

    //Step 15
    @Given("I am on the Delivery Orders grid under Cargo>Booking>Delivery Orders")
    public void iAmOnTheDeliveryOrdersGridUnderCargoBookingDeliveryOrders() throws InterruptedException {
        CargoBookingDeliveryOrders();
    }



































































}
