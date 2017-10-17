package dev.local.repositories;

import dev.local.domain.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {
    Page<Profile> findDistinctProfileByUsernameLikeOrNameLike(String username, String name, Pageable pageable);
    Profile findByUsername(String username);
}
