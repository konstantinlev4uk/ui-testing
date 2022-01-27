package page;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HeroPage extends Form {

    private ILabel gameNameLabel = AqualityServices.getElementFactory().getLabel(By.id("largeiteminfo_game_name"), "Game name");
    private ILabel rarityLabel = AqualityServices.getElementFactory().getLabel(By.id("largeiteminfo_item_type"), "Hero Rarity");
    private ILabel heroLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//div[@id='largeiteminfo_item_descriptors']//div[1]"), "Hero name");
    private ILabel itemNameLabel = AqualityServices.getElementFactory().getLabel(By.id("largeiteminfo_item_name"), "Item name");


    public HeroPage() {
        super(By.xpath("//div[@class='market_listing_iteminfo']"), "Steam hero page");
    }

    public String getGameName() {
        return gameNameLabel.getText();
    }

    public String getRarity() {
        return rarityLabel.getText();
    }

    public String getHero() {
        return heroLabel.getText();
    }

    public String getItemName() {
        return itemNameLabel.getText();
    }
}
