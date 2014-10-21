package dia.upm.cconvexo.interfaces.model;

import android.graphics.Bitmap;
import android.location.Address;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dia.upm.cconvexo.cconvexomap.adapters.JsonRequest;

/**
 * Created by ivan on 31/08/14.
 */
public class Place {

    private String title;
    private Bitmap image;
    private String description;
    private String address;

    public Place()
    {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addressToPlace(Address ad)
    {
        this.title = ad.getFeatureName();
        this.address = ad.getAddressLine(0);
        this.description = "";
        if ( ad.getLocality() != null ) { description = description + " - " + ad.getLocality(); }
        if ( ad.getSubAdminArea() != null ) { description = description + " - " + ad.getSubAdminArea(); }
        if ( ad.getCountryName() != null ) { description = description + " - " + ad.getCountryName(); }

    }

    @Override
    public String toString() {
        return "Place{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Place place = (Place) o;

        if (!address.equals(place.address)) return false;
        if (!title.equals(place.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }

    public void jsontoPlace(JSONObject placeJson, JsonRequest jsonRequest) throws JSONException {
        assert placeJson != null;
        String name = placeJson.getString("name");
        if ( name != null) { setTitle(name);}
        String address = placeJson.getString("vicinity");
        if ( address != null) { setAddress(address); }
        if (placeJson.has("rating"))
        {
        String rating = placeJson.getString("rating");
        if ( rating != null) { setDescription(rating);}
        }

        if (placeJson.has("photos"))
        {
            JSONArray photos = ((JSONArray)placeJson.get("photos"));
            if (photos.length() > 0)
            {
                JSONObject photo = photos.getJSONObject(0);
                String photoReferece = (String) photo.get("photo_reference");
                this.setImage(jsonRequest.getImage(photoReferece));

            }

        }
        if (placeJson.has("types"))
        {
        String types = placeJson.getString("types");
        if (types != null) {
            if (getDescription() != null)
            {
                setDescription(getDescription() + types);
            }
            else
            {
                setDescription(types);
            }
        }
        }
        return ;
    }
}
