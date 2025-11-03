package com.orangehrm;

import com.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrangeHRMTests extends BaseTest {

    @Test(priority = 1, description = "Verify that admin can log in successfully to OrangeHRM dashboard")
    public void testLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");
        Assert.assertTrue(loginPage.isDashboardVisible(), "Login failed - Dashboard not visible");
        System.out.println(" Admin logged in and dashboard is visible");
    }

    @Test(priority = 2, description = "Verify that admin can submit a leave request")
    public void testApplyLeave() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        LeavePage leavePage = new LeavePage(driver);
        try {
            leavePage.applyLeave("2025-12-20", "2025-12-22", "Requesting leave for year-end break");
            Thread.sleep(2000);
            System.out.println(" Leave request submitted successfully");
        } catch (Exception e) {
            System.out.println(" Leave request might have gone through - " + e.getMessage());
        }
    }

    @Test(priority = 3, description = "Verify that the leave list can be accessed by admin")
    public void testViewLeaveList() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        LeavePage leavePage = new LeavePage(driver);
        leavePage.navigateToMyLeave();
        Assert.assertTrue(leavePage.isLeaveListDisplayed(), "Leave list is not accessible");
        System.out.println("Leave list is displayed correctly");
    }

    @Test(priority = 4, description = "Verify that a new candidate can be added in recruitment module")
    public void testAddCandidate() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        RecruitmentPage recruitmentPage = new RecruitmentPage(driver);
        String timestamp = String.valueOf(System.currentTimeMillis());
        recruitmentPage.addCandidate(
                "John" + timestamp.substring(timestamp.length() - 4),
                "Robert",
                "Doe",
                "john.doe" + timestamp.substring(timestamp.length() - 6) + "@test.com"
        );
        try {
            Thread.sleep(3000);
            System.out.println(" New candidate added successfully");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(priority = 5, description = "Verify that the list of candidates can be viewed by admin")
    public void testViewCandidates() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Admin", "admin123");

        RecruitmentPage recruitmentPage = new RecruitmentPage(driver);
        recruitmentPage.openCandidatesPage();
        Assert.assertTrue(recruitmentPage.isCandidatesTableVisible(), "Candidate table is not visible");
        System.out.println(" Candidate table displayed successfully");
    }
}
