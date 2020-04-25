package pl.snowboard4humans.enums;

public enum CategoryPLEnum {
    SKIS("NARTY"),
    SKISHOES("BUTY NARCISRSKIE"),
    SKIPOLES("KIJKI NARCISRSKIE"),
    SKIHELMETS("KASKI NARCISRSKIE"),
    SKIGOGGLES("GOGLE NARCISRSKIE"),
    SKIGLOVES("RĘKAWICE NARCISRSKIE"),
    THERMOACTIVECLOTHING("ODZIEŻ TERMOAKTYWANA"),
    ZERO("BRAK");

    private String category;

    CategoryPLEnum(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
