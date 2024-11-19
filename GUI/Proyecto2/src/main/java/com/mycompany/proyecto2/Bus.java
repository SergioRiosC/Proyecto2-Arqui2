package com.mycompany.proyecto2;

import java.util.HashMap;
import java.util.Map;

public class Bus {
    private Processor[] processors;

    // Estadísticas del bus
    private int invalidations = 0;
    private int readRequests = 0;
    private int writeRequests = 0;
    private Map<Integer, Integer> dataTransmitted = new HashMap<>(); // PE ID -> Bytes transmitidos

    // Esquema de arbitraje
    private int currentProcessor = 0; // Para round-robin
    private boolean usePriority = false; // Cambiar entre esquemas

    public Bus(Processor[] processors) {
        this.processors = processors;

        // Inicializar datos transmitidos para cada procesador
        for (int i = 0; i < processors.length; i++) {
            dataTransmitted.put(i, 0);
        }
    }

    // Cambiar esquema de arbitraje
    public void setArbitrationScheme(boolean priority) {
        this.usePriority = priority;
    }

    // Manejar una solicitud de lectura
    public CacheLine handleReadMiss(int address, int requestingProcessor) {
        readRequests++; // Incrementar estadística
        CacheLine sharedLine = null;

        for (Processor processor : processors) {
            if (processor.getCache().hasLine(address)) {
                sharedLine = new CacheLine(processor.getCache().load(address), MESIProtocol.SHARED);
                processor.getCache().invalidateLine(address); // Pasar a estado SHARED
                invalidations++; // Incrementar invalidaciones
            }
        }

        if (sharedLine == null) {
            // Simular carga desde memoria principal (256 bits -> 32 bytes)
            sharedLine = new CacheLine(0, MESIProtocol.EXCLUSIVE);
            dataTransmitted.put(requestingProcessor, dataTransmitted.get(requestingProcessor) + 32);
        }

        return sharedLine;
    }

    // Manejar una solicitud de escritura
    public void handleWriteMiss(int address, int requestingProcessor) {
        writeRequests++; // Incrementar estadística

        for (Processor processor : processors) {
            if (processor.getCache().hasLine(address)) {
                processor.getCache().invalidateLine(address); // Invalidar otras cachés
                invalidations++; // Incrementar invalidaciones
            }
        }

        // Incrementar datos transmitidos para escritura
        dataTransmitted.put(requestingProcessor, dataTransmitted.get(requestingProcessor) + 32);
    }

    // Seleccionar el procesador según el esquema de arbitraje
    public int selectProcessor() {
        if (usePriority) {
            // Esquema de prioridad: Procesador 0 tiene más prioridad
            for (int i = 0; i < processors.length; i++) {
                if (canAccessBus(i)) {
                    return i;
                }
            }
        } else {
            // Esquema Round-robin
            for (int i = 0; i < processors.length; i++) {
                int candidate = (currentProcessor + i) % processors.length;
                if (canAccessBus(candidate)) {
                    currentProcessor = (candidate + 1) % processors.length;
                    return candidate;
                }
            }
        }
        return -1; // Ningún procesador puede acceder al bus
    }

    // Verificar si un procesador puede acceder al bus (simulación)
    private boolean canAccessBus(int processorId) {
        // Para este ejemplo, asumimos que siempre pueden acceder
        return true;
    }

    // Métodos para obtener estadísticas
    public int getInvalidations() {
        return invalidations;
    }

    public int getReadRequests() {
        return readRequests;
    }

    public int getWriteRequests() {
        return writeRequests;
    }

    public Map<Integer, Integer> getDataTransmitted() {
        return dataTransmitted;
    }
}
