package pl.snowboard4humans.controller.admin.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.snowboard4humans.model.Review;
import pl.snowboard4humans.service.admin.ReviewAdminServices;

@Controller
@RequestMapping(value = "/admin/review")
public class ReviewAdminController {

    ReviewAdminServices reviewAdminServices;

    @Autowired
    public ReviewAdminController(ReviewAdminServices reviewAdminServices) {
        this.reviewAdminServices = reviewAdminServices;
    }

    @GetMapping
    public String getReviews(Model model) {
        return reviewAdminServices.getReviews(model);
    }

    @PostMapping
    public String getReviews(Model model,
                             @ModelAttribute Review review) {
        return reviewAdminServices.addOrUpdateReview(model, review);
    }

    @GetMapping(value = "review_create")
    public String addNewReviewScreen(Model model) {
        return reviewAdminServices.getAddNewReviewScreen(model);
    }

    @GetMapping(value = "editReview")
    public String editReviewScreen(Model model,
                                   @RequestParam(value = "id") int reviewId) {
        return reviewAdminServices.getEditReviewScreen(model, reviewId);
    }

    @GetMapping(value = "viewReview")
    public String editViewScreen(Model model,
                                 @RequestParam(value = "id") int reviewId) {
        return reviewAdminServices.getViewReviewScreen(model, reviewId);
    }

    @GetMapping(value = "deleteReview")
    public String deleteReview(@RequestParam(name = "id") int reviewId,
                               Model model) {
        return reviewAdminServices.deleteReview(model, reviewId);
    }

}
