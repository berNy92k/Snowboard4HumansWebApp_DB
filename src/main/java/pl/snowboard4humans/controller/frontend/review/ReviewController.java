package pl.snowboard4humans.controller.frontend.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.snowboard4humans.model.Review;
import pl.snowboard4humans.service.frontend.ReviewService;

@Controller
@RequestMapping(value = "/homepage/reviews")
public class ReviewController {

  private final ReviewService reviewService;

  @Autowired
  public ReviewController(final ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  @GetMapping(value = "preCreateReview")
  public String addNewReviewScreen(final Model model,
                                   @RequestParam(value = "eqId") final int equipmentId) {
    return reviewService.preCreateReview(model, equipmentId);
  }

  @PostMapping
  public String addNewReview(final Model model,
                             @ModelAttribute final Review review) {
    return reviewService.createReview(model, review);
  }

}
