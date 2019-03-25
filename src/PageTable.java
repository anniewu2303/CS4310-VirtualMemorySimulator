/**
 * @author: Joshua Chen, Annie Wu
 * @date Mar 24, 2019
 * <p>
 * Assignment:  Project 2 - Virtual Memory Simulator
 * Class:       CS 4310 - Operating Systems
 * Instructor:  Dominick Atanasio
 * <p>
 * Page Table (Virtual Memory)
 */

public class PageTable {

    private PageTableEntry[] pageTableEntries;
    private int currEntry = 0; //currEntry in page pageTableEntries
    private static int DEFAULT_MAX_ENTRIES = 256;

    public PageTable() {
        this(DEFAULT_MAX_ENTRIES);
    }

    public PageTable(int maxEntries) {
        pageTableEntries = new PageTableEntry[maxEntries];
    }

    /**
     * Returns a PageTableEntry if it exists in the Page Table.
     * Returns null if it does not.
     *
     * @param vpn
     * @return
     */
    public PageTableEntry getPageTableEntry(String vpn) {
        int pageNum = Integer.parseInt(vpn, 16);
        PageTableEntry entry = null;

        //if the page number is valid in the page pageTableEntries, get the frame number
        if (pageTableEntries[pageNum] != null && pageTableEntries[pageNum].isValid()) {
            entry = pageTableEntries[pageNum];
        }
        return entry;
    }

    /**
     * Page Table Adds or Updates Page Table Entry bits.
     *
     * @param vpn
     * @param frameNumber
     * @param isDirty
     */
    public void update(String vpn, int frameNumber, boolean isDirty) {
        int pageNum = Integer.parseInt(vpn, 16);

        if (pageTableEntries[pageNum] == null) {
            pageTableEntries[pageNum] = new PageTableEntry(frameNumber, isDirty);
        } else {
            pageTableEntries[pageNum].setPageFrameNum(frameNumber);
            pageTableEntries[pageNum].setValidBit(true);          //valid
            pageTableEntries[pageNum].setRefBit(true);            //just referenced
            pageTableEntries[pageNum].setDirtyBit(isDirty);
        }
    }

    /**
     * Gets dirty bit from pageTableEntries at this vpn.
     *
     * @param vpn
     * @return
     */
    public boolean getDirtyBit(String vpn) {
        int pageNum = Integer.parseInt(vpn, 16);
        return pageTableEntries[pageNum].isDirty();
    }

    /**
     * Set dirty bit to true at this vpn.
     *
     * @param vpn
     */
    public void setDirtyBit(String vpn) {
        int pageNum = Integer.parseInt(vpn, 16);
        pageTableEntries[pageNum].setDirtyBit(true);
    }

    /**
     * Gets reference bit from the pageTableEntries at this vpn.
     *
     * @param vpn
     * @return
     */
    public boolean getRefBit(String vpn) {
        int pageNum = Integer.parseInt(vpn, 16);
        return pageTableEntries[pageNum].isRef();
    }

    /**
     * Sets reference bit to true at this vpn.
     *
     * @param vpn
     */
    public void setRefBit(String vpn) {
        int pageNum = Integer.parseInt(vpn, 16);
        pageTableEntries[pageNum].setRefBit(true);
    }

    /**
     * Reset reference bit to false at this vpn.
     *
     * @param vpn
     */
    public void resetRefBit(String vpn) {
        int pageNum = Integer.parseInt(vpn, 16);
        pageTableEntries[pageNum].setRefBit(false);
    }

    /**
     * Reset reference bit to false at this page number (index in array)
     *
     * @param pageNum
     */
    public void resetRefBit(int pageNum) {
        pageTableEntries[pageNum].setRefBit(false);
    }

    /**
     * Get currEntry in pageTableEntries
     *
     * @return
     */
    public int getTableIndex() {
        return currEntry;
    }
}
