package dia.upm.cconvexo.algoritmos.tests;

import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.algoritmos.Incremental;
import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.model.Arista;
import dia.upm.cconvexo.model.Punto;

public class TestIncremental extends TestAbstract {

	protected Incremental algoritmo = null;
	
	public TestIncremental(String name) {
		super(name);
		
	}

	protected void setUp() throws Exception {		
		super.setUp();
		algoritmo = new Incremental();
		list = new LinkedList<Punto>();
		initPuntos();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		algoritmo = null;
		list = null;
	}

	public void test1() throws Exception
	{
		algoritmo.ordenacion_abscisas(list);
		List<Punto> listOrdenadaAbscisas = new LinkedList<Punto>();
		initListaOrdenada(listOrdenadaAbscisas);
		assertEquals(list, listOrdenadaAbscisas);
	}
	
	public void test2() throws Exception
	{
		algoritmo.ordenacion_abscisas(list);
		List<Punto> c_convexo = new LinkedList<Punto>();
		c_convexo.add(list.get(0));
		Punto puntoSoporte = algoritmo.soporte_superior(c_convexo, list.get(0), list.get(1));
		assertEquals(list.get(0), puntoSoporte);		
		c_convexo.add(list.get(1));
		puntoSoporte = algoritmo.soporte_superior(c_convexo, list.get(1), list.get(2));
		assertEquals(list.get(1), puntoSoporte);
		puntoSoporte = algoritmo.soporte_inferior(c_convexo, list.get(1), list.get(2));
		assertEquals(list.get(0), puntoSoporte);
	}
	
	public void test3() throws Exception
	{
		algoritmo.ordenacion_abscisas(list);
		List<Punto> c_convexo = new LinkedList<Punto>();
		c_convexo.add(list.get(0));			
		c_convexo.add(list.get(1));
		c_convexo.add(list.get(2));
		c_convexo.add(list.get(3));
		c_convexo.add(list.get(4));
		c_convexo.add(list.get(5));
		c_convexo.add(list.get(6));
		
		Punto puntoSoporte = algoritmo.soporte_superior(c_convexo, list.get(6), list.get(7));
		assertEquals(list.get(6), puntoSoporte);
		puntoSoporte = algoritmo.soporte_inferior(c_convexo, list.get(6), list.get(7));
		assertEquals(list.get(1), puntoSoporte);		
		
	}
	
	public void test4() throws Exception
	{
		GestorConjuntoConvexo.getInstancia().setListaPuntos(list);
		algoritmo.start(0);
		List<Arista> conjuntoConvexo = GestorConjuntoConvexo.getInstancia().getConjuntoConvexo();
		assertNotNull(conjuntoConvexo);
	}
	
	private void initListaOrdenada(List<Punto> list) {
		// TODO Auto-generated method stub
		intoDataList(72,247,list);
		intoDataList(94,392,list);
		intoDataList(116,315,list);
		intoDataList(328,207,list);
		intoDataList(350,466,list);		
		intoDataList(360,366,list);
		intoDataList(380,69,list);
		intoDataList(428,492,list);
		intoDataList(444,349,list);		
		intoDataList(496,73,list);		
	}



}
