/**
 * Author:        Joshua Chen
 *                Annie Wu 
 * 
 * Assignment:    Program 2 
 * Class:         CS 4310 - Operating Systems 
 * Instructor:    Dominick Atanasio 
 * Date:          24 March 2019 
 * 
 * 
 * - Your CPU will read the test file VM addresses and hand them to the MMU for fetching or writing. 
 *      - If the address is preceded by a zero, then the MMU should only read value. 
 *      - If the address is preceded by a one, then the address will be followed by an integer value 
 *      that needs to be written to physical memory. 
 * 
 * - The MMU should set the r-bit on a read or write of data in the TLB. 
 * - The MMU should set the dirty bit on a write.
 * 
 * - Before the MMU evicts a record in the TLB, the CPU traps to the OS on a Hard Miss, or the CPU
 * interrupts for a clock interrupt, the MMU should update the page table with the current records(s) in the TLB.
 * 
 * - If the MMU is evicting a record in the TLB it will only need to update that one record in the page table, 
 * otherwise, it should update all records in the page table with those in the TLB. 
 * 
 * - When the MMU encounters a soft or hard miss, if the TLB is full it will need to overwrite a record in the TLB with the
 * entry of the page being called for after the CPU traps to the OS. 
 * - You can use the FIFO replacement algorithm for this.
 * 
 */

public class MMU {


    private CSV csv;
    private TLB tlb;
    private TlbEntry tlbEntries[];
    private PhysicalMemory memory;
    private PageTable table;
    private OS os;

    public void getVpnInfo(int rw, String vpn, int value) {

        //int = hex / 16 
        //first two hex (8 bits) is page number 
        int pageNumber = Integer.parseInt(vpn.substring(0,2), 16);
        int frameNumber = getFrameNumber(vpn, pageNumber);
        //next two hex (8 bits) is offset in the page 
        int offset = Integer.parseInt(vpn.substring(2, 4), 16); 
        
        //set r-bit and d-bit for rw 
        for (int i=0; i<16; i++) {
            if (tlbEntries[i].getPageNumber() == pageNumber && tlbEntries[i].isValid()) {
                //PageTable and TLB sets r-bit on read or write 
                tlbEntries[i].setRefBit(true);
                table.setRefBit(pageNumber); //set true at this page number
                
                //TLB sets d-bit on write
                if (rw == 1) {
                    tlbEntries[i].setDirtyBit(true);
                    table.setDirtyBit(pageNumber); //set true at this page number
                }
                return;
            }
        }
        
        switch (rw) {
            //read = 0 
            case 0: 
                //read value from memory to output in CSV 
                csv.value(memory.getPhysicalMemory(frameNumber, offset));
                break;
        
            //write = 1 
            case 1: 
                //write value to memory and output in CSV 
                memory.setPhysicalMemory(frameNumber, offset, value);
                csv.value(value);
                break;
        }
    }

    /**
     * Get the page frame number 
     * @param pageNumber
     * @return frameNumber
     */
    private int getFrameNumber(String vpn, int pageNumber) {
       
        //check TLB first
        int frameNumber = tlb.checkTLB(pageNumber);
        
        if (frameNumber == -1) { //not in TLB = miss 
            //check page table 
            frameNumber = table.checkPageTable(Integer.toString(pageNumber));
            
            tlb.addEntry(vpn, frameNumber);
            
            if (frameNumber == -1) { //not in page table either
                //hard miss
                csv.hard(1);
                csv.soft(0);
                csv.hit(0);
                
                os.hardMiss(vpn);
                table.update(pageNumber, frameNumber);
            }
            else { //in page table
                //soft miss
                csv.soft(1);
                csv.hard(0);
                csv.hit(0);
                //csv.evictedPageNumber(  );
                csv.dirtyEvictedPage(0);
            }
        }
        else { //in TLB = hit 
            csv.hit(1);
            csv.hard(0);
            csv.soft(0);
            
            //csv.evictedPageNumber(  );
            csv.dirtyEvictedPage(0);
        }
        
        return frameNumber;
    }

}
