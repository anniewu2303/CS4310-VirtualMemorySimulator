/**
 * @author Joshua Chen
 * Date Created: Mar 21, 2019
 */
public class PageTable {
    private PageTableEntry[] pageTableEntries;
    private int currEntry;
    private final static int DEFAULT_MAX_ENTRIES = 256; // 8 bit

    public PageTable() {
        this(DEFAULT_MAX_ENTRIES);
    }

    public PageTable(int maxEntries) {
        this.pageTableEntries = new PageTableEntry[maxEntries];
        this.currEntry = 0;
    }

    public void addEntry(int pageFrameNum) {
        pageTableEntries[currEntry] = new PageTableEntry(pageFrameNum);

        if (currEntry == pageTableEntries.length - 1) {
            currEntry = 0;
        } else {
            currEntry += 1;
        }
    }
}
