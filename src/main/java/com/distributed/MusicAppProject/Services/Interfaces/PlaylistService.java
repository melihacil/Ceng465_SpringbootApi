package com.distributed.MusicAppProject.Services.Interfaces;

import api.deezer.exceptions.DeezerException;

public interface PlaylistService {


    String addTrackToPlaylist(String trackName, String username, String playlistName) throws DeezerException;

    String createPlaylist(String username, String playlistName);

    String deletePlaylist(String username, String playlistName);
}
