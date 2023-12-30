package com.distributed.MusicAppProject.Controller;

import api.deezer.exceptions.DeezerException;
import com.distributed.MusicAppProject.DataObject.Requests.Favorite_Request;
import com.distributed.MusicAppProject.DataObject.Response;
import com.distributed.MusicAppProject.MusicUsers.MusicObjects.FavoriteTrack;
import com.distributed.MusicAppProject.Services.FavoriteService;
import com.distributed.MusicAppProject.Services.Interfaces.APIService;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("app/favorites")
public class FavoriteController {
    private final FavoriteService favoriteService;
    private final APIService apiService;

    // Add track to fav list
    @PostMapping("/addFavorite")
    public Response<String> addFavorite(@RequestBody Favorite_Request favorite_request) {
        try {
            String track = apiService.getTrackData(favorite_request.getTrackId()).getTitle();
            String response = favoriteService.saveFavorite(
                    favorite_request.getTrackId(),
                    track,
                    favorite_request.getUsername());
            if (response == null) {
                return new Response<>(false, "An unkown error occured", null);
            } else {
                return new Response<>(true, "success", response);
            }
        } catch (JSONException | DeezerException e) {
            System.out.println("Can't find track! - DEEZER API");
            //throw new RuntimeException(e);
        }
        return null;
    }

    // get all fav list
    @PostMapping("/allFavorites")
    public Response<List<FavoriteTrack>> getAllFavorites(@RequestBody Favorite_Request favorite_request) {
        List<FavoriteTrack> response = favoriteService.getAllFavorites(favorite_request.getUsername());
        if (response == null) {
            return new Response<>(false, "Favorites has not been listed", null);
        }
        return new Response<>(true, "success", response);
    }
    // Delete track from fav list
    @DeleteMapping("/deleteFavorite")
    public Response<String> deleteFavorite(@RequestBody Favorite_Request favorite_request){
        String response = favoriteService.deleteFavorite(favorite_request.getTrackId(), favorite_request.getUsername());
        if (response == null) {
            return new Response<>(false, "An unkown error occured", null);
        }
        return new Response<>(true, "success", response);
    }


}
