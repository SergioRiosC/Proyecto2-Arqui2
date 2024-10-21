
package com.mycompany.mesi;
class Processor implements Runnable {
    private int id;
    private Cache cache;
    private Bus bus;
    private long[] registers; // 4 registros de 64 bits (REG0, REG1, REG2, REG3)
    private boolean running = true;
    private int programCounter = 0; // Para manejar las instrucciones secuenciales
    private String[] instructions;  // Instrucciones del programa cargadas.
    private MESI app;  // Referencia a la app gráfica

    public Processor(int id, Bus bus, String[] instructions, MESI app) {
        this.id = id;
        this.cache = new Cache();
        this.bus = bus;
        this.registers = new long[4]; // Inicializa 4 registros de 64 bits
        this.instructions = instructions;
        this.app = app;  // Guardamos la referencia de la app para usarla luego
    }

    @Override
    public void run() {
        // Simula la ejecución del programa
        while (running && programCounter < instructions.length) {
            String instruction = instructions[programCounter];
            executeInstruction(instruction);
            programCounter++;
        }
    }

    private void executeInstruction(String instruction) {
        String[] parts = instruction.split(" ");
        String opcode = parts[0];

        switch (opcode) {
            case "LOAD":
                int reg = Integer.parseInt(parts[1].replace("REG", ""));
                int address = Integer.parseInt(parts[2]);
                load(reg, address);
                break;
            case "STORE":
                reg = Integer.parseInt(parts[1].replace("REG", ""));
                address = Integer.parseInt(parts[2]);
                store(reg, address);
                break;
            case "INC":
                reg = Integer.parseInt(parts[1].replace("REG", ""));
                increment(reg);
                break;
            case "DEC":
                reg = Integer.parseInt(parts[1].replace("REG", ""));
                decrement(reg);
                break;
            case "JNZ":
                int newAddress = Integer.parseInt(parts[1]);
                if (registers[0] != 0) {
                    programCounter = newAddress - 1;  // Salta a la instrucción indicada
                }
                break;
            default:
                app.log("Instrucción desconocida: " + instruction);
                break;
        }
    }

    public void load(int reg, int address) {
        CacheLine line = cache.read(address, bus, this);
        registers[reg] = line.getData();
        System.out.println("Processor " + this.id + " loaded " + registers[reg] + " into REG" + reg);
        app.log("Processor " + this.id + " loaded " + registers[reg] + " into REG" + reg);
    }

    public void store(int reg, int address) {
        long data = registers[reg];  // Obtiene el valor del registro
        cache.write(address, data, bus, this);
        System.out.println("Processor " + id + " stored " + data + " from REG" + reg + " to address " + address);
        app.log("Processor " + id + " stored " + data + " from REG" + reg + " to address " + address);
    }

    public void increment(int reg) {
        registers[reg]++;
        System.out.println("Processor " + id + " incremented REG" + reg + " to " + registers[reg]);
        app.log("Processor " + id + " incremented REG" + reg + " to " + registers[reg]);
    }

    public void decrement(int reg) {
        registers[reg]--;
        System.out.println("Processor " + id + " decremented REG" + reg + " to " + registers[reg]);
        app.log("Processor " + id + " decremented REG" + reg + " to " + registers[reg]);
    }

    public void stop() {
        running = false;
    }
    
    
    public Cache getCache() {
        return cache;
    }
    
}




