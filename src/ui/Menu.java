package ui;

import model.*;
import java.util.Scanner;

public class Menu {

	public static final int ADD_USER = 1;
	public static final int SHOW_USERS = 2;
	public static final int SET_ACTIVE_USER = 3;
	public static final int ADD_SONG = 4;
	public static final int SHOW_SONGS = 5;
	public static final int ADD_PLAYLIST = 6;
	public static final int ADD_SONG_TO_PLAYLIST = 7;
	public static final int SHOW_PLAYLISTS = 8;
	public static final int MANAGE_ACCESS = 9;
	public static final int RATE_PLAYLIST = 10;
	public static final int EXIT = 11;

	Scanner sc = new Scanner(System.in);

	MCS mcs = new MCS();

	//MCS mcs = new MCS(new User("Sinis", "12345", 19)); // For testing purposes (Starts program with an active user).

	/**
	 * Starts the program.
	 */
	public void start(){
		
		int choice;

		do {


			showMenu();
			choice = getChoice();
			if(mcs.getActiveUser() != null) {

				doChoice(choice);

			} else if(choice == 4 || choice == 6 || choice == 7 || choice == 9 || choice == 10){

				System.out.print("--------------------------\n" + "Please set an active user first. Press ENTER to go back.");

			} else doChoice(choice);

			if(choice != 11) sc.nextLine();

		} while (choice != 11);

	}

	/**
	 * Show the menu.
	 */
	public void showMenu(){

		System.out.println("------------ MENU ------------");
		if(mcs.getActiveUser() != null) {

			System.out.println("Active User: " + mcs.getActiveUser().getUsername());
			System.out.println("------------------------------");

		} else {

			System.out.println("Please set an active user first.");
			System.out.println("------------------------------");

		}
		System.out.println("[1] Create a new user.");
		System.out.println("[2] Show users.");
		System.out.println("[3] Set active user.");
		System.out.println("------------------------------");
		System.out.println("[4] Share a new song.");
		System.out.println("[5] Show shared songs");
		System.out.println("------------------------------");
		System.out.println("[6] Create a new playlist");
		System.out.println("[7] Add a shared song to a playlist");
		System.out.println("[8] Show playlists");
		System.out.println("[9] Manage playlist access");
		System.out.println("[10] Rate a playlist");
		System.out.println("------------------------------");
		System.out.println("[11] Exit program.");
		System.out.println("------------------------------");
		System.out.print("Please choose an option [1-11]: ");
		
	}

	/**
	 * Asks the user for a choice
	 * @return Returns an integer representing the user's choice.
	 */
	public int getChoice(){
		
		int choice;
	
		choice = sc.nextInt();
		sc.nextLine();

		return choice;
		
	}

