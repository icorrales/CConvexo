package dia.upm.cconvexo.cconvexomap.gestores;

import android.location.Address;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ivan on 19/07/14.
 */
public class GestorPuntos {

    private static GestorPuntos instancia;
    List<Address> listaPuntos = null;

    public double getMinx() {
        return minx;
    }

    public void setMinx(double minx) {
        this.minx = minx;
    }

    public double getMiny() {
        return miny;
    }

    public void setMiny(double miny) {
        this.miny = miny;
    }

    public double getMaxx() {
        return maxx;
    }

    public void setMaxx(double maxx) {
        this.maxx = maxx;
    }

    public double getMaxy() {
        return maxy;
    }

    public void setMaxy(double maxy) {
        this.maxy = maxy;
    }

    double minx, miny, maxx,maxy;

    public LatLngBounds getBounds()
    {
        LatLng southwest = new LatLng(minx,miny);
        LatLng northeast = new LatLng(maxx,maxy);
        LatLngBounds bounds = new LatLngBounds(southwest,northeast);
        return  bounds;
    }

    public Address getMidPoint() {
        return midPoint;
    }

    public void setMidPoint(Address midPoint) {
        this.midPoint = midPoint;
    }

    private Address midPoint = null;


    private GestorPuntos() {
        // TODO Auto-generated constructor stub
        listaPuntos = new LinkedList<Address>();
        initBounds();

    }

    private void initBounds() {
        minx= 0;
        miny =0;
        maxx = 0;
        maxy = 0;
        this.midPoint = null;
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
        reviewBounds(location);


    }

    private void reviewBounds(Address location) {
        if (location.getLatitude() < minx ) { minx = location.getLatitude();}
        if (location.getLatitude() > maxx ) { maxx = location.getLatitude(); }
        if (location.getLongitude() < miny) { miny = location.getLongitude(); }
        if ( location.getLongitude() > maxy ) { maxy = location.getLongitude(); }
    }

    public List getLocations() {
        return listaPuntos;
    }

    public void clear() {
        this.listaPuntos.clear();
        initBounds();
    }
}
