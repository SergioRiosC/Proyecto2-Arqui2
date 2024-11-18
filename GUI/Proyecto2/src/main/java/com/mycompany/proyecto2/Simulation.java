package com.mycompany.proyecto2;

import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class Simulation {
    private Processor[] processors;
    private Memory memory;
    private Bus bus;

    public Simulation(Processor[] processors, Memory memory, Bus bus) {
        this.processors = processors;
        this.memory = memory;
        this.bus = bus;
    }

    public void run() {
        // Implementar lógica de ejecución de instrucciones
    }

    public void generateOutput(String filename) {
        try (FileWriter file = new FileWriter(filename)) {
            JSONObject output = new JSONObject();
            // Agregar estadísticas
            file.write(output.toString(4));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

