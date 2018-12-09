package game;

import java.util.HashSet;
import java.util.Set;

public class MatchController {

    private Set<Character> foundCharacters;
    private String word;
    private IOService io;

    public MatchController(String word, IOService io) {
        foundCharacters = new HashSet<>();
        this.word = word;
        this.io = io;
    }

    private boolean wordCompleted() {
        boolean foundMissing = false;
        for (int i = 0; i < word.length() && !foundMissing; i++)
            foundMissing = !foundCharacters.contains(word.charAt(i));
        return !foundMissing;
    }

    public int loopForUserGuesses() throws MatchIsLostException {
        int wrongGuess = 0;
        boolean isGameWon = false;

        while (wrongGuess++ < HangmanAssets.MAX_ATTEMPTS && (isGameWon = !wordCompleted())) {

            io.printGuessesAndMaskedWord(wrongGuess, word, foundCharacters);
            char guess = io.askUserForGuess();

            if (word.indexOf(guess) >= 0)
                foundCharacters.add(guess);
            else
                io.renderHangman(wrongGuess);
        }

        if (isGameWon)
            return wrongGuess;
        else
            throw new MatchIsLostException();
    }
}
