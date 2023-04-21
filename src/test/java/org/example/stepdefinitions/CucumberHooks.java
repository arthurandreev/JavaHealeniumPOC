package org.example.stepdefinitions;

import com.epam.healenium.SelfHealingDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CucumberHooks {
    protected static SelfHealingDriver driver;

    @Before
    public void beforeTest() {
            WebDriverManager.chromedriver().setup();
            WebDriver delegate = new ChromeDriver();
            driver = SelfHealingDriver.create(delegate);
        }
    @After
    public void afterTest() { driver.quit(); }
    }
