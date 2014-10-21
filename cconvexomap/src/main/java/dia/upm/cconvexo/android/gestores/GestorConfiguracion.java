package dia.upm.cconvexo.android.gestores;

import android.util.Log;



/**
 * Created by ivan on 22/03/14.
 */
public class GestorConfiguracion {


//    static String PASO_PASO = "Paso a Paso";
//    static String DIRECTO = "Directo";
//    static String RETARDO = "Retardo";

    static GestorConfiguracion instancia = null;
    private int tipoEjecucion =1;
    private int seconds = 5;
    private int franjas;

    public int getTipoEjecucion() {
        Log.d(this.getClass().getName(),"getTipoEjecucion" + tipoEjecucion);
        return tipoEjecucion;
    }

    public void setTipoEjecucion(int tipoEjecucion) {
        Log.d(this.getClass().getName(),"SetTipoEjecucion" + tipoEjecucion);
        this.tipoEjecucion = tipoEjecucion;
    }

    public int getSeconds() {



        Log.d(this.getClass().getName(),"getSeconds" + seconds);
        return seconds;
    }

    public void setSeconds(int seconds) {
        Log.d(this.getClass().getName(),"SetSeconds" + seconds);
        this.seconds = seconds;
    }

    private GestorConfiguracion()
    {

    }

    public static GestorConfiguracion getInstancia ()
    {
        Log.d("GestorConfiguracion", "getInstancia");
        if (instancia == null)
        {
            instancia = new GestorConfiguracion();
        }
        assert instancia != null;
        return instancia;
    }


    public void setFranjas(int franjas) {
        this.franjas = franjas;
    }

    public int getFranjas() {
        return franjas;
    }
}
