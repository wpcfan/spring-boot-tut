package dev.local.domain;

import lombok.Data;

@Data
public class Product {
    private String id;
    private String name;
    private String domainName;
    private String model;
    private String type;
    private String code;
}
