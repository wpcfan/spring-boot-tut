package dev.local.services

import com.avos.avoscloud.*
import dev.local.domain.Address
import dev.local.domain.Tenant
import dev.local.domain.TenantInfo
import dev.local.repositories.TenantRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TenantServiceImpl
    @Autowired
    constructor(internal val repo: TenantRepository) : TenantService {

    override fun verifySMSCode(code: String, mobile: String) {
        AVOSCloud.verifySMSCode(code, mobile)
    }

    override fun requestSMSCode(mobile: String) {
        AVOSCloud.requestSMSCode(mobile, "register", null, 1)
    }

    override fun addTenantInfo(name: String, address: Address, mobile: String): Tenant {
        return repo.insert(Tenant(name, mobile, address))
    }

    override fun addTenantLicense(id: String, tenantInfo: TenantInfo): Tenant {
        val tanent = repo.findOne(id)
        tanent.info = tenantInfo
        return repo.save(tanent)
    }
}