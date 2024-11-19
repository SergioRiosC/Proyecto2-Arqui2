package com.mycompany.proyecto2;

import org.json.JSONArray;
import org.json.JSONObject;
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
        if (cacheLines.containsKey(address)) {
            CacheLine line = cacheLines.get(address);
            return line.isValid(); // Línea válida si su estado no es INVALID
        }
        return false;
    }

    public void invalidateLine(int address) {
        if (cacheLines.containsKey(address)) {
            CacheLine line = cacheLines.get(address);
            line.setState(MESIProtocol.INVALID); // Cambiar estado a INVALID
        }
    }

    public JSONArray getCacheStateAsJson() {
        JSONArray cacheArray = new JSONArray();

        for (Map.Entry<Integer, CacheLine> entry : cacheLines.entrySet()) {
            JSONObject lineJson = new JSONObject();
            lineJson.put("address", entry.getKey());
            lineJson.put("state", entry.getValue().getState());
            lineJson.put("data", entry.getValue().getData());
            cacheArray.put(lineJson);
        }

        return cacheArray;
    }
}

