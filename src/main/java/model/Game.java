package model;

import java.util.Objects;

public class Game {

    private String gameName;
    private String price;
    private String releaseDate;

    public Game() {
    }

    public Game(String gameName, String price, String releaseDate) {
        this.gameName = gameName;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(gameName, game.gameName) && Objects.equals(price, game.price) && Objects.equals(releaseDate, game.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameName, price, releaseDate);
    }
}
