11.2.1 Requirements

1. When your program runs, you should be able to restart multiple games one after another without the need to exit the program.
2. For each game round, the player's name should be saved in a text file with associated results.
3. After each game round, a high-score list of best points will be displayed, and upon request all results for a particular player should be displayed.
4. The playing time of each game round shall be measured and saved together with the score for each player. The points for each game round should in some way depend on the playing time.
5. Your Java code should use at least one of the data structures ArrayList, HashSet, HashMap out of the package java.util, as well as at least one array in Java code.
6. Your game should be written primarily in Java, but around 20% of the code rows should be in Scala according to the following guidelines:
    a. The main program shall be in Scala and main should be small, around 10 rows of code.
    b. Saving to a text file must be done using Scala, and text file-reading should be done with help of java.util.Scanner in Scala code (for the training you should use the JDK documentation of java.util.Scanner and not use scala.io.Source).
    c. You must collect all file management in a Scala single object with the name Disk.
    d. In any part of the Scala code of your application, use conversion methods- asScala and asJava after importscala.collection.JavaConverters._ for to convert between java.util.ArrayList and appropriate collections in Scala's standard library.
