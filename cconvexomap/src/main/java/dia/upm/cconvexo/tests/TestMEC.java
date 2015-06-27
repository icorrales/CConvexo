package dia.upm.cconvexo.tests;

import android.location.Address;
import android.location.Geocoder;
import android.test.InstrumentationTestCase;

import com.google.android.gms.maps.model.LatLng;

import junit.framework.TestCase;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import dia.upm.cconvexo.algoritmos.GrahamNuevo;
import dia.upm.cconvexo.algoritmos.Jarvis;
import dia.upm.cconvexo.algoritmos.MEC;
import dia.upm.cconvexo.cconvexomap.gestores.GestorGeocoder;
import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.model.Arista;
import dia.upm.cconvexo.model.Circle;
import dia.upm.cconvexo.model.Punto;

/**
 * Created by ivan on 3/04/15.
 */
public class TestMEC extends InstrumentationTestCase {

    Punto Madrid = new Punto(40.4167754,-3.7037902);
    Punto Paris = new Punto(48.856614,2.3522219);
    Punto Berlin = new Punto(52.5200066,13.404954);
    private List<Punto> list;


    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();

        list = new LinkedList<Punto>();

    }

    private void initPuntos() {
        list.add(Madrid);
        list.add(Paris);
        list.add(Berlin);
    }

    /* (non-Javadoc)
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {
        list = null;
        super.tearDown();
    }

    public void test1_FindMEC3Points() throws Exception
    {
        initPuntos();
        getCH();
        assertEquals(3,GestorConjuntoConvexo.getInstancia().getConjuntoConvexo().size());

    }

    private void getCH() {

        GestorConjuntoConvexo.getInstancia().setListaPuntos(list);
        Jarvis algoritmo = new Jarvis();
        algoritmo.start(0);
    }

    public void test2_FindMEC() throws Exception
    {
        initPuntos();
        getCH();
        MEC mec = new MEC();
        mec.init();
        mec.start(0);
        Circle circle = GestorConjuntoConvexo.getInstancia().getMEC();
        assertNotNull(circle);
        assertNotNull(circle.toRadiusMeters());
    }

    public void test3_3PointsNear() throws Exception
    {
        list.add(getPuntoMap("Leganes"));
        list.add(getPuntoMap("Getafe"));
        list.add(getPuntoMap("Mostoles"));
        getCH();
        List<Arista> cc = GestorConjuntoConvexo.getInstancia().getConjuntoConvexo();
        assertEquals(3, cc.size());
        MEC mec = new MEC();
        mec.init();
        mec.start(0);
        Circle circle = GestorConjuntoConvexo.getInstancia().getMEC();
        assertNotNull(circle);
        assertTrue(circle.toRadiusMeters() > 2000);

    }

    public void test4_Mec2Points() throws Exception
    {
        list.add(getPuntoMap("Leganes"));
        list.add(getPuntoMap("Getafe"));
        getCH();
        List<Arista> cc = GestorConjuntoConvexo.getInstancia().getConjuntoConvexo();
        assertEquals(2, cc.size());
        MEC mec = new MEC();
        mec.init();
        mec.start(0);
        Circle circle = GestorConjuntoConvexo.getInstancia().getMECinGMaps();
        assertNotNull(circle);
        assertTrue(circle.radius > 2000);

    }

    private Punto getPuntoMap(String name) throws Exception
    {
        assert name != null;
        Geocoder geocoder = new Geocoder(this.getInstrumentation().getContext(), Locale.ENGLISH);
        GestorGeocoder.setInstancia(geocoder);
        Address ad1 = geocoder.getFromLocationName(name,1).get(0);
        assert ad1 != null;
        Punto p = new Punto(ad1.getLongitude(),ad1.getLatitude());
        return p;

    } 

}
