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
import java.util.List;
import java.io.IOException;

public class ControladorJuego {
    private Jugador jugador = new Jugador();
    private Escenario escenario;
    private ControladorPrincipal controladorPrincipal;
    private Image imgEscenario;
    private StackPane[][] stackPanes;
    
    private int filas;
    private int cols;

    private Stage ventana;
    private int nivel;
    private Scene nivelVentana;
    
    @FXML
    private GridPane gridEscenario;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Text nivelTexto;

    public ControladorJuego() {}
    
    public ControladorJuego(ControladorPrincipal controladorPrincipal) {
        this.controladorPrincipal = controladorPrincipal;

        /*List<String> archivoEscenario = controladorPrincipal.iniciarJuego();
        String[] dimensiones = archivoEscenario.get(0).split(" ");
        filas = Integer.parseInt(dimensiones[0]);
        cols = Integer.parseInt(dimensiones[1]);*/

        imgEscenario = new Image(getClass().getResourceAsStream("/imagenes/escenario.png")); // <-- AQUÍ

    }

    public ControladorJuego(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setNivel(int nivel) {
         this.nivel = nivel;
    }

    public void setNivel(Scene nivelVentana) {
         this.nivel = nivel;
    }

    public ControladorJuego(Stage ventana){
        this.ventana = ventana;

        nivelVentana = cargarVista(this);

        crearGrid();
    }

    private void crearGrid() {
        stackPanes = new StackPane[filas][cols];
        for (int i = 0; i < filas; i++) {
            gridEscenario.getRowConstraints().add(new RowConstraints());
        }
        for (int j = 0; j < cols; j++) {
            gridEscenario.getColumnConstraints().add(new ColumnConstraints());
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
                gridEscenario.add(stack, j, i);
                stackPanes[i][j] = stack;
            }
        }
    }

    private void configurarEscena1(Stage ventana) {
        nivelVentana = cargarVista(this);
        
        ventana.setScene(nivelVentana);
        ventana.show();
    }

   /* private void configurarEscena2(Stage ventana) {
        nivel2 = cargarVista(this, "nivel2");
        
        ventana.setScene(nivel2);
        ventana.show();
    }

    private void configurarEscena3(Stage ventana) {
        nivel3 = cargarVista(this, "nivel3");
        
        ventana.setScene(nivel3);
        ventana.show();
    }

    private void configurarEscena4(Stage ventana) {
        nivel4 = cargarVista(this, "nivel4");
        
        ventana.setScene(nivel4);
        ventana.show();
    }*/

    private Scene cargarVista(ControladorJuego controlador) {
        final String PATH_VISTAS = "../recursos/com/videojuego/vistas/";
        Scene vista = null;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(ControladorPrincipal.class.getResource(PATH_VISTAS + "nivel1.fxml"));
            fxmlLoader.setController(controlador);
            Parent raiz = fxmlLoader.load();
            vista = new Scene(raiz);
        }
        catch(IOException e){
            e.printStackTrace();
            System.out.println("ERROR FATAL. No se encuentra la vista.");
            System.exit(-1);
        }
        return vista;
    }
}