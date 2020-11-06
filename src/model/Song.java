package model;

public class Song {

	private String title;
	private String artist;
	private String releaseDate;
	private String duration;
	private String genre;

	public Song(String title, String artist, String releaseDate, String duration, int genre) {
		
		this.title = title;
		this.artist = artist;
		this.releaseDate = releaseDate;
		this.duration = duration;
		this.genre = Genre.values()[genre-1].name();
	
	}

	public String getInfo() {

		return 	"Title: " + title + "\n" +
				"Artist: " + artist + "\n" +
				"Duration: " + duration + "\n" +
				"Genre: " + genre;

	}

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
