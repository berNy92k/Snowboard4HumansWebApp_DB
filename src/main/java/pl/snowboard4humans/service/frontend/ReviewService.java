package pl.snowboard4humans.service.frontend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.snowboard4humans.constants.ConstantsFrontendPL;
import pl.snowboard4humans.model.Customer;
import pl.snowboard4humans.model.Equipment;
import pl.snowboard4humans.model.Review;
import pl.snowboard4humans.repository.CustomerRepo;
import pl.snowboard4humans.repository.EquipmentRepo;
import pl.snowboard4humans.repository.ReviewRepo;
import pl.snowboard4humans.service.SuperService;

import java.util.Date;

@Service
public class ReviewService extends SuperService {

    private final ReviewRepo reviewRepo;
    private final EquipmentRepo equipmentRepo;
    private final CustomerRepo customerRepo;

    @Autowired
    public ReviewService(ReviewRepo reviewRepo,
                         EquipmentRepo equipmentRepo,
                         CustomerRepo customerRepo) {

        this.reviewRepo = reviewRepo;
        this.equipmentRepo = equipmentRepo;
        this.customerRepo = customerRepo;
    }

    public String preCreateReview(Model model,
                                  Integer equipmentId) {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        Equipment equipment = equipmentRepo.getOne(equipmentId);

        Review review = new Review();
        review.setEquipment(equipment);
        review.setCustomerEmail(username);

        model.addAttribute("review", review);

        return ConstantsFrontendPL.REVIEW_HOMEPAGE_CREATE_URL;
    }

    public String createReview(Model model,
                               Review reviewData) {
        Equipment equipment = reviewData.getEquipment();
        String customerEmail = reviewData.getCustomerEmail();

        Customer customer = customerRepo.findCustomerByEmail(customerEmail);

        reviewData.setCustomer(customer);
        reviewData.setReviewTime(new Date());

        reviewRepo.save(reviewData);

        model.addAttribute("equipment", equipment);
        Integer equipmentId = reviewData.getEquipment().getId();

        return (ConstantsFrontendPL.EQUIPMENT_VIEW_URL_CONTROLLER + "?id=" + equipmentId);
    }

}
