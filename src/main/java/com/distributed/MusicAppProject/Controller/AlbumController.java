package com.distributed.MusicAppProject.Controller;

import api.deezer.exceptions.DeezerException;
import api.deezer.objects.Album;
import com.distributed.MusicAppProject.DataObject.Response;
import com.distributed.MusicAppProject.Services.Interfaces.APIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("app/v1/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final APIService APIService;


    /**
     * The Title.
     */
    //@Value(value = "${app.title.main}")
    public String title;

//    @Autowired
//    public AlbumController(APIService APIService) {
//        this.APIService = APIService;
//
//    }

    @ModelAttribute("title")
    public String popTitle(){
        return title;
    }


    @GetMapping("distApp/albums/{albumName}")
    public Response GetAlbumByID(@PathVariable("albumName") String albumName) throws IOException {
        Album album = null;
        if (albumName == null) {
            return Response
                    .builder()
                    .status(false)
                    ._message("Album ID Null")
                    ._payload(null)
                    .build();
        }
        else {
            try {
                album = APIService.getAlbumData(albumName);
            }
            catch (DeezerException e) {
                System.out.println("Album not found check album String");
                return Response
                        .builder()
                        .status(false)
                        ._message("Album not found check album String")
                        ._payload(null)
                        .build();
            }
            Response response = new Response<>(true, "Tracks found", album);
            System.out.println("Album FOUND = " + album.getTitle()+ " Artist = " + album.getContributors());
            return Response
                    .builder()
                    .status(true)
                    ._message("Album found" )
                    ._payload(response)
                    .build();
        }

    }


}
