package dia.upm.cconvexo.cconvexomap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import dia.upm.cconvexo.algoritmos.Jarvis;
import dia.upm.cconvexo.cconvexomap.activities.HelpWebViewActivity;
import dia.upm.cconvexo.cconvexomap.activities.ListPlaces;
import dia.upm.cconvexo.cconvexomap.adapters.PlaceAutoCompleterAdapter;
import dia.upm.cconvexo.cconvexomap.gestores.GestorGeocoder;
import dia.upm.cconvexo.cconvexomap.gestores.GestorPuntos;
import dia.upm.cconvexo.cconvexomap.midpoint.MidPoint;
import dia.upm.cconvexo.cconvexomap.model.WrapperAddress;
import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.model.Arista;
import dia.upm.cconvexo.model.Punto;

public class CConvexoMap extends FragmentActivity implements GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {

    GoogleMap mMap;
    Geocoder geocoder;
    MidPoint midPoint ;
    private Intent placeIntent;
    private ImageButton deleteButton;
    private EditText weightText;
    final Context context = this;
    private ImageButton mecButton;


    public Geocoder getGeocoder() {
        return geocoder;
    }

    public void setGeocoder(Geocoder geocoder) {
        this.geocoder = geocoder;
    }

    public GoogleMap getmMap() {
        return mMap;
    }

    public void setmMap(GoogleMap mMap) {
        this.mMap = mMap;
    }

