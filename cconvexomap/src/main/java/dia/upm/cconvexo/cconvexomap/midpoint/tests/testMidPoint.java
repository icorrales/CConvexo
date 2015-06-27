package dia.upm.cconvexo.cconvexomap.midpoint.tests;

import android.location.Address;
import android.location.Geocoder;
import android.test.InstrumentationTestCase;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
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
        initPuntosLargos(geocoder);


    }

    private void initPuntosLargos(Geocoder geocoder) throws Exception {

        addLocationGestorPuntos("Barcelona");
        addLocationGestorPuntos("Paris");
        addLocationGestorPuntos("Berlin");

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

    // Test con el calculo de min distances
    public void test4() throws Exception
    {
        addOtrasCiudades();
        MidPoint midPoint = new MidPoint();

        midPoint.algorithm_min_distances();
        Address adExpected = GestorGeocoder.getInstancia().getFromLocation(48.831308,2.363208, 1).get(0);
        Address adReal = midPoint.getAddress(GestorGeocoder.getInstancia());
        assertEquals(adExpected.getLocality(),adReal.getLocality());
//        assertEquals(new LatLng(47.587228,5.97686),midPoint.getLatLng());
    }

    // Test para el calculo de min distance de 5 puntos ( Barcelona, Paris, Londres,Hamburgo y Roma)
    public void test5() throws Exception
    {
        addOtrasCiudades();
        MidPoint midPoint = new MidPoint();

        midPoint.algorithm_min_distances_red();
        Address adReal = midPoint.getAddress(GestorGeocoder.getInstancia());
        Address adExpected = GestorGeocoder.getInstancia().getFromLocationName("Paris", 1).get(0);
        assertEquals(adExpected.getLocality(),adReal.getLocality());
    }

    // Test para el calculo de min distance de 5 puntos ( Barcelona, Paris, Londres,Hamburgo y Roma)
    public void test6() throws Exception
    {
        addOtrasCiudades();
        MidPoint midPoint = new MidPoint();

        midPoint.algorithm_min_distances_red_googlemaps();
        Address adReal = midPoint.getAddress(GestorGeocoder.getInstancia());
        Address adExpected = GestorGeocoder.getInstancia().getFromLocationName("Paris", 1).get(0);
        assertEquals(adExpected.getLocality(),adReal.getLocality());
    }

    private void addOtrasCiudades() throws  Exception
    {
        addLocationGestorPuntos("Roma");
        addLocationGestorPuntos("Londres");
    }

    private void addLocationGestorPuntos(String name) throws Exception{
        Address ad = GestorGeocoder.getInstancia().getFromLocationName(name,1).get(0);
        WrapperAddress wad = new WrapperAddress(ad,1);
        GestorPuntos.getInstancia().addLocation(wad);
    }

}
