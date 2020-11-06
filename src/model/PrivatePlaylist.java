package model;

public class PrivatePlaylist extends Playlist {

    private User owner;

    public PrivatePlaylist(String name, User owner) {

        super(name);
        this.owner = owner;

    }

    @Override
    public String getInfo() {

        return  "Name: " + getName() + "\n" +
                "Duration: " + getDuration() + "\n" +
                "Genres: ";


    }

    @Override
    public String addSong(Song song, Playlist playlist) {

        boolean added = false;

        for(int i = 0; i < MAX_SONGS && !added; i++) {

            if(getSongs()[i] == null) {

                getSongs()[i] = song;
                increaseSongsAmount(getSongsAmount());
                added = true;

            }

        }

        return "Song has been added successfully to " + getName() + " . Press ENTER to continue.";

    }

    // GETTERS
    public User getOwner() {
        return owner;
    }
}
