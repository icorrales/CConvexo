/*
package dia.upm.cconvexo.algoritmos;


	import java.lang.*;
	import java.awt.*;

	*/
/**
	 * An implementation of Graham's Scan algorithm for 2-d convex hull
	 *
	 * @author Alejo Hausner (ah@cs.princeton.edu)
	 *//*


	public class Graham extends java.applet.Applet implements Runnable {
	  */
/**
	   * The thread that is running the hull alg. (or null).
	   *//*

	  private Thread kicker;

	  public synchronized void println (String s) {
	    System.out.println(s);
	  }

	  */
/**
	   * Phase of the algorithm:
	   * 0 = ready
	   * 1 = generating points
	   * 2 = scanning for extreme point
	   * 3 = sorting
	   * 4 = building hull
	   * 5 = done
	   *//*

	  int phase;

	  */
/**
	   * the center of the star-shaped polygon obtained after the sort.
	   *//*


	  Point pivot;

	  */
/**
	   * number of points
	   *//*

	  int npts;

	  */
/**
	   * number of valid points
	   *//*

	  int nvpts;

	  */
/**
	   * The data points themselves
	   *//*

	  Point pts[] = null;

	  */
/**
	   * maximum y value during scan for extreme point
	   *//*

	  float maxy;

	  */
/**
	   * index of point with maximum y value during scan for extreme point
	   *//*

	  int maxyi;

	  */
/**
	   * index of current point during any scan
	   *//*

	  int curpt;

	  */
/**
	   * The hull points
	   *//*

	  Point hull[] = null;

	  */
/**
	   * Indexes into original list of points, from which the hull points came.
	   *//*

	  int hulli[];

	  */
/**
	   * Hull size
	   *//*

	  int nhull;

	  */
/**
	   * The two points being compared during sorting.
	   *//*

	  Point p1,p2,p3;

	  */
/**
	   * The indices of the three points.
	   *//*

	  int i1,i2,i3;

	  */
/**
	   * Various global variables needed for drawing.
	   *//*

	  private Image offScreenImage=null;
	  private Dimension offScreenSize;
	  private Graphics offScreenGraphics;
	  int PointLabelWidth,PointLabelHeight,PointLabelLeading,AngleR;
	  Font myFont;
	  Canvas canvas;

	  private Button StartB;
	  private Checkbox PauseC;


	  */
/**
	   * Returns True if the sequence of points p,q,r form a right-hand turn.
	   *//*


	  float ccw (Point p, Point q) {
	    p1 = p;
	    p2 = pivot;
	    p3 = q;

	    return ccw (p, pivot, q);
	  }

	  float ccw (Point p, Point q, Point r) {
	    return (p.x-q.x)*(r.y-q.y) - (p.y-q.y)*(r.x-q.x);
	  }
	  
	  void erase_point_label(Graphics g, int i, int x) {
	    g.clearRect (x,(i+1)*(PointLabelHeight
				  +PointLabelLeading),
			 PointLabelWidth,
			 PointLabelHeight+PointLabelLeading+1);
	  }

	  void draw_point_label (Graphics g, int i, int x) {
	    g.drawString ("(" + pts[i].x + "," + pts[i].y + ")",
			  x,
			  (i+2)*(PointLabelHeight+PointLabelLeading)
			  -PointLabelLeading
			  );
	  }

	  void swap (Point pts[], int i, int j, boolean draw) {
	    if (draw) {
	      erase_point_label (offScreenGraphics,i,400);
	      erase_point_label (offScreenGraphics,j,400);
	      erase_angle (offScreenGraphics,i);
	      erase_angle (offScreenGraphics,j);
	    }

	    Point tmp = pts[i];
	    pts[i] = pts[j];
	    pts[j] = tmp;

	    if (draw) {
	      offScreenGraphics.setColor(Color.blue);
	      draw_point_label (offScreenGraphics, i, 400);
	      draw_point_label (offScreenGraphics, j, 400);
	      show_angle (offScreenGraphics, i);
	      show_angle (offScreenGraphics, j);
	      
	      pause (100);
	    }
	  }

	  float compare (int i, int j) {
	    return ccw (pts[i],pts[j]);
	  }
	    
	  */
