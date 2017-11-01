package dev.local.domain.policies;

import dev.local.domain.Product;
import lombok.Builder;
import lombok.Value;

/**
 * Bonus
 */
@Value
@Builder
public class Giveaway {
  private Product product;
  private float amount;
}
