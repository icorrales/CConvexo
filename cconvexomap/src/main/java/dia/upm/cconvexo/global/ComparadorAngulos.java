package dia.upm.cconvexo.global;

import java.util.Comparator;

import dia.upm.cconvexo.algoritmos.FunctionsGlobals;
import dia.upm.cconvexo.model.Punto;

public class ComparadorAngulos implements Comparator<Punto> {

	Punto ptoInterior = null;
	
	public ComparadorAngulos(Punto puntoInterior) {
		// TODO Auto-generated constructor stub
		assert ptoInterior == null;
		this.ptoInterior = puntoInterior;
	}

	@Override
	public int compare(Punto arg0, Punto arg1) {
		// TODO Auto-generated method stub
		
		
		return Comparar(arg0, arg1);
	}
	
	

	/*
	   * Esta funcion que compara basicamente 2 puntos, determinar cual de ellos
	   * habra de ser el que este en el principio del array.
	   */
	  int Comparar(Punto punto1, Punto punto2)
	  {
	    double cara;
	    double xdist1;
	    double ydist1;
	    double xdist2;
	    double ydist2;

	    /*
	     * Estudia si p2 esta a la IZQUIERDA o a la DERECHA de la recta del segmento
	     que forman los puntos: puntos[0] y p1
	     */
	    cara = FunctionsGlobals.A_que_Lado(ptoInterior, punto1, punto2);
	    if (cara == FunctionsGlobals.IZQUIERDA)
	      return 1;
	    else if (cara == FunctionsGlobals.DERECHA)
	      return -1;
	    else
	    /*
	     * Si p2 es COLINEAR con la recta del segmento, enconces estudiamos que punto
	     entre p1 y p2 esta mas cerca a puntos[0]
	     */
	    {
	      xdist1 = punto1.getX() - this.ptoInterior.getX();
	      ydist1 = punto1.getY() - this.ptoInterior.getY();
	      xdist2 = punto2.getX() - this.ptoInterior.getX();
	      ydist2 = punto2.getY() - this.ptoInterior.getY();

	      if ((xdist1 * xdist1 + ydist1 * ydist1) <
	          (xdist2 * xdist2 + ydist2 * ydist2))
	        return -1;
	      else
	        return 1;
	    }
	  }

	  
	  
	  
}
