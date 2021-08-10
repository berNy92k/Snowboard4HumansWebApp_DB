package pl.snowboard4humans.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.snowboard4humans.constants.ConstantsAdminENG;
import pl.snowboard4humans.constants.ConstantsPL;
import pl.snowboard4humans.dto.MsgAndListDto;
import pl.snowboard4humans.model.Customer;
import pl.snowboard4humans.model.Equipment;
import pl.snowboard4humans.model.Review;
import pl.snowboard4humans.repository.CustomerRepo;
import pl.snowboard4humans.repository.EquipmentRepo;
import pl.snowboard4humans.repository.ReviewRepo;
import pl.snowboard4humans.service.SuperService;
import pl.snowboard4humans.utils.Utils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewAdminService extends SuperService {

    private final ReviewRepo reviewRepo;
    private final EquipmentRepo equipmentRepo;
    private final CustomerRepo customerRepo;

    @Autowired
    public ReviewAdminService(ReviewRepo reviewRepo,
                              EquipmentRepo equipmentRepo,
                              CustomerRepo customerRepo) {
        this.reviewRepo = reviewRepo;
        this.equipmentRepo = equipmentRepo;
        this.customerRepo = customerRepo;
    }

    public MsgAndListDto<Review> getReviews() {
        final List<Review> reviewList = reviewRepo.findAll();

        final String message;
        if (Utils.isEmpty(reviewList)) {
            message = ConstantsAdminENG.LACK_OF_REVIEW_IN_DB;
        } else {
            message = ConstantsAdminENG.LIST_OF_REVIEW_ADMIN;
        }

        return new MsgAndListDto<>(message, reviewList);
    }

    public MsgAndListDto<Review> getAddNewReviewScreen() {
        final Review review = new Review();
        final Equipment equipment = new Equipment();
        final Customer customer = new Customer();
        review.setEquipment(equipment);
        review.setCustomer(customer);

        return new MsgAndListDto<>(ConstantsAdminENG.CREATE_MODE_FILL_FIELDS_REVIEW_ADMIN, Collections.singletonList(review));
    }

    public MsgAndListDto<Review> addOrUpdateReview(final Review review) {
        final Integer reviewId = review.getId();

        if (reviewId == null) {
            review.setReviewTime(new Date());
        } else {
            final Review reviewById = reviewRepo.findReviewById(reviewId);
            review.setReviewTime(reviewById.getReviewTime());
        }

        final Review reviewSaved = reviewRepo.save(review);
        final String message;
        if (reviewId == null && reviewSaved != null && reviewSaved.getId() != null) {
            message = ConstantsAdminENG.NEW_REVIEW_WAS_CREATED;
        } else if (reviewId != null && reviewSaved != null && reviewSaved.getId() != null) {
            message = ConstantsAdminENG.REVIEW_WAS_UPDATED;
        } else if (reviewId == null && reviewSaved == null) {
            message = ConstantsAdminENG.NEW_REVIEW_WAS_NOT_CREATED;
        } else {
            message = ConstantsAdminENG.REVIEW_WAS_NOT_UPDATED;
        }

        return new MsgAndListDto<>(message, reviewRepo.findAll());
    }

    public MsgAndListDto<Review> getEditReviewScreen(final int userId) {
        final Optional<Review> reviewOptional = reviewRepo.findById(userId);
        final Review review = reviewOptional.orElseGet(Review::new);

        return new MsgAndListDto<>(ConstantsAdminENG.UPDATE_MODE_CHANGE_FIELDS_REVIEW_ADMIN, Collections.singletonList(review));
    }

    public MsgAndListDto<Review> getViewReviewScreen(final int reviewId) {
        final Optional<Review> customerOptional = reviewRepo.findById(reviewId);
        final Review review = customerOptional.orElseGet(Review::new);

        return new MsgAndListDto<>(ConstantsPL.EMPTY_MESSAGE, Collections.singletonList(review));
    }

    public MsgAndListDto<Review> deleteReview(final int reviewId) {
        final Optional<Review> reviewOptional = reviewRepo.findById(reviewId);

        String message;
        if (reviewOptional.isPresent()) {
            reviewRepo.deleteById(reviewId);
            message = ConstantsAdminENG.REVIEW_WAS_DELETED;
        } else {
            message = ConstantsAdminENG.COULD_NOT_FIND_REVIEW_BY_ID + ConstantsAdminENG.DELETED_BY_ANOTHER_REVIEW_ADMIN;
        }

        final List<Review> reviewList = reviewRepo.findAll();

        // if reviewList list is empty then message change
        if (Utils.isEmpty(reviewList)) {
            message = ConstantsAdminENG.LACK_OF_REVIEW_IN_DB;
        }

        return new MsgAndListDto<>(message, reviewList);
    }
}
