/********************************************************************
Class:     CSCI 470
Program:   Assignment 4
Author:    Dinesh Kolla
Z-number:  z1935563
Date Due:  11/14/21

Purpose:   This program takes input number from user and gives the movie he/she wanted and
 			also the top 20 movies closely related to the target movie 

Execution: Command to execute your program
           javac Hw4.java
           java Hw4 
*********************************************************************/

package hw4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Hw4 {
	static int movieNumber = 0;
	static double r = 0.0;
	static int reviewerCount = 0;
	static List<List<Integer>> reviewTableData = new ArrayList<List<Integer>>();
	static List<String> movieNameYearData = new ArrayList<String>();
	static List<Integer> movieReviewCount = new ArrayList<>();
	static int first = 0;
	static int last = 1672;
	static int position;

	// Method to read movie-names2 file
	public static void buildNameFile2() {
		String movieNames;
		try {
			File myObj = new File("/Users/dineshkolla/Downloads/movie-names2.txt");
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) // reading movie matrix file
			{
				String data = myReader.nextLine();
				movieNames = data.substring(4);
				movieNameYearData.add(movieNames);

			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error opening File.");
			e.printStackTrace();
		} // end try-catch

		System.out.println("*** No. of movie names = " + movieNameYearData.size() + "\n");

	}// end buildNameFile2

	// Method to deserialize Movie Matrix file
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<List<Integer>> buildMatrix2() {
		List<List<Integer>> tableData = new ArrayList<List<Integer>>();
		try {
			FileInputStream fis = new FileInputStream("/Users/dineshkolla/Downloads/hw3-kold-movie-matrix2.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);

			tableData = (ArrayList) ois.readObject(); // deserialize

			ois.close();
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
		} // end try-catch

		System.out.println("Table name is: movie-matrix2.ser" + "\n");
		return tableData;
	}// end buildMatrix2

	// Method to check input is numeric or not
	private static boolean isNumaric(String number) {
		int inputNum = 0;
		try {
			inputNum = Integer.parseInt(number);
			return true;
		} catch (Exception e) {
			return false;
		} // end try-catch
	}// end isNumaric

	// method to calculate the sum
	public static double calcSum(List<Integer> reviews) {
		double sum = 0;
		for (double num : reviews) {
			sum += num;
		}

		return sum;
	}// end calcSum

	// method to calculate the average
	public static double calcAvg(double sum, int length) {
		return sum / length;

	}// end calcAvg

	// method to calculate Standard Deviation
	private static double calcStandardDeviation(List<Integer> reviewSD) {
		double sum = 0.0;
		double standardDeviation = 0.0;
		int length = reviewSD.size();

		sum = calcSum(reviewSD); // calling calculate sum method
		double average = calcAvg(sum, length); // calling calculate Average method

		for (double num : reviewSD) {
			standardDeviation = standardDeviation + Math.pow(num - average, 2);
		} // end for

		return Math.sqrt(standardDeviation / (length - 1));
	}// end calcStandardDeviation

	// method to calculate Pearson Value
	private static double calcPearsonValue(List<Integer> reviewer1, List<Integer> reviewer2, List<Integer> reviews) {
		double sum = 0;
		int compNum = position + 1;
		int length = reviewer1.size();
		r = 0.0;
		sum = calcSum(reviewer1); // calling calculate sum method
		double movie1Average = calcAvg(sum, length); // calling calculate Average method

		sum = 0;
		length = reviewer2.size();
		sum = calcSum(reviewer2); // calling calculate sum method
		double movie2Average = calcAvg(sum, length); // calling calculate Average method

		double movie1SD = calcStandardDeviation(reviewer1); // calling calculate Standard Deviation method
		double movie2SD = calcStandardDeviation(reviewer2); // calling calculate Standard Deviation method

		double normalizedScore = 0;
		for (int j = 0; j < reviewer1.size(); j++) {
			double nScore1 = (reviewer1.get(j) - movie1Average) / movie1SD;
			double nScore2 = (reviewer2.get(j) - movie2Average) / movie2SD;
			normalizedScore = normalizedScore + nScore1 * nScore2;
		} // end for

		r = normalizedScore / (length - 1); // calculated r value

		if (position < 10) {
			printFirst(compNum, movie1Average, movie2Average, movie1SD, movie2SD, r, reviews, reviewer2);
		} // end if
		return r;
	}// end calcPearsonValue

	// Method to print first 10 movie values
	public static void printFirst(int compNum, double movie1Average, double movie2Average, double movie1sd,
			double movie2sd, double r2, List<Integer> reviews, List<Integer> movie2reviews) {
		System.out.println("\n" + "\n" + "Movie " + movieNumber + ", comparison movie " +
				compNum + " " + movieNameYearData.get(compNum - 1) + " , common reviewers " + reviews.size());

		System.out.println("compare movie is " + movieNameYearData.get(position));

		System.out.println("no. of common reviewers  " + movie2reviews.size());

		System.out.println("target avg    compare avg  target std   compare std    r");

		System.out.println(
				String.format("%.6f", movie1Average) + "      " + String.format("%.6f", movie2Average) + "      " +
						String.format("%.6f", movie1sd) + "     " + String.format("%.6f", movie2sd) + "      "
						+ String.format("%.6f", r) + "\n" + "\n");
	}// end printFirst

	// Method to print last 10 movie values
	public static void printLast(int compNum, List<Integer> reviews) {
		System.out.println("Movie " + movieNumber + ", comparison movie " +
				compNum + " " + movieNameYearData.get(compNum - 1) + " , common reviewers " + reviews.size());

	}// end printLast

	// Method compare Target movie with every movie
	public static double comapreMovie(List<Integer> inputList, List<Integer> targetList) {

		List<Integer> movie1reviews = new ArrayList<>();
		List<Integer> movie2reviews = new ArrayList<>();
		List<Integer> reviews = new ArrayList<>();
		int newLine = 1;
		int compNum = position + 1;
		r = 0.0;

		for (int j = 0; j < inputList.size(); j++) {
			if (inputList.get(j) != 0 && targetList.get(j) != 0) {
				reviews.add(j + 1);
				movie1reviews.add(inputList.get(j));
				movie2reviews.add(targetList.get(j));
			} // end if
		} // end for

		movieReviewCount.add(movie2reviews.size());

		if (reviewerCount < 2) {
			System.out.println("Common reviewers for movie " + compNum + ":");

			for (int i = 0; i < reviews.size(); i++) {
				if (newLine != 10) {

					System.out.print(reviews.get(i) + "\t");
					newLine++;
				} // end if
				else {
					System.out.print(reviews.get(i) + "\n");
					newLine = 1;
				} // end else
			} // end for

			System.out.println();
		} // end id
		reviewerCount++;

		if (reviews.size() > 10) {
			r = calcPearsonValue(movie1reviews, movie2reviews, reviews); // calling calcPearsonValue method to calculate
																			// r value
		} else {
			if (position < 10) {
				printLast(compNum, reviews); // calling printLast method
			} else if (position >= 1672) {
				printLast(compNum, reviews); // calling printLast method
			}
		} // end if-else

		if ((r >= -1) && (r <= 1)) {
			return r;
		} else {
			return 2;
		} // end if-else
	}// end comapreMovie

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		reviewTableData = buildMatrix2(); // Method to deserialize movie matrix file
		buildNameFile2(); // Method to read movie file
		System.out.println("Rows " + reviewTableData.size() + ", columns " + reviewTableData.get(0).size() + "\n");
		String number;
		double rValue = 0.0;
		ArrayList<GoodMovie> relevantMovies = new ArrayList<GoodMovie>();
		List<String> goodMovieNames = new ArrayList<String>();
		String nearMovies;
		Scanner scanner = new Scanner(System.in);
		System.out.print("\n" + "Movie number: ");
		number = scanner.next(); // read input from user

		while (!number.equals("q") && !number.equals("quit")) {
			if (isNumaric(number)) {
				movieNumber = Integer.parseInt(number);
				if (movieNumber > 0 && movieNumber <= movieNameYearData.size()) {
					int num = movieNumber;
					System.out.println(
							"Movie number: Movie:" + num + " " + movieNameYearData.get(movieNumber - 1) + "\n" + "\n");
					for (int i = 0; i < reviewTableData.size(); i++) {
						position = i;
						rValue = comapreMovie(reviewTableData.get(movieNumber - 1), reviewTableData.get(i));
						if ((rValue >= -1) && (rValue <= 1)) {
							relevantMovies.add(new GoodMovie(movieNameYearData.get(i), rValue));
							// Calling class good movies to store top 20 movies most related to the target
							// movie
						}
					}
					for (GoodMovie name : relevantMovies) {
						goodMovieNames.add(name.getrValue() + "|" + name.getMovie());
					}
					Collections.sort(goodMovieNames, Collections.reverseOrder());
					nearMovies = goodMovieNames.get(1);
					double b1 = 0.0;
					b1 = Double.parseDouble(nearMovies.substring(0, 7));
					if (b1 != 0.0) {
						System.out.println("\n" + "\n" + "\n" + "\t" + "R        No.         Reviews             Name");
						for (int i = 0; i < 20; i++) {
							nearMovies = goodMovieNames.get(i);
							double b = 0.0;
							b = Double.parseDouble(nearMovies.substring(0, 7)); // Printing top 20 movies in descending
																				// order based on there r value
							String s = nearMovies.substring(9);
							int n = movieNameYearData.indexOf(s);
							System.out.println((i + 1) + "\t" + nearMovies.substring(0, 7) + "    " + (n + 1) + "\t"
									+ "  ( "
									+ movieReviewCount.get(n) + "  reviews" + " )    " + nearMovies.substring(9));
						} // end for
					} else {
						System.out.println("\n" + "\n" + "\n" + "Insufficient comparison movies");
					} // end if-else
					break;
				} else if (movieNumber <= 0) {
					System.out.println("Movie number must be between 1 and 1682");
					System.out.print("\n" + "Movie number: ");
					number = scanner.next();
				} else if (movieNumber > movieNameYearData.size()) {
					System.out.println("Movie number must be between 1 and 1682");
					System.out.print("\n" + "Movie number: ");
					number = scanner.next();
				} // end if-else
			} else {
				System.out.println("Invalid entry, try again");
				System.out.print("\n" + "Movie number: ");
				number = scanner.next();
			} // end if-else

		} // end while
	}// end main
}// end Hw4
