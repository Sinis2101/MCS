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

    @Override
    public String getInfo() {

        return  "Name: " + getName() + "\n" +
                "Songs: [" + getSongsAmount() + "/" + MAX_SONGS + "]" + "\n" +
                "Duration: " + getDuration() + "\n" +
                "Genres: " + "\n" +
                "Type: Public" + "\n" +
                "Rating: " + rate;

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

}
