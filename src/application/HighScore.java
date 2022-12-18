package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class HighScore {
	static File myScores = new File ("src/application/scores");
	
	private static String[] highestScore = new String[3];
	
	public static void readFromFile() {
		FileReader myReader;
		try {
			myReader = new FileReader(myScores);
			
			Scanner scan = new Scanner (myReader);
			int count = 0;
			while(scan.hasNextLine()) {
				highestScore[count] = scan.nextLine();
				count++;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void writeToFile (int i) {
		if(i < Integer.valueOf(highestScore[2])) 
			return;
		else if(i < Integer.valueOf(highestScore[1])) {
			highestScore[2] = Integer.toString(i);
		}
		else if(i < Integer.valueOf(highestScore[0])) {
			highestScore[2] = highestScore[1];
			highestScore[1] = Integer.toString(i);
		}
		else if(i > Integer.valueOf(highestScore[0])) {
			highestScore[2] = highestScore[1];
			highestScore[1] = highestScore[0];
			highestScore[0] = Integer.toString(i);
		};
		try {
			FileWriter myWriter = new FileWriter (myScores);
			
			for(int count = 0; count < 3; count++) {
				myWriter.write(highestScore[count] );
				myWriter.write("\n");
			}
			
			myWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		readFromFile();
	}
	
	public static String getHighScore(int i) {
		readFromFile();
		return highestScore[i];
	}
}
