/**
 Author:        Joshua Chen
                Annie Wu 
				
 Assignment:    Program 2 
 Class:         CS 4310 - Operating Systems 
 Instructor:    Dominick Atanasio 
 Date:          24 March 2019 


- When a hard miss occurs, it’s the job of the OS to replace the page and write the evicted page 
back to the drive if the dirty bit is set. The CPU should trap to the OS when hard misses occur.
- The MMU is responsible to set the r-bit and the d-bit; the OS is responsible for unsetting them 
in the page table but the MMU controls the TLB
- The OS should unset the r-bit of all page table entries after the CPU processes 10 instructions
- This simulates the hardware clock interrupt; it should reset the d-bit in the page table when
a page has been written back to the disk.

*/

public class OS {
    private PhysicalMemory ram;

    public OS() {
        ram = new PhysicalMemory();
    }

    public int addEntry(String address) {
        int pfn = ram.addEntry(address);

        // Clock is Full
        if (pfn == -1) {
            PageFrameNode evictedPage = null;

            while (evictedPage == null) {
                PageFrameNode temp = ram.getHead();
                if (temp.isRef()) {
                    temp.setRefBit(false);
                } else {
                    evictedPage = temp;
                }
                ram.moveHead();
            }

            /* EVICT PAGE HERE*/
        }

        pfn = ram.addEntry(address);
        return pfn;
    }
}
