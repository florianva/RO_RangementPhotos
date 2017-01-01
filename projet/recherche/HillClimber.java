package recherche;

import main.Main;
import main.Parametrage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HillClimber {
	
	
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
	    
	
	
	
	public static void run(int critere, int nbRun, int nbEvalMax){
		
		//int nbRun = 1000;
		double sBestTotal = 0;
		
		
		for (int nb = 0; nb<nbRun; nb++){
			
		
			int nbEval = 0;
			//int nbEvalMax = 10000;
			double sBest = 0;
			double s = 0;
			
			
			// uncomment to test it
			// readPhotoExample(photoFileName);
		
			//computeDistances(photoFileName, albumFileName);
			Parametrage.Start(critere);
		
			// one basic solution : order of the index
		
			
			int [] solution = new int[Main.getNumberOfPhoto()];
			int [] solutionVoisine = new int[Main.getNumberOfPhoto()];
			
			int [] bestSolution = new int[Main.getNumberOfPhoto()];
		
			for(int i = 0; i < 55; i++){
			    solution[i] = i;
			}
			
			
		
			// compute the fitness
			solution = Shuffle(solution);
			sBest = Parametrage.Eval(critere, solution);
			if (sBestTotal == 0){
				sBestTotal = sBest;
			}
			
			//System.out.println(sBest);
			nbEval ++;
			
			while(nbEval <= nbEvalMax){
				solutionVoisine = Voisin(solution);
				s = Parametrage.Eval(critere, solutionVoisine);
				//System.out.println(s);
				nbEval ++;
			
				while(sBest <= s && nbEval <= nbEvalMax){
					solutionVoisine = Voisin(solution);
					s = Parametrage.Eval(critere, solutionVoisine);
					//System.out.println(s);
					nbEval ++;
				}
				if(s < sBest){
					sBest = s;
					solution = solutionVoisine;
				}
				System.out.println("best : "+sBest);
				
				//AfficheSolution(solution);
			}
			if (sBest < sBestTotal){
				sBestTotal = sBest;
				bestSolution = solution;
			}
			
			}
		System.out.println("Meilleur : "+sBestTotal);
	}
	
}
