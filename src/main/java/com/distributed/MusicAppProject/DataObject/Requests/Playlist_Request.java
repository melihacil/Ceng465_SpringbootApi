package com.distributed.MusicAppProject.DataObject.Requests;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Playlist_Request {

    String username;
    String playlistName;
    String trackName;


}
