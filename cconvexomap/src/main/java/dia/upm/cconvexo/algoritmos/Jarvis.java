package dia.upm.cconvexo.algoritmos;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.model.Arista;
import dia.upm.cconvexo.model.Punto;



public class Jarvis extends AbstractAlgoritmo {
	
	public final static String nombre = "Jarvis";
	
	public Jarvis()
	{
		
	}
	

	/* 
	 * 
	 * c_convexo := lista_circular_vac�a;
	min_y := punto de {p(1),...,p(N)} con menor ordenada;
		(* si hay varios escoger el de mayor abscisa *) 
	pivote := min_y;
	repeat
	insertar pivote en la lista c_convexo;
	min_angulo := pivote;
	for i := 1 to N do
	if  i != pivote  then
	if  actualizar_min_angulo(pivote,p(i),min_angulo)  then
	min_angulo := p(i)
	endif
	endif
	endfor
	pivote := min_angulo
	until pivote = min_y
	 * */

	@Override
	public void start(int delay) {
		// TODO Auto-generated method stub
		List<Punto> list = GestorConjuntoConvexo.getInstancia().getListaPuntos();
		List<Punto> hull = new LinkedList<Punto>();
		Punto min_y = this.busquedaPuntoMenorOrdenada(list);
		Punto pivote = min_y;
		Arista aristaActual = new Arista();
		Arista aristaMinima = new Arista();
		do {
			hull.add(pivote);
			if (hull.size() >=2)
            {
                Punto origen = aristaMinima.getOrigen();
                Punto destino = aristaMinima.getDestino();
                Arista aSubconjunto = new Arista(origen,destino);
                GestorConjuntoConvexo.getInstancia().anadeArista(aSubconjunto);
            }
			Punto min_angulo = pivote;
			aristaActual.setOrigen(pivote);
			aristaMinima.setOrigen(pivote);
			
			for (Iterator<Punto> iterator = list.iterator(); iterator.hasNext();) {
				Punto i = (Punto) iterator.next();
				if (i.equals(pivote) == false)
				{
					aristaActual.setDestino(i);
					GestorConjuntoConvexo.getInstancia().anadeAristaTmp(aristaActual);
					if (actualizar_min_angulo(pivote, i, min_angulo))
					{
						
						if (aristaMinima.getDestino() != null )
						{
							GestorConjuntoConvexo.getInstancia().borraAristaTmp(aristaMinima);
						}	
						min_angulo = i;
						aristaMinima.setDestino(i);
					}
					else
					{
					  GestorConjuntoConvexo.getInstancia().borraAristaTmp(aristaActual);
					}
				}
					
			}
			pivote = min_angulo;
		} while (pivote != min_y);
        GestorConjuntoConvexo.getInstancia().borraSubconjuntoArista();
        GestorConjuntoConvexo.getInstancia().anadeArista(new Arista(hull.get(hull.size() -1),hull.get(0)));
		
		/*Iterator iterator = hull.iterator();
		Punto puntoPrimero = (Punto) iterator.next();
		Punto puntoAnterior = puntoPrimero;
		for ( Iterator iterator2 = iterator ; iterator.hasNext();) {
			Punto punto = (Punto) iterator.next();
			GestorConjuntoConvexo.getInstancia().anadeArista(new Arista(puntoAnterior,punto));
			puntoAnterior = punto;		
		}
		GestorConjuntoConvexo.getInstancia().anadeArista(new Arista(puntoAnterior,puntoPrimero));*/
	}
	

}
