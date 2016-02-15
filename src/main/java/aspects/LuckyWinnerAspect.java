package aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import entity.User;

import java.math.BigDecimal;
import java.util.Random;

@Aspect
public class LuckyWinnerAspect {

    private static final Logger LOG = LoggerFactory.getLogger(LuckyWinnerAspect.class);

    private Random random = new Random();

    @Around("execution(* services.BookingService.getTicketPrice(..)) && args(..,user)")
    private BigDecimal luckyTicket(ProceedingJoinPoint pjp, User user) throws Throwable {
        BigDecimal price = random.nextBoolean() ? BigDecimal.ZERO : (BigDecimal) pjp.proceed();
        if (price.equals(BigDecimal.ZERO)) {
            LOG.info("LuckyUser {}", user);
        }
        return price;
    }
}
