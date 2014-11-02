package dia.upm.cconvexo.cconvexomap.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Address;
import android.os.AsyncTask;

import java.util.List;

import dia.upm.cconvexo.cconvexomap.adapters.JsonRequest;
import dia.upm.cconvexo.cconvexomap.gestores.GestorPlaces;
import dia.upm.cconvexo.interfaces.model.Place;

/**
 * Created by ivan on 21/09/14.
 */
public class GetPlaces extends AsyncTask <Void,Integer,List<Place>>
        {

    Address place = null;
    Context context = null;
    private ProgressDialog dialog;


            String type = null;

    public GetPlaces(Address arg1, Context arg2)
    {
        assert arg1 != null;
        assert arg2 != null;
        this.place = arg1;
        this.context = arg2;
        this.type = "bar";

    }

    @Override
    protected List<Place> doInBackground(Void... params) {

        JsonRequest request = new JsonRequest();
        List<Place> list = null;
        try {
            list = request.getListPlace(place,type);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


            @Override
    protected void onPreExecute() {
       super.onPreExecute();
       dialog = new ProgressDialog(context);
       dialog.setCancelable(false);
       dialog.setMessage("Loading..");
       dialog.isIndeterminate();
       dialog.show();
    }

    @Override
    protected void onPostExecute(List<Place> listPlaces)
    {
        super.onPostExecute(listPlaces);
//  Prueba de gradle
        if (dialog.isShowing())
        {
            dialog.dismiss();
        }
        GestorPlaces.getInstancia().setPlaces(listPlaces);
    }

    public String getType() {
       return type;
    }

    public void setType(String type) {
       this.type = type;
    }

    }
