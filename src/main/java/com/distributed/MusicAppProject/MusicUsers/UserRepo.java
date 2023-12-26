package com.distributed.MusicAppProject.MusicUsers;

import com.distributed.MusicAppProject.MusicUsers.AppUsers.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<AppUser, String> {

    Optional<AppUser> findByUsername(String username);

    boolean existsByUsername(String username);


}