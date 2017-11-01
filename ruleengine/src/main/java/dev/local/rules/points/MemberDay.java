package dev.local.rules.points;

import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.api.Facts;

import dev.local.domain.Customer;
import dev.local.domain.OrderItem;
import dev.local.domain.enumerations.MembershipType;
import dev.local.domain.policies.BasePolicy;
import dev.local.domain.policies.OrderItemPolicy;
import dev.local.domain.policies.OrderPolicy;
import dev.local.domain.policies.PointsChange;
import lombok.extern.java.Log;

import java.util.Calendar;
import java.util.stream.IntStream;

/**
 * MemberDay is a rule for setting the points for specific date
 * In those days, the points gained should be double
 */
@Log
@Rule(name="MemberDay", description="The rule to double member points")
public class MemberDay {

  private final int[] numOfDay = {1, 15, 25};
  private final float pointRatio = 2f;

  @Condition
  public boolean when(@Fact("OrderItem") OrderItem item, @Fact("Customer") Customer customer) {
    Calendar cal = Calendar.getInstance();
    int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
    return IntStream.of(numOfDay).anyMatch(x -> x == dayOfMonth) &&
      customer.getMembership().getType() != MembershipType.NONE;
  }

  @Action
  public void applyPointChange(Facts facts) {
    OrderPolicy policy = (OrderPolicy) facts.get("OrderPolicy");
    OrderItem item = (OrderItem) facts.get("OrderItem");
    final float original = item.getSalePrice() * item.getAmount();
    final float change = original * pointRatio;
    PointsChange pointsChange = PointsChange.builder()
      .original(original)
      .change(change)
      .build();
    BasePolicy basePolicy =
      BasePolicy.builder()
        .pointsChange(pointsChange)
        .build();
    policy.getItemPolicies().put(
      item.getId(), OrderItemPolicy.builder()
        .item(item)
        .policy(basePolicy)
        .build());
    log.info(item.getId() + ": " + basePolicy.getPointsChange().toString());
  }

  @Priority
  public int getPriority() {
      return 2;
  }

}
