package dia.upm.cconvexo.cconvexomap.gestores;

import android.location.Address;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ivan on 19/07/14.
 */
public class GestorPuntos {

    private static GestorPuntos instancia;
    List<Address> listaPuntos = null;

    private GestorPuntos() {
        // TODO Auto-generated constructor stub
        listaPuntos = new LinkedList<Address>();

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

    public void addLocation(Address location)
    {
        assert location != null;
        listaPuntos.add(location);


    }
}
