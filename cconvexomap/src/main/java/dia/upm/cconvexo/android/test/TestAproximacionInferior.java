package dia.upm.cconvexo.android.test;

import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.algoritmos.AproximacionGeneral;
import dia.upm.cconvexo.algoritmos.AproximacionInferior;
import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.model.Punto;


public class TestAproximacionInferior extends TestAbstract {

	protected AproximacionGeneral algoritmo = null;
	
	public TestAproximacionInferior() {
		super();
	}
		// TODO Auto-generated constructor stub
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		algoritmo = new AproximacionInferior();
		algoritmo.setK_franjas(5);
		list= new LinkedList<Punto>();
		initPuntos();
		initCierreConvexoReal();
		GestorConjuntoConvexo.getInstancia().borraListaPuntos();
		GestorConjuntoConvexo.getInstancia().setListaPuntos(list);
	}
	
	protected void tearDown() throws Exception {
		list = null;
		algoritmo = null;
		super.tearDown();
	}
	
	public void test1() throws Exception
	{
		List<Punto> cierreConvexo = new LinkedList<Punto>();
		algoritmo.start(0);
		//List listaCC= GestorConjuntoConvexo.getInstancia().getListaPuntos();
		//compruebaCierreConvexo(listaCC);
		List listaAristas = GestorConjuntoConvexo.getInstancia().getConjuntoConvexo();
		compruebaCierreConvexoPorAristas(listaAristas);
	}
	
	public void test2() throws Exception
	{
		initPuntos20();
		initCierreConvexoReal20();
		GestorConjuntoConvexo.getInstancia().borraListaPuntos();
		GestorConjuntoConvexo.getInstancia().setListaPuntos(list);
		List<Punto> cierreConvexo = new LinkedList<Punto>();
		algoritmo.start(0);
		//List listaCC= GestorConjuntoConvexo.getInstancia().getListaPuntos();
		//compruebaCierreConvexo(listaCC);
		List listaAristas = GestorConjuntoConvexo.getInstancia().getConjuntoConvexo();
		compruebaCierreConvexoPorAristasGenerico(listaAristas,cierreConvexoReal20);
	}
}
