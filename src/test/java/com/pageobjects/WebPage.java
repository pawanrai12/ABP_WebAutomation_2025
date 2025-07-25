/*
 *  © [2022] Qualitest. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pageobjects;

import org.openqa.selenium.By;
import org.testng.Assert;
import com.framework.components.ApplitoolsOperations;
import com.framework.reusable.WebReusableComponents;
import static com.framework.selenium.SeleniumTestParameters.getAppUrl;

public class WebPage extends WebReusableComponents {

	protected By homeLink = By.linkText("Techlistic");
	protected By loginHeader = By.xpath("//*[@class='login_logo']");

	protected By txtUserName = By.name("user-name");
	protected By txtPassword = By.name("password");
	protected By btnLogin = By.xpath("//*[@id='login-button']");
	protected By btnSauceBackPack = By.xpath("//*[@id='item_4_title_link']");
	protected By btnSauceBikeLight = By.xpath("//*[@id='item_0_title_link']");
	protected By btnAddToCart = By.xpath("//*[contains(text(),'Add to cart')]");
	protected By btnCart = By.xpath("//*[@class='shopping_cart_link']");
	protected By btnRemoveCart = By.xpath("//*[contains(text(),'Remove')]");
	protected By btnCheckOut = By.xpath("//*[@id='checkout']");
	protected By btnContinue = By.xpath("//*[@id='continue']");
	protected By btnFinish = By.xpath("//*[@id='finish']");
	protected By btnTopMenu = By.xpath("//*[@id='react-burger-menu-btn']");
	protected By btnLogOut = By.xpath("//*[@id='logout_sidebar_link']");
	protected By btnAllItems = By.xpath("//*[@id='inventory_sidebar_link']");

	protected By btnAcceptCookies = By.id("ez-accept-all");
	protected By lblHeader = By.xpath("//*[@class='app_logo']");

    protected By lblSauceBackPack = By.xpath("//*[contains(text(),'Sauce Labs Backpack')]");
	protected By lblSauceBikeLight = By.xpath("//*[contains(text(),'Sauce Labs Bike Light')]");
	protected By lblLoginErrorMessage = By.xpath("//*[contains(text(),'Epic sadface: Username and password do not match any user in this service')]");
	protected By lblEmptyCart = By.xpath("//*[@class='cart_desc_label']");
	protected By lblConfirmOrder = By.xpath("//*[contains(text(),'Thank you for your order!')]");
	protected By btnOk = By.xpath("//span[@class='cookie-choices-buttons']/a[text()=\"Ok\"]");
	protected By txtFirstName = By.xpath("//input[@id='first-name']");
	protected By txtLastName = By.xpath("//input[@id='last-name']");
	protected By txtZipCode = By.xpath("//input[@id='postal-code']");
	protected By btnGender = By.id("sex-0");
	protected By btnExp = By.id("exp-6");
	protected By btnProf = By.id("profession-0");

	protected By btnToolUFT = By.id("tool-0");
	protected By btnToolProtractor = By.id("tool-1");
	protected By btnToolSelenium = By.id("tool-2");

	protected By selectContinents = By.id("continents");
	protected By btnSubmit = By.id("submit");
	protected By btnCookie = By.xpath("//a[contains(text(),'Got it')]");

	protected ApplitoolsOperations appliTools = new ApplitoolsOperations();

	protected void launchApp() {
		launchUrl(getAppUrl());
		maximizeWindow();
		appliTools.captureContent("");
	}



	protected void verifyLogin() {
		Assert.assertTrue(elementVisible(loginHeader, 10));
	}
	protected void enterUserName(String textValue) {
		enterText(txtUserName, textValue);
	}

	protected void enterPassword(String textValue) {
		enterText(txtPassword, textValue);
	}
	protected void clickLoginbutton() {
		if(elementVisible(btnLogin)) {
			clickElement(btnLogin);
		}
	}

	protected void clickButton(By buttonName) {
		if(elementVisible(buttonName)) {
			clickElement(buttonName);
		}
	}
	protected void clickAcceptCookies() {
		if(elementVisible(btnAcceptCookies,10)) {
			clickElement(btnAcceptCookies);
		}
	}

	protected void verifyHome() {
		Assert.assertTrue(elementVisible(lblHeader, 5));
	}

	protected void clickOkButton() {
		if(elementVisible(btnOk)) {
			clickElement(btnOk);
		}
	}

	protected void clickSauceBackPackPagelink() {
		if(elementVisible(btnSauceBackPack)) {
			clickElement(btnSauceBackPack);
		}
	}
	protected void verifySauceBackPackPage() {
		Assert.assertTrue(elementVisible(lblSauceBackPack, 5));
	}

	protected void clickAddToCartButton() {
		if(elementVisible(btnAddToCart)) {
			clickElement(btnAddToCart);
		}
	}

	protected void clickCartButton() {
		if(elementVisible(btnCart)) {
			clickElement(btnCart);
		}
	}
	protected void verifyCartSauceLabBackPack() {
		Assert.assertTrue(elementVisible(lblSauceBackPack, 5));
	}
	protected void clickRemovefromCartButton() {
		if(elementVisible(btnRemoveCart)) {
			clickElement(btnRemoveCart);
		}
	}
	protected void verifyEmptyCart() {
		Assert.assertTrue(elementVisible(lblEmptyCart, 5));
	}
	protected void enterFistName(String firstname) {
		enterText(txtFirstName, firstname);
	}

	protected void enterFirstName(String firstName) {
		enterText(txtFirstName, firstName);
	}

	protected void enterLastName(String lastname) {
		enterText(txtLastName, lastname);
	}

	protected void enterZipCode(String zipCode) {
		enterText(txtZipCode, zipCode);
	}

	protected void clickCheckOutButton() {
		if(elementVisible(btnCheckOut)) {
			clickElement(btnCheckOut);
		}
	}

	protected void clickContinueButton() {
		if(elementVisible(btnContinue)) {
			clickElement(btnContinue);
		}
	}

	protected void clickFinishButton() {
		if(elementVisible(btnFinish)) {
			clickElement(btnFinish);
		}
	}
	protected void clickTopMenuButton() {
		if(elementVisible(btnTopMenu)) {
			clickElement(btnTopMenu);
		}
	}
	protected void clickLogOutButton() {
		if(elementVisible(btnLogOut)) {
			clickElement(btnLogOut);
		}
	}

	protected void clickAllItemButton() {
		if(elementVisible(btnAllItems)) {
			clickElement(btnAllItems);
		}
	}

	protected void clickSauceLabBikeLightButton() {
		if(elementVisible(btnSauceBikeLight)) {
			clickElement(btnSauceBikeLight);
		}
	}

	protected void verifySauceBikeLightPage() {
		Assert.assertTrue(elementVisible(lblSauceBikeLight, 5));
	}

	protected void verifyInvalidCredentialsMessage() {
		Assert.assertTrue(elementVisible(lblLoginErrorMessage, 5));
	}

	protected void verifyOrderConfirmPage() {
		Assert.assertTrue(elementVisible(lblConfirmOrder, 20));
	}

	protected void clickGender() {
		clickElement(btnGender);
	}

	protected void clickExperience() {
		clickElement(btnExp);
	}

	protected void clickProfession() {
		clickElement(btnProf);
	}

	protected void clickToolUFT() {
		clickElement(btnToolUFT);
	}

	protected void clickToolProtractor() {
		clickElement(btnToolProtractor);
	}

	protected void clickToolSelenium() {
		clickElement(btnToolSelenium);
	}

	protected void selectCountry(String value) {
		selectByValue(selectContinents,value);
	}

	protected void submitDetails() {
		clickElement(btnSubmit);
		//appliTools.captureContent("");
	}




}
