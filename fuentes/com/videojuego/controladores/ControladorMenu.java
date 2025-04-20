package com.videojuego.controladores;

import com.videojuego.modelos.Jugador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class ControladorMenu {
    private Jugador jugador;

    @FXML
    private Button btnNivel1;

    @FXML
    private Button btnNivel2;

    @FXML
    private Button btnNivel3;

    @FXML
    private Button btnNivel4;

    public ControladorMenu() {
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    @FXML
    private void seleccionarNivel(ActionEvent event) {
        Button boton = (Button) event.getSource();
        int nivel = Integer.parseInt(boton.getText().replace("Nivel ", ""));
        
        iniciarJuego(nivel);
    }

    private void iniciarJuego(int nivel) {
        try {
            // Cargar la vista del juego
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/videojuego/vistas/vistaJuego.fxml"));
            Parent root = loader.load();

            // Obtener el controlador del juego
            ControladorJuego controladorJuego = loader.getController();
            controladorJuego.setJugador(jugador);
            controladorJuego.setNivel(nivel);
            controladorJuego.inicializarJuego();

            // Crear nueva ventana
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Juego - Nivel " + nivel);
            stage.show();

            // Cerrar ventana de menú
            Stage ventanaMenu = (Stage) btnNivel1.getScene().getWindow();
            ventanaMenu.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ventanaJuego(int nivel) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/videojuego/vistas/vistaJuego.fxml"));
            Parent root = loader.load();

            ControladorJuego controladorJuego = loader.getController();

            controladorJuego = new ControladorJuego(jugador, nivel); 

            controladorJuego.inicializarJuego();  

            Stage stage = (Stage) btnNivel1.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Juego - Nivel " + nivel);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}