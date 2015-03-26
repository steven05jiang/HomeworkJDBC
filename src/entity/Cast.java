package entity;

public class Cast {
	private int castId;
	private String characterName;
	private int movieId;
	private int actorId;


	public int getCastId() {
		return castId;
	}

	public void setCastId(int castId) {
		this.castId = castId;
	}

	public int getMovieId() {
		return movieId;
	}

	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}

	public int getActorId() {
		return actorId;
	}

	public void setActorId(int actorId) {
		this.actorId = actorId;
	}

	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public Cast(String characterName, int movieId, int actorId) {
		super();
		this.characterName = characterName;
		this.movieId = movieId;
		this.actorId = actorId;
	}

	public Cast() {}
	
}
