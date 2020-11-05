package model;

public abstract class Playlist {

    private String name;
    private String duration;
    private Song[] songs;
    private Genre[] genres;

    // CONSTRUCTOR
    public Playlist(String name) {

        this.name = name;
        duration = "0:00";
        songs = new Song[30];
        genres = new Genre[6];
        genres[0] = Genre.Unknown;

    }

    // METHODS
    public abstract String getInfo();
    public abstract String addSong(Song song, Playlist playlist);

    // GETTERS
    public String getName() {
        return name;
    }
    public String getDuration() {
        return duration;
    }
    public Song[] getSongs() {
        return songs;
    }
}
