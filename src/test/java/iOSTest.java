import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class iOSTest {

    private static final By helloWorld = By.xpath("//XCUIElementTypeStaticText[@name=\"HELLO WORLD! \"]");
    private static final By randomNumber = By.xpath("//XCUIElementTypeOther[@name=\"MakeRandomNumberCheckbox\"]");

    private static final String platformName="iOS";
    private static final String platformVersion="16.2";
    private static final String deviceName= "iPhone 14";
    private static final String automationName="XCUITest";
    private AppiumDriver driver;
    DesiredCapabilities capabilities;

    @BeforeAll
    public void setup() throws IOException {
        File classpathRoot = new File(System.getProperty("user.dir")+"/src/test/resources/apps");
        File app = new File(classpathRoot.getCanonicalPath(), "HelloWorldiOS.app");
        capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,deviceName );
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
        capabilities.setCapability(MobileCapabilityType.APP, app);
        driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), capabilities);
    }

    @Test
    public void generateRandomNumber() {
        WebDriverWait dynamicWait =new WebDriverWait(driver,30);
        dynamicWait.until(ExpectedConditions.presenceOfElementLocated(helloWorld));

        String actualText=  driver.findElement(helloWorld).getText();

        Assertions.assertEquals("HELLO WORLD! ", actualText);

        driver.findElement(randomNumber).click();
    }

    @AfterAll
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }
}
