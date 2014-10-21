package dia.upm.cconvexo.algoritmos.tests;

import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.algoritmos.AproximacionInferior;
import dia.upm.cconvexo.algoritmos.AproximacionSuperior;
import dia.upm.cconvexo.algoritmos.Jarvis;
import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.model.Arista;
import dia.upm.cconvexo.model.Punto;


public class TestAproximacionSuperior extends TestAbstract {

	protected AproximacionSuperior algoritmo = null;
	
	public TestAproximacionSuperior(String name) {
		super(name);
	}
		// TODO Auto-generated constructor stub
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		algoritmo = new AproximacionSuperior();
		algoritmo.setK_franjas(5);
		list= new LinkedList<Punto>();
		initPuntos();
		initCierreConvexoAproximacion();
		GestorConjuntoConvexo.getInstancia().borraListaPuntos();
		GestorConjuntoConvexo.getInstancia().setListaPuntos(list);
	}
	
	private void initCierreConvexoAproximacion() {
			// TODO Auto-generated method stub
			cierreConvexoReal.clear();
			Punto p = creaPunto(72.0,247.0);
			cierreConvexoReal.add(p);
			p = creaPunto(72.0,392.0);
			cierreConvexoReal.add(p);
			p = creaPunto(412.0,492.0);
			cierreConvexoReal.add(p);
			p = creaPunto(496.0,492.0);
			cierreConvexoReal.add(p);
			p = creaPunto(496,73.0);
			cierreConvexoReal.add(p);
			p = creaPunto(412,69.0);
			cierreConvexoReal.add(p);
			p = creaPunto(327.0,69.0);
			cierreConvexoReal.add(p);
			
		}
	private Punto creaPunto(double i, double j) {
		// TODO Auto-generated method stub
		Punto p = new Punto();
		p.setX(i);
		p.setY(j);
		return p;
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
		List<Arista> listaAristas = GestorConjuntoConvexo.getInstancia().getConjuntoConvexo();
		imprimeCierresConvexos(this.cierreConvexoReal,listaAristas);
		compruebaCierreConvexoPorAristasGenericoAprox(listaAristas, cierreConvexoReal);
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
