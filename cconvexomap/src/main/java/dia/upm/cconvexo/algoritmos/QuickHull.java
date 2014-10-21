//package dia.upm.cconvexo.algoritmos;
//
//import java.awt.Point;
//import java.util.ArrayList;
//import java.util.List;
//
//import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
//import dia.upm.cconvexo.model.Arista;
//import dia.upm.cconvexo.model.Punto;
//
//public class QuickHull {
//
//	public static ArrayList<Punto> start(ArrayList<Punto> points) {
//		ArrayList<Punto> convexHull = new ArrayList<Punto>();
//		if (points.size() < 3) return (ArrayList)points.clone();
//		// find extremals
//		int minPoint = -1, maxPoint = -1;
//		int minX = Integer.MAX_VALUE;
//		int maxX = Integer.MIN_VALUE;
//		for (int i = 0; i < points.size(); i++) {
//			if (points.get(i).getPoint().x < minX) {
//				minX = points.get(i).getPoint().x;
//				minPoint = i;
//			}
//			if (points.get(i).getPoint().x > maxX) {
//				maxX = points.get(i).getPoint().x;
//				maxPoint = i;
//			}
//		}
//		Punto A = points.get(minPoint);
//		Punto B = points.get(maxPoint);
//		convexHull.add(A);
//		convexHull.add(B);
//		points.remove(A);
//		points.remove(B);
//
//		ArrayList<Punto> leftSet = new ArrayList<Punto>();
//		ArrayList<Punto> rightSet = new ArrayList<Punto>();
//
//		for (int i = 0; i < points.size(); i++) {
//			Point p = points.get(i).getPoint();
//			if (pointLocation(A.getPoint(),B.getPoint(),p) == -1)
//				leftSet.add(points.get(i));
//			else
//				rightSet.add(points.get(i));
//		}
//		hullSet(A.getPoint(),B.getPoint(),rightSet,convexHull);
//		hullSet(B.getPoint(),A.getPoint(),leftSet,convexHull);
//
//		return convexHull;
//	}
//
//
//	public static void start2(List<Punto> points) {
//
//		if (points.size() < 3) return;
//		// find extremals
//		int minPoint = -1, maxPoint = -1;
//		int minX = Integer.MAX_VALUE;
//		int maxX = Integer.MIN_VALUE;
//		for (int i = 0; i < points.size(); i++) {
//			if (points.get(i).getPoint().x < minX) {
//				minX = points.get(i).getPoint().x;
//				minPoint = i;
//			}
//			if (points.get(i).getPoint().x > maxX) {
//				maxX = points.get(i).getPoint().x;
//				maxPoint = i;
//			}
//		}
//		Punto A = points.get(minPoint);
//		Punto B = points.get(maxPoint);
//
//		GestorConjuntoConvexo.getInstancia().anadeArista(new Arista(A,B));
//		points.remove(A);
//		points.remove(B);
//
//		ArrayList<Punto> leftSet = new ArrayList<Punto>();
//		ArrayList<Punto> rightSet = new ArrayList<Punto>();
//
//		for (int i = 0; i < points.size(); i++) {
//			Point p = points.get(i).getPoint();
//			if (pointLocation(A.getPoint(),B.getPoint(),p) == -1)
//				leftSet.add(points.get(i));
//			else
//				rightSet.add(points.get(i));
//		}
//		hullSet2(A.getPoint(),B.getPoint(),rightSet);
//		hullSet2(B.getPoint(),A.getPoint(),leftSet);
//
//		return ;
//	}
//
//
//*
//	 * Computes the square of the distance of point C to the segment defined by points AB
//	 * @param A
//	 * @param B
//	 * @param C
//	 * @return
//
//
//	public static int distance(Point A, Point B, Point C) {
//		int ABx = B.x-A.x;
//		int ABy = B.y-A.y;
//		int num = ABx*(A.y-C.y)-ABy*(A.x-C.x);
//		if (num < 0) num = -num;
//		return num;
//	}
//
//	public static void hullSet(Point A, Point B, ArrayList<Punto> set, ArrayList<Punto> hull) {
//		int insertPosition = hull.indexOf(B);
//		if (set.size() == 0) return;
//		if (set.size() == 1) {
//			Punto p = set.get(0);
//			set.remove(p);
//			hull.add(insertPosition,p);
//			return;
//		}
//		int dist = Integer.MIN_VALUE;
//		int furthestPoint = -1;
//		for (int i = 0; i < set.size(); i++) {
//			Point p = set.get(i).getPoint();
//			int distance  = distance(A,B,p);
//			if (distance > dist) {
//				dist = distance;
//				furthestPoint = i;
//			}
//		}
//		Punto P = set.get(furthestPoint);
//		set.remove(furthestPoint);
//		GestorConjuntoConvexo.getInstancia().anadeArista(new Arista(new Punto(B),P));
//
//		// Determine who's to the left of AP
//		ArrayList<Punto> leftSetAP = new ArrayList<Punto>();
//		for (int i = 0; i < set.size(); i++) {
//			Point M = set.get(i).getPoint();
//			if (pointLocation(A,P.getPoint(),M)==1) {
//				//set.remove(M);
//				leftSetAP.add(set.get(i));
//			}
//		}
//
//		// Determine who's to the left of PB
//		ArrayList<Punto> leftSetPB = new ArrayList<Punto>();
//		for (int i = 0; i < set.size(); i++) {
//			Point M = set.get(i).getPoint();
//			if (pointLocation(P.getPoint(),B,M)==1) {
//				//set.remove(M);
//				leftSetPB.add(set.get(i));
//			}
//		}
//
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		hullSet2(A,P.getPoint(),leftSetAP);
//		hullSet2(P.getPoint(),B,leftSetPB);
//
//	}
//
//	public static void hullSet2(Point A, Point B, ArrayList<Punto> set) {
//
//		GestorConjuntoConvexo hull = GestorConjuntoConvexo.getInstancia();
//
//		if (set.size() == 0) return;
//		if (set.size() == 1) {
//			Punto p = set.get(0);
//			set.remove(p);
//			hull.anadeArista(new Arista(new Punto(A),p));
//			return;
//		}
//		int dist = Integer.MIN_VALUE;
//		int furthestPoint = -1;
//		for (int i = 0; i < set.size(); i++) {
//			Point p = set.get(i).getPoint();
//			int distance  = distance(A,B,p);
//			if (distance > dist) {
//				dist = distance;
//				furthestPoint = i;
//			}
//		}
//		Punto P = set.get(furthestPoint);
//		set.remove(furthestPoint);
//		hull.anadeArista(new Arista(new Punto(A),P));
//
//		// Determine who's to the left of AP
//		ArrayList<Punto> leftSetAP = new ArrayList<Punto>();
//		for (int i = 0; i < set.size(); i++) {
//			Point M = set.get(i).getPoint();
//			if (pointLocation(A,P.getPoint(),M)==1) {
//				//set.remove(M);
//				leftSetAP.add(set.get(i));
//			}
//		}
//
//		// Determine who's to the left of PB
//		ArrayList<Punto> leftSetPB = new ArrayList<Punto>();
//		for (int i = 0; i < set.size(); i++) {
//			Point M = set.get(i).getPoint();
//			if (pointLocation(P.getPoint(),B,M)==1) {
//				//set.remove(M);
//				leftSetPB.add(set.get(i));
//			}
//		}
//		hullSet2(A,P.getPoint(),leftSetAP);
//		hullSet2(P.getPoint(),B,leftSetPB);
//
//	}
//	public static int pointLocation(Point A, Point B, Point P) {
//		int cp1 = (B.x-A.x)*(P.y-A.y) - (B.y-A.y)*(P.x-A.x);
//		return (cp1>0)?1:-1;
//	}
//}
