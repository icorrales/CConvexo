package dia.upm.cconvexo.algoritmos;


import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.global.ComparadorAngulos;
import dia.upm.cconvexo.global.Triangulo;
import dia.upm.cconvexo.interfaces.IAlgoritmoHullConvex;
import dia.upm.cconvexo.model.Punto;

public abstract class AbstractAlgoritmo implements IAlgoritmoHullConvex {
	
	

	
	public void nextstep() {
		// TODO Auto-generated method stub

	}

	
	public abstract void start(int delay);
	
	public final int orientation(Punto A, Punto B, Punto P) {


		double cp1 = (double) ((B.x-A.x)*(P.y-A.y) - (B.y-A.y)*(P.x-A.x));
		if (cp1 > 0)
		{
			return FunctionsGlobals.POSITIVA;			
		}
		else if (cp1<0)
		{
			return FunctionsGlobals.NEGATIVA;			
		}
		else return FunctionsGlobals.LINEA;
	}

    public int orientation2(Punto A, Punto B, Punto P)
    {
//        det = CLng(p2.Y) * CLng(p3.X) + CLng(p1.Y) * CLng(p2.X) + CLng(p1.X) * CLng(p3.Y)
        int det = (int) ((B.getY()*P.getX()) + (A.getY()*B.getX()) + (A.getY()*P.getY()));
//        deter = CLng(p1.X) * CLng(p2.Y) + CLng(p2.X) * CLng(p3.Y) + CLng(p1.Y) * CLng(p3.X) - det
        int det2 = (int) ((A.getX()*B.getY())+(B.getX()*P.getY())+ (A.getY()*P.getX()));
        int cp1 = det2 - det;
        int functionResult = FunctionsGlobals.LINEA;
        functionResult = getOrientation(cp1, functionResult);
        return  functionResult;
    }

    public int getOrientation(int cp1, int functionResult) {
        if (cp1 > 0)
        {
            functionResult = FunctionsGlobals.POSITIVA;
        }
        else if (cp1<0)
        {
            functionResult = FunctionsGlobals.NEGATIVA;
        }
        else if (cp1 == 0 ) {functionResult = FunctionsGlobals.LINEA;}
        Log.d("Orientacion Incremental", "La funcion resultado es " + functionResult);
        return functionResult;
    }

    public int orientation3(Punto A, Punto B, Punto P)
    {
        Punto A1 = A;
        Punto A2 = B;
        Punto A3 = P;
        int cp1 = (int) ((A1.x - A3.x) * ((-A2.y) - (-A3.y)) - ((-A1.y) - (-A3.y)) * (A2.x - A3.x));
        int functionResult = FunctionsGlobals.LINEA;
        functionResult = getOrientation(cp1, functionResult);
        return  functionResult;



    }
	public final double distance(Punto A, Punto B)
	{
		double AB2 = Math.pow((B.x - A.x),2) + Math.pow((B.y - A.y),2);
		return Math.sqrt(AB2);
	}
	
	
	/**
	 * Computes the square of the distance of point C to the segment defined by points AB
	 * @param A
	 * @param B
	 * @param C
	 * @return
	 */
	public final int distance(Punto A, Punto B, Punto C) {
		int ABx = (int) (B.x-A.x);
		int ABy = (int) (B.y-A.y);
		int num = (int) (ABx*(A.y-C.y)-ABy*(A.x-C.x));
		if (num < 0) num = -num;
		return num;
	}
	
/*	La funci�n actualizar_min_angulo es la siguiente:

		function actualizar_min_angulo ( pivote, pto, min_angulo) :
		case orientacion(pivote,pto,min_angulo) of
		POSITIVA: return true
		NEGATIVA: return false
		LINEA: if  distancia(pto,pivote) > distancia(min_angulo,pivote)  then
		return true
		else
		return false
		endif
		endcase
*/
	
	public final boolean actualizar_min_angulo(Punto pivote, Punto pto, Punto min_angulo)
	{
		boolean resultado = false;
		switch (orientation(pivote,pto, min_angulo)) {
		case FunctionsGlobals.POSITIVA:
			resultado = true;
			break;
		case FunctionsGlobals.LINEA:
		    if (distance(pto, pivote) > distance(min_angulo, pivote))
		    {
		    	resultado = true;
		    }
		    else
		    {
		    	resultado = false;
		    }
		    break;
		default:
			break;
		}
		return resultado;
	}
	
	boolean estaEnTri(Punto A1,Punto A2,Punto A3, Punto P)
    {   // Decide si un punto P est� dentro del tri�ngulo orientado A1A2A3

        if(orientation(A1,A2,A3)>=0)
               return orientation(A1, A2, P) >= 0 &&
               		  orientation(A2, A3, P) >= 0 &&
               		  orientation(A3, A1, P) >= 0;
        else   return orientation(A1, A2, P) <= 0 &&
        			  orientation(A2, A3, P) <= 0 &&
        			  orientation(A3, A1, P) <= 0;

    }//

