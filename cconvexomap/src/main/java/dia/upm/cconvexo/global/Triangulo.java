package dia.upm.cconvexo.global;

import dia.upm.cconvexo.model.Punto;

public class Triangulo {
	
    protected double edge1, edge2, edge3;
    protected Punto punto1, punto2, punto3;
    protected String sName;

    // Constructor methods ....

    public Triangulo() {}

    public Triangulo( Punto p1, Punto p2, Punto p3) {

       this.sName = null;
       
       this.edge1 = Math.sqrt( (p1.x - p2.x)*(p1.x - p2.x) + (p1.y - p2.y)*(p1.y - p2.y)); 
       this.edge2 = Math.sqrt( (p1.x - p3.x)*(p1.x - p3.x) + (p1.y - p3.y)*(p1.y - p3.y));
       this.edge3 = Math.sqrt( (p2.x - p3.x)*(p2.x - p3.x) + (p2.y - p3.y)*(p2.y - p3.y));
       punto1 = p1;
       punto2 = p2;
       punto3 = p3;
    }


    //  ===================================================
    //  Retrieve X and Y coordinates of a polygon point....
    //  ===================================================

    public double getCentroidX() {
       return ( punto1.x + punto2.x + punto3.x )/3.0;
    }

    public double getCentroidY() {
       return ( punto1.y + punto2.y + punto3.y )/3.0;
    }

    // =====================================
    // Compute perimeter of triangle ....
    // =====================================

    public double perimeter() {
        return edge1 + edge2 + edge3;
    }

    // =====================================
    // Compute area of triangle ....
    // =====================================

    public double area() {
        double dS = perimeter()/2.0;
        double dArea = Math.sqrt( dS*(dS - edge1) *
                                     (dS - edge2) *
                                     (dS - edge3) );
        return dArea;
    }

    /*
     *  ==========================================================
     *  pointInsideTriangle(): Test to see if a (x,y) coordinate is
     *                         inside a triangle
     * 
     *  Arguments: 
     *  -- double dX: X coordinate 
     *  -- double dY: Y coordinate 
     *  =========================================================
     */

    /*public boolean pointInsideTriangle( double dX, double dY ) {
       int iCounter = 0;
       double xinters;
       double   slope;

       // ================================================================
       // Intersection of horizontal line and edge 1 (i.e., puntos 1 and 2)
       // ================================================================

       // Test. Is dY between the y values of puntos 1 and 2?...

       if (dY  > Math.min ( edge1.punto1.dY, edge1.punto2.dY ) &&
           dY <= Math.max ( edge1.punto1.dY, edge1.punto2.dY ) ) {

               if ( (dX <= Math.max( edge1.punto1.dX, edge1.punto2.dX )) &&
                    (edge1.punto1.dY != edge1.punto2.dY) ) {

                         // Compute slope (i.e., dx/dy) of edge ...

                         slope = (edge1.punto2.dX - edge1.punto1.dX)/
                                 (edge1.punto2.dY - edge1.punto1.dY);

                         // Compute x coordinate where edge intersects
                         // horizontal line....

                         xinters = ( dY - edge1.punto1.dY)*slope + edge1.punto1.dX;

                         // Increment counter for no of line crossings ...

                         if ( edge1.punto1.dX == edge1.punto2.dX || dX <= xinters ) 
                             iCounter = iCounter + 1;
               }
       }

       // ================================================================
       // Intersection of horizontal line and edge 2 (puntos 2 and 3)
       // ================================================================

       // Test. Is dY between the y values of puntos 2 and 3?...

       if (dY  > Math.min ( edge2.punto1.dY, edge2.punto2.dY ) &&
           dY <= Math.max ( edge2.punto1.dY, edge2.punto2.dY ) ) {

               if ( (dX <= Math.max( edge2.punto1.dX, edge2.punto2.dX )) &&
                    (edge2.punto1.dY != edge2.punto2.dY) ) {

                         // Compute slope (i.e., dx/dy) of edge ...

                         slope = (edge2.punto2.dX - edge2.punto1.dX)/
                                 (edge2.punto2.dY - edge2.punto1.dY);

                         // Compute x coordinate where edge intersects
                         // horizontal line....

                         xinters = ( dY - edge2.punto1.dY)*slope + edge2.punto1.dX;

                         // Increment counter for no of line crossings ...

                         if ( edge2.punto1.dX == edge2.punto2.dX || dX <= xinters) 
                             iCounter = iCounter + 1;
 
               }
       }

       // ================================================================
       // Intersection of horizontal line and edge 3 (puntos 3 and 1)
       // ================================================================

       // Test. Is dY between the y values of puntos 3 and 1?...

       if (dY  > Math.min ( edge3.punto1.dY, edge3.punto2.dY ) &&
           dY <= Math.max ( edge3.punto1.dY, edge3.punto2.dY ) ) {

               if ( (dX <= Math.max( edge3.punto1.dX, edge3.punto2.dX )) &&
                    (edge3.punto1.dY != edge3.punto2.dY) ) {

                         // Compute slope (i.e., dx/dy) of edge ...

                         slope = (edge3.punto2.dX - edge3.punto1.dX)/
                                 (edge3.punto2.dY - edge3.punto1.dY);

                         // Compute x coordinate where edge intersects
                         // horizontal line....

                         xinters = ( dY - edge3.punto1.dY)*slope + edge3.punto1.dX;

                         // Increment counter for no of line crossings ...

                         if ( edge3.punto1.dX == edge3.punto2.dX || dX <= xinters) 
                             iCounter = iCounter + 1;
               }
       }

       // Test to see if number of intersections is odd or even....

       if (iCounter % 2 == 0)
          return false;
       else
          return true;

    }
*/
    // Convert details of triangle to a string ...

    public String toString() {
        return "Triangle: \"" + sName + "\"\n" +
               "================================= \n" +    
               punto1.toString() + "\n" +
               punto2.toString() + "\n" +
               punto3.toString() + "\n" +
               "================================= \n";
    }

    public Punto getPunto1() {
        return punto1;
    }

    public void setPunto1(Punto punto1) {
        this.punto1 = punto1;
    }

    public Punto getPunto2() {
        return punto2;
    }

    public void setPunto2(Punto punto2) {
        this.punto2 = punto2;
    }

    public Punto getPunto3() {
        return punto3;
    }

    public void setPunto3(Punto punto3) {
        this.punto3 = punto3;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

// Exercise methods in triangle class ....

    
}



