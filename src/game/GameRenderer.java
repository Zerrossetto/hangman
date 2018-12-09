package game;

import static java.lang.System.out;

import java.util.Set;

public class GameRenderer {
		
	private static final String[] HANGMAN_FIGURE = new String[] {
			" ======  ",
			" |/   |  ",
			" |    O  ", 
			" |   -|- ", 
			" |   / \\ ",
			" |       ", 
			" |       ", 
			" ==============\n" + 
			"\n" + 
			"============   RIP  :(" 
	};
	
	private String secret;
	
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
	public static int hangmanLength() {
		return HANGMAN_FIGURE.length;
	}
	
	public String renderHangman(int n) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < n; i++) {
			result.append(HANGMAN_FIGURE[i]);
			if (i < n - 1) {
				result.append("\n");
			}
		}
		return result.toString();
	}
	
	public String mask(Set<Character> found) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < secret.length(); i++) {
			if (found.contains(secret.charAt(i))) {
				result.append(secret.charAt(i));
			} else {
				result.append('_');
			}
		}
		return result.toString();
	}
	
	public void printResults(boolean isGameWon, int wrongGuess, int poäng, int totalTime) {
		if (isGameWon) {
			out.println("YOU WON! :)");
			out.println(renderHangman(wrongGuess));
			out.print("\n");
		} else {
			out.println("YOU HANG!");
			poäng = 0;
		}

		out.println("Du fick " + poäng + " poäng!");
		out.println("Rundan tog " + totalTime + " sekunder.");
		out.println("Rätt svar: " + secret);
		out.println("Antal felgissningar: " + wrongGuess);
		out.println();

		String omgångsResultat = "Antal poäng " + poäng + "\n" + "Rundan tog: " + totalTime
				+ " sekunder. + \n" + "Ordet var: " + secret + "\n" + "Antal felgissningar " + wrongGuess;
		out.println(omgångsResultat);
	}
}
