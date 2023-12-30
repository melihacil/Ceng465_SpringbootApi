package com.distributed.MusicAppProject.Services;

import api.deezer.exceptions.DeezerException;
import com.distributed.MusicAppProject.MusicUsers.AppUsers.AppUser;
import com.distributed.MusicAppProject.MusicUsers.MusicObjects.Playlist;
import com.distributed.MusicAppProject.MusicUsers.MusicObjects.PlaylistRepo;
import com.distributed.MusicAppProject.MusicUsers.UserRepo;
import com.distributed.MusicAppProject.Services.Interfaces.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PlaylistImpl implements PlaylistService {

    private final UserRepo userRepository;
    private final PlaylistRepo playlistRepo;
    //private final DeezerApi apiService;
    private final APIServiceImpl apiService;

    @Override
    public String addTrackToPlaylist(String trackName, String username, String playlistName) throws DeezerException {


        String track = apiService.PlayListSearchTrack(trackName);
        Optional<AppUser> user = userRepository.findByUsername(username);
       // String playlist = playlistRepo.findByPlaylistName(playlistName)[0];


        if (user.isPresent() && trackName != null) {
            if (playlistRepo.existsByUsernameAndPlaylistName(username, playlistName)) {

                Playlist oldPlaylist = playlistRepo.getByPlaylistName(playlistName).get();
                String playlistTracks = oldPlaylist.getPlaylistTracks();
//                Playlist oldPlaylist = Playlist
//                        .builder()
//                        .username(username)
//                        .playlistTracks(playlistTracks)
//                        .playlistName(playlistName)
//                        .build();
                playlistTracks += "," + track;
                Playlist newplaylist = Playlist
                        .builder()
                        .id(oldPlaylist.getId())
                        .username(username)
                        .playlistTracks(playlistTracks)
                        .playlistName(playlistName)
                        .build();
//                playlistRepo.delete(oldPlaylist);
                 playlistRepo.save(newplaylist);
                return "Added track to a playlist";
            } else {
                return "No playlist!!";
            }
        }
        return "User or Track can not found";

    }

    @Override
    public String createPlaylist(String username, String playlistName) {
        Optional<AppUser> user = userRepository.findByUsername(username);
        if (user.isPresent() && playlistName != null) {
            if (!playlistRepo.existsByUsernameAndPlaylistName(username, playlistName)) {
                Playlist newplaylist = Playlist
                        .builder()
                        .username(username)
                        .playlistTracks("-")
                        .playlistName(playlistName)
                        .build();
                playlistRepo.save(newplaylist);
                return "Saved Playlist";
            }
            else {
                return "Playlist Already Saved!";
            }
        }
        return null;
    }


    @Override
    public String deletePlaylist(String username, String playlistName) {
        Optional<AppUser> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            if (playlistRepo.existsByUsernameAndPlaylistName(username, playlistName)) {
                playlistRepo.deleteByUsernameAndPlaylistName(username, playlistName);
                return "Playlist deleted";
            }
            return "Playlist can not found";
        }
        return "User can not found";
    }

}
