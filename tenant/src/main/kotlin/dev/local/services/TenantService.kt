package dev.local.services

import dev.local.domain.Tenant
import dev.local.domain.TenantInfo

interface TenantService {
    fun verifyContact(mobile: String): String
    fun addTenantInfo(name: String, address: String): Tenant
    fun addTenantLicense(id: String, tenantInfo: TenantInfo): Tenant
}