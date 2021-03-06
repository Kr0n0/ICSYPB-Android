package es.upsam.dsm.icsypb_android.entities;

/**
 * Baliza
 *
 * @brief Clase Entity para gestionar la tabla Baliza
 * @author Kr0n0
 *
 * CREATE TABLE IF NOT EXISTS `balizas` (
 * `IDBALIZA` int(11) NOT NULL,
 * `MAC` varchar(17) COLLATE utf8_spanish_ci NOT NULL,
 * `POSICION` int(11) NOT NULL,
 * `TEXTO_ID` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
 * `ID_CONTACTO` int(11) NOT NULL,
 * `ESTROPEADO` int(1) NOT NULL,
 * `MAIL` varchar(50) COLLATE utf8_spanish_ci NOT NULL)
 */
public class Baliza {
    // ATRIBUTOS DE CLASE - CAMPOS DE LA TABLA
    int id;
    String texto_id;
    String mac;
    String posicion;
    int id_contacto;
    int estropeado;
    String mail;

    // CONSTRUCTOR BASE - SIN PARAMETROS
    public Baliza() {
        this.id = 0;
        this.texto_id = "";
        this.mac = "";
        this.posicion = "";
        this.id_contacto = 0;
        this.estropeado = 0;
        this.mail = "";
    }

    /**
     * Baliza
     *
     * @brief Constructor con parámetros
     * @param id    Identificador de Baliza
     * @param texto Texto de Baliza
     * @param mac   MAC Address de Baliza
     * @param posicion  Posición de Baliza
     * @param id_contacto   Identificador del contacto de Baliza
     * @param estropeado    Flag si estropeado o no
     * @param mail  Correo de la baliza
     */
    public Baliza(int id, String texto, String mac, String posicion, int id_contacto, int estropeado, String mail) {
        this.id = id;
        this.texto_id = texto;
        this.mac = mac;
        this.posicion = posicion;
        this.id_contacto = id_contacto;
        this.estropeado = estropeado;
        this.mail = mail;
    }

    /****************************************
         Getters y Setters de los campos
     ****************************************/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto_id;
    }

    public void setTexto(String texto_id) {
        this.texto_id = texto_id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getId_contacto() {
        return id_contacto;
    }

    public void setId_contacto(int id_contacto) {
        this.id_contacto = id_contacto;
    }

    public int getEstropeado() {
        return estropeado;
    }

    public void setEstropeado(int estropeado) {
        this.estropeado = estropeado;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
