package pl.snowboard4humans.dto;

import java.util.Date;

public class ReviewDto extends AbstractIdDto {

    private int rating;
    private String headline;
    private String comment;
    private Date reviewTime;
    private EquipmentDto equipment;
    private CustomerDto customer;

    private String customerEmail;

    public ReviewDto() {
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public EquipmentDto getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentDto equipment) {
        this.equipment = equipment;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
