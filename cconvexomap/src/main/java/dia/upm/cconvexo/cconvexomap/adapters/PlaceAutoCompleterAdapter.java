package dia.upm.cconvexo.cconvexomap.adapters;

import android.content.Context;
import android.location.Address;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;


import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.cconvexomap.CConvexoMap;

public class PlaceAutoCompleterAdapter extends ArrayAdapter<String> implements Filterable {
    private ArrayList<String> resultList;
    private CConvexoMap view;
    private static int maxResults = 5;

    public PlaceAutoCompleterAdapter(Context context, int textViewResourceId, CConvexoMap view) {
        super(context, textViewResourceId);
        assert view != null;
        this.view = view;
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public String getItem(int index) {
        return resultList.get(index);
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    // Retrieve the autocomplete results.
                    Log.d(this.getClass().getName(),"Filter: " + constraint);
                    resultList = (ArrayList<String>) autocomplete(constraint.toString());

                    // Assign the data to the FilterResults
                    filterResults.values = resultList;
                    filterResults.count = resultList.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    notifyDataSetChanged();
                }
                else {
                    notifyDataSetInvalidated();
                }
            }};
        return filter;
    }

    private List<String> autocomplete(String s) {

        List<Address> locationList;
        List<String> listaReturn = new ArrayList<String>();
        try {
            locationList = view.getGeocoder().getFromLocationName(s, maxResults);


        if(locationList == null){
            locationList = new LinkedList<Address>();
        }



        for (Address address : locationList) {
            if (address.getLocality() != null) {
                String newLocal = address.getThoroughfare() + "," + address.getLocality();
                Log.d(this.getClass().getName(),"Location to add: "+ newLocal);
                listaReturn.add(newLocal);

            }
        }


        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d(this.getClass().getName(),"autocomplete: " + listaReturn.toString());
        return listaReturn;
    }
}