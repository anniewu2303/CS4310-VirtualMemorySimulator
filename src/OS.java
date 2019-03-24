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

    public OS() {
        ram = new PhysicalMemory();
    }

    // Clock Algorithm
    public int addEntry(String address) {
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

    private void evict(PageFrame pageToEvict) {
        /* EVICT PAGE HERE*/
    }
}
