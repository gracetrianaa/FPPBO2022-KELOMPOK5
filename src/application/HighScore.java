package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HighScore {
static File myScores = new File ("src/application/scores");
	
	private static String highestScore = new String();
	
	public static void readFromFile() {
		FileReader myReader;
		try {
			myReader = new FileReader(myScores);
			
			Scanner scan = new Scanner (myReader);
			highestScore = scan.nextLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeToFile (int i) {
		try {
			FileWriter myWriter = new FileWriter (myScores);
			
			myWriter.write(Integer.toString(i));
			
			myWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		readFromFile();
	}
	
	public static String getHighScore() {
		readFromFile();
		return highestScore;
	}
}
