package com.distributed.MusicAppProject.Controller;


import api.deezer.exceptions.DeezerException;
import com.distributed.MusicAppProject.DataObject.Requests.Playlist_Request;
import com.distributed.MusicAppProject.DataObject.Response;
import com.distributed.MusicAppProject.Services.Interfaces.APIService;
import com.distributed.MusicAppProject.Services.Interfaces.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("app/playlist")
public class PlaylistController {

    private final APIService apiService;
    private final PlaylistService playlistService;

    @PostMapping("/createPlaylist")
    public Response<String> createPlaylist(@RequestBody Playlist_Request playlist_request){
        String response = playlistService.createPlaylist(playlist_request.getUsername(), playlist_request.getPlaylistName());
        if (response == null) {
            return new Response<>(false, "An error occurred", null);
        }
        return new Response<>(true, "success", response);
    }

    @PostMapping("/addTrackToPlaylist")
    public Response<String> addToPlaylist(@RequestBody Playlist_Request playlist_request){
        String response = null;
        try {
            response = playlistService.addTrackToPlaylist(playlist_request.getTrackName(), playlist_request.getUsername(), playlist_request.getPlaylistName());
        }
        catch (DeezerException e) { System.out.println("Track Not Found");
        }

        if (response == null) {
            return new Response<>(false, "An error occurred", null);
        }
        return new Response<>(true, "success", response);
    }

    @DeleteMapping("/deletePlaylist")
    public Response<String> deletePlaylist(@RequestBody Playlist_Request playlist_request){
        String response = playlistService.deletePlaylist(playlist_request.getUsername(), playlist_request.getPlaylistName());
        if (response == null) {
            return new Response<>(false, "An unknown error occurred", null);
        }
        return new Response<>(true, "success", response);
    }



}