/**
	   * Quick sort pretty much verbatim from Cormen, Leiserson and Rivest
	   *//*


	  void quicksort (Point a[], int p, int q) {
	    
	    Point x;
	    int i,j;

	    if (p < q) {

	      x = a[npts] = a[p];
	      i = p-1;
	      j = q+1;

	      while (true) {
		while (compare(--j,npts) > 0.0);
		while (compare(++i,npts) < 0.0);
		if (i<j)
		  swap (a,i,j,true);
		else
		  break;
	      }
	      quicksort (a,p,  j);
	      quicksort (a,j+1,q);
	    }
	  }

	  void init_points () {
	    int i;

	    pts = new Point[npts+1];
	    nvpts = 0;

	    for (i=0; i<npts; i++) {
	      pts[i] = new Point(0,0);
	      pts[i].x = (int) (Math.random() * 400 * 0.9 + 400 * 0.05);
	      pts[i].y = (int) (Math.random() * 400 * 0.8 + 400 * 0.05);
	      nvpts++;

	      show_point (offScreenGraphics, i, Color.blue);
	      draw_point_label (offScreenGraphics, i, 400);
	      pause(300);
	    }
	  }

	  void h_line (Graphics g, int i, Color c) {
	    g.setColor(c);
	    g.drawLine (0,pts[i].y,400,pts[i].y);
	  
	  }
	   void erase_h_line (int i) {
	    h_line (offScreenGraphics, i, getBackground());
	    show_point (offScreenGraphics, i, Color.blue);
	  }

	  int find_pivot () {
	    maxyi = 0;
	    h_line (offScreenGraphics, maxyi, Color.red);
	    draw_point_label (offScreenGraphics, maxyi, 400);

	    for (curpt=1, maxy=pts[maxyi].y; curpt<npts; curpt++) {

	      if (curpt-1 != maxyi) {
		erase_h_line(curpt-1);
		draw_point_label (offScreenGraphics, curpt-1, 400);
	      }

	      h_line (offScreenGraphics, curpt, Color.green);
	      draw_point_label (offScreenGraphics, curpt, 400);

	      if (pts[curpt].y > maxy) {
		erase_h_line (maxyi);
		draw_point_label (offScreenGraphics, maxyi, 400);

		maxy = pts[curpt].y;
		maxyi = curpt;

		h_line (offScreenGraphics, maxyi, Color.red);
		draw_point_label (offScreenGraphics, maxyi, 400);
	      }
	      pause(200);
	    }

	    return maxyi;
	  }

	  void display_points (Point pts[], int n) {
	    int i;

	    println (" i  x  y");
	    for (i=0; i<n; i++)
	      println (" " + i + " " + pts[i].x + " " + pts[i].y);
	  }
	    

	  void set_pivot () {
	    int pivoti;

	    pivoti = find_pivot ();
	    pivot = pts[pivoti];
	    swap (pts, 0, pivoti,false);
	    maxyi = 0;
	  }

	  void sort_points () {
	    quicksort (pts, 1, npts-1);
	  }

	  void draw_hull_edge (Graphics g, int i, Color c) {
	    g.setColor(c);
	    g.drawLine(hull[i].x,hull[i].y,hull[i+1].x,hull[i+1].y);
	  }

	  void erase_hull_edge (Graphics g, int i) {
	    int j;

	    draw_hull_edge(g,i,getBackground());
	    for (j=hulli[i]; j<hulli[i+1]; j++) {
	      g.setColor(Color.green);
	      g.drawLine(pts[j].x,pts[j].y,pts[j+1].x,pts[j+1].y);
	      show_point (g,j,Color.blue);
	    }
	    show_point (g,j,Color.blue);
	  }
	      
	  void make_hull () {
	    int i;

	    hull = new Point[npts];
	    hulli = new int[npts];

	    hull[0] = pts[0];
	    hulli[0] = 0;
	    hull[1] = pts[1];
	    hulli[1] = 1;
	    nhull = 2;
	    draw_hull_edge(offScreenGraphics, 0, Color.red);

	    pause (400);
	    
	    for (i=2; i<npts; i++) {
	      while (ccw(hull[nhull-2], hull[nhull-1], pts[i])<0.0) {
		erase_hull_edge(offScreenGraphics, nhull-2);
		erase_point_label(offScreenGraphics, hulli[nhull-1],500);
		nhull--;
		pause(400);
	      }
	      hulli[nhull] = i;
	      hull[nhull++] = pts[i];
	      draw_hull_edge(offScreenGraphics, nhull-2, Color.red);
	      show_hull_point(offScreenGraphics, nhull-1, Color.red);
	      pause(400);
	    }
	  }

	  public void run() {
	    phase = 0;
	    pause (1000);

	    phase = 1;
	    repaint();
	    showStatus("Initial points");
	    init_points ();
	    pause (2000);

	    phase = 2;
	    showStatus("Finding extreme point");
	    set_pivot ();
	    repaint();
	    pause (2000);

	    phase = 3;
	    repaint();
	    showStatus("Sorting about the extreme point");
	    sort_points ();
	    pause (2000);

	    repaint();
	    phase = 4;
	    showStatus("Scanning for the hull");
	    make_hull ();
	    pause (400);

	    phase = 5;
	    showStatus("Done");
	    repaint();
	  }

	  public void init() {
	    
	    phase = 0;

	    npts = Integer.parseInt(getParameter ("npts"));
	    nvpts = 0;

	    resize(600, 400);

	    myFont = new Font("Courier",Font.PLAIN,8);
	    FontMetrics fm = getFontMetrics(myFont);
	    PointLabelWidth   = fm.charWidth('X') * 9 + 1;
	    PointLabelHeight  = fm.getHeight();
	    PointLabelLeading = fm.getLeading() / 2;
	    AngleR = (PointLabelHeight+PointLabelLeading)/2;

	    setLayout(new BorderLayout());

	    Panel controls = new Panel();
	    StartB = new Button("Start");
	    controls.add(StartB);
	    PauseC = new Checkbox("Pause");
	    controls.add(PauseC);
	    add("North",controls);

	    canvas = new Canvas();
	    add("Center",canvas);
	    
	  }

	  public boolean action(Event evt, Object arg) {
	    if ("Start".equals(arg)) {
	      startAlg();
	      return true;
	    }
	    else {
//	       println ("event="+evt+", action="+arg);
	      return false;
	    }
	  }

	  public synchronized void stop() {
	    if (kicker != null) {
	      try {
		kicker.stop();
	      } catch (IllegalThreadStateException e) {
		// ignore this exception
	      }
	      kicker = null;
	    }
	  }

	  private synchronized void startAlg() {
	    if (kicker == null || !kicker.isAlive()) {
	      repaint();
	      kicker = new Thread(this);
	      kicker.start();
	    }
	  }

	  void pause (int milliseconds) {
	    while (PauseC.getState()) {
	      try {Thread.sleep (50);} catch (InterruptedException e){};
	    }
	    repaint();
	    try {Thread.sleep(milliseconds);} catch (InterruptedException e){};
	  }

	  void show_point (Graphics g, int i, Color c) {
	    g.setColor(c);
	    g.fillOval (pts[i].x-2,pts[i].y-2, 5,5);
	  }    

	  void show_hull_point (Graphics g, int i, Color c) {
	    g.setColor(c);
	    draw_point_label (g, hulli[i], 500);
	  }

	  void erase_angle (Graphics g, int i) {
	    g.clearRect (400+PointLabelWidth,
			 (i+1)*(PointLabelHeight+PointLabelLeading),
			 2*AngleR, 2*AngleR);
	  }		 

	  void show_angle (Graphics g, int i) {
	    int dx,dy;
	    double len,lx,ly;

	    g.setColor(Color.darkGray);
	    lx = pts[i].x - pivot.x;
	    ly = pts[i].y - pivot.y;
	    len = Math.sqrt (lx*lx+ly*ly);
	    dx = (int)(AngleR * lx/len);
	    dy = (int)(AngleR * ly/len);
	    g.drawLine (400+PointLabelWidth+AngleR+dx,
			(i+1)*(PointLabelHeight+PointLabelLeading)+AngleR+dy,
			400+PointLabelWidth+AngleR-dx,
			(i+1)*(PointLabelHeight+PointLabelLeading)+AngleR-dy);
	  }    

	  void show_points (Graphics g) {
	    int i;

	    g.setColor(Color.blue);
	    g.drawString ("Points",400,PointLabelHeight);

	    for (i=0; i<nvpts; i++) {
	      show_point (g,i,Color.blue);
	      draw_point_label (g, i, 400);
	    }
	  }


	  void show_maxy (Graphics g) {
	    if (curpt >= 0 && curpt < npts) {
	      h_line (g,curpt, Color.green);
	      show_point (g,curpt,Color.green);
	      draw_point_label (g, curpt, 400);
	    }
	    h_line (g, maxyi, Color.red);
	    show_point (g,maxyi,Color.red);
	    draw_point_label (g, maxyi, 400);
	  }

	  void show_spokes (Graphics g) {
	    int i,x[],y[];

	    x = new int[npts];
	    y = new int[npts];
	    g.setColor(Color.darkGray);
	    for (i=0; i<npts; i++)
	      g.drawLine (pivot.x,pivot.y, pts[i].x,pts[i].y);
	  }

	  void show_angles (Graphics g) {
	    int i;

	    g.setColor(Color.darkGray);
	    for (i=0; i<npts; i++)
	      show_angle (g,i);
	  }

	  void show_comparison (Graphics g ) {
	    g.setColor(Color.red);
	    g.drawLine(p1.x,p1.y,p2.x,p2.y);
	    g.drawLine(p2.x,p2.y,p3.x,p3.y);
	  }

	  void show_star (Graphics g) {
	    int i,x[],y[];

	    x = new int[npts+1];
	    y = new int[npts+1];
	    g.setColor(Color.green);
	    for (i=0; i<npts; i++) {
	      x[i] = pts[i].x;
	      y[i] = pts[i].y;
	    }
	    x[npts] = pts[0].x;
	    y[npts] = pts[0].y;
	    g.drawPolygon(x,y,npts+1);
	  }

	  void show_hull (Graphics g) {
	    int i,x[],y[];

	    x = new int[nhull+1];
	    y = new int[nhull+1];

	    g.setColor(Color.red);
	    g.drawString ("Hull",500,PointLabelHeight);

	    for (i=0; i<nhull; i++) {
	      x[i] = hull[i].x;
	      y[i] = hull[i].y;
	      show_hull_point (g,i,Color.red);
	    }
	    if (phase == 5) {
	      x[nhull] = hull[0].x;
	      y[nhull] = hull[0].y;
	      g.drawPolygon(x,y,nhull+1);      
	    }
	    else
	      g.drawPolygon(x,y,nhull);
	  } 

	  public void show_all(Graphics g) {
	    g.clearRect(0,0,600,400);
	    switch (phase) {
	    case 0:
	      break;
	    case 1:
	      show_points (g);
	      break;
	    case 2:
	      show_points (g);
	      show_maxy (g);
	      break;
	    case 3:
	      show_points (g);
	      show_spokes (g);
	      show_angles (g);
	      break;
	    case 4:
	      show_points (g);
	      show_star (g);
	      show_hull (g);
	      break;
	    case 5:
	      show_points (g);
	      show_hull (g);
	      break;
	    default:
	      break;
	    }
	  }

	  public void paint(Graphics g) {
	    if (offScreenImage == null) {
	      Dimension d = size();
	      offScreenImage = createImage(d.width,d.height);
	      offScreenSize = d;
	      offScreenGraphics = offScreenImage.getGraphics();
	      offScreenGraphics.clearRect(0,0,d.width,d.height);
	      offScreenGraphics.setFont(myFont);
	    }
	    show_all(offScreenGraphics);
	    Graphics canvasGraphics = canvas.getGraphics();
	    canvasGraphics.drawImage(offScreenImage,0,0,this);
	  }
	}
*/
