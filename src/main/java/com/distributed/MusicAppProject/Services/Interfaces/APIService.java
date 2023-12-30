package com.distributed.MusicAppProject.Services.Interfaces;

import api.deezer.exceptions.DeezerException;
import api.deezer.objects.Album;
import api.deezer.objects.Track;
import api.deezer.objects.data.TrackData;


public interface APIService {

    Track getTrackData(Integer trackID) throws DeezerException;
    TrackData getTracksArtist (String artistName) throws DeezerException;
    Album getAlbumData (String albumName) throws DeezerException;
}
