package main;
/**
 *
 * Minimal examples for the project in JAVA
 *
 */
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import parametrage.Algorithmes;
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
     * 2 = Iterated Local Search
     */
    
    /**
     * Liste des Criteres de tri
     * 1 = Empreinte (ahashdist)
     * 2 = Empreinte + Nuances de gris
     * 3 = Empreinte + Tags
     * 4 = Empreinte + Nuances de gris + Tags
     */
    
    
    
    /**
     * @param args
     * 1 = Algorithme (cf. Liste des Algorithmes)
     * 2 = Critere(cf. Liste des Criteres de tri)
     * 3 = Nombre d'itertions
     * 4 = Nombre d'evaluations
     * 5 = Choix de l'empreinte pour la distance ( dhashdist, ahashdist ou phashdist ) :
     */
    
    public static void main(String[] args)  { 
    	
    Properties prop = new Properties();
    InputStream input = null;
    
    try {
		input = new FileInputStream("config.properties");
		prop.load(input);
    
    } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    	
    	
	// Path to the photo information file in json format
	setPhotoFileName(prop.getProperty("photoFileName"));
	// Path to the album information file in json format
	setAlbumFileName(prop.getProperty("albumFileName"));
	
	setSolutionFileName(prop.getProperty("solutionFileName"));
	
	
	/*int algorithme = 2;
	int critere = 2;
	int nbRun = 5;
	int nbEval = 10000;
	String empreinte = "ahashdist";*/
	int algorithme = Integer.parseInt(prop.getProperty("algorithme"));
	int critere = Integer.parseInt(prop.getProperty("critere"));
	int nbRun = Integer.parseInt(prop.getProperty("nbRun"));
	int nbEval = Integer.parseInt(prop.getProperty("nbEval"));
	String empreinte = prop.getProperty("empreinte");
	
	setAlgo(algorithme);
	
	
	
	double result = Algorithmes.Launch(algorithme, critere, nbRun, nbEval, empreinte);
	System.out.println("Resultat : "+result);
  
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
