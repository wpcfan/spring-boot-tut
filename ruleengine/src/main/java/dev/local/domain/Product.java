package dev.local.domain;

import lombok.Data;

@Data
public class Product {
    private String id;
    // 通用名
    private String generalName;
    // 商品名
    private String brandName;
    // 特定领域名称：药品来讲就是化学名
    private String domainName;
    // 包装单位
    private PackageUnit packageUnit;
    // 剂型
    private DosageForm dosageForm;
    private String code;
}