	/**
	 * Performs the user choice.
	 * @param choice An integer representing the user's choice.
	 */
	public void doChoice(int choice){

		switch(choice){

			case(ADD_USER):

				if(mcs.checkSpace(mcs.getUserAmount(), mcs.getMaxUsers())){

					User user = createUser();

					System.out.println("------------------------------");
					System.out.println(mcs.add(user));
					System.out.print("Users: [" + mcs.getUserAmount() + "/" + mcs.getMaxUsers() + "]");

				} else {

					System.out.print("------------------------------\n" + "No more users can be added. The limit has been reached. Press ENTER to go back.");

				}

				break;

			case(SHOW_USERS):

				showUsers();

				break;

			case(SET_ACTIVE_USER):

				User user = chooseUser();

				System.out.print(mcs.setActiveUser(user));

				break;

			case(ADD_SONG):

				if(mcs.checkSpace(mcs.getSharedSongsAmount(), mcs.getMaxSharedSongs())){

					Song song = createSong();

					System.out.println("------------------------------");
					System.out.println(mcs.add(song));
					System.out.print("Shared Songs: [" + mcs.getSharedSongsAmount() + "/" + mcs.getMaxSharedSongs() + "]");

				} else {

					System.out.print("------------------------------\n" + "No more songs can be added. The limit has been reached. Press ENTER to go back.");

				}

				break;

			case(SHOW_SONGS):

				showSharedSongs();

				break;

			case(ADD_PLAYLIST):

				if(mcs.checkSpace(mcs.getPlaylistAmount(), mcs.getMaxPlaylists())){

					Playlist playlist = createPlaylist();

					System.out.println("------------------------------");
					System.out.println(mcs.add(playlist));
					System.out.print("Playlists: [" + mcs.getPlaylistAmount() + "/" + mcs.getMaxPlaylists() + "]");

				} else {

					System.out.print("------------------------------\n" + "No more playlists can be added. The limit has been reached. Press ENTER to go back.");

				}

				break;

			case(ADD_SONG_TO_PLAYLIST):

				if(mcs.getPlaylists()[0] != null && mcs.getSongPool()[0] != null) {

					Playlist playlist = choosePlaylist();
					Song song = chooseSong(playlist);

					if(mcs.checkSpace(playlist.getSongsAmount(), Playlist.MAX_SONGS)){

						System.out.println("------------------------------");
						System.out.println(playlist.addSong(song, playlist));
						System.out.print("Playlist Songs: [" + playlist.getSongsAmount() + "/" + Playlist.MAX_SONGS + "]");

					} else {

						System.out.print("------------------------------\n" + "No more songs can be added to this playlist. The limit has been reached. Press ENTER to go back.");

					}

				} else if(mcs.getPlaylists()[0] == null && mcs.getSongPool()[0] != null ) {

					System.out.print("------------------------------\n" + "There are no playlists to add songs to. Press ENTER to go back.");

				} else if(mcs.getPlaylists()[0] != null && mcs.getSongPool()[0] == null ) {

					System.out.print("------------------------------\n" + "There are no songs to add. Press ENTER to go back.");

				} else System.out.print("------------------------------\n" + "No songs or playlists found. Press ENTER to go back.");

				break;

			case(SHOW_PLAYLISTS):

				showPlaylists();

				break;

			case(MANAGE_ACCESS):

				if(mcs.getPlaylists()[0] != null && mcs.getUsers()[0] != null) {

					RestrictedPlaylist playlist = (RestrictedPlaylist) chooseRestrictedPlaylist();

					if(playlist != null) {

						User choosenUser = chooseUser();

						if(mcs.checkSpace(playlist.getUserAmount(), RestrictedPlaylist.MAX_USERS)){

							System.out.println("------------------------------");
							System.out.println(playlist.grantAccess(choosenUser, playlist));
							System.out.print("Playlist Users: [" + playlist.getUserAmount() + "/" + RestrictedPlaylist.MAX_USERS + "]");

						} else {

							System.out.print("------------------------------\n" + "No more users can be added to this playlist. The limit has been reached. Press ENTER to go back.");

						}

					}

				} else if(mcs.getPlaylists()[0] == null && mcs.getUsers()[0] != null ) {

					System.out.print("------------------------------\n" + "There are no playlists to add users to. Press ENTER to go back.");

				} else if(mcs.getPlaylists()[0] != null && mcs.getUsers()[0] == null ) {

					System.out.print("------------------------------\n" + "There are no users to add. Press ENTER to go back.");

				} else System.out.print("------------------------------\n" + "No users or playlists found. Press ENTER to go back.");

				break;

			case(RATE_PLAYLIST):

				if(mcs.getPlaylists()[0] != null) {

					PublicPlaylist playlist = (PublicPlaylist) choosePublicPlaylist();

					if(playlist != null) {

						if(playlist.canRate(mcs.getActiveUser())) {

							System.out.println("----------------------------------");
							System.out.print("Please add a rate [1-5]: ");
							double rate = sc.nextInt();
							sc.nextLine();

							playlist.setRate(mcs.getActiveUser(), rate);

						} else {

							System.out.print("------------------------------\n" + "User '" + mcs.getActiveUser().getUsername() + "' has already rated playlist '" + playlist.getName() + "' . Press ENTER to go back.");

						}

					}

				} else System.out.print("------------------------------\n" + "No playlists found. Press ENTER to go back.");

				break;

			case(EXIT):

				break;

			default:

				System.out.print("------------------------------\n" + "Please choose a valid option. Press ENTER to try again.");

		}

	}

	/**
	 * Asks the user for the new user data.
	 * @return Returns a User Object ready to be added.
	 */
	public User createUser(){

		System.out.println("---------- NEW USER ----------");

		System.out.print("Username: " );
		String name = sc.nextLine();

		System.out.print("Password: ");
		String password = sc.nextLine();

		System.out.print("Age: ");
		int age = sc.nextInt();
		sc.nextLine();

		return new User(name, password, age);

	}

	/**
	 * Asks the user for the new song data.
	 * @return Returns a Song Object ready to be added.
	 */
	public Song createSong(){

		System.out.println("---------- NEW SONG ----------");

		System.out.print("Title: " );
		String title = sc.nextLine();

		System.out.print("Artist: ");
		String artist = sc.nextLine();

		System.out.print("Release Date (dd/mm/yy): ");
		String releaseDate = sc.nextLine();

		System.out.print("Duration (mm:ss): ");
		String duration = sc.nextLine();

		System.out.println("---------- CHOOSE GENRE ----------");
		for(int i = 0; i < Genre.values().length-1; i++) {

			System.out.println("[" + (i+1) + "] " + Genre.values()[i].name());

		}
		System.out.println("----------------------------------");
		System.out.print("Please choose a genre [1-6]: ");
		int genre = sc.nextInt();
		sc.nextLine();

		return new Song(title, artist, releaseDate, duration, genre);

	}

