import java.util.Scanner;

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
        pedirNombreUsuario();
        pedirCorreo();
        jugador.crearJugador(jugador.getNombreUsuario(), jugador.getEmail());
        
        /*if(!this.jugador.comprobarExistenciaJugador(nombreUsuario)) {
            pedirCorreo();
            jugador.crearJugador(nombreUsuario, correo);
        }*/
    }

    /*public void cargarEscenario(Path rutaEscenario) {
        
    }*/
}