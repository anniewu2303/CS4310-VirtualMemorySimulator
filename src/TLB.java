/**
 * @author:        Joshua Chen
 *                  Annie Wu 
 * 
 * Assignment:      Program 2 
 * Class:           CS 4310 - Operating Systems 
 * Instructor:      Dominick Atanasio 
 * Date:            24 March 2019 
 * 
 * 
* - The TLB will be a one-dimensional array if TlbEntry.
* - A TlbEntry consists of a virtual page number and a frame number. 
* - The TLB is small and must be scanned on every lookup. 
* - The arrays used to implement the page table and TLB will be arrays of data structures that represent the tables’ entries.
* 
* Table entry for the TLB:
*       | V-Page# | V | R | D | PageFrame# |
*/

public class TLB {
    private TlbEntry tlbEntries[];
    private int currEntry;
    private final static int DEFAULT_MAX_ENTRIES = 16;
    
    public TLB() {
        this(DEFAULT_MAX_ENTRIES);
    }

    public TLB(int maxEntries) {
        this.tlbEntries = new TlbEntry[maxEntries];
        this.currEntry = 0;
    }

    /**
     * Check TLB for page frame number
     * @param pageNumber
     * @return frameNumber if it exists
     */
    public int checkTLB(int pageNumber) {
        int frameNumber = -1;
        
        for (int i=0; i<DEFAULT_MAX_ENTRIES; i++) {
            if (tlbEntries[i].getPageNumber() == pageNumber && tlbEntries[i].isValid()) {
                frameNumber = tlbEntries[i].getPageFrameNumber();
            }
        }
        
        return frameNumber;
    }

    /**
     * Update this page number in TLB with this frame number
     * @param pageNumber
     * @param frameNumber 
     */
    public void update(int pageNumber, int frameNumber) {
        for (int i=0; i<16; i++) {
            if (tlbEntries[i].getPageNumber() == pageNumber) {
                tlbEntries[i].setPageFrameNumber(frameNumber);
                tlbEntries[i].setValidBit(true);   //valid 
                tlbEntries[i].setRefBit(true);     //just referneced 
                tlbEntries[i].setDirtyBit(false);  //not written to 
            }
        }
    }

    /**
     * Add entry to TLB 
     * @param vpn
     * @param pageFrameNum 
     */
    public void addEntry(String vpn, int pageFrameNum) {
        tlbEntries[currEntry] = new TlbEntry(vpn, pageFrameNum);

        if (currEntry == tlbEntries.length - 1) {
            currEntry = 0;
        } else {
            currEntry += 1;
        }
    }
}
