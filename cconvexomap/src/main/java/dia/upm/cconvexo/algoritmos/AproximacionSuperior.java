package dia.upm.cconvexo.algoritmos;

import android.util.Log;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.gestores.GestorFranjas;
import dia.upm.cconvexo.model.Punto;

public class AproximacionSuperior extends AproximacionGeneral {

    public final static String nombre = "AproxSuperior";

//	public List<Punto> calculaMuestra2(List<Punto> listPuntos) {
//		// TODO Auto-generated method stub
//		Punto x_max = GestorFranjas.getInstancia().getPuntoMax();
//		Punto x_min = GestorFranjas.getInstancia().getPuntoMin();
//		assert listPuntos != null;
//		assert x_max != null && x_min != null;
//		int franja = 0;
//		List<Punto> muestra = new LinkedList<Punto>();
//
//		// Se Introducen los puntos en las franjas atendiendo a su posición.
//		for (Iterator<Punto> iterator = listPuntos.iterator(); iterator.hasNext();) {
//			Punto punto =  iterator.next();
//			franja = GestorFranjas.getInstancia().getFranja(punto);
//			List<Punto> listFranja = GestorFranjas.getInstancia().getListaFranjas().get(franja);
//			// Primero no miramos los puntos x_max o x_min que siempre estarán dentro de la muestra.
//			if (!(punto.equals(x_max) || punto.equals(x_min)))
//			{
//				// Ahora revisamos si el punto es de los que hay que mirar en la franja. Al final nos quedamos con solo dos puntos por franja
//				if (listFranja.isEmpty())
//				{
//					creaPuntoenFranjas(franja, punto, listFranja,0);
//					creaPuntoenFranjas(franja, punto, listFranja,2);
//				}
//				else
//				{
//					if (punto.getY()>listFranja.get(0).getY())
//					{
//						creaPuntoenFranjas(franja, punto, listFranja,0);
//
//					}
//
//					if (punto.getY()<listFranja.get(2).getY())
//					{
//						creaPuntoenFranjas(franja, punto, listFranja,2);
//					}
//				}
//			}
//		}
//		// Hay que hacer la ordenación de superior y retornar la muestra.
//
//
//		for (Iterator iterator = GestorFranjas.getInstancia().getListaFranjas().iterator(); iterator.hasNext();) {
//			List<Punto> list = (List<Punto>) iterator.next();
//			// Si la franja no tiene puntos, no añadimos nada.
//			if (! list.isEmpty())
//			{
//				muestra.add(list.get(0));
//				muestra.add(list.get(1));
//
//			}
//		}
//		// Añadimos los puntos x_min al principio y x_max al final.
//		muestra.add(0, x_min);
//		muestra.add(muestra.size(),x_max);
//		return muestra;
//	}

	private void creaPuntoenFranjas(int franja, Punto punto,
			List<Punto> listFranja, int indexInicial) {
		Punto p = new Punto();
		p.x = GestorFranjas.getInstancia().getAbscisaFranja(franja);
		p.y = punto.y;
		Punto p_next_franja = new Punto();
		p_next_franja.x = GestorFranjas.getInstancia().getAbscisaFranja(franja + 1);
		p_next_franja.y = punto.y;
		listFranja.add(p);
		listFranja.add(p_next_franja);
	}
	
	public List<Punto> calculaMuestra(List<Punto> listPuntos)
	{
		// TODO Auto-generated method stub
        Log.d(AproximacionSuperior.class.getName(), "Inicio calculaMuestra");
        Punto x_max = GestorFranjas.getInstancia().getPuntoMax();
        Punto x_min = GestorFranjas.getInstancia().getPuntoMin();
        assert listPuntos != null;
        assert x_max != null && x_min != null;
        int franja = 0;
        List<Punto> muestra = new LinkedList<Punto>();
        Log.d(AproximacionInferior.class.getName(), "Inicio escogeMaximos");
        escogeMaximosFranjas(listPuntos);
        Log.d(AproximacionInferior.class.getName(), "Fin escogeMaximos");
        mezclaFranjas(muestra);
        Log.d(AproximacionInferior.class.getName(), "Fin mezclaFranjas " + muestra);
        assert muestra.size() >0;
        return muestra;

	}
	
	public void mezclaFranjas(List<Punto> muestra)
	{
		assert muestra != null && muestra.isEmpty();
		for (Iterator iterator = GestorFranjas.getInstancia().getListaFranjas().iterator(); iterator.hasNext();) {
			List<Punto> list = (List<Punto>) iterator.next();
			// Si la franja no tiene puntos, no añadimos nada.
			if (! list.isEmpty())
			{
				assert list.size() == 2;
				Punto punto_max = list.get(0);
				assert punto_max != null;
				Punto punto_min = list.get(1);
				int franja = GestorFranjas.getInstancia().getFranja(punto_max);
				assert franja >= 0 ;
				if (punto_max.equals(punto_min))
				{
					muestra.add(this.creaPuntoenFranjas(punto_max,franja ));
					muestra.add(this.creaPuntoenFranjas(punto_max,franja +1 ));
				}
				else
				{
					muestra.add(this.creaPuntoenFranjas(punto_max,franja ));
					muestra.add(this.creaPuntoenFranjas(punto_min,franja ));
					muestra.add(this.creaPuntoenFranjas(punto_max,franja +1));
					muestra.add(this.creaPuntoenFranjas(punto_min,franja +1));
				}
			}
		}
		
	}

	private Punto creaPuntoenFranjas(Punto punto_max, int franja) {
		// TODO Auto-generated method stub
		assert franja >= 0 && punto_max != null;
		Punto p = new Punto();
		p.x = GestorFranjas.getInstancia().getAbscisaFranja(franja);
		p.y = punto_max.y;
		return p;
	}

	public AproximacionSuperior()
	{
		
	}

	
	
	

	
	

}
