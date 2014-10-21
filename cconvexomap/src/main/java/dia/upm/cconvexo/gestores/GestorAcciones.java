/*
package dia.upm.cconvexo.gestores;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.swing.Action;

import dia.upm.cconvexo.acciones.GenerarPuntosAction;
import dia.upm.cconvexo.acciones.ListarPuntosAction;

public class GestorAcciones {
	
	static GestorAcciones instancia = null;
	private Map<String,Action> mapaAcciones = null;
	
	private GestorAcciones()
	{
		mapaAcciones = new HashMap<String,Action>();
		mapaAcciones.put(GenerarPuntosAction.getNombre(), new GenerarPuntosAction());
		mapaAcciones.put(ListarPuntosAction.getNombre(), new ListarPuntosAction());
		assert mapaAcciones != null;
	}
	
	public static GestorAcciones getInstancia ()
	{
		if (instancia == null)
		{
			instancia = new GestorAcciones();			
		}
		assert instancia != null;
		return instancia;
	}

	public Map<String,Action> getMapa()
	{
		assert mapaAcciones != null;
		return mapaAcciones;
	}
}
*/
