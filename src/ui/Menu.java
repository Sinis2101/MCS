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

	public void start(){
		
		int choice;

		do {

			showMenu();
			choice = getChoice();
			System.out.println();
			doChoice(choice);
			if(choice != 11) sc.nextLine();

		} while (choice != 11);

	}
	
	public void showMenu(){

		System.out.println("------------ MENU ------------");
		if(mcs.getActiveUser() != null) {

			System.out.println("Active User: " + mcs.getActiveUser().getUsername());
			System.out.println("------------------------------");

		} else {

			System.out.println("Please create and set an active user first.");
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
					System.out.println("Users: [" + mcs.getUserAmount() + "/" + mcs.getMaxUsers() + "]");

				} else {

					System.out.print("------------------------------\n" + "No more users can be added. The limit has been reached. Press ENTER to go back.");

				}

				break;

			case(SHOW_USERS):



				break;

			case(SET_ACTIVE_USER):



				break;

			case(ADD_SONG):

				if(mcs.checkSpace(mcs.getSharedSongsAmount(), mcs.getMaxSharedSongs())){

					Song song = createSong();

					System.out.println("------------------------------");
					System.out.println(mcs.add(song));
					System.out.println("Shared Songs: [" + mcs.getSharedSongsAmount() + "/" + mcs.getMaxSharedSongs() + "]");

				} else {

					System.out.print("------------------------------\n" + "No more songs can be added. The limit has been reached. Press ENTER to go back.");

				}

				break;

			case(SHOW_SONGS):



				break;

			case(ADD_PLAYLIST):



				break;

			case(ADD_SONG_TO_PLAYLIST):



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

	// SHOW
	public void showUsers() {

		if(mcs.getUsers()[0] != null){

			for(int i = 0; i < mcs.getUsers().length; i++) {

				if (mcs.getUsers()[i] != null) {

					System.out.println("----- USER " + (i+1) + " -----");
					System.out.println(mcs.getUsers()[i].getInfo());
					System.out.println("-----------------");

				}

			}

		} else {

			System.out.print("--------------------------\n" + "There are no users to show. Press ENTER to continue.");
			sc.nextLine();

		}

	}
	public void showSongPool() {

		Song[] songPool = mcs.getSongPool();

		if(songPool[0] != null){

			for(int i = 0; i < mcs.getMaxUsers(); i++) {

				if (songPool[i] != null) {

					System.out.println("---------- SONG " + (i+1) + " ----------");
					System.out.println("Title: " + songPool[i].getTitle());
					System.out.println("Artist: " + songPool[i].getArtist());
					System.out.println("Duration: " + songPool[i].getDuration());
					System.out.println("Genre: " + songPool[i].getGenre() + "\n");

				}

			}

		} else {

			System.out.print("--------------------------\n" + "There are no shared songs to show. Press ENTER to continue.");
			sc.nextLine();

		}

	}

	// LOGIN

	public String logIn(String usernameTry, String passwordTry) {

		User[] users = mcs.getUsers();

		boolean logged = false;

		for(int i = 0; i < mcs.getMaxUsers() && !logged; i++) {

			if(users[i].getUsername().equals(usernameTry) && users[i].getPassword().equals(passwordTry)) {

				logged = true;
				mcs.setActiveUser(users[i]);
				System.out.println("Welcome, " + mcs.getActiveUser().getUsername() + ". Press ENTER to continue.");

			}

		}

		return "Please try again";

	}

}
