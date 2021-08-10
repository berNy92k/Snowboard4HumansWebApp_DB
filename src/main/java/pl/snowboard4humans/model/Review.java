package pl.snowboard4humans.model;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "review")
public class Review extends AbstractModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Integer id;
    @NotNull
    @Column(name = "rating")
    private int rating;
    @NotNull
    @Column(name = "headline")
    private String headline;
    @NotNull
    @Column(name = "comment")
    private String comment;
    @NotNull
    @Column(name = "review_time")
    private Date reviewTime;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Transient
    private String customerEmail;

    public Review() {
    }

    public Review(int rating, String headline, String comment, Date reviewTime,
                  Equipment equipment, Customer customer) {
        this.rating = rating;
        this.headline = headline;
        this.comment = comment;
        this.reviewTime = reviewTime;
        this.equipment = equipment;
        this.customer = customer;
    }


    public Review(Integer reviewId, int rating, String headline, String comment,
                  Date reviewTime, Equipment equipment, Customer customer) {
        this.id = reviewId;
        this.rating = rating;
        this.headline = headline;
        this.comment = comment;
        this.reviewTime = reviewTime;
        this.equipment = equipment;
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer reviewId) {
        this.id = reviewId;
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

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

}
