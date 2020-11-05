package model;

public class User {

	private String username;
	private String password;
	private int age;
	private Category category;
	private int sharedSongs;

	// CONSTRUCTOR
	public User(String username, String password, int age) {
		
		this.username = username;
		this.password = password;
		this.age = age;
		category = Category.NEWBIE;
		sharedSongs = 0;
		
	}

	// METHODS
	public String getInfo() {

		return 	"Username: " + username + "\n" +
				"Age: " + age + "\n" +
				"Category: " + category.name();

	}

	// GETTERS
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
