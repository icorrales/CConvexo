package dia.upm.cconvexo.cconvexomap.model;

import android.location.Address;

import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

import dia.upm.cconvexo.model.Punto;

/**
 * Created by ivan on 26/10/14.
 */
public class WrapperAddress {

    private Address location = null;
    private String name;


    private int weight;


    public WrapperAddress(Address lo1, int we1)
    {
        initWrapperAddress(lo1, we1);

    }

    private void initWrapperAddress(Address lo1, int we1) {
        assert  location != null;
        assert weight > 0;

        this.location = lo1;
        this.weight = we1;
        this.name = getName();
    }

    public WrapperAddress(Address location, String strWeight) {

        if (strWeight == null || strWeight.length() == 0)
        {
            initWrapperAddress(location,1);
        }
        else
        {
            int weightInt = Integer.parseInt(strWeight);
            initWrapperAddress(location,weightInt);
        }
    }

    public String getName() {

        String nameToShow = "";
        if (location.getThoroughfare() != null)
        {
            nameToShow = nameToShow + location.getThoroughfare();
        }

        if (location.getSubThoroughfare() != null)
        {
            nameToShow = nameToShow + " " + location.getSubThoroughfare();
        }

        if (location.getThoroughfare() != null || location.getSubThoroughfare() != null)
        {
            nameToShow = nameToShow + ",";
        }

        if (location.getLocality() != null)
        {
            nameToShow = nameToShow  + location.getLocality();
        }

        assert nameToShow != null;
        return  nameToShow;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public LatLng getLatLng() {
        assert location != null;
        LatLng latlng = new LatLng(location.getLatitude(),location.getLongitude());
        return latlng;
    }

    public static String nameToShow(Address address)
    {
        String nameToShow = "";
        if (address.getThoroughfare() != null)
        {
            nameToShow = nameToShow + address.getThoroughfare();
        }

        if (address.getSubThoroughfare() != null)
        {
            nameToShow = nameToShow + " " + address.getSubThoroughfare();
        }

        if (address.getThoroughfare() != null || address.getSubThoroughfare() != null)
        {
            nameToShow = nameToShow + ",";
        }

        if (address.getLocality() != null)
        {
            nameToShow = nameToShow  + address.getLocality();
        }

        assert nameToShow != null && nameToShow.isEmpty() == false;
        return  nameToShow;

    }

    public Punto getPunto()
    {
        Punto p = new Punto();
        p.setX(this.getLatLng().longitude);
        p.setY(this.getLatLng().latitude);
        return p;
    }
}