    public List<Address> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Address> locationList) {
        this.locationList = locationList;
    }

    public List<String> getLocationNameList() {
        return locationNameList;
    }

    public void setLocationNameList(List<String> locationNameList) {
        this.locationNameList = locationNameList;
    }

    final static int maxResults = 5;
    List<Address> locationList;
    List<String> locationNameList;

    /// Elementos del panel
    ImageButton convexhullButton;
    ImageButton addButton;
    AutoCompleteTextView citiText;
    private ImageButton placeButton;
    RadioButton rbt_1;

    private View.OnClickListener deleteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (GestorPuntos.getInstancia().isSelected() == false)
            {
                GestorPuntos.getInstancia().clear();
                GestorConjuntoConvexo.getInstancia().initGestor();
                GestorConjuntoConvexo.getInstancia().borraListaPuntos();
                convexhullButton.setEnabled(false);
                enableButtons(false);
                drawMarkers();
            }
            else
            {

                Punto p = GestorPuntos.getInstancia().getSelected().getPunto();
                GestorConjuntoConvexo.getInstancia().borraPunto(p);
                GestorPuntos.getInstancia().deletePointSelected();

                if (GestorPuntos.getInstancia().getLocations().isEmpty())
                {
                    enableButtons(false);

                }

                try {
                    repaintMap();
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(CConvexoMap.class.getName(),"DeletePoint-Error al repintar el mapa");
                }
            }
        }

    };

    private void enableButtons(boolean active) {
        convexhullButton.setEnabled(active);
        placeButton.setEnabled(active);
        deleteButton.setEnabled(active);
        mecButton.setEnabled(active);
    }


    private View.OnClickListener convexHulAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            convexHullAction();

        }
    };



    private void convexHullAction() {
        GestorConjuntoConvexo.getInstancia().initGestor();
        Jarvis algorithm = new Jarvis();

        algorithm.start(10);
        drawMarkers();
        drawConvexHull();
    }

    private View.OnClickListener placesAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Address place = GestorPuntos.getInstancia().getMidPoint().getLocation();
            if ( place == null )
            {

            }
            else
            {
                placeIntent.putExtra("Address",GestorPuntos.getInstancia().getMidPoint().getLocation());
                startActivity(placeIntent);

            }
        }
    };

    private void drawConvexHull() {
        PolylineOptions polyline = new PolylineOptions().width(5).color(Color.RED);

        for (Iterator<Arista> iterator = GestorConjuntoConvexo.getInstancia().getConjuntoConvexo().iterator(); iterator.hasNext(); ) {
            Arista next = iterator.next();
            LatLng puntoLatLng = new LatLng(next.getOrigen().getX(),next.getOrigen().getY());

            mMap.addPolyline(polyline.add(puntoLatLng));
            puntoLatLng = new LatLng(next.getDestino().getX(),next.getDestino().getY());
            mMap.addPolyline(polyline.add(puntoLatLng));
        }

    }

    private void drawMarkers() {
        mMap.clear();
        List<WrapperAddress> listPuntos = GestorPuntos.getInstancia().getLocations();
        for (Iterator<WrapperAddress> iterator = listPuntos.iterator(); iterator.hasNext(); ) {
            WrapperAddress next = iterator.next();
            drawMarker(next,false, BitmapDescriptorFactory.HUE_AZURE);
        }
        if (GestorPuntos.getInstancia().getMidPoint() != null)
        {
            drawMarker(GestorPuntos.getInstancia().getMidPoint(),false,BitmapDescriptorFactory.HUE_ORANGE);

        }

        if (listPuntos.size() > 0)
        {
            changeCamera(CameraUpdateFactory.newLatLngBounds(GestorPuntos.getInstancia().getBounds(),100),null);
        }

    }

    private View.OnClickListener searchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String locationName = citiText.getText().toString();
            String strWeight = weightText.getText().toString();
            if(locationName == null){
                Toast.makeText(getApplicationContext(),
                        "locationName == null",
                        Toast.LENGTH_LONG).show();
            }else{
                    try {
                        locationList = geocoder.getFromLocationName(locationName, maxResults);


                    if(locationList == null){
                        Toast.makeText(getApplicationContext(),
                                "locationList == null",
                                Toast.LENGTH_LONG).show();
                    }else{
                        if(locationList.isEmpty()){
                            Toast.makeText(getApplicationContext(),
                                    "locationList is empty",
                                    Toast.LENGTH_LONG).show();

                        }else{
                            Address location = locationList.get(0);
                            WrapperAddress wlocation = new WrapperAddress(location,strWeight);
                            GestorPuntos.getInstancia().addLocation(wlocation);
                            Punto punto = wlocation.getPunto();
                            GestorConjuntoConvexo.getInstancia().getListaPuntos().add(punto);
                            if (GestorPuntos.getInstancia().getLocations().size() == 1)
                            {
                                enableButtons(true);
                            }
                            repaintMap();
                        }
                    }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            }
            return;
        }


    };

    private void repaintMap() throws Exception {
        midPoint.calc_midpoint();
        Address midPointLocation = midPoint.getAddress(getGeocoder());
        WrapperAddress wmidPoint = new WrapperAddress(midPointLocation,1);
        GestorPuntos.getInstancia().setMidPoint(wmidPoint);
        drawMarkers();
    }

    public void changeAlgorithm(View view)
    {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.rbt_midpoint:
                if (checked)
                {
                    midPoint.setAlgorithm(view.getId());
                }
                break;
            case R.id.rbt_min_dist:
                if (checked)
                {
                    midPoint.setAlgorithm(view.getId());
                }
                break;
            case R.id.rbt_latlgn:
                if (checked)
                {
                    midPoint.setAlgorithm(view.getId());
                }
                break;
        }
        try {
            repaintMap();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * Change the camera position by moving or animating the camera depending on the state of the
     * animate toggle button.
     */
    private void changeCamera(CameraUpdate update, GoogleMap.CancelableCallback callback) {

        mMap.animateCamera(update, 3000, callback);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cconvexo_map);
        geocoder = new Geocoder(getApplicationContext(), Locale.ENGLISH);
        GestorGeocoder.setInstancia(geocoder);
        citiText = (AutoCompleteTextView) findViewById(R.id.editText);
        citiText.setThreshold(3);
        citiText.setAdapter(new PlaceAutoCompleterAdapter(this, R.layout.list_item, this));
        citiText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = (String) parent.getItemAtPosition(position);

            }
        });

        weightText= (EditText) findViewById(R.id.weightText);
        convexhullButton = (ImageButton) findViewById(R.id.searchButton);
        convexhullButton.setOnClickListener(convexHulAction);
        addButton = (ImageButton) findViewById(R.id.addButton);
        addButton.setOnClickListener(searchListener);
        placeButton = (ImageButton) findViewById(R.id.placeButton);
        placeButton.setOnClickListener(placesAction);
        placeIntent = new Intent(this, ListPlaces.class);
        deleteButton = (ImageButton) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(deleteListener);
        mecButton = ( ImageButton) findViewById(R.id.mecButton);
        rbt_1 = (RadioButton) findViewById(R.id.rbt_midpoint);
        rbt_1.setChecked(true);


        enableButtons(false);


        midPoint = new MidPoint();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cconvexo_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_help:
                showHelp();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link com.google.android.gms.maps.SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }

    }


    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapClickListener(this);

    }


    private void drawMarker(WrapperAddress wlocation, boolean b, float colorIcon)
    {
        assert wlocation != null;

        LatLng latLng = wlocation.getLatLng();

        String nameToShow = wlocation.getName();

        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(nameToShow)
                .icon(BitmapDescriptorFactory.defaultMarker(colorIcon))).showInfoWindow();
  /*      if ( b)
        {
            CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng)
                .zoom(10f)
                .bearing(0)
                .tilt(25)
                .build();
            changeCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),null);
        }
        */
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        assert marker != null;
//        Toast.makeText(getApplicationContext(),"Marker Selected",Toast.LENGTH_LONG).show();
        boolean isFound = GestorPuntos.getInstancia().setPointSelected(marker);
        if (isFound == false)
        {
            Toast.makeText(getApplicationContext(),"Title " + marker.getTitle() + " wasn't found",Toast.LENGTH_LONG);
        }

        return false;
    }


    @Override
    public void onMapClick(LatLng latLng) {
//        Toast.makeText(getApplicationContext(),"Click Selected", Toast.LENGTH_LONG).show();
        GestorPuntos.getInstancia().deleteSelected();
        List<Address> listAddress = null;
        String nameToShow = "";
        try {
            listAddress = getGeocoder().getFromLocation(latLng.latitude,latLng.longitude,1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (listAddress.size() == 1)
        {
            nameToShow = WrapperAddress.nameToShow(listAddress.get(0));
        }

        citiText.setText(nameToShow);
        return;
    }

    private void showHelp() {
        Intent intent = new Intent(context,HelpWebViewActivity.class);
        startActivity(intent);


    }

}