package model;

public class PublicPlaylist extends Playlist {

    private static final int MAX_RATINGS = 10;

    private User[] raters;
    private double rate;
    private int ratersAmount;

    /**
     * Public playlist constructor.
     * @param name A string containing the name of the playlist. name != "" && name != null.
     */
    public PublicPlaylist(String name) {

        super(name);
        raters = new User[MAX_RATINGS];
        rate = 0;
        ratersAmount = 0;

    }

    /**
     * Gets basic information from the playlist.
     * @return Returns a string with all the basic information from the playlist.
     */
    @Override
    public String getInfo() {

        return  "Name: " + getName() + "\n" +
                "Songs: [" + getSongsAmount() + "/" + MAX_SONGS + "]" + "\n" +
                "Duration: " + durationIntToString(getDuration()) + "\n" +
                "Type: Public" + "\n" +
                "Rating: " + Math.round((rate/ratersAmount)*10)/10.0 + "\n" +
                "Genres: ";

    }

    /**
     * Adds a song to the playlist.
     * @param song A Song Object that will be added to the playlist. song != null.
     * @param playlist A Playlist Object that will receive the added song. playlist != null.
     * @return Returns a string with the result of the operation.
     */
    @Override
    public String addSong(Song song, Playlist playlist) {

        boolean added = false;

        for(int i = 0; i < MAX_SONGS && !added; i++) {

            if(getSongs()[i] == null) {

                getSongs()[i] = song;
                increaseSongsAmount(getSongsAmount());
                updateDuration(song.getDurationInt(song.getDuration()));
                added = true;

            }

        }

        return "Song '" + song.getTitle() + "' has been added successfully to playlist '" + getName() + "' . Press ENTER to continue.";

    }

    /**
     * Sums entered rate to current rate and adds the rater to the playlist raters list.
     * @param rater A user object representing the user that entered the rating.
     * @param rate A double representing the user's rate.
     */
    public void setRate(User rater, double rate) {

        boolean added = false;

        for(int i = 0; i < MAX_RATINGS && !added; i++) {

            if(raters[i] == null) {

                raters[i] = rater;
                ratersAmount ++;
                added = true;

            }

        }

        this.rate += rate;

    }

    /**
     * Checks if a user has already rated the list.
     * @param activeUser The user trying to rate the list.
     * @return Returns true if the user has not rate the list or false if the user has already rated the list.
     */
    public boolean canRate(User activeUser){

        for(User rater : raters) {

            if(rater == activeUser) return false;

        }

        return true;

    }
}
