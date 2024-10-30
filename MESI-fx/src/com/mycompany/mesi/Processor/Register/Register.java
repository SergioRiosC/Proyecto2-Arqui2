package com.mycompany.mesi.Processor.Register;

/**
 * Represents a 64-bit register with a unique identifier and a stored value.
 * Provides methods to get and set the register's value.
 */
public class Register {
    private final int id;   // Unique identifier of the register
    private long value;     // Value stored in the 64-bit register

    /**
     * Constructs a Register with a specified ID and initializes its value to 0.
     *
     * @param id The unique identifier of the register.
     */
    public Register(int id) {
        this.id = id;
        this.value = 0;
    }

    /**
     * Gets the unique identifier of the register.
     *
     * @return The ID of the register as an integer.
     */
    public int getId() {
        return id;
    }

    /**
     * Retrieves the value currently stored in the register.
     *
     * @return The 64-bit value stored in the register.
     */
    public long getValue() {
        return value;
    }

    /**
     * Sets a specific value in the register.
     *
     * @param value The value to be set in the register. It is a 64-bit long integer.
     */
    public void setValue(long value) {
        this.value = value;
    }

    /**
     * Returns a string representation of the register.
     * This representation includes the register's ID and its current value.
     *
     * @return A string containing the register ID and value.
     */
    @Override
    public String toString() {
        return "Registro ID: " + id + ", Valor: " + value;
    }
}
