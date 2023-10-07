package org.example.stepdefinitions;

import com.epam.healenium.SelfHealingDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CucumberHooks {
    protected static SelfHealingDriver driver;

    @Before
    public void beforeTest() {
           driver = WebDriverFactory.CreateDriver(BrowserType.Firefox);
        }
    @After
    public void afterTest() {
        driver.quit();
    }
    }
