package dev.local.domain.policies;

import dev.local.domain.OrderItem;
import lombok.Builder;
import lombok.Value;

/**
 * ProductPolicy
 */
@Value
@Builder
public class OrderItemPolicy {
  private OrderItem item;
  private BasePolicy policy;
}
