package multiple;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static com.codeborne.selenide.Selenide.*;


@Execution(ExecutionMode.CONCURRENT)
public class ParallelWithCaps{

    public static final String USERNAME = System.getenv("BROWSERSTACK_USERNAME"); //String USERNAME = "BROWSERSTACK_USERNAME";
    public static final String AUTOMATE_KEY = System.getenv("BROWSERSTACK_ACCESS_KEY"); //String AUTOMATE_KEY = "BROWSERSTACK_ACCESS_KEY";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @Test
    public void test1() throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "75.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("name", "parallel_test");
        caps.setCapability("build", "browserstack-gradle-junit5-selenide");

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        WebDriverRunner.setWebDriver(driver);
        open("http://www.google.com");
        $(By.name("q")).setValue("BrowserStack").pressEnter();
        sleep(2000);
        WebDriverRunner.closeWebDriver();
    }
    @Test
    public void test2() throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "IE");
        caps.setCapability("browser_version", "11.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "8.1");
        caps.setCapability("name", "parallel_test");
        caps.setCapability("build", "browserstack-gradle-junit5-selenide");

        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        WebDriverRunner.setWebDriver(driver);
        open("http://www.google.com");
        $(By.name("q")).setValue("BrowserStack").pressEnter();
        sleep(2000);
        WebDriverRunner.closeWebDriver();
    }
}