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
	 * Método para cargar varios escenarios.
	 * @param rutaEscenarios Lista de rutas de los arhivos de escenarios.
	 */
    public void cargarEscenarios(Path rutaEscenario, Integer opcion) {
            System.out.println("\n=== " + "Nivel " + opcion + " ===");

            int obstaculos = 0;
            int espacios = 0;

            try {
                List<String> archivoEscenario = Files.readAllLines(rutaEscenario);

                for (String linea : archivoEscenario) {
                    String[] partes = linea.split(" ");
                    //Creamos un StringBuilder para construir la fila visual del escenario.
                    StringBuilder fila = new StringBuilder();

                    for(String parte : partes) {
                        int cantidad = Integer.valueOf(parte.substring(0, parte.length() - 1));
                        char tipo = parte.charAt(parte.length() - 1);

                        char simbolo;

                        if(tipo == 'E') {
                            simbolo = '|';
                            espacios += cantidad;
                        } else if(tipo == 'O') {
                            simbolo = '¬';
                            obstaculos += cantidad;
                        } else {
                            continue;
                        }
                        //Agregamos el símbolo a la fila tanta veces como indique cantidad. 
                        for(int i = 0; i < cantidad; i++) {
                            fila.append(simbolo); //Método de la clase StringBuilder. Añadimos el símboo a la fila.
                        }
                    }
                    System.out.println(fila.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();            
        }
    }
}