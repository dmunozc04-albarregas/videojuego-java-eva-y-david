import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;
public class Vista {
	
	public void cargarEscenario(Path rutaEscenario) {
        List<String> archivoEscenario;
        int obstaculos = 0;
        int espacios = 0;
        try {
        	if(Files.notExists(rutaEscenario)) {
        		System.out.println("El archivo no existe.");
        	} else {
        		archivoEscenario = Files.readAllLines(rutaEscenario);

        		for(String linea : archivoEscenario) {
        			 for(char caracter : linea.toCharArray()) {
        			 if(caracter == '¬') {
        				obstaculos++;
        			 } else if(caracter == '|') {
        				espacios++;
        		      	}
        		  }
        	   }
            }
            System.out.println("Obstáculos ('¬'): " + obstaculos);
            System.out.println("Espacios ('|'): " + espacios);
        } catch(IOException e) {
			e.printStackTrace();
        }
    }
}