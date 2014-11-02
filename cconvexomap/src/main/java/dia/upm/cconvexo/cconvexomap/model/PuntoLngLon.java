package dia.upm.cconvexo.cconvexomap.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ivan on 2/11/14.
 */
public class PuntoLngLon {

    double lat;
    double lon;

    public PuntoLngLon()
    {
        lat = 0;
        lon = 0;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public LatLng getLatLng()
    {
        LatLng latlon = new LatLng(lat,lon);
        return latlon;
    }
}
