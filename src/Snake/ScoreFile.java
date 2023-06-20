package Snake;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ScoreFile 
{
	
	private static final String FILE_PATH = "scores.txt";

    public static void writeScore(int score) {
    	int currentScore = readScore();
    	if(score > currentScore)
    	{
        try 
    	{
            FileWriter writer = new FileWriter(FILE_PATH);
            writer.write(String.valueOf(score));
            writer.close();
        } 
    	catch (IOException e) 
    	{
            e.printStackTrace();
        }
    	}
    		

    
    }

    public static int readScore() {
        int score = 0;
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                FileReader reader = new FileReader(FILE_PATH);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String scoreString = bufferedReader.readLine();
                score = Integer.parseInt(scoreString);
                bufferedReader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return score;
    }

}
