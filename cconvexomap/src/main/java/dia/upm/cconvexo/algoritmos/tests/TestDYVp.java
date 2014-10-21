package dia.upm.cconvexo.algoritmos.tests;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.algoritmos.DivideYVencerasPreord;
import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.global.ComparadorAbscisas;
import dia.upm.cconvexo.model.Arista;
import dia.upm.cconvexo.model.Punto;


public class TestDYVp extends TestAbstract {
	
	DivideYVencerasPreord algoritmo = null;
	private int mitad;
	private List<Punto> cierreIzdo;
	private List<Punto> cierreDcho;
	

	public TestDYVp(String name) {
		super(name);
		
		// TODO Auto-generated constructor stub
	}
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		algoritmo = new DivideYVencerasPreord();
		list = new LinkedList<Punto>();
		initPuntos();
		initCierreConvexoReal();
		Collections.sort(list,new ComparadorAbscisas());
		mitad = Math.abs(list.size() / 2);
		cierreIzdo = initcierreIzdo();
		cierreDcho = initcierreDcho();
		
	}

	private List<Punto> initcierreIzdo() {
		// TODO Auto-generated method stub
		List<Punto> listIzdo = new LinkedList<Punto>();
		listIzdo.add(list.get(0));
		listIzdo.add(list.get(3));
		listIzdo.add(list.get(4));
		listIzdo.add(list.get(1));
		return listIzdo;
	}

	private List<Punto> initcierreDcho() {
		// TODO Auto-generated method stub
		List<Punto> listDcho = new LinkedList<Punto>();
		listDcho.add(list.get(5));
		listDcho.add(list.get(6));
		listDcho.add(list.get(9));
		listDcho.add(list.get(7));
		return listDcho;
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		list = null;
		algoritmo = null;
		super.tearDown();
	}
	
	public void test1() throws Exception
	{
		
		Arista arista = algoritmo.puenteSuperior(cierreIzdo, cierreDcho);		
		assertEquals(list.get(7),arista.getOrigen());
		assertEquals(list.get(1),arista.getDestino());
	}
	
	public void test2() throws Exception
	{	
		Arista arista = algoritmo.puenteInferior(cierreIzdo, cierreDcho);		
		assertEquals(list.get(0),arista.getOrigen());
		assertEquals(list.get(6),arista.getDestino());

	}
	
	public void test3() throws Exception
	{
		List<Punto> cierreConvexo = new LinkedList<Punto>();
		algoritmo.divideyvenceras(list, cierreConvexo);
		compruebaCierreConvexo(cierreConvexo);
		compruebaCierreConvexoPorAristas(GestorConjuntoConvexo.getInstancia().getConjuntoConvexo());
	}
	
	
	

}
