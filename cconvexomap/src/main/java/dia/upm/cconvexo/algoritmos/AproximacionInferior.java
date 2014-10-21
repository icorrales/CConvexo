package dia.upm.cconvexo.algoritmos;

import android.util.Log;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.gestores.GestorFranjas;
import dia.upm.cconvexo.model.Punto;

public class AproximacionInferior extends AproximacionGeneral {

    public final static String nombre = "AproxInferior";

	public List<Punto> calculaMuestra(List<Punto> listPuntos) {
		// TODO Auto-generated method stub
        Log.d(AproximacionInferior.class.getName(), "Inicio calculaMuestra");

        Punto x_max = GestorFranjas.getInstancia().getPuntoMax();
		Punto x_min = GestorFranjas.getInstancia().getPuntoMin();
		assert listPuntos != null;
		assert x_max != null && x_min != null;
		int franja = 0;
		List<Punto> muestra = new LinkedList<Punto>();
        Log.d(AproximacionInferior.class.getName(), "Inicio escogeMaximosFranjas");
		escogeMaximosFranjas(listPuntos);
        Log.d(AproximacionInferior.class.getName(), "Fin escogeMaximosFranjas");
		mezclaFranjas(muestra);
        Log.d(AproximacionInferior.class.getName(), "Fin calculaMuestra " + muestra.toString());
		return muestra;
	}

	private void mezclaFranjas(List<Punto> muestra) {
		// Ordenacion de la lista final de puntos donde se tiene que hacer el Cierre Convexo de Graham.
		for (Iterator iterator = GestorFranjas.getInstancia().getListaFranjas().iterator(); iterator.hasNext();) {
			List<Punto> list = (List<Punto>) iterator.next();
			// Si la franja no tiene puntos, no añadimos nada.
			if (! list.isEmpty())
			{
				
				
				if (list.get(0).getX()< list.get(1).getX())
				{
					muestra.add(list.get(0));
					muestra.add(list.get(1));
				}
				else if (list.get(0).equals(list.get(1)))
				{
					muestra.add(list.get(0));
				}
				else
				{
					muestra.add(list.get(1));
					muestra.add(list.get(0));
				}
			}
		}
        muestra.add(0,GestorFranjas.getInstancia().getPuntoMin());
        muestra.add(muestra.size(),GestorFranjas.getInstancia().getPuntoMax());
	}

	public AproximacionInferior()
	{
		
	}

}
