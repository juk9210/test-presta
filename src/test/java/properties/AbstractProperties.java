package properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Reporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class AbstractProperties {
    private static EventFiringWebDriver driver;

    public static void log(String message) {
        Reporter.log( message );
        System.out.println( message );
    }

    public static void writeLogs() {
        File logsFile = new File( "Logs.txt" );
        try (BufferedWriter bw = new BufferedWriter( new FileWriter( logsFile ) )) {
            bw.write( EventHandler.sb.toString() );
        } catch (IOException e) {
            e.printStackTrace();
        }
        EventHandler.sb = new StringBuilder();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static EventFiringWebDriver getConfigures() {
        WebDriver driver = getOurDriver();
        driver.manage().window().maximize();
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver( driver );
        eventFiringWebDriver.register( new EventHandler() );
        eventFiringWebDriver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS );
        return eventFiringWebDriver;
    }

    private static WebDriver getOurDriver() {
        System.setProperty( "webdriver.chrome.driver", new File( AbstractProperties.class.getResource("/chromedriver.exe").getFile()).getPath() );
        return new ChromeDriver();
    }
}
