package dia.upm.cconvexo.cconvexomap.midpoint.tests;

import android.location.Address;
import android.location.Geocoder;
import android.test.InstrumentationTestCase;

import com.google.android.gms.maps.model.LatLng;

import java.util.Locale;

import dia.upm.cconvexo.cconvexomap.R;
import dia.upm.cconvexo.cconvexomap.gestores.GestorGeocoder;
import dia.upm.cconvexo.cconvexomap.gestores.GestorPuntos;
import dia.upm.cconvexo.cconvexomap.midpoint.MidPoint;
import dia.upm.cconvexo.cconvexomap.model.WrapperAddress;
import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;

/**
 * Created by ivan on 1/11/14.
 */
public class testMidPoint extends InstrumentationTestCase {


    @Override
    protected void setUp() throws Exception
    {
        Geocoder geocoder = new Geocoder(this.getInstrumentation().getContext(), Locale.ENGLISH);
        GestorGeocoder.setInstancia(geocoder);
        Address ad1 = geocoder.getFromLocationName("Barcelona",1).get(0);
        Address ad2 = geocoder.getFromLocationName("Paris",1).get(0);
        Address ad3 = geocoder.getFromLocationName("Berlin",1).get(0);
        WrapperAddress wad1 = new WrapperAddress(ad1,1);
        WrapperAddress wad2 = new WrapperAddress(ad2,1);
        WrapperAddress wad3 = new WrapperAddress(ad3,1);
        GestorPuntos.getInstancia().addLocation(wad1);
        GestorPuntos.getInstancia().addLocation(wad2);
        GestorPuntos.getInstancia().addLocation(wad3);


    }

    @Override
    protected void tearDown() throws Exception
    {
        GestorPuntos.getInstancia().clear();
    }


    // Test con el calculo viejo.
    public void test1() throws Exception
    {
        MidPoint midPoint = new MidPoint();
        midPoint.setAlgorithm(R.id.rbt_midpoint);
        midPoint.calculateMidPoint(GestorPuntos.getInstancia().getLocations());
        Address adExpected = GestorGeocoder.getInstancia().getFromLocation(47.703084,5.614643,1).get(0);
        Address adReal = midPoint.getAddress(GestorGeocoder.getInstancia());
        assertEquals(adExpected.getLocality(),adReal.getLocality());

    }

    // Test con el calculo de midpoint actual
    public void test2() throws Exception
    {
        MidPoint midPoint = new MidPoint();
        midPoint.setAlgorithm(R.id.rbt_midpoint);
        midPoint.geographic_midpoint();
        Address adExpected = GestorGeocoder.getInstancia().getFromLocation(47.703084,5.614643,1).get(0);
        Address adReal = midPoint.getAddress(GestorGeocoder.getInstancia());
        assertEquals(adExpected.getLocality(),adReal.getLocality());
    }

    // Test con el calculo de avg lat/lon
    public void test3() throws Exception
    {
        MidPoint midPoint = new MidPoint();
        midPoint.setAlgorithm(R.id.rbt_latlgn);
        midPoint.avg_lat_lon();
        Address adExpected = GestorGeocoder.getInstancia().getFromLocation(47.587228, 5.97686, 1).get(0);
        Address adReal = midPoint.getAddress(GestorGeocoder.getInstancia());
        assertEquals(adExpected.getLocality(),adReal.getLocality());
//        assertEquals(new LatLng(47.587228,5.97686),midPoint.getLatLng());
    }

    // Test con el calculo de avg lat/lon
    public void test4() throws Exception
    {
        MidPoint midPoint = new MidPoint();
        midPoint.setAlgorithm(R.id.rbt_min_dist);
        midPoint.algorithm_min_distances();
        Address adExpected = GestorGeocoder.getInstancia().getFromLocation(48.831308,2.363208, 1).get(0);
        Address adReal = midPoint.getAddress(GestorGeocoder.getInstancia());
        assertEquals(adExpected.getLocality(),adReal.getLocality());
//        assertEquals(new LatLng(47.587228,5.97686),midPoint.getLatLng());
    }

}
