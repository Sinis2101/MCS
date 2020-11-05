package model;

public class RestrictedPlaylist extends Playlist {

    private static final int MAX_USERS = 5;

    private User[] users;

    public RestrictedPlaylist(String name, User user) {

        super(name);
        users = new User[MAX_USERS];
        users[0] = user;

    }

}
