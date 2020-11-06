package model;

public abstract class Playlist {

    public static final int MAX_SONGS = 30;

    private String name;
    private String duration;
    private Song[] songs;
    private Genre[] genres;
    private int songsAmount;

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
    public int getSongsAmount() {
        return songsAmount;
    }

    // SETTERS
    public void increaseSongsAmount(int songsAmount) {
        this.songsAmount = songsAmount+1;
    }

}
