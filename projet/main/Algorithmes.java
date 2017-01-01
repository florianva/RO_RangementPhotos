package main;
import recherche.HillClimber;
import recherche.IteratedLocalSearch;

public class Algorithmes {

	public static double Launch(int algo, int critere, int nbRun, int nbEval){
		switch (algo) {
        case 1:  return HillClimber.run(critere, nbRun, nbEval);
        case 2:  return IteratedLocalSearch.run(critere, nbRun, nbEval);
        default: return HillClimber.run(critere, nbRun, nbEval);
    }
	}
	
	public static int[] getSolution(int algo){
		switch (algo) {
        case 1:  return HillClimber.getSolutionFinale();
        default: return HillClimber.getSolutionFinale();
                 
    }
	}
	
}
