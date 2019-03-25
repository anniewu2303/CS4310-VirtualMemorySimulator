import java.io.IOException;

/**
 * @author: Joshua Chen, Annie Wu
 * @date Mar 24, 2019
 * <p>
 * Assignment:  Project 2 - Virtual Memory Simulator
 * Class:       CS 4310 - Operating Systems
 * Instructor:  Dominick Atanasio
 * <p>
 * Operating System
 * <p>
 * Has "Access" to the Physical Memory (RAM).
 * Implements the Clock Page Replacement Algorithm.
 */

public class OS {
    private PhysicalMemory ram;
    private PageTable pageTable;
    private TlbEntry tlbEntry[];
    private MMU mmu;
    private CSV csv;
    private int clockIndex = 0;

    public OS() {
        ram = new PhysicalMemory();
    }

    // Clock Algorithm
    public int addEntry(String address) throws IOException {
        int pfn = ram.addEntry(address);

        // Clock is Full
        if (pfn == -1) {
            PageFrame pageToEvict = null;

            while (pageToEvict == null) {
                PageFrame temp = ram.getHead();

                // If R bit is 1, set to 0 and move head
                if (temp.isRef()) {
                    temp.setRefBit(false);
                }
                // If R bit is 0, evict page
                else {
                    pageToEvict = temp;
                }
                ram.moveHead();
            }
            evict(pageToEvict);
        }
        pfn = ram.addEntry(address);
        return pfn;
    }

    private void evict(PageFrame pageFrameToEvict) {
        int frameToEvict = pageFrameToEvict.getPhysAddr();
        int frameNumber = ram.getPageFrameNum(pageFrameToEvict);
        String vpn = Integer.toHexString(frameNumber);
        int pageNum = Integer.parseInt(vpn, 16);

        //if dirty bit is set, write evicted page back to disk
        if (pageTable.getDirtyBit(vpn) == true) {
            pageTable.update(vpn, -1, false);
        }

//        csv.evictedPageNumber();
//        csv.dirty(pageTable.getDirtyBit(vpn));
    }

    //OS resets all ref bits in page table every 10 instructions
    public void resetAllRef() {
        for (int x=0; x<256; x++) {
            pageTable.resetRefBit(x);
        }
    }
}
