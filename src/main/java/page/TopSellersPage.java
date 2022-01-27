package page;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.Label;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import model.Game;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.List;

public class TopSellersPage extends Form {

    private final String rightMenuBlock = "//div[contains(@class,'search_collapse')][.//div[text()='%s']]";
    private String expandButton = "//div[text()='%s']";
    private String resultRows = "//div[@id='search_resultsRows']//a[%s]";
    private String textCheckBox = "//div[contains(@class,'tab_filter_control_row')][.//span[text()='%s']]";
    private ILabel opacityElementLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//div[@id='search_result_container'][contains(@style,'opa')]"), "Opacity");
    private ILabel searchResultLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//div[@class='search_results_count']"), "Search result label");
    private IButton allTagsButton = AqualityServices.getElementFactory().getButton(By.xpath("//div[@class='block search_collapse_block']//a[@class='see_all_expander']"), "See to all Button");
    private By gameName = By.xpath("//span[@class='title']");
    private By price = By.xpath("//div[contains(@class,'search_price') and not (contains(@class,'combined'))]");
    private By releaseDate = By.xpath("//div[contains(@class,'released')]");
    private By checkAttribute = By.xpath("//span[contains(@class,'checkbox')]");
    private By count = By.xpath("//span[contains(@class,'count')]");

    public TopSellersPage() {
        super(By.xpath("//h2[@class='pageheader full']"), "Top sellers page");
    }

    public void selectOptionInRightMenu(String blockName, String option) {
        expandRightMenuBlock(blockName);
        IButton button = getCheckBox(option).findChildElement(checkAttribute, ElementType.BUTTON);
        button.state().waitForNotExist(Duration.ofSeconds(5));
        button.click();
        opacityElementLabel.state().waitForNotExist(Duration.ofSeconds(5));
    }

    private void expandRightMenuBlock(String blockName) {
        IButton button = AqualityServices.getElementFactory().getButton(By.xpath(String.format(rightMenuBlock, blockName)), "Expand menu button");
        if (button.getAttribute("class").contains("collapsed")) {
            AqualityServices.getElementFactory().getButton(By.xpath(String.format(expandButton, blockName)), "Expand button").click();
        }
    }

    public GamePage openGamePageByIndex(int index) {
        AqualityServices.getElementFactory().getLabel(By.xpath(String.format(resultRows, index)), "Game line").click();
        return new GamePage();
    }

    public Game getGameByIndex(int index) {
        ILabel gameLabel = AqualityServices.getElementFactory().getLabel(By.xpath(String.format(resultRows, index)), "Game line");
        String name = gameLabel.findChildElement(gameName, ElementType.LABEL).getText();
        String releaseDateGame = gameLabel.findChildElement(releaseDate, ElementType.LABEL).getText();
        Label priceLabel = gameLabel.findChildElement(price, ElementType.LABEL);
        List<Label> elems = priceLabel.findChildElements(By.xpath(".//*"), ElementType.LABEL);
        String text = priceLabel.getText().replaceAll("\\$", "!!!");
        for (Label elem : elems) {
            String textToReplace = elem.getText().replaceAll("\\$", "!!!");
            if (text.contains(textToReplace)) {
                text = text.replaceAll(textToReplace, "");
            }
        }
        String price = text.trim().replaceAll("!!!", "\\$");
        return new Game(name, price, releaseDateGame);
    }

    public ILabel getCheckBox(String checkBoxLabel) {
        return AqualityServices.getElementFactory().getLabel(By.xpath(String.format(textCheckBox, checkBoxLabel)), "CheckBox right menu");
    }

    public int getCount(String checkBoxLabel) {
        return Integer.parseInt(getCheckBox(checkBoxLabel).findChildElement(count, ElementType.LABEL).getText().replaceAll(" ", ""));
    }

    public boolean isCheckBoxChecked(String checkBoxLabel) {
        return getCheckBox(checkBoxLabel).getAttribute("class").contains("checked");
    }

    public int getSearchResultNumber() {
        return Integer.parseInt(StringUtils.substringBetween(getSearchResultString(), ":", ".").replaceAll("[^0-9]", ""));
    }

    private String getSearchResultString() {
        return searchResultLabel.getText();
    }

    public void expandAllTags() {
        allTagsButton.click();
    }
}