	public Punto centroide(Punto punto, Punto punto2, Punto punto3) {
		// TODO Auto-generated method stub
		assert punto != null;
		assert punto2 != null;
		assert punto3 != null;
		
		double coordx = (punto.x + punto2.x + punto3.x) /3;
		double coordy = (punto.y + punto2.y + punto3.y) /3;
		
		Punto centroide = new Punto();
		centroide.x = coordx;
		centroide.y = coordy;
		return centroide;
	}

	protected Punto siguiente(Punto vertice_derecho, List<Punto> c_convexo) {
		assert c_convexo.isEmpty() == false && vertice_derecho != null;
		assert c_convexo.contains(vertice_derecho);
		
		int index_v_derecho = c_convexo.indexOf(vertice_derecho); 
		if ( index_v_derecho == c_convexo.size()-1)		
		{
			return c_convexo.get(0);
		}
		else
		{
			assert c_convexo.get(index_v_derecho + 1) != null;
			return c_convexo.get(index_v_derecho + 1);
		}	
		
	}
	
	protected Punto anterior(Punto vertice_derecho, List<Punto> c_convexo) {
		assert c_convexo.isEmpty() == false && vertice_derecho != null;
		assert c_convexo.contains(vertice_derecho);
		
		int index_v_derecho = c_convexo.indexOf(vertice_derecho); 
		if ( index_v_derecho == 0)		
		{
			return c_convexo.get(c_convexo.size() -1);
		}
		else
		{
			assert c_convexo.get(index_v_derecho -1) != null;
			return c_convexo.get(index_v_derecho -1);
		}	
		
	}

	protected Punto busquedaPuntoMenorOrdenada(List<Punto> listaPuntos) {
		// TODO Auto-generated method stub
		Punto ptoInterior = null;
		for (Iterator<Punto> iterator = listaPuntos.iterator(); iterator.hasNext();) {
			Punto punto = iterator.next();
			if (ptoInterior == null)
			{
				ptoInterior=punto;
			}
			else if (punto.y < ptoInterior.y)
			{
				ptoInterior = punto;
			}			
		}
		assert ptoInterior != null;
		return ptoInterior;
	}
	
	protected Punto busquedaPuntoMayorOrdenada(List<Punto> listaPuntos) {
		// TODO Auto-generated method stub
		Punto ptoInterior = null;
		for (Iterator<Punto> iterator = listaPuntos.iterator(); iterator.hasNext();) {
			Punto punto = iterator.next();
			if (ptoInterior == null)
			{
				ptoInterior=punto;
			}
			else if (punto.y > ptoInterior.y)
			{
				ptoInterior = punto;
			}			
		}
		assert ptoInterior != null;
		return ptoInterior;
	}
	
	protected Punto busquedaPuntoMenorAbscisa(List<Punto> listaPuntos) {
		// TODO Auto-generated method stub
		Punto ptoInterior = null;
		for (Iterator<Punto> iterator = listaPuntos.iterator(); iterator.hasNext();) {
			Punto punto = iterator.next();
			if (ptoInterior == null)
			{
				ptoInterior=punto;
			}
			else if (punto.x < ptoInterior.x)
			{
				ptoInterior = punto;
			}			
		}
		assert ptoInterior != null;
		return ptoInterior;
	}
	
	protected Punto busquedaPuntoMayorAbscisa(List<Punto> listaPuntos) {
		// TODO Auto-generated method stub
		Punto ptoInterior = null;
		for (Iterator<Punto> iterator = listaPuntos.iterator(); iterator.hasNext();) {
			Punto punto = iterator.next();
			if (ptoInterior == null)
			{
				ptoInterior=punto;
			}
			else if (punto.x > ptoInterior.x)
			{
				ptoInterior = punto;
			}			
		}
		assert ptoInterior != null;
		return ptoInterior;
	}

    protected void pinta_triangulo(Triangulo t1) {
        assert t1 != null;
        assert t1.getPunto1() != null ;
        assert t1.getPunto2() != null ;
        assert t1.getPunto3() != null ;

        GestorConjuntoConvexo.getInstancia().anadeTrianguloTmp(t1);

    }

    protected void borra_triangulo(Triangulo t1) {
        assert t1 != null;
        assert t1.getPunto1() != null ;
        assert t1.getPunto2() != null ;
        assert t1.getPunto3() != null ;

        GestorConjuntoConvexo.getInstancia().borraTrianguloTmp(t1);

    }

    public void ordenarAngularmente(List<Punto> cierreConvexo, Punto puntoInterior) {
        // TODO Auto-generated method stub
        Comparator<Punto> comparador = new ComparadorAngulos(puntoInterior);
        Collections.sort(cierreConvexo, comparador);
    }
}
