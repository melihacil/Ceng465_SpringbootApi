package com.distributed.MusicAppProject.Services;

import com.distributed.MusicAppProject.MusicUsers.AppUsers.AppUser;
import com.distributed.MusicAppProject.MusicUsers.MusicObjects.FavoriteRepo;
import com.distributed.MusicAppProject.MusicUsers.MusicObjects.FavoriteTrack;
import com.distributed.MusicAppProject.MusicUsers.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final UserRepo userRepository;
    private final FavoriteRepo favoriteRepository;



    public String saveFavorite(int trackId, String trackName, String username) {
        Optional<AppUser> user = userRepository.findByUsername(username);
        if (user.isPresent() && trackName != null) {
            if (!favoriteRepository.existsByUsernameAndTrackName(username, trackName)) {
                FavoriteTrack favouriteMovie = FavoriteTrack
                        .builder()
                        .trackId(trackId)
                        .username(username)
                        .trackName(trackName)
                        .build();
                favoriteRepository.save(favouriteMovie);
                return "Favorite track saved";
            } else {
                return "Favorite Track already saved";
            }
        }
        return "User or Track can not found";
    }

    public String deleteFavorite(int id, String username) {
        Optional<AppUser> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            if (favoriteRepository.existsByTrackId(id)) {
                favoriteRepository.deleteByTrackIdAndUsername(id, username);
                return "Favourite deleted";
            }
            return "Favourite can not found";
        }
        return "User can not found";
    }

    public List<FavoriteTrack> getAllFavorites(String username) {
        Optional<AppUser> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return favoriteRepository.findAllByUsername(username);
        } else {
            return null;
        }
    }
}
