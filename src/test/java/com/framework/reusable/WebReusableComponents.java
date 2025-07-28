/*
 *  © [2022] Qualitest. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http:www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.framework.reusable;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;
//import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.JsonNode;
import com.framework.components.FrameworkException;
import com.framework.components.FrameworkParameters;
import com.framework.components.RestAssuredUtils;
import com.framework.components.ScriptHelper;
import com.framework.components.Settings;
import com.framework.components.ToolName;
import com.framework.cucumber.DriverManager;
import com.framework.cucumber.TestHarness;
import com.framework.data.FrameworkDataTable;
import com.framework.report.Status;
import com.framework.selenium.CustomDriver;
import com.framework.webcrawler.ReadProperties;
import org.testng.asserts.SoftAssert;

import static com.framework.selenium.SeleniumTestParameters.getTimeOut;

/**
 * Abstract base class for reusable libraries created by the user
 * 
 * @author Qualitest
 */
public abstract class WebReusableComponents extends GenericResuableComponents {

	private int responseStatus;
	private HttpURLConnection httpURLConnect;

	/**
	 * The {@link FrameworkDataTable} object (passed from the test script)
	 */
	protected FrameworkDataTable dataTable;

	/**
	 * The {@link CustomDriver} object
	 */
	protected WebDriver driver;

	/**
	 * The {@link ScriptHelper} object (required for calling one reusable library
	 * from another)
	 */
	protected ScriptHelper scriptHelper;

	/**
	 * The {@link Properties} object with settings loaded from the framework
	 * properties file
	 */
	protected Properties properties;

	/**
	 * The {@link } object
	 */
	protected RestAssuredUtils apiDriver;

	/**
	 * The {@link FrameworkParameters} object
	 */
	protected FrameworkParameters frameworkParameters;


	/**
	 * Constructor to initialize the {@link ScriptHelper} object and in turn the
	 * objects wrapped by it
	 * 
	 * @param scriptHelper The {@link ScriptHelper} object
	 */
	Actions action;
	public WebReusableComponents(ScriptHelper scriptHelper) {
		this.scriptHelper = scriptHelper;
		action = new Actions(driver);
		if (scriptHelper != null) {
			this.dataTable = scriptHelper.getDataTable();
			this.report = scriptHelper.getReport();
			this.driver = scriptHelper.getcustomDriver().getWebDriver();	
			this.apiDriver = scriptHelper.getApiDriver();
			properties = Settings.getInstance();

			this.driver = scriptHelper.getcustomDriver().getWebDriver();

			frameworkParameters = FrameworkParameters.getInstance();
		}
		
	}


	/**
	 * Constructor to initialize Appium or Web driver based on the parameters
	 */
	public WebReusableComponents() {
		if (DriverManager.getTestParameters().getMobileToolName().equals(ToolName.APPIUM))
			this.driver = DriverManager.getAppiumDriver();
		else
			this.driver = DriverManager.getWebDriver();
	}

	/**
	 * Function to check the All Broken Links available in the Page
	 * 
	 */
	protected void validateAllLinksInPage() {

		String url;
		int responseCode;

		List<WebElement> links = driver.findElements(By.tagName("a"));

		Iterator<WebElement> it = links.iterator();

		while (it.hasNext()) {

			url = it.next().getAttribute("href");

			if (url == null || url.isEmpty()) {
				continue;
			}

			try {
				httpURLConnect = (HttpURLConnection) (new URL(url).openConnection());

				httpURLConnect.setRequestMethod("HEAD");

				httpURLConnect.connect();

				responseCode = httpURLConnect.getResponseCode();

				if (responseCode >= 400) {
					addTestLog(url, "Response code : " + responseStatus + " - BROKEN", Status.WARNING);
				} else {
					addTestLog(url, "Response code : " + responseStatus + " - OK", Status.DONE);
				}

			} catch (MalformedURLException e) {
				addTestLog("ValidateURL", "Error while validating URL" + e.getMessage(), Status.WARNING);

			} catch (IOException e) {
				addTestLog("ValidateURL", "Error while validating URL" + e.getMessage(), Status.WARNING);
			}
		}

	}



	/**
	 * Function to check the All Broken Image Links available in the Page
	 * 
	 */
	protected void validateAllImageLinksInPage() {

		String url;
		int responseCode;

		List<WebElement> links = driver.findElements(By.tagName("img"));

		Iterator<WebElement> it = links.iterator();

		while (it.hasNext()) {

			url = it.next().getAttribute("href");

			if (url == null || url.isEmpty()) {
				continue;
			}

			try {
				httpURLConnect = (HttpURLConnection) (new URL(url).openConnection());

				httpURLConnect.setRequestMethod("HEAD");

				httpURLConnect.connect();

				responseCode = httpURLConnect.getResponseCode();

				if (responseCode >= 400) {
					addTestLog(url, "Response code : " + responseStatus + " - BROKEN", Status.WARNING);
				} else {
					addTestLog(url, "Response code : " + responseStatus + " - OK", Status.DONE);
				}

			} catch (MalformedURLException e) {
				addTestLog("ValidateURL", "Error while validating URL" + e.getMessage(), Status.WARNING);

			} catch (IOException e) {
				addTestLog("ValidateURL", "Error while validating URL" + e.getMessage(), Status.WARNING);
			}
		}

	}

	public List<WebElement> getEle(By locator){
		List<WebElement> links = driver.findElements(locator);
			return links;
	}
	/**
	 * Function to format the given time variable as specified by the
	 * DateFormatString setting
	 * 
	 * @param time             The date/time variable to be formatted
	 * @param dateFormatString The date format string to be applied
	 * @return The specified date/time, formatted as per the date format string
	 *         specified
	 * @see #getCurrentFormattedTime(String)
	 */
	public static String getFormattedTime(Date time, String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		return dateFormat.format(time);
	}

	/**
	 * Function to get the time difference between 2 {@link Date} variables in
	 * minutes/seconds format
	 * 
	 * @param startTime The start time
	 * @param endTime   The end time
	 * @return The time difference in terms of hours, minutes and seconds
	 */
	public static String getTimeDifference(Date startTime, Date endTime) {
		long timeDifferenceSeconds = (endTime.getTime() - startTime.getTime()) / 1000;
		long timeDifferenceMinutes = timeDifferenceSeconds / 60;

		String timeDifferenceDetailed;
		if (timeDifferenceMinutes >= 60) {
			long timeDifferenceHours = timeDifferenceMinutes / 60;

			timeDifferenceDetailed = Long.toString(timeDifferenceHours) + " hour(s), "
					+ Long.toString(timeDifferenceMinutes % 60) + " minute(s), "
					+ Long.toString(timeDifferenceSeconds % 60) + " second(s)";
		} else {
			timeDifferenceDetailed = Long.toString(timeDifferenceMinutes) + " minute(s), "
					+ Long.toString(timeDifferenceSeconds % 60) + " second(s)";
		}

		return timeDifferenceDetailed;
	}

