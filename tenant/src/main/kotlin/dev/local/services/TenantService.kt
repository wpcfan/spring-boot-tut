package dev.local.services

import dev.local.domain.Address
import dev.local.domain.Tenant
import dev.local.domain.TenantInfo

interface TenantService {
    fun requestSMSCode(mobile: String)
    fun verifySMSCode(code: String, mobile: String)
    fun addTenantInfo(name: String, address: Address, mobile: String): Tenant
    fun addTenantLicense(id: String, tenantInfo: TenantInfo): Tenant
}