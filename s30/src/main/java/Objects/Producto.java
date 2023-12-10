package Objects;

import java.time.LocalDate;

/**
 * La clase Producto representa un producto que puede comprarse en un
 * supermercado.
 * Cada producto tiene varios atributos como nombre, precio, código de barras,
 * etc.
 */
public class Producto {

    // Atributos
    private String nombre;
    private double precio;
    private String marca;
    private int cantidadEnStock;
    private LocalDate fechaCaducidad;
    private boolean perecedero;
    private String categoria;
    private String descripcion;

    /**
     * Constructor de la clase Producto.
     *
     * @param nombre          El nombre del producto.
     * @param precio          El precio del producto.
     * @param marca           La marca del producto.
     * @param cantidadEnStock La cantidad disponible en stock.
     * @param fechaCaducidad  La fecha de caducidad del producto.
     * @param perecedero      Indica si el producto es perecedero o no.
     * @param categoria       La categoría a la que pertenece el producto.
     * @param descripcion     Una breve descripción del producto.
     */
    public Producto(String nombre, double precio, String marca, int cantidadEnStock,
            LocalDate fechaCaducidad, boolean perecedero, String categoria,
            String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
        this.cantidadEnStock = cantidadEnStock;
        this.fechaCaducidad = fechaCaducidad;
        this.perecedero = perecedero;
        this.categoria = categoria;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el nombre del producto.
     *
     * @return El nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     *
     * @param nombre - El nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el precio del producto.
     *
     * @return El precio del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     *
     * @param precio - El nuevo precio.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la marca del producto.
     *
     * @return La marca del producto.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Establece la marca del producto.
     *
     * @param marca - La marca del producto.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtiene la cantidad del producto en stock.
     *
     * @return La cantidad del producto en stock.
     */
    public int getCantidadEnStock() {
        return cantidadEnStock;
    }

    /**
     * Establece la cantidad del producto en stock.
     *
     * @param cantidadEnStock - La cantidad en stock.
     */
    public void setCantidadEnStock(int cantidadEnStock) {
        this.cantidadEnStock = cantidadEnStock;
    }

    /**
     * Obtiene la fecha de caducidad del producto.
     *
     * @return La fecha de caducidad del producto. Null si no está establecida o no
     *         se
     *         puede determinar.
     */
    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    /**
     * Establece la fecha de caducidad del producto.
     *
     * @param fechaCaducidad - La fecha de caducidad del producto.
     */
    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    /**
     * Indica si el producto es perecedero.
     *
     * @return True si el producto es perecedero.
     */
    public boolean esPerecedero() {
        return perecedero;
    }

    /**
     * Establece si el producto es perecedero o no.
     *
     * @param perecedero - True si el producto es perecedero.
     */
    public void setPerecedero(boolean perecedero) {
        this.perecedero = perecedero;
    }

    /**
     * Obtiene la categoría del producto.
     *
     * @return La categoría del producto.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría del producto.
     *
     * @param categoria - La categoría del producto.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene la descripción del producto.
     *
     * @return Cadena con la descripción del producto.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del producto.
     *
     * @param descripcion - Cadena con la descripción del producto.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Método para verificar si el producto está cerca de caducar.
     *
     * @return boolean true si el producto está cerca de caducar.
     */
    public boolean estaCercaDeCaducar() {
        // Suponiendo un umbral de 7 días para simplificar
        return fechaCaducidad != null && LocalDate.now().plusDays(7).isAfter(fechaCaducidad);
    }

    /**
     * Método para obtener el estado del stock.
     *
     * @return Cadena con el estado del producto.
     */
    public String getEstadoStock() {
        if (cantidadEnStock < 10) {
            return "Bajo";
        } else if (cantidadEnStock < 50) {
            return "Medio";
        } else {
            return "Alto";
        }
    }

    /**
     * Método para ajustar el precio dinámicamente.
     *
     * @param porcentajeDescuento
     */
    public void aplicarDescuento(double porcentajeDescuento) {
        this.precio = this.precio - (this.precio * (porcentajeDescuento / 100));
    }

    /**
     * Devuelve una representación en cadena de este Producto.
     * La representación en cadena incluye: Nombre, Precio, Código de barras, Marca,
     * Stock, Fecha de caducidad, Perecedero, Categoría y Descripción.
     *
     * @return Una representación en cadena de este Producto.
     */
    @Override
    public String toString() {
        return String.format("Nombre: %s\nPrecio: $%.2f\nMarca: %s\nStock: %d\n"
                + "Fecha de Caducidad: %s\nPerecedero: %b\nCategoría: %s\n"
                + "Descripción: %s",
                nombre, precio, marca, cantidadEnStock,
                fechaCaducidad, perecedero, categoria, descripcion);
    }
}
