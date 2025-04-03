import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;

/**
 * Clase vista que se encarga de mostrar la información de los escenarios.
 * @author David Muñoz - Eva Retamar
 * Licencia GPL v3. Fecha 03 2025
 */
public class Vista {
	
	/**
	 * Método para cargar y analizar varios escenarios.
	 * @param rutaEscenarios Lista de rutas de los arhivos de escenarios.
	 */
	public void cargarEscenarios(List<Path> rutasEscenarios) {
        for (Path rutaEscenario : rutasEscenarios) {
            System.out.println("\n" + "===" + rutaEscenario.getFileName() + " ===");

            int obstaculos = 0;
            int espacios = 0;

            try {
                List<String> archivoEscenario = Files.readAllLines(rutaEscenario);

                for (String linea : archivoEscenario) {
                    System.out.println(linea);
                    for (char caracter : linea.toCharArray()) {
                        if (caracter == '¬') {
                            obstaculos++;
                        } else if (caracter == '|') {
                            espacios++;
                        }
                    }
                }

                System.out.println("Obstáculos ('¬'): " + obstaculos);
                System.out.println("Espacios ('|'): " + espacios);

            } catch (IOException e) {
				e.printStackTrace();            
			}
        }
    }
}