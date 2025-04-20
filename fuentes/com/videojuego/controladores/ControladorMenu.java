package com.videojuego.controladores;

import com.videojuego.modelos.Jugador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Node;
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

    // Constructor del controlador, recibe el jugador (inicializado en el controlador anterior)
    public ControladorMenu(Jugador jugador) {
        this.jugador = jugador;
    }

    // Método que se llama cuando el jugador selecciona un nivel
    @FXML
    private void seleccionarNivel(ActionEvent event) {
        Button boton = (Button) event.getSource();
        int nivel = Integer.parseInt(boton.getText().replace("Nivel ", ""));
        
        // Pasar el nivel seleccionado al controlador de juego
        iniciarJuego(event, nivel);
    }

    // Método para iniciar el juego con el nivel seleccionado
    private void iniciarJuego(ActionEvent event, int nivel) {
        // Aquí iniciamos el controlador del juego, pasamos el nivel seleccionado.
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //ControladorJuego controladorJuego = new ControladorJuego(usuario, nivel);
        //controladorJuego.iniciarVentana();

        switch (nivel) {
            case 1:
                ventanaJuegoNivel1();
                break;
            case 2:
                ventanaJuegoNivel2();
                break;
            case 3:
                ventanaJuegoNivel3();
                break;
            case 4:
                ventanaJuegoNivel4();
                break;
            default:
                System.out.println("Nivel no válido");
                break;
            }

        //ventanaJuego(nivel);
    }

    private void ventanaJuegoNivel1() {
    // Código para preparar y mostrar la ventana del Nivel 1
    System.out.println("Mostrando ventana de Nivel 1");
    }

    private void ventanaJuegoNivel2() {
    // Código para preparar y mostrar la ventana del Nivel 1
        System.out.println("Mostrando ventana de Nivel 2");
    }

    private void ventanaJuegoNivel3() {
        // Código para preparar y mostrar la ventana del Nivel 1
        System.out.println("Mostrando ventana de Nivel 3");
    }

    private void ventanaJuegoNivel4() {
        // Código para preparar y mostrar la ventana del Nivel 1
        System.out.println("Mostrando ventana de Nivel 4");
    }

    /*private void ventanaJuego(int nivel) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/videojuego/vistas/vistaJuego.fxml"));
            Parent root = loader.load();

            // Obtener el controlador para pasarle el nivel y el usuario
            ControladorJuego controladorJuego = loader.getController();

            controladorJuego = new ControladorJuego(usuario, nivel);  // Crea el objeto correctamente

            // Si tienes algún método adicional, por ejemplo, iniciar la ventana
            controladorJuego.iniciarVentana();  // Esto solo si tienes un método de inicialización

            Stage stage = (Stage) btnNivel1.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Juego - Nivel " + nivel);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}