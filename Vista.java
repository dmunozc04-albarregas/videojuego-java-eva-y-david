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
    private Controlador controlador;

    public Vista(Controlador controlador) {
        this.controlador = controlador;
    }

    /**
     * Método para cargar varios escenarios.
     * @param rutaEscenarios Lista de rutas de los arhivos de escenarios.
     */
    public void cargarEscenarios(Path rutaEscenario, Integer opcion) {
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
     * Método para limpiar la consola según sea Windows o Linux. 
     */
    public void limpiarConsola() {
        try {
             new ProcessBuilder("cmd", "/c", "cls")
                                .inheritIO()        //Cualquier salida de cls se muestra en la consola de java.
                                .start()            //Inicia el proceso de ProcessBuilder(cmd /c cls)            
                                .waitFor();         //Hace que el programa java espere hasta que termina la ejecución para limpiar la pantalla.
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();  // Captura y muestra excepciones relacionadas con la interrupción del proceso
        }
    }

    /**
     * Método que muestra al jugador en el mapa una vez carga por priemra vez o cuando se actualice su posición
     * en el mapa.
     */
    public void mostrarMapaConJugador() {
        limpiarConsola();

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
        if(verificarPosicion()) {
            controlador.perderVida();
        }
    }

    /**
     * Método que recibe la tecla pulsada por el usuario y modifica sus coordenadas para moverlo a través
     * del mapa.
     * @param tecla Tecla que pulsa el usuario.
     */
    /*public void moverJugador(char tecla){
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
        if(verificarPosicion()) {
            controlador.perderVida();
        }
    }*/
    public void moverJugador(char tecla) {
    switch(tecla) {
        case 'w':
            if (filaJugador > 0) {  // Verifica si no sale del límite superior
                filaJugador--;
            }
            break;
        case 'a':
            if (columnaJugador > 0) {  // Verifica si no sale del límite izquierdo
                columnaJugador--;
            }
            break;
        case 's':
            if (filaJugador < mapa.length - 1) {  // Verifica si no sale del límite inferior
                filaJugador++;
            }
            break;
        case 'd':
            if (columnaJugador < mapa[0].length - 1) {  // Verifica si no sale del límite derecho
                columnaJugador++;
            }
            break;
    }
    // Aquí puede agregar cualquier otra lógica adicional (verificar símbolos en la nueva posición, etc.)
}


    /**
     * Método para verificar que si el jugador colisiona con un obstáculo o marco
     * activa la función para eliminar vida del jugador.
     */
    public boolean verificarPosicion() {
        char simbolo = mapa[filaJugador][columnaJugador];
        if(simbolo == '|' || simbolo == '¬' || simbolo == '-') {
                System.out.println("¡Te has encontrado con un obstáculo! Pierdes una vida.");
            return true;
        }
        return false;
    }    

}