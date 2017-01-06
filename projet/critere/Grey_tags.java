package critere;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.Main;
import recherche.HillClimber;

public class Grey_tags {

	// Distance between photos
    public static double [][] photoDist;
    public static long [] photoGrey;
    public static String[][] topTags;
    public static double[][] topProbs;
    public static double[] classement;

    
    public static void Start(String empreinte) {
    	initClassement();
		Distances.computeAlbumDistances(Main.getAlbumFileName());
		computePhotoDistances(Main.getPhotoFileName(), empreinte);
	}
    
    
	
	
	 public static void computePhotoDistances(String fileName, String empreinte) {
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
				JSONArray d = (JSONArray) image.get(empreinte);
				//System.out.println(photoGrey[i]);
				for(int j = 0; j < d.size(); j++) {
				    photoDist[i][j] = (double) d.get(j);
				}
						
			    }


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
     * evaluation en fonction du hash, de la nuance de gris et du classement des photos par tag 
     */
    public static double eval(int [] solution) {
	double sum = 0;

	for(int i = 0; i < Distances.getAlbumInvDist().length; i++) {
	    for(int j = i + 1; j < Distances.getAlbumInvDist().length; j++) {
		sum += photoDist[ solution[i] ][ solution[j] ] * Distances.getAlbumInvDist()[i][j] * photoGrey[i] * (1-getClassement()[i]);
	    }
	}

	return sum;
    }
    
    
    
    
    public static void initClassement(){
    	Map<String,Integer> map = new HashMap<String,Integer>();
    	try{
    		FileReader reader = new FileReader(Main.getPhotoFileName());
    	
    	    JSONParser parser = new JSONParser();
    	
    	    Object obj = parser.parse(reader);
    	
    	    
    	    JSONArray array = (JSONArray) obj;
    	    
    	    topTags = new String[array.size()][array.size()];
    	    topProbs = new double[array.size()][array.size()];
    	    classement = new double[array.size()];
    	
    	    // distance based on the distance between average hash
    	    for(int i = 0; i < array.size(); i++) {
    			JSONObject image = (JSONObject) array.get(i);
    			JSONObject tags = (JSONObject) image.get("tags");
    			JSONArray d = (JSONArray) tags.get("classes");
    			JSONArray p = (JSONArray) tags.get("probs");
    			
    			for(int j = 0; j < 3; j++) {
    				topTags[i][j] = (String) d.get(j);
    				topProbs[i][j] = (double) p.get(j);
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
    		int max = 0;
    		int max2 = 0;
    		int max3 = 0;
    		int nb = 0;
    		int indiceMax = 0;
    		int indiceMax2 = 0;
    		int indiceMax3 = 0;
    		List<Integer> valueList = new ArrayList<Integer>(map.values());
    		for (Integer temp : valueList) {
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
    		//Liste des 3 tags les plus fréquents dans les 3 premiers tags de chaque photo
    		List keys = new ArrayList(map.keySet());
    		Object tag1 = keys.get(indiceMax);
    		Object tag2 = keys.get(indiceMax2);
    		Object tag3 = keys.get(indiceMax3);
    		
    		String data=null;
    		double prob=0;
    		for(int i = 0; i < array.size(); i++) {
    			for (int k = 0; k < 3; k++) {
    				data =topTags[i][k];
    				prob=topProbs[i][k];
    				if(data.equals(tag1.toString())){
    					//System.out.println("photo "+(i+1)+" : "+prob+" -> "+data);
    					classement[i] = 0.10;
    					break;
    				}else{
    					if(data.equals(tag2.toString())){
    						//System.out.println("photo "+(i+1)+" : "+prob+" -> "+data);
    						classement[i] = 0.20;
    						break;
    					}else{
    						if(data.equals(tag3.toString())){
    							//System.out.println("photo "+(i+1)+" : "+prob+" -> "+data);
    							classement[i] = 0.30;
    							break;
    						}else{
    							classement[i] = 0.40;
    						}
    					}
    				}
    			}
    			
    		}
    	
    	setClassement(classement);
    	    
    		
    	} catch(ParseException pe) {	    
    	    System.out.println("position: " + pe.getPosition());
    	    System.out.println(pe);
    	} catch (FileNotFoundException ex) {
    	    ex.printStackTrace();
    	} catch(IOException ex) {
    	    ex.printStackTrace();
    	}
    	
        }




	public static double[] getClassement() {
		return classement;
	}


	public static void setClassement(double[] classement) {
		Grey_tags.classement = classement;
	}

 
    
	
	
	
	
}
