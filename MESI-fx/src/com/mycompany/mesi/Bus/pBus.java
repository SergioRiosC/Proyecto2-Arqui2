package com.mycompany.mesi.Bus;

import com.mycompany.mesi.Memory.Memory;
import com.mycompany.mesi.Processor.Cache.MESIState;
import com.mycompany.mesi.Processor.Cache.CacheLine;
import com.mycompany.mesi.Processor.pProcessor;
import java.util.ArrayList;

public class pBus {
    private ArrayList<pProcessor> processors;
    private Memory memory;

    public pBus(Memory memory) {
        processors = new ArrayList<>();
        this.memory = memory;
    }

    public void addProcessor(pProcessor processor) {
        processors.add(processor);
    }

    public CacheLine readFromMemory(int address, pProcessor requester) {
        for (pProcessor p : processors) {
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

    public void invalidateOtherCaches(int address, pProcessor requester) {
        for (pProcessor p : processors) {
            if (p != requester) {
                CacheLine line = p.getCache().getCacheLine(address);
                if (line != null) {
                    line.setState(MESIState.INVALID);
                }
            }
        }
    }
    public boolean isExclusive(int address, pProcessor requester) {
    for (pProcessor p : processors) {
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
