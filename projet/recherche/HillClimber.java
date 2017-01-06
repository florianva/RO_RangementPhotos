package recherche;

import main.Main;
import parametrage.Critere;
import tools.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HillClimber {
	
	public static int[] solutionFinale;
	public static int[] externalSolution;
	
	 private static int random(int max){
	    	Random r = new Random();
	    	return r.nextInt(max);
	 }
	
	 public static int[] Voisin(int [] solution){
    	
    	int[] newSolution = new int[solution.length];
    	for (int i = 0; i < solution.length; i++) {
			newSolution[i] = solution[i];
		}
    	
    	int indice1 = random(Main.getNumberOfPhoto());
    	int indice2 = random(Main.getNumberOfPhoto());
    	
    	int photo1 = solution[indice1];
    	int photo2 = solution[indice2];
    	
    	newSolution[indice1] = photo2;
    	newSolution[indice2] = photo1;
    	
    	return newSolution;
    	
    }
	 
	 public static int[] Shuffle(int[] solution){
	    	
	    	List<Integer> possibility = new ArrayList<>();
	    	for (int i = 1; i < Main.getNumberOfPhoto(); i++)
	    	{
	    		possibility.add(i);
	    	}
	    	Collections.shuffle(possibility);
	    	
	    	for (int i = 0; i < possibility.size(); i++) {
	    	    solution[i] = possibility.get(i);
	    	  } 
	    	return solution;
	    }
	    
	
	 
	 
	 public static int[] getFirstSolution(){
		 
		 int [] solution = new int[Main.getNumberOfPhoto()];
		 
		 for(int i = 0; i < 54; i++){
			    solution[i] = i;
			}
		 
		return solution = Shuffle(solution);
		 
	 }
	
	
	 public static double run(int critere, int nbRun, int nbEvalMax, String empreinte){
		
		double sBestTotal = 0;
		int [] bestSolution = new int[Main.getNumberOfPhoto()];
		
		
		for (int nb = 0; nb<nbRun; nb++){
			
			
		
			int nbEval = 0;
			double sBest = 0;
			double s = 0;
			
			
			// uncomment to test it
			// readPhotoExample(photoFileName);
		
			Critere.Start(critere, empreinte);
		
		
			
			
			
			int [] solutionVoisine = new int[Main.getNumberOfPhoto()];
			int [] solution = new int[Main.getNumberOfPhoto()];
		
			
			
			if(getExternalSolution() == null){
				solution = getFirstSolution();
			}else{
				solution = getExternalSolution();
			}
			
			sBest = Critere.Eval(critere, solution);
			if (sBestTotal == 0){
				sBestTotal = sBest;
			}
			
			nbEval ++;
			
			while(nbEval <= nbEvalMax){
				solutionVoisine = Voisin(solution);
				s = Critere.Eval(critere, solutionVoisine);
				nbEval ++;
			
				while(sBest <= s && nbEval <= nbEvalMax){
					solutionVoisine = Voisin(solution);
					s = Critere.Eval(critere, solutionVoisine);
					nbEval ++;
				}
				if(s < sBest){
					sBest = s;
					solution = solutionVoisine;
				}
				if(Main.getAlgo() == 1)
					System.out.println("best : "+sBest);
			}
			if (sBest < sBestTotal){
				sBestTotal = sBest;
				bestSolution = solution;
			}
			if(Main.getAlgo() == 1)
				System.out.println("Run "+(nb+1)+" Meilleur : "+sBestTotal);
		}
		
		setSolutionFinale(bestSolution);
		if(Main.getAlgo() == 1){
			try {
				File.write(bestSolution);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sBestTotal;
	}

	public static int[] getSolutionFinale() {
		return solutionFinale;
	}

	public static void setSolutionFinale(int[] solution) {
		solutionFinale = solution;
	}

	public static int[] getExternalSolution() {
		return externalSolution;
	}

	public static void setExternalSolution(int[] externalSolution) {
		HillClimber.externalSolution = externalSolution;
	}
	
	
	
	
}
