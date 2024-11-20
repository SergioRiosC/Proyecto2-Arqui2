package com.mycompany.proyecto2;

import java.util.Map;

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

    public void executeInstruction(Instruction instruction, Map<String, Integer> labels) {
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
                    // Realizar el salto a la etiqueta especificada
                    String label = instruction.getLabel();
                    if (labels.containsKey(label)) {
                        int nextInstructionIndex = labels.get(label);
                        throw new JumpException(nextInstructionIndex); // Lanzar excepción para manejar el salto
                    } else {
                        System.err.println("Etiqueta no encontrada: " + label);
                    }
                }
                break;

            default:
                System.err.println("Instrucción desconocida: " + instruction.getType());
        }
    }

    public Cache getCache() {
        return cache;
    }

    public int getId() {
        return id;
    }

    public long[] getRegisters() {
        return registers;
    }
}
