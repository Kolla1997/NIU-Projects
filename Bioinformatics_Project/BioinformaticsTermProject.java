import java.io.*;
import java.util.*;
import static java.lang.Character.*;

public class BioinformaticsTermProject {
  public static int collectiveMatchingCount = 0;
  public static int gapCollectiveMatchCount = 0;
  public static int collectiveMismatchingCount = 0;
  public static int gapCollectiveMismatchCount = 0;
  public static int collectiveCountTransitions = 0;
  public static int gapCollectiveCountTransitions = 0;
  public static int collectiveCountTransversions = 0;
  public static int gapCollectiveCountTransversions = 0;
  public static int gapCollectiveGapCount = 0;

  public static int alignpairs[][] = new int[4][4];
  public static int noofgapsbylength[] = new int[1000000];
  public static String chromoChars = "ACGT";
  public static int maxnoofgaps = 0;

  public static int indexReturn(char c) {
    int k = -1;
    for (int i = 0; i < 4; i++) {
      if (c == chromoChars.charAt(i)) {
        k = i;
      }
    }
    return k;
  }

  // Method to calculate the Substitution Rate

  public static void subCountReturns(String x, String y) {
    // System.out.println(x.length());

    int countMatches = 0;
    int countMisMatches = 0;
    int countTransactions = 0;
    int countTransversions = 0;
    int string_len1 = x.length();
    int string_len2 = y.length();

    if (string_len2 < string_len1) {
      string_len1 = string_len2;
    } else {
      string_len2 = string_len1;
    }

    for (int i = 0; i < string_len1; i++) {
      char char1 = toUpperCase(x.charAt(i));
      char char2 = toUpperCase(y.charAt(i));
      int k1 = indexReturn(char1);
      int k2 = indexReturn(char2);

      if (char1 == '-' || char2 == '-' || k1 < 0 || k2 < 0) {
        continue;
      }
      alignpairs[k1][k2]++;

      if (k1 != k2) {
        countMisMatches++;
        if ((char1 == 'A' && char2 == 'G') || (char1 == 'G' && char2 == 'A') || (char1 == 'C' && char2 == 'T')
            || (char1 == 'T' && char2 == 'C')) {
          countTransactions++;
        } else {
          countTransversions++;
        }

      } else {

        countMatches++;

      }
    }

    collectiveMatchingCount += countMatches;
    collectiveMismatchingCount += countMisMatches;
    collectiveCountTransitions += countTransactions;
    collectiveCountTransversions += countTransversions;

    float substitutionRate = ((float) collectiveMismatchingCount)
        / (collectiveMatchingCount + collectiveMismatchingCount);

    float titvratio = new Float(collectiveCountTransitions) / collectiveCountTransversions;

    System.out.println("Matches Count:  " + collectiveMatchingCount);
    System.out.println("Mismatches Count: " + collectiveMismatchingCount);
    System.out.println("Transition Count: " + collectiveCountTransitions);
    System.out.println("Transversion Count: " + collectiveCountTransversions);
    System.out.println("\n" + "Substitution rate:" + substitutionRate);
    System.out.println("ti/tv ratio:" + titvratio);
    String bases = "ACGT";
    System.out.println("\n" + "Pair Counts\tA \tC \tG \tT");
    for (int k = 0; k < 4; k++) {
      System.out.print(bases.charAt(k) + "\t");
      for (int j = 0; j < 4; j++) {
        System.out.print("\t" + alignpairs[k][j]);
      }
      System.out.println("\n");
    }
    resetAll();
  }

  // GAP

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
    AddFinalCount(matchCount, mismatchCount, noofgaps, transitionsCount, transversionsCount);
    int totalnoofgapcounts = 0;

    float gapRate = ((float) gapCollectiveGapCount)
        / (gapCollectiveMatchCount + gapCollectiveMismatchCount + gapCollectiveGapCount);

    System.out.println("Matches Count:  " + gapCollectiveMatchCount);
    System.out.println("Mismatches Count: " + gapCollectiveMismatchCount);
    System.out.println("Transition Count: " + gapCollectiveCountTransitions);
    System.out.println("Transversion Count: " + gapCollectiveCountTransversions);
    System.out.print("\n" + "Gap rate:" + gapRate + "\n");

    for (int i = 1; i <= maxnoofgaps; i++) {
      totalnoofgapcounts += noofgapsbylength[i];
    }

    System.out.print("\n" + "Gap Length (Bases) \t 	Gap Count \tGap Frequency" + "\n");

    for (int i = 1; i <= maxnoofgaps; i++) {
      System.out.print("\t\t" + i + "\t\t" + noofgapsbylength[i] + "\t\t"
          + ((float) noofgapsbylength[i]) / gapCollectiveGapCount + "\n");
    }

