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

	/**
	 * MCS Constructor.
	 */
	public MCS(){

		users = new User[MAX_USERS];
		songPool = new Song[MAX_SHARED_SONGS];
		playlists = new Playlist[MAX_PLAYLISTS];

		userAmount = 0;
		sharedSongsAmount = 0;
		playlistAmount = 0;
		this.activeUser = null;

	}

	/**
	 * MCS Constructor for testing purposes (Starts program with an active user).
	 * @param activeUser Active user. activeUser != null.
	 */
	public MCS(User activeUser){

		users = new User[MAX_USERS];
		users[0] = activeUser;
		songPool = new Song[MAX_SHARED_SONGS];
		playlists = new Playlist[MAX_PLAYLISTS];

		userAmount = 1;
		sharedSongsAmount = 0;
		playlistAmount = 0;
		this.activeUser = activeUser;

	}

	/**
	 * Check if a current amount is less than the max amount.
	 * @param currentAmount An integer representing current amount of something.
	 * @param maxAmount An integer representing max amount of something.
	 * @return Returns true if current amount is less than the max amount or false if current amount is more than the max amount.
	 */
	public boolean checkSpace(int currentAmount, int maxAmount){

		return currentAmount < maxAmount;

	}

	/**
	 * Adds a user to the users list.
	 * @param user A User Object to be added to the user list. user != null.
	 * @return Returns message with the result of the operation.
	 */
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

	/**
	 * Adds a song to the songs list.
	 * @param song A Song Object to be added to the song list. song != null.
	 * @return Returns message with the result of the operation.
	 */
	public String add(Song song) {

		String message = "Song has been added successfully. Press ENTER to continue.";
		boolean added = false;

		for(int i = 0; i < MAX_SHARED_SONGS && !added; i++) {

			if(songPool[i] == null) {

				songPool[i] = song;
				sharedSongsAmount ++;
				activeUser.incrementSharedSongs(activeUser.getSharedSongs());
				activeUser.setCategory(activeUser.getSharedSongs());
				added = true;

			}

		}

		return message;

	}

	/**
	 * Adds a playlist to the playlists list.
	 * @param playlist A Playlist Object to be added to the playlist list. playlist != null.
	 * @return Returns message with the result of the operation.
	 */
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

	/**
	 * Checks if a user has access to a playlist.
	 * @param playlist The playlist object the user wants to have access to. playlist != null.
	 * @param activeUser The user that is asking for access. activeUser != null.
	 * @return Returns true if user exists in the users that can access the playlist or false if not.
	 */
	public boolean access(Playlist playlist, User activeUser) {

		if(playlist instanceof PrivatePlaylist) {

			if(activeUser == ((PrivatePlaylist) playlist).getOwner()) return true;

		} else if(playlist instanceof RestrictedPlaylist) {

			for(User accessor : ((RestrictedPlaylist) playlist).getUsers()){

				if(activeUser == accessor) return true;

			}

		} else if(playlist instanceof PublicPlaylist) {

			return true;

		}

		return false;

	}

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

	/**
	 * Sets a user as the active user.
	 * @param activeUser The user to be set as the active user. activeUser != null.
	 * @return Returns message with the result of the operation.
	 */
	public String setActiveUser(User activeUser) {

		this.activeUser = activeUser;

		return "Active user is now: " + activeUser.getUsername() + ". Press ENTER to continue.";

	}

}

