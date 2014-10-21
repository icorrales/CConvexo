package dia.upm.cconvexo.gestores;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.model.Punto;

public class GestorFranjas {
	
	private static GestorFranjas instancia = null;
	Punto puntoMin = null;
	Punto puntoMax = null;
	int numeroFranjas = 5;
	List<List<Punto>> listaFranjas = null;
	
	public Punto getPuntoMin() {
		return puntoMin;
	}

	public void setPuntoMin(Punto puntoMin) {
		this.puntoMin = puntoMin;
	}

	public Punto getPuntoMax() {
		return puntoMax;
	}

	public void setPuntoMax(Punto puntoMax) {
		this.puntoMax = puntoMax;
	}

	public int getNumeroFranjas() {
		return numeroFranjas;
	}

	public void setNumeroFranjas(int numeroFranjas) {
		assert numeroFranjas > 0;
		this.numeroFranjas = numeroFranjas;
		int indice = 0;
        this.listaFranjas.clear();
		while (indice < numeroFranjas)
		{
			this.listaFranjas.add(indice, new LinkedList<Punto>());
			indice=indice + 1;
		}
	}

	public static GestorFranjas getInstancia ()
	{
		if (instancia == null)
		{
			instancia = new GestorFranjas();			
		}
		assert instancia != null;
		return instancia;
	}
	
	public int getFranja(Punto punto)
	{
		assert puntoMin != null;
		assert this.numeroFranjas > 0;

        Log.d(GestorFranjas.class.getName() + "- getFranja", "Inicio getFranja p" + punto.toString());
		
		int franja = 0;
		if (punto.equals(puntoMin))
		{
            Log.d(GestorFranjas.class.getName() + "- getFranja", "franja 0" + puntoMin.toString() + " " + punto.toString());
			franja = 0;
		}
		else
		{
			// Para obtener la franja donde tiene que ir el punto
            Log.d(GestorFranjas.class.getName() + "- getFranja", " franja " + punto.getX() + " - " + this.puntoMin.getX()+ "/" + this.anchoFranja());
			franja = (int) Math.floor((punto.getX()-this.puntoMin.getX())/this.anchoFranja());			
		}

        if (franja >= this.numeroFranjas) { franja = this.numeroFranjas -1; }
        Log.d(GestorFranjas.class.getName() + "- getFranja", "franja " + franja);
		assert franja >= 0;
		return franja;
	}
	public List<List<Punto>> getListaFranjas() {
		return listaFranjas;
	}

	public void setListaFranjas(List<List<Punto>> listaFranjas) {
		this.listaFranjas = listaFranjas;
	}

	private GestorFranjas()
	{
		this.listaFranjas = new LinkedList<List<Punto>>();
	}

	public void reset() {
		// TODO Auto-generated method stub
		this.listaFranjas.clear();
//		this.numeroFranjas = 0;
		this.puntoMax = null;
		this.puntoMin = null;
	}

	public double getAbscisaFranja(int franja) {
		// TODO Auto-generated method stub
		assert franja >= 0;
		assert this.numeroFranjas > 0;
		assert this.puntoMax != null;
		assert this.puntoMin != null;
		double abscisaFranja = 0;
		// Revisar los casos en que la franja sea la 0 o la ultima
		if ( franja == this.numeroFranjas) 
		{
		   abscisaFranja = this.puntoMax.getX();
		}
		else if (franja == 0)
		{
			abscisaFranja = this.puntoMin.getX();
		}
		else
		{
			abscisaFranja = (long) this.puntoMin.getX() + (franja * anchoFranja());
		}
		assert (abscisaFranja >= puntoMin.getX());
		assert (abscisaFranja <= puntoMax.getX());
		return abscisaFranja;
	}

	public long anchoFranja() {
		// TODO Auto-generated method stub
		assert this.puntoMin != null;
		assert this.puntoMax != null;
		assert this.numeroFranjas > 0;
		
		long anchoFranja = Math.round((this.puntoMax.getX() - this.puntoMin.getX()) / this.numeroFranjas);
			
		return anchoFranja;
	}

    public boolean isactivate() {
        return listaFranjas != null && listaFranjas.size() > 0;
    }
}
