package dev.local.repositories

import dev.local.domain.Tenant
import org.springframework.data.mongodb.repository.MongoRepository

interface TenantRepository : MongoRepository<Tenant, String>