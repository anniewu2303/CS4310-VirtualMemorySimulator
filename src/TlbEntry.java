/**
 * @author:     Joshua Chen, Annie Wu
 * @date        Mar 24, 2019
 *
 * Assignment:  Project 2 - Virtual Memory Simulator
 * Class:       CS 4310 - Operating Systems
 * Instructor:  Dominick Atanasio
 *
 * Represents Single Entries in the TLB.
 * Each TLB Entry Contains:
 *          Virtual Page Number (vpn) | Valid Bit | Reference Bit | Dirty Bit | Page Frame Number (pfn)
 */

public class TlbEntry {
    private String vpn;         // Virtual Page Number
    private boolean validBit, refBit, dirtyBit;
    private int pageFrameNum;

    public TlbEntry(String vpn, int pageFrameNum, boolean isDirty) {
        this.vpn = vpn;
        this.validBit = true;   // If in TLB, then it exists in the page table
        this.refBit = true;     // If in TLB, then it just got referenced
        this.dirtyBit = isDirty;
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
}
