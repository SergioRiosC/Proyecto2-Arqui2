package com.mycompany.mesi.Processor.InstructionMemory;

/**
 * Class to test the functionality of the InstructionMemory class.
 * This class runs various tests to verify the correct behavior of 
 * methods in the InstructionMemory class, including setting, getting, 
 * loading, and displaying instructions.
 * 
 * Tests include:
 * - Initialization with a specific size
 * - Setting and retrieving valid instructions
 * - Handling instructions longer than the allowed length
 * - Loading multiple instructions from a block
 * - Checking boundary conditions for valid and invalid indexes
 */
public class InstructionMemoryTest {

    /**
     * Runs all tests for the InstructionMemory class.
     * Creates an instance of InstructionMemory and tests various functionalities,
     * including setting and getting instructions, handling maximum length,
     * and loading instructions from a long block.
     */
    public void runTests() {
        // Initialize InstructionMemory with a size of 5
        InstructionMemory instructionMemory = new InstructionMemory(16);
        System.out.println("InstructionMemory initialized with a size of 16.\n");

        // Test setting and getting instructions
        testSetAndGetInstruction(instructionMemory);
        
        // Test loading multiple instructions from a single block
        testLoadInstructions(instructionMemory);
        
        // Test handling of instructions that exceed the maximum allowed length
        testMaxInstructionLength(instructionMemory);
        
        // Display all instructions in the memory
        instructionMemory.displayInstructions();
        
        System.out.println("\nAll InstructionMemory tests completed.");
    }

    /**
     * Tests setting and retrieving valid instructions in the InstructionMemory.
     *
     * @param instructionMemory The InstructionMemory instance to test.
     */
    private void testSetAndGetInstruction(InstructionMemory instructionMemory) {
        System.out.println("Testing set and get instructions...");

        instructionMemory.setInstruction(0, "LOAD REG0 100");
        System.out.println("Instruction at index 0 (expected 'LOAD REG0 100'): " + instructionMemory.getInstruction(0));

        instructionMemory.setInstruction(1, "STORE REG1 200");
        System.out.println("Instruction at index 1 (expected 'STORE REG1 200'): " + instructionMemory.getInstruction(1));

        instructionMemory.setInstruction(2, "INC REG2");
        System.out.println("Instruction at index 2 (expected 'INC REG0'): " + instructionMemory.getInstruction(2));

        instructionMemory.setInstruction(3, "DEC REG3");
        System.out.println("Instruction at index 3 (expected 'DEC REG1'): " + instructionMemory.getInstruction(3));

        instructionMemory.setInstruction(4, "JNZ 0");
        System.out.println("Instruction at index 4 (expected 'JNZ 0'): " + instructionMemory.getInstruction(4));

        System.out.println("Set and get instructions test completed.\n");
    }

    /**
     * Tests loading multiple instructions into the InstructionMemory from a block
     * where each instruction is separated by a semicolon (;).
     *
     * @param instructionMemory The InstructionMemory instance to test.
     */
    private void testLoadInstructions(InstructionMemory instructionMemory) {
        System.out.println("Testing loading instructions from a block...");

        // Updated instruction block with semicolons between each instruction
        String instructionBlock = "LOAD REG4 444; STORE REG3 333; INC REG2; DEC REG1; JNZ 2; STORE REG0 000; JNZ 0;";
        instructionMemory.loadInstructions(instructionBlock);

        System.out.println("Instructions loaded from block:");
        for (int i = 0; i < instructionMemory.getMaxSize(); i++) {
            System.out.println("Instruction at index " + i + ": " + instructionMemory.getInstruction(i));
        }

        System.out.println("Loading instructions from block test completed.\n");
    }

    /**
     * Tests handling of instructions that exceed the maximum allowed length.
     *
     * @param instructionMemory The InstructionMemory instance to test.
     */
    private void testMaxInstructionLength(InstructionMemory instructionMemory) {
        System.out.println("Testing handling of instructions exceeding max length...");

        try {
            // This instruction is intentionally longer than 64 characters
            String longInstruction = "LOAD REG2 100 LOAD REG0 100 LOAD REG3 100 LOAD REG1 100 LOAD REG1 300"; // More than 64 chars
            instructionMemory.setInstruction(0, longInstruction);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception for instruction length: " + e.getMessage());
        }

        System.out.println("Max instruction length test completed.\n");
    }

}
