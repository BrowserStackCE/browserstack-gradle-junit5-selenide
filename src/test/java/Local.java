import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import org.junit.jupiter.api.RepeatedTest;

public class Local extends BrowserStackTest {

    @RepeatedTest(1)
    public void test() throws Exception {
        open("http://localhost:45691/check");
        if($("body").getText().contains("Up and running"))
            markTestStatus("passed","Expected Title");
        else
            markTestStatus("failed","Unexpected Title");
    }
}
