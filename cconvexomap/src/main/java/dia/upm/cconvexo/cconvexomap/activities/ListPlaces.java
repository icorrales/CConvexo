package dia.upm.cconvexo.cconvexomap.activities;

import android.location.Address;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.GridView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.cconvexomap.R;
import dia.upm.cconvexo.cconvexomap.adapters.ListTypesAdapter;
import dia.upm.cconvexo.cconvexomap.adapters.PlacesAdapter;
import dia.upm.cconvexo.cconvexomap.gestores.GestorGeocoder;
import dia.upm.cconvexo.cconvexomap.gestores.GestorPlaces;
import dia.upm.cconvexo.cconvexomap.gestores.GestorPuntos;
import dia.upm.cconvexo.cconvexomap.listeners.PlacesListener;
import dia.upm.cconvexo.interfaces.model.Place;

public class ListPlaces extends ActionBarActivity implements PlacesListener {


    private PlacesAdapter adapter;
    ExpandableListView listTypes = null;
    private ListTypesAdapter adapterListWiew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listplaces);
        listTypes = (ExpandableListView) findViewById(R.id.eLW_listPlaces);
 //       adapterListWiew = new ListTypesAdapter(this);
        Address place = GestorPuntos.getInstancia().getMidPoint();
        List<Address> listaTipo = null;
        List<Place> places = null;
        adapter = new PlacesAdapter(this);
        try {
            listaTipo = GestorGeocoder.getInstancia().getFromLocationName("calle mayor",20);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            places = new LinkedList<Place>();

            GetPlaces tarea = new GetPlaces(GestorPuntos.getInstancia().getMidPoint(),this);
            tarea.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        List<Place> places = adapterListaToPlaces(listaTipo);
        adapter.setPlaces(places);
        if (place.getLocality() != null)
        {
            EditText et = (EditText) findViewById(R.id.eT_listPlaces);
            et.setText("Cerca de " + place.getLocality());
        }

        GridView gv = (GridView) findViewById(R.id.gV_listPlaces);
        gv.setAdapter(adapter);
        GestorPlaces.getInstancia().addListener(this);



    }


    private List<Place> adapterListaToPlaces(List<Address> listaTipo) {

        List<Place> places = new ArrayList<Place>();

        for (Iterator<Address> iterator = listaTipo.iterator(); iterator.hasNext(); ) {
            Address next =  iterator.next();
            Place newplace = new Place();
            newplace.addressToPlace(next);
            places.add(newplace);
        }
        return places;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.list_places, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void placeChanged(List<Place> places) {
        assert places != null;
        Log.d(ListPlaces.class.getName(),"PlaceChanged : " + places.toString());
        adapter.setPlaces(places);
        GridView gv = (GridView) findViewById(R.id.gV_listPlaces);
        gv.setAdapter(adapter);
    }
}
