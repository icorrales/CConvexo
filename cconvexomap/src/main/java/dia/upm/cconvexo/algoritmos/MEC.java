package dia.upm.cconvexo.algoritmos;

import java.util.List;

import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;
import dia.upm.cconvexo.interfaces.IAlgoritmoHullConvex;
import dia.upm.cconvexo.model.Arista;
import dia.upm.cconvexo.model.Circle;
import dia.upm.cconvexo.model.Punto;

/**
 * Created by ivan on 22/11/14.
 */
public class MEC implements IAlgoritmoHullConvex {

//    public final static String nombre = GestorMensajes.getInstancia().getResourceString(R.string.MEC);
    protected boolean inGmaps = false;

    @Override
    public void start(int delay) {
        if (GestorConjuntoConvexo.getInstancia().getConjuntoConvexo().size() > 0)
        {
            initMEC();
        }

    }

    /**
     * Method to calculate the minimum enclose circle of this ConvexHull.
     */
    private void initMEC() {
        Circle circleMin = null;
        List<Arista> cconvexo= GestorConjuntoConvexo.getInstancia().getConjuntoConvexo();

        if (cconvexo.size() <= 2)
        {
            Arista a1 = cconvexo.get(0);
            circleMin = new Circle(a1.getDestino(),a1.getOrigen());
//            GestorMensajes.getInstancia().addMessage(R.string.mec_1);
            GestorConjuntoConvexo.getInstancia().setMEC(circleMin);

        }
        else
        {
        List<Punto> cconvexoPuntos = GestorConjuntoConvexo.getInstancia().getConjuntoConvexoPuntos();



        for (int i = 0; i < cconvexo.size(); i++) {
            Arista a1 = cconvexo.get(i);
            for (int j = 0; j < cconvexoPuntos.size(); j++) {
                Punto p = cconvexoPuntos.get(j);
                if (a1.contains(p) == false )
                {
                    Circle circle = new Circle(p,a1.getOrigen(),a1.getDestino(),inGmaps);
                    GestorConjuntoConvexo.getInstancia().setCircleTmp(circle);
                    if (circle.contains(cconvexoPuntos))
                    {
                        if ( circleMin == null)
                        {
                            circleMin = circle;
//                          GestorMensajes.getInstancia().addMessage(R.string.mec_1);
                            GestorConjuntoConvexo.getInstancia().setMEC(circle);


                        }
                        else
                        {
                            double areaCircle = circle.area();
                            if (areaCircle < circleMin.area())
                            {
                                circleMin = circle;
//                                GestorMensajes.getInstancia().addMessage(R.string.mec_1);
                                GestorConjuntoConvexo.getInstancia().setMEC(circleMin);
                            }
                            else
                            {
//                                GestorMensajes.getInstancia().addMessage(R.string.mec_2);
                                GestorConjuntoConvexo.getInstancia().anadePuntoGrafico(null);
                            }

                        }
                    }
                    else
                    {
//                        GestorMensajes.getInstancia().addMessage(R.string.mec_3);
                        GestorConjuntoConvexo.getInstancia().anadePuntoGrafico(null);
                    }


                }

            }

        }

        }
        GestorConjuntoConvexo.getInstancia().setCircleTmp(null);
    }

    @Override
    public void nextstep() {

    }


    public void init() {
        GestorConjuntoConvexo.getInstancia().setMEC(null);
        GestorConjuntoConvexo.getInstancia().setCircleTmp(null);

//        GestorMensajes.getInstancia().getHistoricoMensajes().clear();
    }

}
