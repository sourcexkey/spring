package aspects;

import entity.User;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import services.DiscountService;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class DiscountAspect {
    private Map<User, Integer> counter = new HashMap<>();

    @AfterReturning(pointcut = "execution(* services.DiscountService.DiscountStrategy.execute(..)) && args(user,..)", returning = "discountSize")
    public void totalDiscounts(User user, float discountSize) {
        if (discountSize != 0.) {
            Integer amount = counter.getOrDefault(user, 0);
            counter.put(user, ++amount);
        }
    }

    public int getDiscountsAmountByUser(User user) {
        return counter.getOrDefault(user, 0);
    }

    public int getTotalDiscounts() {
        return counter.values().stream().mapToInt(Integer::intValue).sum();
    }
}
