package com.videojuego.controladores;

import com.videojuego.modelos.Escenario;
import com.videojuego.modelos.Jugador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import java.io.IOException;

public class ControladorJuego {
    private Jugador jugador;
    private Escenario escenario;
    private int nivel;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Text nivelTexto;
    /*@FXML
    private GridPane grid; 

    private Stage ventana;
    private Scene vistaJuego;
    
    private Jugador jugador;
    private int nivel;

    private Integer filas = 10; 
    private Integer cols = 10;
    private static final Integer LADO = 128;

    private StackPane[][] stackPanes; 
    private Image imgEscenario;
    //private ImageView ivPersonaje;
*/
    public ControladorJuego() {}

    /*public ControladorJuego(Jugador jugador, int nivel) {
        this.jugador = jugador;
        this.nivel = nivel;
    }*/

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
/*
    public void inicializarJuego() {
        stackPanes = new StackPane[filas][cols];
        imgEscenario = new Image(this.getClass().getResourceAsStream("escenario.png"));
        crearGrid();
        dibujarMapa();
    }

    public void iniciarVentana() {
        this.ventana = ventana;
        stackPanes = new StackPane[filas][cols];
        imgEscenario = new Image(this.getClass().getResourceAsStream("/com/videojuego/imagenes/escenario.png")); 

        vistaJuego = cargarVista(this, "vistaJuego"); 
        ventana = new Stage(); 
        ventana.setScene(vistaJuego);
        ventana.setTitle("Juego - Nivel " + nivel);
        ventana.show();

        crearGrid();
        dibujarMapa();
        //configurarEscena();
        //mostrarMapaConJugador();
    }*/
/*
    private void crearGrid() {
        for (int i = 0; i < filas; i++) {
            grid.getRowConstraints().add(new RowConstraints());
        }
        for (int j = 0; j < cols; j++) {
            grid.getColumnConstraints().add(new ColumnConstraints());
        }
        // Crear celdas como StackPane con imagen base
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
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
        ponerCelda(0, 0, obtenerViewport('A'));
        ponerCelda(0, cols - 1, obtenerViewport('B'));
        ponerCelda(filas - 1, 0, obtenerViewport('I')); 
        ponerCelda(filas - 1, cols - 1, obtenerViewport('D')); 

        for (int i = 1; i < filas - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
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
            case 'A': return new Rectangle2D(1 * LADO, 3 * LADO, LADO, LADO); 
            case 'B': return new Rectangle2D(1 * LADO, 3 * LADO, LADO, LADO); 
            case 'I': return new Rectangle2D(2 * LADO, 4 * LADO, LADO, LADO); 
            case 'D': return new Rectangle2D(2 * LADO, 4 * LADO, LADO, LADO); 
            case 'O': return new Rectangle2D(4 * LADO, 2 * LADO, LADO, LADO);
            case 'E': return new Rectangle2D(5 * LADO, 2 * LADO, LADO, LADO); 
            default: return new Rectangle2D(5 * LADO, 2 * LADO, LADO, LADO); 
        }
    }

    private void configurarEscena(Stage ventana) {
        vistaJuego = cargarVista(this, "vistaJuego");
        
        ventana.setScene(vistaJuego);
        ventana.setTitle("Laberinto");
        ventana.show();
    }

    private Scene cargarVista(ControladorJuego controlador, String nombreVista) {
        final String PATH_VISTAS = "../vistas/";
        Scene vista = null;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(ControladorPrincipal.class.getResource(PATH_VISTAS + nombreVista + ".fxml"));
            fxmlLoader.setController(controlador);
            Parent raiz = fxmlLoader.load();
            vista = new Scene(raiz);
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("ERROR FATAL. No se encuentra la vista " + nombreVista + ".");
            System.exit(-1);
        }
        return vista;
    }*/
}