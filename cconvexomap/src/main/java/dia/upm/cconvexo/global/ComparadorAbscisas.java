package dia.upm.cconvexo.global;

import java.util.Comparator;

import dia.upm.cconvexo.model.Punto;

public class ComparadorAbscisas implements Comparator<Punto> {

	@Override
	public int compare(Punto o1, Punto o2) {
		// TODO Auto-generated method stub
		assert o1 != null && o2 != null;
		if (o1.getX()<o2.getX())
		{
			return -1; // o1 es menor que o2
		}
		else if (o1.getX()>o2.getX())
		{
			return 1; // o1 es mayor que o2
		}
		else //o1 = o2
		{
			return 0;
		}
	}

}
