package game;

import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;

import static game.GameRenderer.hangmanLength;
import static java.lang.System.out;

public class HangmanGame {
	
	private static final int GAME_LOST = Integer.MIN_VALUE;
	private static final String YES = "yes";
	private static final String NO = "no";
	
	private GameStatistics stats;
	private GameRenderer renderer;
	private Set<Character> found;
	private String secret;
	private Scanner scan;
	
	public HangmanGame() {
		stats = new GameStatistics();
		renderer = new GameRenderer();
		found = new HashSet<>();
		scan = new Scanner(System.in);
	}

	/** Start this game. Returns true if player wants to play a new game. */
	public boolean start() {
		
		String userChoice;

		do {
			out.println("Do you want to play? (yes/no)");
			userChoice = scan.nextLine().toLowerCase();
		} while (!(YES.equals(userChoice) || NO.equals(userChoice)));
		
		boolean isNewGame = YES.equals(userChoice);

		if (isNewGame) play();
		
		return isNewGame;
	}

	public void end() {
		scan.close();
	}

	private boolean allCharactersFound() {
		boolean foundMissing = false;
		int i = 0;
		while (i < secret.length() && !foundMissing) {
			foundMissing = !found.contains(secret.charAt(i));
			i++;
		}
		return !foundMissing;
	}

	private char makeGuess() {
		String guess;

		do {
			out.println("Make a guess: ");
			guess = scan.nextLine().toLowerCase();
		} while (guess.length() != 1);

		return guess.charAt(0);
	}

	private void play() {
		
		found.clear();
		secret = WordsRepository.getInstance().randomWord();
		renderer.setSecret(secret);
		
		boolean isGameWon;
		
		stats.gameStarted();
		int wrongGuess = loopForUserGuesses();
		stats.gameEnded();
		
		
		if(!(isGameWon = wrongGuess == GAME_LOST))
			wrongGuess = hangmanLength();
	
		int poäng = Math.min((secret.length() - wrongGuess) * 50 - stats.gameEnded(), 0);
		
		renderer.printResults(isGameWon, wrongGuess, poäng, stats.getTotalTime());

	}
	
	private int loopForUserGuesses() {
		int wrongGuess = 0;
		boolean isGameWon = false;

		while (wrongGuess++ < hangmanLength() && (isGameWon = !allCharactersFound())) {
			out.print("\nGuesses: "+ wrongGuess + "\t");
			out.println(renderer.mask(found));
			char guess = makeGuess();
			if (secret.indexOf(guess) >= 0)
				found.add(guess);
			else 
				System.out.println(renderer.renderHangman(wrongGuess));
		}
		
		if (isGameWon)
			return wrongGuess;
		else
			return GAME_LOST;
	}
}
