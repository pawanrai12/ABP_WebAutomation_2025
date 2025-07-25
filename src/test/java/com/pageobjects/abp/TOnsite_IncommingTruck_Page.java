package com.pageobjects.abp;

import com.framework.context.ScenarioContext;
import com.framework.reusable.WebReusableComponents;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

public class TOnsite_IncommingTruck_Page extends WebReusableComponents
{
    String Tareweight;
    String Grossweight;

    protected By Trucks = By.xpath("//span[normalize-space(text())='Trucks']");
    protected By safezoneimgclick = By.xpath("//a[@class='cursor-pointer']//img[@alt='CommTrac brand logo']");

    //Step 17.1
    protected By onsiteMenu = By.xpath("//a[@class='menu-label text-left' and text()='On Site']");
    protected void UserNavigatesToTrucksonSite() throws InterruptedException {
        setZoomLevel(50);
        JSClick(Trucks);
        JSClick(onsiteMenu);
        Thread.sleep(3000);
        JSClick(Trucks);
        clickElement(safezoneimgclick);

    }

    //Step 17.2
    protected By addIncomingTruckBtn = By.xpath("//a[@id='Truck_Visit.Grid.addIncoming']");
    protected void UserselectsTheAddIncomingTruck() throws InterruptedException {
        clickOnElementaction(addIncomingTruckBtn);
        Thread.sleep(5000);
    }

    //Step 17.3
    protected By incomingtruckpageTitle = By.xpath("//a[./span[text()='Add Incoming Truck']]");
    protected void verifyIncomingTruckPage()
    {
        verifyPageHeaderTitle(incomingtruckpageTitle,"Add Incoming Truck","***Add Incoming Truck Page Title is**:");
    }

    //Step 18.1
    protected By primaryintakeInput = By.xpath("//input[@id='form-component-primaryIntake']");
    protected By primaryintakeIndex = By.xpath("(//tr[@class='detailed-filter-select-option ng-scope'])[1]");
    protected By trucknoInput = By.xpath("//input[@id='form-component-truck']");
    protected By trucknoIndex = By.xpath("(//button[@class='filter-select-option ng-binding'])[1]");
    protected By driverInput = By.xpath("//input[@id='form-component-driver']");
    protected By trailerInput = By.xpath("//input[@id='form-component-trailer']");

    //check Tarediv div xpath for now changed from 1 to 2
    protected By TareInput = By.xpath("//input[@id='form-component-tareWeight']");
    protected By Tarediv = By.xpath("(//div[contains(@class,'numeric-input-readable') and contains(@class,'input-text')])[2]");

    //check Ggross div xpath for now changed from 1 to 2
    protected By grossDiv = By.xpath("(//div[contains(@class, 'numeric-input-readable') and contains(@class, 'input-text')])[2]");
    protected By grossInp = By.xpath("//input[@id='form-component-grossWeight']");

    protected By safeclick = By.xpath("(//span[@class='icon-info form-component-help-icon'])[9]");
    protected void infoclicksSafeclicks()
    {
        clickElement(safeclick);
    }
    protected By savenextBtn = By.xpath("//span[normalize-space(text())='Save And Next']");
    protected void UserclicksSaveNext()
    {
        JSClick(savenextBtn);
    }
    protected By saveR = By.xpath("//span[normalize-space(text())='Save']");
    protected void UserclicksSaveR()
    {
        JSClick(saveR);
    }

    protected void TruckBookout7A() throws InterruptedException {

        //***From the Extracted text -Stored Globally.** from the Truck_intake page --"weight value"
        String productweight = (String) ScenarioContext.get("ProductWeight");
        System.out.println("Retrieved Weight value from the Truck intake.pageobjects: " +productweight);

        // Convert ProdWeightval string to integer
        int totalWeight = 0;
        try {
            totalWeight = Integer.parseInt(productweight.trim());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing ProdWeightval: " + productweight);
            return;
        }

        int netChunk = 30;
        int tareWeight = 14;

        int fullLoops = totalWeight / netChunk;
        int remainder = totalWeight % netChunk;

        System.out.println("Total Weight: " + totalWeight);
        System.out.println("Full Loops: " + fullLoops);
        System.out.println("Remainder: " + remainder);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        // Full truck loop
        for (int i = 0; i < fullLoops; i++) {
            System.out.println("=== Full Truck Entry " + (i + 1) + " ===");

            UserNavigatesToTrucksonSite();
            UserselectsTheAddIncomingTruck();
            UserEntersTruckDetails();

            Grossweight = String.format("%.3f", (float)(netChunk + tareWeight));
            enterDynamicNumericInput(grossDiv, grossInp, Grossweight);

            UserclicksSaveR();
            Bookout();
            UserclicksSaveR();

            // Wait until report is visible or downloaded (replace with actual condition)
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='grid content-stretch no-underline']//span[@class='p-menuitem-text' and text()='Report Selection']")));

            // Log after report is generated
            System.out.println("Truck report is generated for Truck #" + (i + 1));

            // Log after full truck completion
            System.out.println("Truck #" + (i + 1) + " is done");
        }

