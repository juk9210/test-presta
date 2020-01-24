package properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.util.Arrays;

public class EventHandler implements WebDriverEventListener {
    static StringBuilder sb = new StringBuilder();

    /**
     * Method for writing correct log for events
     *
     * @param message
     */
    private void log(String message) {
        System.out.println( message );
        sb.append( message ).append( "\n" );
    }

    /**
     * Getting the name of web element.
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
        log( "Trying to accept alert." );
    }

    public void afterAlertAccept(WebDriver webDriver) {
        log( "Alert is accepted successfully." );
    }

    public void afterAlertDismiss(WebDriver webDriver) {
        log( "Dismiss is canceled successfully." );
    }

    public void beforeAlertDismiss(WebDriver webDriver) {
        log( "Trying to cancel alert." );
    }

    public void beforeNavigateTo(String s, WebDriver webDriver) {
        log( "Trying to navigate to " + s );

    }

    public void afterNavigateTo(String s, WebDriver webDriver) {
        log( "Navigated to " + s + " successfully" );

    }

    public void beforeNavigateBack(WebDriver webDriver) {
        log( "Trying to navigate back." );
    }

    public void afterNavigateBack(WebDriver webDriver) {
        log( "Navigated back successfully." );
    }

    public void beforeNavigateForward(WebDriver webDriver) {
        log( "Trying to navigate forward." );
    }

    public void afterNavigateForward(WebDriver webDriver) {
        log( "Navigated forward successfully." );
    }

    public void beforeNavigateRefresh(WebDriver webDriver) {
        log( "Trying to refresh." );
    }

    public void afterNavigateRefresh(WebDriver webDriver) {
        log( "The page is refreshed successfully" );
    }

    public void beforeFindBy(By by, WebElement webElement, WebDriver webDriver) {
        log( "Trying to find: " + getElement( webElement ) + " by: " + by.toString() );
    }

    public void afterFindBy(By by, WebElement webElement, WebDriver webDriver) {
        log( "Founds successfully" );
    }

    public void beforeClickOn(WebElement webElement, WebDriver webDriver) {
        log( "Trying to click on " + getElement( webElement ) );
    }

    public void afterClickOn(WebElement webElement, WebDriver webDriver) {
        log( "Clicked successfully" );
    }

    public void beforeChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        log( "Trying to change value of " + getElement( webElement ) + " on " + Arrays.toString( charSequences ) );
    }

    public void afterChangeValueOf(WebElement webElement, WebDriver webDriver, CharSequence[] charSequences) {
        log( getElement( webElement ) + " changed on " + Arrays.toString( charSequences ) + " successfully" );
    }

    public void beforeScript(String s, WebDriver webDriver) {
        log( "Trying to execute script " + s );
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