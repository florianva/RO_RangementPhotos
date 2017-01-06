package parametrage;
import recherche.HillClimber;
import recherche.IteratedLocalSearch;

public class Algorithmes {

	public static double Launch(int algo, int critere, int nbRun, int nbEval, String empreinte){
		switch (algo) {
        case 1:  return HillClimber.run(critere, nbRun, nbEval, empreinte);
        case 2:  return IteratedLocalSearch.run(critere, nbRun, nbEval, empreinte);
        default: return HillClimber.run(critere, nbRun, nbEval, empreinte);
    }
	}
	
	public static int[] getSolution(int algo){
		switch (algo) {
        case 1:  return HillClimber.getSolutionFinale();
        default: return HillClimber.getSolutionFinale();
                 
    }
	}
	
}
