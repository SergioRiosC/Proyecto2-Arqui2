package com.mycompany.mesi.Processor.Register;

/**
 * Represents a set of registers, each identified by a unique index.
 * Allows for initializing a custom number of registers or a default set of 4.
 * Provides methods to get and set values of individual registers in the set. 
 */
public class RegisterSet {
    private final Register[] registers; // Array of registers

    /**
     * Default constructor that creates a RegisterSet with 4 registers.
     */
    public RegisterSet() {
        this(4); // Calls the other constructor with the default value of 4
    }

    /**
     * Constructor that allows specifying the number of registers.
     * If the specified number is invalid (<= 0), defaults to 4 registers.
     *
     * @param numRegisters The number of registers to create in the set.
     */
    public RegisterSet(int numRegisters) {
        // If the number of registers is invalid, set to default value of 4
        if (numRegisters <= 0) {
            System.out.println("Invalid number of registers. Setting default to 4 registers.");
            numRegisters = 4;
        }
        
        registers = new Register[numRegisters];
        for (int i = 0; i < numRegisters; i++) {
            registers[i] = new Register(i); // Sets the index as the register ID
        }
    }

    /**
     * Retrieves the value stored in a specific register.
     *
     * @param index The index of the register.
     * @return The value of the register as a 64-bit long integer.
     * @throws IllegalArgumentException if the index is out of range.
     */
    public long getValue(int index) {
        validateIndex(index);
        return registers[index].getValue();
    }

    /**
     * Sets a specific value in a register.
     *
     * @param index The index of the register.
     * @param value The value to set in the register as a 64-bit long integer.
     * @throws IllegalArgumentException if the index is out of range.
     */
    public void setValue(int index, long value) {
        validateIndex(index);
        registers[index].setValue(value);
    }

    /**
     * Validates that a given index is within the valid range for the register array.
     *
     * @param index The index to validate.
     * @throws IllegalArgumentException if the index is out of range.
     */
    private void validateIndex(int index) {
        if (index < 0 || index >= registers.length) {
            throw new IllegalArgumentException("Index out of range: " + index);
        }
    }

    /**
     * Returns a string representation of the RegisterSet.
     * This includes the ID and value of each register in the set.
     *
     * @return A formatted string representing each register in the set.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("RegisterSet: \n");
        for (Register register : registers) {
            sb.append(register.toString()).append("\n");
        }
        return sb.toString();
    }
}
