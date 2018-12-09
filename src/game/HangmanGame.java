package game;

public class HangmanGame {

	private GameStatistics stats;
	private WordsRepository repo;
	private IOService io;
	
	public HangmanGame() {
		stats = new GameStatistics();
		repo  = new WordsRepository();
		io = new IOService();
		repo.init();
	}

	public void play() {

		boolean playAgain = true;
		MatchController match;
		String word;

		io.printWelcomeMessage();

		while (playAgain) {

			stats.gameStarted();
			word = repo.randomWord();
			match = new MatchController(word, io);
			int wrongGuesses = 0;

			try {
				wrongGuesses = match.loopForUserGuesses();
				io.printMatchIsWon(wrongGuesses);
			} catch (MatchIsLostException e) {
				io.printMatchIsLost();
			}

			int end = stats.gameEnded();

			int poäng = Math.max((word.length() - wrongGuesses) * 50 - end, 0);

			io.printResults(word, wrongGuesses, poäng, stats.getTotalTime());

			playAgain = io.askUserToPlayAgain();
		}

		io.close();

	}
	

}
