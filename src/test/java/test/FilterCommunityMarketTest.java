package test;

import aquality.selenium.browser.AqualityServices;
import form.SearchCommunityMarketForm;
import model.GameFilter;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.CommunityMarketPage;
import page.HeroPage;

import java.util.List;
import java.util.Locale;

public class FilterCommunityMarketTest extends BaseTest {

    @Test
    public void checkFilterCommunityMarketTest() {

     homePage.changeLanguageNew(configData.getProperty("languageEn"), configData.getProperty("languageLink"));

        Assert.assertTrue(homePage.state().isDisplayed(), "HomePage is not loaded");
        AqualityServices.getBrowser().goTo(pageProperties.getProperty("communitymarket"));

        CommunityMarketPage communityMarketPage = new CommunityMarketPage();
        Assert.assertTrue(communityMarketPage.state().isDisplayed(), "Community MarketPage is not loaded");

        SearchCommunityMarketForm searchCommunityMarketForm = communityMarketPage.openSearchCommunityMarketForm();
        Assert.assertTrue(searchCommunityMarketForm.state().isDisplayed(), "Search Community Market Form is not loaded");

        GameFilter gameFilter = new GameFilter(testData.getProperty("hero"), testData.getProperty("rarity"), testData.getProperty("search"), testData.getProperty("game"));
        searchCommunityMarketForm.searchByFilter(gameFilter);

        List<String> searchOptions = communityMarketPage.getSearchOptions();
        Assert.assertTrue(searchOptions.contains(testData.getProperty("game")), "Game not selected");
        Assert.assertTrue(searchOptions.contains(testData.getProperty("hero")), "Hero not selected");
        Assert.assertTrue(searchOptions.contains(testData.getProperty("rarity")), "Rarity not selected");

        int index = searchOptions.indexOf("\"golden\"");
        String searchInput = searchOptions.get(index).replaceAll("[^A-Za-z]+", "");
        Assert.assertEquals(searchInput, testData.getProperty("search"), "searchInput not equal");

        List<String> gameNames = communityMarketPage.getItemNames(5);
        for (int i = 0; i < 5; i++) {
            Assert.assertTrue(gameNames.get(i).toLowerCase(Locale.ROOT).
                    contains(("\"golden\"").replaceAll("[^A-Za-z]+", "").toLowerCase(Locale.ROOT)), "Game does not contain golden");
        }

        int countBeforeDelete = communityMarketPage.getItemNames().size();
        communityMarketPage.deleteFilterOption(testData.getProperty("search"));
        int countAfterDelete = communityMarketPage.getItemNames().size();
        Assert.assertTrue(countBeforeDelete != countAfterDelete, "Filter option not delete");

        String expectedName = communityMarketPage.getItemNames().get(0);
        String expectedGameName = communityMarketPage.getGameNames().get(0);
        HeroPage heroPage = communityMarketPage.openHeroPageByNumber(0);
        String actualName = heroPage.getItemName();
        String actualGameName = heroPage.getGameName();
        Assert.assertEquals(expectedName, actualName, "Hero name does not match");
        Assert.assertEquals(expectedGameName, actualGameName, "Game name does not match");
        Assert.assertTrue(heroPage.getHero().contains(testData.getProperty("hero")), "heroPage does not contain hero name");
        Assert.assertTrue(heroPage.getRarity().contains(testData.getProperty("rarity")), "heroPage does not contain hero rarity");
    }
}
