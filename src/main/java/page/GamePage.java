package page;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class GamePage extends Form {

    private ILabel gameNameLabel = AqualityServices.getElementFactory().getLabel(By.id("appHubAppName"), "Game name label");
    private ILabel gamePriceLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//div[@class='game_area_purchase_game']//div[@class='game_purchase_price price' or @class='discount_final_price']"), "Game price label");
    private ILabel releaseDateLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//div[@class='date']"), "Game release date label");


    public GamePage() {
        super(By.xpath("//div[@class='game_page_background game']"), "Steam game page");
    }

    public String getGameName() {
        return gameNameLabel.getText();
    }

    public String getGameReleasedDate() {
        return releaseDateLabel.getText();
    }

    public String getGamePrice() {
        return gamePriceLabel.getText().replaceAll("USD", "").trim();
    }
}
