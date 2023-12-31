package com.distributed.MusicAppProject.Services;

import api.deezer.DeezerApi;
import api.deezer.exceptions.DeezerException;
import api.deezer.objects.*;
import api.deezer.objects.data.TrackData;
import com.distributed.MusicAppProject.DataObject.Requests.ApiAuth_Request;
import com.distributed.MusicAppProject.Services.Interfaces.APIService;
import com.distributed.MusicAppProject.Utils.HTTPConnection;
import com.distributed.MusicAppProject.Utils.SIMHRestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class APIServiceImpl implements APIService {


    DeezerApi deezerApi; //= new DeezerApi();
    /**
     * Instantiates a new API service.
     *
     * httpConnectionUtils the http connection utils
     */
    private SIMHRestTemplate restTemplate;
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
    }

    public boolean DeezerApiInit(String deezerCode) {
        if (deezerApi == null)
            deezerApi = new DeezerApi();
        try {
            //Scanner scanner = new Scanner(System.in);
            //String loginUrl = deezerApi.auth().getLoginUrl(APP_ID, REDIRECT_URI, Permission.BASIC_ACCESS);
            //System.out.println(loginUrl); // https://connect.deezer.com/oauth/auth.php?app_id=123&redirect_uri=your.domain.com&perms=basic_access
            //System.out.println("https://connect.deezer.com/oauth/auth.php?app_id=657351&redirect_uri=https://localhost/callback&perms=basic_access");
            //System.out.print("Please enter code from deezer: ");

            // Step 3. Get access_token.
            DeezerAccessToken accessToken = deezerApi.auth().getAccessToken(APP_ID, SECRET, deezerCode).execute();
            deezerApi.setAccessToken(accessToken);
            distProject = deezerApi.user().getMe().execute();
            System.out.println(distProject);
        }
        catch (Exception e) {
            System.out.println("DEEZER ERROR ERROR!");
            e.printStackTrace();
            return false;

        }

        return true;
        // Now we are ready to execute any request we want.

    }

    @Override public String getCodeLink() throws DeezerException {
        if (deezerApi == null)
            deezerApi = new DeezerApi();
        String loginUrl = deezerApi.auth().getLoginUrl(APP_ID, REDIRECT_URI, Permission.BASIC_ACCESS);
        System.out.println(loginUrl); // https://connect.deezer.com/oauth/auth.php?app_id=123&redirect_uri=your.domain.com&perms=basic_access
        System.out.println("https://connect.deezer.com/oauth/auth.php?app_id=657351&redirect_uri=https://localhost/callback&perms=basic_access");
        return "https://connect.deezer.com/oauth/auth.php?app_id=657351&redirect_uri=https://localhost/callback&perms=basic_access";
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
    public String authenticate(ApiAuth_Request apiAuthRequest) {


        System.out.println("https://connect.deezer.com/oauth/auth.php?app_id=657351&redirect_uri=https://localhost/callback&perms=basic_access");
        System.out.print("Please enter code from deezer: ");


         if (DeezerApiInit(apiAuthRequest.getApiCode())) {
             return "Deezer Accepted API CODE!";
         }

            return "Deezer Rejected API CODE!";

    }


    public void TestStuff() {
        //deezerApi.search().searchTrack("").execute().getData().get(0).getPreview();
    }

    @Override
    public TrackData getTracksArtist (String artistName) throws DeezerException{
        TrackData data = deezerApi.search().searchTrack(artistName).execute();
        return data;
    }


    public String PlayListSearchTrack(String trackName) throws DeezerException{
        return deezerApi.search().searchTrack(trackName).execute().getData().get(0).getTitle();
    }


}
