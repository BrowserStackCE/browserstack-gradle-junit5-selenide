import com.browserstack.local.Local;

import java.io.FileReader;
import java.net.URL;
import java.util.*;
import com.codeborne.selenide.WebDriverRunner;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;


public class BrowserStackTest {
    public WebDriver driver;
    private Local bsLocal;

    private static JSONObject config;
    public Map<String, String> envCapabilities;
    public Map<String, String> commonCapabilities;
    public DesiredCapabilities capabilities;

    @BeforeEach
    public void setUp(TestInfo testInfo,RepetitionInfo repetitionInfo) throws Exception {

        if (System.getProperty("config") != null) {
            //System.out.println("BSTest:" + System.getProperty("config"));

            JSONParser parser = new JSONParser();
            config = (JSONObject) parser.parse(new FileReader("src/test/resources/conf/" + System.getProperty("config") + ".conf.json"));
        }
        //System.out.println(repetitionInfo.getCurrentRepetition()+"/"+ repetitionInfo.getTotalRepetitions());
        int taskID = repetitionInfo.getCurrentRepetition();

        JSONArray envs = (JSONArray) config.get("environments");
        capabilities = new DesiredCapabilities();
        envCapabilities = (Map<String, String>) envs.get((taskID-1));

        Iterator it = envCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
        }

        commonCapabilities = (Map<String, String>) config.get("capabilities");
        it = commonCapabilities.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (capabilities.getCapability(pair.getKey().toString()) == null) {
                capabilities.setCapability(pair.getKey().toString(), pair.getValue().toString());
            }
        }

        String username = System.getenv("BROWSERSTACK_USERNAME");
        if (username == null) {
            username = (String) config.get("user");
        }

        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
        if (accessKey == null) {
            accessKey = (String) config.get("key");
        }

        if (capabilities.getCapability("browserstack.local") != null && capabilities.getCapability("browserstack.local") == "true") {
            bsLocal = new Local();
            HashMap<String, String> bsLocalArgs = new HashMap<String, String>();
            bsLocalArgs.put("key", accessKey);
            //bsLocalArgs.put("force", "true");
            bsLocal.start(bsLocalArgs);
            System.out.println(bsLocal.isRunning());
        }
        driver = new RemoteWebDriver(new URL("http://" + username + ":" + accessKey + "@" + config.get("server") + "/wd/hub"), capabilities);
        WebDriverRunner.setWebDriver(driver);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public void tearDown() throws Exception {
        WebDriverRunner.closeWebDriver();
        if(bsLocal != null) {
            bsLocal.stop();
        }
    }

    public void markTestStatus(String status, String reason){
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \""+status+"\", \"reason\": \""+reason+"\"}}");
    }
}
