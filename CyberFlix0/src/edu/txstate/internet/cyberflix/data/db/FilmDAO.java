package edu.txstate.internet.cyberflix.data.db;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import edu.txstate.internet.cyberflix.data.actor.Actor;
import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.data.film.FilmCategory;
import edu.txstate.internet.cyberflix.data.film.Film.FilmRating;
import edu.txstate.internet.cyberflix.data.helper.FilmFactory;

/**
 * @author Two
 *
 */
public class FilmDAO extends DAO {
	private final static Logger LOGGER = Logger.getLogger(FilmDAO.class.getName());
	
	private static final int    FILM_ID_COLUMN          = 1;
	private static final int    FILM_TITLE_COLUMN       = 2;
	private static final int    FILM_DESCRIPTION_COLUMN = 3;
	private static final int    FILM_LENGTH_COLUMN      = 4;
	private static final int    FILM_RATING_COLUMN      = 5;
	private static final int    FILM_RELEASE_YEAR       = 6;
	
	private static final String FILM_SELECT_STRING      = "SELECT film.film_id, film.title, film.description," +
			"film.length, film.rating, film.release_year ";
	private static final String CATEGORY_CLAUSES = " FROM film, film_category WHERE film.film_id = film_category.film_id AND film_category.category_id =";
	private static final String ALPHABETICAL_CLAUSES = "FROM film WHERE film.title LIKE ?%";

	
	public List <Film> findFilmsByAttributes (String title, String description, int length, FilmRating i) {
		String selectString = buildString (title, description, length, i);
		List <Film> films = null;
		Connection dbConnection = null;
		try {
			dbConnection = DAO.getDBConnection();
			Statement statement 	= dbConnection.createStatement();
			ResultSet results       = statement.executeQuery(selectString);
			films = buildResults (results);
			dbConnection.close();
		} catch (SQLException e) {
			System.err.println("FilmDAO.findFilmsByAttributes: " + e.toString());
			LOGGER.severe(e.toString());
			closeQuietly(dbConnection);
		}	
		return films;
	}

	@Override
	public void save(Object anObject) throws SQLException {
		// This will be a no-op because we won't allow changes to films
	}
	
