package parametrage;
import critere.*;

public class Critere {

	public static void Start(int arg, String empreinte){
		switch (arg) {
        case 1:  Empreinte.Start(empreinte);
                 break;
        case 2:  Grey.Start(empreinte);
		 		 break;
        case 3:  Tags.Start(empreinte);
		 		 break;
        case 4:  Grey_tags.Start(empreinte);
        		 break;
        default: Empreinte.Start(empreinte);
                 break;
    }
	}
	
	
	public static double Eval(int arg, int [] solution){
		switch (arg) {
        case 1:  return Empreinte.eval(solution);
        case 2:  return Grey.eval(solution);
        case 3:  return Tags.eval(solution);
        case 4:  return Grey_tags.eval(solution);
        default: return Empreinte.eval(solution);
    }
	}
	
	
}
