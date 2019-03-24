/**
 * @author: Joshua Chen, Annie Wu
 * @date Mar 24, 2019
 * <p>
 * Assignment:  Project 2 - Virtual Memory Simulator
 * Class:       CS 4310 - Operating Systems
 * Instructor:  Dominick Atanasio
 * <p>
 * Translation Lookaside Buffer
 * One-Dimensional Array of TlbEntry.
 * TLB (Default) Contains 16 Entries.
 * TLB uses a FIFO Replacement Algorithm.
 */

public class TLB {
    private TlbEntry[] tlbEntries;
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
     *
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
