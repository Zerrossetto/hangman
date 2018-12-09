package game;

public class GameStatistics {
	
	private long startTime;
	private int totalTime;
	private int gamesPlayed;
	
	public GameStatistics() {
		startTime = totalTime = gamesPlayed = 0;
	}
	
	public void gameStarted() {
		gamesPlayed++;
		startTime = System.nanoTime();
	}
	
	public int gameEnded() {
		int gameTime = Math.toIntExact((System.nanoTime() - startTime) / 1000000000);
		this.totalTime =+ gameTime;
		return gameTime;
	}
	
	public int getTotalTime() {
		return getTotalTime(true);
	}
	
	public int getTotalTime(boolean rounded) {
		if (rounded)
			return Math.round(totalTime);
		return totalTime;
	}
	
	public int getGamesPlayed() {
		return gamesPlayed;
	}
}
