package Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase ClienteProducto representa la relación entre un cliente y
 * los productos comprados. Cada instancia de esta clase contiene información
 * sobre los productos comprados por un cliente.
 */
public class ClienteProducto {

    // Atributos
    private Cliente cliente;
    private boolean estaSiendoAtendido;
    private List<Producto> productosComprados;

    /**
     * Constructor de la clase ClienteProducto.
     *
     * @param cliente El cliente asociado a la compra.
     */
    public ClienteProducto(Cliente cliente) {
        this.cliente = cliente;
        this.productosComprados = new ArrayList<>();
    }

    /**
     * Obtiene el cliente asociado a la compra.
     *
     * @return El cliente asociado a la compra.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Obtiene la lista de productos comprados por el cliente.
     *
     * @return La lista de productos comprados.
     */
    public List<Producto> getProductosComprados() {
        return productosComprados;
    }

    /**
     * Agrega un producto a la lista de productos comprados por el cliente.
     *
     * @param producto El producto a agregar a la lista.
     */
    public void agregarProducto(Producto producto) {
        productosComprados.add(producto);
    }

    /**
     * Calcula el monto total de la compra sumando los precios de los productos.
     *
     * @return El monto total de la compra.
     */
    public double calcularMontoTotal() {
        double montoTotal = 0.0;
        for (Producto producto : productosComprados) {
            montoTotal += producto.getPrecio();
        }
        return montoTotal;
    }

    /**
     * Método para aplicar los beneficios de membresía.
     * 
     * @return Los beneficios de membresía para la compra sumando los precios de los
     *         productos.
     */
    public double aplicarBeneficiosDeMembresia() {
        // 10% de descuento para los miembros
        return cliente.tieneMembresia() ? calcularMontoTotal() * 0.9 : calcularMontoTotal();
    }
    
    /**
     *  verifica si el cliente esta siendo atendido
     * @return boolean
     */
    public synchronized boolean intentarAtender() {
        if (!estaSiendoAtendido) {
            estaSiendoAtendido = true;
            return true;
        }
        return false;
    }

    /**
     *  finaliza el proceso de atencion
     */
    public synchronized void finalizarAtencion() {
        estaSiendoAtendido = false;
    }
}
