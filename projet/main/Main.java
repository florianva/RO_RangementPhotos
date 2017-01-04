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
import java.util.Random;
import java.util.Set;

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
    
    public static String[][] topTags;
    

    /**
     * Liste des Algorithmes
     * 1 = Hill Climber First Improvement
     */
    
    /**
     * Liste des Criteres de tri
     * 1 = Empreinte (ahashdist)
     * 2 = Tags
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
	
	
	int algorithme = 1;
	int critere = 2;
	int nbRun = 1000;
	int nbEval = 100000;
	
	setAlgo(algorithme);
	Map<String,Integer> map = new HashMap<String,Integer>();
	
	
	
	//double result = Algorithmes.Launch(algo, critere, nbRun, nbEval);
	//System.out.println("Resultat : "+result);
	
	try{
		FileReader reader = new FileReader(getPhotoFileName());
	
	    JSONParser parser = new JSONParser();
	
	    Object obj = parser.parse(reader);
	
	    JSONArray array = (JSONArray) obj;
	    
	    topTags = new String[array.size()][array.size()];
	
	    // distance based on the distance between average hash
	    for(int i = 0; i < array.size(); i++) {
		JSONObject image = (JSONObject) array.get(i);
		JSONObject tags = (JSONObject) image.get("tags");
		JSONArray d = (JSONArray) tags.get("classes");
		
		for(int j = 0; j < 3; j++) {
			topTags[i][j] = (String) d.get(j);
		}
		
		
		for (int k = 0; k < 3; k++) {
		    String word=topTags[i][k];
		    if (!map.containsKey(word)){
		        map.put(word,1);
		    } else {
		    	if(word.equals("nobody")){
		    		map.put(word,0);
		    	}else{
		    		map.put(word, map.get(word) +1);
		    	}
		    }
		}
			
		
	    }
	    int maxValueInMap=(Collections.max(map.values()));
		for (Entry<String, Integer> entry : map.entrySet()) {  // Itrate through hashmap
            if (entry.getValue()==maxValueInMap) {
                System.out.println(entry.getKey());     // Print the key with max value
            }
        }
		int max = 0;
		int max2 = 0;
		int max3 = 0;
		int nb = 0;
		int indiceMax = 0;
		int indiceMax2 = 0;
		int indiceMax3 = 0;
		List<Integer> valueList = new ArrayList<Integer>(map.values());
		System.out.println("\n==> Size of Value list: " + valueList.size());
		for (Integer temp : valueList) {
			//System.out.println(temp);
			 if (temp > max){
				 max3 = max2;
				 max2 = max;
				 max = temp;
				 indiceMax = nb;
			 }else{
				 if(temp > max2){
					 max3 = max2;
					 max2 = temp;
					 indiceMax2 = nb;
				 }else{
					 if(temp > max3){
						 max3 = temp;
						 indiceMax3 = nb;
					 }
				 }
			 }
			 nb++;
		}
		System.out.println(map);
		System.out.println(max);
		System.out.println(max2);
		System.out.println(max3);
		
		List keys = new ArrayList(map.keySet());
		Object tag1 = keys.get(indiceMax);
		Object tag2 = keys.get(indiceMax2);
		Object tag3 = keys.get(indiceMax3);
		System.out.println(tag1.toString());
		System.out.println(tag2.toString());
		System.out.println(tag3.toString());
		
		
		
		
		
		
		
		
		
	    
		
	} catch(ParseException pe) {	    
	    System.out.println("position: " + pe.getPosition());
	    System.out.println(pe);
	} catch (FileNotFoundException ex) {
	    ex.printStackTrace();
	} catch(IOException ex) {
	    ex.printStackTrace();
	}
	
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
