package pl.snowboard4humans.enums;

public enum CategoryEnum {
    SKIS(1),
    SKISHOES(2),
    SKIPOLES(3),
    SKIHELMETS(4),
    SKIGOGGLES(5),
    SKIGLOVES(6),
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
