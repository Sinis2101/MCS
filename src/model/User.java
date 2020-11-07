package model;

public class User {

	private String username;
	private String password;
	private int age;
	private Category category;
	private int sharedSongs;

	/**
	 * Constructor of a user.
	 * @param username A String containing the username of the user. username != "" && username != null.
	 * @param password A String containing the password of the user. password != "" && password != null.
	 * @param age An integer containing the age of the user.
	 */
	public User(String username, String password, int age) {
		
		this.username = username;
		this.password = password;
		this.age = age;
		category = Category.NEWBIE;
		sharedSongs = 0;
		
	}

	/**
	 * Gets basic information from the user.
	 * @return Returns a string with all the basic information from the user.
	 */
	public String getInfo() {

		return 	"Username: " + username + "\n" +
				"Age: " + age + "\n" +
				"Category: " + category.name() + "\n" +
				"Shared Songs: " + sharedSongs;


	}

	/**
	 * Increase the amount of songs in the user has shared.
	 * @param sharedSongs An integer representing the current shared songs amount.
	 */
	public void incrementSharedSongs(int sharedSongs) {
		this.sharedSongs = sharedSongs+1;
	}

	/**
	 * Sets user category based on the amount of songs shared.
	 * @param sharedSongs An integer representing the user's amount of shared songs.
	 */
	public void setCategory(int sharedSongs) {

		if(sharedSongs < 3) category = Category.NEWBIE;
		else if(sharedSongs < 10) category = Category.LITTLE_CONTRIBUITOR;
		else if(sharedSongs < 30) category = Category.MILD_CONTRIBUITOR;
		else category = Category.STAR_CONTRIBUITOR;

	}

	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public int getAge() {
		return age;
	}
	public Category getCategory() {
		return category;
	}
	public int getSharedSongs() {
		return sharedSongs;
	}
}
