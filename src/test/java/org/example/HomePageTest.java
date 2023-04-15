package org.example;

import com.epam.healenium.SelfHealingDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

public class HomePageTest {

    private SelfHealingDriver driver;

    @BeforeAll
    static void setupClass(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    protected void setupTest() {

        WebDriver delegate = new ChromeDriver();
        driver = SelfHealingDriver.create(delegate);
    }

    @AfterEach
    public void cleanup() {
        driver.quit();
    }
    @Test
    public void navigateToAngularMaterialPageTest() {
        HomePage homePage = new HomePage(driver);
        driver.navigate().to("http://localhost:4200/");
        homePage.angularMaterial.click();
    }
}