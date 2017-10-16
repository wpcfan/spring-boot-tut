package dev.local.rules;

import dev.local.domain.Customer;
import dev.local.domain.Staff;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

import java.util.Date;

@Rule(
        name = "MembershipDiscount",
        description = "The general discount rule applied to members")
public class MembershipDiscount {

    @Condition
    public boolean when(
            @Fact("customer") Customer customer,
            @Fact("startDate") Date startDate,
            @Fact("endDate") Date endDate) {
        Date date = new Date();
        return customer.getMembership().getType() > 0
                && startDate.before(date)
                && endDate.after(date);
    }

    @Action
    public void applyDiscount(Facts facts) {

        System.out.println("Discount applied");
        // can add/remove/modify facts
    }
}
