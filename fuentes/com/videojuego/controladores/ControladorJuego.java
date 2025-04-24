package com.videojuego.controladores;

import com.videojuego.modelos.Escenario;
import com.videojuego.modelos.Jugador;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import java.nio.file.Path;
import java.util.Objects;

public class ControladorJuego {
    private Jugador jugador;
    private Escenario escenario;
    private int nivel;
    
    @FXML
    private GridPane gridEscenario;

    @FXML
    private Text nivelTexto;

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void cargarEscenario(Path rutaEscenario, int nivel) {
        escenario = new Escenario(rutaEscenario, nivel);

        gridEscenario.getChildren().clear();
        gridEscenario.getColumnConstraints().clear();
        gridEscenario.getRowConstraints().clear();

        int filas = escenario.getFilas();
        int columnas = escenario.getColumnas();

        for (int i = 0; i < filas; i++) {
            gridEscenario.getRowConstraints().add(new RowConstraints(60));
            for (int j = 0; j < columnas; j++) {
                if (i == 0) gridEscenario.getColumnConstraints().add(new ColumnConstraints(60));
                StackPane celda = escenario.getCeldaVista(i, j);
                gridEscenario.add(celda, j, i);
            }
        }

        escenario.posicionarJugador();
        escenario.mostrarMapaConJugador();
        nivelTexto.setText("Nivel " + nivel);
    }

    public void actualizarTextoNivel(int nivel) {
        if (nivelTexto != null) {
            nivelTexto.setText("Nivel " + nivel);
        }
    }

    // Tecla pulsada para mover jugador
    public void manejarMovimiento(char tecla) {
        if (escenario != null) {
            escenario.moverJugador(tecla);
        }
    }
}
