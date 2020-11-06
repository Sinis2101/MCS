package model;

public class User {

	private String username;
	private String password;
	private int age;
	private Category category;
	private int sharedSongs;

	public User(String username, String password, int age) {
		
		this.username = username;
		this.password = password;
		this.age = age;
		category = Category.NEWBIE;
		sharedSongs = 0;
		
	}

	public String getInfo() {

		return 	"Username: " + username + "\n" +
				"Age: " + age + "\n" +
				"Category: " + category.name() + "\n" +
				"Shared Songs: " + sharedSongs;


	}

	public void incrementSharedSongs(int sharedSongs) {
		this.sharedSongs = sharedSongs+1;
	}
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
