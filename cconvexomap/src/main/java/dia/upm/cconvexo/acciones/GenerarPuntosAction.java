/*


package dia.upm.cconvexo.acciones;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.geom.Point2D;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;



import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.interfaces.IDelegatePaint;
import dia.upm.cconvexo.model.Punto;





public class GenerarPuntosAction extends AbstractAction{
	
	private static final String nombre = "Generar Puntos ...";
	private IDelegatePaint delegate;
	private Graphics2D graphic;
	
	public GenerarPuntosAction()
	{
		super(getNombre());
	}
	public GenerarPuntosAction(IDelegatePaint delegate, Graphics2D graphic)
	{
		assert delegate != null;
		assert graphic != null;
		this.delegate = delegate;
		this.graphic = graphic;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		// Generacion de la lista de puntos y almacenamiento en el gestor.
		String numeroPuntos = JOptionPane.showInputDialog("Numero de Puntos", 10);
		List<Punto> listaPuntos = new LinkedList<Punto>();
		Random random = new Random(GregorianCalendar.getInstance().getTimeInMillis());
		assert random != null;
		int NMAX = Integer.parseInt(numeroPuntos);
		for (int i = 0; i < NMAX ; i++) {
			Punto point = new Punto();
			int puntox = Math.abs((random.nextInt() % 1000));
			int puntoy = Math.abs(random.nextInt() % 600);
			point.setLocation(puntox,puntoy);
			listaPuntos.add(point);
			System.out.print(point);
		}
		
		// Llamar al delegado para que pinte los puntos o no hacerlo que al cambiar la lista de puntos en el gestor este lance los eventos pertinentes.
		// delegate.paint(graphic);
		GestorConjuntoConvexo.getInstancia().borraListaPuntos();
		GestorConjuntoConvexo.getInstancia().setListaPuntos(listaPuntos);
	}
	
	public static String getNombre()
	{
	  assert nombre != null;
	  return nombre;
	}

	

}


*/