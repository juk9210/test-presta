package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import properties.AbstractProperties;

public abstract class AbstractSettings {
    final WebDriverWait wait;
    final WebDriver driver;

    AbstractSettings() {
        driver = AbstractProperties.getDriver();
        wait = new WebDriverWait( driver, 10 );
    }

    public void waitElement(By by) {
        wait.until( ExpectedConditions.elementToBeClickable( by ) );
    }

    public void waitElementVisible(By by) {
        wait.until( ExpectedConditions.visibilityOfElementLocated( by ) );
    }

    public void waitURL(String url) {
        wait.until( ExpectedConditions.urlContains( url ) );
    }
}

