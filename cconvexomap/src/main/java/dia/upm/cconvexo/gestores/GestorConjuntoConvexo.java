package dia.upm.cconvexo.gestores;




//import java.awt.Color;
import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;



import dia.upm.cconvexo.global.Triangulo;
import dia.upm.cconvexo.interfaces.IDelegatePaint;
import dia.upm.cconvexo.model.Arista;
import dia.upm.cconvexo.model.Circle;
import dia.upm.cconvexo.model.Punto;

public class GestorConjuntoConvexo {



    private Punto selected = null;
    private Circle circleTmp = null;
    private Circle MEC = null;

    public List<Arista> getSubconjuntoArista() {
        return subconjuntoArista;
    }

    public void setSubconjuntoArista(List<Arista> subconjuntoArista) {
        this.subconjuntoArista = subconjuntoArista;
    }

    public void borraSubconjuntoArista()
    {
        borraSubconjuntoArista(false);
    }

    public void borraSubconjuntoArista(boolean refresh)
    {
        this.subconjuntoArista.clear();
        for (int i = 0; i < listaListener.size(); i++) {

            if ( refresh)
            {
            IDelegatePaint delegate = listaListener.get(i);
            delegate.paintPuntos();
            }
        }

    }

    static GestorConjuntoConvexo instancia = null;
	private List<Punto> listaPuntos = null;
	private List<Arista> conjuntoConvexo = null;
	private List<Punto> subconjuntoPuntos = null;
    private List<Arista>subconjuntoArista = null;
	private List<IDelegatePaint> listaListener = null;
    private List<Punto> puntosGeograficos = null;

    public boolean isOrdenaAngularmente() {
        return ordenaAngularmente;
    }

    public void setOrdenaAngularmente(boolean ordenaAngularmente) {
        this.ordenaAngularmente = ordenaAngularmente;
    }

    private boolean ordenaAngularmente = false;
	
	
	private GestorConjuntoConvexo() {
		// TODO Auto-generated constructor stub
		listaListener = new LinkedList<IDelegatePaint>();
		conjuntoConvexo = new LinkedList<Arista>();
        listaPuntos = new LinkedList<Punto>();
        subconjuntoArista = new LinkedList<Arista>();
        puntosGeograficos = new LinkedList<Punto>();
        subconjuntoPuntos = new LinkedList<Punto>();
		
	}
	
	public static GestorConjuntoConvexo getInstancia ()
	{
		if (instancia == null)
		{
			instancia = new GestorConjuntoConvexo();			
		}
		assert instancia != null;
		return instancia;
	}

	public List<Punto> getListaPuntos() {
		return listaPuntos;
	}

	public void setListaPuntos(List<Punto> listaPuntos) {
		assert listaPuntos != null;
		assert listaListener != null;
		this.listaPuntos = listaPuntos;
        initGestor();
		for (Iterator<IDelegatePaint> iterator = listaListener.iterator(); iterator.hasNext();) {
			IDelegatePaint delegate = iterator.next();
			delegate.paintPuntos();
			
		}
		
	}

    public void initGestor() {
        this.setSelected(null);
        initCC();
    }

    public void initCC() {
        this.conjuntoConvexo.clear();
        this.subconjuntoArista.clear();
        this.puntosGeograficos.clear();
        this.ordenaAngularmente = false;
        this.subconjuntoPuntos.clear();
        this.circleTmp = null;
        this.MEC = null;

    }

    public void borraListaPuntos()
	{
		listaPuntos = new LinkedList<Punto>();
		conjuntoConvexo.clear();
        for (IDelegatePaint delegate : listaListener) {
            delegate.borraPuntos();
            duerme();
        }
	}
	
	public void anadeArista(Arista a1)
	{
		assert conjuntoConvexo != null;
		assert a1 != null;
		assert a1.getDestino() != null && a1.getOrigen() != null;
		if ( ! conjuntoConvexo.contains(a1))
		{
			conjuntoConvexo.add(a1);
            for (IDelegatePaint delegate : listaListener) {
//                delegate.mensajeDescripcion(R.string.textAddArista + a1.toString());
                delegate.paintArista(a1);

            }
		}
	}
	
	
	public void borraArista(Arista a1)
	{
		assert conjuntoConvexo != null;
		assert a1 != null;
		assert a1.getDestino() != null && a1.getOrigen() != null;				
//		assert conjuntoConvexo.contains(a1);
		if (conjuntoConvexo.contains(a1))
		{	
		conjuntoConvexo.remove(a1);
		for (Iterator<IDelegatePaint> iterator = listaListener.iterator(); iterator.hasNext();) {
			IDelegatePaint delegate = iterator.next();
//            delegate.mensajeDescripcion(R.string.textDeleteArista + a1.toString());
			delegate.borraRecta(a1);
		}
		}
	}
	
