/**
 * @author: Joshua Chen, Annie Wu
 * @date Mar 24, 2019
 * <p>
 * Assignment:  Project 2 - Virtual Memory Simulator
 * Class:       CS 4310 - Operating Systems
 * Instructor:  Dominick Atanasio
 * <p>
 * Physical Memory (RAM)
 * <p>
 * (Default) Holds 16 Pages
 * (Default) Page Size of 12 bits.
 */

public class PhysicalMemory {
    private PageFrame[][] ram;
    private static int DEFAULT_NUM_PAGE_FRAMES = 16;
    private static int DEFAULT_PAGE_SIZE = 256;    // 12 bits
    private int head;

    public PhysicalMemory() {
        this(DEFAULT_NUM_PAGE_FRAMES, DEFAULT_PAGE_SIZE);
    }

    public PhysicalMemory(int numPageFrames, int pageSize) {
        this.ram = new PageFrame[numPageFrames][pageSize];
        this.head = 0;
    }

    /**
     * Attempts to Add Entry into Page Frames.
     *
     * @param address
     * @return
     */
    public int addEntry(String address) {
        int pfn = -1;
        int physAddr = Integer.parseInt(address, 16);

        if (this.head < ram.length) {
            pfn = this.head;
            ram[this.head][0] = new PageFrame(physAddr);
            head += 1;
        }
        return pfn;
    }

    /**
     * Returns the head pointer of the clock algorithm.
     *
     * @return
     */
    public PageFrame getHead() {
        return ram[head][0];
    }

    /**
     * Move head pointer of the clock algorithm.
     */
    public void moveHead() {
        if (this.head == ram.length - 1) {
            head = 0;
        } else {
            head += 1;
        }
    }
}
