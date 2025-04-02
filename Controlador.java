import java.util.Scanner;

public class Controlador {
	private static Scanner teclado;
    private Vista vista;
    private Jugador jugador;
    private static String nombreUsuario;
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

	public static String pedirCorreo() {
        String correo;
    	do {
        	System.out.print("No estás registrado. Introduce tu correo: ");
        	correo = teclado.nextLine();
        	if (!correo.contains("@")) {
            	System.out.println("Correo no válido. Asegúrate de que contiene '@'.");
        	}
    	} while (!correo.contains("@"));
    	return correo;
	}

    public void iniciarJuego() {
    	pedirNombreUsuario();
    	
    	if(!this.jugador.existeJugador()) {
    		String correo = pedirCorreo();
    		jugador.crearJugador(nombreUsuario, correo);
    		System.out.println("Jugador registrado correctamente.");
        }
    }

    public void cargarEscenario(Path rutaEscenario) {
        List<String> contenidoEscenario;
        try {
            InputStream caracterEntrada = Files.newInputStream(rutaEscenario);

            ObjectInputStream flujoEntrada = new ObjectInputStream(caracterEntrada);

            contenidoEscenario = (List<String>) flujoEntrada.readObject();

            for(String caracter : contenidoEscenario) {
                System.out.println(caracter);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }


}
