package dia.upm.cconvexo.interfaces;

//import java.awt.Color;
//import java.awt.Graphics2D;

//import javax.swing.JComponent;

import dia.upm.cconvexo.model.Arista;
import dia.upm.cconvexo.model.Punto;

public interface IDelegatePaint {

//	public void paint(JComponent component, Graphics2D graphic);
    public void paintPuntos();
	public void paintPunto(Punto pto);
	public void paintArista(Arista a1);
	public void paintArista(Arista a1, int color);
	public void borraRecta(Arista a1);
	public void borraPuntos();
	public void borraPuntoSubconjunto(Punto p);
}
