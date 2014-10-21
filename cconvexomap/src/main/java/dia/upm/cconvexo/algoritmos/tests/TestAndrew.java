package dia.upm.cconvexo.algoritmos.tests;


import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.algoritmos.Andrew;
import dia.upm.cconvexo.algoritmos.DerivadosGraham;
import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.model.Punto;

/**
 * @author icorrales
 *
 */
public class TestAndrew extends TestAbstract {

	protected DerivadosGraham algoritmo = null;
	/**
	 * @param name
	 */
	public TestAndrew(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		algoritmo = new Andrew();
		list = new LinkedList<Punto>();
		initPuntos();
		// initPuntos20();
		initCierreConvexoReal();
		//initCierreConvexoReal20();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	// Test que revisa que funcione el ordenamiento de la lista.	
	public void test2() throws Exception
	{
		Punto ptoCentrico = algoritmo.centroide(list.get(0), list.get(1), list.get(2));
		algoritmo.ordenarAngularmente(list, list.get(list.size()-1));		
		assertNotNull(list);
		List listOrdenada = new LinkedList<Punto>();
		initListaOrdenada(listOrdenada);
		assertEquals(listOrdenada,list);		
	}
	
	// Test que revisa que el cierre convexo es identico a la lista ordenada angularmente
	public void test3() throws Exception
	{
		List listOrdenada = new LinkedList<Punto>();
		initListaOrdenada(listOrdenada);
		algoritmo.ordenarAngularmente(list, list.get(list.size()-1));
		cierreConvexoReal.clear();
		cierreConvexoReal.addAll(listOrdenada);
		super.compruebaCierreConvexo(list);
		
	}
	
	// Test que comprueba el conjunto convexo ordenado de 10 elemenetos.
	public void test4() throws Exception
	{
		GestorConjuntoConvexo.getInstancia().setListaPuntos(this.list);
		algoritmo.start(0);
		super.compruebaCierreConvexoPorAristas(GestorConjuntoConvexo.getInstancia().getConjuntoConvexo());
		
		
	}
	
	// Test que comprueba el conjunto convexo por aristas ordenado, de 20 elemenetos.
		public void test5() throws Exception
		{
			initPuntos20();
			initCierreConvexoReal20();
			GestorConjuntoConvexo.getInstancia().setListaPuntos(this.list);
			algoritmo.start(0);
			super.compruebaCierreConvexoPorAristasGenerico(GestorConjuntoConvexo.getInstancia().getConjuntoConvexo(), this.cierreConvexoReal20);
			
			
		}

	private void initListaOrdenada(List<Punto> list) {
		// TODO Auto-generated method stub
		intoDataList(380,69,list);
		intoDataList(496,73,list);
		intoDataList(444,349,list);
		intoDataList(428,492,list);
		intoDataList(360,366,list);
		intoDataList(350,466,list);
		intoDataList(328,207,list);
		intoDataList(94,392,list);
		intoDataList(116,315,list);
		intoDataList(72,247,list);
		
		
		
		
		
		
		
				
																										
	}
	
	
}
