package com.videojuego.vistas;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;
import javafx.fxml.FXML;

public class EscenarioView {
    private Stage ventana;
    private Scene escena;
    private Escenario escenario;

    @FXML
    private GridPane grid;

    private Integer filas = 10;
    private Integer columnas = 10;
    private StackPane[][] stackPanes;

    private Image imgEscenario;
    private ImageView ivPersonaje;

    private static final Integer LADO = 128;

    public EscenarioView(Stage ventana, Escenario escenario){
        this.ventana = ventana;
        stackPanes = new StackPane[filas][columnas];
        this.imgEscenario = new Image(this.getClass().getResourceAsStream("/escenario.png"));
        inicializarVista();
    }

    private void inicializarVista() {
        crearGrid();
        dibujarMapa();
        configurarEscena();
        mostrarMapaConJugador();
    }

    private void crearGrid() {
        // Definir filas y columnas del GridPane
        for (int i = 0; i < filas; i++) {
            grid.getRowConstraints().add(new RowConstraints());
        }
        for (int j = 0; j < columnas; j++) {
            grid.getColumnConstraints().add(new ColumnConstraints());
        }
        // Crear celdas como StackPane con imagen base
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                StackPane stack = new StackPane();
                ImageView base = new ImageView(imgEscenario);
                base.setFitWidth(60);
                base.setFitHeight(60);
                base.setPreserveRatio(true);
                stack.getChildren().add(base);
                grid.add(stack, j, i);
                stackPanes[i][j] = stack;
            }
        }
    }

    private void dibujarMapa() {
        // Ejemplo de cómo se dibujaría el mapa manualmente
        ponerCelda(0, 0, obtenerViewport('A')); // esquina superior izquierda
        ponerCelda(0, columnas - 1, obtenerViewport('B')); // esquina superior derecha
        ponerCelda(filas - 1, 0, obtenerViewport('I')); // esquina inferior izquierda
        ponerCelda(filas - 1, columnas - 1, obtenerViewport('D')); // esquina inferior derecha

        // Puedes rellenar el resto del mapa aquí como prefieras
        // Ejemplo: rellenar todo el centro como espacio libre
        for (int i = 1; i < filas - 1; i++) {
            for (int j = 1; j < columnas - 1; j++) {
                ponerCelda(i, j, obtenerViewport('E'));
            }
        }
    }

    private void ponerCelda(int fila, int columna, Rectangle2D viewport) {
        ImageView imagen = (ImageView) stackPanes[fila][columna].getChildren().get(0);
        imagen.setViewport(viewport);
    }

    private Rectangle2D obtenerViewport(char tipo) {
        switch (tipo) {
            case 'A': return new Rectangle2D(1 * LADO, 3 * LADO, LADO, LADO); // borde
            case 'B': return new Rectangle2D(1 * LADO, 3 * LADO, LADO, LADO); // borde
            case 'I': return new Rectangle2D(2 * LADO, 4 * LADO, LADO, LADO); // interior
            case 'D': return new Rectangle2D(2 * LADO, 4 * LADO, LADO, LADO); // interior
            case 'O': return new Rectangle2D(4 * LADO, 2 * LADO, LADO, LADO); // obstáculo
            case 'E': return new Rectangle2D(5 * LADO, 2 * LADO, LADO, LADO); // espacio
            default: return new Rectangle2D(5 * LADO, 2 * LADO, LADO, LADO); // por defecto espacio
        }
    }

    private void configurarEscena() {
        vistaPrincipal = new Scene(grid);
        ventana.setScene(vistaPrincipal);
        ventana.setTitle("Mazmorra");
        ventana.show();
    }

    /**
     * Función que crea el personaje y lo coloca en la celda correspondiente
     * basada en la posición del jugador.
     */
    public void mostrarMapaConJugador() {
        int fila = escenario.getFilaJugador();
        int columna = escenario.getColumnaJugador();

        crearPersonaje(fila, columna);
    }

    public void crearPersonaje(Integer fila, Integer columna) {
        // Crear la imagen del personaje
        Image imgPersonaje = new Image(this.getClass().getResourceAsStream("/2dpixx_-_free_assets_-_warrior_character_size_128x160_isometric_-_attack.png"));
        ivPersonaje = new ImageView(imgPersonaje);
        ivPersonaje.setFitHeight(60);
        ivPersonaje.setPreserveRatio(true);
        Rectangle2D vpPersonaje = new Rectangle2D(3 * 128, 1 * 160, 128, 160);
        ivPersonaje.setViewport(vpPersonaje);

        // Colocar al personaje en la celda (fila, columna) determinada
        StackPane stack = stackPanes[fila][columna];
        stack.getChildren().add(ivPersonaje);
    }

    // Método para obtener la StackPane en una celda
    public StackPane getCelda(int fila, int columna) {
        return stackPanes[fila][columna];
    }
}