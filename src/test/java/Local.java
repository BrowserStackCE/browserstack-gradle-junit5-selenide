import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import org.junit.jupiter.api.RepeatedTest;


import static com.codeborne.selenide.Selenide.*;
import java.util.regex.Pattern;

public class Local extends BrowserStackTest {

    @RepeatedTest(1)
    public void test() throws Exception {
        open("http://localhost:45691/check");
        sleep(2000);
    }
}
