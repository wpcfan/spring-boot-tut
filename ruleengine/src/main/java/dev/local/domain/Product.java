package dev.local.domain;

import dev.local.domain.enumerations.DosageForm;
import dev.local.domain.enumerations.PackageUnit;
import java.io.Serializable;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Product implements Serializable {

    private final static long serialVersionUID = 1L;
    /**
     * 唯一标识 ID
     */
    private String id;
    /**
     * 通用名
     */
    private String generalName;
    // /**
    //  * 商品名
    //  */
    // private String brandName;
    // /**
    //  * 特定领域名称：药品来讲就是化学名
    //  */
    // private String domainName;
    // /**
    //  * 包装单位
    //  */
    // private PackageUnit packageUnit;
    // /**
    //  * 剂型
    //  */
    // private DosageForm dosageForm;
    /**
     * 产品代码
     */
    private String code;
}
