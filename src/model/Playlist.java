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

    /**
     * Gets duration in seconds and converts it to String format (mm:ss)
     * @param duration An integer representing duration in seconds.
     * @return Returns String with duration in format (mm:ss).
     */
    public String durationIntToString(int duration){

        int minutes = duration/60;
        int seconds = duration%60;

        if(seconds<10){

            return minutes + ":0" + seconds;

        } else return minutes + ":" + seconds;

    }

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

    /**
     * Increase the amount of songs in the playlist.
     * @param songsAmount An integer representing the current song amount.
     */
    public void increaseSongsAmount(int songsAmount) {
        this.songsAmount = songsAmount+1;
    }

    /**
     * Updates duration of the playlist based on the sum of the duration of all the playlist songs.
     * @param duration An integer representing the duration of the recent added song.
     */
    public void updateDuration(int duration){

        this.duration += duration;

    }

    /**
     * Updates genres of the playlist based on the genres of all the playlist songs.
     * @param songGenre A String representing the genre of the recent added song.
     */
    public void updateGenres(String songGenre){

        genres[0] = songGenre;

    }

}
