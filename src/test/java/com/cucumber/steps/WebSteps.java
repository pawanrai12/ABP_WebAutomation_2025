package com.cucumber.steps;

import java.util.Properties;

import com.framework.components.Settings;
import com.framework.cucumber.TestHarness;
import com.pageobjects.WebPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

public class WebSteps extends WebPage {

	protected TestHarness testHarness = new TestHarness();
	protected Properties properties = Settings.getInstance();

	@When("Launch Application using {string}")
	public void launch_Application(String tcid) {
		testHarness.initializeTestData(tcid);
		launchApp();
	}
	@Then("verify login page displayed successfully")
	public void verify_login_page_displayed_successfully() {
		// Write code here that turns the phrase above into concrete actions
		//clickAcceptCookies();
		verifyLogin();
	}

	@When("^User enters userCredentials in text field$")
	public void enterUserCredentials() {

		String userName = testHarness.getData("General_Data", "UserName");
		String password = testHarness.getData("General_Data", "Password");
		enterUserName(userName);
		enterPassword(password);
	}

	@When("User clicks Login button")
	public void clickLoginButton() {

		clickLoginbutton();

	}

	@When("User clicks SauceLabBackPack button")
	public void user_clicks_sauce_lab_back_pack_button() {
		clickSauceBackPackPagelink();
	}
	@Then("User should see SauceLabBackPack Page")
	public void user_should_see_sauce_lab_back_pack_page() {
		verifySauceBackPackPage();
	}
	@When("User clicks AddToCart button")
	public void user_clicks_add_to_cart_button() {
		clickAddToCartButton();
	}
	@When("User clicks Cart button")
	public void user_clicks_cart_button() {

		clickCartButton();
	}
	@When("User should see SauceLabBackPack Product")
	public void user_should_see_sauce_lab_back_pack_product() {

		verifyCartSauceLabBackPack();
	}

	@When("User clicks Remove button")
	public void user_clicks_remove_button() {
		clickRemovefromCartButton();
	}
	@Then("User should see NoProduct in Cart")
	public void user_should_see_no_product_in_cart() {

		verifyEmptyCart();
	}
	@When("User clicks CheckOut button")
	public void user_clicks_check_out_button() {
		clickCheckOutButton();
	}
	@When("User enters Checkout information")
	public void user_enters_checkout_information() {

		String firstName = testHarness.getData("General_Data", "FirstName");
		String lastName = testHarness.getData("General_Data", "LastName");
		String zipCode = testHarness.getData("General_Data", "ZipCode");
		System.out.println(firstName+lastName+zipCode);

		enterFirstName("firstName");
		enterLastName("lastName");
		enterZipCode("zipCode");
	}
	@When("User clicks Continue button")
	public void user_clicks_continue_button() {
		clickContinueButton();
	}
	@When("User clicks Finish button")
	public void user_clicks_finish_button() {
		clickFinishButton();
	}
	@Then("User should see ThankYouOrder Page")
	public void user_should_see_thank_you_order_page() {
		verifyOrderConfirmPage();

	}
	@When("User clicks TopMenu button")
	public void user_clicks_top_menu_button() {
		clickTopMenuButton();
	}
	@When("User clicks LogOut button")
	public void user_clicks_log_out_button() {
		clickLogOutButton();
	}


	@When("User clicks AllItem button")
	public void user_clicks_all_item_button() {
		clickAllItemButton();
	}
	@When("User clicks SauceLabBikeLight button")
	public void user_clicks_sauce_lab_bike_light_button() {
		clickSauceLabBikeLightButton();
	}
	@Then("User should see SauceLabBikeLight Page")
	public void user_should_see_sauce_lab_bike_light_page() {
		verifySauceBikeLightPage();
	}

	@Then("verify error message displayed successfully")
	public void verify_error_message_displayed_successfully() {
		verifyInvalidCredentialsMessage();
	}
	@Then("verify home page displayed successfully")
	public void verifyhome()  {
		//clickAcceptCookies();
		verifyHome();
	//	clickOkButton();
	}

	@Then("enter automation practice form details")
	public void enterdetails() {
		String firstName = testHarness.getData("General_Data", "FirstName");
		String lastName = testHarness.getData("General_Data", "LastName");
		String country = testHarness.getData("General_Data", "Country");
		enterFistName(firstName);
		enterLastName(lastName);
		clickGender();
		clickExperience();
		clickProfession();
		clickProfession();
		clickToolProtractor();
		clickToolSelenium();
		selectCountry(country);
	}

	@Then("submit the entered details")
	public void submitdetails() {
		submitDetails();
	}

	@Given("run functional test script for {string} from {string}")
	public void run_functional_test_script(String testcaseName, String file) {    
		if(file.contains(".json")) {
			runAutomationTests(file,testcaseName);
		} else {
			findJsonFilesInFolder(file,testcaseName);
		}
	}
	
}
