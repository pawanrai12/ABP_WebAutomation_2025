package com.pageobjects.abp;

import com.framework.reusable.WebReusableComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class StorageAreaPage extends WebReusableComponents
{
    // ******Cargo > Storage Area****
    protected By Cargo = By.xpath("//span[normalize-space(text())='Cargo']");
    protected By safeZone = By.xpath("//*[@id=\"CommtracVue\"]/div[1]/header/div/div[1]/nav/ol/li[1]/a/span/img");
    protected By storageareaMenu = By.xpath("//a[@class='menu-label text-left' and text()='Storage Areas']");
    private double boxHeight;
    private double boxX;
    private double boxY;
    private double boxWidth;

    protected void UserNavigatesToCargoStorageAreas()
    {
        setZoomLevel(50);
        clickElement(Cargo);
        scrollIntoViewAndClick(storageareaMenu);
        clickElement(safeZone);
    }

    protected By automationarea = By.xpath("//span[@class='data-type-text' and text()='AUTOMATION AREA']");
    protected By viewStockyard = By.xpath("//span[@class='text ng-binding' and text() ='View Stockyard']");

    protected void UserNavigatetoAreaStockyard() throws InterruptedException {
        Thread.sleep(2000);
        getEle(automationarea).get(1).click();
        scrollIntoViewAndClick(viewStockyard);
        Thread.sleep(2000);

    }
    //creating the Storage Area
    protected By addStockareaSqrBtn = By.xpath("(//button[@data-ng-repeat='shape in supportedShapes' and @data-ng-if='actions.addArea'])[1]");
    protected By addStockareaCirlBtn = By.xpath("(//button[@data-ng-repeat='shape in supportedShapes' and @data-ng-if='actions.addArea'])[2]");
    protected By RsaveBtn = By.xpath("//span[@class='text ng-binding' and text()='Save']");

    protected void UserAddArea() throws InterruptedException {
        Thread.sleep(2000);
        //clickElement(addStockareaSqrBtn);
        Thread.sleep(2000);
        //clickElement(RsaveBtn);

    }

    public void getExistingBoxDimensionsAndCoordinates() {
        // Corrected XPath based on the provided DOM.
        // This targets the <use> element that is a direct child of a <g> with class 'geom-data-item'.
        By locator = By.xpath("//g[@class='geom-data-item']/use[@class='shape-eventcatcher']");
        // Or, if there are other 'use' elements, you might need to be more specific,
        // e.g., By.xpath("//g[@class='geom-data-item'][1]/use") for the first one,
        // or by its xlink:href if you have that ID.
        // For example: By.xpath("//g[@class='geom-data-item']/use[contains(@xlink:href, '#shape-')]");

        try {
            // Add an explicit wait to ensure the element is present in the DOM
            // This is crucial for dynamically loaded elements and preventing NoSuchElementException
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Wait up to 15 seconds
            WebElement existingBox = wait.until(ExpectedConditions.presenceOfElementLocated(locator));

            System.out.println("Found existing box element.");

            // Use JavascriptExecutor to get the bounding client rect
            JavascriptExecutor js = (JavascriptExecutor) driver;
            // The script returns [left, top, width, height] relative to the viewport
            double[] rect = (double[]) js.executeScript(
                    "var rect = arguments[0].getBoundingClientRect(); " +
                            "return [rect.left, rect.top, rect.width, rect.height];",
                    existingBox
            );

            // Assign to instance variables
            this.boxX = rect[0];
            this.boxY = rect[1];
            this.boxWidth = rect[2];
            this.boxHeight = rect[3];

            System.out.println("Existing Box Dimensions:");
            System.out.println("  X: " + boxX);
            System.out.println("  Y: " + boxY);
            System.out.println("  Width: " + boxWidth);
            System.out.println("  Height: " + boxHeight);

        } catch (Exception e) {
            System.err.println("Error getting existing box dimensions: " + e.getMessage());
            // It's generally good practice to throw a custom exception or log more detail
            throw new RuntimeException("Failed to get existing box dimensions: " + e.getMessage(), e);
        }
    }



    //Step 40.1
    protected By viewcontentsBtn = By.xpath("//span[normalize-space(text())='View Contents']");
    protected By filterBtn = By.xpath("//button[@class='popup-toggle button-secondary']");
    protected By addfilterBtn = By.xpath("//button[contains(@class, 'filter-add-button')]");
    protected By area = By.xpath("(//button[normalize-space(text())='Area'])[1]");
    protected By selectarea = By.id("form-component-sys.Name-0-operator");
    protected By areainput = By.xpath("//input[@id='form-component-sys.Name-0']");
    protected By applyfilterBtn = By.xpath("//button[contains(., 'Apply Filter')]");
    protected By pageselect = By.xpath("//select[@data-ng-model='clause.pageSize']");

    protected void areaselection_Consignment() throws InterruptedException {
        clickElement(filterBtn);
        Thread.sleep(2000);
        clickElement(addfilterBtn);
        Thread.sleep(2000);
        clickElement(area);
        Thread.sleep(2000);
        selectDropdownDD(selectarea,"text","Contains");
        Thread.sleep(2000);
        enterText(areainput,"Consignment");
        Thread.sleep(2000);
        clickElement(applyfilterBtn);
        Thread.sleep(2000);


        //clickOnArea("Consignment Internal Move");


        Thread.sleep(2000);
        clickElement(viewcontentsBtn);

        Thread.sleep(6000);
        //printConsignmentAndMTValues();

    }
}
