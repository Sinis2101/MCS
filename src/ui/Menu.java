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

	//MCS mcs = new MCS();
	MCS mcs = new MCS(new User("Sinis", "12345", 19));

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

	public int getChoice(){
		
		int choice;
	
		choice = sc.nextInt();
		sc.nextLine();

		return choice;
		
	}	

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

				if(mcs.getUsers()[0] != null){

					User user = null;

					System.out.println("-------- CHOOSE USER ---------");
					for (int i = 0; i < mcs.getUserAmount(); i++) {

						System.out.println("[" + (i+1) + "] " + mcs.getUsers()[i].getUsername());

					}
					System.out.println("------------------------------");
					System.out.print("Please choose a user [1-" + mcs.getUserAmount() + "]: ");
					int userChoice = sc.nextInt();
					sc.nextLine();

					System.out.print(mcs.setActiveUser(mcs.getUsers()[userChoice-1]));

				} else {

					System.out.print("------------------------------\n" + "There are no users to choose from. Press ENTER to go back.");

				}

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

				Playlist playlist = choosePlaylist();
				Song song = chooseSong(playlist);

				if(mcs.checkSpace(playlist.getSongsAmount(), Playlist.MAX_SONGS)){

					System.out.println("------------------------------");
					System.out.println(playlist.addSong(song, playlist));
					System.out.print("Playlist Songs: [" + playlist.getSongsAmount() + "/" + Playlist.MAX_SONGS + "]");

				} else {

					System.out.print("------------------------------\n" + "No more songs can be added to this playlist. The limit has been reached. Press ENTER to go back.");

				}

				break;

			case(SHOW_PLAYLISTS):



				break;

			case(MANAGE_ACCESS):



				break;

			case(RATE_PLAYLIST):



				break;

			case(EXIT):

				break;

			default:

				System.out.print("------------------------------\n" + "Please choose a valid option. Press ENTER to try again.");

		}

	}

	// CREATE
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
	public Song createSong(){

		System.out.println("---------- NEW SONG ----------");

		System.out.print("Title: " );
		String title = sc.nextLine();

		System.out.print("Artist: ");
		String artist = sc.nextLine();

		System.out.print("Release Date: ");
		String releaseDate = sc.nextLine();

		System.out.print("Duration: ");
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

	// SHOW
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

	// CHOOSE
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

}
