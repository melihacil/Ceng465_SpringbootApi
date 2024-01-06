package com.distributed.MusicAppProject.MusicUsers.MusicObjects;



import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Hidden
@Table(name="user_favoriteTrack")
public class FavoriteTrack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int trackId;
    private String username;
    private String trackName;
}