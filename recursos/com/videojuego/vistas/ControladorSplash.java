package recursos.com.videojuego.vistas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class ControladorSplash {

    @FXML
    private Button btnComenzar;

    @FXML
    private void ventanaAccesoUsuario() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("solicitar_usuario.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            Stage stage = (Stage) btnComenzar.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Acceso Usuario");
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