	/**
	 * Function to return the current time
	 * 
	 * @return The current time
	 * @see #getCurrentFormattedTime(String)
	 */
	public static Date getCurrentTime() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * Function to return the current time, formatted as per the DateFormatString
	 * setting
	 * 
	 * @param dateFormatString The date format string to be applied
	 * @return The current time, formatted as per the date format string specified
	 * @see #getCurrentTime()
	 * @see #getFormattedTime(Date, String)
	 */
	public static String getCurrentFormattedTime(String dateFormatString) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
		Calendar calendar = Calendar.getInstance();
		return dateFormat.format(calendar.getTime());
	}

	/**
	 * Function to pause the execution for the specified time period
	 * 
	 * @param timeInseconds The wait time in seconds
	 */
	public void waitUntilElementPresent(Long timeInseconds) {
		long endTime = System.currentTimeMillis() + (timeInseconds * 1000);
		while (System.currentTimeMillis() < endTime) {
		}
	}

	/**
	 * Function to wait until the page loads completely
	 * 
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilPageLoaded(long timeOutInSeconds) {
		WebElement oldPage = driver.findElement(By.tagName("html"));

		(new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds))).until(ExpectedConditions.stalenessOf(oldPage));

	}

	/**
	 * Function to wait until the page readyState equals 'complete'
	 * 
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilPageReadyStateComplete(long timeOutInSeconds) {
		ExpectedCondition<Boolean> pageReadyStateComplete = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};

		(new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds))).until(pageReadyStateComplete);
	}

	/**
	 * Function to wait until the specified element is located
	 * 
	 * @param by               The {@link WebDriver} locator used to identify the
	 *                         element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilElementLocated(By by, long timeOutInSeconds) {
		(new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds))).until(ExpectedConditions.presenceOfElementLocated(by));
	}

	/**
	 * Function to wait until the specified element is visible
	 *
	 * @param by               The {@link WebDriver} locator used to identify the
	 *                         element
	 * @param timeOutInSeconds The wait timeout in seconds
	 * @return
	 */
	public WebElement waitUntilElementVisible(By by, long timeOutInSeconds) {

		(new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds))).until(ExpectedConditions.visibilityOfElementLocated(by));
		return null;
	}

	/**
	 * Function to wait until the specified element is visible
	 *
	 * @param by               The {@link WebDriver} locator used to identify the
	 *                         element
	 * @param timeOutInSeconds The wait timeout in seconds
	 * @return
	 */
	public WebElement waitUntilElementClickable(By by, long timeOutInSeconds) {

		(new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds))).until(ExpectedConditions.elementToBeClickable(by));
		return null;
	}

	/**
	 * Function to wait until the specified element is enabled
	 * 
	 * @param by               The {@link WebDriver} locator used to identify the
	 *                         element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilElementEnabled(By by, long timeOutInSeconds) {

		(new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds))).until(ExpectedConditions.elementToBeClickable(by));

	}

	/**
	 * Function to wait until the specified element is disabled
	 * 
	 * @param by               The {@link WebDriver} locator used to identify the
	 *                         element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilElementDisabled(By by, long timeOutInSeconds) {

		(new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds)))
				.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(by)));

	}

	/**
	 * Function to do a mouseover on top of the specified element
	 * 
	 * @param by The {@link WebDriver} locator used to identify the element
	 */
	public void mouseOver(By by) {
		try {
			waitUntilElementVisible(by, 3);
			Actions actions = new Actions(driver);
			actions.moveToElement(driver.findElement(by)).build().perform();
			addTestLog("Move to element ", "The element moved successfully - " + by, Status.PASS);
		} catch (Exception e) {
			addTestLog("Move to element ", "The element not moved - " + by + ". Error message - " + e.getMessage(),
					Status.FAIL);
		}
	}

	/**
	 * Function to verify whether the specified object exists within the current
	 * page
	 * 
	 * @param by The {@link WebDriver} locator used to identify the element
	 * @return Boolean value indicating whether the specified object exists
	 */
	public Boolean objectExists(By by) {
		return !driver.findElements(by).isEmpty();
	}

	/**
	 * Function to verify whether the specified text is present within the current
	 * page
	 * 
	 * @param textPattern The text to be verified
	 * @return Boolean value indicating whether the specified test is present
	 */
	public Boolean isTextPresent(String textPattern) {
		return driver.findElement(By.cssSelector("BODY")).getText().matches(textPattern);
	}

	/**
	 * Function to check if an alert is present on the current page
	 * 
	 * @param timeOutInSeconds The number of seconds to wait while checking for the
	 *                         alert
	 * @return Boolean value indicating whether an alert is present
	 */
	public Boolean isAlertPresent(long timeOutInSeconds) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds)).until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (TimeoutException ex) {
			return false;
		}
	}

	/**
	 * Function to click element when it is visible
	 * 
	 * @param by The {@link WebDriver} locator used to identify the element //
	 *           * @param name of the element
	 */
	public void clickElement(By by) {
		try {
			// JavascriptExecutor js = (JavascriptExecutor) driver;
			// js.executeScript("argument[0].scrollIntoView(false);",driver.findElement(by));
			waitUntilElementClickable(by, 8);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));

			driver.findElement(by).click();
			addTestLog("Click Element", by + " is clicked successfully", Status.PASS);
//			waitUntil(1);
//			
		} catch (Exception e) {
			addTestLog("Click Element", "The element is NOT clicked - " + by + ". Error message - " + e.getMessage(),
					Status.FAIL);
		}
	}

