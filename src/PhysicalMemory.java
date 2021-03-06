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
    public int addEntry(String address, boolean isDirty, int data) {
        int pfn = -1;
        int physAddr = Integer.parseInt(address, 16);

        if (this.head < ram.length - 1) {
            pfn = this.head;
            ram[this.head][0] = new PageFrame(isDirty, physAddr, data);
            moveHead();
        }
        return pfn;
    }

    /**
     * Get page frame number from page frame.
     *
     * @param pageFrame
     * @return
     */
    public int getPageFrameNum(PageFrame pageFrame) {
        int frameNumber = -1;
        for (int x = 0; x < 16; x++) {
            if (ram[x][0] == pageFrame) {
                frameNumber = x;
            }
        }
        return frameNumber;
    }

    /**
     * Returns the head pointer of the clock algorithm.
     *
     * @return
     */
    public PageFrame getHeadFrame() {
        return ram[head][0];
    }

    /**
     * Return int value of head.
     *
     * @return
     */
    public int getHead() {
        return this.head;
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

    /**
     * Return max number of page frames.
     *
     * @return
     */
    public int getNumPageFrames() {
        return ram.length;
    }
}
