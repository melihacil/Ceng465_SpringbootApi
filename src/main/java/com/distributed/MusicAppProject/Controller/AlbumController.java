package com.distributed.MusicAppProject.Controller;

import com.distributed.MusicAppProject.MusicUsers.MusicObjects.Album;
import com.distributed.MusicAppProject.MusicUsers.MusicObjects.Albums;
import com.distributed.MusicAppProject.Services.Interfaces.APIService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/albums/details/{albumId}")
    public String getSelectedAlbum(@PathVariable("albumId") Integer albumId, Model model) {
        if (albumId == null) {
            return "error-custom";
        } else {

            Album currentAlbum = new Album();
            try {
                currentAlbum = APIService.getAlbumByAlbumId(albumId);
            } catch (Exception e) {
                e.printStackTrace();
            }

            model.addAttribute("currentAlbum", currentAlbum);
            return "album_details";
        }
    }
//
//    @GetMapping("/reloadAlbums")
//    public String albumsReturn(@ModelAttribute("albums") Albums albums, @ModelAttribute("currentArtistId") Integer artistId, Model model) {
//        if (albums == null) {
//            return "error-custom";
//        }
//        final String s = "redirect:/albums/{" + artistId + "}";
//        return s;
//    }

    /**
     * @return all the albums associated with this artist ID
     */
    @GetMapping("/albums/{artistId}")
    public String getAllAlbums(@PathVariable("artistId") Integer artistId, Model model) {
        if (artistId == null) {
            return "error-custom";
        } else {
            Albums albums = new Albums();
            try {
                albums = albumsRequest(artistId);
            } catch (Exception e) {
//                log.info("Cause: [ " + e.getCause() + " ]  Message: [" + e.getMessage() + " ]");
                e.printStackTrace();

                System.out.println("Cause: [ " + e.getCause() + " ]  Message: [" + e.getMessage() + " ]");
            }
            model.addAttribute("albums", albums);
            return "albums";
        }
    }

    private Albums albumsRequest(Integer id) throws Exception {
        Albums albums = null;
        try {
            albums = APIService.getAlbumsByArtistId(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (albums != null)
            return albums;
        else
            throw new Exception("No Albums Found for this Artist");
    }
}
