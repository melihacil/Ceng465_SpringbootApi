package com.distributed.MusicAppProject.MusicUsers.MusicObjects;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface FavoriteRepo  extends JpaRepository<FavoriteTrack, String> {

    boolean existsByUsernameAndTrackName(String username, String trackName);

    //  find all by username
    List<FavoriteTrack> findAllByUsername(String username);

    boolean existsByTrackId(int trackId);

    @Transactional
    void deleteByMovieIdAndUsername(int trackId, String username);
}
