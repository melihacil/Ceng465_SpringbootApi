package com.distributed.MusicAppProject.Controller;

import com.distributed.MusicAppProject.DataObject.DeezerSearch;
import com.distributed.MusicAppProject.MusicUsers.MusicObjects.Artists;
import com.distributed.MusicAppProject.Services.Interfaces.APIService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;


@RestController
@RequestMapping("app/v1/search")
@RequiredArgsConstructor
public class ArtistSearchController {
    /**
     * The Console Logger.
     */


    private final APIService apiService;


    /**
     * The Title.
     */

    public String title;



    @ModelAttribute("title")
    public String popTitle() {
        return title;
    }

    /**
     * Index string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("initialized", false);
        model.addAttribute("search", new DeezerSearch());
        return "index";
    }

    /**
     * Artist view string.
     *
     * @param model the model
     * @return the string
     */
    @GetMapping("/artists")
    public String artistSearchResults(Model model) {
        model.addAttribute("search", new DeezerSearch());
        return "index";
    }

    /**
     * Artist search string.
     *
     * @param search the search
     * @param model  the model
     * @return the string
     */
    @PostMapping(path = "/searchForm")
    public String artistSearch(@ModelAttribute("search") DeezerSearch search, Model model) {

        Artists artistsList = new Artists();
        try {
            artistsList = searchArtist(search);
        } catch (Exception e) {
            //log.info("Cause: [ " + e.getCause() + " ]  Message: [" + e.getMessage() + " ]");
            System.out.println("Cause: [ " + e.getCause() + " ]  Message: [" + e.getMessage() + " ]");
            e.printStackTrace();
        }
        if (artistsList.getData().size() == 0) {
            return "null_artist_error";
        }

        model.addAttribute("artists", artistsList);
        model.addAttribute("initialized", true);
        model.addAttribute("search", search);
        return "index";
    }

    private Artists searchArtist(DeezerSearch search) throws Exception {
        try {
            return apiService.searchArtist(search.getUserInput());

        } catch (RestClientException e) {
            e.printStackTrace();
            //log.info("Cause: [ " + e.getCause() + " ]  Message: [" + e.getMessage() + " ]");

        }
        throw new Exception("Something un-expected went wrong during the Artist Search");
    }
}
