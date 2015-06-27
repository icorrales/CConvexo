package dia.upm.cconvexo.cconvexomap.midpoint;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.lang.Math;
import java.util.Locale;

import dia.upm.cconvexo.cconvexomap.R;
import dia.upm.cconvexo.cconvexomap.gestores.GestorGeocoder;
import dia.upm.cconvexo.cconvexomap.gestores.GestorPuntos;
import dia.upm.cconvexo.cconvexomap.model.PuntoLngLon;
import dia.upm.cconvexo.cconvexomap.model.WrapperAddress;

/**
 * Created by ivan on 23/08/14.
 */
public class MidPoint {


    private double Lon;
    private double Lat;
    private double Hyp;
//    private double midlat;
//    private double mitlng;
    private double x , y ,z ;
    private double x1,y1,z1;
    private int totdays = 0;
    PuntoLngLon pt;
    double[] lats1;
    double[] lons1;
    int[] days1;
    double[] sinlats;
    double[] coslats;
    private int algorithm = R.id.rbt_midpoint;
    private LatLng latLng;
    private final double rad90 = Math.toRadians(90);
    private final double rad180 = Math.toRadians(180);


    public MidPoint()
    {

    }



    /*
    Given the values for the first location in the list:
    Lat1, lon1, years1, months1 and days1
    Convert Lat1 and Lon1 from degrees to radians.
    lat1 = lat1 * PI/180
    lon1 = lon1 * PI/180
    Convert lat/lon to Cartesian coordinates for first location.
    X1 = cos(lat1) * cos(lon1)
    Y1 = cos(lat1) * sin(lon1)
    Z1 = sin(lat1)
    Compute weight (by time) for first location.
    w1= (years1 * 365.25) + (months1 * 30.4375) + days1
    If locations are to be weighted equally, set w1, w2 etc all equal to 1.
    Repeat steps 1-3 for all remaining locations in the list.
    Compute combined total weight for all locations.
    Totweight = w1 + w2 + ... + wn
    Compute weighted average x, y and z coordinates.
    x = ((x1 * w1) + (x2 * w2) + ... + (xn * wn)) / totweight
    y = ((y1 * w1) + (y2 * w2) + ... + (yn * wn)) / totweight
    z = ((z1 * w1) + (z2 * w2) + ... + (zn * wn)) / totweight
    Convert average x, y, z coordinate to latitude and longitude. Note that in Excel and possibly some other applications, the parameters need to be reversed in the atan2 function, for example, use atan2(X,Y) instead of atan2(Y,X).
    Lon = atan2(y, x)
    Hyp = sqrt(x * x + y * y)
    Lat = atan2(z, hyp)
    Convert lat and lon to degrees.
    lat = lat * 180/PI
    lon = lon * 180/PI
    Special case:
    If abs(x) < 10-9 and abs(y) < 10-9 and abs(z) < 10-9 then the geographic midpoint is the center of the earth.
     */

    public void calculateMidPoint(List<WrapperAddress> locations)
    {
        double x = 0;
        double y = 0;
        double z = 0;
        double w = 1;
        double w_total = 0;

        for (Iterator<WrapperAddress> iterator = locations.iterator(); iterator.hasNext(); ) {
            WrapperAddress wNext = iterator.next();
            Address next = wNext.getLocation();
            double lat1 = (next.getLatitude() * Math.PI)/180;
            double long1 = (next.getLongitude() * Math.PI)/180;
            double x1 = Math.cos(lat1) * Math.cos(long1);
            double y1 = Math.cos(lat1) * Math.sin(long1);
            double z1 = Math.sin(lat1);
            x = x + (x1*wNext.getWeight());
            y = y + (y1*wNext.getWeight());
            z = z + (z1*wNext.getWeight());
            w_total= w_total + wNext.getWeight();
        }
        x = x / w_total;
        y = y /w_total;
        z = z / w_total;

        Lon = Math.atan2(y, x);
        Hyp = Math.sqrt((x*x) + (y*y) );
        Lat = Math.atan2(z,Hyp);

        toDegrees();

    }

