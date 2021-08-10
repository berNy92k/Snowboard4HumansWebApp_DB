package pl.snowboard4humans.dto;

public abstract class AbstractIdDto extends AbstractDto {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