	//get list of 5 newest films
	public List <Film> findNewestFilms(int maxNew)
	{
		List <Film>films = new ArrayList <Film> ();
	
		try {
			Connection cn = DAO.getDBConnection();
			Statement statement = cn.createStatement();
			String statementString = FILM_SELECT_STRING + " FROM film ORDER BY film.release_year DESC";
			ResultSet result = statement.executeQuery(statementString);

			int c = 0;
			while(result.next() && c < maxNew)
			{
				int filmID, length;
				String title, description, releaseYear, rating;
				FilmRating rawRating;
				filmID = result.getInt("film.film_id");
				title = result.getString("film.title");
				description = result.getString("film.description");
				releaseYear = result.getString("film.release_year");
				length = result.getInt("film.length");
				rating = result.getString("film.rating");
				rawRating = FilmFactory.convert(rating);
				
				Film film = new Film(filmID, title, description, releaseYear, length, rawRating);
				films.add(film);
				c++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}
	
	public List <Film> findFilmsByCategory(FilmCategory category)
	{
		List <Film> films = new ArrayList <Film>();
		try {
			Statement statement = Driver.conn.createStatement();
			String statementString;
			StringBuilder stringBuilder = new StringBuilder(FILM_SELECT_STRING);
			stringBuilder.append(CATEGORY_CLAUSES);
			stringBuilder.append(category.ordinal());
			statementString = stringBuilder.toString();
			
			ResultSet result = statement.executeQuery(statementString);

			while(result.next())
			{
				int filmID, length;
				String title, description, releaseYear, rating;
				FilmRating rawRating;
				filmID = result.getInt("film.film_id");
				title = result.getString("film.title");
				description = result.getString("film.description");
				releaseYear = result.getString("film.release_year");
				length = result.getInt("film.length");
				rating = result.getString("film.rating");
				rawRating = FilmFactory.convert(rating);
				
				Film film = new Film(filmID, title, description, releaseYear, length, rawRating);
				films.add(film);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return films;
	}
	
	public List <Film> findFilmsAlphabetically(String firstCharacter)
	{
		List <Film> films = new ArrayList <Film>();
		try {
			Connection cn = DAO.getDBConnection();
			Statement statement = cn.createStatement();
			String statementString = FILM_SELECT_STRING + "FROM film WHERE film.title LIKE '" + firstCharacter + "%'";
			System.out.println(statementString);
			ResultSet result = statement.executeQuery(statementString);

			while(result.next())
			{
				System.out.println("asdf");
				int filmID, length;
				String title, description, releaseYear, rating;
				FilmRating rawRating;
				filmID = result.getInt("film.film_id");
				title = result.getString("film.title");
				description = result.getString("film.description");
				releaseYear = result.getString("film.release_year");
				length = result.getInt("film.length");
				rating = result.getString("film.rating");
				rawRating = FilmFactory.convert(rating);
				
				Film film = new Film(filmID, title, description, releaseYear, length, rawRating);
				films.add(film);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return films;
	}
	
	public Film getFilmDetail(Film film)
	{
		ActorDAO actorGetter = new ActorDAO();
		
		int filmID, length;
		String title, description, releaseYear;
		FilmRating rating;
		List <Actor> actorList = new ArrayList <Actor> ();
		
		filmID = film.getFilmID();
		length = film.getLength();
		title = film.getTitle();
		description = film.getDescription();
		releaseYear = film.getReleaseYear();
		rating = film.getRating();
		actorList = actorGetter.getActorsInFilm(film);
		
		Film currentFilm = new Film(filmID, title, description, releaseYear, length, rating, actorList);
		
		return currentFilm;
	}
	
	public List <Actor> findActorsInFilm(Film film)
	{
		ActorDAO actorGetter = new ActorDAO();
		List <Actor> actorList = new ArrayList <Actor> ();
		
		actorList = actorGetter.getActorsInFilm(film);
		
		return actorList;
	}
	
	/**
	 * Build the WHERE clause for this query based on the parameters passed.
	 * If a string parameter is null or the empty string it won't be used.
	 * If an integer parameter is zero, it won't be used.
	 * If rating is null or unrated (UR) it won't be used 
	 * @param titleSubstring matches a substring in the movie's title
	 * @param descriptionSubstring matches a substring in the movie's description
	 * @param lengthMaximum matches any movie of running time lengthMaximum or less
	 * @param ratingMaximum matches any movie of with specified ratingMaximum or less
	 * @return String
	 */
	private String buildString (String titleSubstring, String descriptionSubstring, int lengthMaximum, FilmRating ratingMaximum) {
		final String OPENING_QUOTED_WILD_CARD = "'%";
		final String CLOSING_QUOTED_WILD_CARD = "%'";
		
		int numberWhereClauses = 0;
		StringBuilder stringBuilder = new StringBuilder(FILM_SELECT_STRING);
		stringBuilder.append(" FROM ").append(getDBName()).append(".film").append(" WHERE ");
		
		// create the clause to select any movie that contains titleSubstring in its title
		if ((titleSubstring != null) && (titleSubstring != "")) {
			stringBuilder.append("title like ").
			append(OPENING_QUOTED_WILD_CARD).
			append(titleSubstring).
			append(CLOSING_QUOTED_WILD_CARD);
			numberWhereClauses++;
		}
		
		// create the clause to select any movie that contains descriptionSubstring in its description
		if ((descriptionSubstring != null) && (descriptionSubstring != "")) {
			if (numberWhereClauses != 0) {
				stringBuilder.append(" AND ");
			}
			stringBuilder.append("description like ").
			append(OPENING_QUOTED_WILD_CARD).
			append(descriptionSubstring).
			append(CLOSING_QUOTED_WILD_CARD);
			numberWhereClauses++;
		}
		
		// create the clause to select any movie that has a running time <= lengthMaximum
		if (lengthMaximum > 0)  {
			if (numberWhereClauses != 0) {
				stringBuilder.append(" AND ");
			}
			stringBuilder.append("length <= ").append(String.valueOf(lengthMaximum));
			numberWhereClauses++;
		}
		
		// create the clause to select any movie with a rating <= ratingMaximum
		// Note: Sakila stores movie ratings as enums that can be queried by their ordinal 
		// values. Ordinals for MySQL enum fields are assigned in order of declaration, just
		// as they are in Java; however, MySQL ordinals begin with 1, not 0
		if ((ratingMaximum != null) && (ratingMaximum != FilmRating.UR)){
			if (numberWhereClauses != 0) {
				stringBuilder.append(" AND ");
			}
			int databaseRatingOrdinal = ratingMaximum.ordinal() + 1;
			stringBuilder.append("rating <= ").append(databaseRatingOrdinal);
			numberWhereClauses++;
		}
		String selectString = stringBuilder.toString();
		LOGGER.info(selectString);
		return selectString;
	}

	private List<Film> buildResults (ResultSet results) {
		ArrayList <Film> films  = new ArrayList <Film> ();
		FilmFactory filmFactory = new FilmFactory ();
		try {
			while (results.next()) {
				int    id             =  results.getInt   (FILM_ID_COLUMN );
				String filmTitle       = results.getString(FILM_TITLE_COLUMN );
				String filmDescription = results.getString(FILM_DESCRIPTION_COLUMN);
				int    filmLength      = results.getInt   (FILM_LENGTH_COLUMN);
				String filmRating      = results.getString(FILM_RATING_COLUMN);
				String release_year    = results.getString(FILM_RELEASE_YEAR);
				FilmRating convertedRating = FilmFactory.convert(filmRating);
				Film film = filmFactory.makeFilm(id, filmTitle, filmDescription, release_year, filmLength, convertedRating);
				films.add(film);
			}
		} catch (SQLException e) {
			LOGGER.severe(e.toString());
		}
		return films;
	}
}