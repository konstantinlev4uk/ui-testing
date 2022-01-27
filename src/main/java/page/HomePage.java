package page;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.IElementFactory;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HomePage extends Form {

    private String currentLanguageOption = "//span[text()='%s']";
    private IButton languagePulldownButton = AqualityServices.getElementFactory().getButton(By.id("language_pulldown"), "Language Pulldown Button");
    private String languageDropdown = "//div[@id='language_dropdown']//a[contains(text(),'%s')]";
    private String languageLabel = "//span[text()='%s']";

    public HomePage() {
        super(By.id("store_nav_area"), "Steam home page");
    }

    public void changeLanguageNew(String languageLink, String languageOptionAdd) {
        IElementFactory elementFactory = AqualityServices.getElementFactory();
        if (
                elementFactory.findElements(By.xpath(String.format(currentLanguageOption, languageOptionAdd)), ElementType.LINK).size() == 0
        ) {
            languagePulldownButton.click();
            elementFactory.getLink(By.xpath(String.format(languageDropdown, languageLink)), "Option language link").click();
            elementFactory.getLabel(By.xpath(String.format(languageLabel, languageOptionAdd)), "Option language label", ElementState.DISPLAYED).state().waitForClickable();
        }
    }
}