    public Address getAddress ( Geocoder geocoder) throws Exception
    {
        List<Address> locations = geocoder.getFromLocation(Lat,Lon,1);
        Address location = null;
        if (locations.size() == 0)
        {
            location = new Address(Locale.CANADA);
            location.setLatitude(Lat);
            location.setLongitude(Lon);
            location.setLocality("No Address");
        }
        else
        {
            location = locations.get(0);
        }
        return location;
    }


/*
    function calculate(){
        if(p.length>1||par)
        {var midlat=0,midlng=0;var x=0;var y=0;var z=0;var x1,y1,z1;var pt=new Object();pt.lat=0;pt.lon=0;var totdays=0;
        var lats1=new Array();var lons1=new Array();var days1=new Array();var sinlats=new Array();var coslats=new Array();with(Math)
*/
    public void calculate()
    {

        x = 0;
        y = 0;
        z =0;
        totdays = 0;
        pt = new PuntoLngLon();
        List<WrapperAddress> listLocations = GestorPuntos.getInstancia().getLocations();
        lats1 = new double[listLocations.size()];
        lons1 = new double[listLocations.size()];
        days1 = new int[listLocations.size()];
        sinlats = new double[listLocations.size()];
        coslats = new double[listLocations.size()];



/*
            {for(i=0;i<p.length;i++)
            {lats1[i]=rad(p[i].marker.getPosition().lat());
                lons1[i]=rad(p[i].marker.getPosition().lng());
                sinlats[i]=sin(lats1[i]);
                coslats[i]=cos(lats1[i]);
                days1[i]=p[i].y*365.25+p[i].m*30.4375+p[i].d*1;
                if(days1[i]==0)
                {days1[i]=1}
                totdays=totdays+days1[i];
                x1=coslats[i]*cos(lons1[i]);
                y1=coslats[i]*sin(lons1[i]);
                z1=sinlats[i];
                x+=x1*days1[i];
                y+=y1*days1[i];
                z+=z1*days1[i]
            }

            */
        for (int i = 0; i < listLocations.size(); i++) {
            WrapperAddress wrapperAddress = listLocations.get(i);
            lats1[i] =  (wrapperAddress.getLatLng().latitude * Math.PI)/180;
            lons1[i] = (wrapperAddress.getLatLng().longitude * Math.PI)/180;
            sinlats[i] = Math.sin(lats1[i]);
            coslats[i] = Math.cos(lats1[i]);
            days1[i] = wrapperAddress.getWeight();
            totdays = totdays + days1[i];
            x1 = coslats[i] * Math.cos(lons1[i]);
            y1 = coslats[i] * Math.sin(lons1[i]);
            z1 = sinlats[i];
            x = x + (x1 * days1[i]);
            y = y + (y1 * days1[i]);
            z = z + (z1 * days1[i]);
        }



    /*
                x=x/totdays;
                y=y/totdays;
                z=z/totdays;
                midlng=atan2(y,x);
                hyp=sqrt(x*x+y*y);
                midlat=atan2(z,hyp);

*/
        x = x / totdays;
        y = y / totdays;
        z = z / totdays;
        Lon = Math.atan2(y, x);
        Hyp = Math.sqrt((x*x) + (y*y) );
        Lat = Math.atan2(z,Hyp);


    }


    public void geographic_midpoint()
    {
        calculate();
        toDegrees();

    }

    /*
                if(cI!=2&&abs(x)<1e-9&&abs(y)<1e-9&&abs(z)<1e-9)
                {if(MM){MM=remove(MM)}displayError("The midpoint is the center of the earth.")}
                else{if(cI==2)
                {y=0;x=0;
                    for(i=0;i<lats1.length;i++)
                    {y=y+lats1[i]*days1[i];
                        x=x+normalizeLongitude(lons1[i]-midlng)*days1[i]}
                    midlat=y/totdays;
                    midlng=normalizeLongitude(x/totdays+midlng)}
        */
    public void avg_lat_lon()
    {
        calculate();
        x = 0;
        y = 0;
        for ( int i = 0; i < lats1.length; i++ ) {
            y = y + (lats1[i] *days1[i]);
            x = x + (normalizeLongitude(lons1[i] - Lon)*days1[i]);
        }
        Lat = y /totdays;
        Lon = normalizeLongitude((x / totdays) + Lon);
        toDegrees();

    }

    private void toDegrees() {
        Lat = (Lat * 180 ) / Math.PI;
        Lon = (Lon * 180 ) / Math.PI;
    }
/*
                else{
                    if(cI==1){
                        if(lats1.length>2||lats1.length==2&days1[0]!=days1[1]){
                            var tries=0;
                            lats1[lats1.length]=midlat;
                            lons1[lons1.length]=midlng;
                            var distrad=rad90;
                            var mindist=10000000;
                            var sum,gMindist,lat2,slat,cdist,minlat,minlon;
                            var t=new Array(8,6,7,2,0,1,5,3,4);
                            var scale=new Array(0.7071,0.7071,1,0.7071,0.7071,1,1,1,1);
                            var testcenter=true;
                            i=lats1.length+8;
                            */

