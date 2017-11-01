package dev.local.domain.policies;

import dev.local.domain.Product;
import lombok.Builder;
import lombok.Value;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Coupon
 */
@Value
@Builder
public class Coupon {
  private String code;
  private float amount;
  private Date dueDate;
  private List<Product> excludes = new ArrayList<>();
  private List<Product> includes = new ArrayList<>();
}
