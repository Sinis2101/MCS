package model;

public class RestrictedPlaylist extends Playlist {

    private static final int MAX_USERS = 5;

    private User[] users;

    public RestrictedPlaylist(String name, User user) {

        super(name);
        users = new User[MAX_USERS];
        users[0] = user;

    }

    @Override
    public String getInfo() {

        return  "Name: " + getName() + "\n" +
                "Duration: " + getDuration() + "\n" +
                "Genres: " + "\n" +
                "Type: Restricted" + "\n" +
                "Users with access: ";

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

        return "Song '" + song.getTitle() + "' has been added successfully to playlist '" + getName() + "' . Press ENTER to continue.";

    }

    // GETTERS
    public User[] getUsers() {
        return users;
    }
}
