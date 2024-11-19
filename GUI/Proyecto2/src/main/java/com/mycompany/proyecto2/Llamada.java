package com.mycompany.proyecto2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Llamada {
    public static void main(String[] args) {
        // Valores predeterminados (por si el archivo config.txt no los tiene)
        int numProcessors = 4;
        int memorySize = 256;

        // Leer configuración desde el archivo
        try (BufferedReader br = new BufferedReader(new FileReader("./GUI/Proyecto2/src/main/java/com/mycompany/proyecto2/config/config.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    switch (key) {
                        case "numProcessors":
                            numProcessors = Integer.parseInt(value);
                            break;
                        case "memorySize":
                            memorySize = Integer.parseInt(value);
                            break;
                        default:
                            System.err.println("Clave desconocida en config.txt: " + key);
                    }
                } else {
                    System.err.println("Formato inválido en config.txt: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error leyendo el archivo config.txt: " + e.getMessage());
        }

        // Crear memoria compartida
        Memory sharedMemory = new Memory(memorySize);

        // Crear procesadores
        Processor[] processors = new Processor[numProcessors];
        for (int i = 0; i < numProcessors; i++) {
            processors[i] = new Processor(i, sharedMemory);
        }

        // Crear bus
        Bus bus = new Bus(processors);

        // Conectar procesadores al bus
        for (Processor processor : processors) {
            processor.connectBus(bus);
        }

        // Ejecutar simulación desde archivo de instrucciones
        Simulation simulation = new Simulation(processors, sharedMemory, bus);
        simulation.run();

        // Generar archivo de salida
        simulation.generateOutput("./GUI/Proyecto2/src/main/java/com/mycompany/proyecto2/resultados/resultados.json");
    }
}