    public void algorithm_min_distances()
    {
        calculate();

        if( (lats1.length > 2) || (lats1.length == 2 && (days1[0] != days1[1]))  )
        {
            int tries = 0;
            lats1[lats1.length -1] = Lat;
            lons1[lons1.length -1] = Lon;
            double distrad = Math.toRadians(90);
            double mindist = 10000000;
            double sum, gMindist=0,lat2,slat,cdist,minlat=0,minlon=0;
            int[] t = {8,6,7,2,0,1,5,3,4};
            double[] scale = {0.7071,0.7071,1,0.7071,0.7071,1,1,1,1};
            boolean testcenter = true;
            int i = lats1.length + 8;


    /*
                            while(distrad>2e-8&&tries<5000){
                                if(i<0){i=8}
                                while(i>=0){
                                    if(i<9){
                                        y=floor(t[i]/3)-1;
                                        x=t[i]%3;
                                        switch(x){
                                            case 1:
                                                pt.lon=midlng;
                                                pt.lat=midlat-y*distrad;
                                                pt=normalizeLatitude(pt);
                                                break;
                                            case 0:
                                                pt.lon=midlng;
                                                pt.lat=midlat-y*distrad*scale[i];
                                                pt=normalizeLatitude(pt);
                                                lat2=pt.lat;
                                                slat=sin(lat2);
                                                cdist=cos(distrad*scale[i]);
                                                pt.lat=asin(slat*cdist);
                                                pt.lon=normalizeLongitude(pt.lon+atan2(-sin(distrad*scale[i])*cos(lat2),cdist-slat*sin(pt.lat)))
                                                ;break;
                                            case 2:
                                                pt.lon=normalizeLongitude(midlng+normalizeLongitude(midlng-pt.lon))
                                        }
                                    }

                                    */
            while ( distrad > 2e-8 && tries <5000)
            {
                if ( i<0 ) {i=8;}
                while ( i>=0)
                {
                    if (i<9)
                    {
                        y= Math.floor(t[i]/3) -1;
                        x=t[i]%3;
                        if (x == 1)
                        {
                            pt.setLon(Lon);
                            pt.setLat(Lat-(y*distrad));
                            pt= normalizeLatitude(pt);
                        }
                        else if (x == 0)
                        {
                            pt.setLon(Lon);
                            pt.setLat(Lat-(y*distrad*scale[i]));
                            pt= normalizeLatitude(pt);
                            lat2 = pt.getLat();
                            slat = Math.sin(lat2);
                            cdist= Math.cos(distrad*scale[i]);
                            pt.setLat(Math.asin(slat*cdist));
                            double temp1 = normalizeLongitude(pt.getLon()+Math.atan2(-Math.sin(distrad * scale[i]) * Math.cos(lat2), cdist - (slat * Math.sin(pt.getLat()))));
                            pt.setLon(temp1);
                        } else if ( x == 2)
                        {
                            pt.setLon(normalizeLongitude(Lon+normalizeLongitude(Lon-pt.getLon())));
                        }
    /*
                                    else{
                                        pt.lat=lats1[i-9];
                                        pt.lon=lons1[i-9]}
                                    if(pt.lon!=midlng||pt.lat!=midlat||testcenter){
                                        sum=0;
                                        for(j=0;j<lats1.length-1;j++){
                                            sum+=acos(sinlats[j]*sin(pt.lat)+coslats[j]*cos(pt.lat)*cos(pt.lon-lons1[j]))*days1[j]
                                        }
                                        if(!testcenter){
                                            if(sum<mindist){
                                                mindist=sum;
                                                minlat=pt.lat;
                                                minlon=pt.lon
                                            }
                                        }else{
                                            gMindist=sum;
                                            testcenter=false
                                        }
                                    }
                                    i--
                                }
                                */

                    } else {
                        pt.setLat(lats1[i-9]);
                        pt.setLon(lons1[i-9]);
                    }
                    if (pt.getLon() != Lon || pt.getLat() != Lat || testcenter) {
                        sum = 0;
                        for(int j=0;j<lats1.length-1;j++){
                            sum+=Math.acos(sinlats[j] * Math.sin(pt.getLat()) + coslats[j] * Math.cos(pt.getLat()) * Math.cos(pt.getLon() - lons1[j]))*days1[j];
                        }
                        if(!testcenter){
                            if(sum<mindist){
                                mindist=sum;
                                minlat=pt.getLat();
                                minlon=pt.getLon();
                            }
                        }else{
                            gMindist=sum;
                            testcenter=false;
                        }
                    }
                    i--;

                }


            /*
                                if(mindist-gMindist<-4e-14){
                                    midlat=minlat;
                                    midlng=minlon;
                                    gMindist=mindist}
                                else{distrad=distrad*0.5}
                                tries++
                            }
                        }
                    }
                } //fin de calculate
*/
                if(mindist-gMindist<-4e-14){
                    Lat=minlat;
                    Lon=minlon;
                    gMindist=mindist;
                }
                else{distrad=distrad*0.5;}
                tries++;
            }
        }
        toDegrees();
    }


