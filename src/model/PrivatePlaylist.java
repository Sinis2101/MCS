package model;

public class PrivatePlaylist extends Playlist {

    private User owner;

    /**
     * Private Playlist constructor.
     * @param name A string containing the name of the playlist. name != "" && name != null.
     * @param owner A user object containing the active user.
     */
    public PrivatePlaylist(String name, User owner) {

        super(name);
        this.owner = owner;

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
                "Type: Private" + "\n" +
                "Owner: " + owner.getUsername() + "\n" +
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
                updateGenres(song.getGenre());
                added = true;

            }

        }

        return "Song '" + song.getTitle() + "' has been added successfully to playlist '" + getName() + "' . Press ENTER to continue.";

    }

    public User getOwner() {
        return owner;
    }

}
