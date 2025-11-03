package com.orangehrm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LeavePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By leaveMenuBtn = By.xpath("//span[text()='Leave']");
    private By applyBtn = By.linkText("Apply");
    private By leaveTypeDropdownField = By.xpath("//label[text()='Leave Type']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']");
    private By leaveTypeOptions = By.xpath("//div[@role='option']/span");
    private By fromDateInput = By.xpath("//label[text()='From Date']/parent::div/following-sibling::div//input");
    private By toDateInput = By.xpath("//label[text()='To Date']/parent::div/following-sibling::div//input");
    private By commentsInput = By.xpath("//label[text()='Comments']/parent::div/following-sibling::div//textarea");
    private By submitLeaveBtn = By.cssSelector("button[type='submit']");
    private By myLeaveMenu = By.linkText("My Leave");
    private By leaveTable = By.cssSelector(".oxd-table");

    public LeavePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateToLeave() {
        wait.until(ExpectedConditions.elementToBeClickable(leaveMenuBtn));
        driver.findElement(leaveMenuBtn).click();
    }

    public void clickApply() {
        wait.until(ExpectedConditions.elementToBeClickable(applyBtn));
        driver.findElement(applyBtn).click();
    }

    public void selectLeaveType() {
        wait.until(ExpectedConditions.elementToBeClickable(leaveTypeDropdownField));
        driver.findElement(leaveTypeDropdownField).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(leaveTypeOptions));
        driver.findElements(leaveTypeOptions).get(1).click(); // Select first available leave type
    }

    public void enterFromDate(String date) {
        WebElement fromDate = driver.findElement(fromDateInput);
        fromDate.clear();
        fromDate.sendKeys(date);
    }

    public void enterToDate(String date) {
        WebElement toDate = driver.findElement(toDateInput);
        toDate.clear();
        toDate.sendKeys(date);
    }

    public void enterComments(String comments) {
        driver.findElement(commentsInput).sendKeys(comments);
    }

    public void clickApplyLeaveButton() {
        driver.findElement(submitLeaveBtn).click();
    }

    public void applyLeave(String fromDate, String toDate, String comments) {
        navigateToLeave();
        clickApply();
        selectLeaveType();
        enterFromDate(fromDate);
        enterToDate(toDate);
        enterComments(comments);
        clickApplyLeaveButton();
    }

    public void navigateToMyLeave() {
        navigateToLeave();
        wait.until(ExpectedConditions.elementToBeClickable(myLeaveMenu));
        driver.findElement(myLeaveMenu).click();
    }

    public boolean isLeaveListDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(leaveTable));
            return driver.findElement(leaveTable).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
