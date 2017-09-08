package dev.local.repositories;

import dev.local.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(final String username);
    Page<User> findByUsernameLike(final String exp, Pageable pageable);
}
