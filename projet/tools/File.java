package tools;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import main.Main;


public class File {
	
	public static void write(int[] solution) throws FileNotFoundException {
		String ligne = "";
		
		for (int i = 0; i < solution.length; i++) {
			ligne += solution[i] + " ";
		}
			PrintWriter writer = new PrintWriter(Main.getSolutionFileName());
			writer.write(ligne);
			writer.close();
	}
		
}
