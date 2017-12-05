package edu.txstate.internet.cyberflix.data.strategy;

import edu.txstate.internet.cyberflix.data.film.Film;
import edu.txstate.internet.cyberflix.data.strategy.SelectorStrategy;

public class StrategyFindFilmByDescription extends SelectorStrategy {
	String description;
	
	public StrategyFindFilmByDescription (String description) {
		this.description = description;
	}

	@Override
	public boolean meetsCriteria(Film aFilm) {
		String filmTitle = aFilm.getTitle().toLowerCase();
		String filmDescription = aFilm.getDescription().toLowerCase();
		
		if ((filmTitle.indexOf(description) != -1) ||
	        (filmDescription.indexOf(description) != -1)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "StrategyFindFilmByDescription [description=" + description + "]";
	}
	
	

}
