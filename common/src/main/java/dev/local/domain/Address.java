package dev.local.domain;

import lombok.Data;

@Data
public class Address {
    private String province;
    private String city;
    private String district;
    private String street;
}
