package main;
import critere.Empreinte;

public class Parametrage {

	public static void Start(int arg){
		switch (arg) {
        case 1:  Empreinte.Start();
                 break;
        default: Empreinte.Start();
                 break;
    }
	}
	
	
	public static double Eval(int arg, int [] solution){
		switch (arg) {
        case 1:  return Empreinte.eval(solution);
        default: return Empreinte.eval(solution);
    }
	}
	
	
}
