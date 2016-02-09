package services.discounts;

import org.joda.time.DateTime;

import entity.Session;
import entity.User;
import services.DiscountService;

public class BirthdayDiscount extends DiscountService.DiscountStrategy {

    public BirthdayDiscount(float discountAmount) {
        super(discountAmount);
    }

    @Override
    public float execute(User user, Session session) {
        return user.getBirthDay().equals(DateTime.now().withMillisOfDay(0))
               ? super.getDiscountAmount() : 0;
    }
}
