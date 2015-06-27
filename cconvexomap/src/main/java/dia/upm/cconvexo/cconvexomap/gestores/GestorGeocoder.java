package dia.upm.cconvexo.cconvexomap.gestores;

import android.location.Geocoder;
import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

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

    public static double toRadiusMeters(LatLng center, LatLng radius) {
        float[] result = new float[1];
        Location.distanceBetween(center.latitude, center.longitude,
                radius.latitude, radius.longitude, result);
        return result[0];
    }
}
