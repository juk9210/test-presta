package properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.util.Arrays;

/**
 * Class that will handle events
 *
 * @author Shakhov Yevhen.
 */
public class EventHandler implements WebDriverEventListener {
    static StringBuilder sb = new StringBuilder();

    /**
     * Method for writing log for events.
     *
     * @param message
     */
    private void log(String message) {
        System.out.println( message );
        sb.append( message ).append( "\n" );
    }

    /**
     * Method to get the name of the web element.
     *
     * @param webElement
     * @return
     */
    private String getElement(WebElement webElement) {
        if (webElement != null) {
            if (webElement.getText() != null) {
                return webElement.getText();
            } else if (webElement.getTagName() != null) {
                return webElement.getTagName();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void beforeAlertAccept(WebDriver webDriver) {
        log( "Attempt to accept warning." );
    }

    public void afterAlertAccept(WebDriver webDriver) {
        log( "Alert received successfully." );
    }

    public void afterAlertDismiss(WebDriver webDriver) {
        log( "Dismissal successfully canceled." );
    }

    public void beforeAlertDismiss(WebDriver webDriver) {
        log( "Attempt to cancel warning." );
    }

    public void beforeNavigateTo(String s, WebDriver webDriver) {
        log( "Try to go to " + s );

    }

    public void afterNavigateTo(String s, WebDriver webDriver) {
        log( "Navigated to " + s + " successfully" );

    }

    public void beforeNavigateBack(WebDriver webDriver) {
        log( "Attempt to go back." );
    }

    public void afterNavigateBack(WebDriver webDriver) {
        log( "Navigated back successfully." );
    }

    public void beforeNavigateForward(WebDriver webDriver) {
        log( "Attempt to move forward." );
    }

    public void afterNavigateForward(WebDriver webDriver) {
        log( "Navigated forward successfully." );
    }

    public void beforeNavigateRefresh(WebDriver webDriver) {
        log( "Attempt to update." );
    }

    public void afterNavigateRefresh(WebDriver webDriver) {
        log( "Page Updated Successfully" );
    }

    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        log( "Trying to find: " + getElement( webElement ) + " by: " + by.toString() );
    }

    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        log( "Found successfully" );
    }

    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        log( "Attempt to click on " + getElement( webElement ) );
    }

    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        log( "Clicked successfully" );
    }

    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        log( "Attempt to change value of " + getElement( webElement ) + " on " + Arrays.toString( charSequences ) );
    }

    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        log( "WebElement changed on " + Arrays.toString( charSequences ) + " successfully" );
    }

    public void beforeScript(String s, WebDriver webDriver) {
        log( "Attempt to execute a script " + s );
    }

    public void afterScript(String s, WebDriver webDriver) {
        log( s + " executed successfully" );
    }

    public void beforeSwitchToWindow(String s, WebDriver webDriver) {
        log( "Trying to switch to window " + s );
    }

    public void afterSwitchToWindow(String s, WebDriver webDriver) {
        log( s + " switched successfully" );
    }

    public void onException(Throwable throwable, WebDriver webDriver) {
        log( "Next exception has been caused: " + throwable );
    }

    public <X> void beforeGetScreenshotAs(OutputType<X> outputType) {

    }

    public <X> void afterGetScreenshotAs(OutputType<X> outputType, X x) {

    }

    public void beforeGetText(WebElement webElement, WebDriver webDriver) {
        log( "Trying to get text of " + getElement( webElement ) );
    }

    public void afterGetText(WebElement webElement, WebDriver webDriver, String s) {
        log( "Text: " + s + " of " + getElement( webElement ) + " got successfully" );
    }
}