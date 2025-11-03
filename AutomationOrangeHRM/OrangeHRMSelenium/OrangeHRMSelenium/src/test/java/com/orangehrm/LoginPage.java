package com.orangehrm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Element locators
    private By usernameInput = By.name("username");
    private By passwordInput = By.name("password");
    private By submitButton = By.cssSelector("button[type='submit']");
    private By dashboardTitle = By.xpath("//h6[text()='Dashboard']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Open login page
    public void openLoginPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    // Enter username
    public void typeUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        driver.findElement(usernameInput).sendKeys(username);
    }

    // Enter password
    public void typePassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    // Click login button
    public void pressLoginButton() {
        driver.findElement(submitButton).click();
    }

    // Verify dashboard is displayed
    public boolean isDashboardVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardTitle));
            return driver.findElement(dashboardTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Perform login
    public void login(String username, String password) {
        openLoginPage();
        typeUsername(username);
        typePassword(password);
        pressLoginButton();
    }
}
