/**
 * @author: Joshua Chen, Annie Wu
 * @date Mar 24, 2019
 * <p>
 * Assignment:  Project 2 - Virtual Memory Simulator
 * Class:       CS 4310 - Operating Systems
 * Instructor:  Dominick Atanasio
 * <p>
 * Represents Single Page Table Entries in the Page Table.
 * <p>
 * Each TLB Entry Contains:
 * Valid Bit | Reference Bit | Dirty Bit | Page Frame Number (pfn)
 */

public class PageTableEntry {
    private boolean validBit, refBit, dirtyBit;
    private int pageFrameNum;

    public PageTableEntry(int pageFrameNum, boolean isDirty) {
        this.validBit = true;   // If in TLB, then it exists in the page table
        this.refBit = true;     // If in TLB, then it just got referenced
        this.dirtyBit = isDirty;  // Not necessarily had it's value changed.
        this.pageFrameNum = pageFrameNum;
    }


    public boolean isValid() {
        return validBit;
    }

    public void setValidBit(boolean validBit) { this.validBit = validBit; }

    public boolean isRef() {
        return refBit;
    }

    public void setRefBit(boolean refBit) {
        this.refBit = refBit;
    }

    public boolean isDirty() {
        return dirtyBit;
    }

    public void setDirtyBit(boolean dirtyBit) {
        this.dirtyBit = dirtyBit;
    }

    public int getPageFrameNum() {
        return pageFrameNum;
    }

    public void setPageFrameNum(int pageFrameNum) { this.pageFrameNum = pageFrameNum; }
}
