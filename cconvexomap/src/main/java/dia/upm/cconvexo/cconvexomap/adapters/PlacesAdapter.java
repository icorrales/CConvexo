package dia.upm.cconvexo.cconvexomap.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import dia.upm.cconvexo.cconvexomap.R;
import dia.upm.cconvexo.interfaces.model.Place;

/**
 * Created by ivan on 30/08/14.
 */
public class PlacesAdapter extends BaseAdapter {

    private Context mContext;
    List<Place> places;

    public List<Place> getPlaces() {
        return places;
    }

    public void setPlaces(List<Place> places) {
        Log.d(this.getClass().getName(), "set new places " );
        this.places = places;
    }

    public PlacesAdapter(Context c)
    {
        super();
        assert c != null;
        mContext = c;
    }


    @Override
    public int getCount() {
        int size=0;
        if (places != null)
        {
            size = places.size();
        }

        Log.d(this.getClass().getName(),"getCount:" + size );
        return size;
    }

    @Override
    public Object getItem(int position) {
        assert places != null ;
        Object place = null;
        if (places != null)
        {
            place = places.get(position);
        }
        Log.d(this.getClass().getName(),"getItem :" + position + " " + place.toString() );
        return place;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Place place = (Place) this.getItem(position);
        View rootView = LayoutInflater
                .from(mContext)
                .inflate(R.layout.place, parent, false);
        TextView title = (TextView) rootView.findViewById(R.id.textPlaceTitle);
        title.setText(place.getTitle());
        //title.setLayoutParams(new GridView.LayoutParams(85, 40));
        TextView address = (TextView) rootView.findViewById(R.id.textPlaceAddress);
        address.setText(place.getAddress());
        //address.setLayoutParams(new GridView.LayoutParams(85,40));
        TextView description = (TextView) rootView.findViewById(R.id.textPlaceDescription);
        description.setText(place.getDescription());
        //description.setLayoutParams(new GridView.LayoutParams(85, 40));
        ImageView image = (ImageView) rootView.findViewById(R.id.imagePlace);
        image.setImageBitmap(place.getImage());
        //image.setLayoutParams(new GridView.LayoutParams(85, 85));

        return rootView;
    }
}
