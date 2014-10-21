/*

package dia.upm.cconvexo.prueba;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;

import dia.upm.cconvexo.algoritmos.QuickHull;
import dia.upm.cconvexo.gestores.GestorAcciones;
import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.interfaces.IDelegatePaint;
import dia.upm.cconvexo.model.Arista;
import dia.upm.cconvexo.model.Punto;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/

/*
public class NewJPanel extends javax.swing.JPanel implements IDelegatePaint{
	private Canvas jTextArea1;
	private JFrame jFrame1;
	private JScrollPane jScrollCanvas1;
	
	private JMenu jMenu1;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/

	/*
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setJMenuBar(new MenuSuperior());
		frame.getContentPane().add(new NewJPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		
		frame.pack();
		frame.setVisible(true);
	}
	
	public NewJPanel() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setPreferredSize(new Dimension(400, 300));
			{
	
			}
			{
				this.setLayout(new BorderLayout());
				
				JTextArea jTextArea2 = new JTextArea();
				jTextArea2.setPreferredSize(new Dimension(200,100));
				jTextArea1 = new Canvas();			
				jScrollCanvas1 = new JScrollPane(jTextArea1);
				jScrollCanvas1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				this.add(jScrollCanvas1, BorderLayout.CENTER);
				this.add(jTextArea2,BorderLayout.NORTH);
				initAll();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initAll() {
		// TODO Auto-generated method stub
		GestorConjuntoConvexo.getInstancia().addListener(this);		
	}

	private JFrame getJFrame1() {
		if(jFrame1 == null) {
			jFrame1 = new JFrame();
		}
		
		
		return jFrame1;
	}

	@Override
	public void paint(JComponent component, Graphics2D graphic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paintPuntos(Graphics2D graphic) {
		// TODO Auto-generated method stub
		
		RGraphics grafico = new RGraphics((Graphics2D) jTextArea1.getGraphics());
		List<Punto> listaPuntos = GestorConjuntoConvexo.getInstancia().getListaPuntos();
		for (Iterator iterator = listaPuntos.iterator(); iterator.hasNext();) {
			Punto punto = (Punto) iterator.next();
			assert punto != null;
			grafico.drawFatPoint(punto.getPoint());
			grafico.drawString(punto.toString(), (int) punto.getX(), Math.abs((int) punto.getY()));
			
		}
		
		
		
	    
		
/*		for (int i =1 ; i < hull.size(); i++) {
			grafico.drawLine( (hull.get(i-1)).getPoint(),(hull.get(i)).getPoint());
		}
		if (hull.size() > 0)
			grafico.drawLine(hull.get(hull.size()-1).getPoint(),hull.get(0).getPoint());
*/

/*
	}



	@Override
	public void borraRecta(Arista a1) {
		assert a1 != null;
		assert a1.getOrigen() != null && a1.getDestino() != null;
		
		RGraphics grafico = new RGraphics((Graphics2D) jTextArea1.getGraphics());
		grafico.setColor(Color.white);
		grafico.drawLine(a1.getOrigen().getPoint(),a1.getDestino().getPoint());
		grafico.setColor(Color.BLACK);
		grafico.drawFatPoint(a1.getOrigen().getPoint());
		grafico.drawFatPoint(a1.getDestino().getPoint());

	}

	@Override
	public void paintArista(Arista a1) {
		// TODO Auto-generated method stub
		
		paintArista(a1, Color.BLACK);
	}
	
	@Override
	public void paintArista(Arista a1, Color color) {
		// TODO Auto-generated method stub
		assert a1 != null;
		assert a1.getOrigen() != null && a1.getDestino() != null;
		
		RGraphics grafico = new RGraphics((Graphics2D) jTextArea1.getGraphics());
		grafico.setBackground(color);
		grafico.drawLine(a1.getOrigen().getPoint(),a1.getDestino().getPoint());		

	}


	@Override
	public void borraPuntoSubconjunto(Punto p) {
		// TODO Auto-generated method stub
		assert p != null;
		RGraphics grafico = new RGraphics((Graphics2D) jTextArea1.getGraphics());
		grafico.setColor(grafico.getBackground());
		grafico.drawFatPoint(p.getPoint());
		grafico.setColor(Color.BLACK);
	}

	@Override
	public void borraPuntos() {
	    RGraphics grafico = new RGraphics((Graphics2D) jTextArea1.getGraphics());
	    Rectangle rec = jTextArea1.getBounds();
	    grafico.clearRect(0, 0, rec.width, rec.height);
	    

		
	}

	@Override
	public void paintPunto(Punto punto) {
		// TODO Auto-generated method stub
		assert punto != null;
		RGraphics grafico = new RGraphics((Graphics2D) jTextArea1.getGraphics());
		grafico.drawFatPoint(punto.getPoint());
	}

	
	

}


*/