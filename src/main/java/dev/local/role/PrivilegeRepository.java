package dev.local.role;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends MongoRepository<Privilege, String> {

}
