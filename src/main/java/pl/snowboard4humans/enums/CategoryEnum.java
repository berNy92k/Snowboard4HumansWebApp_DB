package pl.snowboard4humans.enums;

public enum CategoryEnum {
    SNOWBOARDS(1),
    SNOWBOARDSHOES(2),
    SNOWBOARDBINDINGS(3),
    SNOWBOARDGLOVES(4),
    SNOWBOARDGOGGLES(5),
    SNOWBOARDHELMETS(6),
    THERMOACTIVECLOTHING(7),
    ZERO(0);

    private int category;

    CategoryEnum(int category) {
        this.category = category;
    }

    public int getCategory() {
        return category;
    }
}