	public void addListener (IDelegatePaint delegate) 
	{
		assert delegate != null;
		listaListener.add(delegate);
	}

	public void removeListener(IDelegatePaint delegate)
	{
		assert delegate != null;
		listaListener.remove(delegate);
	}

	public List<Arista> getConjuntoConvexo() {
		// TODO Auto-generated method stub
		return this.conjuntoConvexo;
	}
	
	public void setSubconjuntoPuntos(List<Punto> lista)
	{
		assert lista != null;
		subconjuntoPuntos = lista;
        for (Iterator<IDelegatePaint> iterator = listaListener.iterator(); iterator.hasNext();) {
            IDelegatePaint delegate = iterator.next();
            delegate.paintPuntos();
        }
	}
	
	public void borrarPuntoSubconjunto(Punto p)
	{
		assert p != null;
		for (Iterator<IDelegatePaint> iterator = listaListener.iterator(); iterator.hasNext();) {
			IDelegatePaint delegate = iterator.next();
			delegate.borraPuntoSubconjunto(p);

		}
	}

	public void anadePuntoGrafico(Punto puntoInterior) {
		assert puntoInterior != null;
		
		for (Iterator<IDelegatePaint> iterator = listaListener.iterator(); iterator.hasNext();) {
			IDelegatePaint delegate = iterator.next();
			delegate.paintPuntos();

		}
		
	}

	public void pintaCierreConvexo() {
		// TODO Auto-generated method stub
		for (Iterator<Arista> iterator = conjuntoConvexo.iterator(); iterator.hasNext();) {
			Arista a1 = iterator.next();

			for (int i = 0; i < listaListener.size(); i++) {
				
			
				IDelegatePaint delegate = listaListener.get(i);
				  delegate.paintArista(a1, Color.RED);
			}
		}
	}

    public void anadeAristaTmp(Arista a1)
    {
        subconjuntoArista.add(a1);
        for (int i = 0; i < listaListener.size(); i++) {


            IDelegatePaint delegate = listaListener.get(i);
            delegate.paintArista(a1);
        }
    }

    public void borraAristaTmp(Arista a1)
    {
        borraAristaTmp(a1,false);
    }

    public void borraAristaTmp(Arista a1,boolean refresh)
    {
        subconjuntoArista.remove(a1);
        if (refresh)
        {
            for (int i = 0; i < listaListener.size(); i++) {
                IDelegatePaint delegate = listaListener.get(i);
                delegate.paintArista(a1);
            }
        }
    }