	/**
	 * Asks the user for the new playlist data.
	 * @return Returns a Playlist Object ready to be added.
	 */
	public Playlist createPlaylist(){

		System.out.println("-------- NEW PLAYLIST --------");

		System.out.print("Name: " );
		String name = sc.nextLine();

		System.out.println("---- CHOOSE PLAYLIST TYPE ----");
		System.out.println("[1] Private (Only you have access).");
		System.out.println("[2] Restricted (Access: 5 users).");
		System.out.println("[3] Public (Everyone has access).");
		System.out.println("------------------------------");
		System.out.print("Please choose a type [1-3]: ");
		int type = sc.nextInt();
		sc.nextLine();

		switch(type){

			case(2):

				return new RestrictedPlaylist(name, mcs.getActiveUser());

			case(3):

				return new PublicPlaylist(name);

			default:

				return new PrivatePlaylist(name, mcs.getActiveUser());

		}

	}

	/**
	 * Shows all users.
	 */
	public void showUsers() {

		if(mcs.getUsers()[0] != null){

			for(int i = 0; i < mcs.getUsers().length; i++) {

				if (mcs.getUsers()[i] != null) {

					System.out.println("----------- USER " + (i+1) + " -----------");
					System.out.println(mcs.getUsers()[i].getInfo());

				}

			}

			System.out.print("------------------------------\n" + "All users have been shown. Press ENTER to go back.");

		} else {

			System.out.print("------------------------------\n" + "There are no users to show. Press ENTER to go back.");

		}

	}

	/**
	 * Shows all shared songs.
	 */
	public void showSharedSongs() {

		if(mcs.getSongPool()[0] != null){

			for(int i = 0; i < mcs.getSongPool().length; i++) {

				if (mcs.getSongPool()[i] != null) {

					System.out.println("----------- SONG " + (i+1) + " -----------");
					System.out.println(mcs.getSongPool()[i].getInfo());

				}

			}

			System.out.print("------------------------------\n" + "All songs have been shown. Press ENTER to go back.");

		} else {

			System.out.print("------------------------------\n" + "There are no songs to show. Press ENTER to go back.");

		}

	}

	/**
	 * Shows all the playlists the active user has access to.
	 */
	public void showPlaylists() {

		Playlist[] accessiblePlaylists = new Playlist[20];
		int accessiblePlaylistsAmount = 0;
		int index = 0;

		for(int i = 0; i < mcs.getPlaylistAmount(); i++){

			if(mcs.access(mcs.getPlaylists()[i], mcs.getActiveUser())){

				accessiblePlaylists[index] = mcs.getPlaylists()[i];
				index ++;
				accessiblePlaylistsAmount ++;

			}

		}

		if(accessiblePlaylists[0] != null){

			for(int i = 0; i < accessiblePlaylistsAmount; i++) {

				if (accessiblePlaylists[i] != null) {

					System.out.println("--------- PLAYLIST " + (i+1) + " ---------");
					System.out.println(accessiblePlaylists[i].getInfo());
					String[] genres = accessiblePlaylists[i].getGenres();
					for(String genre : genres) {

						if(genre != null) System.out.println("- " + genre);

					}

				}

			}

			System.out.print("------------------------------\n" + "All playlists have been shown. Press ENTER to go back.");

		} else {

			System.out.print("------------------------------\n" + "There are no playlists to show. Press ENTER to go back.");

		}

	}

	/**
	 * Allow the user to see and choose from all the playlists he has access to.
	 * @return Returns the playlist the user chose.
	 */
	public Playlist choosePlaylist() {

		Playlist[] accessiblePlaylists = new Playlist[20];
		int accessiblePlaylistsAmount = 0;
		int index = 0;

		for(int i = 0; i < mcs.getPlaylistAmount(); i++){

			if(mcs.access(mcs.getPlaylists()[i], mcs.getActiveUser())){

				accessiblePlaylists[index] = mcs.getPlaylists()[i];
				index ++;
				accessiblePlaylistsAmount ++;

			}

		}

		if(accessiblePlaylistsAmount != 0){

			for(Playlist playlist : accessiblePlaylists) {

				if(playlist != null){

					System.out.println("------ CHOOSE PLAYLIST -------");
					for (int i = 0; i < accessiblePlaylistsAmount; i++) {

						System.out.println("[" + (i+1) + "] " + accessiblePlaylists[i].getName());

					}
					System.out.println("------------------------------");
					System.out.print("Please choose a playlist [1-" + accessiblePlaylistsAmount + "]: ");
					int userChoice = sc.nextInt();
					sc.nextLine();

					return accessiblePlaylists[userChoice-1];

				}

			}

		} else {

		System.out.print("------------------------------\n" + "There are no playlists to choose from. Press ENTER to go back.");
		return null;

		}

		return null;

	}

