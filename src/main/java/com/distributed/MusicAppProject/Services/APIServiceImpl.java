package com.distributed.MusicAppProject.Services;

import com.distributed.MusicAppProject.MusicUsers.MusicObjects.Album;
import com.distributed.MusicAppProject.MusicUsers.MusicObjects.Albums;
import com.distributed.MusicAppProject.MusicUsers.MusicObjects.Artists;
import com.distributed.MusicAppProject.MusicUsers.MusicObjects.Track;
import com.distributed.MusicAppProject.Services.Interfaces.APIService;
import com.distributed.MusicAppProject.Utils.HTTPConnection;
import com.distributed.MusicAppProject.Utils.SIMHRestTemplate;
import com.distributed.MusicAppProject.Utils.UrlConfigs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class APIServiceImpl implements APIService {

    private SIMHRestTemplate restTemplate;

    /**
     * Instantiates a new API service.
     *
     * @param httpConnectionUtils the http connection utils
     */
    @Autowired
    public APIServiceImpl(HTTPConnection httpConnectionUtils) {
        this.restTemplate = new SIMHRestTemplate(httpConnectionUtils);

    }


    @Override
    public List<Track> getTopFiveTracks(int artistId) { //TODO: request top 5 tracks
        return null;
    }

    @Override
    public Artists searchArtist(String userInput) throws Exception {
        String urlQuery = UrlConfigs.buildArtistSearchQuery(userInput);

        return restTemplate.get(urlQuery, Artists.class);
    }

    @Override
    public Album getAlbumByAlbumId(Integer albumId) throws Exception {
        String url = UrlConfigs.buildAlbumDetailURL(albumId);
        return restTemplate.get(url, Album.class);
    }

    @Override
    public Albums getAlbumsByArtistId(Integer artistId) throws Exception {

        String url = UrlConfigs.buildAlbumLinkURL(artistId);

        return restTemplate.get(url, Albums.class);
    }


}
