package com.mycompany.mesi;

enum MESIState {
    MODIFIED,  // La línea ha sido modificada, es diferente en la caché y es la única copia válida.
    EXCLUSIVE, // La línea está en una caché y coincide con la memoria, sin copias en otras cachés.
    SHARED,    // La línea puede estar en varias cachés, pero es consistente con la memoria.
    INVALID    // La línea es inválida y debe obtenerse nuevamente de la memoria.
}

class CacheLine {
    private int address;
    private long data;  // Datos de 64 bits
    private MESIState state;

    public CacheLine(int address, long data) {
        this.address = address;
        this.data = data;
        this.state = MESIState.INVALID;
    }

    public int getAddress() {
        return address;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public MESIState getState() {
        return state;
    }

    public void setState(MESIState state) {
        this.state = state;
    }
}
