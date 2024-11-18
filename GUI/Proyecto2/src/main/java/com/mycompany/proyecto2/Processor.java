package com.mycompany.proyecto2;

public class Processor {
    private int id;
    private Cache cache;
    private long[] registers = new long[4]; // REG0, REG1, REG2, REG3
    private Bus bus;

    public Processor(int id, Memory memory) {
        this.id = id;
        this.cache = new Cache(id, memory);
    }

    public void connectBus(Bus bus) {
        this.bus = bus;
        cache.connectBus(bus);
    }

    public void executeInstruction(Instruction instruction) {
        switch (instruction.getType()) {
            case LOAD:
                registers[instruction.getRegister()] = cache.load(instruction.getAddress());
                break;
            case STORE:
                cache.store(instruction.getAddress(), registers[instruction.getRegister()]);
                break;
            case INC:
                registers[instruction.getRegister()]++;
                break;
            case DEC:
                registers[instruction.getRegister()]--;
                break;
            case JNZ:
                if (registers[instruction.getRegister()] != 0) {
                    // Implementar salto
                }
                break;
        }
    }

    public Cache getCache() {
        return cache;
    }
}
