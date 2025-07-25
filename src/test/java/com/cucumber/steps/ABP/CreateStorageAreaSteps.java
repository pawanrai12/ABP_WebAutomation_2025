package com.cucumber.steps.ABP;

import com.pageobjects.abp.StorageAreaPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CreateStorageAreaSteps extends StorageAreaPage
{
    @When("User navigates to Cargo Storage Area")
    public void UserNavigatesToCargoStorageArea() throws InterruptedException {
        UserNavigatesToCargoStorageAreas();
        UserNavigatetoAreaStockyard();
    }

    @And("create new Storage Area")
    public void createNewStorageArea() throws InterruptedException {
        UserAddArea();
        getExistingBoxDimensionsAndCoordinates();
    }

    @And("I Drag and Drop to create a box at coordinates")
    public void iDragAndDropToCreateABoxAtCoordinates()
    {
    }

    @Then("a new stock box should be displayed")
    public void aNewStockBoxShouldBeDisplayed() {

    }

    @And("I drag and drop to create a box from coordinates {int}, {int} to {int}, {int}")
    public void iDragAndDropToCreateABoxAtCoordinates(int x1, int y1, int x2, int y2) {
        // Calculate width and height from the coordinates for relative movement
        // These are relative offsets from the starting point for moveByOffset.
        int offsetX = x2 - x1;
        int offsetY = y2 - y1;

        Actions actions = new Actions(driver);

        // Option 1: Using the <body> element as a starting point (recommended for absolute coordinates)
        // This moves the mouse to x1, y1 (relative to the viewport) and then performs the drag.
        try {
            WebElement body = driver.findElement(By.tagName("body"));

            // Perform the drag and drop action
            actions.moveToElement(body, x1, y1) // Move to the start point (x1, y1) relative to body's top-left
                    .clickAndHold()
                    .moveByOffset(offsetX, offsetY) // Drag by the calculated offset
                    .release()
                    .build()
                    .perform();

            System.out.println("Successfully dragged and dropped to create a box from (" + x1 + "," + y1 + ") to (" + x2 + "," + y2 + ")");

        } catch (Exception e) {
            System.err.println("Error performing drag and drop: " + e.getMessage());
            // You might want to throw an exception or mark the step as failed in a real scenario
            throw new RuntimeException("Failed to create box via drag and drop: " + e.getMessage(), e);
        }
    }
    // --- Helper for obtaining dynamic coordinates ---
    // If your coordinates are based on an existing element, you'd find that element first.
    // Example of a helper method (can be in a separate utility class or within your step definition class)
    public double[] getElementCoordinates(By locator) {
        WebElement element = driver.findElement(locator);
        // Using JavaScript to get bounding client rect, which provides viewport-relative coordinates
        // This is generally more reliable for visual positions than element.getLocation() + element.getSize()
        // especially with complex SVG or CSS transforms.
        return (double[]) ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "var rect = arguments[0].getBoundingClientRect(); " +
                        "return [rect.left, rect.top, rect.right, rect.bottom, rect.width, rect.height];",
                element
        );
    }
}
