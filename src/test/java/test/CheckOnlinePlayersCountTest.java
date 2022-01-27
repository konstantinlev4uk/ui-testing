package test;

import aquality.selenium.browser.AqualityServices;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.AboutPage;
import page.HomePage;

public class CheckOnlinePlayersCountTest extends BaseTest {

    @Test
    public void checkOnlinePlayersCountTest() {

        homePage.changeLanguageNew(configData.getProperty("languageEn"), configData.getProperty("languageLink"));

        Assert.assertTrue(homePage.state().isDisplayed(), "HomePage is not loaded");
        AqualityServices.getBrowser().goTo(pageProperties.getProperty("about"));
        AboutPage aboutPage = new AboutPage();
        Assert.assertTrue(aboutPage.state().isDisplayed(), "AboutPage is not loaded");

        Assert.assertTrue(aboutPage.getOnlinePlayingNow() < aboutPage.getOnlineTotal(), "Players now more than total online");

        aboutPage.clickStoreButton();
        Assert.assertTrue(new HomePage().state().isDisplayed(), "new HomePage is not loaded");
    }
}
