
package com.mycompany.mesi;
import java.util.ArrayList;

class Bus {
    private ArrayList<Processor> processors;
    private Memory memory;

    public Bus(Memory memory) {
        processors = new ArrayList<>();
        this.memory = memory;
    }

    public void addProcessor(Processor processor) {
        processors.add(processor);
    }

    public CacheLine readFromMemory(int address, Processor requester) {
        for (Processor p : processors) {
            if (p != requester) {
                CacheLine line = p.getCache().getCacheLine(address);
                if (line != null && line.getState() == MESIState.MODIFIED) {
                    memory.write(address, line.getData());
                    line.setState(MESIState.SHARED);
                }
            }
        }
        long data = memory.read(address);
        return new CacheLine(address, data);
    }

    public void invalidateOtherCaches(int address, Processor requester) {
        for (Processor p : processors) {
            if (p != requester) {
                CacheLine line = p.getCache().getCacheLine(address);
                if (line != null) {
                    line.setState(MESIState.INVALID);
                }
            }
        }
    }
    public boolean isExclusive(int address, Processor requester) {
    for (Processor p : processors) {
        if (p != requester) {
            CacheLine line = p.getCache().getCacheLine(address);
            if (line != null && line.getState() != MESIState.INVALID) {
                return false;  // Otro procesador tiene la línea, no es exclusiva
            }
        }
    }
    return true;  // Ningún otro procesador tiene la línea, es exclusiva
    }

}

