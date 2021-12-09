import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
public class Single extends BrowserStackTest {

    @RepeatedTest(1)
    public void test() throws Exception {
        open("http://www.google.com");
        try{
            driver.findElement(By.id("KByQx")).click();
            driver.findElement(By.id("L2AGLb")).click();
        }catch (Exception e){
            System.out.println("Modal does not exist");
        }
        $(By.name("q")).setValue("BrowserStack");
        $(By.name("q")).pressEnter();

        if(driver.getTitle().equals("BrowserStack - Google Search"))
            markTestStatus("passed","Expected Title");
        else
            markTestStatus("failed","Unexpected Title");
    }
}
