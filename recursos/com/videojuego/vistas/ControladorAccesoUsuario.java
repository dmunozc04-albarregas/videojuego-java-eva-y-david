package recursos.com.videojuego.vistas;

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

public class ControladorAccesoUsuario {

    @FXML
    private TextField nombreUsuarioAComprobar;

    public void setNombreUsuario(String nombre) {
        nombreUsuarioAComprobar.setText(nombre);
    }

    @FXML
    private Button btnValidarUsuario;

    private Jugador jugador = new Jugador();

    @FXML
    private void comprobarNombreUsuario() {
        String nombreUsuario = nombreUsuarioAComprobar.getText().trim();

        if (nombreUsuario.isEmpty()) {
            mostrarAlerta("Por favor, introduce un nombre.");
            return;
        }

        if (jugador.comprobarExistenciaJugador(nombreUsuario)) {
            mostrarAlerta("¡Entrando al juego...");
        } else {
            //mostrarAlerta("El usuario no existe.");
            ventanaRegistroUsuario();
        }
    }

    private void mostrarAlerta(String mensaje) {
        Alert alerta = new Alert(AlertType.INFORMATION);
        alerta.setTitle("Verificación de usuario");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

 private void ventanaRegistroUsuario() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("registrar_usuario.fxml"));
        Parent root = loader.load();

        ControladorRegistroUsuario controlador = loader.getController();
        controlador.setNombreUsuario(nombreUsuarioAComprobar.getText().trim());

        Scene scene = new Scene(root);
        Stage modalStage = new Stage();
        modalStage.setScene(scene);
        modalStage.setTitle("Registrar Usuario");

        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.initOwner(nombreUsuarioAComprobar.getScene().getWindow());

        modalStage.showAndWait();

    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
