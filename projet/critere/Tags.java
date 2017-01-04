package critere;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.Distances;
import main.Main;
import recherche.HillClimber;

public class Tags {

	// Distance between photos
    public static double [][] photoDist;
    public static long [] photoGrey;

    
    public static void Start() {
		Distances.computeAlbumDistances(Main.getAlbumFileName());
		computePhotoDistances(Main.getPhotoFileName());
	}
    
    
	
	
	 public static void computePhotoDistances(String fileName) {
			try {
			    FileReader reader = new FileReader(fileName);

			    JSONParser parser = new JSONParser();

			    Object obj = parser.parse(reader);

			    JSONArray array = (JSONArray) obj;

			    photoDist = new double[array.size()][array.size()];
			    photoGrey = new long[array.size()];

			    // distance based on the distance between average hash
			    for(int i = 0; i < array.size(); i++) {
				JSONObject image = (JSONObject) array.get(i);
				photoGrey[i] = (long) image.get("greyavg");
				JSONArray d = (JSONArray) image.get("dhashdist");
				//System.out.println(photoGrey[i]);
				for(int j = 0; j < d.size(); j++) {
				    photoDist[i][j] = (double) d.get(j);
				}
						
			    }
			    

			    /*
			    for(int i = 0; i < photoDist.length; i++) {
				for(int j = 0; j < photoDist.length; j++) {
				    System.out.print(" " + photoDist[i][j]);
				}
				System.out.println();
			    }
			    */


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
     * Un exemple de fonction objectif (à minimiser):
     *   distance entre les photos pondérées par l'inverse des distances spatiales sur l'album
     *   Modélisaiton comme un problème d'assignement quadratique (QAP)
     *
     *   Dans cette fonction objectif, 
     *      pas de prise en compte d'un effet de page (harmonie/cohérence de la page)
     *      par le choix de distance, pas d'intéraction entre les photos sur des différentes pages
     */
    public static double eval(int [] solution) {
	double sum = 0;

	for(int i = 0; i < Distances.getAlbumInvDist().length; i++) {
	    for(int j = i + 1; j < Distances.getAlbumInvDist().length; j++) {
		sum += photoDist[ solution[i] ][ solution[j] ] * Distances.getAlbumInvDist()[i][j] * photoGrey[i] ;
	    }
	}

	return sum;
    }
    
	
	
	
	
}
