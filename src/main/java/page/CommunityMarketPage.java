package page;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.Label;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.forms.Form;
import form.SearchCommunityMarketForm;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class CommunityMarketPage extends Form {

    private By searchResultOptions = By.xpath("//div[@class='market_search_results_header']//a[contains(@class,'search')]");
    private By resultNames = By.xpath("//div[@id='searchResultsRows']//a//span[contains(@id,'name')]");
    private By gameName = By.xpath("//div[@id='searchResultsRows']//a//span[contains(@class,'game_name')]");
    private IButton advancedButton = AqualityServices.getElementFactory().getButton(By.xpath("//div[@class='market_search_advanced_button']"), "Market search advanced button");
    private String deleteFilterOption = "//a[contains(text(),'%s')]//span[@class='removeIcon']";
    private String heroPageByNumber = "resultlink_%s";

    public CommunityMarketPage() {
        super(By.xpath("//div[@class='market_header_logo']"), "Steam community market page");
    }

    public SearchCommunityMarketForm openSearchCommunityMarketForm() {
        advancedButton.click();
        return new SearchCommunityMarketForm();
    }

    public List<String> getSearchOptions() {
        List<Label> searchOptions = AqualityServices.getElementFactory().findElements(searchResultOptions, ElementType.LABEL);
        List<String> listOptions = new ArrayList<>();
        for (Label i : searchOptions) {
            listOptions.add(i.getText().trim());
        }
        return listOptions;
    }

    public List<String> getItemNames(int resultCount) {
        List<Label> gameItemNames = AqualityServices.getElementFactory().findElements(resultNames, ElementType.LABEL);
        List<String> gameItemNamesString = new ArrayList<>();
        for (int i = 0; i < resultCount; i++) {
            gameItemNamesString.add(gameItemNames.get(i).getText().trim());
        }
        return gameItemNamesString;
    }

    public List<String> getItemNames() {
        List<Label> gameItemNames = AqualityServices.getElementFactory().findElements(resultNames, ElementType.LABEL);
        List<String> gameItemNamesString = new ArrayList<>();
        for (Label a : gameItemNames) {
            gameItemNamesString.add(a.getText().trim());
        }
        return gameItemNamesString;
    }

    public void deleteFilterOption(String option) {
        AqualityServices.getElementFactory().
                getButton(By.xpath(String.format(deleteFilterOption, option)), "Delete option").click();
    }

    public List<String> getGameNames() {
        List<Label> gameNames = AqualityServices.getElementFactory().findElements(gameName, ElementType.LABEL);
        List<String> gameNamesString = new ArrayList<>();
        for (Label a : gameNames) {
            gameNamesString.add(a.getText().trim());
        }
        return gameNamesString;
    }

    public HeroPage openHeroPageByNumber(int number) {
        AqualityServices.getElementFactory().getButton(By.id(String.format(heroPageByNumber, number)), "Open Hero button").click();
        return new HeroPage();
    }
}
