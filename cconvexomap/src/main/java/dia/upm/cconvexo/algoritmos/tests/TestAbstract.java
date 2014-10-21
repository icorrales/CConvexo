package dia.upm.cconvexo.algoritmos.tests;

import junit.framework.TestCase;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.model.Arista;
import dia.upm.cconvexo.model.Punto;

public abstract class TestAbstract extends TestCase {
	
	
	protected List<Punto> list = new LinkedList<Punto> ();
	protected List<Punto> cierreConvexoReal = new LinkedList<Punto> ();
	protected List<Punto> cierreConvexoReal20 = new LinkedList<Punto> ();

	public TestAbstract(String name)
	{		
		super(name);
				
		
	}

	public void initCierreConvexoReal()
	{
		cierreConvexoReal.clear();
		cierreConvexoReal.add(list.get(4));
		cierreConvexoReal.add(list.get(9));
		cierreConvexoReal.add(list.get(7));
		cierreConvexoReal.add(list.get(5));
		cierreConvexoReal.add(list.get(0));
	}
	
	public void initPuntos() {
		list.clear();
		intoDataList(94,392,list);
		intoDataList(116,315,list);
		intoDataList(444,349,list);
		intoDataList(360,366,list);
		intoDataList(72,247,list);
	    //5
		intoDataList(428,492,list);
		intoDataList(328,207,list);
		intoDataList(496,73,list);
		intoDataList(350,466,list);
		intoDataList(380,69,list);
		
	} 
	
	public void initPuntos20() {
		
		list.clear();
		
		intoDataList(242,429,list);
		intoDataList(320,7,list);
		intoDataList(202,312,list);
		intoDataList(499,110,list);
		intoDataList(109,77,list);
		//5
		intoDataList(370,119,list);
		intoDataList(335,264,list);
		intoDataList(347,205,list);
		intoDataList(153,203,list);
		intoDataList(467,159,list);
		//10
		intoDataList(471,337,list);
		intoDataList(433,281,list);
		intoDataList(171,166,list);
		intoDataList(276,382,list);
		intoDataList(98,318,list);
		//15
		intoDataList(146,423,list);
		intoDataList(154,243,list);
		intoDataList(222,268,list);
		intoDataList(25,34,list);
		intoDataList(256,68,list);
		intoDataList(320,4,list);
	}
	
	
	
	
	
	public void initCierreConvexoReal20()	
	{
		cierreConvexoReal20.clear();
		cierreConvexoReal20.add(list.get(18));
		cierreConvexoReal20.add(list.get(14));
		cierreConvexoReal20.add(list.get(15));
		cierreConvexoReal20.add(list.get(0));
		cierreConvexoReal20.add(list.get(10));
		cierreConvexoReal20.add(list.get(3));
		cierreConvexoReal20.add(list.get(20));
	}
	
	
	
	public void compruebaCierreConvexo(List<Punto> cierreConvexo) throws Exception
	{
		assertEquals(cierreConvexo.size(), cierreConvexoReal.size());
		for (Iterator iterator = cierreConvexoReal.iterator(); iterator.hasNext();) {
			Punto punto = (Punto) iterator.next();
			assertTrue(cierreConvexo.contains(punto));
		}
	}
	
	public void compruebaCierreConvexo20(List<Punto> cierreConvexo) throws Exception
	{
		assertEquals(cierreConvexoReal20.size(),cierreConvexo.size());
		for (Iterator iterator = cierreConvexo.iterator(); iterator.hasNext();) {
			Punto punto = (Punto) iterator.next();
			assertTrue(cierreConvexoReal20.contains(punto));
		}
	}

	protected void intoDataList(int x, int y, List<Punto> lista) {
		Punto punto = new Punto();
		punto.setX(x);
		punto.setY(y);
		lista.add(punto);
	}

	protected void compruebaCierreConvexoPorAristas(List<Arista> conjuntoConvexo) {
		// TODO Auto-generated method stub
		imprimeCierresConvexos(cierreConvexoReal, conjuntoConvexo);
		assertEquals(cierreConvexoReal.size(),conjuntoConvexo.size());
		Arista a1 = null;
		Arista a2 = null;
		Punto punto = null;
		Punto puntoSiguiente = null;
		for (int i = 0; i < cierreConvexoReal.size()-1; i++) {
			punto = cierreConvexoReal.get(i);
			puntoSiguiente = cierreConvexoReal.get(i +1);
			a1 = new Arista(punto, puntoSiguiente);
			a2 = new Arista(puntoSiguiente, punto);
			assertTrue(conjuntoConvexo.contains(a1) || conjuntoConvexo.contains(a2));			
		}
		a1 = new Arista(puntoSiguiente,cierreConvexoReal.get(0));
		a2 = new Arista(cierreConvexoReal.get(0),puntoSiguiente);
		assertTrue(conjuntoConvexo.contains(a1) || conjuntoConvexo.contains(a2));								
	}
	
	protected void compruebaCierreConvexoPorAristasGenerico(List<Arista> conjuntoConvexo, List<Punto> cierreConvexo) {
		// TODO Auto-generated method stub
		imprimeCierresConvexos(cierreConvexo, conjuntoConvexo);
		assertEquals(conjuntoConvexo.size(), cierreConvexo.size());
		Arista a1 = null;
		Arista a2 = null;
		Punto punto = null;
		Punto puntoSiguiente = null;
				
		for (int i = 0; i < cierreConvexo.size()-1; i++) {
			punto = cierreConvexo.get(i);
			puntoSiguiente = cierreConvexo.get(i +1);
			a1 = new Arista(punto, puntoSiguiente);
			a2 = new Arista(puntoSiguiente, punto);
			System.out.println(a1.toString() + "|" + a2.toString());
			assertTrue(conjuntoConvexo.contains(a1) || conjuntoConvexo.contains(a2));			
		}
		a1 = new Arista(puntoSiguiente,cierreConvexo.get(0));
		a2 = new Arista(cierreConvexo.get(0),puntoSiguiente);
		assertTrue(conjuntoConvexo.contains(a1) || conjuntoConvexo.contains(a2));								
	}
	
	protected void compruebaCierreConvexoPorAristasGenericoAprox(List<Arista> conjuntoConvexo, List<Punto> cierreConvexo) {
		Arista a1 = null;
		Arista a2 = null;
		Punto punto = null;
		Punto puntoSiguiente = null;
		imprimeCierresConvexos(cierreConvexo, conjuntoConvexo);
				
		for (int i = 0; i < cierreConvexo.size()-1; i++) {
			punto = cierreConvexo.get(i);
			puntoSiguiente = cierreConvexo.get(i +1);
			a1 = new Arista(punto, puntoSiguiente);
			a2 = new Arista(puntoSiguiente, punto);
			System.out.println(a1.toString() + "|" + a2.toString());
			assertTrue(conjuntoConvexo.contains(a1) || conjuntoConvexo.contains(a2));			
		}
		a1 = new Arista(puntoSiguiente,cierreConvexo.get(0));
		a2 = new Arista(cierreConvexo.get(0),puntoSiguiente);
		assertTrue(conjuntoConvexo.contains(a1) || conjuntoConvexo.contains(a2));								
	
	}

	protected void imprimeCierresConvexos(List<Punto> cierreConvexo, List<Arista> aristas) {
		// TODO Auto-generated method stub
		System.out.println("Cierre Real:" + cierreConvexo.toString());
		System.out.println("Aristas Obtenidas:" + aristas.toString());
		
	}
}
