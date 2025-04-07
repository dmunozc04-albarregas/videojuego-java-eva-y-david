import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * Clase controlador que gestiona la interacción entre el usuario, la vista y el modelo.
 * @author David Muñoz - Eva Retamar
 * Licencia GPL v3. Fecha 03 2025
 */
public class Controlador {
    private static Scanner teclado;
    private Vista vista;
    private Jugador jugador;
    //private Escenario escenario;

    /**
     * Contructor de la clase Controlador.
     * @param teclado Objeto Scanner para la entrada de datos del usuario.
     * @param jugador Instancia de la case Jugador para gestionar el usuario.
     */
    public Controlador(Scanner teclado, Jugador jugador) {
        this.vista = new Vista();
        this.teclado = teclado;
        this.jugador = jugador;
    }

    /**
     * Solicita al usuario su nombre y lo almacena en el objeto Jugador.
     */
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

    /**
     * Solicita al usuario su correo y lo almacena en el objeto Jugador.
     * Se valida que el correo contenga '@'.
     */
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

     /**
     * Inicia el flujo del juego: pide el nombre del usuario, verifica si está registrado
     * y, en caso contrario, le solicita el correo y lo guarda. Luego, carga los escenarios.
     */
    public void iniciarJuego() {
        pedirNombreUsuario();
        if(!this.jugador.comprobarExistenciaJugador(jugador.getNombreUsuario())) {
            pedirCorreo();
            jugador.crearJugador(jugador.getNombreUsuario(), jugador.getEmail());
        }

        Integer opcion = solicitarOpcion();

        Map<String,Path> rutasEscenarios = new HashMap<>();
        rutasEscenarios.put("nivel 1",Paths.get("escenarios/escenario1.txt"));
        rutasEscenarios.put("nivel 2",Paths.get("escenarios/escenario2.txt"));
        rutasEscenarios.put("nivel 3",Paths.get("escenarios/escenario3.txt"));
        rutasEscenarios.put("nivel 4",Paths.get("escenarios/escenario4.txt"));
        
        Path rutaEscenarioElegido = null;
        for(String clave : rutasEscenarios.keySet()){
            if(rutasEscenarios.containsKey("nivel " + opcion)){
                rutaEscenarioElegido = rutasEscenarios.get("nivel " + opcion);
            }
        }

        vista.cargarEscenarios(rutaEscenarioElegido, opcion);
    }

    public Integer solicitarOpcion(){
        Integer opcion = 0;
        System.out.print("Eliga un nivel: ");
        opcion = Integer.valueOf(teclado.nextLine());

        return opcion;
    }

    
}