	/**
	 * Allow the user to see and choose from all the restricted playlists he has access to.
	 * @return Returns the restricted playlist the user chose.
	 */
	public Playlist chooseRestrictedPlaylist() {

		Playlist[] accessiblePlaylists = new Playlist[20];
		int accessiblePlaylistsAmount = 0;
		int index = 0;

		for(int i = 0; i < mcs.getPlaylistAmount(); i++){

			if(mcs.access(mcs.getPlaylists()[i], mcs.getActiveUser()) && mcs.getPlaylists()[i] instanceof RestrictedPlaylist){

				accessiblePlaylists[index] = mcs.getPlaylists()[i];
				index ++;
				accessiblePlaylistsAmount ++;

			}

		}

		if(accessiblePlaylistsAmount != 0){

			for(Playlist playlist : accessiblePlaylists) {

				if(playlist != null){

					System.out.println("------ CHOOSE PLAYLIST -------");
					for (int i = 0; i < accessiblePlaylistsAmount; i++) {

						System.out.println("[" + (i+1) + "] " + accessiblePlaylists[i].getName());

					}
					System.out.println("------------------------------");
					System.out.print("Please choose a playlist [1-" + accessiblePlaylistsAmount + "]: ");
					int userChoice = sc.nextInt();
					sc.nextLine();

					return accessiblePlaylists[userChoice-1];

				}

			}

		} else {

			System.out.print("------------------------------\n" + "There are no restricted playlists to manage. Press ENTER to go back.");
			return null;

		}

		return null;

	}

	/**
	 * Allow the user to see and choose from all the public playlists he has access to.
	 * @return Returns the public playlist the user chose.
	 */
	public Playlist choosePublicPlaylist() {

		Playlist[] publicPlaylists = new Playlist[20];
		int publicPlaylistsAmount = 0;
		int index = 0;

		for(int i = 0; i < mcs.getPlaylistAmount(); i++){

			if(mcs.getPlaylists()[i] instanceof PublicPlaylist){

				publicPlaylists[index] = mcs.getPlaylists()[i];
				index ++;
				publicPlaylistsAmount ++;

			}

		}

		if(publicPlaylistsAmount != 0){

			for(Playlist playlist : publicPlaylists) {

				if(playlist != null){

					System.out.println("------ CHOOSE PLAYLIST -------");
					for (int i = 0; i < publicPlaylistsAmount; i++) {

						System.out.println("[" + (i+1) + "] " + publicPlaylists[i].getName());

					}
					System.out.println("------------------------------");
					System.out.print("Please choose a playlist [1-" + publicPlaylistsAmount + "]: ");
					int userChoice = sc.nextInt();
					sc.nextLine();

					return publicPlaylists[userChoice-1];

				}

			}

		} else {

			System.out.print("------------------------------\n" + "There are no public playlists to rate. Press ENTER to go back.");
			return null;

		}

		return null;

	}

	/**
	 * Allow the user to see and choose from all the shared songs.
	 * @return Returns the song the user chose.
	 */
	public Song chooseSong(Playlist playlist) {

		if(mcs.getSongPool()[0] != null){

			System.out.println("---- CHOOSE SONG TO ADD ------");
			for (int i = 0; i < mcs.getSharedSongsAmount(); i++) {

				System.out.println("[" + (i+1) + "] " + mcs.getSongPool()[i].getTitle());

			}
			System.out.println("------------------------------");
			System.out.print("Please choose a song to add to " + playlist.getName() + " [1-" + mcs.getSharedSongsAmount() + "]: ");
			int userChoice = sc.nextInt();
			sc.nextLine();

			return mcs.getSongPool()[userChoice-1];

		} else {

			System.out.print("------------------------------\n" + "There are no songs to choose from. Press ENTER to go back.");
			return null;

		}

	}

	/**
	 * Allow the user to see and choose from all the users.
	 * @return Returns the user the user chose.
	 */
	public User chooseUser() {

		if(mcs.getUsers()[0] != null){

			System.out.println("--------- CHOOSE USER --------");
			for (int i = 0; i < mcs.getUserAmount(); i++) {

				System.out.println("[" + (i+1) + "] " + mcs.getUsers()[i].getUsername());

			}
			System.out.println("------------------------------");
			System.out.print("Please choose a user [1-" + mcs.getUserAmount() + "]: ");
			int userChoice = sc.nextInt();
			sc.nextLine();

			return mcs.getUsers()[userChoice-1];

		} else {

			System.out.print("------------------------------\n" + "There are no users to choose from. Press ENTER to go back.");
			return null;

		}

	}

}
