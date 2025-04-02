import java.nio.file.Files;
import java.io.IOException;
import java.util.List;
public class Vista {
	
	public void cargarEscenario(Path rutaEscenario) {
        List<String> archivoEscenario;
        int obsaculos = 0;
        int espacios = 0;
        try {
        	if(Files.notExists(rutaEscenario)) {
        		System.out.println("El archivo no existe.")
        	} else {
        		archivoEscenario = Files.readAllLines(rutaEscenario);

        		for(String linea : archivoEscenario) {
        			for(char caracter : linea.toCharArray()) {
        			if(caracter == ¬) {
        				obsaculos++;
        			} else if(caracter == |) {
        				espacios++;
        			}
        		}
        	}
        	System.out.println("Total de caracteres: " + totalCaracteres);
            System.out.println("Obstáculos ('¬'): " + obstaculos);
            System.out.println("Espacios (' '): " + espacios);
        } catch(IOException e) {
			e.printStackTrace();
        }
    }
}