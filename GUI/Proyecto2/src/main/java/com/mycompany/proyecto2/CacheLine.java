package com.mycompany.proyecto2;

public class CacheLine {
    private long data;
    private String state;

    public CacheLine(long data, String state) {
        this.data = data;
        this.state = state;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isValid() {
        return !state.equals(MESIProtocol.INVALID);
    }

    public boolean isWritable() {
        return state.equals(MESIProtocol.MODIFIED) || state.equals(MESIProtocol.EXCLUSIVE);
    }
}
