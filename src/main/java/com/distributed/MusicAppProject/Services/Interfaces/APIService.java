package com.distributed.MusicAppProject.Services.Interfaces;

import api.deezer.exceptions.DeezerException;
import api.deezer.objects.Album;
import api.deezer.objects.Track;
import api.deezer.objects.data.TrackData;
import com.distributed.MusicAppProject.MusicUsers.MusicObjects.Artists;

import java.util.List;


public interface APIService {
    /**
     * Gets top five tracks.
     *
     * @param artistId the artist id
     * @return the top five tracks
     */
    List<Track> getTopFiveTracks(int artistId);

    /**
     * Search artist
     *
     * @param artistName the artist name
     * @return the artists returned by the search query
     * @throws Exception the exception
     */
    Artists searchArtist(String artistName) throws Exception;


    Track getTrackData(Integer trackID) throws DeezerException;
    TrackData getTracksArtist (String artistName) throws DeezerException;
    Album getAlbumData (String albumName) throws DeezerException;
}