        // Handle remainder truck
        if (remainder > 0) {
            System.out.println("=== Remainder Truck Entry ===");

            UserNavigatesToTrucksonSite();
            UserselectsTheAddIncomingTruck();
            UserEntersTruckDetails();

            float finalGross = remainder + tareWeight;
            Grossweight = String.format("%.3f", finalGross);
            enterDynamicNumericInput(grossDiv, grossInp, Grossweight);

            UserclicksSaveR();
            Bookout();
            UserclicksSaveR();
            Thread.sleep(9000);

            // Wait for remainder report
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='grid content-stretch no-underline']//span[@class='p-menuitem-text' and text()='Report Selection']")));

            System.out.println("Truck report is generated for Remainder Truck");

            System.out.println("Remainder Truck is done");
        }
    }

    protected void TruckBookout7B() throws InterruptedException {
        //***From the Extracted text -Stored Globally.** from the Truck_intake page --"weight value"
        String productweight = (String) ScenarioContext.get("ProductWeight");
        System.out.println("Retrieved Weight value from the Truck intake.pageobjects: " +productweight);

        // Convert ProdWeightval string to integer
        int totalWeight = 0;
        try {
            totalWeight = Integer.parseInt(productweight.trim());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing ProdWeightval: " + productweight);
            return;
        }

        int netChunk = 30;
        int tareWeight = 14;

        int fullLoops = totalWeight / netChunk;
        int remainder = totalWeight % netChunk;

        System.out.println("Total Weight: " + totalWeight);
        System.out.println("Full Loops: " + fullLoops);
        System.out.println("Remainder: " + remainder);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        // Full truck loop
        for (int i = 0; i < fullLoops; i++) {
            System.out.println("=== Full Truck Entry " + (i + 1) + " ===");

            UserNavigatesToTrucksonSite();
            UserselectsTheAddIncomingTruck();
            UserEntersTruckDetails_ConginmentGroup();

            Grossweight = String.format("%.3f", (float)(netChunk + tareWeight));
            enterDynamicNumericInput(grossDiv, grossInp, Grossweight);

            UserclicksSaveR();
            Bookout();
            UserclicksSaveR();

            // Wait until report is visible or downloaded (replace with actual condition)
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='grid content-stretch no-underline']//span[@class='p-menuitem-text' and text()='Report Selection']")));

            // Log after report is generated
            System.out.println("Truck report is generated for Truck #" + (i + 1));

            // Log after full truck completion
            System.out.println("Truck #" + (i + 1) + " is done");
        }

        // Handle remainder truck
        if (remainder > 0) {
            System.out.println("=== Remainder Truck Entry ===");

            UserNavigatesToTrucksonSite();
            UserselectsTheAddIncomingTruck();
            UserEntersTruckDetails_ConginmentGroup();

            float finalGross = remainder + tareWeight;
            Grossweight = String.format("%.3f", finalGross);
            enterDynamicNumericInput(grossDiv, grossInp, Grossweight);

            UserclicksSaveR();
            Bookout();
            UserclicksSaveR();

            // Wait for remainder report
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='grid content-stretch no-underline']//span[@class='p-menuitem-text' and text()='Report Selection']")));
            Thread.sleep(9000);
            System.out.println("Truck report is generated for Remainder Truck");

            System.out.println("Remainder Truck is done");
        }
    }

    // For the Consignment
    protected void TruckBookout7C() throws InterruptedException {
        //***From the Extracted text -Stored Globally.** from the Truck_intake page --"weight value"
        String productweight = (String) ScenarioContext.get("ProductWeight");
        System.out.println("Retrieved Weight value from the Truck intake.pageobjects: " +productweight);

        // Convert ProdWeightval string to integer
        int totalWeight = 0;
        try {
            totalWeight = Integer.parseInt(productweight.trim());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing ProdWeightval: " + productweight);
            return;
        }

        int netChunk = 30;
        int tareWeight = 14;

        int fullLoops = totalWeight / netChunk;
        int remainder = totalWeight % netChunk;

        System.out.println("Total Weight: " + totalWeight);
        System.out.println("Full Loops: " + fullLoops);
        System.out.println("Remainder: " + remainder);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        // Full truck loop
        for (int i = 0; i < fullLoops; i++) {
            System.out.println("=== Full Truck Entry " + (i + 1) + " ===");

            UserNavigatesToTrucksonSite();
            UserselectsTheAddIncomingTruck();
            UserEntersTruckDetails_Consignment();

            Grossweight = String.format("%.3f", (float)(netChunk + tareWeight));
            enterDynamicNumericInput(grossDiv, grossInp, Grossweight);

            UserclicksSaveR();
            Bookout();
            UserclicksSaveR();

            // Wait until report is visible or downloaded (replace with actual condition)
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='grid content-stretch no-underline']//span[@class='p-menuitem-text' and text()='Report Selection']")));

            // Log after report is generated
            System.out.println("Truck report is generated for Truck #" + (i + 1));

            // Log after full truck completion
            System.out.println("Truck #" + (i + 1) + " is done");
        }

        // Handle remainder truck
        if (remainder > 0) {
            System.out.println("=== Remainder Truck Entry ===");

            UserNavigatesToTrucksonSite();
            UserselectsTheAddIncomingTruck();
            UserEntersTruckDetails_Consignment();

            float finalGross = remainder + tareWeight;
            Grossweight = String.format("%.3f", finalGross);
            enterDynamicNumericInput(grossDiv, grossInp, Grossweight);

            UserclicksSaveR();
            Bookout();
            UserclicksSaveR();

            // Wait for remainder report
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='grid content-stretch no-underline']//span[@class='p-menuitem-text' and text()='Report Selection']")));
            Thread.sleep(9000);
            System.out.println("Truck report is generated for Remainder Truck");

            System.out.println("Remainder Truck is done");
        }
    }

    // For the 7D Test cases
    protected void TruckBookout7D() throws InterruptedException {
        //***From the Extracted text -Stored Globally.** from the Truck_intake page --"weight value"
        String productweight = (String) ScenarioContext.get("ProductWeight");
        System.out.println("Retrieved Weight value from the Truck intake.pageobjects: " +productweight);

        // Convert ProdWeightval string to integer
        int totalWeight = 0;
        try {
            totalWeight = Integer.parseInt(productweight.trim());
        } catch (NumberFormatException e) {
            System.err.println("Error parsing ProdWeightval: " + productweight);
            return;
        }

        int netChunk = 30;
        int tareWeight = 14;

        int fullLoops = totalWeight / netChunk;
        int remainder = totalWeight % netChunk;

        System.out.println("Total Weight: " + totalWeight);
        System.out.println("Full Loops: " + fullLoops);
        System.out.println("Remainder: " + remainder);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        // Full truck loop
        for (int i = 0; i < fullLoops; i++) {
            System.out.println("=== Full Truck Entry " + (i + 1) + " ===");

            UserNavigatesToTrucksonSite();
            UserselectsTheAddIncomingTruck();
            UserEntersTruckDetails_Consignment();

            Grossweight = String.format("%.3f", (float)(netChunk + tareWeight));
            enterDynamicNumericInput(grossDiv, grossInp, Grossweight);

            UserclicksSaveR();
            Bookout();
            UserclicksSaveR();

            // Wait until report is visible or downloaded (replace with actual condition)
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='grid content-stretch no-underline']//span[@class='p-menuitem-text' and text()='Report Selection']")));

            // Log after report is generated
            System.out.println("Truck report is generated for Truck #" + (i + 1));

            // Log after full truck completion
            System.out.println("Truck #" + (i + 1) + " is done");
        }

        // Handle remainder truck
        if (remainder > 0) {
            System.out.println("=== Remainder Truck Entry ===");

            UserNavigatesToTrucksonSite();
            UserselectsTheAddIncomingTruck();
            UserEntersTruckDetails_Consignment();

            float finalGross = remainder + tareWeight;
            Grossweight = String.format("%.3f", finalGross);
            enterDynamicNumericInput(grossDiv, grossInp, Grossweight);

            UserclicksSaveR();
            Bookout();
            UserclicksSaveR();

            // Wait for remainder report
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='grid content-stretch no-underline']//span[@class='p-menuitem-text' and text()='Report Selection']")));
            Thread.sleep(9000);
            System.out.println("Truck report is generated for Remainder Truck");

            System.out.println("Remainder Truck is done");
        }
    }

    //For Common Stowage Area
    protected void UserEntersTruckDetails() throws InterruptedException {

        // From the Extracted text -Storing Globally.
        String intakeusrn = (String) ScenarioContext.get("IUSRN");
        System.out.println("Retrieved Intake USRN in B.pageobjects: " + intakeusrn);

        //Entering the Primary Intake
        //enterText(primaryintakeInput,"USRN-1000267");
        enterText(primaryintakeInput,intakeusrn);
        clickElement(primaryintakeIndex);

        // Check for Proper truck No. now selected through index1
        enterText(trucknoInput,"t");
        clickElement(trucknoIndex);

        // Entering driver data
        enterText(driverInput,"David Miller");
        infoclicksSafeclicks();

        // Entering trailer data  -// Generate a random number
        double randomNumber = Math.floor(Math.random() * 9000) + 1000;
        // Combine the random number with a name
        String nameWithNumber = "TR" + randomNumber;
        enterText(trailerInput,nameWithNumber);
        infoclicksSafeclicks();

        // User clicks  SAVE and Next
        UserclicksSaveNext();

        // Selects Destination Parent and Clicks on Automation Area
        clickElement(DestinationParent);
        Thread.sleep(3000);
        clickElement(AutomationArea);
        Thread.sleep(5000);

        // Selects Destination --Globally Passing the Destination Name --//For Globally Passing the Destination value from the Excel sheet
        selectDropdownDD(selectDestination,"text","SALT-COMMON STOWAGE");
        //selectDropdownOption(selectDestination, "text", destinationText);

        // Enter Gross weight and Declared Globally
//        Grossweight = "44.000";
//        enterDynamicNumericInput(grossDiv, grossInp,Grossweight);

    }

    protected By DestinationParent = By.xpath("//input[@id='form-component-destinationParent']");
    protected By AutomationArea = By.xpath("//button[contains(normalize-space(.), 'AUTOMATION AREA')]");
    protected By selectDestination = By.xpath("//select[@id='form-component-destination']");

    //For Consignment Group Area
    protected By lastwashedInput = By.xpath("//input[@class='form-control']");
    protected By TruckchecksInput = By.xpath("//div[@id='form-component-truckChecks']//button[contains(@class, 'ms-choice')]");
    protected By Truckselectallcheckbox = By.xpath("//input[@type='checkbox' and @data-name='selectAll']");

    protected By lastloadInput3 = By.xpath("//input[@id='form-component-lastLoad3']");
    protected By lastloadInput2 = By.xpath("//input[@id='form-component-lastLoad2']");
    protected By lastloadInput1 = By.xpath("//input[@id='form-component-lastLoad1']");


    protected By lastloadMethodInput1 = By.xpath("//input[@id='form-component-lastLoad1cleaningMethod']");
    protected By lastloadMethodInput2 = By.xpath("//input[@id='form-component-lastLoad2cleaningMethod']");
    protected By lastloadMethodInput3 = By.xpath("//input[@id='form-component-lastLoad3cleaningMethod']");

    protected By lastloadIndexclick = By.xpath("//ul[@class='filter-select-options']//button[contains(@class, 'filter-select-option')][1]");

    protected By tasccnoinput = By.xpath("//input[@id='form-component-schemeRegistration']");


    protected By selectAssurance = By.id("form-component-originalSchemeGuid");
    protected void UserEntersTruckDetails_ConginmentGroup() throws InterruptedException {

        // From the Extracted text -Storing Globally.
        String intakeusrn = (String) ScenarioContext.get("IUSRN");
        System.out.println("Retrieved Intake USRN in B.pageobjects: " + intakeusrn);

        //Entering the Primary Intake
        enterText(primaryintakeInput,intakeusrn);
        clickElement(primaryintakeIndex);

        // Check for Proper truck No. now selected through index1
        enterText(trucknoInput,"a");
        clickElement(trucknoIndex);

        // Entering driver data
        enterText(driverInput,"Rahul Killer");
        infoclicksSafeclicks();

        // Entering trailer data  -// Generate a random number
        double randomNumber = Math.floor(Math.random() * 9000) + 1000;
        // Combine the random number with a name
        String nameWithNumber = "TR" + randomNumber;
        enterText(trailerInput,nameWithNumber);
        infoclicksSafeclicks();

        // User clicks  SAVE and Next
        UserclicksSaveNext();

        // Selects Destination Parent and Clicks on Automation Area
        clickElement(DestinationParent);
        Thread.sleep(3000);
        clickElement(AutomationArea);
        Thread.sleep(5000);

        // Selects Destination --Globally Passing the Destination Name --//For Globally Passing the Destination value from the Excel sheet
        selectDropdownDD(selectDestination,"text","SUNFLOWER PELLETS");

        //last washed enter date
        enterDate(lastwashedInput,"Today");

        //Truck checks
        clickElement(TruckchecksInput);
        clickElement(Truckselectallcheckbox);
        infoclicksSafeclicks();

        //Last Load
        enterText(lastloadInput1,"beans");
        JSClick(lastloadIndexclick);

        enterText(lastloadMethodInput1,"Washed");
        Thread.sleep(3000);
        JSClick(lastloadIndexclick);


        enterText(lastloadInput2,"beans");
        Thread.sleep(3000);
        JSClick(lastloadIndexclick);


        enterText(lastloadMethodInput2,"Washed");
        Thread.sleep(3000);
        JSClick(lastloadIndexclick);

        enterText(lastloadInput3,"beans");
        Thread.sleep(3000);
        JSClick(lastloadIndexclick);


        enterText(lastloadMethodInput3,"Washed");
        Thread.sleep(3000);
        JSClick(lastloadIndexclick);

        // Entering Tascc no
        enterText(tasccnoinput,"TASCC1234");

        // Select Assurance
        selectDropdownByMatchingText(selectAssurance,"contains","TASCC (TASCC)");

    }
    // For Consignment
    protected void UserEntersTruckDetails_Consignment() throws InterruptedException {

        // From the Extracted text -Storing Globally.
        String intakeusrn = (String) ScenarioContext.get("IUSRN");
        System.out.println("Retrieved Intake USRN in B.pageobjects: " + intakeusrn);

        //Entering the Primary Intake
        enterText(primaryintakeInput,intakeusrn);
        clickElement(primaryintakeIndex);

        // Check for Proper truck No. now selected through index1
        enterText(trucknoInput,"a");
        clickElement(trucknoIndex);

        // Entering driver data
        enterText(driverInput,"RajaniKanth");
        infoclicksSafeclicks();

        // Entering trailer data  -// Generate a random number
        double randomNumber = Math.floor(Math.random() * 9000) + 1000;
        // Combine the random number with a name
        String nameWithNumber = "TR" + randomNumber;
        enterText(trailerInput,nameWithNumber);
        infoclicksSafeclicks();

        // User clicks  SAVE and Next
        UserclicksSaveNext();

        // Selects Destination Parent and Clicks on Automation Area
        clickElement(DestinationParent);
        Thread.sleep(3000);
        clickElement(AutomationArea);
        Thread.sleep(5000);

        // Selects Destination
        selectDropdownDD(selectDestination,"text","Consignment");

        //last washed enter date
        enterDate(lastwashedInput,"Today");

        //Truck checks
        clickElement(TruckchecksInput);
        clickElement(Truckselectallcheckbox);
        infoclicksSafeclicks();

        //Last Load
        enterText(lastloadInput1,"maize");
        JSClick(lastloadIndexclick);

        enterText(lastloadMethodInput1,"Washed");
        Thread.sleep(3000);
        JSClick(lastloadIndexclick);


        enterText(lastloadInput2,"maize");
        Thread.sleep(3000);
        JSClick(lastloadIndexclick);


        enterText(lastloadMethodInput2,"Washed");
        Thread.sleep(3000);
        JSClick(lastloadIndexclick);

        enterText(lastloadInput3,"maize");
        Thread.sleep(3000);
        JSClick(lastloadIndexclick);


        enterText(lastloadMethodInput3,"Washed");
        Thread.sleep(3000);
        JSClick(lastloadIndexclick);

        // Entering Tascc no
        enterText(tasccnoinput,"TASCC786");

        // Select Assurance
        selectDropdownByMatchingText(selectAssurance,"contains","TASCC (TASCC)");

    }

    //Step 18.2
    //Click on Save R

    //Step 18.3
    protected By bookoutBtn = By.xpath("//span[@class='text ng-binding' and text()='Book Out']");
    protected void Bookout() {
        //*** The Step performing clicking on Intake ID from the Table and Clicking on BookOut
        //clickOnIntakeID("IBR:\"03072025\"\"092403 AM\"");

        //***From the Extracted text -Storing Globally.** from the Truck_intake page --"IBR:\"03072025\"\"092403 AM\"
        String Intakedetails = (String) ScenarioContext.get("IBR");
        System.out.println("Retrieved Intake Details IBR in Truck intake.pageobjects: " +Intakedetails);

        clickOnIntakeID(Intakedetails);
        JSClick(bookoutBtn);

        // Enter Tare weight and Declared Globally
        Tareweight = "14.000";
        enterDynamicNumericInput(Tarediv, TareInput, Tareweight);
        infoclicksSafeclicks();
    }

    public void clickOnIntakeID(String shuntValueToMatch) {
        try {
            List<WebElement> rows = driver.findElements(
                    By.xpath("//td[@class='scrollingContainer']//tbody[@class='ng-scope']/tr/td/span[@data-column-name='tv.Intake']"));

            System.out.println("Total rows found: " + rows.size());

            for (WebElement row : rows) {
                String rowText = row.getText();
                System.out.println("Row text: " + rowText);  // Print every row for visibility

                if (rowText.contains(shuntValueToMatch)) {
                    System.out.println("Clicking on row with IntakeID: " + shuntValueToMatch);
                    System.out.println("Row matched: " + rowText);  // Print the matched row
                    row.click();
                    return;
                }
            }

            System.out.println("Intake ID not found: " + shuntValueToMatch);

        } catch (Exception e) {
            System.out.println("Exception while clicking on Intake ID: " + e.getMessage());
        }
    }

    //Step 23.1 --Navigates to Truck Onsite
    //Step 23.2
    protected By completedTruckBtn = By.xpath("//a[@id='Truck_Visit.Grid.viewCompleted']");
    protected void UserNavigateToTheCompletedTrucks() throws InterruptedException {
        clickElement(completedTruckBtn);
        Thread.sleep(5000);
    }
    //Step 23.3
    protected By completedtruckpageTitle = By.xpath("//a[./span[text()='Completed Trucks']]");
    protected void verifyCompletedTruckPage()
    {
        verifyPageHeaderTitle(completedtruckpageTitle,"Completed Trucks","***Completed Trucks Page Title is** is as below:");
    }

    //Step 24.1 --Navigates to Cargo Storage areas
    // ******Cargo > Storage Area****
    protected By Cargo = By.xpath("//span[normalize-space(text())='Cargo']");
    protected By storageareaMenu = By.xpath("//a[@class='menu-label text-left' and text()='Storage Areas']");
    protected By safeZone = By.xpath("//li[@data-pc-section='homeitem']//img[@alt='CommTrac brand logo']");

    protected void UserNavigatesToCargoStorageAreas()
    {
        clickElement(Cargo);
        scrollIntoViewAndClick(storageareaMenu);
        clickElement(safeZone);
    }
    //Step 24.2
    protected By automationarea = By.xpath("//span[@class='data-type-text' and text()='AUTOMATION AREA']");
    protected By viewStockyard = By.xpath("//span[@class='text ng-binding' and text() ='View Stockyard']");
    protected void UserNavigateToAreaStockyard() throws InterruptedException {
        Thread.sleep(6000);
        getEle(automationarea).get(1).click();
        scrollIntoViewAndClick(viewStockyard);
    }

    //Step 24.3
    protected By storageareaTitle = By.xpath("//span[@class='p-menuitem-text' and text() ='Storage Areas']");
    protected void allDischargedProducts()
    {
        verifyPageHeaderTitle(storageareaTitle,"Storage Areas","Storage Areas Page:");
    }

}