    System.out.print("\t\tTotal" + "\t\t" + totalnoofgapcounts + "\t\t"
        + ((float) totalnoofgapcounts) / gapCollectiveGapCount + "\n");
    System.out.println("\n");
    gapResetAll();
  }

  // GAP
  public static void AddFinalCount(int matchCount, int mismatchCount, int noofgaps, int transitionsCount,
      int transversionsCount) {
    gapCollectiveMatchCount += matchCount;
    gapCollectiveMismatchCount += mismatchCount;
    gapCollectiveCountTransitions += transitionsCount;
    gapCollectiveCountTransversions += transversionsCount;
    gapCollectiveGapCount += noofgaps;

  }

  public static void resetAll() {
    collectiveMatchingCount = 0;
    collectiveMismatchingCount = 0;
    collectiveCountTransitions = 0;
    collectiveCountTransversions = 0;
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        alignpairs[i][j] = 0;
      }
    }
  }

  // GAP
  public static void gapResetAll() {
    gapCollectiveMatchCount = 0;
    gapCollectiveMismatchCount = 0;
    gapCollectiveCountTransitions = 0;
    gapCollectiveCountTransversions = 0;
    gapCollectiveGapCount = 0;

    for (int j = 0; j < 1000000; j++) {
      noofgapsbylength[j] = 0;
    }

  }

  public static void main(String args[]) throws IOException {

    String input[] = {
        "/Users/dineshkolla/Desktop/Masters Back_up/Master's/Bio/Term Project/sars-cov2/pw/sars2.omicron.sing.maf" };
    for (int i = 0; i < 1; i++) {
      BufferedReader files = new BufferedReader(new FileReader(input[i]));
      String genes[] = new String[2];
      int no_gens = 0;
      String l = files.readLine();

      while (l != null) {
        if (l.length() > 0) {
          char character = l.charAt(0);
          if (character == 's') {
            int location = l.lastIndexOf(" ");
            genes[no_gens++] = l.substring(location + 1);

            if (no_gens == 2) {
              System.out.println(genes[1].length());
              System.out.println("\n" + "\t\t\tGenome 'orf1a'" + "\n");
              System.out.println("SUBSTITUTION RATE\n");
              subCountReturns(genes[0].substring(265, 13467), genes[1].substring(265, 13467));
              System.out.println("\n\nGAP RATE\n");
              gapCountReturns(genes[0].substring(265, 13467), genes[1].substring(265, 13467));

              System.out.println("\n" + "\t\t\tGenome 'orf1b'" + "\n");
              System.out.println("SUBSTITUTION RATE\n");
              subCountReturns(genes[0].substring(13467, 21554), genes[1].substring(13467, 21554));
              System.out.println("\n\nGAP RATE\n");
              gapCountReturns(genes[0].substring(13467, 21554), genes[1].substring(13467, 21554));

              System.out.println("\n" + "\t\t\tGenome 'S'" + "\n");
              System.out.println("SUBSTITUTION RATE\n");
              subCountReturns(genes[0].substring(21562, 25383), genes[1].substring(21562, 25383));
              System.out.println("\n\nGAP RATE\n");
              gapCountReturns(genes[0].substring(21562, 25383), genes[1].substring(21562, 25383));

              System.out.println("\n" + "\t\t\tGenome 'E'" + "\n");
              System.out.println("SUBSTITUTION RATE\n");
              subCountReturns(genes[0].substring(26244, 26471), genes[1].substring(26244, 26471));
              System.out.println("\n\nGAP RATE\n");
              gapCountReturns(genes[0].substring(26244, 26471), genes[1].substring(26244, 26471));

              System.out.println("\n" + "\t\t\tGenome 'M'" + "\n");
              System.out.println("SUBSTITUTION RATE\n");
              subCountReturns(genes[0].substring(26522, 27190), genes[1].substring(26522, 27190));
              System.out.println("\n\nGAP RATE\n");
              gapCountReturns(genes[0].substring(26522, 27190), genes[1].substring(26522, 27190));

              System.out.println("\n" + "\t\t\tGenome 'N'" + "\n");
              System.out.println("SUBSTITUTION RATE\n");
              subCountReturns(genes[0].substring(28273, 29532), genes[1].substring(28273, 29532));
              System.out.println("\n\nGAP RATE\n");
              gapCountReturns(genes[0].substring(28273, 29532), genes[1].substring(28273, 29532));

              no_gens = 0;
            }
          }

        }
        l = files.readLine();
      }

      files.close();

    }

  }
}
