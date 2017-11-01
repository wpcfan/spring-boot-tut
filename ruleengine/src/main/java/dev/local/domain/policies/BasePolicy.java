package dev.local.domain.policies;

import java.util.List;
import lombok.Builder;
import lombok.Value;
import lombok.experimental.Wither;

/**
 * BasePolicy
 */
@Value
@Builder
@Wither
public class BasePolicy {
  private PriceChange priceChange;
  private List<Giveaway> giveaways;
  private PointsChange pointsChange;
  private List<Coupon> coupons;
}
