/**
 Author:        Joshua Chen
                Annie Wu 
				
 Assignment:    Program 2 
 Class:         CS 4310 - Operating Systems 
 Instructor:    Dominick Atanasio 
 Date:          24 March 2019 
 
 
- The TLB will be a one-dimensional array if TlbEntry.
- A TlbEntry consists of a virtual page number and a frame number. 
- The TLB is small and must be scanned on every lookup. 
- The arrays used to implement the page table and TLB will be arrays of data structures that represent the tables’ entries.

Table entry for the TLB:
    | V-Page# | V | R | D | PageFrame# |
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
     * FIFO Replacement
     * @param vpn
     * @param pageFrameNum
     */
    public void addEntry(String vpn, int pageFrameNum, boolean isDirty) {
        tlbEntries[currEntry] = new TlbEntry(vpn, pageFrameNum, isDirty);

        if (currEntry == tlbEntries.length - 1) {
            currEntry = 0;
        } else {
            currEntry += 1;
        }
    }


    public TlbEntry getTLBEntry(String vpn) {
        TlbEntry entry = null;

        for (int i = 0; i < tlbEntries.length; i++) {
            if (tlbEntries[i] != null && vpn.equals(tlbEntries[i].getVpn())) {
                entry = tlbEntries[i];
                break;
            }
        }
        return entry;
    }
}
