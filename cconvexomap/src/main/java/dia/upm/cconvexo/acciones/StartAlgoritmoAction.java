/* package dia.upm.cconvexo.acciones;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import dia.upm.cconvexo.gestores.GestorAlgoritmos;
import dia.upm.cconvexo.interfaces.IAlgoritmoHullConvex;

public class StartAlgoritmoAction extends AbstractAction {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		IAlgoritmoHullConvex algoritmo = GestorAlgoritmos.getInstancia().getAlgoritmo((String) this.getValue(this.NAME));
		
		switch (GestorAlgoritmos.getInstancia().getExecutiontype()) {
		case GestorAlgoritmos.TEDIRECTA:
			algoritmo.start(0);
			break;
		case GestorAlgoritmos.TERETARDO:
			algoritmo.start(GestorAlgoritmos.getInstancia().getDelay());
			break;
		default:
			assert GestorAlgoritmos.getInstancia().getExecutiontype() == GestorAlgoritmos.TESTEPTOSTEP;
			algoritmo.nextstep();
			break;
		}
		algoritmo.start(200);
		
	}
	
	
	public StartAlgoritmoAction(String nombreAlgoritmo)	
	{
		super(nombreAlgoritmo);
	}

}


*/