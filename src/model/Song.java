package model;

public class Song {

	private String title;
	private String artist;
	private String releaseDate;
	private String duration;
	private String genre;

	/**
	 * Constructor of a song.
	 * @param title A String containing the title of the song. title != "" && title != null.
	 * @param artist A String containing the artist of the song. artist != "" && artist != null.
	 * @param releaseDate A String containing the release date of the song. releaseDate != "" && releaseDate != null.
	 * @param duration A String containing the duration of the song. duration != "" && duration != null.
	 * @param genre A integer representing the index of the song genre.
	 */
	public Song(String title, String artist, String releaseDate, String duration, int genre) {
		
		this.title = title;
		this.artist = artist;
		this.releaseDate = releaseDate;
		this.duration = duration;
		this.genre = Genre.values()[genre-1].name();
	
	}

	/**
	 * Gets basic information from the song.
	 * @return Returns a string with all the basic information from the song.
	 */
	public String getInfo() {

		return 	"Title: " + title + "\n" +
				"Artist: " + artist + "\n" +
				"Duration: " + duration + "\n" +
				"Genre: " + genre;

	}

	/**
	 * Converts duration in format (mm:ss) to duration in seconds.
	 * @param duration A String containing the duration of the song. duration != "" && duration != null.
	 * @return Returns an integer with the duration of the song in seconds.
	 */
	public int getDurationInt(String duration){

		String[] stringUnits = duration.split(":");
		int[] intUnits = new int[2];

		int minutes = Integer.parseInt(stringUnits[0]);
		int seconds = Integer.parseInt(stringUnits[1]);

		int secDuration = (minutes*60) + seconds;

		return secDuration;

	}

	public String getTitle() {
		return title;
	}
	public String getArtist() {
		return artist;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public String getDuration() {
		return duration;
	}
	public String getGenre() {
		return genre;
	}

}
