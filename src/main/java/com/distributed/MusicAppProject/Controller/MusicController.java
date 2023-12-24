package com.distributed.MusicAppProject.Controller;

import com.distributed.MusicAppProject.DataObject.Response;
import com.distributed.MusicAppProject.Services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("app/v1/music")
public class MusicController {

    @Autowired
    private final MusicService _service;

    public MusicController(MusicService _service) {
        this._service = _service;
    }

    @GetMapping("/{id}")
    public Response<String> GetMusicByID(@PathVariable int id) throws IOException {
        String musicName = "" ;//_service.GetMusicByID(id);

        if (musicName == null)
            return new Response<>(false, "Music Not Found!", null);

        return new Response<>(true, "Music = " + musicName + " is found!", musicName);


    }

    // get all movies
//    @GetMapping("/all")
//    public Response<List<UserMovie>> getAllMovies(@AuthenticationPrincipal User user) throws JSONException {
//        List<UserMovie> response = movieService.getAllMovies(user.getUsername());
//        if (response == null) {
//            return new BaseResponse<>(false, "Movies has not been listed", null);
//        }
//        return new BaseResponse<>(true, "success", response);
//    }


}
