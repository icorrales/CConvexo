package dia.upm.cconvexo.model;


import android.graphics.Point;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

/**
 * Class Punto that extends from Point. Extends Point class to control some methods better.
 */
public class Punto  extends Point {

	public double x;
	public double y;
    public double z;
    public LatLng latLng;
    private static final double MAX_DISTANCE = 20;

    public Punto(double centerx, double centery) {
        this.x = centerx;
        this.y = centery;
        builtLatLng();


    }

    private void builtLatLng() {
        double lon = Math.atan2(this.y, this.x);
        double hyp = Math.sqrt((this.x*this.x) + (this.y*this.y) );
        double lat = Math.atan2(this.z,hyp);
        this.latLng = toDegrees(lat,lon);
    }

    public Punto (LatLng lat)
    {
        assert lat != null;
        latLng = lat;
/*        LatLng latDegrees = toRadians();
        x = Math.cos(latDegrees.latitude) * Math.cos(latDegrees.longitude);
        y = Math.cos(latDegrees.latitude) * Math.sin(latDegrees.longitude);
        z = Math.sin(latDegrees.latitude); */
        this.x = lat.latitude;
        this.y = lat.longitude;
        this.z = 1;

    }

    private LatLng toDegrees(double lat, double lon) {

        LatLng valueToReturn = new LatLng((lat * 180) / Math.PI,(lon * 180) / Math.PI);

        return  valueToReturn;

    }

    private LatLng toRadians() {
        LatLng valueToReturn = new LatLng((latLng.latitude * Math.PI) / 180,(latLng.longitude * Math.PI) / 180);
        return  valueToReturn;


    }

    /**
     * Setter for field x
     * @param x
     */
	public void setX(double x) {
		this.x = x;
        builtLatLng();
	}

    /**
     * Setter for field y
     * @param y
     */
	public void setY(double y) {
		this.y = y;
        builtLatLng();
	}

    /**
     * Constructor for Punto.
     */
	public Punto()
	{
		
	}
    // antes estaba con -b.y

    /**
     * Constructor for Punto from a Point java class.
     * @param b
     */
	 public Punto(Point b) {
		// TODO Auto-generated constructor stub
		this.x = b.x;
		this.y = b.y;
        builtLatLng();
	}


    /**
     * getter for field x
     * @return x coordinate value
     */
	public double getX() {
		// TODO Auto-generated method stub
		
		return x;
	}

    /**
     * getter for field y
     * @return y coordinate value
     */

	public double getY() {
		// TODO Auto-generated method stub
		return y;
	}


	public void setLocation(double arg0, double arg1) {
		// TODO Auto-generated method stub
		x = arg0;
		y = - arg1;

	}
	
	@Override
    /**
     * Método para override el método toString, ya que no se obtiene de point, sino de punto.
     */
	public String toString()
	{
		return "("+ x + "," + y+")";
	}
	
	@Override
    /**
     * Metodo para override el meotod igual, ya que no se obtiene de point, sino de punto.
     */
	public boolean equals(Object o)
	{
		assert o != null;
		assert o instanceof Punto;
		
		Punto p = (Punto) o;

		return p.getX() == x && p.getY() == y; 
		
	}


    /**
     * Adapter to point class.
     * @return
     */
	public Point getPoint()
	{
		

		return new Point((int)this.x,Math.abs((int)this.y));
	}

    /**
     * Funcion de Punto que nos dice si un punto esta cerca a otro
     * @param p punto al que hay que ver si esta cerca este punto
     * @return true si distancia entre this y p es menor que MAX_DIST
     */
    public boolean isClose(Punto p) {

        boolean isClose = false;
        double distance = this.distance(p);
        Log.d(Punto.class.getName(), "Distancia de " + p.toString() + " a " + this.toString() + " = " + distance);
        if ( distance< MAX_DISTANCE)
        {
            isClose = true;
        }

        return isClose;
    }

    public double distance(Punto p) {
        assert p != null;
        double distance = Math.hypot((this.getX() - p.getX()), (this.getY() - p.getY()));
        return distance;
    }


}
