import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
/**
 * Clase principal que crea un directorio de configuración y subdirectorios.
 * @autor David Muñoz - Eva Retamar
 * Licencia GPL v3. Fecha 03 2025
 * 
 */
public class App {
	public static void main(String[] args) {
		comprobarFicheroConfiguracion();
	}

	/**
	 * Método para comprobar si existe el fichero de configuración
	 */
	public static void comprobarFicheroConfiguracion() {
		File ficheroConfiguracion = new File("config.txt");
		Path pathActual = Paths.get("");

		try{
			if(!ficheroConfiguracion.exists()){
				ficheroConfiguracion.createNewFile();
				System.out.println("Fichero de configuración creado correctamente");
				//crearDirectorios()
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
	 * @param directorio Es el directorio donde se crearan los subdirectorios.
	 * @param nombreSubdirectorio Es el nombre que se le dará al subdirectorio creado.
	 */
	public static void crearDirectorios(Path directorio, String nombreSubdirectorio) {
		Path subdirectorio = directorio.resolve(nombreSubdirectorio);

		try {
			Files.createDirectory(subdirectorio);
			System.out.println("El directorio " + nombreSubdirectorio + " ha sido creado.");
		} catch(IOException e) {
			System.out.println("No se pudo crear el directorio " + nombreSubdirectorio);
		}
	}
}