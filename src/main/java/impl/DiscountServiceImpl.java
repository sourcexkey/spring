package impl;

import entity.Session;
import entity.User;
import services.DiscountService;

import java.util.List;

public class DiscountServiceImpl implements DiscountService {

    private List<DiscountStrategy> discounts;

    public DiscountServiceImpl(List<DiscountStrategy> discounts) {
        this.discounts = discounts;
    }

    @Override
    public float getDiscount(User user, Session session) {
        return discounts.stream().map(d -> d.execute(user, session)).max(Float::compare).get();
    }
}
