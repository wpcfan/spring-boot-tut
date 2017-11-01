package dev.local.rules;

import org.jeasy.rules.annotation.Rule;
import org.jeasy.rules.core.CompositeRule;

/**
 * ComositeRule
 */
@Rule(name="CompositeRule", description="combine the ")
public class CombinedRule extends CompositeRule {

  public CombinedRule(Object... rules) {
    for (Object rule : rules) {
      addRule(rule);
    }
  }

  @Override
  public int getPriority() {
      return 0;
  }
}
