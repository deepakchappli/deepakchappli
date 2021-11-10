package Day1;

import com.applitools.eyes.*;
import com.applitools.eyes.locators.VisualLocator;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class Program1 {
    private static Eyes eyes;
    public static <let> void main(String[] args) {


       // System.setProperty("webdriver.chrome.driver", "C:\\Users\\deepa\\Downloads\\chromedriver.exe");

            WebDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://demo.applitools.com/");
            VisualGridRunner runner = new VisualGridRunner(10);
            Configuration config = new Configuration();
            config.setAppName("TestApp");
            config.setTestName("Test ufg");
            // You can get your api key from the Applitools dashboard
            config.setApiKey("eAcV2Ms8Z1Lno7dlbQL9es9QpxWCkMKL34RlXRLNPik110");

            // create a new batch info instance and set it to the configuration
            config.setBatch(new BatchInfo("Ultrafast Batch"));

            // Add browsers with different viewports
            config.addBrowser(800, 600, BrowserType.CHROME);
            config.addBrowser(700, 500, BrowserType.FIREFOX);
            config.addBrowser(1600, 1200, BrowserType.IE_11);
            config.addBrowser(1024, 768, BrowserType.EDGE_CHROMIUM);
            config.addBrowser(800, 600, BrowserType.SAFARI);

            // Add mobile emulation devices in Portrait mode
            config.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT);
            config.addDeviceEmulation(DeviceName.Pixel_2, ScreenOrientation.PORTRAIT);

            // Set the configuration object to eyes
            //eyes.setConfiguration(config);


            //ClassicRunner runner1 = new ClassicRunner();
            eyes = new Eyes(runner);
            eyes.setMatchLevel(MatchLevel.STRICT);
            eyes.setBaselineEnvName("desktop browser");
            eyes.setBranchName("Compare branches");
            eyes.setConfiguration(config);
        /* webDriver = new Builder() {
             @Override
             public Object build() {
                 return null;
             }
         }.withCapabilies(Capabilities.chrome()).build();*/




        try {
            //eyes.setApiKey("eAcV2Ms8Z1Lno7dlbQL9es9QpxWCkMKL34RlXRLNPik110");
            eyes.open(driver);

            eyes.check("App Window", Target.window().fully());
            //Visual locator
            List<String> names_A = Arrays.asList(new String[]{"log-in"});

            Map<String, List<Region>> locRegions5 = eyes.locate(VisualLocator.names(Collections.singletonList("log-in")).first());

			System.out.println("Change made");


            System.out.println(driver.getTitle());
            eyes.closeAsync();
        }
        catch(Exception ex)
        {
            eyes.abortAsync();
            System.out.println("Exception: "+ex);
        }
        finally
        {
            driver.quit();
            TestResultsSummary allTestResults = runner.getAllTestResults(true);
            System.out.println(allTestResults);
        }

    }
}


