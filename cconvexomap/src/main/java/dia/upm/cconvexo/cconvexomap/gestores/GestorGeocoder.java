package dia.upm.cconvexo.cconvexomap.gestores;

import android.location.Geocoder;

import java.util.Locale;

/**
 * Created by ivan on 13/09/14.
 */
public class GestorGeocoder {

    private static Geocoder instancia = null;


    public static Geocoder getInstancia ()
    {
        if (instancia == null)
        {
            instancia = null;
        }
        assert instancia != null;
        return instancia;
    }

    public static void setInstancia( Geocoder geocoder)
    {
        instancia = geocoder;
    }
}
