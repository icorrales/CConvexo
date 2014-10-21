/*

package dia.upm.cconvexo.prueba;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

import dia.upm.cconvexo.acciones.GenerarPuntosAction;
import dia.upm.cconvexo.acciones.ListarPuntosAction;
import dia.upm.cconvexo.acciones.StartAlgoritmoAction;
import dia.upm.cconvexo.algoritmos.Andrew;
import dia.upm.cconvexo.algoritmos.BusquedaAristas;
import dia.upm.cconvexo.algoritmos.DivideYVencerasPreord;
import dia.upm.cconvexo.algoritmos.EliminacionPtosInteriores;
import dia.upm.cconvexo.algoritmos.GrahamNuevo;
import dia.upm.cconvexo.algoritmos.Jarvis;
import dia.upm.cconvexo.algoritmos.QuickHullNuevo;
import dia.upm.cconvexo.gestores.GestorAcciones;
import dia.upm.cconvexo.gestores.GestorAlgoritmos;

public class MenuSuperior extends JMenuBar {
	
	JMenu itemMenuArchivo = null;
	JMenu itemMenuEdicion = null;
	JMenu itemMenuVer = null;
	JMenu itemMenuOpciones = null;
	JMenu itemMenuAlgoritmos = null;
	JMenu itemMenuAyuda = null;
	
	
	public MenuSuperior() {
		
		
		initGui();
	}


	private void initGui() {
		itemMenuArchivo = new JMenu("Archivo");
		itemMenuEdicion = new JMenu("Edicion");
		itemMenuVer = new JMenu("Ver");
		itemMenuOpciones = new JMenu("Opciones");
		itemMenuAlgoritmos = new JMenu("Algoritmos");
		itemMenuAyuda = new JMenu("Ayuda");
		
		// Menu Archivo
		
	    menuArchivo();
	    
		// Menu Edicion
	    
	    menuEdicion();
	    
	    // Menu Ver
	    
	    menuVer();
	    
	    // Menu Opciones
	    
	    menuOpciones();
	   
	    // MenuAlgoritmos
	    menuAlgoritmos();
	    // Menu Ayuda
	    
	    
	    
	    add(itemMenuArchivo);	    
		add(itemMenuEdicion);
		add(itemMenuVer);
		add(itemMenuOpciones);
		add(itemMenuAlgoritmos);
		add(itemMenuAyuda);
		
		itemMenuArchivo.getPopupMenu().setLightWeightPopupEnabled(false);
		itemMenuEdicion.getPopupMenu().setLightWeightPopupEnabled(false);
		itemMenuVer.getPopupMenu().setLightWeightPopupEnabled(false);
		itemMenuOpciones.getPopupMenu().setLightWeightPopupEnabled(false);
		itemMenuAlgoritmos.getPopupMenu().setLightWeightPopupEnabled(false);
		itemMenuAyuda.getPopupMenu().setLightWeightPopupEnabled(false);
		
		
		
	}


	private void menuAlgoritmos() {
		
		Action algoritmoDYVp = new StartAlgoritmoAction(DivideYVencerasPreord.nombre);
		assert algoritmoDYVp != null;
		JMenuItem itemMAlgDYVp = new JCheckBoxMenuItem(algoritmoDYVp);
		itemMenuAlgoritmos.add(itemMAlgDYVp);
		
		// TODO Auto-generated method stub
		Action algoritmoJarvis = new StartAlgoritmoAction(Jarvis.nombre);
		assert algoritmoJarvis != null;
		JMenuItem itemMAlgJarvis = new JCheckBoxMenuItem(algoritmoJarvis);
		itemMenuAlgoritmos.add(itemMAlgJarvis);
		
		Action algoritmoQuickHull = new StartAlgoritmoAction(QuickHullNuevo.nombre);
		assert algoritmoQuickHull != null;
		JMenuItem itemMAlgQuickHull = new JCheckBoxMenuItem(algoritmoQuickHull);
		itemMenuAlgoritmos.add(itemMAlgQuickHull);
		
		Action algoritmoBusAristas = new StartAlgoritmoAction(BusquedaAristas.nombre);
		assert algoritmoBusAristas != null;
		JMenuItem itemMAlgBusAristas = new JCheckBoxMenuItem(algoritmoBusAristas);
		itemMenuAlgoritmos.add(itemMAlgBusAristas);
		
		Action algoritmoPtosInteriores = new StartAlgoritmoAction(EliminacionPtosInteriores.nombre);
		assert algoritmoPtosInteriores != null;
		JMenuItem itemMAlgPtosInteriores = new JCheckBoxMenuItem(algoritmoPtosInteriores);
		itemMenuAlgoritmos.add(itemMAlgPtosInteriores);
		
		Action algoritmoGraham = new StartAlgoritmoAction(GrahamNuevo.nombre);
		assert algoritmoGraham != null;
		JMenuItem itemMAlgGraham = new JCheckBoxMenuItem(algoritmoGraham);
		itemMenuAlgoritmos.add(itemMAlgGraham);
		
		Action algoritmoAndrew = new StartAlgoritmoAction(Andrew.nombre);
		assert algoritmoAndrew != null;
		JMenuItem itemMAlgAndrew = new JCheckBoxMenuItem(algoritmoAndrew);
		itemMenuAlgoritmos.add(itemMAlgAndrew);
		
		
	}


	private void menuOpciones() {
		JMenuItem itemMOpcEjes = new JMenuItem("Ejes");
	    itemMenuOpciones.addSeparator();
	    JMenuItem itemMOpcTRetardo = new JMenuItem("Tiempo de Retardo");
	    itemMOpcTRetardo.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// Hacer el dialogo a medida.
				String valorRetardo = JOptionPane.showInputDialog("Valor del retardo", GestorAlgoritmos.getInstancia().getDelay());
				GestorAlgoritmos.getInstancia().setDelay(Integer.parseInt(valorRetardo));
			}});
	    
	    JMenuItem itemMOpcTDirecta = new JRadioButtonMenuItem("Directa");
	    itemMOpcTDirecta.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				GestorAlgoritmos.getInstancia().setExecutiontype(GestorAlgoritmos.TEDIRECTA);
			}});
	    
	    JMenuItem itemMOpcTDelay = new JRadioButtonMenuItem("Retardo");
	    itemMOpcTDelay.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				GestorAlgoritmos.getInstancia().setExecutiontype(GestorAlgoritmos.TERETARDO);
			}});
	    
	    JMenuItem itemMOpcTSteptoStep = new JRadioButtonMenuItem("Paso a Paso");
	    itemMOpcTSteptoStep.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				GestorAlgoritmos.getInstancia().setExecutiontype(GestorAlgoritmos.TESTEPTOSTEP);
			}});
	    ButtonGroup grupoBotonesTE = new ButtonGroup();
	    grupoBotonesTE.add(itemMOpcTDirecta);
	    grupoBotonesTE.add(itemMOpcTDelay);
	    grupoBotonesTE.add(itemMOpcTSteptoStep);
	    
	    itemMenuOpciones.add(itemMOpcEjes);
	    itemMenuOpciones.add(itemMOpcTRetardo);
	    itemMenuOpciones.add(itemMOpcTDirecta);
	    itemMenuOpciones.add(itemMOpcTDelay);
	    itemMenuOpciones.add(itemMOpcTSteptoStep);
	    
	    itemMenuOpciones.getPopupMenu().setLightWeightPopupEnabled(true);
	}


	private void menuVer() {
		JCheckBoxMenuItem itemMenuVerRejilla = new JCheckBoxMenuItem("Rejilla");
	    JCheckBoxMenuItem itemMenuVerEjes = new JCheckBoxMenuItem("Ejes");
	    JCheckBoxMenuItem itemMenuVerCoordenadas = new JCheckBoxMenuItem("Coordenadas");
	    
	    itemMenuVer.add(itemMenuVerRejilla);
	    itemMenuVer.add(itemMenuVerEjes);
	    itemMenuVer.add(itemMenuVerCoordenadas);
	}


	private void menuEdicion() {
		JMenuItem itemMEdiCentrar = new JMenuItem("Centrar puntos");
	    JMenuItem itemMEdiLista = new JMenuItem("Lista puntos ...");
	    JMenuItem itemMEdiAleatorios = new JMenuItem("Puntos aleatorios ...");
	    
	    
	    
	    Map< String, Action> mapaAcciones = GestorAcciones.getInstancia().getMapa();
	    assert mapaAcciones != null;
	    Action listaPuntos = mapaAcciones.get(ListarPuntosAction.getNombre());
	    assert listaPuntos != null;
	    itemMEdiLista.setAction(listaPuntos);
	    Action aleatorios = mapaAcciones.get(GenerarPuntosAction.getNombre());
	    assert aleatorios != null;
	    itemMEdiAleatorios.setAction(aleatorios);
	    
	    itemMenuEdicion.add(itemMEdiCentrar);
	    itemMenuEdicion.add(itemMEdiLista);
	    itemMenuEdicion.add(itemMEdiAleatorios);
	}


	private void menuArchivo() {
		JMenuItem itemMenuArcNuevo = new JMenuItem("Nuevo");
	    JMenuItem itemMenuArcAbrir = new JMenuItem("Abrir");
	    JMenuItem itemMenuArcGuardar = new JMenuItem("Guardar");
	    JMenuItem itemMenuArcGuardarComo = new JMenuItem("Guardar como..");
	    JMenuItem itemMenuArcImprimir = new JMenuItem("Imprimir");
	    JMenuItem itemMenuArcSalir = new JMenuItem("Salir");
	    
	    itemMenuArchivo.add(itemMenuArcNuevo);	    
	    itemMenuArchivo.add(itemMenuArcAbrir);
	    itemMenuArchivo.addSeparator();
	    itemMenuArchivo.add(itemMenuArcGuardar);
	    itemMenuArchivo.add(itemMenuArcGuardarComo);
	    itemMenuArchivo.addSeparator();
	    itemMenuArchivo.add(itemMenuArcImprimir);
	    itemMenuArchivo.addSeparator();
	    itemMenuArchivo.add(itemMenuArcSalir);
	}

}


*/