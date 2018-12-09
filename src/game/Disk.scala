package game

/** Handles read/write from/to Disk.
  * Reading uses java.util.Scanner
  * Writing uses java.io
  */
object Disk {

  def loadAllResults() = ???

  def appendResult(playerName: String, points: Int, fileName: String) = {
    val fil = new java.io.File(fileName)
    val scannad = new java.util.Scanner(fil)
    val nyRad: String = ("Namn: " + playerName + "\n" + "Poäng: " + points.toString)

    val ändrad = scannad.toString + "\n" + nyRad
    saveString(ändrad, fileName)

  }

  /** Save `text` to a text file called `fileName`. */
  private def saveString(text: String, fileName: String): Unit = {
    val f = new java.io.File(fileName)
    val pw = new java.io.PrintWriter(f, "UTF-8")
    try pw.write(text) finally pw.close()
  }
}
