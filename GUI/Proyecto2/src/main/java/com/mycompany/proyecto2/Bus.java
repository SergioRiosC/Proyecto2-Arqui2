package com.mycompany.proyecto2;

public class Bus {
    private Processor[] processors;

    public Bus(Processor[] processors) {
        this.processors = processors;
    }

    public CacheLine handleReadMiss(int address, int requestingProcessor) {
        CacheLine sharedLine = null;

        for (Processor processor : processors) {
            if (processor.getCache().hasLine(address)) {
                sharedLine = new CacheLine(processor.getCache().load(address), MESIProtocol.SHARED);
                processor.getCache().invalidateLine(address); // Pasar a estado SHARED
            }
        }

        if (sharedLine == null) {
            long data = 0; // Simulación: cargar de la memoria compartida
            sharedLine = new CacheLine(data, MESIProtocol.EXCLUSIVE);
        }

        return sharedLine;
    }

    public void handleWriteMiss(int address, int requestingProcessor) {
        for (Processor processor : processors) {
            if (processor.getCache().hasLine(address)) {
                processor.getCache().invalidateLine(address);
            }
        }
    }
}
