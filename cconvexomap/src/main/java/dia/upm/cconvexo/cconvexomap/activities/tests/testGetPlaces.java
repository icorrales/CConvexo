package dia.upm.cconvexo.cconvexomap.activities.tests;

import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.test.InstrumentationTestCase;

import java.util.List;
import java.util.Locale;

import dia.upm.cconvexo.cconvexomap.adapters.JsonRequest;
import dia.upm.cconvexo.cconvexomap.gestores.GestorGeocoder;
import dia.upm.cconvexo.interfaces.model.Place;

/**
 * Created by ivan on 21/09b/14.
 */
public class testGetPlaces extends InstrumentationTestCase{

    public void test1 () throws Exception
    {
        Geocoder geocoder = new Geocoder(this.getInstrumentation().getContext(),Locale.ENGLISH);
        GestorGeocoder.setInstancia(geocoder);
        List<Address> listaDirecciones = GestorGeocoder.getInstancia().getFromLocationName("Madrid", 20);
        JsonRequest request = new JsonRequest();
        List<Place> listaPlaces = request.getListPlace(listaDirecciones.get(0));
        assertTrue(listaPlaces.size() > 0);
        assertTrue(listaPlaces.size() == 10);

    }

    // test 2 get this image https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photo_reference=CnRqAAAAZuAktuj_OUnR-dciuI6CZFeFYj3rooeP1leQN_GHxk4qWszS8cXFVeshlwMGD66TX5akvSNqpvMLZ2DpDS8jAPfoy8Q7rtO6mTwaX7zY_mmDqvis4TJpQvkxeAc6Q16rMDW_xdrLbk1URLtWPhSThBIQqfb3YUDfH67cVeZl4xJwtxoUvQE8-BRDOz18ZbX4sfxH0l08CNI&key=AIzaSyD4u_Xd7vRiQnyoDQEizAg2HAzHyyRA4Rs
    public void test2 () throws Exception
    {
        String key = "CnRqAAAAZuAktuj_OUnR-dciuI6CZFeFYj3rooeP1leQN_GHxk4qWszS8cXFVeshlwMGD66TX5akvSNqpvMLZ2DpDS8jAPfoy8Q7rtO6mTwaX7zY_mmDqvis4TJpQvkxeAc6Q16rMDW_xdrLbk1URLtWPhSThBIQqfb3YUDfH67cVeZl4xJwtxoUvQE8-BRDOz18ZbX4sfxH0l08CNI";
        JsonRequest request = new JsonRequest();
        Bitmap bm = request.getImage(key);
        assertNotNull(bm);
        assertEquals(bm.getWidth(),100);
    //    assertEquals(bm.getHeight(),37);
    }
}
