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
	
        private static char[][] mapa = null;
        private static Integer filaJugador;
        private static Integer columnaJugador;

	/**
	 * Método para cargar varios escenarios.
	 * @param rutaEscenarios Lista de rutas de los arhivos de escenarios.
	 */
    public void cargarEscenarios(Path rutaEscenario, Integer opcion) {
            System.out.println("\n=== " + "Nivel " + opcion + " ===");

            int obstaculos = 0;
            int espacios = 0;
            int borde = 0;
            int guion = 0;

            try {
                List<String> archivoEscenario = Files.readAllLines(rutaEscenario);
                mapa = new char[archivoEscenario.size()][];
              
                for (int i = 0; i < archivoEscenario.size(); i++) {
                    String linea = archivoEscenario.get(i);
                    String[] partes = linea.split(" ");
                    StringBuilder fila = new StringBuilder();

                    for(String parte : partes) {
                        int cantidad = Integer.valueOf(parte.substring(0, parte.length() - 1));
                        char tipo = parte.charAt(parte.length() - 1);

                        char simbolo = ' ';

                        switch(tipo) {
                            case 'E': simbolo = '_';
                                espacios += cantidad;
                                break;
                            case 'O': simbolo = '¬';
                                obstaculos += cantidad;
                                break;
                            case 'B': simbolo = '|'; 
                                borde += cantidad;
                                break;
                            case 'G': simbolo = '-';
                                guion += cantidad;
                                break;
                            default:
                                simbolo = ' ';
                                break;
                        }

                        //Agregamos el símbolo a la fila tanta veces como indique cantidad. 
                        for(int j = 0; j < cantidad; j++) {
                            fila.append(simbolo); //Método de la clase StringBuilder. Añadimos el símbolo a la fila.
                        }
                    }
                    mapa[i] = fila.toString().toCharArray();
                    //System.out.println(fila.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();            
        }
    }
   
    /**
     * Método con el cuál el jugador aparece en la primera posición del mapa que no sea ni borde ni obstáculo.
     */
    public void posicionarJugador(){
        for(int i = 0; i < mapa.length; i++){
            for(int j = 0; j < mapa[i].length; j++){
                if(mapa[i][j] == '_'){
                    filaJugador = i;
                    columnaJugador = j;
                    return;
                }
            }
        }
    }

    /**
     * Método que muestra al jugador en el mapa una vez carga por priemra vez o cuando se actualice su posición
     * en el mapa.
     */
    public void mostrarMapaConJugador() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (i == filaJugador && j == columnaJugador) {
                    System.out.print('Ï');
                } else {
                    System.out.print(mapa[i][j]);
                }
            }
            System.out.println();
        }
    }

    /**
     * Método que recibe la tecla pulsada por el usuario y modifica sus coordenadas para moverlo a través
     * del mapa.
     * @param tecla Tecla que pulsa el usuario.
     */
    public void moverJugador(char tecla){
        switch(tecla) {
            case 'w':
                 filaJugador--;
                 break;
            case 'a':
                columnaJugador--;
                break;
            case 's':
                filaJugador++;
                break;
            case 'd':
                columnaJugador++;
                break;
        }
    }

}