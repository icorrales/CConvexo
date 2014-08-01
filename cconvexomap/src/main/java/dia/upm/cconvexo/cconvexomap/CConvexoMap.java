package dia.upm.cconvexo.cconvexomap;

import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import dia.upm.cconvexo.cconvexomap.adapters.PlaceAutoCompleterAdapter;
import dia.upm.cconvexo.cconvexomap.gestores.GestorPuntos;

public class CConvexoMap extends FragmentActivity {

    GoogleMap mMap;
    Geocoder geocoder;

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
    Button addButton;
    AutoCompleteTextView citiText;
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
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

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
                            Toast.makeText(getApplicationContext(),
                                    "number of result: " + locationList.size(),
                                    Toast.LENGTH_LONG).show();
                            Address location = locationList.get(0);
                            GestorPuntos.getInstancia().addLocation(location);
                            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                            mMap.addMarker(new MarkerOptions()
                                    .position(latLng)
                                    .title(locationName)
                                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                            CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng)
                                    .zoom(10f)
                                    .bearing(0)
                                    .tilt(25)
                                    .build();
                            changeCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),null);
                        }
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
        geocoder = new Geocoder(this, Locale.ENGLISH);
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
        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(searchListener);

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

}
