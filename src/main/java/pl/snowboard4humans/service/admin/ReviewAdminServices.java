package pl.snowboard4humans.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.snowboard4humans.constants.ConstantsAdminENG;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.model.Customer;
import pl.snowboard4humans.model.Equipment;
import pl.snowboard4humans.model.Review;
import pl.snowboard4humans.repository.CustomerRepo;
import pl.snowboard4humans.repository.EquipmentRepo;
import pl.snowboard4humans.repository.ReviewRepo;
import pl.snowboard4humans.service.SuperService;
import pl.snowboard4humans.utils.Utils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewAdminServices extends SuperService {
    private ReviewRepo reviewRepo;
    private EquipmentRepo equipmentRepo;
    private CustomerRepo customerRepo;

    @Autowired
    public ReviewAdminServices(ReviewRepo reviewRepo,
                               EquipmentRepo equipmentRepo,
                               CustomerRepo customerRepo) {
        this.reviewRepo = reviewRepo;
        this.equipmentRepo = equipmentRepo;
        this.customerRepo = customerRepo;
    }

    public String getReviews(Model model) {
        String message = ConstantsAdminENG.LIST_OF_REVIEW_ADMIN;

        List<Review> reviewList = reviewRepo.findAll();

        // if reviewList list is empty then message change
        if (Utils.isEmpty(reviewList)) {
            message = ConstantsAdminENG.LACK_OF_REVIEW_IN_DB;
        }

        return getRequestDispatcherWithDefaultMessage(model,
                message,
                ConstantsAdminENG.REVIEW_LIST_OBJECT,
                reviewList,
                ConstantsAdminENG.REVIEW_LIST_URL);
    }

    public String getAddNewReviewScreen(Model model) {
        List<Equipment> equipmentList = equipmentRepo.findAll();
        List<Customer> customerList = customerRepo.findAll();

        model.addAttribute("equipmentList", equipmentList);
        model.addAttribute("customerList", customerList);

        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsAdminENG.CREATE_MODE_FILL_FIELDS_REVIEW_ADMIN,
                ConstantsAdminENG.REVIEW_OBJECT,
                new Review(),
                ConstantsAdminENG.REVIEW_CREATE_URL);
    }

    public String addOrUpdateReview(Model model,
                                    Review review) {
        String message;

        Integer reviewId = review.getId();

        if (reviewId == null) {
            review.setReviewTime(new Date());
        } else {
            Review reviewById = reviewRepo.findReviewById(reviewId);
            review.setReviewTime(reviewById.getReviewTime());
        }

        Review reviewSaved = reviewRepo.save(review);

        List<Review> userList = reviewRepo.findAll();
        if (reviewId == null && reviewSaved != null && reviewSaved.getId() != null) {
            message = ConstantsAdminENG.NEW_REVIEW_WAS_CREATED;
        } else if (reviewId != null && reviewSaved != null && reviewSaved.getId() != null) {
            message = ConstantsAdminENG.REVIEW_WAS_UPDATED;
        } else if (reviewId == null && reviewSaved == null) {
            message = ConstantsAdminENG.NEW_REVIEW_WAS_NOT_CREATED;
        } else {
            message = ConstantsAdminENG.REVIEW_WAS_NOT_UPDATED;
        }

        return getRequestDispatcherWithDefaultMessage(model,
                message,
                ConstantsAdminENG.REVIEW_LIST_OBJECT,
                userList,
                ConstantsAdminENG.REVIEW_LIST_URL);
    }

    public String getEditReviewScreen(Model model,
                                      int userId) {
        Optional<Review> reviewOptional = reviewRepo.findById(userId);
        Review review = reviewOptional.orElseGet(Review::new);

        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsAdminENG.UPDATE_MODE_CHANGE_FIELDS_REVIEW_ADMIN,
                ConstantsAdminENG.REVIEW_OBJECT,
                review,
                ConstantsAdminENG.REVIEW_CREATE_URL);
    }

    public String getViewCustomerScreen(Model model,
                                        int reviewId) {
        Optional<Review> customerOptional = reviewRepo.findById(reviewId);
        Review review = customerOptional.orElseGet(Review::new);

        return getRequestDispatcherWithDefaultMessage(model,
                ConstantsPL.EMPTY_MESSAGE,
                ConstantsAdminENG.REVIEW_OBJECT,
                review,
                ConstantsAdminENG.REVIEW_VIEW_URL);
    }

}
