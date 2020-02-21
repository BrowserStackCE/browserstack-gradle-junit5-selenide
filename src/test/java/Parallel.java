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
public class Parallel extends BrowserStackTest{

    final int i = 2;
    @RepeatedTest(i)
    public void repeatedTestMethod1(RepetitionInfo repetitionInfo){
        open("http://www.google.com");
        $(By.name("q")).setValue("BrowserStack").pressEnter();
        sleep(2000);
    }
    @RepeatedTest(i)
    public void repeatedTestMethod2(RepetitionInfo repetitionInfo){
        open("http://www.google.com");
        $(By.name("q")).setValue("BrowserStack").pressEnter();
        sleep(2000);
    }
}