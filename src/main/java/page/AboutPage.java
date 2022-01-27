package page;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AboutPage extends Form {

    private ILabel onlineTotalLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//div[@class='online_stat'][.//div[@class='online_stat_label gamers_online']]"), "Online total players");
    private ILabel onlinePlayingNowLabel = AqualityServices.getElementFactory().getLabel(By.xpath("//div[@class='online_stat'][.//div[@class='online_stat_label gamers_in_game']]"), "Online now players");
    private IButton storeButton = AqualityServices.getElementFactory().getButton(By.xpath("//div[@id='global_header']//a[contains(text(),'STORE')]"), "Store button");

    public AboutPage() {
        super(By.id("about_greeting"), "Steam about page");
    }

    public Integer getOnlineTotal() {
        return Integer.parseInt(onlineTotalLabel.getText().replaceAll("[^0-9]", ""));
    }

    public Integer getOnlinePlayingNow() {
        return Integer.parseInt(onlinePlayingNowLabel.getText().replaceAll("[^0-9]", ""));
    }

    public void clickStoreButton() {
        storeButton.click();
    }
}
