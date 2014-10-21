package dia.upm.cconvexo.cconvexomap.gestores;

import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

/**
 * Created by ivan on 14/09/14.
 */
public class GestorConexionHttp {

    private static HttpClient instancia = null;
    private final String keyApi = "AIzaSyD4u_Xd7vRiQnyoDQEizAg2HAzHyyRA4Rs";


    public static HttpClient getInstancia()
    {
        if ( instancia == null)
        {
            instancia = new DefaultHttpClient();
        }
        assert  instancia != null;
        return instancia;
    }
}
