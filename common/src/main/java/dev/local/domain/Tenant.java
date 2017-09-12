package dev.local.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Tenant {
    @Id
    private String id;
    @Indexed(unique=true, direction= IndexDirection.DESCENDING, dropDups=true)
    @NonNull
    private String name;
    @NonNull
    private String registerMobile;
    @NonNull
    private Address address;
    private boolean enabled;
    private TenantInfo info;
}
