package test;

import aquality.selenium.browser.AqualityServices;
import help.PropertyUtil;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.HomePage;

import java.util.Properties;

public class BaseTest {

    protected HomePage homePage;
    PropertyUtil helper;
    Properties pageProperties;
    Properties testData;
    Properties configData;

    @BeforeMethod
    protected void beforeMethod() {
        helper = new PropertyUtil();
        Properties propertiesDriver = helper.getProperties("driver.properties");
        pageProperties = helper.getProperties("page.properties");
        testData = helper.getProperties("testData.properties");
        configData=helper.getProperties("configData.properties");
        AqualityServices.getBrowser().goTo(pageProperties.getProperty("home"));
        AqualityServices.getBrowser().setWindowSize(Integer.parseInt(propertiesDriver.getProperty("widthWindow")),
                Integer.parseInt(propertiesDriver.getProperty("heightWindow")));
        homePage = new HomePage();
    }

    @AfterMethod
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }
}
