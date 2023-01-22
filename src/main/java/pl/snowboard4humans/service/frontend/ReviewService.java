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
  public ReviewService(final ReviewRepo reviewRepo,
                       final EquipmentRepo equipmentRepo,
                       final CustomerRepo customerRepo) {
    this.reviewRepo = reviewRepo;
    this.equipmentRepo = equipmentRepo;
    this.customerRepo = customerRepo;
  }

  public String preCreateReview(final Model model, final Integer equipmentId) {
    final String username;
    final Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      username = ((UserDetails) principal).getUsername();
    } else {
      username = principal.toString();
    }

    final Equipment equipment = equipmentRepo.getOne(equipmentId);
    final Review review = Review.builder()
        .equipment(equipment)
        .customerEmail(username)
        .build();

    model.addAttribute("review", review);

    return ConstantsFrontendPL.REVIEW_HOMEPAGE_CREATE_URL;
  }

  public String createReview(final Model model,
                             final Review reviewData) {
    final Equipment equipment = reviewData.getEquipment();
    final String customerEmail = reviewData.getCustomerEmail();

    final Customer customer = customerRepo.findCustomerByEmail(customerEmail);

    reviewData.setCustomer(customer);
    reviewData.setReviewTime(new Date());

    reviewRepo.save(reviewData);

    model.addAttribute("equipment", equipment);

    return (ConstantsFrontendPL.EQUIPMENT_VIEW_URL_CONTROLLER + "?id=" + reviewData.getEquipment().getId());
  }

}
