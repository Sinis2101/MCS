package model;

public class RestrictedPlaylist extends Playlist {

    public static final int MAX_USERS = 5;

    private User[] users;
    private int userAmount;

    public RestrictedPlaylist(String name, User user) {

        super(name);
        users = new User[MAX_USERS];
        users[0] = user;
        userAmount = 1;

    }

    @Override
    public String getInfo() {

        return  "Name: " + getName() + "\n" +
                "Songs: [" + getSongsAmount() + "/" + MAX_SONGS + "]" + "\n" +
                "Duration: " + durationIntToString(getDuration()) + "\n" +
                "Genres: " + "\n" +
                "Type: Restricted" + "\n" +
                "Users with access:" + "\n" +
                "- " + getUsersList()[0] + "\n" +
                "- " + getUsersList()[1] + "\n" +
                "- " + getUsersList()[2] + "\n" +
                "- " + getUsersList()[3] + "\n" +
                "- " + getUsersList()[4];

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

    public String grantAccess(User user, Playlist playlist) {

        boolean isAccessor = false;

        for(User accessor : users) {

            if(accessor == user) {

                isAccessor = true;
                return "User '" + user.getUsername() + "' has already access to playlist '" + getName() + "' . Press ENTER to go back.";

            }

        }

        boolean added = false;

        for(int i = 0; i < MAX_USERS && !added && !isAccessor; i++) {

            if(users[i] == null) {

                users[i] = user;
                userAmount ++;
                added = true;

            }

        }

        return "User '" + user.getUsername() + "' has now access to playlist '" + getName() + "' . Press ENTER to continue.";

    }

    // GETTERS
    public User[] getUsers() {
        return users;
    }
    public int getUserAmount() {
        return userAmount;
    }
    public String[] getUsersList() {

        String[] userList = new String[MAX_USERS];

        for(int i = 0; i < MAX_USERS; i++){

            if(users[i] == null) {

                userList[i] = "";

            } else {

                userList[i] = users[i].getUsername();

            }
        }

        return userList;

    }

}
