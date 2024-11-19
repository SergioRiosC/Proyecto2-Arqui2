package com.mycompany.proyecto2;

public class JumpException extends RuntimeException {
    private final int targetInstructionIndex;

    public JumpException(int targetInstructionIndex) {
        this.targetInstructionIndex = targetInstructionIndex;
    }

    public int getTargetInstructionIndex() {
        return targetInstructionIndex;
    }
}
