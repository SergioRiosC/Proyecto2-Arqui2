/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto2;

public class Instruction {
    public enum Type {
        LOAD, STORE, INC, DEC, JNZ
    }

    private Type type;
    private int register;
    private int address;
    private String label; // Para JNZ

    // Constructor para LOAD y STORE
    public Instruction(Type type, int register, int address) {
        this.type = type;
        this.register = register;
        this.address = address;
    }

    // Constructor para INC, DEC
    public Instruction(Type type, int register) {
        this(type, register, -1);
    }

    // Constructor para JNZ
    public Instruction(Type type, int register, String label) {
        this.type = type;
        this.register = register;
        this.label = label;
    }

    public Type getType() {
        return type;
    }

    public int getRegister() {
        return register;
    }

    public int getAddress() {
        return address;
    }

    public String getLabel() {
        return label;
    }
}

