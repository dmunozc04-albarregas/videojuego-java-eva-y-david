import java.util.HashMap;
import java.util.Map;

public class Jugador{
	private String nombreUsuario;
	private String email;
	private static HashMap<String, String> jugadoresRegistrados = new HashMap<>();

	public Jugador(String nombreUsuario, String email){
		this.nombreUsuario = nombreUsuario;
		this.email = email;
	}

	public void comprobarExistenciaJugador(String nombreUsuario){
		if(!jugadoresRegistrados.containsKey(nombreUsuario)){
			System.out.println("El usuario no existe");
			registrarUsuario(nombreUsuario);
		}
		else{
			System.out.println("El usuario existe");
		}
	}

	public void registrarUsuario(String nombreUsuario){
		String emailUsuario = null;
		/*System.out.println("Introduzca su email para completar el registro: ");
		emailUsuario = teclado.nextLine();*/
		jugadoresRegistrados.put(nombreUsuario, emailUsuario);
		/*System.out.println("Usuario registrado correctamente");*/
	}
}