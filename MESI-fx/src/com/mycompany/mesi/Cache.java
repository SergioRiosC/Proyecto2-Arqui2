package com.mycompany.mesi;
import java.util.HashMap;

class Cache {
    private HashMap<Integer, CacheLine> cacheLines;
    private int cacheSize = 8; // 8 bloques de 32 bytes como mínimo.

    public Cache() {
        cacheLines = new HashMap<>();
    }

    public CacheLine read(int address, Bus bus, Processor processor) {
        CacheLine line = cacheLines.get(address);
        if (line == null || line.getState() == MESIState.INVALID) {
            line = bus.readFromMemory(address, processor);
            cacheLines.put(address, line);
            
            if (bus.isExclusive(address, processor)) {
            line.setState(MESIState.EXCLUSIVE);
            } else {
                line.setState(MESIState.SHARED);
            }
        }
        return line;
    }

    public void write(int address, long data, Bus bus, Processor processor) {
        CacheLine line = cacheLines.get(address);
        if (line == null || line.getState() == MESIState.INVALID) {
            line = bus.readFromMemory(address, processor);
            cacheLines.put(address, line);
            
            if (bus.isExclusive(address, processor)) {
            line.setState(MESIState.EXCLUSIVE);
            } else {
                line.setState(MESIState.SHARED);
            }
        }

        if (line.getState() == MESIState.EXCLUSIVE || line.getState() == MESIState.SHARED) {
            // Si la línea está en estado Exclusive o Shared, primero se invalida en otras cachés
            bus.invalidateOtherCaches(address, processor);
        }
            
        line.setData(data);
        line.setState(MESIState.MODIFIED);
    }
    
    public CacheLine getCacheLine(int i){
        return cacheLines.get(i);
    }
}
