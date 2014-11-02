package dia.upm.cconvexo.cconvexomap.gestores;

import android.location.Address;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dia.upm.cconvexo.cconvexomap.model.WrapperAddress;
import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;

/**
 * Created by ivan on 19/07/14.
 */
public class GestorPuntos {

    private static GestorPuntos instancia;
    List<WrapperAddress> listaPuntos = null;
    Map<String,WrapperAddress> mapAddress = null;
    private WrapperAddress selected;

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
        LatLng southwest = new LatLng(miny,minx);
        LatLng northeast = new LatLng(maxy,maxx);
        LatLngBounds bounds = new LatLngBounds(southwest,northeast);
        return  bounds;
    }

    public WrapperAddress getMidPoint() {
        return midPoint;
    }

    public void setMidPoint(WrapperAddress midPoint) {
        this.midPoint = midPoint;
    }

    private WrapperAddress midPoint = null;


    private GestorPuntos() {
        // TODO Auto-generated constructor stub
        listaPuntos = new LinkedList<WrapperAddress>();
        mapAddress = new HashMap<String, WrapperAddress>();
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

    public void addLocation(WrapperAddress location)
    {
        assert location != null;
        mapAddress.put(location.getName(),location);
        listaPuntos.add(location);

        if (listaPuntos.size() == 1)
        {
            minx = location.getLocation().getLongitude();
            maxx = location.getLocation().getLongitude();
            miny = location.getLocation().getLatitude();
            maxy = location.getLocation().getLatitude();
        }
        else
        {
            reviewBounds(location);
        }


    }





    private void reviewBounds(WrapperAddress wlocation) {

        assert wlocation != null && wlocation.getLocation() != null;



        Address location = wlocation.getLocation();
        if (location.getLatitude() < miny ) { miny = location.getLatitude();}
        if (location.getLatitude() > maxy ) { maxy = location.getLatitude(); }
        if (location.getLongitude() < minx) { minx = location.getLongitude(); }
        if ( location.getLongitude() > maxx ) { maxx = location.getLongitude(); }
    }

    public List getLocations() {
        return listaPuntos;
    }

    public void clear() {
        this.mapAddress.clear();
        listaPuntos.clear();
        initBounds();
    }

    public boolean setPointSelected(Marker location) {
        assert location != null && location.getTitle() != null;
        boolean isFound = mapAddress.containsKey(location.getTitle());
        if ( mapAddress.containsKey(location.getTitle()))
        {
            selected = mapAddress.get(location.getTitle());
        }
        return isFound;
    }

    public void deleteSelected()
    {
        selected = null;
    }

    public void deletePointSelected()
    {
        listaPuntos.remove(selected);
        mapAddress.remove(selected.getName());
        deleteSelected();
    }

    public boolean isSelected() {
        return selected != null;
    }

    public WrapperAddress getSelected() {
        return selected;
    }
}
