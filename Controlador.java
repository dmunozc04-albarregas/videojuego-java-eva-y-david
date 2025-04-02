import java.util.Scanner;

public class Controlador {
	private static Scanner teclado;
    private Vista vista;
    private Jugador jugador;
    private static String nombreUsuario;
    private static String correo;
    //private Escenario escenario;

    public Controlador(Scanner teclado) {
        this.vista = new Vista();
        this.teclado = teclado;
    }

    public static void pedirNombreUsuario() {
    	do {
        	System.out.print("Introduce tu nombre de usuario: ");
        	nombreUsuario = teclado.nextLine();
        	if (nombreUsuario.isEmpty()) {
            	System.out.println("El nombre de usuario no puede estar vacío. Inténtalo de nuevo.");
        	}
    	} while (nombreUsuario.isEmpty());
	}

	public static void pedirCorreo() {
    	do {
        	System.out.print("No estás registrado. Introduce tu correo: ");
        	correo = teclado.nextLine();
        	if (!correo.contains("@")) {
            	System.out.println("Correo no válido. Asegúrate de que contiene '@'.");
        	}
    	} while (!correo.contains("@"));
	}

    public void iniciarJuego() {
    	pedirNombreUsuario();
        pedirCorreo();
        jugador.crearJugador(nombreUsuario, correo);
    	
    	/*if(!this.jugador.comprobarExistenciaJugador(nombreUsuario)) {
    		pedirCorreo();
    		jugador.crearJugador(nombreUsuario, correo);
        }*/
    }

    /*public void cargarEscenario(Path rutaEscenario) {
        
    }*/
}
