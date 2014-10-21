package dia.upm.cconvexo.cconvexomap;

import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import dia.upm.cconvexo.algoritmos.Jarvis;
import dia.upm.cconvexo.cconvexomap.activities.ListPlaces;
import dia.upm.cconvexo.cconvexomap.adapters.PlaceAutoCompleterAdapter;
import dia.upm.cconvexo.cconvexomap.gestores.GestorGeocoder;
import dia.upm.cconvexo.cconvexomap.gestores.GestorPuntos;
import dia.upm.cconvexo.cconvexomap.midpoint.MidPoint;
import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.model.Arista;
import dia.upm.cconvexo.model.Punto;

public class CConvexoMap extends FragmentActivity {

    GoogleMap mMap;
    Geocoder geocoder;
    MidPoint midPoint ;
    private Intent placeIntent;
    private ImageButton deleteButton;
    private View.OnClickListener deleteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            GestorPuntos.getInstancia().clear();
            drawMarkers();

        }

    };


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
    Button searchButton;
    ImageButton addButton;
    AutoCompleteTextView citiText;
    private ImageButton placeButton;

    private View.OnClickListener convexHulAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            GestorConjuntoConvexo.getInstancia().initGestor();
            Jarvis algorithm = new Jarvis();

            algorithm.start(10);
            drawMarkers();
            drawConvexHull();





        }
    };

    private View.OnClickListener placesAction = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Address place = GestorPuntos.getInstancia().getMidPoint();
            if ( place == null )
            {

            }
            else
            {
                placeIntent.putExtra("Address",GestorPuntos.getInstancia().getMidPoint());
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
        List listPuntos = GestorPuntos.getInstancia().getLocations();
        for (Iterator iterator = listPuntos.iterator(); iterator.hasNext(); ) {
            Address next = (Address) iterator.next();
            drawMarker(next,false, BitmapDescriptorFactory.HUE_AZURE);
        }
        if (GestorPuntos.getInstancia().getMidPoint() != null)
        {
            drawMarker(GestorPuntos.getInstancia().getMidPoint(),false,BitmapDescriptorFactory.HUE_ORANGE);

        }

        if (listPuntos.size() > 0)
        {
            changeCamera(CameraUpdateFactory.newLatLngBounds(GestorPuntos.getInstancia().getBounds(),50),null);
        }

    }

    private View.OnClickListener searchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String locationName = citiText.getText().toString();
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
                            GestorPuntos.getInstancia().addLocation(location);
                            Punto punto = new Punto();
                            punto.setX(location.getLatitude());
                            punto.setY(location.getLongitude());
                            GestorConjuntoConvexo.getInstancia().getListaPuntos().add(punto);
                            midPoint.calculateMidPoint(GestorPuntos.getInstancia().getLocations());
                            Address midPointLocation = midPoint.getAddress(getGeocoder());
                            GestorPuntos.getInstancia().setMidPoint(midPointLocation);
                            drawMarkers();
                        }
                    }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

            }
            return;
        }


    };



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
        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(convexHulAction);
        addButton = (ImageButton) findViewById(R.id.addButton);
        addButton.setOnClickListener(searchListener);
        placeButton = (ImageButton) findViewById(R.id.placeButton);
        placeButton.setOnClickListener(placesAction);
        placeIntent = new Intent(this, ListPlaces.class);
        deleteButton = (ImageButton) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(deleteListener);

        midPoint = new MidPoint();

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
    }

    public void searchCity()
    {

    }

    private void drawMarker(Address location, boolean b, float colorIcon)
    {
        assert location != null;

        LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
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


        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(nameToShow)
                .icon(BitmapDescriptorFactory.defaultMarker(colorIcon)));
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
}
