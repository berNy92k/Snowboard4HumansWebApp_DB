package pl.snowboard4humans.enums;

public enum CategoryPLEnum {
    SNOWBOARDS("DESKI SNOWBOARDOWE"),
    SNOWBOARDSHOES("BUTY SNOWBOARDOWE"),
    SNOWBOARDBINDINGS("WIĄZANIA SNOWBOARDOWE"),
    SNOWBOARDGLOVES("RĘKAWICE SNOWBOARDOWE"),
    SNOWBOARDGOGGLES("GOGLE SNOWBOARDOWE"),
    SNOWBOARDHELMETS("KASKI SNOWBOARDOWE"),
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
