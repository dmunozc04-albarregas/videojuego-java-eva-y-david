import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Clase principal que crea un directorio de configuración y subdirectorios.
 * @autor David Muñoz - Eva Retamar
 * Licencia GPL v3. Fecha 03 2025
 */
public class App {
    
    private static Jugador jugador = new Jugador(null, null);
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {
        comprobarFicheroConfiguracion();
        instanciarJugadores();
        
        // Controlador controlador = new Controlador();
        // controlador.iniciarJuego();
    }

    /**
     * Método para comprobar si existe el fichero de configuración.
     */
    private static void comprobarFicheroConfiguracion() {
        File ficheroConfiguracion = new File("config.txt");

        try {
            if (!ficheroConfiguracion.exists()) {
                ficheroConfiguracion.createNewFile();
                System.out.println("Fichero de configuración creado correctamente");
                crearDirectorios();
            }
            System.out.println("Ok");
        } catch (IOException e) {
            System.out.println("Algo ha ido mal");
            e.printStackTrace();
        }
    }

    /**
     * Método para crear subdirectorios.
     */
    private static void crearDirectorios() {
        Path pathActual = Paths.get("");
        Path directorioEscenario = pathActual.resolve("escenarios");
        Path directorioJugador = pathActual.resolve("jugador");
        Path directorioPartida = pathActual.resolve("partida");

        try {
            Files.createDirectory(directorioEscenario);
            Files.createDirectory(directorioJugador);
            Files.createDirectory(directorioPartida);
            System.out.println("Directorios de configuración creados correctamente");
        } catch (IOException e) {
            System.out.println("No se pudieron crear los directorios.");
        }
    }

    /**
     * Método para instanciar jugadores.
     */
    private static void instanciarJugadores() {
        System.out.println("Instanciando jugadores...");
        // Aquí deberías implementar la lógica para crear o cargar jugadores.
    }
}
