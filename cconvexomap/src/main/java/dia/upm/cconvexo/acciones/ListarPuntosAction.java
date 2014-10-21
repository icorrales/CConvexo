/*

package dia.upm.cconvexo.acciones;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.model.Punto;

public class ListarPuntosAction extends AbstractAction {

	private static final String nombre = "Lista Puntos ...";
	
	public ListarPuntosAction()
	{
		super(getNombre());
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		List<Punto> listaPuntos = GestorConjuntoConvexo.getInstancia().getListaPuntos();		
		JOptionPane.showInputDialog(null, "Lista de Puntos", "Lista de Puntos",
		        JOptionPane.YES_OPTION, null, listaPuntos.toArray(),null);
	}
	
	public static String getNombre()
	{
	  assert nombre != null;
	  return nombre;
	}

}


*/