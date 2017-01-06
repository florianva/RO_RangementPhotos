package critere;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Distances {
	
	
	
    // Inverse of the distance between positions in the album
    public static double [][] albumInvDist;


	public static void computeAlbumDistances(String fileName) {
		try {
		    FileReader reader = new FileReader(fileName);

		    JSONParser parser = new JSONParser();
		    Object obj = parser.parse(reader);

		    JSONObject album = (JSONObject) obj;

		    // number of pages
		    long nPage = (long) album.get("page");

		    // number of photo in each page
		    JSONArray pageSize = (JSONArray) album.get("pagesize");

		    // number on the first page
		    int size = (int) (long) pageSize.get(0);
		    // total number of photo in the album
		    int nbPhoto = 0;
		    for(int i = 0; i < pageSize.size(); i++) 
			nbPhoto += (int) (long) pageSize.get(i);

		    albumInvDist = new double[nbPhoto][nbPhoto];

		    // compute the distance
		    for(int i = 0; i < nbPhoto; i++) 
			for(int j = 0; j < nbPhoto; j++) 
			    albumInvDist[i][j] = inverseDistance(size, i, j);
		    	setAlbumInvDist(albumInvDist);
		    

		} catch(ParseException pe) {	    
		    System.out.println("position: " + pe.getPosition());
		    System.out.println(pe);
		} catch (FileNotFoundException ex) {
		    ex.printStackTrace();
		} catch(IOException ex) {
		    ex.printStackTrace();
		}
	    }
	
	
	
	
	
	 
    public static double inverseDistance(int size, int i, int j) {
	// number of pages
	int pagei = i / size;
	int pagej = j / size;

	if (pagei != pagej)
	    // not on the same page: distance is infinite. Another choice is possible of course!
	    return 0;
	else {
	    // positions in the page
	    int posi = i % size;
	    int posj = j % size;

	    // coordinate on the page
	    int xi = posi % 2;
	    int yi = posi / 2;
	    int xj = posj % 2;
	    int yj = posj / 2;

	    // Manhatthan distance
	    return ((double) 1) / (double) (Math.abs(xi - xj) + Math.abs(yi - yj));
	}
    }


    
    public static double[][] getAlbumInvDist() {
		return albumInvDist;
    }



	public static void setAlbumInvDist(double[][] albumInvDist) {
		Distances.albumInvDist = albumInvDist;
	}
	
	
	
}
