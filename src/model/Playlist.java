package model;

public abstract class Playlist {

    public static final int MAX_SONGS = 30;

    private String name;
    private int duration;
    private Song[] songs;
    private String[] genres;
    private int songsAmount;

    public Playlist(String name) {

        this.name = name;
        duration = 0;
        songs = new Song[30];
        genres = new String[6];
        genres[0] = Genre.Unknown.name();

    }

    /**
     * Gets basic information from playlist.
     * @return Returns a string with all the basic information from the playlist.
     */
    public abstract String getInfo();

    /**
     * Adds a song to the playlist.
     * @param song A Song Object that will be added to the playlist. song != null.
     * @param playlist A Playlist Object that will receive the added song. playlist != null.
     * @return Returns a string with the result of the operation.
     */
    public abstract String addSong(Song song, Playlist playlist);

    public String durationIntToString(int duration){

        int minutes = duration/60;
        int seconds = duration%60;

        if(seconds<10){

            return minutes + ":0" + seconds;

        } else return minutes + ":" + seconds;

    }

    // GETTERS
    public String getName() {
        return name;
    }
    public int getDuration() {
        return duration;
    }
    public Song[] getSongs() {
        return songs;
    }
    public int getSongsAmount() {
        return songsAmount;
    }
    public String[] getGenres() {
        return genres;
    }

    // SETTERS
    public void increaseSongsAmount(int songsAmount) {
        this.songsAmount = songsAmount+1;
    }
    public void updateDuration(int duration){

        this.duration += duration;

    }
    public void updateGenres(String songGenre){

        genres[0] = songGenre;

    }

}
