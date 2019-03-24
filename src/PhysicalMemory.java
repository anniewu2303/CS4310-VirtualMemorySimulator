/**
 * Author:        Joshua Chen
 *                Annie Wu
 *
 * Assignment:    Program 2
 * Class:         CS 4310 - Operating Systems
 * Instructor:    Dominick Atanasio
 * Date:          24 March 2019
 *
 * - Physical memory will be a two-dimensional array to simulate the page-frame# and the page of byte-addressable, byte-sized data.
 * - For instance, if physical memory can hold 16 pages of data and each page is 1kB,
 * then you would have a 2d array like: ram[16][1024].
 * - In each byte of ram you will store an integer value
 * (yes, an int is typically larger than a byte but this will make the project simpler)
 * <p>
 * - Physical memory address width is 12 bits
 */

public class PhysicalMemory {
    private int[][] ram;
    private static int DEFAULT_NUM_PAGE_FRAMES = 16;
    private static int DEFAULT_PAGE_SIZE = 256;    // 12 bits
    private int currPageFrameNum;

    public PhysicalMemory() {
        this(DEFAULT_NUM_PAGE_FRAMES, DEFAULT_PAGE_SIZE);
    }

    public PhysicalMemory(int numPageFrames, int pageSize) {
        this.ram = new int[numPageFrames][pageSize];
        this.currPageFrameNum = 0;
    }

    public int addEntry(String address) {
        int pfn = this.currPageFrameNum;

        int physAddr = Integer.parseInt(address, 16);
        ram[this.currPageFrameNum][0] = physAddr;

        if (currPageFrameNum == ram.length - 1) {
            currPageFrameNum = 0;
        } else {
            currPageFrameNum += 1;
        }
        return pfn;
    }
}
