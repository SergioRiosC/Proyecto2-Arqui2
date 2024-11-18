package com.mycompany.proyecto2;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    private int processorId;
    private Memory memory;
    private Bus bus;
    private Map<Integer, CacheLine> cacheLines;

    public Cache(int processorId, Memory memory) {
        this.processorId = processorId;
        this.memory = memory;
        this.cacheLines = new HashMap<>();
    }

    public void connectBus(Bus bus) {
        this.bus = bus;
    }

    public long load(int address) {
        if (cacheLines.containsKey(address)) {
            CacheLine line = cacheLines.get(address);
            if (!line.isValid()) {
                line = bus.handleReadMiss(address, processorId);
                cacheLines.put(address, line);
            }
            return line.getData();
        } else {
            CacheLine line = bus.handleReadMiss(address, processorId);
            cacheLines.put(address, line);
            return line.getData();
        }
    }

    public void store(int address, long data) {
        if (cacheLines.containsKey(address)) {
            CacheLine line = cacheLines.get(address);
            if (line.getState().equals(MESIProtocol.MODIFIED) || line.getState().equals(MESIProtocol.EXCLUSIVE)) {
                line.setData(data);
                line.setState(MESIProtocol.MODIFIED);
            } else {
                bus.handleWriteMiss(address, processorId);
                line.setData(data);
                line.setState(MESIProtocol.MODIFIED);
            }
        } else {
            bus.handleWriteMiss(address, processorId);
            CacheLine line = new CacheLine(data, MESIProtocol.MODIFIED);
            cacheLines.put(address, line);
        }
    }

    public boolean hasLine(int address) {
        return cacheLines.containsKey(address) && cacheLines.get(address).isValid();
    }

    public void invalidateLine(int address) {
        if (cacheLines.containsKey(address)) {
            cacheLines.get(address).setState(MESIProtocol.INVALID);
        }
    }
}
