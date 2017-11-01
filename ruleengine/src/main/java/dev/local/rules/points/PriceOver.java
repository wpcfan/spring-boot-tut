package dev.local.rules.points;

import dev.local.domain.Customer;
import dev.local.domain.Membership;
import dev.local.domain.OrderItem;
import dev.local.domain.enumerations.MembershipType;
import dev.local.domain.policies.BasePolicy;
import dev.local.domain.policies.OrderItemPolicy;
import dev.local.domain.policies.OrderPolicy;
import dev.local.domain.policies.PointsChange;
import lombok.extern.java.Log;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

/**
 * PriceOver
 */
@Log
@Rule(
  name="PriceOver",
  description="When price is greater than certain value, the points rate should be less")
public class PriceOver {

  private final float price = 2000f;
  private final float rateRatio = 0.6f;

  @Condition
  public boolean when(@Fact("OrderItem") OrderItem item, @Fact("Customer") Customer customer) {
    return item.getSalePrice() > price
      && customer.getMembership().getType() != MembershipType.NONE;
  }

  @Action
  public void applyPointChanges(Facts facts) {
    OrderPolicy policy = (OrderPolicy) facts.get("OrderPolicy");
    OrderItem item = (OrderItem) facts.get("OrderItem");
    float change = item.getSalePrice() * rateRatio;
    PointsChange pointsChange = PointsChange.builder().change(change).build();
    BasePolicy basePolicy = BasePolicy.builder().pointsChange(pointsChange).build();

    policy.getItemPolicies().put(
      item.getId(), OrderItemPolicy.builder()
        .item(item)
        .policy(basePolicy)
        .build());
    log.info(item.getId() + ": " + basePolicy.getPointsChange().toString());
  }

  @Priority
  public int getPriority() {
      return 1;
  }

}
