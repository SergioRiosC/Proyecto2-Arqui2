package com.mycompany.mesi;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MESI extends Application {
    private TextArea logArea;
    private Thread t1, t2, t3, t4; // Los hilos de los 4 procesadores

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("MESI Cache Simulator");

        // Configuración del área de texto
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setWrapText(true);  // Permite que el texto se ajuste en la línea

        VBox layout = new VBox();
        layout.getChildren().add(logArea);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);

        // Evento para cerrar correctamente la ventana
        primaryStage.setOnCloseRequest(event -> {
            // Detener los hilos de los procesadores
            if (t1 != null && t1.isAlive()) t1.interrupt();
            if (t2 != null && t2.isAlive()) t2.interrupt();
            if (t3 != null && t3.isAlive()) t3.interrupt();
            if (t4 != null && t4.isAlive()) t4.interrupt();
            Platform.exit();  // Salir de la aplicación JavaFX
            System.exit(0);   // Cerrar el programa completamente
        });

        primaryStage.show();

        startSimulation();
    }

    private void startSimulation() {
        Memory memory = new Memory();
        Bus bus = new Bus(memory);

        // Instrucciones de prueba para cada procesador
        String[] instructionsP1 = {
            "LOAD REG0 100", 
            "INC REG0", 
            "STORE REG0 100",
            "JNZ 0" // Salta si REG0 != 0
        };

        String[] instructionsP2 = {
            "LOAD REG1 100",
            "DEC REG1",
            "STORE REG1 100"
        };

        String[] instructionsP3 = {
            "LOAD REG2 200", 
            "INC REG2", 
            "STORE REG2 200",
            "JNZ 0" // Salta si REG2 != 0
        };

        String[] instructionsP4 = {
            "LOAD REG3 200",
            "DEC REG3",
            "STORE REG3 200"
        };

        // Crear los 4 procesadores
        Processor p1 = new Processor(1, bus, instructionsP1, this);
        Processor p2 = new Processor(2, bus, instructionsP2, this);
        Processor p3 = new Processor(3, bus, instructionsP3, this);
        Processor p4 = new Processor(4, bus, instructionsP4, this);

        bus.addProcessor(p1);
        bus.addProcessor(p2);
        bus.addProcessor(p3);
        bus.addProcessor(p4);

        // Ejecutar los 4 procesadores en hilos separados
        t1 = new Thread(p1);
        t2 = new Thread(p2);
        t3 = new Thread(p3);
        t4 = new Thread(p4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }

    // Método auxiliar para imprimir en la interfaz gráfica
    public void log(String message) {
        Platform.runLater(() -> {
            logArea.appendText(message + "\n");
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
