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

    public PuntoLngLon(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PuntoLngLon)) return false;

        PuntoLngLon that = (PuntoLngLon) o;

        if (Double.compare(that.lat, lat) != 0) return false;
        if (Double.compare(that.lon, lon) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(lat);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lon);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
