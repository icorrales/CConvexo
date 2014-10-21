package dia.upm.cconvexo.android.gestores;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.interfaces.IDelegatePaint;
import dia.upm.cconvexo.model.Punto;

/**
 * Created by ivan on 17/09/13.
 */
public class GestorPuntos {

    static GestorPuntos instancia = null;
    private List<Punto> listaPuntos = null;
    private List<Punto> conjuntoConvexo = null;
    private List<Punto> subconjuntoPuntos = null;
    private List<IDelegatePaint> listaListener = null;


    private GestorPuntos() {
        // TODO Auto-generated constructor stub
        listaListener = new LinkedList<IDelegatePaint>();
        conjuntoConvexo = new LinkedList<Punto>();

    }

    public static GestorPuntos getInstancia ()
    {
        if (instancia == null)
        {
            instancia = new GestorPuntos();
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
        for (Iterator<IDelegatePaint> iterator = listaListener.iterator(); iterator.hasNext();) {
            IDelegatePaint delegate = iterator.next();
            delegate.paintPuntos();

        }

    }




}
