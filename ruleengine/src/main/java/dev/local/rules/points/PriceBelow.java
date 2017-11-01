package dev.local.rules.points;

import dev.local.domain.OrderItem;
import org.jeasy.rules.annotation.Action;
import org.jeasy.rules.annotation.Condition;
import org.jeasy.rules.annotation.Fact;
import org.jeasy.rules.annotation.Priority;
import org.jeasy.rules.annotation.Rule;

/**
 * PriceBelow
 */
@Rule(name="PriceBelow")
public class PriceBelow {
  private final float price = 2000f;

  @Condition
  public boolean when(@Fact("OrderItem") OrderItem item) {
    return item.getSalePrice() <= price;
  }

  @Action
  public void applyPointChange() {

  }

  @Priority
  public int getPriority() {
      return 3;
  }
}
