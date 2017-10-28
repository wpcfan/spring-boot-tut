package dev.local.auth.repositories;

import dev.local.auth.domain.Authority;
import dev.local.auth.domain.AuthorityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepo extends JpaRepository<Authority, AuthorityId>{
}
