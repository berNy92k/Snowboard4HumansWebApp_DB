package pl.snowboard4humans.controller.frontend.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.snowboard4humans.model.Review;
import pl.snowboard4humans.service.frontend.ReviewServices;

@Controller
@RequestMapping(value = "/homepage/reviews")
public class ReviewController {

    private ReviewServices reviewServices;

    @Autowired
    public ReviewController(ReviewServices reviewServices) {
        this.reviewServices = reviewServices;
    }

    @GetMapping(value = "preCreateReview")
    public String addNewReviewScreen(Model model,
                                     @RequestParam(value = "eqId") int equipmentId) {
        return reviewServices.preCreateReview(model, equipmentId);
    }

    @PostMapping
    public String addNewReview(Model model,
                               @ModelAttribute Review review) {
        return reviewServices.createReview(model, review);
    }

}
