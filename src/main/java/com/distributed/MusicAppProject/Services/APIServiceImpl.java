package com.distributed.MusicAppProject.Services;

import api.deezer.DeezerApi;
import api.deezer.exceptions.DeezerException;
import api.deezer.objects.*;
import api.deezer.objects.data.TrackData;
import com.distributed.MusicAppProject.MusicUsers.MusicObjects.Artists;
import com.distributed.MusicAppProject.Services.Interfaces.APIService;
import com.distributed.MusicAppProject.Utils.HTTPConnection;
import com.distributed.MusicAppProject.Utils.SIMHRestTemplate;
import com.distributed.MusicAppProject.Utils.UrlConfigs;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class APIServiceImpl implements APIService {

    private SIMHRestTemplate restTemplate;

    DeezerApi deezerApi = new DeezerApi();
    /**
     * Instantiates a new API service.
     *
     * @param httpConnectionUtils the http connection utils
     */

    private static final int APP_ID = 657351;

    /**
     * Can be found at https://developers.deezer.com/myapps
     */
    private static final String SECRET = "6adb3b0654b689413fbe379a9c4965d7";

    /**
     * Your domain where user will be redirected to.
     */
    private static final String REDIRECT_URI = "https://localhost/";

    User distProject;
    @Autowired
    public APIServiceImpl(HTTPConnection httpConnectionUtils){
        this.restTemplate = new SIMHRestTemplate(httpConnectionUtils);

        this.deezerApi = new DeezerApi();
        try {
            String loginUrl = deezerApi.auth().getLoginUrl(APP_ID, REDIRECT_URI, Permission.BASIC_ACCESS);
            System.out.println(loginUrl); // https://connect.deezer.com/oauth/auth.php?app_id=123&redirect_uri=your.domain.com&perms=basic_access
            System.out.println("https://connect.deezer.com/oauth/auth.php?app_id=657351&redirect_uri=https://localhost/callback&perms=basic_access");
            System.out.print("Please enter code: ");
            String code = new Scanner(System.in).next();

            // Step 3. Get access_token.
            DeezerAccessToken accessToken = deezerApi.auth().getAccessToken(APP_ID, SECRET, code).execute();
            deezerApi.setAccessToken(accessToken);

            // Now we are ready to execute any request we want.
            distProject = deezerApi.user().getMe().execute();
            System.out.println(distProject);

        }
        catch (DeezerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Album getAlbumData (String albumName) throws DeezerException{
       // Album albumData = deezerApi.search().searchAlbum(albumName).execute();
        Album albumData =  deezerApi.search().searchAlbum(albumName).execute().getData().get(0);
        //Album albumData = deezerApi.album().getById(albumID).execute();
        return albumData;
    }

    @Override
    public Track getTrackData (Integer trackID) throws DeezerException{
        Track data = deezerApi.track().getById(trackID).execute();
        return data;
    }

    @Override
    public TrackData getTracksArtist (String artistName) throws DeezerException{
        TrackData data = deezerApi.search().searchTrack(artistName).execute();
        return data;
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


}
