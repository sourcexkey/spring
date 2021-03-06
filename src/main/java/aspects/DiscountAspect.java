package aspects;

import entity.User;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Aspect
public class DiscountAspect {

    private Map<User, Map<String, Integer>>
            counter =
            new HashMap<>();

    @AfterReturning(pointcut = "execution(* services.DiscountService.DiscountStrategy.execute(..)) && args(user,..)", returning = "discountSize")
    public void totalDiscounts(JoinPoint jp, User user, float discountSize) {
        if (discountSize != 0.) {
            Map<String, Integer> discountsCounter = counter.getOrDefault(user, new HashMap<>());
            String discount = jp.getTarget().getClass().getSimpleName();
            Integer amount = discountsCounter.getOrDefault(discount, 0);
            discountsCounter.put(discount, ++amount);
            counter.putIfAbsent(user, discountsCounter);
        }
    }

    public Map<String, Integer> getDiscountsAmountByUser(User user) {
        return counter.getOrDefault(user, Collections.EMPTY_MAP);
    }

    public int getTotalDiscounts() {
        return counter.values().stream().mapToInt(
                discounts -> discounts.values().stream().mapToInt(Integer::intValue).sum()).sum();
    }
}
