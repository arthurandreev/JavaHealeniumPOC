package org.example;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;

public class HomePageTest extends BaseTest {
    @Test
    public void navigateToAngularMaterialPageTest() {
        HomePage homePage = new HomePage(driver);
        driver.navigate().to("http://localhost:4200/");
        homePage.angularMaterial.click();
        // Wait for a new tab to be opened
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        // Switch to the new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        wait.until(ExpectedConditions.urlToBe("https://material.angular.io/"));
        String expectedTitle = "Angular Material UI component library";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }
}