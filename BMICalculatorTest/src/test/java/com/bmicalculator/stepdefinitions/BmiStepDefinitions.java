package com.bmicalculator.stepdefinitions;

import com.bmicalculator.pages.BmiCalculatorPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert; // FIX 1: Changed from org.junit.jupiter.api.Assertions
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BmiStepDefinitions {

    private WebDriver driver;
    private BmiCalculatorPage bmiPage;

    @Before
    public void setup() {
        // Setup WebDriver
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless"); // Run in headless mode
        //options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Given("the user is on the BMI Calculator page")
    public void the_user_is_on_the_bmi_calculator_page() {
        driver.get("https://www.calculator.net/bmi-calculator.html");
        bmiPage = new BmiCalculatorPage(driver);
    }

    @When("the user enters age as {string}, gender as {string}, height as {string}'{string}\", and weight as {string}")
    public void the_user_enters_age_gender_height_and_weight(String age, String gender, String feet, String inches, String pounds) {
        bmiPage.enterAge(age);
        bmiPage.selectGender(gender);
        bmiPage.enterHeight(feet, inches);
        bmiPage.enterWeight(pounds);
    }

    @When("clicks the calculate button")
    public void clicks_the_calculate_button() {
        bmiPage.clickCalculate();
    }

    @Then("the result should show a BMI category of {string}")
    public void the_result_should_show_a_bmi_category_of(String expectedCategory) {
        String result = bmiPage.getResult();
        
        // FIX 2: Changed from Assertions.assertTrue to Assert.assertTrue
        Assert.assertTrue("Expected category: " + expectedCategory + ", but got: " + result,
                result.contains(expectedCategory));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}