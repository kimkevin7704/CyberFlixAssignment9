package edu.txstate.internet.cyberflix.data.strategy;

import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.data.film.Film.FilmRating;

public class StrategyFindFilmByRating extends SelectorStrategy {
	
	Film.FilmRating rating;

	public StrategyFindFilmByRating(FilmRating rating) {
		super();
		this.rating = rating;
	}

	@Override
	public boolean meetsCriteria(Film searchCandidate) {
		Film.FilmRating rating = searchCandidate.getRating();
		if (this.rating.ordinal() >= rating.ordinal()) return true;
		return false;
	}

}
