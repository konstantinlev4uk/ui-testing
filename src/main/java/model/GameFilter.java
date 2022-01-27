package model;

import java.util.Objects;

public class GameFilter {

    private String heroSelect;
    private String rarityCheckbox;
    private String searchInput;
    private String gameSelect;

    public GameFilter(String heroSelect, String rarityCheckbox, String searchInput, String gameSelect) {
        this.heroSelect = heroSelect;
        this.rarityCheckbox = rarityCheckbox;
        this.searchInput = searchInput;
        this.gameSelect = gameSelect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameFilter that = (GameFilter) o;
        return Objects.equals(heroSelect, that.heroSelect) && Objects.equals(rarityCheckbox, that.rarityCheckbox) && Objects.equals(searchInput, that.searchInput) && Objects.equals(gameSelect, that.gameSelect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(heroSelect, rarityCheckbox, searchInput, gameSelect);
    }

    public void setHeroSelect(String heroSelect) {
        this.heroSelect = heroSelect;
    }

    public void setRarityCheckbox(String rarityCheckbox) {
        this.rarityCheckbox = rarityCheckbox;
    }

    public void setSearchInput(String searchInput) {
        this.searchInput = searchInput;
    }

    public void setGameSelect(String gameSelect) {
        this.gameSelect = gameSelect;
    }

    public String getHeroSelect() {
        return heroSelect;
    }

    public String getRarityCheckbox() {
        return rarityCheckbox;
    }

    public String getSearchInput() {
        return searchInput;
    }

    public String getGameSelect() {
        return gameSelect;
    }
}
