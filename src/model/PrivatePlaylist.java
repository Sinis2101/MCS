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
                "Songs: [" + getSongsAmount() + "/" + MAX_SONGS + "]" + "\n" +
                "Duration: " + durationIntToString(getDuration()) + "\n" +
                "Genres: " + "\n" +
                "Type: Private" + "\n" +
                "Owner: " + owner.getUsername();

    }

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

    // GETTERS
    public User getOwner() {
        return owner;
    }
}
