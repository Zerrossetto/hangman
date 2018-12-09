package game;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class WordsRepository {

	private static final String REMOTE_LIST_URL = "http://runeberg.org/words/ord.niklas.frykholm";
	private static final String REMOTE_LIST_CHARSET = "ISO-8859-1";
	private static WordsRepository instance;
	
	private List<String> words;
	
	private WordsRepository() {
		words = fetchRemote();
	}
	
	public String randomWord() {
		return words.get((int) (Math.random() * words.size()));
	}
	
	public static WordsRepository getInstance() {
		if(instance == null)
			instance = new WordsRepository();
		
		return instance;
	}
	
	private static List<String> fetchRemote() {
		
		URL url;
		ArrayList<String> words = new ArrayList<String>();
		
		try {
			url = new URL(REMOTE_LIST_URL);
		} catch (MalformedURLException e) {
			System.err.println("Error: " + e);
			throw new RuntimeException(e);
		}
		
		try (Scanner scan = new Scanner(url.openStream(), REMOTE_LIST_CHARSET)) {
			while (scan.hasNext())
				words.add(scan.next());
		} catch (Exception e) {
			System.err.println("Error: " + e);
			throw new RuntimeException(e);
		}
		return words;
	}
}
