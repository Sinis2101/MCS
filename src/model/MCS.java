package model;

public class MCS {

	private static final int MAX_USERS = 10;
	private static final int MAX_SHARED_SONGS = 30;
	private static final int MAX_PLAYLISTS = 20;

	private User[] users;
	private Song[] songPool;
	private Playlist[] playlists;

	private int userAmount;
	private int sharedSongsAmount;
	private int playlistAmount;
	private User activeUser;

	public MCS(){

		users = new User[MAX_USERS];
		songPool = new Song[MAX_SHARED_SONGS];
		playlists = new Playlist[MAX_PLAYLISTS];

		userAmount = 0;
		sharedSongsAmount = 0;
		playlistAmount = 0;
		this.activeUser = null;

	}

	public boolean checkSpace(int currentAmount, int maxAmount){

		return currentAmount < maxAmount;

	}

	public String add(User user) {

		String message = "User has been added successfully. Press ENTER to continue.";
		boolean added = false;
		userAmount ++;

		for(int i = 0; i < MAX_USERS && !added; i++) {

			if(users[i] == null) {

				users[i] = user;
				added = true;

			}

		}

		return message;

	}
	public String add(Song song) {

		String message = "Song has been added successfully. Press ENTER to continue.";
		boolean added = false;
		sharedSongsAmount ++;

		for(int i = 0; i < MAX_SHARED_SONGS && !added; i++) {

			if(songPool[i] == null) {

				songPool[i] = song;
				added = true;

			}

		}

		return message;

	}
	public String add(Playlist playlist) {

		String message = "Playlist has been added successfully. Press ENTER to continue.";
		boolean added = false;
		playlistAmount ++;

		for(int i = 0; i < MAX_PLAYLISTS && !added; i++) {

			if(playlists[i] == null) {

				playlists[i] = playlist;
				added = true;

			}

		}

		return message;

	}

	// GETTERS
	public int getMaxUsers() {
		return MAX_USERS;
	}
	public int getMaxPlaylists() {
		return MAX_PLAYLISTS;
	}
	public int getMaxSharedSongs() {
		return MAX_SHARED_SONGS;
	}
	public User[] getUsers() {
		return users;
	}
	public Song[] getSongPool() {
		return songPool;
	}
	public Playlist[] getPlaylists() {
		return playlists;
	}
	public int getUserAmount() {
		return userAmount;
	}
	public int getSharedSongsAmount() {
		return sharedSongsAmount;
	}
	public int getPlaylistAmount() {
		return playlistAmount;
	}
	public User getActiveUser() {
		return activeUser;
	}

	// SETTERS
	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}

}

