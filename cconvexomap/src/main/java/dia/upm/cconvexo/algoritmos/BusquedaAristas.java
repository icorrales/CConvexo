package dia.upm.cconvexo.algoritmos;

import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.model.Arista;
import dia.upm.cconvexo.model.Punto;

public class BusquedaAristas extends AbstractAlgoritmo {

	public final static String nombre = "BusAristas";
	
public void start(int delay)
{
	List<Punto> listaPuntos = GestorConjuntoConvexo.getInstancia().getListaPuntos();
	if (listaPuntos.size() != 1)
	{
		List<Arista> aristas = new LinkedList<Arista>();
		
	}

/*	c_convexo := lista_circular_vacia;
if N=1 then
c_convexo := {p(1)};
else
aristas := lista_vacia;

*/ 


/*
for i:=1 to N-1 do
for j:=i+1 to N do
k:=1; es_arista:=true; pto_izq:=false; pto_dcha:=false;

*/ 
	int N = listaPuntos.size();

	for (int i = 0; i < N; i++) {
		
		for (int j = i+1; j < N; j++) {
			
			int k = 0;
			boolean es_arista = true;
			boolean pto_izq = false;
			boolean pto_dcha = false;
			
			/*
			while k != N and es_arista do
			if k !=i and k != j then
			case orientacion(p(i),p(j),p(k)) of
			NEGATIVA: pto_dcha:=true;
					       if pto_izq then es_arista:=false endif
			POSITIVA: pto_izq:=true;
					     if pto_dcha then es_arista:=false endif
			LINEA: if p(k) no esta en el segmento [p(i),p(j)] then
						es_arista:=false
					endif
			end case
			endif
			k:=k+1
			endwhile

			*/
            Arista aTmp= new Arista();
            aTmp.setOrigen(listaPuntos.get(i));
            aTmp.setDestino(listaPuntos.get(j));
            GestorConjuntoConvexo.getInstancia().anadeAristaTmp(aTmp);
			while (k != N && es_arista)
			{
				if (k != i && k != j)
				{


                    GestorConjuntoConvexo.getInstancia().anadaPuntoSubconjunto(listaPuntos.get(k));
					switch (orientation(listaPuntos.get(i), listaPuntos.get(j), listaPuntos.get(k)))
					{
					case FunctionsGlobals.NEGATIVA: 
						pto_dcha = true;
						if (pto_izq == true)
						{
							es_arista = false;
                            GestorConjuntoConvexo.getInstancia().borraAristaTmp(aTmp);
						}
						break;
					case FunctionsGlobals.POSITIVA:
						pto_izq = true;
						if (pto_dcha == true)
						{
							es_arista = false;
                            GestorConjuntoConvexo.getInstancia().borraAristaTmp(aTmp);
						}
						break;
					case FunctionsGlobals.LINEA:
						break;
					}
				}
                GestorConjuntoConvexo.getInstancia().borraPuntoSubconjunto(listaPuntos.get(k));
				k++;
			}

			/*
			if es_arista then
			if pto_izq then
				insertar la arista p(i)->p(j) en la lista aristas;
			elseif pto_dcha then
				insertar la arista p(j)->p(i) en la lista aristas;	
			else
				insertar la arista p(i)->p(j) en la lista aristas;
				insertar la arista p(j)->p(i) en la lista aristas;
			endif
			endif
			endfor
			endfor
			
			
			arista:=primera arista de la lista aristas;
			repeat
			origen:=punto origen de arista;
			destino:=punto destino de arista;
			insertar origen en c_convexo;
			arista:=arista de la lista aristas cuyo punto origen sea destino
			until aristas = lista_vacia
			endif 
			*/
			if (es_arista)
			{
                GestorConjuntoConvexo.getInstancia().borraAristaTmp(aTmp);
				if (pto_izq)
				{

					GestorConjuntoConvexo.getInstancia().anadeArista(aTmp);
				}
				else if (pto_dcha)
				{
					Arista arista = new Arista(listaPuntos.get(j),listaPuntos.get(i));
					GestorConjuntoConvexo.getInstancia().anadeArista(arista);
				}
				else
				{
					Arista arista1 = new Arista(listaPuntos.get(i),listaPuntos.get(j));
					Arista arista2 = new Arista(listaPuntos.get(j),listaPuntos.get(i));
					GestorConjuntoConvexo.getInstancia().anadeArista(arista1);
					GestorConjuntoConvexo.getInstancia().anadeArista(arista2);					
				}
			}

		}
		
	}
 
}	
	public BusquedaAristas()
	{
		
	}
	
	

}
