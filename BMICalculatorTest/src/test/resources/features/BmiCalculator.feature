Feature: BMI Calculator Functionality Validation

  As a user, I want to calculate my BMI
  So that I can know my health status based on standard categories.

  @SmokeTest
  Scenario Outline: Calculate BMI for different user data
    Given the user is on the BMI Calculator page
    When the user enters age as "<age>", gender as "<gender>", height as "<feet>'<inches>", and weight as "<pounds>"
    And clicks the calculate button
    Then the result should show a BMI category of "<category>"

    Examples:
      | age | gender | feet | inches | pounds | category        |
      | 30  | Male   | 5    | 10     | 120    | Normal          |
      | 45  | Female | 5    | 3      | 160    | Overweight      |
      | 25  | Male   | 6    | 1      | 140    | Underweight     |
      | 50  | Female | 5    | 5      | 200    | Obese           |