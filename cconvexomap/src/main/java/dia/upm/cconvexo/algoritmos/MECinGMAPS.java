package dia.upm.cconvexo.algoritmos;

import dia.upm.cconvexo.gestores.GestorConjuntoConvexo;

/**
 * Created by ivan on 12/04/15.
 */
public class MECinGMAPS extends MEC {

    public void init() {
        GestorConjuntoConvexo.getInstancia().setMEC(null);
        GestorConjuntoConvexo.getInstancia().setCircleTmp(null);
        this.inGmaps = true;

//        GestorMensajes.getInstancia().getHistoricoMensajes().clear();
    }
}
