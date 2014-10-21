package dia.upm.cconvexo.algoritmos;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.global.ComparadorAngulos;
import dia.upm.cconvexo.model.Arista;
import dia.upm.cconvexo.model.Punto;

public abstract class DerivadosGraham extends AbstractAlgoritmo {

	public DerivadosGraham() {
		super();
	}

	protected void pintaAristas(int delay, List<Punto> cierreConvexo) {

		GestorConjuntoConvexo.getInstancia().getSubconjuntoArista().clear();
	}

	protected void pintaOrdenacion(int delay, Punto puntoInterior, List<Punto> cierreConvexo) {
		for (int i = 0; i < cierreConvexo.size(); i++) {

			Punto punto2 = (Punto) cierreConvexo.get(i);
            if (! puntoInterior.equals(punto2))
            {
			    GestorConjuntoConvexo.getInstancia().anadeAristaTmp(new Arista(puntoInterior, punto2));
            }
		}
	}

	protected void scan_graham(Punto puntoInterior, List<Punto> cierreConvexo) {
		Punto verticeInicio = puntoInterior;
		Punto v = puntoInterior;
		while (siguiente(v,cierreConvexo) != verticeInicio) {
			Punto siguiente_V = siguiente(v, cierreConvexo);					
			Punto sig_sig_V =  siguiente(siguiente_V,cierreConvexo);
            Arista a1 = new Arista(v,siguiente_V);
            Arista a2 = new Arista(siguiente_V,sig_sig_V);
            Arista a3 = new Arista(puntoInterior,siguiente_V);
            GestorConjuntoConvexo.getInstancia().anadeAristaTmp(a1);
            GestorConjuntoConvexo.getInstancia().anadeAristaTmp(a2);
			if (orientation(v, siguiente_V, sig_sig_V) == FunctionsGlobals.POSITIVA)
			{
				v = siguiente(v, cierreConvexo);
                GestorConjuntoConvexo.getInstancia().anadeArista(a1);

			}
			else
			{
				if (v != verticeInicio)
				{
					cierreConvexo.remove(siguiente_V);
                    GestorConjuntoConvexo.getInstancia().borraArista(a1);
                    GestorConjuntoConvexo.getInstancia().borraAristaTmp(a3);
					int indice_v = cierreConvexo.indexOf(v);
					v = cierreConvexo.get(indice_v-1);
				}
			}
            GestorConjuntoConvexo.getInstancia().borraAristaTmp(a1);
            GestorConjuntoConvexo.getInstancia().borraAristaTmp(a2);
		}
	}

}