package com.videojuego.modelos;

import com.videojuego.controladores.ControladorPrincipal;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;


/**
 * Clase vista que se encarga de mostrar la información de los escenarios.
 * @author David Muñoz - Eva Retamar
 * Licencia GPL v3. Fecha 03 2025
 */
public class Escenario {
    private static int ancho;
    private static int alto;
    private StackPane[][] mapa;
    private static final int LADO = 128;

    private int filaJugador;
    private int columnaJugador;

    private Image imgEscenario;

    public Escenario(Path rutaEscenario, Integer opcion) {
        imgEscenario = new Image(this.getClass().getResourceAsStream("/escenario.png"));
        cargarEscenarios(rutaEscenario, opcion);
    }

    /**
     * Método para cargar varios escenarios.
     * @param rutaEscenarios Lista de rutas de los arhivos de escenarios.
     */
    public void cargarEscenarios(Path rutaEscenario, Integer opcion) {
        int espacios = 0;
        int obstaculos = 0;
        int bordeSuperior = 0;
        int bordeInferior = 0;
        int bordeIzquierdo = 0;
        int bordeDerecho = 0;
        try {
            List<String> archivoEscenario = Files.readAllLines(rutaEscenario);
            
            String[] dimensiones = archivoEscenario.get(0).split(" ");
            ancho = Integer.parseInt(dimensiones[0]);
            alto = Integer.parseInt(dimensiones[1]);

            mapa = new StackPane[alto][ancho];
              
            for (int i = 1; i < alto; i++) {
                String linea = archivoEscenario.get(i);
                String[] partes = linea.split(" ");
                for(int j = 0; j < ancho; j++) {
                    String parte = partes[j];
                    int cantidad = Integer.parseInt(parte.substring(0, parte.length() - 1));
                    char casilla = parte.charAt(parte.length() - 1);
                    Rectangle2D recorte = getRecortePorCasilla(casilla);


                        for (int k = 0; k < cantidad; k++) {
                            ImageView baldosa = new ImageView(imgEscenario);

                            baldosa.setViewport(recorte);
                            baldosa.setFitWidth(LADO);
                            baldosa.setFitHeight(LADO);

                            StackPane celda = new StackPane(baldosa);
                            mapa[i][j] = celda;
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();            
        }
    }

    private Rectangle2D getRecortePorCasilla(char casilla) {
        switch (casilla) {
            case 'E': return new Rectangle2D(2*LADO, 0*LADO, LADO, LADO);
            case 'O': return new Rectangle2D(4*LADO, 2*LADO, LADO, LADO);
            case 'A': return new Rectangle2D(1*LADO, 5*LADO, LADO, LADO);
            case 'B': return new Rectangle2D(1*LADO, 3*LADO, LADO, LADO);
            case 'I': return new Rectangle2D(2*LADO, 4*LADO, LADO, LADO);
            case 'D': return new Rectangle2D(0*LADO, 4*LADO, LADO, LADO);
            default: return new Rectangle2D(0, 0, LADO, LADO);  // Casilla por defecto
        }
    }
   
    /**
     * Método con el cuál el jugador aparece en la primera posición del mapa que no sea ni borde ni obstáculo.
     */
    public void posicionarJugador(){
        for(int i = 0; i < mapa.length; i++){
            for(int j = 0; j < mapa[i].length; j++){
                if(getCelda(i, j) == 'E'){
                    filaJugador = i;
                    columnaJugador = j;
                    return;
                }
            }
        }
    }

    /**
     * Método que muestra al jugador en el mapa una vez carga por priemra vez o cuando se actualice su posición
     * en el mapa.
     */
    public void mostrarMapaConJugador() {
        for (int i = 0; i < mapa.length; i++) {
            for (int j = 0; j < mapa[i].length; j++) {
                if (i == filaJugador && j == columnaJugador) {
                    ImageView ivjugador = new ImageView(new Image(getClass().getResourceAsStream("/jugador.png")));
                    ivjugador.setFitWidth(60);
                    ivjugador.setPreserveRatio(true);
                    Rectangle2D vpJugador = new Rectangle2D(3*128, 1*160, 128, 160);
                    ivjugador.setViewport(vpJugador);

                    StackPane celda = mapa[i][j];
                    celda.getChildren().clear();  
                    celda.getChildren().add(ivjugador);  

                    return;
                }
            }
        }
    }

    /**
     * Método que recibe la tecla pulsada por el usuario y modifica sus coordenadas para moverlo a través
     * del mapa.
     * @param tecla Tecla que pulsa el usuario.
     */
    public void moverJugador(char tecla) {
        Integer nuevaFila = filaJugador;
        Integer nuevaColumna = columnaJugador;

        switch(tecla) {
            case 'w':
                if (filaJugador > 0) {  // Verifica si no sale del límite superior
                    nuevaFila--;
                }
                break;
            case 'a':
                if (columnaJugador > 0) {  // Verifica si no sale del límite izquierdo
                    nuevaColumna--;
                }
                break;
            case 's':
                if (filaJugador < mapa.length - 1) {  // Verifica si no sale del límite inferior
                    nuevaFila++;
                }
                break;
            case 'd':
                if (columnaJugador < mapa[0].length - 1) {  // Verifica si no sale del límite derecho
                    nuevaColumna++;
                }
                break;
        }
        // Verifica si la nueva posición tiene un obstáculo
        if (verificarPosicion(nuevaFila, nuevaColumna)) {
            filaJugador = nuevaFila;
            columnaJugador = nuevaColumna;
            mostrarMapaConJugador();
        }
    }

    /**
     * Método para verificar que si el jugador colisiona con un obstáculo o marco
     * activa la función para eliminar vida del jugador.
     */
    public boolean verificarPosicion(int fila, int columna) {
        StackPane celda = mapa[fila][columna];
        ImageView baldosa = (ImageView) celda.getChildren().get(0);

        Rectangle2D recorte = (Rectangle2D) baldosa.getViewport();

        if (esObstaculo(recorte)) {
            System.out.println("¡Te has encontrado con un obstáculo! Pierdes una vida.");

        // Pausa para que el jugador vea la colisión
        /*try {
            Thread.sleep(1000);  // 1000 milisegundos = 1 segundo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        return true;  
        }
        return false; 
    }    

    /**
    * Método para verificar si el recorte corresponde a un obstáculo o borde.
    * @param recorte El recorte de la celda.
    * @return true si el recorte es de un obstáculo, false si no.
    */
    private boolean esObstaculo(Rectangle2D recorte) {
        return (recorte.getMinX() == 4*LADO && recorte.getMinY() == 2*LADO) || 
                (recorte.getMinX() == 1*LADO && recorte.getMinY() == 5*LADO) || 
                (recorte.getMinX() == 1*LADO && recorte.getMinY() == 3*LADO) || 
                (recorte.getMinX() == 2*LADO && recorte.getMinY() == 4*LADO) || 
                (recorte.getMinX() == 0*LADO && recorte.getMinY() == 4*LADO);   
    }

    // Método para obtener el número de filas del mapa
    public int getFilas() {
        return mapa.length;
    }

    // Método para obtener el número de columnas del mapa
    public int getColumnas() {
        return mapa[0].length;
    }

    // Método para obtener el símbolo en una celda específica
    public char getCelda(int fila, int columna) {
        StackPane celda = mapa[fila][columna];
        ImageView baldosa = (ImageView) celda.getChildren().get(0);
        Rectangle2D recorte = (Rectangle2D) baldosa.getViewport();
    
        if (recorte.getMinX() == 2 * LADO && recorte.getMinY() == 0 * LADO) {
            return 'E'; // Espacio vacío
        } else if (recorte.getMinX() == 4 * LADO && recorte.getMinY() == 2 * LADO) {
            return 'O'; // Obstáculo
        } else if (recorte.getMinX() == 1 * LADO && recorte.getMinY() == 5 * LADO) {
            return 'A'; // Borde superior
        } else if (recorte.getMinX() == 1 * LADO && recorte.getMinY() == 3 * LADO) {
            return 'B'; // Borde inferior
        } else if (recorte.getMinX() == 2 * LADO && recorte.getMinY() == 4 * LADO) {
            return 'I'; // Borde izquierdo
        } else if (recorte.getMinX() == 0 * LADO && recorte.getMinY() == 4 * LADO) {
            return 'D'; // Borde derecho
        } else {
            return ' '; // Caso por defecto (vacío o no reconocido)
    }
    }
}