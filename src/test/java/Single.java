import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;
import java.util.regex.Pattern;

public class Single extends BrowserStackTest {

    @RepeatedTest(1)
    public void test() throws Exception {
        open("http://www.google.com");
        $(By.name("q")).setValue("BrowserStack").pressEnter();
        sleep(2000);
    }
}
