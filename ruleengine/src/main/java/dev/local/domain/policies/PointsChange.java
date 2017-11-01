package dev.local.domain.policies;

import lombok.Builder;
import lombok.Value;

/**
 * PointsChange
 */
@Value
@Builder
public class PointsChange {
  private final float original;
  private final float change;
}
