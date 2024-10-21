
package com.mycompany.mesi;
import java.util.HashMap;

class Memory {
    private HashMap<Integer, Long> memory;

    public Memory() {
        memory = new HashMap<>();
    }

    public long read(int address) {
        return memory.getOrDefault(address, 0L);
    }

    public void write(int address, long data) {
        memory.put(address, data);
    }
}


