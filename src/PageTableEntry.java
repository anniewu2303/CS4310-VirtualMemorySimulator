/*
 Author:        Joshua Chen
                Annie Wu 
				
 Assignment:    Program 2 
 Class:         CS 4310 - Operating Systems 
 Instructor:    Dominick Atanasio 
 Date:          24 March 2019 


Table entry for the virtual page table:
	| V | R | D | PageFrame# |
*/

public class PageTableEntry {
    private boolean validBit, refBit, dirtyBit;
    private int pageFrameNum;

    public PageTableEntry(boolean valid, boolean ref, boolean dirty, int frame) {
        this.validBit = true;   // If in TLB, then it exists in the page table
        this.refBit = true;     // If in TLB, then it just got referenced
        this.dirtyBit = false;  // Not necessarily had it's value changed.
        this.pageFrameNum = pageFrameNum;
    }


    public boolean isValid() {
        return validBit;
    }

    public void setValidBit(boolean validBit) {
        this.validBit = validBit;
    }

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

    public void setPageFrameNum(int pageFrameNum) {
        this.pageFrameNum = pageFrameNum;
    }
}
