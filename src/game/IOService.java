package game;

import static java.lang.System.out;

import java.io.InputStream;
import java.util.Scanner;
import java.util.Set;

public class IOService {

    private static final String YES = "yes";
    private static final String NO = "no";

    private Scanner scan;
    private char maskPlaceholder;

    public IOService() {
        this(System.in);
    }

    public IOService(InputStream is) {
        scan = new Scanner(is);
        maskPlaceholder = '_';
    }

    public void close() {
        scan.close();
    }

    public char askUserForGuess() {
        String guess =  null;

        do {
            if (guess != null && guess.length() != 1)
                out.print("Invalid value, insert just one letter. ");
            out.print("Make a guess: ");
            guess = scan.nextLine().toLowerCase();
        } while (guess.length() != 1);

        out.println();

        return guess.charAt(0);
    }

    public boolean askUserToPlayAgain() {
        String userChoice;

        do {
            out.println("Do you want to play again? (yes/no)");
            userChoice = scan.nextLine().toLowerCase();
        } while (!(YES.equals(userChoice) || NO.equals(userChoice)));

        return YES.equals(userChoice);
    }

    public void printWelcomeMessage() {
        out.println("Welcome to Hangman!");
    }

    public void renderHangman(int wrongGuesses) {
        for (int i = 0; i < wrongGuesses; i++)
            out.println(HangmanAssets.FIGURE[i]);
    }

    public void printGuessesAndMaskedWord(int wrongGuesses, String word, Set<Character> charsToShow) {
        out.println("\nGuesses: "+ wrongGuesses);
        out.print("Word: ");

        for (int i = 0; i < word.length(); i++) {
            if (charsToShow.contains(word.charAt(i)))
                out.print(word.charAt(i));
            else
                out.print(maskPlaceholder);
        }
        out.println("\n");
    }

    public void printMatchIsLost() {
        out.println("YOU HANG!");
    }

    public void printMatchIsWon(int wrongGuesses) {
        out.println("YOU WON! :)");
        renderHangman(wrongGuesses);
    }

    public void printResults(String word, int wrongGuesses, int poäng, int totalTime) {

        out.println("Du fick " + poäng + " poäng!");
        out.println("Rundan tog " + totalTime + " sekunder.");
        out.println("Rätt svar: " + word);
        out.println("Antal felgissningar: " + wrongGuesses);
        out.println();

        String omgångsResultat = "Antal poäng " + poäng + "\n" + "Rundan tog: " + totalTime
                + " sekunder. + \n" + "Ordet var: " + word + "\n" + "Antal felgissningar " + wrongGuesses;
        out.println(omgångsResultat);
    }
}
