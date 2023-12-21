package java_basic._1026_extends.c;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/27 14:16
 */

public class Computer {

    private String cpu;
    private int disk;
    private int memory;

    public Computer(String cpu, int disk, int memory) {
        this.cpu = cpu;
        this.disk = disk;
        this.memory = memory;
    }

    public String getDetails() {
        return "cpu=" + cpu + " memory=" + memory + " disk=" + disk;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public int getDisk() {
        return disk;
    }

    public void setDisk(int disk) {
        this.disk = disk;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }
}
