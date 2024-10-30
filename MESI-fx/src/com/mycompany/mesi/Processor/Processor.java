package com.mycompany.mesi.Processor;

import com.mycompany.mesi.Bus.Bus;
import com.mycompany.mesi.Processor.Cache.*;
import com.mycompany.mesi.Processor.Register.RegisterSet;
import com.mycompany.mesi.Processor.InstructionMemory.InstructionMemory;

/**
 * Represents a processor in a multi-processor system with a cache and register set.
 * The processor can execute basic instructions (LOAD, STORE, INC, DEC, JNZ) 
 * and interacts with a shared bus and instruction memory.
 */
public class Processor {
    private final int id; // Processor ID
    private final RegisterSet registerSet; // Set of registers
    private final InstructionMemory instructionMemory; // Memory holding instructions for the processor
    private final Cache cache; // Cache for storing data in memory
    private final Bus bus; // Shared bus for inter-processor communication
    private int programCounter = 0; // Keeps track of the current instruction
    private boolean running = true; // Indicates whether the processor is active

    /**
     * Constructs a Processor instance with a specific ID, shared bus, instruction memory, and register set.
     *
     * @param id The unique identifier for the processor.
     * @param bus The shared Bus instance for inter-processor communication.
     * @param instructionMemory The instruction memory containing the program.
     * @param registerSet The set of registers for storing intermediate values.
     */
    public Processor(int id, Bus bus, InstructionMemory instructionMemory, RegisterSet registerSet) {
        this.id = id;
        this.cache = new Cache();
        this.bus = bus;
        this.instructionMemory = instructionMemory;
        this.registerSet = registerSet;
    }

    /**
     * Loads data from a specific memory address into a register.
     * If the data is not in the cache or is invalid, requests it from the bus.
     *
     * @param reg The register number to load the data into.
     * @param address The memory address to load the data from.
     */
    public void load(int reg, int address) {
        CacheLine line = cache.getLine(address);

        // Check if the cache line is valid; if not, request from bus
        if (line == null || line.getState() == MESIState.INVALID) {
            line = bus.readFromMemory(address, this);

            // Set cache line state based on bus response
            if (bus.isExclusive(address, this)) {
                cache.setLine(address, line.getData(), MESIState.EXCLUSIVE);
            } else {
                cache.setLine(address, line.getData(), MESIState.SHARED);
            }
        }

        // Load data into the specified register
        registerSet.setValue(reg, line.getData());
        System.out.println("Processor " + id + " loaded " + line.getData() + " from address " + address + " into REG" + reg);
    }

    /**
     * Stores data from a register to a specific memory address.
     * If the cache line is not valid, requests it from the bus, and updates its state.
     *
     * @param reg The register number containing the data to store.
     * @param address The memory address to store the data at.
     */
    public void store(int reg, int address) {
        long data = registerSet.getValue(reg); // Get the data from the specified register
        CacheLine line = cache.getLine(address);

        // Check if the cache line is valid; if not, request from bus
        if (line == null || line.getState() == MESIState.INVALID) {
            line = bus.readFromMemory(address, this);
            cache.setLine(address, line.getData(), MESIState.EXCLUSIVE);
        }

        // Invalidate other caches if the line is SHARED or EXCLUSIVE
        if (line.getState() == MESIState.SHARED || line.getState() == MESIState.EXCLUSIVE) {
            bus.invalidateOtherCaches(address, this);
        }

        // Update the cache with the new data and set state to MODIFIED
        cache.updateLine(address, data);
        System.out.println("Processor " + id + " stored " + data + " from REG" + reg + " to address " + address);
    }

    /**
     * Increments the value in the specified register by 1.
     *
     * @param reg The register number to increment.
     */
    public void increment(int reg) {
        long value = registerSet.getValue(reg);
        registerSet.setValue(reg, value + 1);
        System.out.println("Processor " + id + " incremented REG" + reg + " to " + registerSet.getValue(reg));
    }

    /**
     * Decrements the value in the specified register by 1.
     *
     * @param reg The register number to decrement.
     */
    public void decrement(int reg) {
        long value = registerSet.getValue(reg);
        registerSet.setValue(reg, value - 1);
        System.out.println("Processor " + id + " decremented REG" + reg + " to " + registerSet.getValue(reg));
    }

    /**
     * Jumps to a specified instruction address if the value in REG0 is not zero.
     *
     * @param address The instruction address to jump to.
     */
    public void jumpIfNotZero(int address) {
        long value = registerSet.getValue(0); // Use REG0 to check the condition
        if (value != 0) {
            programCounter = address;
            System.out.println("Processor " + id + " jumping to instruction " + address + " as REG0 != 0");
        } else {
            System.out.println("Processor " + id + " did not jump as REG0 == 0");
        }
    }

    /**
     * Executes the next instruction in the instruction memory.
     * Interprets and performs actions based on the opcode (LOAD, STORE, INC, DEC, JNZ).
     */
    public void executeNextInstruction() {
        if (running && programCounter < instructionMemory.getMaxSize()) {
            // Get the current instruction
            String instruction = instructionMemory.getInstruction(programCounter);
            programCounter++;

            // Parse and execute the instruction
            String[] parts = instruction.split(" ");
            String opcode = parts[0];
            int reg, address;

            switch (opcode) {
                case "LOAD" -> {
                    reg = Integer.parseInt(parts[1].replace("REG", ""));
                    address = Integer.parseInt(parts[2]);
                    load(reg, address);
                }
                case "STORE" -> {
                    reg = Integer.parseInt(parts[1].replace("REG", ""));
                    address = Integer.parseInt(parts[2]);
                    store(reg, address);
                }
                case "INC" -> {
                    reg = Integer.parseInt(parts[1].replace("REG", ""));
                    increment(reg);
                }
                case "DEC" -> {
                    reg = Integer.parseInt(parts[1].replace("REG", ""));
                    decrement(reg);
                }
                case "JNZ" -> {
                    address = Integer.parseInt(parts[1]);
                    jumpIfNotZero(address);
                }
                default -> System.out.println("Processor " + id + ": Unknown instruction - " + instruction);
            }
        }
    }

    /**
     * Stops the processor from executing further instructions.
     * Sets the running flag to false.
     */
    public void stop() {
        running = false;
    }

    /**
     * Retrieves the cache associated with this processor.
     *
     * @return The Cache instance used by this processor.
     */
    public Cache getCache() {
        return cache;
    }

    /**
     * Checks if the processor is currently running.
     *
     * @return True if the processor is running, otherwise false.
     */
    public boolean isRunning() {
        return running;
    }
    
}
