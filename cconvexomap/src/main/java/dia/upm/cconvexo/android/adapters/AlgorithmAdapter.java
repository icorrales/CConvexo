package dia.upm.cconvexo.android.adapters;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import dia.upm.cconvexo.algoritmos.Andrew;
import dia.upm.cconvexo.algoritmos.AproximacionInferior;
import dia.upm.cconvexo.algoritmos.AproximacionSuperior;
import dia.upm.cconvexo.algoritmos.BusquedaAristas;
import dia.upm.cconvexo.algoritmos.DivideYVencerasPreord;
import dia.upm.cconvexo.algoritmos.EliminacionPtosInteriores;
import dia.upm.cconvexo.algoritmos.GrahamNuevo;
import dia.upm.cconvexo.algoritmos.Incremental;
import dia.upm.cconvexo.algoritmos.Jarvis;
import dia.upm.cconvexo.algoritmos.QuickHullNuevo;
import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;

/**
 * Created by ivan on 25/03/14.
 */
public class AlgorithmAdapter extends ArrayAdapter<String> {

    String[] datos = {BusquedaAristas.nombre, EliminacionPtosInteriores.nombre ,  Jarvis.nombre, QuickHullNuevo.nombre ,Incremental.nombre,  GrahamNuevo.nombre,Andrew.nombre, DivideYVencerasPreord.nombre , AproximacionInferior.nombre, AproximacionSuperior.nombre};
    public AlgorithmAdapter(Context context, int resource) {
        super(context, resource);
        this.addAll(datos);

    }

    @Override
    public boolean areAllItemsEnabled()
    {

        boolean respuesta = ! GestorConjuntoConvexo.getInstancia().getListaPuntos().isEmpty();
        Log.d("areAllItemsEnabled " + respuesta, this.getClass().getName());
        return respuesta;
    }
    
    @Override
    public  boolean isEnabled(int position)
    {
        boolean respuesta = ! GestorConjuntoConvexo.getInstancia().getListaPuntos().isEmpty();
        Log.d("isEnabled " + respuesta, this.getClass().getName());
        return respuesta;
    }



}
