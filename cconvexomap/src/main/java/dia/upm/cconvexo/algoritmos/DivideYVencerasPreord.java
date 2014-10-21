package dia.upm.cconvexo.algoritmos;

import android.util.Log;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.global.ComparadorAbscisas;
import dia.upm.cconvexo.model.Arista;
import dia.upm.cconvexo.model.Punto;

public class DivideYVencerasPreord extends AbstractAlgoritmo {
	
public final static String nombre = "DivideYVencerasPreord";
	
	public DivideYVencerasPreord() {
		// TODO Auto-generated constructor stub
	}
    	
	
	public void start(int delay) {

        Log.d("DivideYVencerasPreord","Inicio Start ");
		List<Punto> listaPuntos = GestorConjuntoConvexo.getInstancia().getListaPuntos();
		assert listaPuntos != null && listaPuntos.size() > 0;
		List<Punto> listaPuntosCopia = new LinkedList<Punto>(listaPuntos);
		Collections.sort(listaPuntosCopia,new ComparadorAbscisas());
		List<Punto> cierreConvexo = new LinkedList<Punto> ();
        Log.d("DivideYVencerasPreord","Inicio dyv");
		divideyvenceras(listaPuntosCopia,cierreConvexo);
        Log.d("DivideYVencerasPreord","cierre convexo:" + GestorConjuntoConvexo.getInstancia().getConjuntoConvexo());
		GestorConjuntoConvexo.getInstancia().pintaCierreConvexo();
	}


	public void divideyvenceras(List<Punto> listaPuntosCopia, List<Punto> cierreConvexo) {
		// TODO Auto-generated method stub
		if (listaPuntosCopia.size() == 1)
		{
			cierreConvexo.add(listaPuntosCopia.get(0));
		}
		else
		{
			divide(listaPuntosCopia,cierreConvexo);
		}
	}


	public void divide(List<Punto> listaPuntosCopia, List<Punto> cierreConvexo) {
		// TODO Auto-generated method stub
		int mitad = Math.abs(listaPuntosCopia.size()/2);
		List<Punto> cierreIzq = new LinkedList<Punto> ();
		List<Punto> cierreDcho = new LinkedList<Punto> ();
		divideyvenceras(listaPuntosCopia.subList(0, mitad), cierreIzq);
		divideyvenceras(listaPuntosCopia.subList(mitad, listaPuntosCopia.size()), cierreDcho);
		mezclar(cierreIzq,cierreDcho,cierreConvexo);
	}


	public void mezclar(List<Punto> cierreIzq, List<Punto> cierreDcho,
			List<Punto> cierreConvexo) {
		// TODO Auto-generated method stub
		assert cierreIzq != null;
		assert cierreDcho != null;
		Arista arista1 = puenteInferior(cierreIzq, cierreDcho);
		Punto infIzda = arista1.getOrigen();
		Punto infDcho = arista1.getDestino();
		Arista arista2 = puenteSuperior(cierreIzq, cierreDcho);
		Punto supIzda = arista2.getDestino();
		Punto supDcha = arista2.getOrigen();				
		anadePuntosCierre(cierreDcho, cierreConvexo, infDcho, supDcha);
		anadePuntosCierre(cierreIzq, cierreConvexo, supIzda, infIzda);
        Log.d("DivideYVencerasPreord","Anade arista " + arista1.toString());
        Log.d("DivideYVencerasPreord","Anade arista " + arista2.toString());
		GestorConjuntoConvexo.getInstancia().anadeArista(arista1);
		GestorConjuntoConvexo.getInstancia().anadeArista(arista2);
		borraAristasCierre(cierreDcho, infDcho, supDcha);
		borraAristasCierre(cierreIzq, supIzda, infIzda);					
		}


	private void borraAristasCierre(List<Punto> cierreDcho, Punto infDcho,
			Punto supDcha) {
		Punto vertice = supDcha;
		while (vertice != infDcho)
		{
			Arista a1 = new Arista(vertice,siguiente(vertice, cierreDcho));
            Log.d("DivideYVencerasPreord","Borra arista " + a1.toString());
			GestorConjuntoConvexo.getInstancia().borraArista(a1);
            //Log.d("DivideYVencerasPreord","Borra arista " + a1.toString());
			//a1 = new Arista(siguiente(vertice, cierreDcho),vertice);
			//GestorConjuntoConvexo.getInstancia().borraArista(a1);
			vertice = siguiente(vertice, cierreDcho);
		}
	}


	private void anadePuntosCierre(List<Punto> cierreDcho,
			List<Punto> cierreConvexo, Punto infDcho, Punto supDcha) {
		Punto vertice = infDcho;
		while ( vertice != supDcha )
		{
			cierreConvexo.add(vertice);
			vertice = siguiente(vertice, cierreDcho);
		}
		cierreConvexo.add(vertice);
	}
		
		
	
	
	public Arista puenteSuperior(List<Punto> cierreIzq, List<Punto> cierreDcho)
	{
		Punto izq = this.busquedaPuntoMayorAbscisa(cierreIzq);
		Punto dcho = this.busquedaPuntoMenorAbscisa(cierreDcho);
		Punto sig_izq = siguiente(izq, cierreIzq);
		Punto ant_dcho = anterior(dcho,cierreDcho);
		int orien_izq = orientation(dcho,izq,sig_izq);
		int orien_dcho = orientation(ant_dcho,dcho,izq);
		while (orien_izq == FunctionsGlobals.NEGATIVA || orien_dcho  == FunctionsGlobals.NEGATIVA)			
		{
			if (orien_izq == FunctionsGlobals.NEGATIVA)
			{
				izq = sig_izq;
				sig_izq = siguiente(sig_izq, cierreIzq);
				
			}
			else
			{
				dcho = ant_dcho;
				ant_dcho = anterior(ant_dcho, cierreDcho);
				
			}
			orien_izq = orientation(dcho,izq,sig_izq);
			orien_dcho = orientation(ant_dcho,dcho,izq);
		}
		assert izq != null;
		assert dcho != null;		
		return new Arista(dcho,izq);
	}
	
	public Arista puenteInferior(List<Punto> cierreIzq, List<Punto> cierreDcho)
	{
		Punto izq = this.busquedaPuntoMayorAbscisa(cierreIzq);
		Punto dcho = this.busquedaPuntoMenorAbscisa(cierreDcho);
		Punto ant_izq = anterior(izq, cierreIzq);
		Punto sig_dcho = siguiente(dcho,cierreDcho);
		int orien_izq = orientation(dcho,izq,ant_izq);
		int orien_dcho = orientation(sig_dcho,dcho,izq);
		while (orien_izq == FunctionsGlobals.POSITIVA || orien_dcho  == FunctionsGlobals.POSITIVA)			
		{
			if (orien_izq == FunctionsGlobals.POSITIVA)
			{
				izq = ant_izq;
				ant_izq = anterior(ant_izq, cierreIzq);
				
			}
			else
			{
				dcho = sig_dcho;
				sig_dcho = siguiente(sig_dcho, cierreDcho);
				
			}
			orien_izq = orientation(dcho,izq,ant_izq);
			orien_dcho = orientation(sig_dcho,dcho,izq);
		}
		assert izq != null;
		assert dcho != null;	
		return new Arista(izq,dcho);
	}

}
