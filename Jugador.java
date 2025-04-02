import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Jugador implements Serializable{
	private String nombreUsuario;
	private String email;


	public Jugador(){
		this.nombreUsuario = nombreUsuario;
		this.email = email;
	}

	public Jugador(String nombreUsuario, String email){
		this.nombreUsuario = nombreUsuario;
		this.email = email;
	}

	/*public void comprobarExistenciaJugador(String nombreUsuario){
       
    }*/

	public void crearJugador(String nombreUsuario, String email){
		List<Jugador> contenidoFicheroJugador = new ArrayList<>();
		contenidoFicheroJugador.add(new Jugador(nombreUsuario, email));
        Path rutaJugadoresRegistrados = Paths.get("jugador/" + nombreUsuario + ".bin");

        try{
        	OutputStream ficheroJugador = Files.newOutputStream(rutaJugadoresRegistrados);

        	ObjectOutputStream flujoSalida = new ObjectOutputStream(ficheroJugador);

        	flujoSalida.writeObject(contenidoFicheroJugador);

        	System.out.println("Jugador registrado correctamente.");

        }
        catch(Exception e){
        	e.printStackTrace();
        }
	}

	public void setNombreUsuario(String nombreUsuario){
		this.nombreUsuario = nombreUsuario;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getNombreUsuario(){
		return nombreUsuario;
	}

	public String getEmail(){
		return email;
	}
}