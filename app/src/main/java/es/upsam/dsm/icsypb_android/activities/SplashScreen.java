package es.upsam.dsm.icsypb_android.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.ExecutionException;

import es.upsam.dsm.icsypb_android.R;
import es.upsam.dsm.icsypb_android.controller.Singleton;

/**
 * SplashScreen
 *
 * @brief Activity inicial tipo SplashScreen que instancia el Singleton
 * @author Kr0n0
 *
 * Referencias :    http://www.androidhive.info/2013/07/how-to-implement-android-splash-screen-2/
 *                  http://devtroce.com/2012/01/19/mensajes-de-dialogo-popup-alertdialog/
 */
public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean bOK = false;
        Intent i;

        //TODO Cargar imagen de fondo

        // 1 - Visualizamos el activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // 2 - Instanciamos Singleton con el contexto de aplicación
        Singleton datos = Singleton.getInstance(this);

        // 3 - Comprobamos la conexión a Internet
        bOK = datos.comprobarConexion();
        Log.d("[SplashScreen]", "comprobarConexion() - Devuelve " + bOK);
        //TODO Si false Mensaje de error con boton OK y salida de aplicación

        // 4 - Recibimos las rutas
        try {
            bOK = datos.recibirRutas();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("[SplashScreen]", "recibirRutas() - Devuelve " + bOK);
        //TODO Si false Mensaje de error con boton OK y salida de aplicación

        // 5 - Cargar el siguiente activity
        i = new Intent(this, RutasActivity.class);
        startActivity(i);
    }

}
