package main;
import critere.*;

public class Parametrage {

	public static void Start(int arg){
		switch (arg) {
        case 1:  Empreinte.Start();
                 break;
        case 2:  Tags.Start();
        		 break;
        default: Empreinte.Start();
                 break;
    }
	}
	
	
	public static double Eval(int arg, int [] solution){
		switch (arg) {
        case 1:  return Empreinte.eval(solution);
        case 2:  return Tags.eval(solution);
        default: return Empreinte.eval(solution);
    }
	}
	
	
}
