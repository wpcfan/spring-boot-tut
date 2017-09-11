package dev.local.services

import dev.local.domain.Tenant
import dev.local.domain.TenantInfo

class TenantServiceImpl : TenantService {
    override fun verifyContact(mobile: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addTenantInfo(name: String, address: String): Tenant {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addTenantLicense(id: String, tenantInfo: TenantInfo): Tenant {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}