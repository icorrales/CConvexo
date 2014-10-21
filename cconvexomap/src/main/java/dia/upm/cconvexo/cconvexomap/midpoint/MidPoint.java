package dia.upm.cconvexo.cconvexomap.midpoint;

import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.util.Iterator;
import java.util.List;
import java.lang.Math;
import java.util.Locale;

/**
 * Created by ivan on 23/08/14.
 */
public class MidPoint {


    private double Lon;
    private double Lat;
    private double Hyp;


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

    public void calculateMidPoint(List<Address> locations)
    {
        double x = 0;
        double y = 0;
        double z = 0;
        double w = 1;
        double w_total = locations.size();

        for (Iterator<Address> iterator = locations.iterator(); iterator.hasNext(); ) {
            Address next = iterator.next();
            double lat1 = (next.getLatitude() * Math.PI)/180;
            double long1 = (next.getLongitude() * Math.PI)/180;
            double x1 = Math.cos(lat1) * Math.cos(long1);
            double y1 = Math.cos(lat1) * Math.sin(long1);
            double z1 = Math.sin(lat1);
            x = x + x1;
            y = y + y1;
            z = z + z1;
        }
        x = x / w_total;
        y = y /w_total;
        z = z / w_total;

        Lon = Math.atan2(y, x);
        Hyp = Math.sqrt((x*x) + (y*y) );
        Lat = Math.atan2(z,Hyp);

        Lat = (Lat * 180 ) / Math.PI;
        Lon = (Lon * 180 ) / Math.PI;

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
}
