package dev.local.domain.policies;

import lombok.Builder;
import lombok.Value;

/**
 * PriceChange
 */
@Value
@Builder
public class PriceChange {
  private float orginal;
  private float change;
}
