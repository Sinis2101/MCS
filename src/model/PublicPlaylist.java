package model;

public class PublicPlaylist extends Playlist {

    private static final int MAX_RATINGS = 10;

    private User[] raters;
    private double rate;
    private int ratersAmount;

    public PublicPlaylist(String name) {

        super(name);
        raters = new User[MAX_RATINGS];
        rate = 0;
        ratersAmount = 0;

    }

}
