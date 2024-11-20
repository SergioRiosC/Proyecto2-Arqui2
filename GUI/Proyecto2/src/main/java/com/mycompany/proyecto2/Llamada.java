package com.mycompany.proyecto2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Llamada {
    private static int numProcessors;
    private static int memorySize;

    public Llamada() {
        numProcessors = 4; // Valores predeterminados
        memorySize = 256;  // Valores predeterminados
    }

    public void iniciar() {
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

        System.out.println("Configuración cargada: numProcessors=" + numProcessors + ", memorySize=" + memorySize);

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

        // Finalización de la simulación
        System.out.println("Simulación completada. Archivos JSON generados en cada paso.");
    }
}
