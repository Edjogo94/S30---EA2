package Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * La clase Cajera representa a una cajera que realiza el proceso de cobro en el
 * supermercado.
 */
public class Cajera implements Runnable {

    private String nombre;
    private List<ClienteProducto> clientesEnCola;
    private long tiempoTotalCobro;
    private List<String> registroInteracciones;

    public Cajera(String nombre, List<ClienteProducto> clientesEnCola) {
        this.nombre = nombre;
        this.clientesEnCola = clientesEnCola;
        this.tiempoTotalCobro = 0;
        this.registroInteracciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Procesa el cobro para cada cliente en la cola.
     */
    @Override
    public void run() {
        for (ClienteProducto clienteProducto : clientesEnCola) {
            procesarCobro(clienteProducto);
        }
    }

    /**
     * Obtiene el tiempo total que tomó realizar todos los cobros.
     *
     * @return El tiempo total de cobro.
     */
    public long getTiempoTotalCobro() {
        return tiempoTotalCobro;
    }

    /**
     * Obtiene el registro de interacciones con los clientes.
     *
     * @return El registro de interacciones.
     */
    public List<String> getRegistroInteracciones() {
        return registroInteracciones;
    }

    private void registrarInteraccion(String mensaje) {
        registroInteracciones.add(mensaje);
    }

    /**
     * Simula el proceso de cobro para un cliente específico.
     *
     * @param clienteProducto El cliente y sus productos a cobrar.
     */
    private void procesarCobro(ClienteProducto clienteProducto) {
        if (clienteProducto.intentarAtender()) {
            try {
                registrarInteraccion(nombre + ": Inicia el servicio a " + clienteProducto.getCliente().getNombre());

                // Simula escanear productos y calcular el monto total
                double montoTotal = clienteProducto.aplicarBeneficiosDeMembresia();
                registrarInteraccion(
                        nombre + ": Monto total para " + clienteProducto.getCliente().getNombre() + ": $" + montoTotal);

                // Simula el tiempo que toma realizar el cobro
                try {
                    Thread.sleep(1000 * clienteProducto.getProductosComprados().size()); // Simula 1 segundo por
                                                                                         // producto
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Actualiza el tiempo total de cobro
                tiempoTotalCobro += clienteProducto.getProductosComprados().size();

                registrarInteraccion(nombre + ": Finaliza el servicio a " + clienteProducto.getCliente().getNombre());
            } finally {
                clienteProducto.finalizarAtencion();
            }
        }
    }
}
