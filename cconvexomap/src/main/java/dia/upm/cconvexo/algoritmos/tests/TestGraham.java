/**
 * 
 */
package dia.upm.cconvexo.algoritmos.tests;

import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.algoritmos.DerivadosGraham;
import dia.upm.cconvexo.algoritmos.GrahamNuevo;
import dia.upm.cconvexo.model.Punto;

/**
 * @author icorrales
 *
 */
public class TestGraham extends TestAbstract {

	protected DerivadosGraham algoritmo = null;
	/**
	 * @param name
	 */
	public TestGraham(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		algoritmo = new GrahamNuevo();
		list = new LinkedList<Punto>();
		initPuntos();
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
		Punto ptoCentrico = algoritmo.centroide(list.get(0), list.get(1), list.get(2));
		Punto ptoCentricoExpected = new Punto();
		ptoCentricoExpected.x=218;
		ptoCentricoExpected.y=352;
		assertEquals(ptoCentricoExpected, ptoCentrico);		
	}
	
	public void test2() throws Exception
	{
		Punto ptoCentrico = algoritmo.centroide(list.get(0), list.get(1), list.get(2));
		algoritmo.ordenarAngularmente(list, list.get(list.size()-1));		
		assertNotNull(list);
		List listOrdenada = new LinkedList<Punto>();
		initListaOrdenada(listOrdenada);
		assertEquals(list, listOrdenada);
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
