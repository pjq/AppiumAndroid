package me.pjq.appium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

import static org.junit.Assert.assertEquals;

/**
 * Created by pengjianqing on 9/25/14.
 */
public class AppiumTest {
    private AndroidDriver driver = null;

    @Before
    public void setup() {
        File appDir = new File("./app/build/outputs/apk/");
        File app = new File(appDir, "app-debug.apk");

        if (app.exists()){
            System.out.println("app exists");
        }

        DesiredCapabilities capabilities = DesiredCapabilities.android();
        capabilities.setCapability(CapabilityType.VERSION, "4.0");
        capabilities.setCapability(CapabilityType.PLATFORM, "Android");
        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", app.getAbsolutePath());
//        capabilities.setCapability(CapabilityType.APP_PACKAGE, "me.pjq.appiumandroid");
//        capabilities.setCapability(DesiredCapabilities.APP_ACTIVITY, "MyActivity");
//        capabilities.setCapability(CapabilityType.APP, app.getAbsolutePath());

        try {
//            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
    }

    @Test
    public void testAppInfo() throws Exception{
        String appString = driver.getAppStrings();
        assertEquals(appString, "asdf");
    }

    @Test
    public void appiumExampleTest() throws Exception {
        // find button with label or content-description "Button 1"
//        WebElement button=driver.findElement(By.id("section_label"));
        driver.findElementById("section_label");
        String appString = driver.getAppStrings();
        // click on button and start second Activity
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
