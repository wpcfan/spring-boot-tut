package dev.local.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
class LegalEntity {
    @Id
    private String id;
    private String name;
    @Indexed(unique=true, direction= IndexDirection.DESCENDING, dropDups=true)
    private String idNo;
    private String idType;
    private byte[] idFront;
    private byte[] idBack;
}
