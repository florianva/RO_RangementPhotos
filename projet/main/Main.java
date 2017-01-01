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

import critere.Empreinte;
import recherche.HillClimber;


/**
 * @author verel
 * @date 2015/11/07
 */
public class Main {
    
	
    public static int numberOfPhoto = 55;
    public static String photoFileName;
    public static String albumFileName;
    

    
    /**
     * @param args
     * 1 = critere
     * 2 = Nombre d'itertions
     * 3 = Nombre d'evaluations
     */
    public static void main(String[] args)  { 
	// Path to the photo information file in json format
	setPhotoFileName("/Users/florian/prj1-ro/data/info-photo.json");
	// Path to the album information file in json format
	setAlbumFileName("/Users/florian/prj1-ro/data/info-album.json");
	HillClimber.run(1, 1000, 10000);
	
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

}
