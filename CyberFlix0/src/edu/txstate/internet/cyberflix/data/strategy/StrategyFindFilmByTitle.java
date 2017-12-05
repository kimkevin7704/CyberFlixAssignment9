package edu.txstate.internet.cyberflix.data.strategy;

import edu.txstate.internet.cyberflix.data.film.Film;

public class StrategyFindFilmByTitle extends SelectorStrategy {
	String title; 

	public StrategyFindFilmByTitle (String title) {
		this.title = title.toLowerCase();
	}

	@Override
	public boolean meetsCriteria(Film aFilm) {
		String filmTitle = aFilm.getTitle().toLowerCase();
		if (filmTitle.indexOf(title) != -1) return true;
		return false;
	}

	@Override
	public String toString() {
		return "StrategyFindFilmByTitle [title=" + title + "]";
	}
	
	
}
