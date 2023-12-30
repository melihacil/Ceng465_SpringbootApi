DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users(
    id INTEGER PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS users_favoriteTrack(
    id INTEGER PRIMARY KEY,
    trackId INTEGER NOT NULL,
    username VARCHAR(255) NOT NULL,
    trackname VARCHAR(255) NOT NULL,
    FOREIGN KEY (username) REFERENCES public.users (username)
);

CREATE TABLE IF NOT EXISTS users_trackReview(
    id INTEGER PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    trackname VARCHAR(255) NOT NULL,
    trackReview VARCHAR(MAX) NOT NULL,
    FOREIGN KEY (username) REFERENCES public.users (username),
    FOREIGN KEY (trackname) REFERENCES public.users_favoriteTrack (trackname)
);

CREATE TABLE IF NOT EXISTS users_playlist(
    id INTEGER PRIMARY KEY,
    playlistName VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    playlistTracks VARCHAR(MAX) NOT NULL
);

