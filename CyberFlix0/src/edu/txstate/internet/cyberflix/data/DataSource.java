package edu.txstate.internet.cyberflix.data;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;

import edu.txstate.internet.cyberflix.data.actor.Actor;
import edu.txstate.internet.cyberflix.data.actor.ActorInventory;
import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.data.film.FilmCatalog;
import edu.txstate.internet.cyberflix.data.helper.ActorReader;
import edu.txstate.internet.cyberflix.data.helper.FilmActorBuilder;
import edu.txstate.internet.cyberflix.data.helper.FilmActorReader;
import edu.txstate.internet.cyberflix.data.helper.FilmReader;
import edu.txstate.internet.cyberflix.data.strategy.SelectorStrategy;
import edu.txstate.internet.cyberflix.data.strategy.StrategyFindFilmByDescription;
import edu.txstate.internet.cyberflix.data.strategy.StrategyFindFilmByLength;
import edu.txstate.internet.cyberflix.data.strategy.StrategyFindFilmByRating;
import edu.txstate.internet.cyberflix.data.strategy.StrategyFindFilmByTitle;
import edu.txstate.internet.cyberflix.utils.ServletUtils;

public class DataSource {

	final static String FILM_FILE             = "films.csv";
	final static String ACTORS_FILE           = "actors.csv";
	final static String FILM_ACTORS_LINK_FILE = "film_actors.csv";
	
	public static void init () {
		String realPath = ServletUtils.getProjectInputFilesPath();
		FilmReader aReader = new FilmReader ();
		List <Film> films   = aReader.readFilmFile(realPath, FILM_FILE);
        FilmCatalog filmInventory = FilmCatalog.getInstance();
        filmInventory.addAll (films);
        
		ActorReader actorReader = new ActorReader ();
		List <Actor> actors = actorReader.readActorFile(realPath, ACTORS_FILE);
		ActorInventory actorInventory = ActorInventory.getInstance();
		actorInventory.addAll(actors);
		
		FilmActorReader filmActorReader = new FilmActorReader ();
		List <SimpleEntry <Integer, Integer>> pairs = filmActorReader.readFilmActorFile(realPath, FILM_ACTORS_LINK_FILE);
		
		FilmActorBuilder builder = new FilmActorBuilder ();
		builder.build(filmInventory, actorInventory, pairs);
	}
	
	public static List <Film>findFilmByTitle (String title) {
	    SelectorStrategy strategy = new StrategyFindFilmByTitle (title);
		return findFilmByStrategy (strategy);
	}

	public static List <Film> findFilmByDescription (String description) {
	    SelectorStrategy strategy = new StrategyFindFilmByDescription (description);
		return findFilmByStrategy (strategy);
	}

	public static List <Film> findFilmByLength (int length) {
	    SelectorStrategy strategy = new StrategyFindFilmByLength (length);
		return findFilmByStrategy (strategy);
	}
	
	public static List <Film> findFilmByRating (Film.FilmRating rating) {
		SelectorStrategy strategy = new StrategyFindFilmByRating (rating);
		return findFilmByStrategy(strategy);
	}
	
	private static List <Film> findFilmByStrategy (SelectorStrategy strategy) {
		List <Film>foundFilms = FilmCatalog.getInstance().findFilmByStrategy(strategy);
		return foundFilms;
	}
	
	
}