    private void duerme() {

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Punto> getPuntosGeograficos() {
        return puntosGeograficos;
    }

    public List<Punto> getSubconjuntoPuntos() {
        return subconjuntoPuntos;
    }

    public void anadaPuntoSubconjunto(Punto punto) {
        assert punto != null;
        assert subconjuntoPuntos != null;
        assert ! subconjuntoPuntos.contains(punto);
        subconjuntoPuntos.add(punto);
        for (Iterator<IDelegatePaint> iterator = listaListener.iterator(); iterator.hasNext();) {
            IDelegatePaint delegate = iterator.next();
            delegate.paintPuntos();

        }
    }

    public void borraPuntoSubconjunto(Punto punto) {
        assert punto != null;
        assert subconjuntoPuntos != null;
        subconjuntoPuntos.remove(punto);
        for (Iterator<IDelegatePaint> iterator = listaListener.iterator(); iterator.hasNext();) {
            IDelegatePaint delegate = iterator.next();
//            delegate.paintPuntos();

        }
    }

    public void borraSubconjuntoPuntos() {
        this.subconjuntoPuntos.clear();

    }


    public void anadeTrianguloTmp(Triangulo t1) {
        Arista a1 = new Arista(t1.getPunto1(),t1.getPunto2());
        if ( ! subconjuntoArista.contains(a1)) {
            subconjuntoArista.add(a1);
        }
        Arista a2 = new Arista(t1.getPunto2(),t1.getPunto3());
        if ( ! subconjuntoArista.contains(a2)) {
            subconjuntoArista.add(a2);
        }
        Arista a3 = new Arista(t1.getPunto1(),t1.getPunto3());
        if ( ! subconjuntoArista.contains(a3)) {
            subconjuntoArista.add(a3);
        }

        for (int i = 0; i < listaListener.size(); i++) {


            IDelegatePaint delegate = listaListener.get(i);
            delegate.paintArista(a1);
        }
    }

    public void borraTrianguloTmp(Triangulo t1) {
        Arista a1 = new Arista(t1.getPunto1(),t1.getPunto2());
        subconjuntoArista.remove(a1);
        subconjuntoArista.remove(new Arista(t1.getPunto2(), t1.getPunto3()));
        subconjuntoArista.remove(new Arista(t1.getPunto1(), t1.getPunto3()));
        for (int i = 0; i < listaListener.size(); i++) {


            IDelegatePaint delegate = listaListener.get(i);
//            delegate.borraRecta(a1);
        }
    }

    public void borraArista(int indice_v_sop_inf) {
        Log.d(GestorConjuntoConvexo.class.getName() + " borraArista ", "Inicio borraArista: " + indice_v_sop_inf  );
        Arista arista = getConjuntoConvexo().get(indice_v_sop_inf);
        Log.d(GestorConjuntoConvexo.class.getName() + " borraArista ", "Arista: " + arista.toString() );
        Log.d(GestorConjuntoConvexo.class.getName() + " borraArista ", "CH antes: " + getConjuntoConvexo().toString());
        borraArista(arista);
        Log.d(GestorConjuntoConvexo.class.getName() + " borraArista ", "CH despues: " + getConjuntoConvexo().toString());

    }

    public void actualizaCierre(List<Punto> c_convexo) {
        assert c_convexo != null;
        if (c_convexo.size() > 1)
        {
            this.borraSubconjuntoArista();
            this.subconjuntoPuntos.clear();
            this.getConjuntoConvexo().clear();

            for (int i = 0; i +1 < c_convexo.size(); i++) {
                Punto p1 = c_convexo.get(i);
                Punto p2 = c_convexo.get(i+1);
                this.getConjuntoConvexo().add(new Arista(p1,p2));
            }
            this.getConjuntoConvexo().add(new Arista(c_convexo.get(c_convexo.size()-1),c_convexo.get(0)));
        }
        for (int i = 0; i < listaListener.size(); i++) {


            IDelegatePaint delegate = listaListener.get(i);
            delegate.paintPuntos();
        }


    }


    /**
     * Method isClose : test if one point is near to some other point that is in the point list.
     * @param p Punto to test
     * @return true if the punto p is near of one point that is in the list point.
     */
    public boolean isClose(Punto p) {
        boolean isClose = false;

        for (Punto p1 : listaPuntos) {
            if (p1.isClose(p))
            {
                isClose = true;
                selected = p1;
            }

        }
        return  isClose;


    }

    public Punto getSelected() {
        return selected;
    }

    public void setSelected(Punto selected) {
        this.selected = selected;
    }

    public List<Punto> getConjuntoConvexoPuntos() {

        List<Punto> conjuntoConvexoPuntos = new LinkedList<Punto>();
        for (Iterator<Arista> iterator = conjuntoConvexo.iterator(); iterator.hasNext(); ) {
            Arista next = iterator.next();
            if (conjuntoConvexoPuntos.contains(next.getOrigen()) == false)
            {
                conjuntoConvexoPuntos.add(next.getOrigen());
            }

            if (conjuntoConvexoPuntos.contains(next.getDestino()) == false)
            {
                conjuntoConvexoPuntos.add(next.getDestino());
            }

        }

        return conjuntoConvexoPuntos;
    }


    public void setCircleTmp(Circle circle) {
        circleTmp = circle;
        for (int i = 0; i < listaListener.size(); i++) {


            IDelegatePaint delegate = listaListener.get(i);
            delegate.paintPuntos();
        }

    }

    public void setMEC(Circle circle) {
        MEC = circle;
        for (int i = 0; i < listaListener.size(); i++) {


            IDelegatePaint delegate = listaListener.get(i);
            delegate.paintPuntos();
        }

    }

    public Circle getCircleTmp() {
        return circleTmp;
    }

    public Circle getMEC() {
        return MEC;
    }


    public void borraPunto(Punto selected) {
        assert selected != null;
        listaPuntos.remove(selected);
    }

    public Circle getMECinGMaps() {

        assert this.getConjuntoConvexoPuntos().size() >=1 ;
        assert this.MEC != null;
        List<Punto> cconvexoPuntos= getConjuntoConvexoPuntos();
        for (int i = 0; i < cconvexoPuntos.size(); i++) {
            Punto p1 = cconvexoPuntos.get(i);
            double distanceP1 = MEC.toRadiusMeters(MEC.centro.latLng,p1.latLng);
            if ( distanceP1 > MEC.radius)
            {
                MEC.radius = distanceP1;
            }

        }
        assert MEC.radius >=0;
        return MEC;


    }
}
