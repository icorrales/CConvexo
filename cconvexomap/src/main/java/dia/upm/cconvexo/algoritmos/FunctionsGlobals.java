package dia.upm.cconvexo.algoritmos;

//import java.awt.Point;

import dia.upm.cconvexo.model.Punto;

public class FunctionsGlobals {
	
	public static final int POSITIVA = 1;
	public static final int NEGATIVA = -1;
	public static final int LINEA = 0;
	public static final int IZQUIERDA = 1;
	public static final int DERECHA = -1;
	
	public static final int A_que_Lado (Punto A, Punto P, Punto B) {
		int cp1 = (int) (((B.x-A.x)*(P.y-A.y)) - ((P.x - A.x)*(B.y - A.y)));
		

		if (cp1 > 0)
		{
			return FunctionsGlobals.IZQUIERDA;			
		}
		else if (cp1<0)
		{
			return FunctionsGlobals.DERECHA;			
		}
		else return FunctionsGlobals.LINEA;
	}

}
