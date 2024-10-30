package com.mycompany.mesi.Processor.InstructionMemory;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the memory that stores a list of instructions for the processor.
 * Allows for setting, retrieving, and loading instructions, with a maximum 
 * instruction size of 64 characters and a configurable maximum memory size.
 */
public class InstructionMemory {
    private List<String> instructions; // List of instructions
    private int maxSize;               // Maximum size of the instruction memory
    private final int maxInstructionLength = 64; // Maximum length of a single instruction

    /**
     * Constructs an InstructionMemory with a specified maximum size.
     * Initializes the memory with empty instructions.
     *
     * @param maxSize The maximum number of instructions the memory can hold.
     * @throws IllegalArgumentException if maxSize is less than or equal to zero.
     */
    public InstructionMemory(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("The memory size must be greater than zero.");
        }
        this.maxSize = maxSize;
        this.instructions = new ArrayList<>(maxSize);
        
        // Initialize the list with empty instructions
        for (int i = 0; i < maxSize; i++) {
            instructions.add("");
        }
    }

    /**
     * Sets a specific instruction at the given index in the memory.
     *
     * @param index The position at which to set the instruction.
     * @param instruction The instruction string to set.
     * @throws IndexOutOfBoundsException if the index is out of range.
     * @throws IllegalArgumentException if the instruction length exceeds 64 characters.
     */
    public void setInstruction(int index, String instruction) {
        if (index < 0 || index >= maxSize) {
            throw new IndexOutOfBoundsException("Instruction index out of range.");
        }
        if (instruction.length() > maxInstructionLength) {
            throw new IllegalArgumentException("The instruction exceeds the maximum length of 64 characters.");
        }
        instructions.set(index, instruction);
    }

    /**
     * Retrieves the instruction stored at the specified index in the memory.
     *
     * @param index The position of the instruction to retrieve.
     * @return The instruction string at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public String getInstruction(int index) {
        if (index < 0 || index >= maxSize) {
            throw new IndexOutOfBoundsException("Instruction index out of range.");
        }
        return instructions.get(index);
    }

    /**
     * Loads multiple instructions into the memory from a single block of instructions.
     * The instructions in the block should be separated by semicolons (;).
     * Each instruction is limited to 64 characters. If the memory limit is reached, 
     * no additional instructions will be loaded.
     *
     * @param instructionBlock The string containing multiple instructions separated by semicolons.
     */
    public void loadInstructions(String instructionBlock) {
        // Split the string by semicolons
        String[] instructionArray = instructionBlock.split(";");
        int currentIndex = 0;

        for (String instruction : instructionArray) {
            // Trim spaces from each instruction and check length constraints
            instruction = instruction.trim();
            
            if (instruction.length() > maxInstructionLength) {
                System.out.println("Warning: Instruction exceeds max length and will be skipped: " + instruction);
                continue;
            }
            if (currentIndex >= maxSize) {
                System.out.println("Instruction memory full. No more instructions will be loaded.");
                break;
            }

            // Set the instruction at the current index
            setInstruction(currentIndex, instruction);
            currentIndex++;
        }
    }

    /**
     * Displays all instructions currently stored in the memory.
     * Prints each instruction with its respective position in the memory.
     */
    public void displayInstructions() {
        System.out.println("Instruction Memory:");
        for (int i = 0; i < instructions.size(); i++) {
            System.out.println("Position " + i + ": " + instructions.get(i));
        }
    }

    /**
     * Retrieves the maximum size of the instruction memory.
     *
     * @return The maximum number of instructions the memory can hold.
     */
    public int getMaxSize() {
        return maxSize;
    }
}
