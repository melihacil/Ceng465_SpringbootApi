package com.distributed.MusicAppProject.Controller;

import api.deezer.exceptions.DeezerException;
import api.deezer.objects.Track;
import api.deezer.objects.data.TrackData;
import com.distributed.MusicAppProject.DataObject.Response;
import com.distributed.MusicAppProject.Services.Interfaces.APIService;
import com.distributed.MusicAppProject.Services.MusicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequiredArgsConstructor
@RequestMapping("app/v1/music")
public class MusicController {

    @Autowired
    private final MusicService _service;

    private final APIService APIService;

    @GetMapping("/{id}")
    public Response GetMusicByID(@PathVariable int id) throws IOException {
        String musicName = "" ;//_service.GetMusicByID(id);
        Track track = null;
        try  {
            track = APIService.getTrackData(id);
        }
        catch (DeezerException e) {
            System.out.println("Track Not Found Check TRACK ID");
            return Response
                    .builder()
                    .status(false)
                    ._message("Track Not Found Check ID")
                    ._payload(null)
                    .build();
        }
        Response response = new Response<>(true, "Album Found", track);
        System.out.println("Track FOUND = " + track.getTitle()  + " Artist = " + track.getArtist());
        return Response
                .builder()
                .status(true)
                ._message("Track found = " + track.getTitle() )
                ._payload(response)
                .build();
    }

    @GetMapping("/tracks/{artistName}")
    public Response GetMusicByArtistName(@PathVariable String artistName) throws IOException {
        String musicName = "" ;//_service.GetMusicByID(id);
        TrackData  trackData= null;
        try  {
            trackData = APIService.getTracksArtist(artistName);
        }
        catch (DeezerException e) {
            System.out.println("Artist no Found Check Artist String");
            return Response
                    .builder()
                    .status(false)
                    ._message("Artist no Found Check Artist String")
                    ._payload(null)
                    .build();
        }
        Response response = new Response<>(true, "Tracks found", trackData.getData());
        System.out.println("Track FOUND = " + trackData.getData()+ " Artist = " + trackData.getTotal());
        return Response
                .builder()
                .status(true)
                ._message("Tracks Found of artist" )
                ._payload(response)
                .build();
    }

    //TrackData trackData = deezerApi.search().searchTrack("eminem").execute();
}
