package com.bmicalculator.pages;

// FIX: Added the necessary imports for WebDriver, WebElement, FindBy, and PageFactory
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BmiCalculatorPage {

    WebDriver driver;

    // Locators using @FindBy
    @FindBy(id = "cage")
    private WebElement ageInput;

    @FindBy(id = "csex1")
    private WebElement maleRadio;

    @FindBy(id = "csex2")
    private WebElement femaleRadio;

    @FindBy(id = "cheightfeet")
    private WebElement heightFeetInput;

    @FindBy(id = "cheightinch")
    private WebElement heightInchInput;

    @FindBy(id = "cpound")
    private WebElement weightPoundInput;

    @FindBy(xpath = "//input[@value='Calculate']")
    private WebElement calculateButton;

    @FindBy(css = ".rightresult .bigtext b")
    private WebElement resultText;

    // Constructor to initialize elements
    public BmiCalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Methods to interact with the elements
    public void enterAge(String age) {
        ageInput.clear();
        ageInput.sendKeys(age);
    }

    public void selectGender(String gender) {
        if (gender.equalsIgnoreCase("Male")) {
            maleRadio.click();
        } else if (gender.equalsIgnoreCase("Female")) {
            femaleRadio.click();
        }
    }

    public void enterHeight(String feet, String inches) {
        heightFeetInput.clear();
        heightFeetInput.sendKeys(feet);
        heightInchInput.clear();
        heightInchInput.sendKeys(inches);
    }

    public void enterWeight(String pounds) {
        weightPoundInput.clear();
        weightPoundInput.sendKeys(pounds);
    }

    public void clickCalculate() {
        calculateButton.click();
    }

    public String getResult() {
        return resultText.getText();
    }
}