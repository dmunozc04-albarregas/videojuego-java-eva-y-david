package fuentes.com.videojuego.controladores;

import fuentes.com.videojuego.Jugador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ControladorRegistroUsuario{

    private Jugador jugador = new Jugador();

    @FXML
    private Button btnRegistro;

    @FXML
    private TextField nombreUsuarioRegistro;

    @FXML
    private TextField correoUsuarioRegistro;

    // Método que recibe el nombre de usuario desde la ventana anterior
    public void setNombreUsuario(String nombre) {
        nombreUsuarioRegistro.setText(nombre); 
    }

    @FXML
    private void validarRegistro() {
        String nombreUsuario = nombreUsuarioRegistro.getText().trim();
        String correoUsuario = correoUsuarioRegistro.getText().trim();

        if (nombreUsuario.isEmpty() || correoUsuario.isEmpty()) {
            mostrarAlerta("Por favor, introduce un nombre / email.");
            return;
        } else {
            jugador.crearJugador(nombreUsuario, correoUsuario);
            mostrarAlerta("Usuario creado correctamente, entrando al juego...");
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Registro de jugador"); 
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
