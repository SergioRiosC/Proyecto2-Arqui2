package com.mycompany.proyecto2;

public class Memory {
    private long[] memory;

    public Memory(int size) {
        memory = new long[size];
    }

    public long read(int address) {
        return memory[address];
    }

    public void write(int address, long data) {
        memory[address] = data;
    }
}
