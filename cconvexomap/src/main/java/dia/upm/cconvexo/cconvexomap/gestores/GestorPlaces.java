package dia.upm.cconvexo.cconvexomap.gestores;

import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.cconvexomap.listeners.PlacesListener;
import dia.upm.cconvexo.interfaces.model.Place;

/**
 * Created by ivan on 21/09/14.
 */
public class GestorPlaces {

    private static GestorPlaces instancia;


    private List<Place> places;
    private List<PlacesListener> listeners;

    public static GestorPlaces getInstancia()
    {
        if ( instancia == null)
        {
            instancia = new GestorPlaces();
        }
        assert  instancia != null;
        return instancia;
    }

    public List<Place> getPlaces() {
        return places;
    }


    private GestorPlaces()
    {
        places = new LinkedList<Place>();
        listeners = new LinkedList<PlacesListener>();
    }

    public void setPlaces(List<Place> places) {
        if (places == null)
        {
            places = new LinkedList<Place>();
        }
        else
        {
            this.places = places;
        }
        for (int i = 0; i < listeners.size() ; i++) {
            PlacesListener listener = listeners.get(i);
            listener.placeChanged(places);

        }
    }

    public void addListener(PlacesListener listener)
    {
        assert listener != null;
        if (listeners.contains(listener) == false)
        {
            listeners.add(listener);
        }

    }
}
