package game

object Main {
  def play(): Unit = {
    var playAgain = true
    val game = new HangmanGame()
    while (playAgain) {
      playAgain = game.start()
    }
    game.end()
  }

  def main(args: Array[String]): Unit = {
    println("Welcome to Hangman!")
    play()
  }
}
