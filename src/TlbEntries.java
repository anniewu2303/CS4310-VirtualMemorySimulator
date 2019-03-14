/**
 * @author Joshua Chen
 * Date Created: Mar 14, 2019
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
