package org.example.stepdefinitions;

import com.epam.healenium.SelfHealingDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.HomePage;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

    public class HomePageStepDefinitions {
        SelfHealingDriver driver = CucumberHooks.driver;
        HomePage homePage = new HomePage(driver);
        @Given("the user is on the Home page")
        public void theUserIsOnTheHomePage() {
            driver.navigate().to("http://localhost:4200/");
        }

        @When("the user clicks on the Angular Material button")
        public void theUserClicksOnTheAngularMaterialButton() {
            homePage.angularMaterial.click();
        }

        @Then("a new tab should open with the Angular Material page")
        public void aNewTabShouldOpenWithTheAngularMaterialPage() {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            wait.until(ExpectedConditions.urlContains("material.angular.io/"));
        }

        @Then("the page title should be {string}")
        public void thePageTitleShouldBe(String expectedPageTitle) {
            String actualPageTitle = driver.getTitle();
            Assert.assertEquals(expectedPageTitle, actualPageTitle);
        }
    }
