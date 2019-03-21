/*
 Author:        Joshua Chen
                Annie Wu 
				
 Assignment:    Program 2 
 Class:         CS 4310 - Operating Systems 
 Instructor:    Dominick Atanasio 
 Date:          24 March 2019 


Table entry for TLB:
	| V-Page# | V | R | D | PageFrame# |
*/


public class TlbEntry {
    private String vpn;         // Virtual Page Number
    private boolean validBit, refBit, dirtyBit;
    private int pageFrameNum;

    public TlbEntry(String vpn, int pageFrameNum) {
        this.vpn = vpn;
        this.validBit = true;   // If in TLB, then it exists in the page table
        this.refBit = true;     // If in TLB, then it just got referenced
        this.dirtyBit = false;  // Not necessarily had it's value changed.
        this.pageFrameNum = pageFrameNum;
    }

    public String getVpn() {
        return vpn;
    }

    public void setVpn(String vpn) {
        this.vpn = vpn;
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
