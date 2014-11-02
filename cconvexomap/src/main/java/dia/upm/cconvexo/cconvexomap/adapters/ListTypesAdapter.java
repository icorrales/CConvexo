package dia.upm.cconvexo.cconvexomap.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.cconvexomap.activities.ListPlaces;

/**
 * Created by ivan on 19/10/14.
 */
public class ListTypesAdapter extends ArrayAdapter{


    private static final HashMap<String, String> mapTypes;
    static
    {
      mapTypes = new HashMap<String, String>();
      mapTypes.put("Bares","bar");
      mapTypes.put("Cafes","cafe");
      mapTypes.put("Casino","casino");
      mapTypes.put("Cines","movie_theather");
      mapTypes.put("Museos","museum");
      mapTypes.put("Pubs","night_club");
      mapTypes.put("Restaurantes","restaurant");
    }

    private List<String> types;

    public ListTypesAdapter(Context c,int i1) {
        super(c,i1);
        init();

    }

    private void init() {
        types = new LinkedList<String>();
        types.addAll(mapTypes.keySet());
        this.addAll(types);
    }

    @Override
    public String getItem(int position)
    {
        assert position > 0 && position < types.size();
        String type = types.get(position);
        return mapTypes.get(type);
    }

}
