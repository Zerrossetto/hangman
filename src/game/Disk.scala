package game

import scala.collection.mutable.ListBuffer

/** Handles read/write from/to Disk.
  * Reading uses java.util.Scanner
  * Writing uses java.io
  */
object Disk {


  def loadAllResults(fileName: String) : Vector[(String, Int)] = {
    // from mutable to immutable
    loadAllResultsMutable(fileName).toVector
  }

  private def loadAllResultsMutable(fileName: String) : ListBuffer[(String, Int)] = {

    var scores = new ListBuffer[(String, Int)]()
    val file = new java.io.InputStreamReader(
      new java.io.FileInputStream(fileName),
      java.nio.charset.Charset.forName("UTF-8").newDecoder()
    )
    val scanner = new java.util.Scanner(file)

    try {
      while (scanner.hasNextLine) {
        val scoreLine = scanner.nextLine().split(';')
        scores += Tuple2(scoreLine(0).toString, scoreLine(1).toInt)
      }
    } finally {
      scanner.close()
      file.close()
    }
    scores
  }

  def appendResult(fileName: String, playerName: String, points: Int) : Unit = {
    val file = new java.io.OutputStreamWriter(
      new java.io.FileOutputStream(fileName),
      java.nio.charset.Charset.forName("UTF-8").newEncoder()
    )
    file.append(playerName + ";" + points)
    file.close()
  }
}
