package services.discounts;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import entity.Session;
import entity.User;
import services.DiscountService;
import services.TicketService;

public class EachTenTicketDiscount extends DiscountService.DiscountStrategy {

    private final int eachTicketDiscount;
    @Autowired
    private TicketService ticketService;

    public EachTenTicketDiscount(float discountAmount, int eachTicketDiscount) {
        super(discountAmount);
        this.eachTicketDiscount = eachTicketDiscount;
    }

    @Override
    public float execute(User user, Session session) {
        return ticketService.getBookedTickets(user).size() + 1 % eachTicketDiscount == 0
               ? super.getDiscountAmount() : 0;
    }
}
