/*public class Controlador {
	
    private Vista vista;
    private Jugador jugador;
    private Escenario escenario;

    public Controlador() {
        this.vista = new Vista();

    }

    public static String pedirNombreUsuario() {
		String nombre;
    	do {
        	System.out.print("Introduce tu nombre de usuario: ");
        	nombre = scanner.nextLine().trim();
        	if (nombre.isEmpty()) {
            	System.out.println("El nombre de usuario no puede estar vacío. Inténtalo de nuevo.");
        	}
    	} while (nombre.isEmpty());
    	return nombre;
	}

	public static String pedirCorreo() {
        String correo;
    	do {
        	System.out.print("No estás registrado. Introduce tu correo: ");
        	correo = scanner.nextLine().trim();
        	if (!correo.contains("@")) {
            	System.out.println("Correo no válido. Asegúrate de que contiene '@'.");
        	}
    	} while (!correo.contains("@"));
    	return correo;
	}

    public void iniciarJuego(Jugador jugador) {
    	String nombreUsuario = pedirNombreUsuario();
    	
    	if(!jugador.existeJugador()) {
    		String correo = pedirCorreo();
    		jugador.crearJugador(nombreUsuario, correo);
    		System.out.println("Jugador registrado correctamente.");
    }

    public void cargarEscenario(String nombreEscenario) {
    	vista.mostrarEscenario();
    }


}
*/