package dia.upm.cconvexo.model;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.Iterator;
import java.util.List;

import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;

/**
 * Created by ivan on 22/11/14.
 */
public class Circle {

    public double radius;
    public Punto centro;

    static final double TOL = 0.00000000001;

    public Circle( Punto p1, Punto p2, Punto p3)
    {
        initCircle(p1, p2, p3);

    }

    private void initCircle(Punto p1, Punto p2, Punto p3) {
        double offset = Math.pow(p2.x,2) + Math.pow(p2.y,2);
        double bc =   ( Math.pow(p1.x,2) + Math.pow(p1.y,2) - offset )/2.0;
        double cd =   (offset - Math.pow(p3.x, 2) - Math.pow(p3.y, 2))/2.0;
        double det =  (p1.x - p2.x) * (p2.y - p3.y) - (p2.x - p3.x)* (p1.y - p2.y);

        if (Math.abs(det) < TOL) { throw new IllegalArgumentException("Yeah, lazy."); }

        double idet = 1/det;

        double centerx =  (bc * (p2.y - p3.y) - cd * (p1.y - p2.y)) * idet;
        double centery =  (cd * (p1.x - p2.x) - bc * (p2.x - p3.x)) * idet;
        double radius =
                Math.sqrt( Math.pow(p2.x - centerx,2) + Math.pow(p2.y-centery,2));

        centro = new Punto(centerx,centery);
        this.radius = radius;
    }


    // Metodo para obtener un circulo correcto en el mapa de google maps.
    // Ya que no es una espacio euclideo y las distancias cambian entre puntos.
    public Circle( Punto p1, Punto p2, Punto p3,boolean inGmaps)
    {
        initCircle(p1, p2, p3);
        if (inGmaps)
        {
            double d1 = toRadiusMeters(p1.latLng,p2.latLng);
            double d2 = toRadiusMeters(p1.latLng,p3.latLng);
            double d3 = toRadiusMeters(p2.latLng,p3.latLng);
            if (d1>d2)
            {
                if (d1 > d3)
                {
                    this.radius = d1;
                }
                else
                {
                    this.radius = d3;
                }
            }
            else
            {
                if (d2 > d3)
                {
                    this.radius = d2;
                }
                else
                {
                    this.radius = d3;
                }

            }
        }

//        this.radius = toRadiusMeters(centro,this.radius);
    }

    public double area()
    {
        return Math.PI*Math.pow(radius,2);
    }

    public boolean contains(Punto p)
    {
        return (Math.abs(p.distance(centro)) <= radius + TOL);
    }


    public boolean contains(List<Punto> cconvexoPuntos) {

        boolean containsPoints = true;

        for (Iterator<Punto> iterator = cconvexoPuntos.iterator(); iterator.hasNext(); ) {
            Punto next = iterator.next();
            if (this.contains(next) == false)
            {
                containsPoints = false;
            }
        }
        return containsPoints;
    }

    public Circle(Punto p1, Punto p2) {

        initCircle2Points(p1, p2);

    }

    private void initCircle2Points(Punto p1, Punto p2) {
        double centro_x = 0.5*(p1.x + p2.x);
        double centro_y = 0.5*(p1.y + p2.y);
        centro = new Punto(centro_x,centro_y);
        radius = centro.distance(p1);
    }

    public Circle(Punto p1, Punto p2,boolean inGmaps) {

        initCircle2Points(p1,p2);
        if (inGmaps)
        {
            double d1 = toRadiusMeters(centro.latLng,p1.latLng);
            double d2 = toRadiusMeters(centro.latLng,p1.latLng);
            if (d1 > d2)
            {
                radius = d1;
            }
            else
            {
                radius = d2;
            }
        }

    }

    public double toRadiusMeters()
    {
        return toRadiusMeters(new LatLng(centro.getX(),centro.getY()),pointIntoCircleLatLng());
    }

    public double toRadiusMeters(LatLng center, LatLng radius) {
        float[] result = new float[1];
        Location.distanceBetween(center.latitude, center.longitude,
                radius.latitude, radius.longitude, result);
        return result[0];
    }

    /*
    Method that return a point that is in the circle and with distance radius.
    This method was created because in googlemaps you need to have the distance in meters and for getting it , we need
    LATLNG of center point and radius LATLNG.
     */
    public LatLng pointIntoCircleLatLng() {

        Punto puntoDelConvexHull = GestorConjuntoConvexo.getInstancia().getConjuntoConvexoPuntos().get(0);
        return new LatLng(puntoDelConvexHull.getX(),puntoDelConvexHull.getY());
    }
}
