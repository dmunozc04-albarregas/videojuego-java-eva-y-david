import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

public class Controlador {
    private static Scanner teclado;
    private Vista vista;
    private Jugador jugador;
    //private Escenario escenario;

    public Controlador(Scanner teclado, Jugador jugador) {
        this.vista = new Vista();
        this.teclado = teclado;
        this.jugador = jugador;
    }

    public void pedirNombreUsuario() {
        String nombreUsuario;
        do {
            System.out.print("Introduce tu nombre de usuario: ");
            nombreUsuario = teclado.nextLine();
            if (nombreUsuario.isEmpty()) {
                System.out.println("El nombre de usuario no puede estar vacío. Inténtalo de nuevo.");
            }
        } while (nombreUsuario.isEmpty());

        jugador.setNombreUsuario(nombreUsuario); 
    }

    public void pedirCorreo() {
        String correo;
        do {
            System.out.print("No estás registrado. Introduce tu correo: ");
            correo = teclado.nextLine();
            if (!correo.contains("@")) {
                System.out.println("Correo no válido. Asegúrate de que contiene '@'.");
            }
        } while (!correo.contains("@"));

        jugador.setEmail(correo);
    }

    public void iniciarJuego() {
        /*Path rutaEscenario1 = Paths.get("escenarios/escenario1.txt");
        Path rutaEscenario2 = Paths.get("escenarios/escenario2.txt");
        Path rutaEscenario3 = Paths.get("escenarios/escenario3.txt");
        Path rutaEscenario4 = Paths.get("escenarios/escenario4.txt");
        */

        pedirNombreUsuario();
        if(!this.jugador.comprobarExistenciaJugador(jugador.getNombreUsuario())) {
            pedirCorreo();
            jugador.crearJugador(jugador.getNombreUsuario(), jugador.getEmail());
        }

        List<Path> rutasEscenarios = new ArrayList<>();
        rutasEscenarios.add(Paths.get("escenarios/escenario1.txt"));
        rutasEscenarios.add(Paths.get("escenarios/escenario2.txt"));
        rutasEscenarios.add(Paths.get("escenarios/escenario3.txt"));
        rutasEscenarios.add(Paths.get("escenarios/escenario4.txt"));
        
        vista.cargarEscenarios(rutasEscenarios);
        
        /*vista.cargarEscenario(rutaEscenario1);
        vista.cargarEscenario(rutaEscenario2);
        vista.cargarEscenario(rutaEscenario3);
        vista.cargarEscenario(rutaEscenario4);*/
    }

    
}