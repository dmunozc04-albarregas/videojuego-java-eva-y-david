import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.util.Scanner;
/**
 * Clase principal que crea un directorio de configuración y subdirectorios.
 * @autor David Muñoz - Eva Retamar
 * Licencia GPL v3. Fecha 03 2025
 * 
 */
public class App {
	static Scanner teclado = new Scanner(System.in);
	
	public static void main(String[] args) {
		comprobarFicheroConfiguracion();
		/*Controlador controlador = new Controlador();
		controlador.iniciarJuego();*/
	}

	/**
	 * Método para comprobar si existe el fichero de configuración
	 */
	public static void comprobarFicheroConfiguracion() {
		File ficheroConfiguracion = new File("config.txt");

		try{
			if(!ficheroConfiguracion.exists()){
				ficheroConfiguracion.createNewFile();
				System.out.println("Fichero de configuración creado correctamente");
				crearDirectorios();
				System.out.println("Directorios de configuración creados correctamente");
			}
			System.out.println("Ok");
		} 
		catch (IOException e){
			System.out.println("Algo ha ido mal");
			e.printStackTrace();
		}
	}

	/**
	 * Método para crear un subdirectorios.
	 */
	public static void crearDirectorios() {
		Path pathActual = Paths.get("");
		Path directorioEscenario = pathActual.resolve("escenario");
		Path directorioJugador = pathActual.resolve("jugador");
		Path directorioPartida = pathActual.resolve("partida");

		try {
			Files.createDirectory(directorioEscenario);
			Files.createDirectory(directorioJugador);
			Files.createDirectory(directorioPartida);
			System.out.println("Los directorios han sido creado.");
		} catch(IOException e) {
			System.out.println("No se pudo crear los directorios.");
		}
	}

}