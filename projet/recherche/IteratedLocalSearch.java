package recherche;

import java.io.FileNotFoundException;
import java.util.Random;

import main.Main;
import parametrage.Algorithmes;
import tools.File;

public class IteratedLocalSearch {

	
	
	public static double run(int critere, int nbRun, int nbEvalMax, String empreinte){
	
		int searchAlgo = 1;
		int searchNbRun = 1;
		int searchNbEval = 10000;
		int[] newSolution;
		double sBestTotal = 0;
		int [] bestSolution = new int[Main.getNumberOfPhoto()];
		
		for (int nb = 0; nb<nbRun; nb++){
			int nbEval = 0;
			double sBest = 0;
			double s = 0;
			
		
			sBest = Algorithmes.Launch(searchAlgo, critere, searchNbRun, searchNbEval, empreinte);
			int solution[] = Algorithmes.getSolution(searchAlgo);
			nbEval++;
			
			while(nbEval <= nbEvalMax){
				newSolution = perturbation(solution, 0.25);
				HillClimber.setExternalSolution(newSolution);
				s = Algorithmes.Launch(searchAlgo, critere, searchNbRun, searchNbEval, empreinte);
				nbEval ++;
				
				if (s < sBest){
					sBest = s;
					for (int i=0; i<newSolution.length; i++)
						solution[i] = newSolution[i];
					if(Main.getAlgo() == 2)
						System.out.println("best : "+sBest);
					
				}
				
				
				
			}
			if (sBest < sBestTotal || sBestTotal == 0){
				sBestTotal = sBest;
				for (int i=0; i<solution.length; i++)
					bestSolution[i] = solution[i];
			}
			if(Main.getAlgo() == 2)
				System.out.println("Run "+(nb+1)+" Meilleur : "+sBestTotal);
		}
		if(Main.getAlgo() == 2){
			try {
				File.write(bestSolution);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sBestTotal;	
		
	}
	
	public static int[] perturbation(int[] solution, double pourcentage){
		int[] newSolution = new int[solution.length];
		for (int i=0; i<solution.length; i++)
			newSolution[i] = solution[i];
		
		for(int i=0; i< (solution.length*pourcentage); i++){
			
			int indice1 = random(Main.getNumberOfPhoto());
	    	int indice2 = random(Main.getNumberOfPhoto());
	    	
	    	int photo1 = solution[indice1];
	    	int photo2 = solution[indice2];
	    	
	    	newSolution[indice1] = photo2;
	    	newSolution[indice2] = photo1;
	    	
	    	for(int j=0; j<newSolution.length; j++)
	    		solution[j] = newSolution[j];
	  
		}
		return solution;
	}
	
	 private static int random(int max){
	    	Random r = new Random();
	    	return r.nextInt(max);
	 }
		
}
