package dev.local.taskmgr.repositories;

import dev.local.taskmgr.domain.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
    Page<Profile> findDistinctProfileByUsernameLikeOrNameLike(String username, String name, Pageable pageable);
    Profile findByUsername(String username);
}
