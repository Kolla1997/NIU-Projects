import java.io.*;
import java.util.*;
import static java.lang.Character.*;

class BioinformaticsProject2 {
  // Initializing the Variables
  public static int gapCollectiveMatchCount = 0;//
  public static int gapCollectiveMismatchCount = 0;//
  public static int gapCollectiveGapCount = 0;//
  public static int noofgapsbylength[] = new int[1000000];//
  public static int maxnoofgaps = 0;//

  public static void AddFinalCount(int matchCount, int mismatchCount, int noofgaps) {
    gapCollectiveMatchCount += matchCount;
    gapCollectiveMismatchCount += mismatchCount;
    gapCollectiveGapCount += noofgaps;
  }

  // This method Returns the index to increase the Count

  public static void gapCountReturns(String x, String y) {

    int matchCount = 0;
    int mismatchCount = 0;
    int transitionsCount = 0;
    int transversionsCount = 0;
    int noofgaps = 0;//
    boolean indelGap = false;//
    int currGapLen = 0;//
    int string1_len = x.length();
    int string2_len = y.length();

    if (string2_len < string1_len) // Checks if the String2 lenghth is less than String 1 length
    {
      string1_len = string2_len; // Assigns the lenght which has lower value
    } else {
      string2_len = string1_len;
    }

    for (int i = 0; i < string1_len; i++) {
      char char1 = toUpperCase(x.charAt(i));
      char char2 = toUpperCase(y.charAt(i));

      if (char1 != '-' && char2 != '-') // If it has this characters it continues without increment else
      {
        if (indelGap) {
          if (maxnoofgaps < currGapLen) {
            maxnoofgaps = currGapLen;
          }
          noofgapsbylength[currGapLen]++;
        }
        indelGap = false;
        if (char1 != char2) // If there is a mismatch in the chromosomes.it increases the count
        {
          mismatchCount++;
          if ((char1 == 'A' && char2 == 'G') || (char1 == 'G' && char2 == 'A') || (char1 == 'C' && char2 == 'T')
              || (char1 == 'T' && char2 == 'C')) // If the characters are like these then transition count increases
          {
            transitionsCount++;
          } else {
            transversionsCount++; // Else it increases the transeversion count
          }
        } else {
          matchCount++;
        }
      } else {
        if (indelGap) {
          currGapLen++;
        } else {
          indelGap = true;
          currGapLen = 1;
          noofgaps++;
        }

      }
    }
    if (indelGap) {
      if (maxnoofgaps < currGapLen) {
        maxnoofgaps = currGapLen;
      }
      noofgapsbylength[currGapLen]++; // update count of gaps by length
    }
    AddFinalCount(matchCount, mismatchCount, noofgaps);
    int totalnoofgapcounts = 0;
    float gapRate = ((float) gapCollectiveGapCount)
        / (gapCollectiveMatchCount + gapCollectiveMismatchCount + gapCollectiveGapCount);

    System.out.println("Matches Count:  " + gapCollectiveMatchCount);
    System.out.println("Mosmatches Count  " + gapCollectiveMismatchCount);
    System.out.print("Gap rate:" + gapRate + "\n");

    for (int i = 1; i <= maxnoofgaps; i++) {
      totalnoofgapcounts += noofgapsbylength[i];
    }

    System.out.print("Gap Length (Bases) \t 	Gap Count \tGap Frequency" + "\n");

    for (int i = 1; i <= maxnoofgaps; i++) {
      System.out.print("\t\t" + i + "\t\t" + noofgapsbylength[i] + "\t\t"
          + ((float) noofgapsbylength[i]) / gapCollectiveGapCount + "\n");
    }

    System.out.print("\t\tTotal" + "\t\t" + totalnoofgapcounts + "\t\t"
        + ((float) totalnoofgapcounts) / gapCollectiveGapCount + "\n");
    System.out.println("\n");
    ResetAll();
  }

  // This method resets all the values for the another allignment value
  public static void ResetAll() {
    gapCollectiveMatchCount = 0;
    gapCollectiveMismatchCount = 0;
    gapCollectiveGapCount = 0;

    for (int j = 0; j < 1000000; j++) {
      noofgapsbylength[j] = 0;
    }

  }

  public static void main(String args[]) throws IOException

  {
    // Takes 3 files as input to produce the tabel
    String input[] = {
        "/Users/dineshkolla/Desktop/Masters Back_up/Master's/Bio/Term Project/sars-cov2/pw/sars2.omicron.sing.maf" };
    for (int k = 0; k < 3; k++) {
      BufferedReader files = new BufferedReader(new FileReader(input[k]));

      String genes[] = new String[2];
      int no_gens = 0;
      String l = files.readLine();

      while (l != null) {
        if (l.length() > 0) {
          char character = l.charAt(0);
          if (character == 's') // Checks if the character is s
          {
            int location = l.lastIndexOf(" ");
            genes[no_gens++] = l.substring(location + 1);

            if (no_gens == 2) // Checks if the genes count is 2
            {

              System.out.println("\n" + "orf1a" + "\n");
              gapCountReturns(genes[0].substring(265, 13467), genes[1].substring(265, 13467));
              System.out.println("\n" + "orf1b" + "\n");
              gapCountReturns(genes[0].substring(13467, 21554), genes[1].substring(13467, 21554));
              System.out.println("\n" + "S" + "\n");
              gapCountReturns(genes[0].substring(21562, 25383), genes[1].substring(21562, 25383));
              System.out.println("\n" + "E" + "\n");
              gapCountReturns(genes[0].substring(26244, 26471), genes[1].substring(26244, 26471));
              System.out.println("\n" + "M" + "\n");
              gapCountReturns(genes[0].substring(26522, 27190), genes[1].substring(26522, 27190));
              System.out.println("\n" + "N" + "\n");
              gapCountReturns(genes[0].substring(28273, 29532), genes[1].substring(28273, 29532));
              // Gets the count of matches,mismatches ,trasnversion and transition count
              no_gens = 0;
            }
          }
        } // ........length of line greather than zero
        l = files.readLine();
      } // while close
      files.close();
      /*
       * int totalnoofgapcounts = 0;
       * float gapRate = ((float) collectiveGapCount)
       * / (collectiveMatchCount + collectiveMismatchCount + collectiveGapCount);
       * System.out.print("Gap rate:" + gapRate + "\n");
       * 
       * for (int i = 1; i <= maxnoofgaps; i++) {
       * totalnoofgapcounts += noofgapsbylength[i];
       * }
       * 
       * System.out.print("Gap Length (Bases) \t 	Gap Count \tGap Frequency" +
       * "\n");
       * 
       * for (int i = 1; i <= maxnoofgaps; i++) {
       * System.out.print("\t\t" + i + "\t\t" + noofgapsbylength[i] + "\t\t"
       * + ((float) noofgapsbylength[i]) / collectiveGapCount + "\n");
       * }
       * 
       * System.out.print("\t\tTotal" + "\t\t" + totalnoofgapcounts + "\t\t"
       * + ((float) totalnoofgapcounts) / collectiveGapCount + "\n");
       * System.out.println("\n");
       */

    } // for close
  } // main method close
} // class close
