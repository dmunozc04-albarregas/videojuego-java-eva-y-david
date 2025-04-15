package fuentes.com.videojuego;

import recursos.com.videojuego.vistas.ControladorVistas;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import javafx.application.Application;
import javafx.stage.Stage;



/**
 * Clase principal que crea un directorio de configuración y subdirectorios.
 * @author David Muñoz - Eva Retamar
 * Licencia GPL v3. Fecha 03 2025
 */
public class App extends Application{
    
    private static Scanner teclado = new Scanner(System.in);
    private static ControladorVistas ControladorVistas;

    public static void main(String[] args) {
        comprobarFicheroConfiguracion();
        Jugador jugador = new Jugador();
        Controlador controlador = new Controlador(teclado, jugador);
        launch(args);
        //controlador.iniciarJuego();
        teclado.close();
    }

    @Override
    public void start(Stage stage) throws IOException {
        new ControladorVistas(stage);
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
        //Path directorioEscenario = pathActual.resolve("escenarios");
        Path directorioJugador = pathActual.resolve("jugador");
        Path directorioPartida = pathActual.resolve("partida");

        try {
            //Files.createDirectory(directorioEscenario);
            Files.createDirectory(directorioJugador);
            Files.createDirectory(directorioPartida);
            System.out.println("Directorios de configuración creados correctamente");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No se pudieron crear los directorios.");
        }
    }


}

