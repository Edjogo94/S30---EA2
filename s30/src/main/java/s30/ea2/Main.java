package s30.ea2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Objects.Cajera;
import Objects.Cliente;
import Objects.ClienteProducto;
import Objects.Producto;

public class Main {

    /**
     *  el programa principal
     * @param args 
     */
    public static void main(String[] args) {
        // Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        // Lista de cajeros
        List<Cajera> cajeras = new ArrayList<>();

        // Opción para que el usuario elija
        System.out.println("Seleccione una opción:");
        System.out.println("1. Crear productos y clientes manualmente");
        System.out.println("2. Usar productos y clientes de prueba");
        int opcion = scanner.nextInt();

        if (opcion == 1) {
            // Crear productos y clientes manualmente
            List<ClienteProducto> clientesEnCola = new ArrayList<>();
            crearProductosYClientesManualmente(clientesEnCola, scanner);

            // Crear cajeras y asignar la lista de clientes
            System.out.println("Ingrese la cantidad de cajeras:");
            int cantidadCajeras = scanner.nextInt();
            for (int i = 1; i <= cantidadCajeras; i++) {
                cajeras.add(new Cajera("Cajera" + i, new ArrayList<>(clientesEnCola)));
            }
        } else if (opcion == 2) {
            // Usar productos y clientes de prueba
            List<ClienteProducto> clientesEnCola = new ArrayList<>();
            usarProductosYClientesDePrueba(clientesEnCola);

            // Crear cajeras y asignar la lista de clientes
            System.out.println("Ingrese la cantidad de cajeras:");
            int cantidadCajeras = scanner.nextInt();
            for (int i = 1; i <= cantidadCajeras; i++) {
                cajeras.add(new Cajera("Cajera" + i, new ArrayList<>(clientesEnCola)));
            }
        } else {
            System.out.println("Opción no válida. Saliendo del programa.");
            scanner.close();
            return;
        }

        // Cerrar el scanner
        scanner.close();

        // Crear hilos para las cajeras y empezar a trabajar
        List<Thread> hilosCajeras = new ArrayList<>();
        for (Cajera cajera : cajeras) {
            Thread hiloCajera = new Thread(cajera);
            hilosCajeras.add(hiloCajera);
            hiloCajera.start();
        }

        // Imprimir interacciones en tiempo real para todas las cajeras
        imprimirInteraccionesEnTiempoReal(cajeras, hilosCajeras);

        // Esperar a que todos los hilos terminen antes de imprimir el resumen
        for (Thread hiloCajera : hilosCajeras) {
            try {
                hiloCajera.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Imprimir tiempo total de cobro para cada cajera
        System.out.println("\nResumen de tiempo total de cobro por cajera:");
        for (Cajera cajera : cajeras) {
            System.out.println(cajera.getNombre() + ": " + cajera.getTiempoTotalCobro() + " segundos");
        }
    }

    /**
     * 
     * @param clientesEnCola
     * @param scanner 
     */
    private static void crearProductosYClientesManualmente(List<ClienteProducto> clientesEnCola, Scanner scanner) {
        System.out.println("Ingrese la cantidad de clientes:");
        int cantidadClientes = scanner.nextInt();
        scanner.nextLine(); // Consumir la línea después de leer un entero

        for (int i = 0; i < cantidadClientes; i++) {
            System.out.println("Ingrese el nombre del cliente " + (i + 1) + ":");
            String nombreCliente = scanner.nextLine();
            System.out.println("Ingrese el número de identificación del cliente " + (i + 1) + ":");
            String numeroIdentificacion = scanner.nextLine();
            System.out.println("¿El cliente " + (i + 1) + " tiene membresía? (true/false):");
            boolean tieneMembresia = scanner.nextBoolean();
            scanner.nextLine(); // Consumir la línea después de leer un booleano

            Cliente cliente = new Cliente(nombreCliente, numeroIdentificacion, tieneMembresia);
            ClienteProducto clienteProducto = new ClienteProducto(cliente);

            System.out.println("Ingrese la cantidad de productos para el cliente " + (i + 1) + ":");
            int cantidadProductos = scanner.nextInt();
            scanner.nextLine(); // Consumir la línea después de leer un entero

            for (int j = 0; j < cantidadProductos; j++) {
                System.out.println("Ingrese el nombre del producto " + (j + 1) + ":");
                String nombreProducto = scanner.nextLine();
                System.out.println("Ingrese el precio del producto " + (j + 1) + ":");
                double precioProducto = scanner.nextDouble();
                scanner.nextLine(); // Consumir la línea después de leer un double
                System.out.println("Ingrese la marca del producto " + (j + 1) + ":");
                String marcaProducto = scanner.nextLine();
                System.out.println("Ingrese la cantidad en stock del producto " + (j + 1) + ":");
                int cantidadEnStock = scanner.nextInt();
                scanner.nextLine(); // Consumir la línea después de leer un entero
                System.out.println("Ingrese la fecha de caducidad del producto " + (j + 1) + " (formato YYYY-MM-DD):");
                String fechaCaducidad = scanner.nextLine();
                System.out.println("¿El producto " + (j + 1) + " es perecedero? (true/false):");
                boolean esPerecedero = scanner.nextBoolean();
                scanner.nextLine(); // Consumir la línea después de leer un booleano
                System.out.println("Ingrese la categoría del producto " + (j + 1) + ":");
                String categoriaProducto = scanner.nextLine();
                System.out.println("Ingrese la descripción del producto " + (j + 1) + ":");
                String descripcionProducto = scanner.nextLine();

                LocalDate fechaCaducidadLD = LocalDate.parse(fechaCaducidad);
                Producto producto = new Producto(nombreProducto, precioProducto, marcaProducto, cantidadEnStock,
                        fechaCaducidadLD, esPerecedero, categoriaProducto, descripcionProducto);
                clienteProducto.agregarProducto(producto);
            }

            clientesEnCola.add(clienteProducto);
        }
    }

    /**
     *  funcion para usar productos y clientes de prueba
     * @param clientesEnCola 
     */
    private static void usarProductosYClientesDePrueba(List<ClienteProducto> clientesEnCola) {
        // Crear productos de prueba
        Producto producto1 = new Producto("Leche", 1.5, "Lechera", 20, LocalDate.now().plusDays(30), true, "Lácteos",
                "Leche fresca");
        Producto producto2 = new Producto("Pan", 2.0, "Bimbo", 30, LocalDate.now().plusDays(7), true, "Panadería",
                "Pan integral");
        Producto producto3 = new Producto("Manzanas", 3.0, "Verdeval", 15, LocalDate.now().plusDays(14), true, "Frutas",
                "Manzanas frescas");

        // Crear clientes de prueba
        Cliente cliente1 = new Cliente("Juan", "123456", true);
        Cliente cliente2 = new Cliente("María", "789012", false);

        // Crear instancias de ClienteProducto de prueba
        ClienteProducto clienteProducto1 = new ClienteProducto(cliente1);
        clienteProducto1.agregarProducto(producto1);
        clienteProducto1.agregarProducto(producto2);

        ClienteProducto clienteProducto2 = new ClienteProducto(cliente2);
        clienteProducto2.agregarProducto(producto2);
        clienteProducto2.agregarProducto(producto3);

        // Agregar clientes de prueba a la lista
        clientesEnCola.add(clienteProducto1);
        clientesEnCola.add(clienteProducto2);
    }

    /**
     * muestra los registros de interaccion hasta ek momento mientras sigan procesos de atencion activos
     * @param cajeras
     * @param hilosCajeras 
     */
    private static void imprimirInteraccionesEnTiempoReal(List<Cajera> cajeras, List<Thread> hilosCajeras) {
        // Temporizador para imprimir interacciones cada segundo
        int intervaloSegundos = 1;

        // Bucle para imprimir interacciones en tiempo real
        while (algunHiloEstaVivo(hilosCajeras)) {
            try {
                Thread.sleep(intervaloSegundos * 1000); // Convertir segundos a milisegundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Imprimir interacciones hasta el momento para todas las cajeras
            System.out.println("\nInteracciones en tiempo real:");
            for (Cajera cajera : cajeras) {
                List<String> registroInteracciones = cajera.getRegistroInteracciones();
                for (String log : registroInteracciones) {
                    System.out.println(log);
                }
            }
        }
    }
    
    /**
     * verifica si hay alguna atencion en proceso
     * @param hilos
     * @return 
     */
    private static boolean algunHiloEstaVivo(List<Thread> hilos) {
        // Verifica si al menos uno de los hilos está vivo
        for (Thread hilo : hilos) {
            if (hilo.isAlive()) {
                return true;
            }
        }
        return false;
    }
}
