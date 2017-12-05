package edu.txstate.internet.cyberflix.data.film;

import java.util.ArrayList;
import java.util.List;

import edu.txstate.internet.cyberflix.data.actor.Actor;

public class Film {
	public enum FilmRating {
		G, PG, PG_13, R, NC_17, X, UR
	}
	
	private int filmID;
	private String title;
	private String description;
	private String releaseYear;
	private int length;
	private FilmRating rating;
	private String     posterImage;
	private List <Actor> actors;
	private String actorsString;

	public Film(int filmID, String title, String description,
			String releaseYear, int length, FilmRating rating) {
		super();
		this.filmID = filmID;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.length = length;
		this.rating = rating;
		this.actors = new ArrayList <Actor> ();
	}
	
	public Film(int filmID, String title, String description,
			String releaseYear, int length, FilmRating rating, List <Actor> actors) {
		super();
		this.filmID = filmID;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.length = length;
		this.rating = rating;
		this.actors = actors;
	}
	
	public void addActor (Actor anActor) {
		if (!actors.contains(anActor)) actors.add(anActor);
	}
	
	public List <Actor>getActors () {
		return actors;
	}
	
	public String getDescription() {
		return description;
	}

	public int getFilmID() {
		return filmID;
	}

	public int getLength() {
		return length;
	}
	
	public String getPosterImage () {
		return posterImage;
	}

	public FilmRating getRating() {
		return rating;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public String getTitle() {
		return title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public void setPosterImage (String image) {
		this.posterImage = image;
	}

	public void setRating(FilmRating rating) {
		this.rating = rating;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "Film [filmID=" + filmID + ", title=" + title + ", description="
				+ description + ", releaseYear=" + releaseYear + ", length="
				+ length + ", rating=" + rating + "]";
	}

	public String getActorsString() {
		return actorsString;
	}

	public String setActorsString(List<Actor> actors) {
		actorsString = " ";
		for(int i = 0; i < actors.size(); i++)
		{
			if(i < actors.size()-1)
			{
				actorsString = actorsString + actors.get(i).getFirstName() + " " + actors.get(i).getLastName() + ", ";				
			}
			else
				actorsString = actorsString + actors.get(i).getFirstName() + " " + actors.get(i).getLastName();
		}
		return actorsString;
	}
}
