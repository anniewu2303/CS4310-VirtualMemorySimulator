/**
 * @author Joshua Chen
 * Date Created: Mar 24, 2019
 */
public class PageFrame {
    private boolean refBit;
    private int physAddr;

    public PageFrame(int physAddr) {
        this.refBit = true;
        this.physAddr = physAddr;
    }

    public boolean isRefBit() {
        return refBit;
    }

    public void setRefBit(boolean refBit) {
        this.refBit = refBit;
    }

    public int getPhysAddr() {
        return physAddr;
    }

    public void setPhysAddr(int physAddr) {
        this.physAddr = physAddr;
    }
}
