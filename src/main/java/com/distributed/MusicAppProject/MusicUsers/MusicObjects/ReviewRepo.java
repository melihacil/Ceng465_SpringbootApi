package com.distributed.MusicAppProject.MusicUsers.MusicObjects;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review, String> {


    // Repo functions
    boolean existsByUsernameAndTrackName(String username, String trackname);

    @Transactional
    void deleteByUsernameAndTrackName(String username, String trackname);
}
