package edu.txstate.internet.cyberflix.utils;

import java.util.ArrayList;

public class MovieImageHandler {
	private static int count = -1;
	
	private static ArrayList <String> movies = new ArrayList<String>();
    
	public static String nextMovieTitle () {
		if (count == -1) {
			movies.add("1.jpg");
			movies.add("2.jpg");
			movies.add("3.jpg");
			movies.add("4.jpg");
			movies.add("5.jpg");
			count = 0;
		}
		if (count == movies.size()-1) count = 0;
		int indexToReturn = count;
		count ++;
		return movies.get(indexToReturn);
	}

}
