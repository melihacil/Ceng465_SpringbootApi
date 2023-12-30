package com.distributed.MusicAppProject.MusicUsers.MusicObjects;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaylistRepo extends JpaRepository<Playlist, String> {


    String[ ]findByPlaylistName(String playlistName);

    boolean existsByUsernameAndPlaylistName(String username, String playlistName);

    Optional<Playlist> getByPlaylistName(String playlistName);

    @Transactional
    void deleteByUsernameAndPlaylistName(String username, String playlistName);
}
