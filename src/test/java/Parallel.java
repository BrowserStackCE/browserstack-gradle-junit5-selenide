import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;


@Execution(ExecutionMode.CONCURRENT)
public class Parallel extends BrowserStackTest{

    final int i = 2;
    @RepeatedTest(i)
    public void repeatedTestMethod1(RepetitionInfo repetitionInfo){
        open("http://www.google.com");
        try{
            driver.findElement(By.id("KByQx")).click();
            driver.findElement(By.id("L2AGLb")).click();
        }catch (Exception e){
            System.out.println("Modal does not exist");
        }
        $(By.name("q")).setValue("BrowserStack").pressEnter();
        if(driver.getTitle().equals("BrowserStack - Google Search"))
            markTestStatus("passed","Expected Title");
        else
            markTestStatus("failed","Unexpected Title");
    }
    @RepeatedTest(i)
    public void repeatedTestMethod2(RepetitionInfo repetitionInfo){
        open("http://www.google.com");
        try{
            driver.findElement(By.id("KByQx")).click();
            driver.findElement(By.id("L2AGLb")).click();
        }catch (Exception e){
            System.out.println("Modal does not exist");
        }
        $(By.name("q")).setValue("Avacado").pressEnter();
        if(driver.getTitle().equals("Avacado - Google Search"))
            markTestStatus("passed","Expected Title");
        else
            markTestStatus("failed","Unexpected Title");
    }
}