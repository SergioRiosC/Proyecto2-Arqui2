package com.mycompany.proyecto2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.nio.file.*;
import java.util.Map;

public class Simulation {
    private Processor[] processors;
    private Memory memory;
    private Bus bus;
    private int stepCounter = 0; // Contador para los pasos

    public Simulation(Processor[] processors, Memory memory, Bus bus) {
        this.processors = processors;
        this.memory = memory;
        this.bus = bus;
    }

    // Método principal de la simulación
    public void run() {
        // Limpiar el directorio de resultados antes de comenzar
        clearResultsDirectory("./GUI/Proyecto2/src/main/java/com/mycompany/proyecto2/resultados");

        try (BufferedReader reader = new BufferedReader(new FileReader("./GUI/Proyecto2/src/main/java/com/mycompany/proyecto2/config/input.txt"))) {
            String line;
            int instructionIndex = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String type = parts[0];
                int reg = Integer.parseInt(parts[1]);
                int address = parts.length > 2 ? Integer.parseInt(parts[2]) : -1;

                Instruction.Type instructionType = Instruction.Type.valueOf(type);
                Instruction instruction = new Instruction(instructionType, reg, address);

                // Ejecutar la instrucción en el procesador correspondiente
                Processor processor = processors[instructionIndex % processors.length];
                try {
                    processor.executeInstruction(instruction, Map.of()); // Usar etiquetas si aplica
                } catch (JumpException e) {
                    // Manejar saltos (si hay)
                    instructionIndex = e.getTargetInstructionIndex();
                    continue;
                }

                // Generar archivo JSON después de cada paso
                generateStepOutput("./GUI/Proyecto2/src/main/java/com/mycompany/proyecto2/resultados/resultados" + (++stepCounter) + ".json");

                instructionIndex++;
            }
        } catch (IOException e) {
            System.err.println("Error leyendo archivo input.txt: " + e.getMessage());
        }
    }

    // Limpiar el directorio de resultados al inicio
    private void clearResultsDirectory(String directoryPath) {
        try {
            Files.walk(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .forEach(File::delete);
            System.out.println("Directorio de resultados limpiado.");
        } catch (IOException e) {
            System.err.println("Error limpiando el directorio de resultados: " + e.getMessage());
        }
    }

    // Generar archivo JSON para cada paso
    private void generateStepOutput(String filename) {
        try (FileWriter file = new FileWriter(filename)) {
            JSONObject output = new JSONObject();

            // Agregar estado de los procesadores
            JSONArray processorsArray = new JSONArray();
            for (Processor processor : processors) {
                JSONObject processorJson = new JSONObject();
                processorJson.put("id", processor.getId());
                processorJson.put("registers", processor.getRegisters()); // Agregar registros
                processorJson.put("cache", processor.getCache().getCacheStateAsJson());
                processorsArray.put(processorJson);
            }
            output.put("processors", processorsArray);

            // Agregar estadísticas del bus
            JSONObject busStats = new JSONObject();
            busStats.put("read_requests", bus.getReadRequests());
            busStats.put("write_requests", bus.getWriteRequests());
            busStats.put("invalidations", bus.getInvalidations());
            JSONObject dataTransmitted = new JSONObject();
            for (Map.Entry<Integer, Integer> entry : bus.getDataTransmitted().entrySet()) {
                dataTransmitted.put("PE_" + entry.getKey(), entry.getValue());
            }
            busStats.put("data_transmitted", dataTransmitted);
            output.put("bus_stats", busStats);

            // Escribir archivo JSON
            file.write(output.toString(4)); // Formato legible
            System.out.println("Archivo generado: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
