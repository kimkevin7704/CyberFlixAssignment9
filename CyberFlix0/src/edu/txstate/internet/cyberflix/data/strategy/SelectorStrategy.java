package edu.txstate.internet.cyberflix.data.strategy;

import edu.txstate.internet.cyberflix.data.film.Film;

public abstract class SelectorStrategy  {
	public abstract boolean meetsCriteria (Film searchCandidate);
}