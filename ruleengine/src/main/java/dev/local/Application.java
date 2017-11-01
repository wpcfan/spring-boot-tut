package dev.local;

import dev.local.domain.Customer;
import dev.local.domain.Membership;
import dev.local.domain.Order;
import dev.local.domain.OrderItem;
import dev.local.domain.Product;
import dev.local.domain.Staff;
import dev.local.domain.enumerations.MembershipType;
import dev.local.domain.policies.BasePolicy;
import dev.local.domain.policies.OrderItemPolicy;
import dev.local.domain.policies.OrderPolicy;
import dev.local.rules.CombinedRule;
import dev.local.rules.points.MemberDay;
import dev.local.rules.points.PriceBelow;
import dev.local.rules.points.PriceOver;

import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Facts facts = new Facts();
        Product product = Product.builder()
            .code("SX13434234")
            .generalName("扑热息痛")
            .build();
        Staff staff = Staff.builder()
            .username("13898810892")
            .build();
        OrderItem item = OrderItem.builder()
            .id("0101023")
            .product(product)
            .amount(3)
            .salePrice(3500f)
            .soldBy(staff)
            .build();
        facts.put("OrderItem", item);

        Membership membership = Membership.builder()
            .type(MembershipType.PLATINUM)
            .pointsRemained(2000f)
            .code("xfadfd12314")
            .build();
        Customer customer = Customer.builder()
            .name("lisi")
            .membership(membership)
            .build();

        facts.put("Customer", customer);

        OrderItemPolicy itemPolicy1 = OrderItemPolicy.builder()
            .item(item)
            .policy(BasePolicy.builder().build())
            .build();
        Map<String, OrderItemPolicy> itemPolicies = new HashMap<>();
        itemPolicies.put(item.getId(), itemPolicy1);

        OrderPolicy policy = OrderPolicy.builder()
            .order(Order.builder().id("xxxx1312321412").build())
            .policy(BasePolicy.builder().build())
            .itemPolicies(itemPolicies)
            .build();
        facts.put("OrderPolicy", policy);
        Rules rules = new Rules();
        rules.register(new CombinedRule(new PriceBelow(), new MemberDay()));
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
    }
}
