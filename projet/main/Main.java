package main;
/**
 *
 * Minimal examples for the project in JAVA
 *
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import recherche.HillClimber;


/**
 * @author verel
 * @date 2015/11/07
 */
public class Main {
    
	
    public static int numberOfPhoto = 55;
    public static String photoFileName;
    public static String albumFileName;
    public static String solutionFileName;
    public static int algo;
    

    /**
     * Liste des Algorithmes
     * 1 = Hill Climber First Improvement
     */
    
    /**
     * Liste des Criteres de tri
     * 1 = Empreinte (ahashdist)
     */
    
    
    
    /**
     * @param args
     * 1 = Algorithme (cf. Liste des Algorithmes)
     * 2 = Critere(cf. List des Criteres de tri)
     * 3 = Nombre d'itertions
     * 4 = Nombre d'evaluations
     */
    
    public static void main(String[] args)  { 
	// Path to the photo information file in json format
	setPhotoFileName("/Users/florian/prj1-ro/data/info-photo.json");
	// Path to the album information file in json format
	setAlbumFileName("/Users/florian/prj1-ro/data/info-album.json");
	
	setSolutionFileName("/Users/florian/prj1-ro/data/chronologic-order.sol");
	
	
	int algorithme = 2;
	int critere = 1;
	int nbRun = 10;
	int nbEval = 1000;
	
	setAlgo(algorithme);
	
	double result = Algorithmes.Launch(algo, critere, nbRun, nbEval);
	System.out.println("Resultat : "+result);
	
	
    }






	/**
     *
     * Example of json file parsing
     *
     * see: https://code.google.com/p/json-simple/
     * for more example to decode json under java
     *
     */
    public static void readPhotoExample(String fileName) {
	try {
	    FileReader reader = new FileReader(fileName);

	    JSONParser parser = new JSONParser();
	    
	    // parser the json file
	    Object obj = parser.parse(reader);
	    //System.out.println(obj);

	    // extract the array of image information
	    JSONArray array = (JSONArray) obj;
	    System.out.println("The first element:\n" + array.get(0));

	    JSONObject obj2 = (JSONObject) array.get(0);
	    System.out.println("the id of the first element is: " + obj2.get("id"));    

	    JSONArray arraytag = (JSONArray) ((JSONObject)obj2.get("tags")).get("classes");
	    System.out.println("Tag list of the first element:");
	    for(int i = 0; i < arraytag.size(); i++)
		System.out.print(" " + arraytag.get(i));
	    System.out.println();

	} catch(ParseException pe) {	    
	    System.out.println("position: " + pe.getPosition());
	    System.out.println(pe);
	} catch (FileNotFoundException ex) {
	    ex.printStackTrace();
	} catch(IOException ex) {
	    ex.printStackTrace();
	}
    }

    
    
 
    public static void AfficheSolution(int [] solution){
    	for (int i = 0; i<solution.length; i++){
    		System.out.print(solution[i]+" ");
    	}
    }

   
    
    public static String getPhotoFileName() {
		return photoFileName;
    }


	public static void setPhotoFileName(String photo) {
		photoFileName = photo;
	}


	public static String getAlbumFileName() {
		return albumFileName;
	}


	public static void setAlbumFileName(String album) {
		albumFileName = album;
	}



	public static int getNumberOfPhoto() {
		return numberOfPhoto;
	}


	public static void setNumberOfPhoto(int numberOfPhoto) {
		Main.numberOfPhoto = numberOfPhoto;
	}

	
	public static String getSolutionFileName() {
		return solutionFileName;
	}

	public static void setSolutionFileName(String solutionFileName) {
		Main.solutionFileName = solutionFileName;
	}

	public static int getAlgo() {
		return algo;
	}


	public static void setAlgo(int algo) {
		Main.algo = algo;
	}
	
}
