package dev.local;

import dev.local.domain.Customer;
import dev.local.domain.Membership;
import dev.local.domain.Staff;
import dev.local.rules.MembershipDiscount;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Facts facts = new Facts();
        facts.put(
                "customer",
                new Customer(
                        1L,
                        "zhangsan",
                        "zs001",
                        new Membership(1, "yyy",  100)));
        try {
            facts.put("startDate", formatter.parse("2017-10-01"));
            facts.put("endDate", formatter.parse("2017-10-16"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        facts.put("staff", new Staff("xxx", "wangwu","ww"));
        Rules rules = new Rules();
        rules.register(new MembershipDiscount());
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules, facts);
    }
}