//	public void clickElement(By by, String index) {
//		try {
//		//	JavascriptExecutor js = (JavascriptExecutor) driver;
//		//	js.executeScript("argument[0].scrollIntoView(false);",driver.findElement(by));
//			waitUntilElementClickable(by, 8);
//			driver.findElement(by).click();
//			addTestLog("Click Element", by + " is clicked successfully", Status.PASS);
//			waitUntil(1);
////			
//		} catch (Exception e) {
//			addTestLog("Click Element", "The element is NOT clicked - " + by + ". Error message - " + e.getMessage() , Status.FAIL);
//		}
//	}

	/**
	 * Function to enter text in element when it is visible
	 * 
	 * @param by           The {@link WebDriver} locator used to identify the
	 *                     element
	 * @param valueToEnter of the element
	 */
	public void enterText(By by, String valueToEnter) {
		try {
			WebElement e = driver.findElement(by);
			waitUntilElementVisible(by, 3);
			WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
			w.until(ExpectedConditions.elementToBeClickable(by));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", e);

			e.clear();
			e.sendKeys(valueToEnter);
			//e.sendKeys(Keys.TAB);
			addTestLog("Enter Text ", "[" + valueToEnter + "] is entered successfully in " + by, Status.PASS);

		} catch (Exception e) {
			addTestLog("Click Element", "The element is NOT entered - " + by + ". Error message - " + e.getMessage(),
					Status.FAIL);
		}
	}


	/**
	 * Function to verify if element is redirected to correct URL
	 * 
	 * @param expURL
	 */
	public void verifyRedirect(String expURL) {
		if (driver.getCurrentUrl().equalsIgnoreCase(expURL))
			addTestLog("Redirect", "Current URL is same as expected URL " + expURL, Status.PASS);
		else
			addTestLog("Redirect", "Current URL is same as expected URL " + expURL, Status.FAIL);
	}

	/**
	 * Function to get list of WebElements
	 * 
	 * @param by The {@link WebDriver} locator used to identify the element
	 */
	public List<WebElement> getWebElementList(By by) {
		List<WebElement> element = null;
		try {
			waitUntilElementLocated(by, 3);
			element = driver.findElements(by);
		} catch (Exception e) {
			addTestLog("Get element list", "Unable to get element list - " + by + ". Error message - " + e.getMessage(),
					Status.FAIL);
		}
		return element;
	}

	/**
	 * Function to get text from a element
	 * 
	 * @param by The {@link WebDriver} locator used to identify the element
	 */

	public String getTextFromElement(By by) {
		String text = null;
		try {
			waitUntilElementLocated(by, 3);
			WebElement element = driver.findElement(by);

			// Try getText()
			text = element.getText().trim();

			// If getText() is empty, try value attribute (useful for input, textarea, etc.)
			if (text.isEmpty()) {
				text = element.getAttribute("value");
			}

			// As fallback, try innerText (for certain Angular/React dynamic elements)
			if (text == null || text.trim().isEmpty()) {
				text = element.getAttribute("innerText");
			}

			// Final fallback to ensure not null
			if (text == null) {
				text = "";
			}

			addTestLog("Get Text", "Successfully retrieved text for " + by + ": '" + text + "'", Status.PASS);
		} catch (Exception e) {
			addTestLog("Get Text", "Failed to retrieve text for " + by + ". Error: " + e.getMessage(), Status.FAIL);
			System.out.println("Exception in getTextFromElement: " + e.getMessage());
		}
		return text;
	}


	/**
	 * Function to assign default value when it is null or blank
	 * 
	 * @param value of the variable
	 */
	public String checkifNullorEmpty(String value) {
		if (value == null || value.equals(""))
			return "0";
		else
			return value;
	}

	/**
	 * Function to get value for the locator
	 * 
	 * @param classname   of the class
	 * @param locatorName of the locator
	 */
	public String getValue(Object classname, String locatorName) {

		try {
			Field field = classname.getClass().getField(locatorName);
			return field.get(classname).toString();
		} catch (Exception e) {
			throw new FrameworkException("The variable name is not declared in the class file. " + e.getMessage());
		}

	}

	/**
	 * Function to check if element is visible
	 * 
	 * @param by The {@link WebDriver} locator used to identify the element
	 */
	public boolean elementVisible(By by) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Function to check if element is visible
	 * 
	 * @param by The {@link WebDriver} locator used to identify the element
	 */
	public boolean elementVisible(By by, long seconds) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Function to get current url
	 * 
	 * @return
	 */
	public String getCurrentURL() {
		String url = null;
		try {
			url = driver.getCurrentUrl();
			addTestLog("Get URL ", "URL retrieved successfully. URL - " + url, Status.PASS);
		} catch (Exception e) {
			addTestLog("Get URL", "Unable to retrieve url. Error message - " + e.getMessage(), Status.FAIL);
		}
		return url;
	}

	/**
	 * Function to switch to latest window
	 */
	public void switchToLastestWindow() {
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
			addTestLog("Switch Window", "Window switched successfully. Window - " + winHandle, Status.PASS);
		}
	}

	/**
	 * Function to switch to default window
	 */
	public void switchToParentWindow() {
		driver.switchTo().defaultContent();
	}

	/**
	 * Function to wait until the new tab loads
	 * 
	 */
	public void waitUntilNewTabLoads() {
		Set<String> window = driver.getWindowHandles();
		while (!(window.size() > 1)) {
			window = driver.getWindowHandles();
		}
	}

	/**
	 * Function to wait until the page loads
	 * 
	 * @param url Provide the url to wait
	 */
	public void waitUntilPageLoads(String url) {
		try {
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(
					webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.URL").equals(url));
			addTestLog("Wait Page Loads", "Wait until the page loads. URL - " + url, Status.PASS);
		} catch (Exception e) {
			addTestLog("Wait Page Loads", "Error while waiting - " + e.getMessage(), Status.FAIL);
		}

	}

	/**
	 * Function to get the attribute values
	 * 
	 * @param by        Selenium id,xpath,css of the element
	 * @param attribute Attribute value to get
	 * @return
	 */
	public String getAttribute(By by, String attribute) {
		String value = null;
		try {
			value = driver.findElement(by).getAttribute(attribute);
			addTestLog("Get Attribute", "To get attribute - " + attribute + " , value is - " + value, Status.PASS);
		} catch (Exception e) {
			addTestLog("Get Attribute", "Unable to retrieve attribute - " + attribute, Status.FAIL);
		}
		return value;

	}

	/**
	 * Function to get the time period
	 * 
	 * @param value      The date need to be retrieved. Ex: Current week, Previous
	 *                   week, Current month etc.,
	 * @param dateFormat The date format to return
	 * @return
	 */
	public String getTimePeriod(String value, String dateFormat) {

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

		switch (value) {
		case "Current Week":
			calendar.setFirstDayOfWeek(Calendar.MONDAY);
			while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
				calendar.add(Calendar.DATE, -1);
			}
			String startDate = formatter.format(calendar.getTime());
			calendar.add(Calendar.DATE, 6);
			return startDate + " - " + formatter.format(calendar.getTime());

		case "Previous Week":
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			calendar.add(Calendar.DATE, -7);
			String firstDateOfPreviousWeek = formatter.format(calendar.getTime());
			calendar.add(Calendar.DATE, 6);
			return firstDateOfPreviousWeek + " - " + formatter.format(calendar.getTime());

		case "Previous Month":
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DATE, 1);
			String firstDateOfPreviousMonth = formatter.format(calendar.getTime());
			calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			return firstDateOfPreviousMonth + " - " + formatter.format(calendar.getTime());

		case "Year To Date":
			calendar.set(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			String yearenddate = formatter.format(calendar.getTime());
			Date date = new Date();
			return yearenddate + " - " + formatter.format(date);

		default:
			return null;
		}
	}

	/**
	 * Function to open a new tab
	 * 
	 */
	public void openNewTab() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		try {
			jse.executeScript("window.open()");
			addTestLog("Open New tab", "New tab opened successfully", Status.PASS);
		} catch (Exception e) {
			addTestLog("Open New tab", "Unable to open new tab", Status.FAIL);
		}

	}

	/**
	 * Function to wait until the specified element is located
	 * 
	 * @param by               The {@link WebDriver} locator used to identify the
	 *                         element
	 * @param timeOutInSeconds The wait timeout in seconds
	 */
	public void waitUntilInvisibilityOfElementLocated(By by, long timeOutInSeconds) {
		try {
			(new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds))).until(ExpectedConditions.invisibilityOfElementLocated(by));
			addTestLog("Wait until Invisibility", "The element - " + by + " is invisible as expected", Status.PASS);
		} catch (Exception e) {
			addTestLog("Wait until Invisibility",
					"The element - " + by + " is displayed after waiting - " + timeOutInSeconds + " seconds",
					Status.FAIL);
		}

	}

	/**
	 * Function to switch window by using title name
	 * 
	 * @param titleName The title name
	 * 
	 */
	public void switchToWindow(String titleName) {
		try {
			String currentWindow = driver.getWindowHandle();
			for (String winHandle : driver.getWindowHandles()) {
				if (driver.switchTo().window(winHandle).getTitle().contains(titleName))
					break;
				else
					driver.switchTo().window(currentWindow);
			}
			addTestLog("Switch To window", "The window - " + currentWindow + " is switched successfully", Status.PASS);
		} catch (Exception e) {
			addTestLog("Switch To window", "Unable to switch window - " + titleName, Status.FAIL);
		}

	}

	/**
	 * Function to click on element using action builder class
	 * 
	 * @param by xpath/id/classname/name for the element
	 */
	public void clickOnElementaction(By by) {
		try {
			waitUntilElementLocated(by, 3);
			WebElement element = driver.findElement(by);
			Actions action = new Actions(driver);
			action.moveToElement(element).click().perform();
			addTestLog("Click - Action Builder", "The element - " + by + " is clicked successfully", Status.PASS);
		} catch (Exception e) {
			addTestLog("Click - Action Builder",
					"The element - " + by + " is unable to click. Error - " + e.getMessage(), Status.FAIL);
		}

	}

	/**
	 * Function to convert number to dollar value
	 * 
	 * @param currencyAmount Provide the number you need to convert
	 * @return dollar amount
	 */
	public String convertNumberToDollar(double currencyAmount) {
		String dollarValue = null;
		try {
			Locale usa = new Locale("en", "US");
			NumberFormat dollarFormat = NumberFormat.getCurrencyInstance(usa);
			dollarValue = dollarFormat.format(currencyAmount);
			addTestLog("Convert Dollar value", "The number is converted to dollar value - " + dollarValue, Status.PASS);
		} catch (Exception e) {
			addTestLog("Convert Dollar value", "Unable to convert dollar value - " + dollarValue, Status.FAIL);
		}
		return dollarValue;
	}

	/**
	 * Function to launch url
	 * 
	 * @param url Provide the hyper link to open in the browser
	 */
	public void launchUrl(String url) {
//        String loginData = System.getProperty("loginUserName") + ":" + System.getProperty("loginPassword") + "@";
//		String urlWithLoginData = url.replaceAll(loginData, "");

	//	url ="https://www.saucedemo.com/";
		launchUrl(url,"");
		setImplicitWait(getTimeOut());

	}
	/**
	 * Function to launch url
	 *
	 * @param url Provide the hyper link to open in the browser
	 */
	public void launchUrl(String url, String urlWithLoginData) {
		try {
			if (!true)
				driver.manage().window().maximize();
			    setZoomLevel(50);
			if(!urlWithLoginData.isEmpty()) {
				driver.get(urlWithLoginData);
			}
			else {
				driver.get(url);
			}
			addTestLog("Launch URL", "URL launched sucessfully - " + url, Status.PASS);
		} catch (Exception e) {
			addTestLog("Launch URL", "Unable to launch - " + url + " . Error - " + e.getMessage(), Status.FAIL);
		}
	}

	/**
	 * Function to maximise window
	 * 
	 */
	public void setZoomLevel(double zoomPercentage) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.body.style.zoom='" + zoomPercentage + "%'");
			System.out.println("Zoom level set to: " + zoomPercentage + "%");
			addTestLog("Zoom Level", "Browser zoom set to: " + zoomPercentage + "%", Status.PASS);
		} catch (Exception e) {
			System.out.println("Failed to set zoom level: " + e.getMessage());
			addTestLog("Zoom Level", "Failed to set zoom to " + zoomPercentage + "%. Error: " + e.getMessage(), Status.FAIL);
		}
	}

	public void maximizeWindow() {
		try {
			if (!DriverManager.getTestParameters().getMobileToolName().equals(ToolName.APPIUM)) {
				driver.manage().window().maximize();
				addTestLog("Window Maximize", "Window maximized successfully", Status.PASS);
			}
		} catch (Exception e) {
			addTestLog("Window Maximize", "Unable to maximize the window. Error - " + e.getMessage(), Status.FAIL);
		}
	}

	/**
	 * Function to switch window by providing it's index
	 * 
	 * @param index Index number
	 */
	public void switchToWindowIndex(int index) {
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			List<String> windowStrings = new ArrayList<>(windowHandles);
			String reqWindow = windowStrings.get(index);
			driver.switchTo().window(reqWindow);
			addTestLog("Window Switch", "Window index - " + index + " switched successfully", Status.PASS);
		} catch (Exception e) {
			addTestLog("Window Switch", "Unable to switch Window index - " + index, Status.FAIL);
		}
	}
	//Hello release 1.0

	/**
	 * Function to set implicit wait
	 * 
	 * @param - timeInSeconds Provide time in seconds
	 * 
	 */
	public void setImplicitWait(long timeInSeconds) {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSeconds));
		} catch (Exception e) {
			addTestLog("Implicit Wait", "Unable to set implicit wait", Status.FAIL);
		}

	}

	/**
	 * Function to select the specified value from a listbox
	 * 
	 * @param by    The {@link WebDriver} locator used to identify the listbox
	 * @param value The value to be selected within the listbox
	 */
	public void selectByValue(By by, String value) {
		try {
			WebElement element = driver.findElement(by);
			waitUntilElementLocated(by, 3);
			Select select = new Select(element);
			select.selectByVisibleText(value);
			addTestLog("Dropdown Selection", "The value - " + value + " is selected successfully in - " + by,
					Status.PASS);
		} catch (Exception e) {
			addTestLog("Dropdown Selection",
					"Unable to select " + value + " in - " + by + " . Error - " + e.getMessage(), Status.FAIL);
		}
	}

	/**
	 * Function to find all json files in a folder
	 * 
	 * @param inputJson
	 * @throws Exception
	 */
	public void findJsonFilesInFolder(String inputJson, String testcaseName) {
		File folder = new File("." + File.separator + "src" + File.separator + "test" + File.separator + "resources"
				+ File.separator + inputJson);
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {
				if (file.getName().contains(".json"))
					runAutomationTests(inputJson + File.separator + file.getName(), testcaseName);
			} else {
				findJsonFilesInFolder(inputJson, testcaseName);
			}
		}
	}

	/**
	 * Function to execute the Generated script JSON file
	 * 
	 * @param fileName
	 * @throws Exception
	 */
	public void runAutomationTests(String fileName, String testcaseName) {
		ReadProperties propertiesReader = new ReadProperties();
		String baseURL = null;
		try {
			propertiesReader.readJSON(fileName);
			baseURL = propertiesReader.js.get("launch").toString();
			baseURL = baseURL.replaceAll("^.|.$", "");
			driver.manage().window().maximize();
			String xpath;
			for (String key : propertiesReader.js.keySet()) {
				if (key.equals(testcaseName)) {
					launchUrl(baseURL);
					JsonNode val = propertiesReader.js.get(key);
					if (val.isArray()) {
						for (final JsonNode objNode : val) {
							xpath = objNode.toString();
							xpath = xpath.replace("\\", "").replaceAll("^.|.$", "");
							xpath = xpath.replace("!!", "\n");
							String[] action = xpath.split("\\::");
							switch (action[1]) {
							case "click":
								if (driver.findElements(By.xpath(action[0])).size() > 0) {
									boolean isDisplayed = driver.findElement(By.xpath(action[0])).isDisplayed();
									boolean isEnabled = driver.findElement(By.xpath(action[0])).isEnabled();
									if (isDisplayed && isEnabled) {
										clickElement(By.xpath(action[0]));
									}
								} else {
									addTestLog("Test Generator : ",
											"Object : " + action[0] + " Object is not Enabled or Displayed",
											Status.FAIL);
								}
								break;
							default:
								addTestLog("Test Generator : ", "Invalid parameters", Status.FAIL);
								break;
							}
						}
					}
				}
			}
		} catch (Exception e1) {
			addTestLog("Test Generator : ", "Unable to read JSON. Error - " + e1.getMessage(), Status.FAIL);
		}
	}
	//For Partial visible text
	public void selectDropdownByMatchingText(By dropdownLocator, String matchType, String matchValue) {
		try {
			WebElement dropdownElement = driver.findElement(dropdownLocator);
			Select select = new Select(dropdownElement);

			String matchedOptionText = null;

			List<WebElement> options = select.getOptions();
			for (WebElement option : options) {
				String text = option.getText().trim();
				switch (matchType.toLowerCase()) {
					case "startswith":
						if (text.startsWith(matchValue)) {
							matchedOptionText = text;
						}
						break;

					case "endswith":
						if (text.endsWith(matchValue)) {
							matchedOptionText = text;
						}
						break;

					case "contains":
						if (text.contains(matchValue)) {
							matchedOptionText = text;
						}
						break;

					case "regex":
						if (Pattern.compile(matchValue).matcher(text).find()) {
							matchedOptionText = text;
						}
						break;

					default:
						throw new IllegalArgumentException("Invalid matchType. Use: startsWith, endsWith, contains, regex.");
				}

				if (matchedOptionText != null) {
					break;
				}
			}

			if (matchedOptionText != null) {
				select.selectByVisibleText(matchedOptionText);
				System.out.println("Selected option: " + matchedOptionText);
				addTestLog("Dropdown Selection", "Selected option using matchType: '" + matchType + "' => " + matchedOptionText, Status.PASS);
			} else {
				throw new RuntimeException("No matching option found for: " + matchValue);
			}

		} catch (Exception e) {
			System.out.println("Dropdown selection failed: " + e.getMessage());
			addTestLog("Dropdown Selection", "Failed to select from dropdown. Error: " + e.getMessage(), Status.FAIL);
		}
	}


	public void selectDropdownDS(By dropdownLocator) throws InterruptedException {

		WebElement dropdownElement = driver.findElement(dropdownLocator);
		Select select = new Select(dropdownElement);
		List<WebElement> option = select.getOptions();

		for (WebElement ele : option) {
			if(ele.getText().length()>2){
				Thread.sleep(5000);
				ele.click();
				break;
			}
		}
	}

	//Improved one
	//OLD One
	//Custom Dropdown for All other than -select class

	public void selectDropdownDDD(By dropdownLocator, String selectionType, String selectionValue) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			// Wait for dropdown trigger to be clickable
			wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
			WebElement dropdownElement = driver.findElement(dropdownLocator);
			String tagName = dropdownElement.getTagName();

			if ("select".equalsIgnoreCase(tagName)) {
				// Native <select> element
				Select select = new Select(dropdownElement);
				switch (selectionType.toLowerCase()) {
					case "value":
						select.selectByValue(selectionValue);
						break;
					case "text":
						select.selectByVisibleText(selectionValue);
						break;
					case "index":
						select.selectByIndex(Integer.parseInt(selectionValue));
						break;
					default:
						throw new IllegalArgumentException("Invalid selection type: " + selectionType + ". Use 'value', 'text', or 'index'.");
				}
				addTestLog("Dropdown Selection", "Selected from standard dropdown using [" + selectionType + "] = " + selectionValue, Status.PASS);
			} else {
				// Custom dropdown (non-<select>) - Enhanced for PrimeNG dropdowns

				// Click to open dropdown
				dropdownElement.click();
				Thread.sleep(300); // Reduced from 500ms to 300ms

				// Wait for dropdown items container to appear - shorter timeout
				By dropdownContainer = By.cssSelector(".p-dropdown-items-wrapper, .p-dropdown-items");
				WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Reduced from 15s
				shortWait.until(ExpectedConditions.presenceOfElementLocated(dropdownContainer));

				// Optimized XPath strategies - putting the working strategy first with shorter waits
				By[] optionLocators = {
						// Strategy 1: Partial match on aria-label (this worked for you!)
						By.xpath(String.format("//li[@class='p-dropdown-item' and contains(@aria-label,'%s')]", selectionValue.split(" \\(")[0])),

						// Strategy 2: Full aria-label match (backup)
						By.xpath(String.format("//li[@class='p-dropdown-item' and @aria-label='%s']", selectionValue)),

						// Strategy 3: Direct text content match
						By.xpath(String.format("//li[@class='p-dropdown-item' and normalize-space(text())='%s']", selectionValue)),

						// Strategy 4: Contains text (more flexible)
						By.xpath(String.format("//li[@class='p-dropdown-item' and contains(text(),'%s')]", selectionValue)),

						// Strategy 5: Contains text (more flexible)
						By.xpath(String.format("//li[@role='option' and @aria-label='%s']", selectionValue))
				};

				WebElement desiredOption = null;
				Exception lastException = null;

				// Try each locator strategy with reduced wait times for speed
				for (int i = 0; i < optionLocators.length; i++) {
					try {
						System.out.println("Trying locator strategy " + (i + 1) + ": " + optionLocators[i].toString());

						// Use shorter wait for each attempt - 3 seconds instead of 15
						WebDriverWait shortWait1 = new WebDriverWait(driver, Duration.ofSeconds(8));
						shortWait1.until(ExpectedConditions.visibilityOfElementLocated(optionLocators[i]));

						// Scroll into view and click
						List<WebElement> matchingOptions = driver.findElements(optionLocators[i]);
						if (matchingOptions.size() > 0) {
							WebElement freshOption = matchingOptions.get(0); // Always re-fetch the element fresh

							((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", freshOption);
							Thread.sleep(250); // Let the scroll finish

							try {
								freshOption.click(); // First try native click
							} catch (StaleElementReferenceException | ElementClickInterceptedException stale) {
								// In case it went stale again, try to refetch
								System.out.println("Retrying due to stale element...");
								WebElement retryOption = driver.findElements(optionLocators[i]).get(0);
								((JavascriptExecutor) driver).executeScript("arguments[0].click();", retryOption);
							}
							desiredOption = freshOption;  // <-- Add this line!

							System.out.println("Success with strategy " + (i + 1));
							break;
						}

					} catch (Exception e) {
						lastException = e;
						System.out.println("Strategy " + (i + 1) + " failed: " + e.getMessage());
						continue;
					}
				}
				if (desiredOption != null) {
					addTestLog("Custom Dropdown Selection", "Selected custom dropdown item [" + selectionValue + "]", Status.PASS);
				} else {
					throw lastException != null ? lastException : new Exception("Could not locate dropdown option with any strategy");

				}
			}
		} catch (Exception e) {
			String message = "Failed to select dropdown value [" + selectionValue + "] using [" + selectionType + "]. Error: " + e.getMessage();
			System.out.println(message);

			// Debug information
			try {
				System.out.println("Available dropdown options:");
				List<WebElement> allOptions = driver.findElements(By.cssSelector(".p-dropdown-item"));
				for (int i = 0; i < allOptions.size(); i++) {
					WebElement option = allOptions.get(i);
					System.out.println("Option " + i + ": Text='" + option.getText() + "', aria-label='" + option.getAttribute("aria-label") + "'");
				}
			} catch (Exception debugEx) {
				System.out.println("Could not retrieve debug information: " + debugEx.getMessage());
			}

			addTestLog("Dropdown Selection", message, Status.FAIL);
			logDropdownFailure(dropdownLocator, e);
		}
	}

	//DD Ends here

	public void selectCustomDropdown(By dropdown, By dropdownOptions, String visibleText) {
		try {
			// Click the dropdown to display options
			waitUntilElementVisible(dropdown, 8);
			driver.findElement(dropdown).click();

			// Wait for options to be visible
			waitUntilElementVisible(dropdownOptions, 5);

				// Get all options
			List<WebElement> options = driver.findElements(dropdownOptions);
			boolean found = false;
			for (WebElement option : options) {
				if (option.getText().trim().equalsIgnoreCase(visibleText.trim())) {
					// Scroll the element into view using JavaScript
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
					// Use JavaScript to click the element
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", option);
					//option.click();
					found = true;
					break;
				}
			}

			if (!found) {
				throw new Exception("Option '" + visibleText + "' not found in custom dropdown");
			}

			addTestLog("Select Dropdown", "Selected value: " + visibleText, Status.PASS);
		} catch (Exception e) {
			addTestLog("Select Dropdown", "Failed to select '" + visibleText + "'. Error: " + e.getMessage(), Status.FAIL);
		}
	}

	//The Dropdown DD Ends here

	public void selectDropdownByValue(By by, String value) {
		try {

			waitUntilElementVisible(by, 8);
			WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
			w.until(ExpectedConditions.elementToBeClickable(by));
			driver.findElement(by).click();
			selectValue(by, value);
			addTestLog("Click Element", by + " is clicked successfully", Status.PASS);
			//waitUntil(1);
		} catch (Exception e) {
			addTestLog("Click Element", "The element is NOT clicked - " + by + ". Error message - " + e.getMessage(),
					Status.FAIL);
		}
	}

	/**
	 * Function to Select the dropdown value by its value and index
	 *
	 * @param by    The {@link WebDriver} locator used to identify the element
	 * @param value of the element
	 */
	public void selectDropdownByValue(By by, String value, String index) {
		try {

			waitUntilElementVisible(by, 8);
			WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
			w.until(ExpectedConditions.elementToBeClickable(by));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", driver.findElement(by));
			driver.findElement(by).click();
			waitUntil(1);
			selectValue(by, value, index);
			addTestLog("Click Element", by + " is clicked successfully", Status.PASS);
			//waitUntil(1);
		} catch (Exception e) {
			addTestLog("Click Element", "The element is NOT clicked - " + by + ". Error message - " + e.getMessage(),
					Status.FAIL);
		}
	}
	/**
	 * Function to Select the value by value and index
	 *
	 * @param by    The {@link WebDriver} locator used to identify the element
	 * @param value of the element
	 */
	public void selectValue(By by, String value, String index) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement element = driver.findElement(by);
		Actions action = new Actions(driver);
		if (!DriverManager.getTestParameters().getMobileToolName().equals(ToolName.APPIUM))
			action.sendKeys(element, Keys.DOWN).build().perform();
		WebElement option = driver.findElement(By.xpath("(//*[text()='" + value + "'])[" + index + "]")); // Fehler ist
																											// hier
		w.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//*[text()='" + value + "'])[" + index + "]")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", option);
		option.click();
		//action.moveToElement(option).click().build().perform();
		addTestLog("Select Option", "selected Option [" + value + "] from [" + by + "}", Status.PASS);
	}



	/**
	 * Function to Select the value by value
	 *
	 * @param by    The {@link WebDriver} locator used to identify the element
	 * @param value of the element
	 */
	public void selectValue(By by, String value) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement element = driver.findElement(by);
		Actions action = new Actions(driver);
		if (!DriverManager.getTestParameters().getMobileToolName().equals(ToolName.APPIUM))
			action.sendKeys(element, Keys.DOWN).build().perform();
		WebElement option = driver.findElement(By.xpath("//*[text()='" + value + "']"));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[text()='" + value + "']")));
		action.moveToElement(option).click().build().perform();
		addTestLog("Select Option", "selected Option [" + option + "]", Status.PASS);
	}
	/**
	 * Function to sign in and handle window
	 *
	 */
	public void SignInAndhandleWindow() {
		TestHarness harness = new TestHarness();
		 String sheetName = "LoginCredentials";
		clickElement(generateLocator("XPATH","//*[text()='Anmelden']"));
		Set<String> windows = driver.getWindowHandles();
        Iterator<String> it = windows.iterator();
        String parentId = it.next();
        String childId = it.next();
        driver.switchTo().window(childId);
        //Login-Prozess

        clickElement(generateLocator("XPATH","//*[@id='label_expand_1']"));
        enterText(generateLocator("XPATH","(//input[contains(@id,'nickname')])[1]"),harness.getData(sheetName, "username"));
        enterText(generateLocator("XPATH","(//input[contains(@id,'sa_password')])[1]"),harness.getData(sheetName, "password"));
        clickElement(generateLocator("XPATH","//*[@id='loginButton']"));
        //Submit
        
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        
        clickElement(generateLocator("XPATH","//*[@id='zustimmenSubmitButton']")); 
        
        //wieder Parent Window
        driver.switchTo().window(parentId);
        //Auswahl der Bereiche
        clickElement(generateLocator("XPATH","(//*[contains(text(),'Antrag stellen')])[1]")); 
        clickElement(generateLocator("XPATH","(//*[contains(text(),'Wohnberechtigungsschein')])[1]")); 
        String standort= harness.getData(sheetName, "Standort");
        String standortMissingLastCharacter= standort.substring(0, standort.length() - 1);
        enterText(generateLocator("XPATH","(//input[contains(@id,'search-location-header-search_input')])[1]"),standortMissingLastCharacter);       
        String xpathFirstLocationResult= "(//*[@id='search-location-header-search']//*[@class='bp-default-auto-suggestion-item__result'])[1]";
        clickElement(generateLocator("XPATH",xpathFirstLocationResult)); 
       
        //Antrag stellen
        clickElement(generateLocator("XPATH","(//*[contains(text(),'Antrag stellen')])[4]")); 
//        try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
        
        
	}
	/**
	 * Function to shadow weiter
	 *
	 */
	public void shadowWeiter(String element) {
		
		JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

        Map<String, Object> shadowRoot = (Map<String, Object>) jsDriver.executeScript("return document.getElementsByTagName('lucom-wci-form')[0].shadowRoot");
//        WebElement shadowRoot = (WebElement) jsDriver.executeScript("return document.getElementsByTagName('lucom-wci-form')[0].shadowRoot");
//        WebElement shadowContent = shadowRoot.findElement(By.cssSelector("#shadow_content"));
        String id = (String) ((Map<String, Object>) shadowRoot).get("shadow-6066-11e4-a52e-4f735466cecf");
        RemoteWebElement shadowRootElement = new RemoteWebElement();
        shadowRootElement.setParent((RemoteWebDriver) driver);
        shadowRootElement.setId(id);

        System.out.println("shadowRoot: " + shadowRoot);
        System.out.println("shadowRootElement: " + shadowRootElement);

        WebElement button = shadowRootElement.findElement(By.id(element));

        System.out.println("button: " + button);
        button.click();       
       
	}
	/**
	 * Function to Shadow click field
	 *
	 */
