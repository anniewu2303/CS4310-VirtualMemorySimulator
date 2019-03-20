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


public class TlbEntries {
    private String vpn;         // Virtual Page Number
    private int frame_num;
    private boolean validBit, refBit, dirtyBit;

    public TlbEntries(String vpn, int frame_num) {
        this.vpn = vpn;
        this.frame_num = frame_num;
        this.validBit = true;   // If in TLB, then it exists in the page table
        this.refBit = true;     // If in TLB, then it just got referenced
        this.dirtyBit = false;  // Not necessarily had it's value changed.
    }

    public String getVpn() {
        return vpn;
    }

    public int getFrame_num() {
        return frame_num;
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
