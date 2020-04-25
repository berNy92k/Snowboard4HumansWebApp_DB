package pl.snowboard4humans.enums;

public enum SexEnum {
    ALL("all"),
    MAN("MAN"),
    WOMAN("WOMAN"),
    CHILD("CHILD"),
    ZERO("0");

    private String sex;

    SexEnum(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }
}
