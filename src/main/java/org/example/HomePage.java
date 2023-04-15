package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private SelfHealingDriver driver;

    @FindBy(linkText = "Angular Material")
    public WebElement angularMaterial;

    public HomePage(SelfHealingDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