public void shadowClickField(String element) {
		
		JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

        Map<String, Object> shadowRoot = (Map<String, Object>) jsDriver.executeScript("return document.getElementsByTagName('lucom-wci-form')[0].shadowRoot");
//        WebElement shadowRoot = (WebElement) jsDriver.executeScript("return document.getElementsByTagName('lucom-wci-form')[0].shadowRoot");
//        WebElement shadowContent = shadowRoot.findElement(By.cssSelector("#shadow_content"));
        String id = (String) ((Map<String, Object>) shadowRoot).get("shadow-6066-11e4-a52e-4f735466cecf");
        RemoteWebElement shadowRootElement = new RemoteWebElement();
        shadowRootElement.setParent((RemoteWebDriver) driver);
        shadowRootElement.setId(id);

     //   System.out.println("shadowRoot: " + shadowRoot);
     //   System.out.println("shadowRootElement: " + shadowRootElement);

        WebElement webElement = shadowRootElement.findElement(By.id(element));        
        webElement.click(); 
        webElement.sendKeys("7");
        webElement.sendKeys(Keys.TAB);
        
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}

	/**
	 * Function to get the title of the current page
	 *
	 * @return The title of the current page
	 */
	public String getPageTitle() {
		String title = null;
		try {
			title = driver.getTitle();
			addTestLog("Get Page Title", "Page title retrieved successfully: " + title, Status.PASS);
		} catch (Exception e) {
			addTestLog("Get Page Title", "Unable to retrieve page title. Error: " + e.getMessage(), Status.FAIL);
		}
		return title;
	}

	/**
	 * Function to verify the title of the current page using soft assertion
	 *
	 * @param expectedTitle The expected title of the page
	 */
	public void verifyPageTitle(String expectedTitle) {
		SoftAssert softAssert = new SoftAssert(); // Create SoftAssert instance
		String actualTitle = null;

		// Check if driver is initialized
		if (driver == null) {
			System.out.println("Error: WebDriver instance is null");
			softAssert.fail("WebDriver instance is null");
			addTestLog("Verify Page Title", "WebDriver instance is null", Status.FAIL);
			softAssert.assertAll(); // Report immediately to avoid further issues
			return;
		}

		try {
			System.out.println("Attempting to retrieve page title...");
			actualTitle = driver.getTitle(); // Retrieve page title
			System.out.println("The Page Title is: " + actualTitle);

			// Verify title is not null or empty
			if (actualTitle == null || actualTitle.isEmpty()) {
				softAssert.fail("Page title is null or empty!");
				addTestLog("Verify Page Title", "Page title is null or empty", Status.FAIL);
			} else {
				softAssert.assertEquals(actualTitle, expectedTitle, "Page title does not match!");
				addTestLog("Verify Page Title", "Page title verified successfully: " + actualTitle, Status.PASS);
			}
		} catch (Exception e) {
			System.out.println("Error retrieving page title: " + e.getMessage());
			softAssert.fail("Failed to retrieve page title due to exception: " + e.getMessage());
			addTestLog("Verify Page Title", "Unable to verify page title. Error: " + e.getMessage(), Status.FAIL);
		}

		System.out.println("Completed verifyPageTitle execution");
		softAssert.assertAll(); // Report all collected errors at the end
	}


	public boolean verifyConfirmationMessage(By by, String expectedMessage) {
		try {
			// Wait until the message element is visible
			waitUntilElementVisible(by, 8);

			// Get the actual message text
			String actualMessage = driver.findElement(by).getText().trim();

			// Compare actual and expected messages
			if (actualMessage.equals(expectedMessage)) {
				addTestLog("Verify Confirmation Message",
						"Message verified successfully: " + actualMessage, Status.PASS);
				return true;
			} else {
				addTestLog("Verify Confirmation Message",
						"Message does not match. Expected: " + expectedMessage + ", Actual: " + actualMessage,
						Status.FAIL);
				return false;
			}

		} catch (Exception e) {
			addTestLog("Verify Confirmation Message",
					"Failed to verify message at " + by + ". Error: " + e.getMessage(), Status.FAIL);
			return false;
		}
	}

    //customize method for USRN no.
	public String getVal(By by){
		// Get the actual message text
		String actualMessage = driver.findElement(by).getText().trim();
		actualMessage= actualMessage.replaceAll("(i?)Your changes to","");
		actualMessage = actualMessage.replaceAll("(i?) have been Saved.","").trim();
		return actualMessage;
	}


	// Checking without expected result
	public boolean verifyAndLogConfirmationMessage(By by) {
		try {
			// Wait until the confirmation message is visible
			waitUntilElementVisible(by, 8);

			// Get the actual message text
			String actualMessage = driver.findElement(by).getText().trim();
			// Print to console
			System.out.println("Confirmation Message: " + actualMessage);

			// Log to test report
			addTestLog("Confirmation Message", "Message displayed: " + actualMessage, Status.PASS);

			return true;

		} catch (Exception e) {
			System.out.println("Confirmation message not found or error occurred: " + e.getMessage());

			addTestLog("Confirmation Message", "Failed to retrieve message at " + by + ". Error: " + e.getMessage(), Status.FAIL);

			return false;
		}
	}


	// New method
	public void verifyPageHeaderTitle(By locator, String expectedTitle, String pageName) {
		try {
			// Get actual title from the element
			String actualTitle = driver.findElement(locator).getText().trim();
			String expected = expectedTitle.trim();

			// Always print actual and expected title
			System.out.println("Verifying Title for: " + pageName);
			System.out.println("Expected Title: " + expectedTitle);
			System.out.println("Actual Title: " + actualTitle);

			// Soft assertion
			SoftAssert softAssert = new SoftAssert();
			softAssert.assertEquals(actualTitle, expectedTitle, pageName + " title does not match!");

			// Log the result
			if (actualTitle.equals(expectedTitle)) {
				addTestLog("Verify " + pageName + " Title",
						"Page title matched: " + actualTitle, Status.PASS);
			} else {
				System.out.println("Title mismatch for " + pageName + ": expected '" + expected + "', but got '" + actualTitle + "'");
				addTestLog("Verify " + pageName + " Title",
						"Expected: " + expectedTitle + ", but found: " + actualTitle, Status.FAIL);
			}

			//softAssert.assertAll(); // Ensure failures are reported

		} catch (Exception e) {
			// Print all values on failure
			System.out.println("Exception during page title verification for " + pageName);
			System.out.println("Expected Title: " + expectedTitle);
			System.out.println("Locator Used: " + locator.toString());
			e.printStackTrace(); // For full stack trace in console

			addTestLog("Verify " + pageName + " Title",
					"Error occurred while verifying title. Error: " + e.getMessage(), Status.FAIL);
		}
	}
	public void JSClick(By locator) {
		int retries = 2;
		boolean clicked = false;

		for (int attempt = 1; attempt <= retries; attempt++) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				wait.until(ExpectedConditions.elementToBeClickable(locator));

				WebElement element = driver.findElement(locator);

				// Try JavaScript click
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
				System.out.println("JavaScript click succeeded on attempt " + attempt + " for: " + locator);
				addTestLog("JavaScript Click", "Clicked using JavaScript on attempt " + attempt + ": " + locator, Status.PASS);
				clicked = true;
				break;

			} catch (Exception jsException) {
				System.out.println("JavaScript click failed on attempt " + attempt + " for: " + locator);

				// Retry with normal Selenium click if JavaScript click fails
				try {
					WebElement fallbackElement = driver.findElement(locator);
					fallbackElement.click();
					System.out.println("Fallback Selenium click succeeded on attempt " + attempt + " for: " + locator);
					addTestLog("Fallback Click", "Fallback click succeeded for: " + locator, Status.PASS);
					clicked = true;
					break;
				} catch (Exception fallbackException) {
					System.out.println("Fallback click also failed on attempt " + attempt + " for: " + locator);
				}
			}
		}

		if (!clicked) {
			addTestLog("Click Failure", "Failed to click on element after retries: " + locator, Status.FAIL);
		}
	}

	public void scrollIntoViewAndClick(By locator) {
		try {
			WebElement element = driver.findElement(locator);

			// Scroll into view and click using JavaScript
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].scrollIntoView(true); arguments[0].click();", element);

			// Logging
			System.out.println("Successfully scrolled to and clicked on element: " + locator.toString());
			addTestLog("Scroll & Click", "Successfully clicked on element: " + locator.toString(), Status.PASS);

		} catch (Exception e) {
			System.out.println("Failed to scroll and click on element: " + locator.toString());
			addTestLog("Scroll & Click", "Failed to click on element: " + locator.toString() + ". Error: " + e.getMessage(), Status.FAIL);
		}
	}
	public void scrollIntoView(By locator) {
		try {
			WebElement element = driver.findElement(locator);

			// Scroll into view using JavaScript
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

			// Logging
			System.out.println("Successfully scrolled to element: " + locator.toString());
			addTestLog("Scroll", "Successfully scrolled to element: " + locator.toString(), Status.PASS);

		} catch (Exception e) {
			System.out.println("Failed to scroll to element: " + locator.toString());
			addTestLog("Scroll", "Failed to scroll to element: " + locator.toString() + ". Error: " + e.getMessage(), Status.FAIL);
		}
	}

	public void iterateDropdownOptions(By dropdownLocator, String dropdownName) {
		try {
			List<WebElement> options = driver.findElements(dropdownLocator);

			int totalOptions = options.size();
			System.out.println("Total options in '" + dropdownName + "': " + totalOptions);
			addTestLog("Dropdown Options",
					"Dropdown '" + dropdownName + "' has " + totalOptions + " options.", Status.PASS);

			for (int i = 0; i < totalOptions; i++) {
				String optionText = options.get(i).getText().trim();
				System.out.println("Option " + (i + 1) + ": " + optionText);
				addTestLog("Dropdown Option", "Option " + (i + 1) + ": " + optionText, Status.PASS);
			}

		} catch (Exception e) {
			System.out.println("Error while iterating dropdown '" + dropdownName + "': " + e.getMessage());
			addTestLog("Dropdown Options",
					"Failed to iterate dropdown '" + dropdownName + "'. Error: " + e.getMessage(), Status.FAIL);
		}
	}

	// To Enter Date and Time
	public void enterDateTime(By by, String valueToEnter) {
		try {
			WebElement inputField = driver.findElement(by);
			waitUntilElementVisible(by, 5);

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", inputField);

			String finalValue = valueToEnter;
			if ("Today".equalsIgnoreCase(valueToEnter)) {
				finalValue = getFormattedDateTime(0);
			} else if ("Tomorrow".equalsIgnoreCase(valueToEnter)) {
				finalValue = getFormattedDateTime(1);
			}

			// Try normal clear + sendKeys
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
				wait.until(ExpectedConditions.elementToBeClickable(by));

				inputField.clear();
				inputField.sendKeys(finalValue);
				inputField.sendKeys(Keys.TAB); // To trigger blur event if needed
			} catch (Exception sendKeysFail) {
				// Fallback to JS if normal sendKeys doesn't work (e.g., Angular readonly or masked input)
				((JavascriptExecutor) driver).executeScript(
						"arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input', { bubbles: true })); arguments[0].dispatchEvent(new Event('change'));",
						inputField, finalValue
				);
			}

			addTestLog("Enter DateTime", "[" + finalValue + "] entered successfully in " + by, Status.PASS);

		} catch (Exception e) {
			addTestLog("Enter DateTime", "Failed to enter value in " + by + ". Error - " + e.getMessage(), Status.FAIL);
		}
	}


	private String getFormattedDateTime(int daysToAdd) {
		LocalDateTime dateTime = LocalDateTime.now().plusDays(daysToAdd);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");
		return dateTime.format(formatter);
	}

	public void enterDate(By by, String valueToEnter) {
		try {
			WebElement e = driver.findElement(by);
			waitUntilElementVisible(by, 3);

			WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
			w.until(ExpectedConditions.elementToBeClickable(by));

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", e);

			String finalValue = valueToEnter;

			if ("Today".equalsIgnoreCase(valueToEnter)) {
				finalValue = getFormattedDate(0); // only date
			} else if ("Tomorrow".equalsIgnoreCase(valueToEnter)) {
				finalValue = getFormattedDate(1); // only date
			}

			e.clear();
			e.sendKeys(finalValue);

			addTestLog("Enter Date", "[" + finalValue + "] is entered successfully in " + by, Status.PASS);

		} catch (Exception ex) {
			addTestLog("Enter Date", "The element is NOT entered - " + by + ". Error message - " + ex.getMessage(), Status.FAIL);
		}
	}

	private String getFormattedDate(int daysToAdd) {
		LocalDateTime dateTime = LocalDateTime.now().plusDays(daysToAdd);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
		return dateTime.format(formatter);
	}

	//Entering input dynamic input text box
	public void enterDynamicNumericInput(By readonlyDivBy, By inputBy, String valueToEnter) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			// Step 1: Wait for readonly div to be visible and clickable
			waitUntilElementVisible(readonlyDivBy, 3);
			WebElement divElement = driver.findElement(readonlyDivBy);
			wait.until(ExpectedConditions.elementToBeClickable(readonlyDivBy));

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", divElement);

			try {
				divElement.click();
			} catch (Exception clickEx) {
				// Fallback: JS click if normal click fails
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", divElement);
			}

			// Optional: Wait for any animation or input render
			Thread.sleep(500);

			// Step 2: Wait for editable input field
			waitUntilElementVisible(inputBy, 3);
			WebElement input = driver.findElement(inputBy);
			wait.until(ExpectedConditions.elementToBeClickable(inputBy));

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", input);

			// Step 3: Robust clearing and entering value
			input.clear();
			input.sendKeys(Keys.chord(Keys.CONTROL, "a"));
			input.sendKeys(Keys.BACK_SPACE);
			input.sendKeys(valueToEnter);

			addTestLog("Enter Numeric Value", "[" + valueToEnter + "] entered successfully in " + inputBy, Status.PASS);

		} catch (Exception e) {
			addTestLog("Enter Numeric Value", "Failed to enter value in " + inputBy + ". Error - " + e.getMessage(), Status.FAIL);
		}
	}

	//Get text from the select class method

	// ========== Get Selected Option Text ==========
	public String getSelectedOptionText(By dropdownBy) {
		String selectedText = "";
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated(dropdownBy));
			wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownBy));
			wait.until(ExpectedConditions.elementToBeClickable(dropdownBy));

			WebElement dropdownElement = driver.findElement(dropdownBy);
			String tagName = dropdownElement.getTagName();

			if ("select".equalsIgnoreCase(tagName)) {
				// Native <select>
				try {
					Select select = new Select(dropdownElement);
					selectedText = select.getFirstSelectedOption().getText().trim();
					addTestLog("Get Selected Option", "Value from native select: [" + selectedText + "]", Status.PASS);
				} catch (StaleElementReferenceException staleEx) {
					WebElement refreshed = driver.findElement(dropdownBy);
					Select retrySelect = new Select(refreshed);
					selectedText = retrySelect.getFirstSelectedOption().getText().trim();
					addTestLog("Get Selected Option (Retry)", "After retry: [" + selectedText + "]", Status.PASS);
				}
			} else {
				// Custom dropdowns
				selectedText = dropdownElement.getText().trim();
				if (selectedText.isEmpty()) {
					selectedText = dropdownElement.getAttribute("value") != null
							? dropdownElement.getAttribute("value").trim() : "";
				}

				addTestLog("Get Custom Dropdown Option", "From custom dropdown: [" + selectedText + "]", Status.PASS);
			}

		} catch (Exception e) {
			logDropdownFailure(dropdownBy, e);
		}

		return selectedText;
	}

	private void logDropdownFailure(By by, Exception e) {
		System.out.println("Failed to retrieve selected option text from: " + by);
		e.printStackTrace();
		addTestLog("Get Selected Option Text", "Unable to retrieve selected text for " + by + ". Error: " + e.getMessage(), Status.FAIL);
	}


	//Get text from the element new method.
	public String getTextofElement(By by) {
		String text = "";
		try {
			// Try fast access with reduced timeout
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			wait.until(ExpectedConditions.presenceOfElementLocated(by));

			WebElement element = driver.findElement(by);

			// Try to get visible text
			text = element.getText().trim();

			// Fallback: if text is empty, try using "value" attribute
			if (text.isEmpty()) {
				text = element.getAttribute("value") != null ? element.getAttribute("value").trim() : "";
			}

			addTestLog("Get Text", "Text retrieved successfully for " + by + ": [" + text + "]", Status.PASS);
		} catch (StaleElementReferenceException stale) {
			// Retry once for stale elements
			try {
				WebElement retryElement = driver.findElement(by);
				text = retryElement.getText().trim();
				if (text.isEmpty()) {
					text = retryElement.getAttribute("value") != null ? retryElement.getAttribute("value").trim() : "";
				}
				addTestLog("Get Text (Retry)", "Text retrieved after retry for " + by + ": [" + text + "]", Status.PASS);
			} catch (Exception retryEx) {
				logTextFailure(by, retryEx);
			}
		} catch (Exception e) {
			logTextFailure(by, e);
		}
		return text;
	}

	private void logTextFailure(By by, Exception e) {
		System.out.println("Failed to retrieve text from: " + by);
		e.printStackTrace();
		addTestLog("Get Text", "Unable to retrieve text for " + by + ". Error: " + e.getMessage(), Status.FAIL);
	}

	/**
	 * Switches to an iframe using a flexible method (By locator, index, or name).
	 * @param identifier Can be a By object, index (Integer), or name/id (String)
	 */
	public void switchToIFrame(Object identifier) {
		try {
			driver.switchTo().defaultContent(); // Always reset to root frame before switching

			if (identifier instanceof By) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt((By) identifier));
				addTestLog("Switch to iFrame", "Switched to iframe using locator: " + identifier, Status.PASS);

			} else if (identifier instanceof Integer) {
				driver.switchTo().frame((int) identifier);
				addTestLog("Switch to iFrame", "Switched to iframe using index: " + identifier, Status.PASS);

			} else if (identifier instanceof String) {
				driver.switchTo().frame((String) identifier);
				addTestLog("Switch to iFrame", "Switched to iframe using name/id: " + identifier, Status.PASS);

			} else {
				throw new IllegalArgumentException("Invalid identifier type for iframe switch: " + identifier);
			}

		} catch (Exception e) {
			addTestLog("Switch to iFrame", "Failed to switch to iframe using: " + identifier + ". Error: " + e.getMessage(), Status.FAIL);
		}
	}

	public void switchToDefaultContent() {
		try {
			driver.switchTo().defaultContent();
			addTestLog("Switch to Default Content", "Switched to the main (default) content", Status.PASS);
		} catch (Exception e) {
			addTestLog("Switch to Default Content", "Failed to switch. Error: " + e.getMessage(), Status.FAIL);
		}
	}

	public void clickOnTableElementByValue(String valueToMatch, By Locator) {
		try {
			List<WebElement> elements = driver.findElements(Locator);
			System.out.println("Total elements found: " + elements.size());

			for (WebElement element : elements) {
				String text = element.getText().trim();
				System.out.println("Element text: " + text);

				if (text.contains(valueToMatch)) {
					System.out.println("Clicking on element matching: " + valueToMatch);
					element.click();
					return;
				}
			}

			System.out.println("No element found with value: " + valueToMatch);
		} catch (Exception e) {
			System.out.println("Exception in clickOnElementByValue(): " + e.getMessage());
			e.printStackTrace();
		}
	}

	//New Method to Print values in Storage Area Table
	public void printTableValuesSideBySide(By consignmentLocator, By metricTonneLocator) {
		try {
			List<WebElement> consignmentNames = driver.findElements(consignmentLocator);
			List<WebElement> metricTonnes = driver.findElements(metricTonneLocator);

			System.out.println("Total consignment rows found: " + consignmentNames.size());

			if (consignmentNames.size() != metricTonnes.size()) {
				String warning = "⚠️ Warning: Mismatched number of Consignment Names and M/T values. " +
						"Consignments: " + consignmentNames.size() + ", M/T: " + metricTonnes.size();
				System.out.println(warning);
				//addTestLog(warning);
			}

			for (int i = 0; i < consignmentNames.size(); i++) {
				String consignmentName = consignmentNames.get(i).getText().trim();
				String metricTonneValue = i < metricTonnes.size() ? metricTonnes.get(i).getText().trim() : "N/A";

				String logMessage = "Consignment: " + consignmentName + ", M/T Value: " + metricTonneValue;
				System.out.println(logMessage);
				//addTestLog(logMessage);
			}

			if (consignmentNames.isEmpty()) {
				String msg = "⚠️ No consignment data found on the page.";
				System.out.println(msg);
				//addTestLog(msg);
			}

		} catch (Exception e) {
			String error = "❌ Exception while reading table data: " + e.getMessage();
			System.out.println(error);
			//addTestLog(error);
			e.printStackTrace();
		}
	}

	public void selectDropdownDD(By dropdownLocator, String selectionType, String selectionValue) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

			// Wait for dropdown to be clickable
			wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator));
			WebElement dropdownElement = driver.findElement(dropdownLocator);
			String tagName = dropdownElement.getTagName();

			if ("select".equalsIgnoreCase(tagName)) {
				// Standard <select>
				Select select = new Select(dropdownElement);
				switch (selectionType.toLowerCase()) {
					case "value":
						select.selectByValue(selectionValue);
						break;
					case "text":
						select.selectByVisibleText(selectionValue);
						break;
					case "index":
						select.selectByIndex(Integer.parseInt(selectionValue));
						break;
					default:
						throw new IllegalArgumentException("Invalid selection type: " + selectionType);
				}
				addTestLog("Dropdown Selection", "Selected from standard dropdown using [" + selectionType + "] = " + selectionValue, Status.PASS);
			} else {
				// Custom dropdown (PrimeNG, etc.)
				dropdownElement.click();
				Thread.sleep(300); // Allow dropdown to open

				By dropdownContainer = By.cssSelector(".p-dropdown-items-wrapper, .p-dropdown-items");
				new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(dropdownContainer));

				By[] optionLocators = {
						// Strategy 1: aria-label exact match (promoted from strategy 5)
						By.xpath(String.format("//li[@role='option' and @aria-label='%s']", selectionValue)),

						// Strategy 2: contains aria-label
						By.xpath(String.format("//li[@class='p-dropdown-item' and contains(@aria-label,'%s')]", selectionValue.split(" \\(")[0])),

						// Strategy 3: full aria-label match
						By.xpath(String.format("//li[@class='p-dropdown-item' and @aria-label='%s']", selectionValue)),

						// Strategy 4: normalize-space text match
						By.xpath(String.format("//li[@class='p-dropdown-item' and normalize-space(text())='%s']", selectionValue)),

						// Strategy 5: contains text
						By.xpath(String.format("//li[@class='p-dropdown-item' and contains(text(),'%s')]", selectionValue))
				};

				WebElement desiredOption = null;
				Exception lastException = null;

				for (int i = 0; i < optionLocators.length; i++) {
					try {
						System.out.println("Trying locator strategy " + (i + 1) + ": " + optionLocators[i]);

						List<WebElement> matchingOptions = driver.findElements(optionLocators[i]);

						for (WebElement option : matchingOptions) {
							if (option.isDisplayed() && option.isEnabled()) {
								((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", option);
								Thread.sleep(250);

								try {
									option.click();
								} catch (StaleElementReferenceException | ElementClickInterceptedException stale) {
									System.out.println("Retrying due to stale element...");
									WebElement retryOption = driver.findElements(optionLocators[i]).get(0);
									((JavascriptExecutor) driver).executeScript("arguments[0].click();", retryOption);
								}

								System.out.println("Success with strategy " + (i + 1));
								addTestLog("Custom Dropdown Selection", "Selected custom dropdown item using strategy " + (i + 1) + ": [" + selectionValue + "]", Status.PASS);
								return; // Exit once selected
							}
						}
					} catch (Exception e) {
						lastException = e;
						System.out.println("Strategy " + (i + 1) + " failed: " + e.getMessage());
					}
				}

				// If none worked, throw last exception
				throw lastException != null ? lastException : new Exception("Could not locate dropdown option with any strategy.");
			}

		} catch (Exception e) {
			String message = "Failed to select dropdown value [" + selectionValue + "] using [" + selectionType + "]. Error: " + e.getMessage();
			System.out.println(message);

			// Print available dropdown options for debug
			try {
				System.out.println("Available dropdown options:");
				List<WebElement> allOptions = driver.findElements(By.cssSelector(".p-dropdown-item"));
				for (int i = 0; i < allOptions.size(); i++) {
					WebElement option = allOptions.get(i);
					System.out.println("Option " + i + ": Text='" + option.getText() + "', aria-label='" + option.getAttribute("aria-label") + "'");
				}
			} catch (Exception debugEx) {
				System.out.println("Could not retrieve debug information: " + debugEx.getMessage());
			}

			addTestLog("Dropdown Selection", message, Status.FAIL);
			logDropdownFailure(dropdownLocator, e);
		}
	}

	//1300





































}