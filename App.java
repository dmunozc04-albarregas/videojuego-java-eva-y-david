import java.io.IOException;
import java.nio.file.Path;
/**
 * Clase principal que cre un directorio de configuración y sbdirectorios.
 * @autor David Muñoz - Eva Retamar
 * Licencia GPL v3. Fecha 03 2025
 * 
 */
public class App {
	public static void main(String[] args) {

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
			System.out.println("El dorectorio " + nombreSubdirectorio + " ha sido creado.");
		} catch(IOException e) {
			System.out.println("No se pudo crear el directorio " + nombreSubdirectorio);
		}
	}
}