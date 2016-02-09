package services;

import entity.Session;
import entity.User;

public interface DiscountService {

    float getDiscount(User user, Session session);

    abstract class DiscountStrategy {

        final float discountAmount;

        public DiscountStrategy(float discountAmount) {
            this.discountAmount = discountAmount;
        }

        public abstract float execute(User user, Session session);

        public float getDiscountAmount() {
            return discountAmount;
        }
    }
}