package es.upsam.dsm.icsypb_android.utilities;;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;


/**
 * BluetoothDiscovery
 *
 * @brief Clase para gestionar el discover de dispositivos Bluetooth
 * @author Kr0n0
 *
 * Referencias :    http://stackoverflow.com/questions/29122031/how-can-i-show-the-scanned-bluetooth-devices-in-the-listview
 *                  https://github.com/ArcSung/android_arduino_IRStopwatch/blob/master/src/com/arc/android_timer/BluetoothDiscovery.java
 *
 */
public class BluetoothDiscovery {
    // ATRIBUTOS DE CLASE
    ArrayList<String> BluetoothArray = new ArrayList<String>();
    private BluetoothAdapter mBTA;
    private SingBroadcastReceiver mReceiver;
    String ownMAC;
    SimpleDateFormat sdf;

    // Constructor
    public BluetoothDiscovery(Activity activity, ArrayList<String> alMACs)
    {
        sdf=new SimpleDateFormat("dd/MM/YYYY hh:mm:ss");
        mBTA = BluetoothAdapter.getDefaultAdapter();
        // 1 - Recogemos la propia MAC Address antes de hacer nada
        setOwnMAC(mBTA.getAddress());
        // 2 - Empezamos el discover
        BluetoothArray = alMACs;
        // 3 - Si está escanenado, cancelamos
        if(mBTA.isDiscovering()) mBTA.cancelDiscovery();
        mBTA.startDiscovery();
        mReceiver = new SingBroadcastReceiver();
        IntentFilter ifilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        activity.registerReceiver(mReceiver, ifilter);
    }

    // The BroadcastReceiver that listens for discovered devices and
    // changes the title when discovery is finished
    private class SingBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            String mac_actual;
            Boolean encontrado = false;

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Si el dispositivo no está asociado de serie
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    // Si está en el array añadimos
                    mac_actual = device.getAddress();
                    encontrado = buscarArray(BluetoothArray, mac_actual);
                    if (encontrado = true) {

                    /* Añadimos los campos al objeto Tracking que creemos
                        String mac_usuario :: ownMAC;
                        String mac_baliza :: mac_actual;
                        String fecha_actual :: fecha_actual=sdf.format(date);;
                        int id_baliza :: ;
                        int id_ruta; */
                        Log.d("[BluetoothDiscovery]", "Dispositivo encontrado para añadir - " + mac_actual);
                    }
                }
            }
        }
    }

    /**
     * buscarArray
     *
     * @brief Busca la MAC Address en el ArrayList
     * @param alMACs
     * @param cadena
     * @return true si encontrado, false si no encontrado
     */
    public boolean buscarArray(ArrayList<String> alMACs, String cadena) {
        boolean resultado = false;

        // 1 - Recorremos el ArrayList de cadenas
        for (String i : alMACs) {
            // 2 - Si la MAC es igual a la que tenemos
            if (i.matches(cadena)) {
                resultado = true;
                break;
            }
        }
        return (resultado);
    }

    public void startBTScan () {
        if (!(mBTA.isDiscovering()))
            mBTA.startDiscovery();
    }

    public void stopBTScan () {
        if (mBTA.isDiscovering())
            mBTA.cancelDiscovery();
    }

    public String getOwnMAC() {
        return ownMAC;
    }

    public void setOwnMAC(String ownMAC) {
        this.ownMAC = ownMAC;
    }

}