    public void algorithm_min_distances_red_googlemaps()
    {
        List<WrapperAddress> listaLocations = GestorPuntos.getInstancia().getLocations();

        WrapperAddress ptoCentral = listaLocations.get(0);
        double distMin = distMinima_gmaps(ptoCentral, listaLocations);
        for (int i = 1; i < listaLocations.size(); i++) {
            WrapperAddress next = listaLocations.get(i);
            double dist_next = distMinima_gmaps(next, listaLocations);
            if (dist_next < distMin)
            {
                distMin = dist_next;
                ptoCentral = next;
            }

        }
        Lat = ptoCentral.getLatLng().latitude;
        Lon = ptoCentral.getLatLng().longitude;

    }

    private double distMinima_gmaps(WrapperAddress next, List<WrapperAddress> listaLocations) {
        assert  next != null;
        assert listaLocations != null;
        double distMin = 0;
        for (WrapperAddress punto : listaLocations) {

            distMin = distMin + GestorGeocoder.toRadiusMeters(next.getLatLng(),punto.getLatLng());
        }
        return distMin;

    }

    public void algorithm_min_distances_red()
    {
        calculate();
        //toDegrees();
        //Actual punto central
        PuntoLngLon ptoCentral = new PuntoLngLon(Lat,Lon);



        List<WrapperAddress> listaLocations = GestorPuntos.getInstancia().getLocations();
        List<PuntoLngLon> listPtos = new LinkedList<PuntoLngLon>();

        for (Iterator<WrapperAddress> iterator = listaLocations.iterator(); iterator.hasNext(); ) {
            WrapperAddress next = iterator.next();
            PuntoLngLon pNext = new PuntoLngLon((next.getLatLng().latitude* Math.PI)/180,(next.getLatLng().longitude* Math.PI)/180);
            listPtos.add(pNext);
        }


        double distMin = distMinima(ptoCentral,listPtos);
        for (int i = 0; i < listPtos.size(); i++) {
            PuntoLngLon next = listPtos.get(i);
            double dist_next = distMinima(next,listPtos);
            if (dist_next < distMin)
            {
                distMin = dist_next;
                ptoCentral = next;
            }

        }
        Lat = ptoCentral.getLat();
        Lon = ptoCentral.getLon();
        toDegrees();
    }

    private double distMinima(PuntoLngLon next,List<PuntoLngLon> listPuntos) {
        assert  next != null;
        assert listPuntos != null;
        double distMin = 0;
        for (PuntoLngLon punto : listPuntos) {
            distMin += distancia_Ley_de_Cosenos(next,punto);
        }
        return distMin;
    }

    public double distancia_Ley_de_Cosenos(PuntoLngLon p1,PuntoLngLon p2)
    {
        assert p1 != null;
        assert p2 != null;

        double distance = 0;
        if (!p1.equals(p2))
            {
            distance = Math.acos(Math.sin(p1.getLat())*Math.sin(p2.getLat()) + Math.cos(p1.getLat())*Math.cos(p2.getLat())*Math.cos(p2.getLon() - p1.getLon()));
            }
        return distance;
    }


    /*function normalizeLatitude(a)
    {if(Math.abs(a.lat)>rad90)
        {a.lat=rad180-a.lat-2*rad180*(a.lat<-rad90);
        a.lon=normalizeLongitude(a.lon-rad180)}
        return a}
     */

    public PuntoLngLon normalizeLatitude(PuntoLngLon p)
    {

        if (Math.abs(p.getLat()) > rad90)
        {
            if ( p.getLat()<-rad90)
             p.setLat(rad180 - p.getLat() -(2*rad180));
            else
             p.setLat(rad180 - p.getLat());

            p.setLon(normalizeLongitude(p.getLon() - rad180));
        }
        return  p;

    }

    /*
    function normalizeLongitude(a)
        {var b=Math.PI;
        if(a>b)
        {a=a-2*b}
        else
        {if(a<-b)
            {a=a+2*b}
        }
        return a}
     */
    public double normalizeLongitude( double a)
    {
        if ( a > Math.PI)
        {
            a = a - (2*Math.PI);
        }
        else
        {
            a = a + (2*Math.PI);
        }
        return  a;
    }

    public void setAlgorithm(int algorithm) {
        this.algorithm = algorithm;
    }

    public int getAlgorithm() {
        return algorithm;
    }

    public LatLng getLatLng() {
        latLng = new LatLng(Lat,Lon);
        return latLng;
    }

    public void calc_midpoint() {
        switch (this.algorithm)
        {
            case R.id.rbt_midpoint:
                this.geographic_midpoint();
                break;
            case R.id.rbt_min_dist:
                this.algorithm_min_distances_red_googlemaps();
                break;
            case R.id.rbt_latlgn:
                this.avg_lat_lon();
                break;
        }
    }
}