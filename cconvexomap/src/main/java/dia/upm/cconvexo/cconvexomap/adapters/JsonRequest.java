package dia.upm.cconvexo.cconvexomap.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import dia.upm.cconvexo.cconvexomap.gestores.GestorConexionHttp;
import dia.upm.cconvexo.interfaces.model.Place;

/**
 * Created by ivan on 14/09/14.
 */
public class JsonRequest {

    private final String keyApi = "AIzaSyD4u_Xd7vRiQnyoDQEizAg2HAzHyyRA4Rs";
    private final String urlImage = "https://maps.googleapis.com/maps/api/place/photo?";

    public JsonRequest()
    {

    }

    public String getRequest(String types, String radius, Address ad)
    {
        StringBuilder sb = new StringBuilder(
                "https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        sb.append("location=" + ad.getLatitude() + ","
                + ad.getLongitude());
        sb.append("&radius="+radius);
        sb.append("&types=" + types);
        sb.append("&sensor=false");
        sb.append("&key="+keyApi);
        sb.append("&rankedby=distance");
        return sb.toString();
    }

    public String getUrlImage(String id)
    {
        StringBuilder sbImage = new StringBuilder(urlImage);
        sbImage.append("photoreference=" + id);
        sbImage.append("&key="+keyApi);
        sbImage.append("&maxheight=80");
        return sbImage.toString();

    }

    public String convertStreamToString(InputStream in) {
        // TODO Auto-generated method stub
        BufferedReader br=new BufferedReader(new InputStreamReader(in));
        StringBuilder jsonstr=new StringBuilder();
        String line;
        try {
            while((line=br.readLine())!=null)
            {
                String t=line+"\n";
                jsonstr.append(t);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonstr.toString();
    }

    public List<Place> parse(JSONObject jsonobj) throws JSONException {
        List<Place> result = new LinkedList<Place>();
        JSONArray resarray=jsonobj.getJSONArray("results");
        for (int i = 0; i < 10; i++) {
             JSONObject placeJson= resarray.getJSONObject(i);
             Place place = new Place();
             place.jsontoPlace(placeJson,this);
             result.add(place);
        }
        return result;
    }

    public List<Place> getListPlace(Address place, String type) throws Exception {

        List<Place> result = new LinkedList<Place>();
        HttpClient cliente = GestorConexionHttp.getInstancia();
        JsonRequest request = new JsonRequest();
        HttpGet placesGet = new HttpGet(request.getRequest(type,"50000", place));
        HttpResponse placesResponse = cliente.execute(placesGet);
        StatusLine placeSearchStatus = placesResponse.getStatusLine();
        if (placeSearchStatus.getStatusCode() == 200)
        {
            HttpEntity jsonentity=placesResponse.getEntity();
            InputStream in=jsonentity.getContent();
            JSONObject jsonobj=new JSONObject(request.convertStreamToString(in));
            result=parse(jsonobj);

        }
        else
        {
            Log.d(JsonRequest.class.getName(), "Error en la conexion al places api");
        }

        return result;
    }

    private Bitmap downloadImage(String strUrl) throws Exception {
        Bitmap bitmap=null;
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            /** Creating an http connection to communcate with url */
            urlConnection = (HttpURLConnection) url.openConnection();

            /** Connecting to url */
            urlConnection.connect();

            /** Reading data from url */
            iStream = urlConnection.getInputStream();

            /** Creating a bitmap from the stream returned from the url */
            bitmap = BitmapFactory.decodeStream(iStream);

        }catch(Exception e){
            Log.d("Exception while downloading url", e.toString());
            e.printStackTrace();
            throw e;
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return bitmap;
    }

    public Bitmap getImage(String photoReferece)  {
        assert photoReferece != null;
        Bitmap bitmap = null;

        String urltoRequest = this.getUrlImage(photoReferece);
        try {
            bitmap = downloadImage(urltoRequest);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(JsonRequest.class.getName(),"Error al descargar la imagen");
        }
        return bitmap;
    }
}
