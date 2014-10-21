/*

package dia.upm.cconvexo.acciones;

import java.awt.Canvas;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;

import dia.upm.cconvexo.gestores.GestorAcciones;

public class InicioAccionesAction {
	
	public InicioAccionesAction()
	{
		
	}
	
	public static void inicioAcciones(JFrame frame, Canvas canvas)
	{
		
		//Creacion de las acciones
		AbstractAction accion1 = new GenerarPuntosAction();
		
		//Inserccion de las acciones en la tabla hash
		GestorAcciones gestor = GestorAcciones.getInstancia();
		Map<String, Action> mapaAcciones = gestor.getMapa();
		mapaAcciones.put(accion1.NAME, accion1);
		
		
		
	}
	

}

*/