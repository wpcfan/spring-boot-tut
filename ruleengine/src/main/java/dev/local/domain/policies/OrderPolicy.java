package dev.local.domain.policies;

import dev.local.domain.Order;
import java.util.Map;
import lombok.Builder;
import lombok.Value;

/**
 * OrderPolicy
 */
@Value
@Builder
public class OrderPolicy {
  private Order order;
  private BasePolicy policy;
  private Map<String, OrderItemPolicy> itemPolicies;
}
