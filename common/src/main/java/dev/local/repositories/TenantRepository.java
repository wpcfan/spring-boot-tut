package dev.local.repositories;

import dev.local.domain.Tenant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TenantRepository extends MongoRepository<Tenant, String>{
}
