package pl.snowboard4humans.controller.admin.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.snowboard4humans.constants.ConstantsAdminENG;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.controller.frontend.SuperController;
import pl.snowboard4humans.dto.MsgAndListDto;
import pl.snowboard4humans.model.Review;
import pl.snowboard4humans.service.admin.CustomerAdminService;
import pl.snowboard4humans.service.admin.EquipmentAdminService;
import pl.snowboard4humans.service.admin.ReviewAdminService;

@Controller
@RequestMapping(value = "/admin/review")
public class ReviewAdminController extends SuperController {

    private final ReviewAdminService reviewAdminService;
    private final EquipmentAdminService equipmentAdminService;
    private final CustomerAdminService customerAdminService;

    @Autowired
    public ReviewAdminController(final ReviewAdminService reviewAdminService,
                                 final EquipmentAdminService equipmentAdminService,
                                 final CustomerAdminService customerAdminService) {
        this.reviewAdminService = reviewAdminService;
        this.equipmentAdminService = equipmentAdminService;
        this.customerAdminService = customerAdminService;
    }

    @GetMapping
    public String getReviews(final Model model) {
        final MsgAndListDto<Review> reviews = reviewAdminService.getReviews();

        return getRequestDispatcherWithDefaultMessage(model,
                reviews.getMessage(),
                ConstantsAdminENG.REVIEW_LIST_OBJECT,
                reviews.getListOfElements(),
                ConstantsAdminENG.REVIEW_LIST_URL);
    }

    @PostMapping
    public String getReviews(final Model model,
                             @ModelAttribute final Review review) {
        final MsgAndListDto<Review> reviews = reviewAdminService.addOrUpdateReview(review);

        return getRequestDispatcherWithDefaultMessage(model,
                reviews.getMessage(),
                ConstantsAdminENG.REVIEW_LIST_OBJECT,
                reviews.getListOfElements(),
                ConstantsAdminENG.REVIEW_LIST_URL);
    }

    @GetMapping(value = "review_create")
    public String addNewReviewScreen(final Model model) {
        model.addAttribute("equipmentList", equipmentAdminService.findAll());
        model.addAttribute("customerList", customerAdminService.findAll());

        final MsgAndListDto<Review> reviews = reviewAdminService.getAddNewReviewScreen();

        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsAdminENG.CREATE_MODE_FILL_FIELDS_REVIEW_ADMIN,
                ConstantsAdminENG.REVIEW_OBJECT,
                reviews.getListOfElements().get(0),
                ConstantsAdminENG.REVIEW_CREATE_URL);
    }

    @GetMapping(value = "editReview")
    public String editReviewScreen(final Model model,
                                   @RequestParam(value = "id") final int reviewId) {

        model.addAttribute("equipmentList", equipmentAdminService.findAll());
        model.addAttribute("customerList", customerAdminService.findAll());

        final MsgAndListDto<Review> reviews = reviewAdminService.getEditReviewScreen(reviewId);
        return getRequestDispatcherWithDefaultMessage(model,
                reviews.getMessage(),
                ConstantsAdminENG.REVIEW_OBJECT,
                reviews.getListOfElements().get(0),
                ConstantsAdminENG.REVIEW_CREATE_URL);
    }

    @GetMapping(value = "viewReview")
    public String editViewScreen(final Model model,
                                 @RequestParam(value = "id") final int reviewId) {
        final MsgAndListDto<Review> reviews = reviewAdminService.getViewReviewScreen(reviewId);

        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsPL.EMPTY_MESSAGE,
                ConstantsAdminENG.REVIEW_OBJECT,
                reviews.getListOfElements().get(0),
                ConstantsAdminENG.REVIEW_VIEW_URL);
    }

    @GetMapping(value = "deleteReview")
    public String deleteReview(final Model model,
                               @RequestParam(name = "id") final int reviewId) {
        final MsgAndListDto<Review> reviews = reviewAdminService.deleteReview(reviewId);

        return getRequestDispatcherWithDefaultMessage(model,
                reviews.getMessage(),
                ConstantsAdminENG.REVIEW_LIST_OBJECT,
                reviews.getListOfElements(),
                ConstantsAdminENG.REVIEW_LIST_URL);
    }

}
