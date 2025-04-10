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
    private int vidas = 3;

    /**
     * Contructor de la clase Controlador.
     * @param teclado Objeto Scanner para la entrada de datos del usuario.
     * @param jugador Instancia de la case Jugador para gestionar el usuario.
     */
    public Controlador(Scanner teclado, Jugador jugador) {
        this.vista = new Vista(this);
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

        mostrarMenu();
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
        vista.posicionarJugador();
        
        do{
            vista.mostrarMapaConJugador();
            obtenerTecla();
        }
        while(vidas > 0);
    }

    /**
     * Método para mostrar los diferentes niveles posibles para jugar
     */
    public void mostrarMenu(){
        System.out.println("1. Nivel 1");
        System.out.println("2. Nivel 2");
        System.out.println("3. Nivel 3");
        System.out.println("4. Nivel 4");
    }

    /**
     * Método que recoge la tecla introducida por el usuario para poder saber a que posición mover el usuario.
     * Adicionalmente, comprueba que las teclas sean las indicadas y que no se introduzca un espacio.
     */
    public void obtenerTecla(){
        String teclaString = " ";

        do {
            System.out.print("Pulse para mover al jugador (W,A,S,D) y ENTER: ");
            teclaString = teclado.nextLine().toLowerCase();

            if (teclaString.isEmpty()) {
                System.out.println("¡No se ha introducido ninguna tecla! Por favor, ingrese W, A, S, o D.");
            }
        }while((teclaString.isEmpty()));

        if (!teclaString.isEmpty()) {
            char tecla = teclaString.charAt(0);

            if (tecla == 'w' || tecla == 'a' || tecla == 's' || tecla == 'd') {
                vista.moverJugador(tecla);
            } else {
                System.out.println("Tecla no válida. Usa W, A, S, D para mover.");
            }
        }
    }

    /**
     * Pierde una vida y verifica si el juego ha terminado.
     */
    public void perderVida() {
        vidas--;
        System.out.println("¡Has perdido una vida! Vidas restantes: " + vidas);
        
        if (vidas == 0) {
            System.out.println("¡Has perdido todas tus vidas! Fin de la partida.");
            System.exit(0);
        }
    }

    /**
     * Método que solicita al usuario el nivel a elegir.
     */
    public Integer solicitarOpcion(){
        Integer opcion = 0;
        System.out.print("Eliga un nivel: ");
        opcion = Integer.valueOf(teclado.nextLine());

        return opcion;
    }  
}