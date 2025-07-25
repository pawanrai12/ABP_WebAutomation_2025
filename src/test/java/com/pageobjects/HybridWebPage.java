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

import com.framework.components.Settings;
import com.framework.cucumber.TestHarness;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.framework.components.ApplitoolsOperations;
import com.framework.components.ScriptHelper;
import com.framework.reusable.WebReusableComponents;

import java.util.Properties;

import static com.framework.selenium.SeleniumTestParameters.getAppUrl;

public class HybridWebPage extends WebReusableComponents {
	protected TestHarness testHarness = new TestHarness();
	protected Properties properties = Settings.getInstance();
	public HybridWebPage(ScriptHelper scriptHelper) {
		super(scriptHelper);
	}

	protected By homeLink = By.linkText("Techlistic");
	protected By btnAcceptCookies = By.id("ez-accept-all");
	protected By lblHeader = By.xpath("//div[contains(text(),'Swag Labs')]");
	protected By btnOk = By.xpath("//a[contains(text(),'OK')]");
	protected By txtLoginID = By.xpath("//input[@id='user-name']");
	protected By txtPassword = By.xpath("//input[@id='password']");
	protected By btnGender = By.id("sex-0");
	protected By btnExp = By.id("exp-6");
	protected By btnProf = By.id("profession-0");
	protected By btnToolUFT = By.id("tool-0");
	protected By btnToolProtractor = By.id("tool-1");
	protected By btnToolSelenium = By.id("tool-2");
	protected By selectContinents = By.id("continents");
	protected By btnSubmit = By.id("login-button");
	protected By btnCookie = By.xpath("//a[contains(text(),'Got it')]");
	
	ApplitoolsOperations appliTools = new ApplitoolsOperations();

	public void launchApp() {
		launchUrl(getAppUrl());
		//maximizeWindow();
		appliTools.openEyes(driver);
		appliTools.captureContent("");
	}

	public void clickAcceptCookies() {
		clickElement(btnAcceptCookies);
	}
	
	public void verifyHomeScreen() {
		Assert.assertTrue(elementVisible(lblHeader, 20));
	}
	
	public void clickOkButton() {
		if(elementVisible(btnOk)) {
			clickElement(btnOk);
		}
	}
	
	public void enterLoginId(String loginName) {
		enterText(txtLoginID, loginName);
	}
	
	public void enterPassword(String password) {
		enterText(txtPassword, password);
	}
	
	public void clickGender() {
		clickElement(btnGender);
	}
	
	public void clickExperience() {
		clickElement(btnExp);
	}
	
	public void clickProfession() {
		clickElement(btnProf);
	}
	
	public void clickToolUFT() {
		clickElement(btnToolUFT);
	}
	
	public void clickToolProtractor() {
		clickElement(btnToolProtractor);
	}
	
	public void clickToolSelenium() {
		clickElement(btnToolSelenium);
	}
	
	public void selectCountry(String value) {
		selectByValue(selectContinents,value);
	}

	public void clickSubmit() {
		clickElement(btnSubmit);
	}
}
