package game

import scala.collection.mutable.ListBuffer

/** Handles read/write from/to Disk.
  * Reading uses java.util.Scanner
  * Writing uses java.io
  */
object Disk {


  def loadAllResults(fileName: String) : Vector[(String, Int)] = {
    // from mutable to immutable
    return loadAllResultsMutable(fileName).toVector
  }

  private def loadAllResultsMutable(fileName: String) : ListBuffer[(String, Int)] = {

    val file = new java.io.InputStreamReader(
      new java.io.FileInputStream(fileName),
      java.nio.charset.Charset.forName("UTF-8").newDecoder()
    )
    val scanner = new java.util.Scanner(file)
    var scores = new ListBuffer[(String, Int)]()

    while (scanner.hasNextLine()) {
      val scoreLine = scanner.nextLine().split(';')
      scores += new Tuple(scoreLine(0), scoreLine(1).toInt)
    }
    file.close()

    return scores
  }

  def appendResult(fileName: String, playerName: String, points: Int) = {
    val file = new java.io.OutputStreamWriter(
      new java.io.FileOutputStream(fileName),
      java.nio.charset.Charset.forName("UTF-8").newEncoder()
    )
    file.append(playerName + ";" + points)
    file.close()
  }
}
