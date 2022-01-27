package test;

import aquality.selenium.browser.AqualityServices;
import model.Game;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.GamePage;
import page.TopSellersPage;

public class TorSellersOptionsTest extends BaseTest {

    @Test
    public void checkTorSellersOptionsTest() {

       homePage.changeLanguageNew(configData.getProperty("languageRu"), "язык");

        Assert.assertTrue(homePage.state().isDisplayed(), "HomePage is not loaded");
        AqualityServices.getBrowser().goTo(pageProperties.getProperty("topSellers"));

        TopSellersPage topSellersPage = new TopSellersPage();
        Assert.assertTrue(topSellersPage.state().isDisplayed(), "TopSellersPage is not loaded");

        topSellersPage.selectOptionInRightMenu("Операционная система", "SteamOS + Linux");
        Assert.assertTrue(topSellersPage.isCheckBoxChecked("SteamOS + Linux"), "checkbox not checked");

        topSellersPage.selectOptionInRightMenu("Количество игроков", "Кооператив (LAN)");
        Assert.assertTrue(topSellersPage.isCheckBoxChecked("Кооператив (LAN)"), "checkbox not checked");

        topSellersPage.expandAllTags();
        int expectedCount = topSellersPage.getCount("Экшен");
        topSellersPage.selectOptionInRightMenu("Метки", "Экшен");
        Assert.assertTrue(topSellersPage.isCheckBoxChecked("Экшен"), "checkbox not checked");
        int actualCount = topSellersPage.getSearchResultNumber();
        Assert.assertEquals(expectedCount, actualCount, "Wrong result");

        Game game = topSellersPage.getGameByIndex(1);
        String name = game.getGameName();
        String price = game.getPrice();
        String releasedDate = game.getReleaseDate();
        GamePage gamePage = topSellersPage.openGamePageByIndex(1);
        String actualName = gamePage.getGameName();
        String actualPrice = gamePage.getGamePrice();
        String actualReleasedDate = gamePage.getGameReleasedDate();
        Assert.assertEquals(actualName, name, "Names games does not match");
        Assert.assertEquals(actualPrice, price, "Price game does not match");
        Assert.assertEquals(actualReleasedDate, releasedDate, "Realease data game does not match");
    }
}
