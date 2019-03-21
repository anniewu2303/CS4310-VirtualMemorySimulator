/**
 * @author Joshua Chen
 * Date Created: Mar 21, 2019
 */
public class PageTableEntry {
    private int pageFrameNum;
    private boolean validBit, refBit, dirtyBit;

    public PageTableEntry(int pageFrameNum) {
        this.pageFrameNum = pageFrameNum;
        this.validBit = true;   // If in TLB, then it exists in the page table
        this.refBit = true;     // If in TLB, then it just got referenced
        this.dirtyBit = false;  // Not necessarily had it's value changed.
    }

    public int getPageFrameNum() {
        return pageFrameNum;
    }

    public boolean isValidBit() {
        return validBit;
    }

    public void setValidBit(boolean validBit) {
        this.validBit = validBit;
    }

    public boolean isRefBit() {
        return refBit;
    }

    public void setRefBit(boolean refBit) {
        this.refBit = refBit;
    }

    public boolean isDirtyBit() {
        return dirtyBit;
    }

    public void setDirtyBit(boolean dirtyBit) {
        this.dirtyBit = dirtyBit;
    }
}
