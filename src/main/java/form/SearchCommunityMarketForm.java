package form;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IComboBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import model.GameFilter;
import org.openqa.selenium.By;
import page.CommunityMarketPage;

public class SearchCommunityMarketForm extends Form {

    private String rarityImmortalCheckBox = "//div[contains(@class,'filter_container')][.//span[text()='%s']]//input";
    private String optionGame = "//div[contains(@id,'app_option')]//span[text()='%s']";
    private IButton appOptionButton = AqualityServices.getElementFactory().getButton(By.id("market_advancedsearch_appselect_activeapp"), "Game Button");
    private IComboBox selectHeroDota2ComboBox = AqualityServices.getElementFactory().getComboBox(By.xpath("//select[@name='category_570_Hero[]']"), "Hero select");
    private ITextBox searchInputTextBox = AqualityServices.getElementFactory().getTextBox(By.id("advancedSearchBox"), "Search input");
    private IButton searchButton = AqualityServices.getElementFactory().getButton(By.xpath("//div[@class='btn_medium btn_green_white_innerfade']"), "Search button");

    public SearchCommunityMarketForm() {
        super(By.id("market_advancedsearch_dialog"), "Steam community market form");
    }

    private void selectGame(String gameName) {
        appOptionButton.click();
        AqualityServices.getElementFactory().getButton(By.xpath(String.format(optionGame, gameName)), "Select name game").click();
    }

    private void selectOptionHero(String nameHero) {
        selectHeroDota2ComboBox.selectByText(nameHero);
    }

    private void selectCheckBoxRarity(String rarity) {
        AqualityServices.getElementFactory().getCheckBox(By.xpath(String.format(rarityImmortalCheckBox, rarity)), "Rarity select checkbox").check();
    }

    private void inputSearch(String searchOption) {
        searchInputTextBox.type(searchOption);
    }

    public CommunityMarketPage searchByFilter(GameFilter gameFilter) {
        selectGame(gameFilter.getGameSelect());
        selectOptionHero(gameFilter.getHeroSelect());
        selectCheckBoxRarity(gameFilter.getRarityCheckbox());
        inputSearch(gameFilter.getSearchInput());
        searchButton.click();
        return new CommunityMarketPage();
    }
}
