package Objects;

/**
 * La clase Cliente representa a un cliente que realiza compras en el supermercado.
 * Cada cliente tiene un nombre, un número de identificación y puede tener una membresía.
 */
public class Cliente {

    // Atributos
    private String nombre;
    private String numeroIdentificacion;
    private boolean tieneMembresia;

    /**
     * Constructor de la clase Cliente.
     *
     * @param nombre               El nombre del cliente.
     * @param numeroIdentificacion El número de identificación del cliente.
     * @param tieneMembresia       Indica si el cliente tiene membresía o no.
     */
    public Cliente(String nombre, String numeroIdentificacion, boolean tieneMembresia) {
        this.nombre = nombre;
        this.numeroIdentificacion = numeroIdentificacion;
        this.tieneMembresia = tieneMembresia;
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param nombre El nuevo nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el número de identificación del cliente.
     *
     * @return El número de identificación del cliente.
     */
    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    /**
     * Establece el número de identificación del cliente.
     *
     * @param numeroIdentificacion El nuevo número de identificación del cliente.
     */
    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
    }

    /**
     * Verifica si el cliente tiene membresía.
     *
     * @return true si el cliente tiene membresía, false en caso contrario.
     */
    public boolean tieneMembresia() {
        return tieneMembresia;
    }

    /**
     * Establece si el cliente tiene membresía.
     *
     * @param tieneMembresia true si el cliente tiene membresía, false en caso contrario.
     */
    public void setTieneMembresia(boolean tieneMembresia) {
        this.tieneMembresia = tieneMembresia;
    }

    /**
     * Devuelve una representación en cadena del cliente.
     *
     * @return Una cadena que representa al cliente con sus atributos.
     */
    @Override
    public String toString() {
        return String.format("Nombre: %s\nNúmero de Identificación: %s\nTiene Membresía: %b",
                nombre, numeroIdentificacion, tieneMembresia);
    }
}
