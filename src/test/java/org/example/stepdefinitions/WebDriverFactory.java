package org.example.stepdefinitions;

import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    public static SelfHealingDriver CreateDriver(BrowserType browserType) {
        switch (browserType) {
            case Chrome:
                return CreateChromeDriver();
            case Firefox:
                return CreateFirefoxDriver();
            default:
                throw new UnsupportedOperationException("Browser type '{browserType}' is not supported. Supported browser types are: Chrome, Firefox.");
        }
    }

        private static SelfHealingDriver CreateChromeDriver()
        {
            var delegate = new ChromeDriver();
            return SelfHealingDriver.create(delegate);
        }

    private static SelfHealingDriver CreateFirefoxDriver()
    {
        var delegate = new FirefoxDriver();
        return SelfHealingDriver.create(delegate);
    }
}
enum BrowserType
{
    Chrome,
    Firefox
}
